package org.arquillian.example;

import org.arquillian.example.entity.impl.Employee;

import javax.ejb.EJB;
import javax.ejb.Remote;
import java.util.List;

@Remote
public interface IEmployeeService {
    public List<Employee> selectEmployee();
}
