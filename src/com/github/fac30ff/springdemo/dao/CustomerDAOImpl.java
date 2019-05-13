package com.github.fac30ff.springdemo.dao;

import java.util.Collections;
import java.util.List;

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
		return Collections.unmodifiableList(sf.getCurrentSession().createQuery("from Customer order by lastName", Customer.class).getResultList());
	}

	@Override
	public void saveCustomer(Customer customer) {
		sf.getCurrentSession().saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomerById(int id) {
		return sf.getCurrentSession().get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(int id) {
		sf.getCurrentSession().createQuery("delete from Customer where id=:customerId").setParameter("customerId", id).executeUpdate();
	}

}
