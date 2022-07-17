package com.test.movie.service.impl;

import com.test.movie.dto.OrderDto;
import com.test.movie.entity.Movie;
import com.test.movie.entity.Order;
import com.test.movie.repository.MovieRepository;
import com.test.movie.repository.OrderRepository;
import com.test.movie.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Autowired
    private MovieRepository movieRepository;

    public static Specification<Order> getOrders(Movie movie, String time) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> preds = new ArrayList<>();
            if(movie != null)
                preds.add(criteriaBuilder.equal(root.get("movie"), movie));
            if(time != null)
                preds.add(criteriaBuilder.equal(root.get("time"), time));

            return criteriaBuilder.and(preds.toArray(new Predicate[0]));
        };
    }

    public List<Order> findAllOrders(Map<String, String> map) {
        Movie movie = movieRepository.findById(Integer.parseInt(map.get("movieId"))).orElse(null);
        Integer pageNumber = Integer.parseInt(map.get("pageNumber"));
        Integer pageSize = Integer.parseInt(map.get("pageSize"));
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Order> orders = orderRepository.findAll(getOrders(movie,
                map.get("time")), page);
        return orders.getContent();
    }

    public Order findById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(OrderDto orderDto) {
        Movie movie = movieRepository.findById(orderDto.getMovieId()).get();
        Order order = new Order(movie, orderDto.getTime());
        return orderRepository.save(order);
    }

    public Order updateOrder(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId()).orElse(new Order());
        Movie movie = movieRepository.findById(orderDto.getMovieId()).orElse(null);
        order.setTime(orderDto.getTime());
        order.setMovie(movie);
        return orderRepository.saveAndFlush(order);
    }

    public Order deleteOrder(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId()).get();
        orderRepository.delete(order);
        return order;
    }
}
