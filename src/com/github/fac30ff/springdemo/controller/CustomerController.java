package com.github.fac30ff.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.fac30ff.springdemo.entity.Customer;
import com.github.fac30ff.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService cs;
	
	@RequestMapping(path="/list", method=RequestMethod.GET)
	public String listCustomers(Model model) {
		List<Customer> customers = cs.getCustomers();
		model.addAttribute("customers", customers);
		return "list-customers";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		cs.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
		Customer customer = cs.getCustomerById(id);
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	
	@DeleteMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		cs.deleteCustomer(id);
		return "redirect:/customer/list";
	}
}
