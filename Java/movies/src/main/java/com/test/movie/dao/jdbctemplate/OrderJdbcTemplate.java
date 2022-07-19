package com.test.movie.dao.jdbctemplate;

import static com.test.movie.util.SqlConstants.CREATE_ORDER;
import static com.test.movie.util.SqlConstants.DELETE_ORDER;
import static com.test.movie.util.SqlConstants.SELECT_ALL_ORDERS_WITH_OFFSET;
import static com.test.movie.util.SqlConstants.SELECT_MOVIE_BY_ID;
import static com.test.movie.util.SqlConstants.SELECT_ORDER_BY_ID;
import static com.test.movie.util.SqlConstants.UPDATE_ORDER;

import com.test.movie.dao.mappers.CreateOrder;
import com.test.movie.dao.mappers.DeleteOrder;
import com.test.movie.dao.mappers.UpdateOrder;
import com.test.movie.dto.OrderDto;
import com.test.movie.entity.Movie;
import com.test.movie.entity.Order;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class OrderJdbcTemplate {
    private static final Logger logger = LoggerFactory.getLogger(OrderJdbcTemplate.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private DataSource dataSource;

    public OrderJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                             DataSource dataSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.dataSource = dataSource;
    }

    public Order findById(Integer id) {
        logger.info("[JDBC TEMPLATE] find by id");
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(SELECT_ORDER_BY_ID, params, ((rs, rowNum) -> {
            Order o = new Order();
            o.setId(rs.getInt("id"));
            o.setTime(rs.getString("time"));
            Movie m = new Movie();
            m.setId(rs.getInt("movieId"));
            m.setName(rs.getString("name"));
            m.setDirector(rs.getString("director"));
            o.setMovie(m);
            return o;
        }));
    }

    public List<Order> findAllOrders(Map<String, String> map) {
        logger.info("[JDBC TEMPLATE] find all orders");
        Map<String, Object> params = new HashMap<>();
        Integer limit = Integer.valueOf(map.get("pageSize"));
        Integer offset = Integer.valueOf(map.get("pageNumber"));
        params.put("limit", limit);
        params.put("offset", offset * limit);
        return namedParameterJdbcTemplate.query(SELECT_ALL_ORDERS_WITH_OFFSET, params, (rs -> {
            List<Order> orders = new ArrayList<>();
            while(rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setTime(rs.getString("time"));
                Movie m = new Movie();
                m.setId(rs.getInt("movieId"));
                m.setName(rs.getString("name"));
                m.setDirector(rs.getString("director"));
                o.setMovie(m);
                orders.add(o);
            }
            return orders;
        }));
    }

    public Order createOrder(OrderDto orderDto) {
        logger.info("[JDBC TEMPLATE] - create order");
        Map<String, Object> params = new HashMap<>();
        params.put("movieId", orderDto.getMovieId());
        params.put("time", orderDto.getTime());
        CreateOrder co = new CreateOrder(dataSource, CREATE_ORDER);
        KeyHolder kh = new GeneratedKeyHolder();
        co.updateByNamedParam(params, kh);
        Order o = new Order();
        o.setId(kh.getKey().intValue());
        o.setTime(orderDto.getTime());
        Movie m = getMovie(orderDto.getMovieId());
        o.setMovie(m);
        return o;
    }

    public Order updateOrder(OrderDto orderDto) {
        logger.info("[JDBC TEMPLATE] - update order");
        Map<String, Object> params = new HashMap<>();
        params.put("id", orderDto.getId());
        params.put("movieId", orderDto.getMovieId());
        params.put("time", orderDto.getTime());
        UpdateOrder uo = new UpdateOrder(dataSource, UPDATE_ORDER);
        KeyHolder kh = new GeneratedKeyHolder();
        uo.updateByNamedParam(params, kh);
        Order o = new Order();
        o.setId(kh.getKey().intValue());
        o.setTime(orderDto.getTime());
        Movie m = getMovie(orderDto.getMovieId());
        o.setMovie(m);
        return o;
    }

    public Order deleteOrder(OrderDto orderDto) {
        logger.info("[JDBC TEMPLATE] - delete order");
        Map<String, Object> params = new HashMap<>();
        params.put("id", orderDto.getId());
        DeleteOrder deleteOrder = new DeleteOrder(dataSource, DELETE_ORDER);
        KeyHolder kh = new GeneratedKeyHolder();
        deleteOrder.updateByNamedParam(params, kh);
        Order o = new Order();
        o.setId(kh.getKey().intValue());
        o.setTime(orderDto.getTime());
        Movie m = getMovie(orderDto.getMovieId());
        o.setMovie(m);
        return o;
    }

    private Movie getMovie(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(SELECT_MOVIE_BY_ID, params, (rs, rowNum) -> {
            Movie m = new Movie();
            m.setId(rs.getInt("id"));
            m.setName(rs.getString("name"));
            m.setDirector(rs.getString("director"));
            return m;
        });
    }
}
