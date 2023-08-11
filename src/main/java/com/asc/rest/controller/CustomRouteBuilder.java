package com.asc.rest.controller;

import static org.apache.camel.Exchange.HTTP_RESPONSE_CODE;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.DefaultMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.asc.rest.dto.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.auto);
        

        rest("/api")
                .consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE)
                .get("/order").outType(Employee.class).to("direct:get-employee-data")
                .post("/order").type(Employee.class).outType(Employee.class).to("direct:save-employee-data");
        
        
        
        from("direct:get-employee-data").process(this::getAllEmployee).end();
        
        from("direct:save-employee-data").process(this::createEmployees)
        .log("Data Saved")
        .end();
        
        
    }  
    
    private void getAllEmployee(Exchange exchange) {
    	Employee e1 = Employee.builder().id(1001).name("ABC").age(40).designation("Manager").build();
    	Employee e2 = Employee.builder().id(1001).name("ABC").age(40).designation("Manager").build();
    	
    	List<Employee> list = new ArrayList<>();
    	list.add(e1);
    	list.add(e2);
    	
    	Message message = new DefaultMessage(exchange.getContext());
    	message.setBody(list);
    	exchange.setMessage(message);
    }
    
    private void createEmployees(Exchange exchange) {
    	Employee employee = exchange.getMessage().getBody(Employee.class);
    	log.info("Employee Object \n");
    	log.info(" ::::  {}", employee);
    	Message message = new DefaultMessage(exchange.getContext());
    	message.setBody(employee);
    	exchange.setMessage(message);
    }
}
