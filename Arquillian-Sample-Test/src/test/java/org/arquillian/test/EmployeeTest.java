package org.arquillian.test;

import org.arquillian.sample.service.impl.EmployeeService;
import org.arquillian.sample.service.IEmployeeService;
import org.arquillian.sample.common.Logger;
import org.arquillian.sample.common.PerformanceInterceptor;
import org.arquillian.sample.entity.IBaseEntity;
import org.arquillian.sample.entity.impl.BaseEntity;
import org.arquillian.sample.entity.impl.Employee;
import org.arquillian.sample.facade.IBaseFacade;
import org.arquillian.sample.facade.IEmployeeFacade;
import org.arquillian.sample.facade.impl.BaseFacade;
import org.arquillian.sample.facade.impl.EmployeeFacade;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ejb.EJB;
import java.io.IOException;
import java.util.List;

@ExtendWith(ArquillianExtension.class)
public class EmployeeTest {
    public static final String MAIN_NAME = "Employee";
    public static final String JAR = ".jar";
    public static final String WAR = ".war";
    public static final String EAR = ".ear";
    public static final String TEST_APPLICATION_XML = "test-application.xml";
    public static final String MAIN_JAVA_PACKAGE = "org.arquillian.sample";

    @Deployment
    public static Archive<?> createDeployment() throws IOException {
        final JavaArchive jar = ShrinkWrap.create(JavaArchive.class, MAIN_NAME + JAR)
                .addClass(IEmployeeService.class)
                .addClass(EmployeeService.class)
                .addClass(PerformanceInterceptor.class)
                .addClass(Logger.class)
                .addClass(IEmployeeFacade.class)
                .addClass(EmployeeFacade.class)
                .addClass(IBaseFacade.class)
                .addClass(BaseFacade.class)
                .addClass(Employee.class)
                .addClass(IBaseEntity.class)
                .addClass(BaseEntity.class)
//                .addPackages(true, MAIN_JAVA_PACKAGE)
                .addAsManifestResource("test-persistence.xml", "persistence.xml");
        final WebArchive war = ShrinkWrap.create(WebArchive.class, MAIN_NAME + WAR)
                .addClass(EmployeeTest.class);
        final EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class, MAIN_NAME + EAR)
                .setApplicationXML(TEST_APPLICATION_XML)
                .addAsModule(jar).addAsModule(war);
        return ear;
    }

    public static final String REMOTE_QUALIFIER = "arquillian-wildfly-remote";

    public static final String[] NAME_LISTS = {
            "Kevin",
            "Alice",
            "Kelly"
    };
    @EJB
    private IEmployeeService employeeService;

    @Test
    public void selectEmployeeTest() {
        List<Employee> employees = employeeService.selectEmployee();
        for (int i = 0; i < employees.size(); i++) {
            Assertions.assertEquals(NAME_LISTS[i], employees.get(i).getName());
        }
    }
}
