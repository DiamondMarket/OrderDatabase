package com.diamondmarket.orders.model.request;

import com.diamondmarket.orders.model.Orders;

public class Data {

	private Orders orders;

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Data [orders=" + orders + "]";
	}
	
	
}
