package com.diamondmarket.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondmarket.orders.model.Orders;
import com.diamondmarket.orders.model.TransactionContext;
import com.diamondmarket.orders.model.exceptions.DeleteOrderException;
import com.diamondmarket.orders.model.exceptions.GetOrdersException;
import com.diamondmarket.orders.repository.OrderRepository;

@Service
public class DeleteOrdersService {
	
	private OrderRepository orderRepository;
	
	@Autowired
	public DeleteOrdersService(OrderRepository orderRepository) {		
			this.orderRepository = orderRepository;
	}
	
	public void deleteOrders(TransactionContext context, String orderId) throws DeleteOrderException {
		 Orders order = orderRepository.findOne(orderId);
		 if (order == null) {
			 throw new DeleteOrderException("no orders are available for this user");
		 }
		try {
			orderRepository.delete(orderId);
		} catch (Exception e) {
			throw new DeleteOrderException(e.getMessage(), e);
		}
		
	}

}
