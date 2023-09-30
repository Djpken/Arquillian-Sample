package org.arquillian.sample.service;

import org.arquillian.sample.entity.impl.Employee;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface IEmployeeService {
    public List<Employee> selectEmployee();
}
