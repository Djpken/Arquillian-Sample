package org.arquillian.example.facade.impl;


import org.arquillian.example.entity.impl.Employee;
import org.arquillian.example.facade.IEmployeeFacade;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

@Stateless
public class EmployeeFacade extends BaseFacade<Employee> implements IEmployeeFacade {

    public EmployeeFacade() {
        super(Employee.class);
    }

    @Override
    public void delete(Employee entity) throws EJBException {

    }
}