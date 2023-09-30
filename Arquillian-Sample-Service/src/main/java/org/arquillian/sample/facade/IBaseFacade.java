package org.arquillian.sample.facade;

import javax.ejb.EJBException;
import java.util.List;

/**
 * @author Kinson
 *
 * @param <T>
 */
public interface IBaseFacade<T> {
		
	/**
	 * Perform an initial save of a previously unsaved <T> entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 * 			  Trade entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 * 
	 */
	public void save(T entity) throws EJBException;
	
	/**
	 * Persist a previously saved Trade entity and return it or a copy of it to
	 * the sender. A copy of the Trade entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity.
	 * 
	 * @param entity
	 * 			  Trade entity to update
	 * @return Trade the persisted Trade entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public T update(T entity) throws EJBException;
		
	/**
	 * Delete a persistent <T> entity.
	 * 
	 * @param entity
	 * 			  <T> entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(T entity) throws EJBException;
		
	/**
	 * @param entity
	 * @return
	 */
	public boolean contains(T entity) throws RuntimeException;
		
	/**
	 * @param id
	 * @return
	 */
	public T findById(Object id) throws RuntimeException;

	
	/**
	 * @param sql
	 * @param maxResult
	 * @return
	 * @throws RuntimeException
	 */
	public List<T> findBySQL(String sql, int maxResult) throws RuntimeException;
	
	/**
	 * Find all <T> entities.
	 * 
	 * @param rowStartIdxAndCount
	 * 			Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *          row index in the query result-set to begin collecting the
	 *          results. rowStartIdxAndCount[1] specifies the the maximum
	 *          count of results to return.
	 * @return List<T> all Trade entities
	 */
	public List<T> findAll(final int... rowStartIdxAndCount) throws RuntimeException;
	
	/**
	 * Retrieve ID from a sequence
	 * 
	 * @param sequencename
	 * @return
	 */
	public String getIdFromSeq(String sequencename);

}
