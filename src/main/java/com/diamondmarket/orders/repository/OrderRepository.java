package com.diamondmarket.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.diamondmarket.orders.model.Orders;

public interface OrderRepository extends MongoRepository<Orders, String> {

}
