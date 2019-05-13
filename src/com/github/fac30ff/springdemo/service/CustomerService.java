package com.github.fac30ff.springdemo.service;

import java.util.List;

import com.github.fac30ff.springdemo.entity.Customer;

public interface CustomerService {
	public List<Customer> getCustomers();
	public void saveCustomer(Customer customer);
	public Customer getCustomerById(int id);
	public void deleteCustomer(int id);
}
