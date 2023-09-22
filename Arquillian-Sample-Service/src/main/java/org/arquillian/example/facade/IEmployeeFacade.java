package org.arquillian.example.facade;


import org.arquillian.example.entity.impl.Employee;

import javax.ejb.Local;

/**
 * Local interface for TisJoHeaderFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Local
public interface IEmployeeFacade extends IBaseFacade<Employee>{
	
}