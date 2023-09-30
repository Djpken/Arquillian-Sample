package org.arquillian.sample.service.impl;


import org.arquillian.sample.common.PerformanceInterceptor;
import org.arquillian.sample.entity.impl.Employee;
import org.arquillian.sample.facade.IEmployeeFacade;
import org.arquillian.sample.service.IEmployeeService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless
@Interceptors({PerformanceInterceptor.class})
public class EmployeeService implements IEmployeeService {
    @EJB
    private IEmployeeFacade employeeFacade;
    @Override
    public List<Employee> selectEmployee() {
        List<Employee> all = employeeFacade.findAll();
        return all;
    }
}
