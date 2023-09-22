package org.arquillian.example;


import org.arquillian.example.common.PerformanceInterceptor;
import org.arquillian.example.entity.impl.Employee;
import org.arquillian.example.facade.IEmployeeFacade;

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
