package org.arquillian.sample.facade;


import org.arquillian.sample.entity.impl.Employee;

import javax.ejb.Local;

/**
 * Local interface for TisJoHeaderFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Local
public interface IEmployeeFacade extends IBaseFacade<Employee>{
	
}