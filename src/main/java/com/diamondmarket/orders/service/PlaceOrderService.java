package com.diamondmarket.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diamondmarket.orders.model.Orders;
import com.diamondmarket.orders.model.exceptions.PlaceOrderException;
import com.diamondmarket.orders.model.request.InputRequest;
import com.diamondmarket.orders.repository.OrderRepository;

@Service
public class PlaceOrderService {
	private OrderRepository orderRepository;
		
	@Autowired
	public PlaceOrderService(OrderRepository orderRepository) {		
			this.orderRepository = orderRepository;
	}
	
	public Orders placeOrder(InputRequest inputRequest) throws Exception {
		Orders orders = orderRepository.findOne(inputRequest.getData().getOrders().getId());
		if (orders == null) {
			Orders insert = orderRepository.insert(inputRequest.getData().getOrders());
			if (insert == null) {
				throw new PlaceOrderException("No user found to update the orders");
			}
			return insert;
		}
		
		boolean addAll = orders.getOrders().addAll(inputRequest.getData().getOrders().getOrders());
		
		if(addAll) {
			try {
				Orders save = orderRepository.save(orders);
				if (save == null) {
					throw new PlaceOrderException("No user found to update the orders");
				}
				return save;
			} catch (Exception e) {
				e.printStackTrace();
				throw new PlaceOrderException(e.getMessage(), e);
			}
		} else {
			throw new PlaceOrderException("orders are not updated");
		}
		
		
	}
}
