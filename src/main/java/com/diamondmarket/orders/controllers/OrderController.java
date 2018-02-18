package com.diamondmarket.orders.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.diamondmarket.orders.model.Response;
import com.diamondmarket.orders.model.TransactionContext;
import com.diamondmarket.orders.model.request.InputRequest;
import com.diamondmarket.orders.service.DeleteOrdersService;
import com.diamondmarket.orders.service.GetOrderService;
import com.diamondmarket.orders.service.PlaceOrderService;
import com.diamondmarket.orders.model.Error;

@RestController
public class OrderController {
	
	private GetOrderService getOrderService;
	private PlaceOrderService placeOrderService;
	private DeleteOrdersService deleteOrdersService;
	
	@Autowired
	public OrderController(GetOrderService getOrderService, PlaceOrderService placeOrderService, DeleteOrdersService deleteOrdersService) {
		this.deleteOrdersService = deleteOrdersService;
		this.getOrderService = getOrderService;
		this.placeOrderService = placeOrderService;
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "v1/orders", produces = "application/json" , method = RequestMethod.GET)
	public ResponseEntity<Response> getOrders(@RequestHeader HttpHeaders httpHeaders, @RequestParam("orderId") String orderId) {
		
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
		
		try {
			return successResponse(context, getOrderService.getOrders(context, orderId), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return errorResponse(context, e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "v1/orders/deleteOrders", produces = "application/json" , method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteOrders(@RequestHeader HttpHeaders httpHeaders, @RequestParam("orderId") String orderId) {
		
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
		
		try {
			deleteOrdersService.deleteOrders(context, orderId);
			return successResponse(context, "user orders are successfully deleted with userId " + orderId, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return errorResponse(context, e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "v1/orders/placeOrder", produces = "application/json" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Response> placeOrder(@RequestHeader HttpHeaders httpHeaders, @RequestBody InputRequest inputRequest) {
		
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
		
		try {
			return successResponse(context, placeOrderService.placeOrder(inputRequest), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return errorResponse(context, e, HttpStatus.BAD_REQUEST);
		}
	}
	
	private ResponseEntity<Response> successResponse(TransactionContext context, Object object, HttpStatus httpStatus){
		HttpHeaders headers = new HttpHeaders();
		headers.add("correlationId", context.getCorrelationId());
		headers.add("ApplicationLabel", context.getApplicationLabel());
		headers.add("Content-Type", "application/json");
		Response response = new Response();
		response.setData(object);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, headers , httpStatus);
		return responseEntity;
	}
	
	private ResponseEntity<Response> errorResponse(TransactionContext context, Exception exception, HttpStatus httpStatus){
		HttpHeaders headers = new HttpHeaders();
		headers.add("correlationId", context.getCorrelationId());
		headers.add("ApplicationLabel", context.getApplicationLabel());
		headers.add("Content-Type", "application/json");
		Error error = new Error();
		error.setCode(httpStatus.toString() + "0001");
		error.setReason(exception.getMessage());
		Response response = new Response();
		response.setError(error);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, headers, httpStatus);
		return responseEntity;
	}
}
