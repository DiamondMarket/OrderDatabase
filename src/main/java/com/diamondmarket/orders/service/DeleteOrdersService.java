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
			// TODO Auto-generated constructor stub
	}
	
	public void deleteOrders(TransactionContext context, String orderId) throws DeleteOrderException {
		 Orders findOne = orderRepository.findOne(orderId);
		 if (findOne == null) {
			 throw new DeleteOrderException("no orders are available for this user");
		 }
		try {
			orderRepository.delete(orderId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DeleteOrderException(e.getMessage(), e);
		}
		
	}

}
