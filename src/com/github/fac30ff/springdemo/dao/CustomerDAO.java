package com.github.fac30ff.springdemo.dao;

import java.util.List;

import com.github.fac30ff.springdemo.entity.Customer;

public interface CustomerDAO {
	public List<Customer> getCustomers();
	public void saveCustomer(Customer customer);
	public Customer getCustomerById(int id);
	public void deleteCustomer(int id);
}
