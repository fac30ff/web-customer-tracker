package com.github.fac30ff.springdemo.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.fac30ff.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<Customer> getCustomers() {
		return Collections.unmodifiableList(getCurrentSession().createQuery("from Customer order by lastName", Customer.class).getResultList());
	}

	@Override
	public void saveCustomer(Customer customer) {
		getCurrentSession().saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomerById(int id) {
		return getCurrentSession().get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(int id) {
		getCurrentSession().createQuery("delete from Customer where id=:customerId").setParameter("customerId", id).executeUpdate();
	}

	@Override
	public List<Customer> searchCustomersByName(String searchName) {
		return  validateSearchName(searchName) ? 
				getCurrentSession().createQuery("from Customer where lower(firstName) like :name or lower(lastName) like :name", Customer.class).setParameter("name", "%"+searchName.toLowerCase()+"%").getResultList() 
				: 
				getCustomers();
	}
	
	private Session getCurrentSession() {
		return sf.getCurrentSession();
	}
	
	private boolean validateSearchName(String searchName) {
		return searchName != null && searchName.trim().length() > 0;
	}

}
