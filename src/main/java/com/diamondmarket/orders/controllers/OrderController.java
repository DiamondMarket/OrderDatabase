package com.diamondmarket.orders.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.diamondmarket.orders.model.Orders;
import com.diamondmarket.orders.model.Response;

@RestController
public class OrderController {
	
	@RequestMapping(value = "v1/orders", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Response> getOrders(@RequestHeader HttpHeaders httpHeaders, @RequestParam("orderId") String orderId) {
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("oauth id", "Orders");
		

		 ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(headers ,HttpStatus.OK);
		 return responseEntity;
		 
	}

}
