package com.test.movie.service.impl;

import com.test.movie.dao.OrderDao;
import com.test.movie.dto.OrderDto;
import com.test.movie.entity.Order;
import com.test.movie.service.IOrderService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {
    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> findAllOrders(Map<String, String> map) {
        return orderDao.findAll(map);
    }

    public Order findById(Integer id) {
        return orderDao.findById(id);
    }

    public Order createOrder(OrderDto orderDto) {
        return orderDao.createOrder(orderDto);
    }

    public Order updateOrder(OrderDto orderDto) {
        return orderDao.updateOrder(orderDto);
    }

    public Order deleteOrder(OrderDto orderDto) {
        return orderDao.deleteOrder(orderDto);
    }

    public void switchJdbc(boolean isJdbcEnabled) {
        orderDao.setJdbcEnabled(isJdbcEnabled);
    }
}
