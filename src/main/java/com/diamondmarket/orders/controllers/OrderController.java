package com.diamondmarket.orders.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.http.HttpEntity;
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

import com.diamondmarket.orders.model.Data;
import com.diamondmarket.orders.model.Orders;
import com.diamondmarket.orders.model.Response;
import com.diamondmarket.orders.model.TransactionContext;
import com.diamondmarket.orders.model.Error;
@RestController
public class OrderController {
	
	@RequestMapping(value = "v1/orders", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Response> getOrders(@RequestHeader HttpHeaders httpHeaders, @RequestParam("orderId") String orderId) {
		
		TransactionContext context = new TransactionContext();
		if(httpHeaders.get("correlationId") != null) {
			context.setCorrelationId(httpHeaders.get("correlationId").toString());	
		} else {
			context.setCorrelationId("demo");
		}
		if(httpHeaders.get("ApplicationLabel") != null) {
			context.setApplicationLabel(httpHeaders.get("ApplicationLabel").toString());
		} else {
			context.setApplicationLabel("demo");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("oauth id", "Orders");
		

		 ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(headers ,HttpStatus.OK);
		 return responseEntity;
		 
	}
	
	private HttpEntity<Response> successResponse(TransactionContext context, Object object, HttpStatus httpStatus){
		HttpHeaders headers = new HttpHeaders();
		headers.add("correlationId", context.getCorrelationId());
		headers.add("ApplicationLabel", context.getApplicationLabel());
		Data<Object> data = new Data<>(object);
		Response response = new Response();
		response.setData(data);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, headers , httpStatus);
		return responseEntity;
	}
	
	private HttpEntity<Response> errorResponse(TransactionContext context, Exception exception, HttpStatus httpStatus){
		HttpHeaders headers = new HttpHeaders();
		headers.add("correlationId", context.getCorrelationId());
		headers.add("ApplicationLabel", context.getApplicationLabel());
		
		Error error = new Error();
		error.setCode(httpStatus.toString() + "0001");
		error.setReason(exception.getMessage());
		Response response = new Response();
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, headers, httpStatus);
		return responseEntity;
	}
}
