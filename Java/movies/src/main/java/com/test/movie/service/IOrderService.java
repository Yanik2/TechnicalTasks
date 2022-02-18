package com.test.movie.service;

import com.test.movie.dto.OrderDto;
import com.test.movie.entity.Order;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    List<Order> findAllOrders(Map<String, String> map);
    Order findById(Integer id);
    Order createOrder(OrderDto orderDto);
    Order updateOrder(OrderDto orderDto);
    Order deleteOrder(OrderDto orderDto);
}
