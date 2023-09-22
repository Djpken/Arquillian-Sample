package org.arquillian.example.facade.impl;

import org.arquillian.example.facade.IBaseFacade;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;


/**
 * @author Kinson
 *
 * @param <T>
 */
public abstract class BaseFacade<T> implements IBaseFacade<T> {

	private Class<T> entityBeanType;
	
	@PersistenceContext(unitName="mmcs2EjbPU")
	protected EntityManager entityManager;
	
	@Resource 
	protected SessionContext ctx;
	

	public BaseFacade(Class<T> entityBeanType) {
		this.entityBeanType = entityBeanType;
	}
		
	/* (non-Javadoc)
	 * @see hk.gov.csd.mmcs2.ejb.session.IBaseFacade#save(java.lang.Object)
	 */
	public void save(T entity) throws EJBException {
		try {
			entityManager.persist(entity);
			entityManager.flush();
		} catch (EJBException e) {
			ctx.setRollbackOnly();
			throw e;
		}
	}
	
	/* (non-Javadoc)
	 * @see hk.gov.csd.mmcs2.ejb.session.IBaseFacade#update(java.lang.Object)
	 */
	public T update(T entity) throws EJBException {
		try {
			T result = entityManager.merge(entity);
			entityManager.flush();
			return result;
		} catch (EJBException e) {		
			ctx.setRollbackOnly();
			throw e;
		}
	}
		
	/* (non-Javadoc)
	 * @see hk.gov.csd.mmcs2.ejb.session.IBaseFacade#delete(java.lang.Object)
	 */
	public abstract void delete(T entity) throws EJBException;
		
	/* (non-Javadoc)
	 * @see hk.gov.csd.mmcs2.ejb.session.IBaseFacade#contains(java.lang.Object)
	 */
	public boolean contains(T entity) throws RuntimeException {
		try {
			return entityManager.contains(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see hk.gov.csd.mmcs2.ejb.session.IBaseFacade#findById(java.lang.Object)
	 */
	public T findById(Object id) throws RuntimeException {
		try {
			T instance = entityManager.find(getEntityBeanType(), id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	/**
	 * Find all <T> entities with a specific property value.
	 * 
	 * @param table
	 * 			  the name of the Table
	 * @param propertyName
	 * 			  the name of the Trade property to query
	 * @param value
	 * 			  the property value to match
	 * @param rowStartIdxAndCount
	 * 			  Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T> found by query
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByProperty(String propertyName, Object value, final int... rowStartIdxAndCount) throws RuntimeException {		
		StringBuilder querysb = new StringBuilder();
		querysb.append("select model from ");
		querysb.append(entityBeanType.getSimpleName());
		querysb.append(" model where model.");
		querysb.append(propertyName);
		querysb.append("= :propertyValue");
		Query query = null;
		try {
			query = entityManager.createQuery(querysb.toString());
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findBySQL(String sql, int maxResult) {
		Query query = null;
		try {
			query = entityManager.createQuery(sql);
			query.setMaxResults(maxResult);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
		
	/* (non-Javadoc)
	 * @see hk.gov.csd.mmcs2.ejb.session.IBaseFacade#findAll(int[])
	 */
	@SuppressWarnings("unchecked")	
	public List<T> findAll(final int... rowStartIdxAndCount) throws RuntimeException {		
		StringBuilder querysb = new StringBuilder();
		querysb.append("select model from ");
		querysb.append(entityBeanType.getSimpleName());
		querysb.append(" model");

		Query query = null;		
		try {
			query = entityManager.createQuery(querysb.toString());
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	protected Class<T> getEntityBeanType() {
		return entityBeanType;
	}
	
	public String getIdFromSeq(String sequencename) {
		StringBuilder sb = new StringBuilder();
		// Convert SQL syntax from Oracle to MariaDB
		//sb.append("SELECT ");
		//sb.append(sequencename);
		//sb.append(".nextval FROM DUAL");
		sb.append("SELECT NEXTVAL(");
		sb.append(sequencename);
		sb.append(") FROM DUAL");
		
		try {
			Query query = entityManager.createNativeQuery(sb.toString());
			return ((BigInteger) query.getSingleResult()).toString();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
