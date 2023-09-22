package org.arquillian.test;

import org.arquillian.example.EmployeeService;
import org.arquillian.example.IEmployeeService;
import org.arquillian.example.common.Logger;
import org.arquillian.example.common.PerformanceInterceptor;
import org.arquillian.example.entity.IBaseEntity;
import org.arquillian.example.entity.impl.BaseEntity;
import org.arquillian.example.entity.impl.Employee;
import org.arquillian.example.facade.IBaseFacade;
import org.arquillian.example.facade.IEmployeeFacade;
import org.arquillian.example.facade.impl.BaseFacade;
import org.arquillian.example.facade.impl.EmployeeFacade;
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
    public static final String JAR_NAME = MAIN_NAME + ".jar";
    public static final String WAR_NAME = MAIN_NAME + ".war";
    public static final String EAR_NAME = MAIN_NAME + ".ear";
    public static final String TEST_APPLICATION_XML = "test-application.xml";

    @Deployment
    public static Archive<?> createDeployment() throws IOException {
        final JavaArchive jar = ShrinkWrap.create(JavaArchive.class, JAR_NAME)
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
        final WebArchive war = ShrinkWrap.create(WebArchive.class, WAR_NAME)
                .addClass(EmployeeTest.class);
        final EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class, EAR_NAME)
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
            Assertions.assertEquals(NAME_LISTS[i],employees.get(i).getName());
        }
    }
}
