package com.test.movie.dao;

import com.test.movie.dao.jdbctemplate.OrderJdbcTemplate;
import com.test.movie.dto.OrderDto;
import com.test.movie.entity.Movie;
import com.test.movie.entity.Order;
import com.test.movie.repository.MovieRepository;
import com.test.movie.repository.OrderRepository;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class OrderDao extends AbstractMovieDao {
    private OrderRepository orderRepository;
    private MovieRepository movieRepository;
    private OrderJdbcTemplate orderJdbcTemplate;

    public OrderDao(OrderRepository orderRepository,
                    OrderJdbcTemplate orderJdbcTemplate,
                    MovieRepository movieRepository) {
        this.orderRepository = orderRepository;
        this.orderJdbcTemplate = orderJdbcTemplate;
        this.movieRepository = movieRepository;
    }

    public Order findById(Integer id) {
        return isJdbcEnabled ? orderJdbcTemplate.findById(id) :
                orderRepository.findById(id).orElse(null);
    }

    public List<Order> findAll(Map<String, String> map) {
        return isJdbcEnabled ? orderJdbcTemplate.findAllOrders(map) : findAllOrders(map);
    }

    public Order createOrder(OrderDto orderDto) {
        return isJdbcEnabled ? orderJdbcTemplate.createOrder(orderDto) : saveNewOrder(orderDto);
    }

    private Order saveNewOrder(OrderDto orderDto) {
        Movie movie = movieRepository.findById(orderDto.getMovieId()).get();
        Order order = new Order(movie, orderDto.getTime());
        return orderRepository.save(order);
    }

    public Order updateOrder(OrderDto orderDto) {
        return isJdbcEnabled ? orderJdbcTemplate.updateOrder(orderDto) :
                updateExistingOrder(orderDto);
    }

    private Order updateExistingOrder(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId()).orElse(new Order());
        Movie movie = movieRepository.findById(orderDto.getMovieId()).orElse(null);
        order.setTime(orderDto.getTime());
        order.setMovie(movie);
        return orderRepository.saveAndFlush(order);
    }

    public Order deleteOrder(OrderDto orderDto) {
        return isJdbcEnabled ? orderJdbcTemplate.deleteOrder(orderDto) :
                deleteExistingOrder(orderDto);
    }

    private Order deleteExistingOrder(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId()).get();
        orderRepository.delete(order);
        return order;
    }

    private List<Order> findAllOrders(Map<String, String> map) {
        Integer pageNumber = Integer.parseInt(map.get("pageNumber"));
        Integer pageSize = Integer.parseInt(map.get("pageSize"));
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Order> orders = orderRepository.findAll(page);
        return orders.getContent();
    }
}
