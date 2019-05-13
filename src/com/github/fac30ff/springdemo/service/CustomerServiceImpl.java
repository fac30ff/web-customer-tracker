package com.github.fac30ff.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.fac30ff.springdemo.dao.CustomerDAO;
import com.github.fac30ff.springdemo.entity.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDAO cd;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Customer> getCustomers() {
		return cd.getCustomers();
	}

	@Override
	public void saveCustomer(Customer customer) {
		cd.saveCustomer(customer);
	}

	@Override
	public Customer getCustomerById(int id) {
		return cd.getCustomerById(id);
	}

	@Override
	public void deleteCustomer(int id) {
		cd.deleteCustomer(id);
	}

	@Override
	public List<Customer> searchCustomersByName(String searchName) {
		return cd.searchCustomersByName(searchName);
	}

}
