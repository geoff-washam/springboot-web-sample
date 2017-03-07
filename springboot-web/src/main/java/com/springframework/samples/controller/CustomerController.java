package com.springframework.samples.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springframework.samples.models.Customer;
import com.springframework.samples.repositories.CustomerRepository;

@Controller
class CustomerController {

    private final CustomerRepository customers;

    @Autowired
    public CustomerController(CustomerRepository customerService) {
        this.customers = customerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("customers", customers.findAll());
        return "customer/customers";
    }

    @RequestMapping(value = "/customer/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Customer customer = new Customer();
        model.put("customer", customer);
        return "customer/createOrEdit";
    }

    @RequestMapping(value = "/customer/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customer/createOrEdit";
        } else {
            this.customers.save(customer);
            return "redirect:/customer/details/" + customer.getId();
        }
    }

    @RequestMapping(value = "/customer/edit/{customerId}", method = RequestMethod.GET)
    public String initUpdateCustomerForm(@PathVariable("customerId") int customerId, Model model) {
        Customer customer = this.customers.findOne(customerId);
        model.addAttribute(customer);
        return "customer/createOrEdit";
    }

    @RequestMapping(value = "/customer/edit/{customerId}", method = RequestMethod.POST)
    public String processUpdateCustomerForm(@Valid Customer customer, BindingResult result, @PathVariable("customerId") int customerId) {
        if (result.hasErrors()) {
            return "customer/createOrEdit";
        } else {
        	customer.setId(customerId);
            this.customers.save(customer);
            return "redirect:/customer/details/{customerId}";
        }
    }
    
    @RequestMapping("/customer/details/{customerId}")
    public String customerDetails(@PathVariable("customerId") int customerId, Model model) {
        Customer customer = this.customers.findOne(customerId);
        model.addAttribute(customer);
        return "customer/details";
    }

}
