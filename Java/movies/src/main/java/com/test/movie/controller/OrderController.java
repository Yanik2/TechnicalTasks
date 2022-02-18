package com.test.movie.controller;

import com.test.movie.dto.OrderDto;
import com.test.movie.entity.Order;
import com.test.movie.service.IOrderService;
import com.test.movie.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public List<Order> findAllOrders(@RequestParam Map<String, String> params) {
        return orderService.findAllOrders(params);
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable String id) {
        return orderService.findById(Integer.parseInt(id));
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderDto order) {
        return orderService.createOrder(order);
    }

    @PutMapping
    public Order updateOrder(@RequestBody OrderDto order) {
        return orderService.updateOrder(order);
    }

    @DeleteMapping
    public Order deleteOrder(@RequestBody OrderDto order) {
        return orderService.deleteOrder(order);
    }
}
