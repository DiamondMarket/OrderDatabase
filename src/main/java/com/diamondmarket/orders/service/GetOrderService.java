package com.diamondmarket.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondmarket.orders.model.Orders;
import com.diamondmarket.orders.model.TransactionContext;
import com.diamondmarket.orders.model.exceptions.GetOrdersException;
import com.diamondmarket.orders.repository.OrderRepository;

@Service
public class GetOrderService {
	
	private OrderRepository orderRepository;

	@Autowired
	public GetOrderService(OrderRepository orderRepository) {		
		this.orderRepository = orderRepository;
		// TODO Auto-generated constructor stub
	}



	public Orders getOrders(TransactionContext context, String orderId) throws Exception {
		try {
			 Orders findOne = orderRepository.findOne(orderId);
			 if (findOne == null) {
				 throw new GetOrdersException("unable to find user");
			 }
			 return findOne;
		} catch (Exception e) {
			throw new GetOrdersException(e.getMessage(), e);
		}
	}

}
