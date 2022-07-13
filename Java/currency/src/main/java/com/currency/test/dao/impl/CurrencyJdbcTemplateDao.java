package com.currency.test.dao.impl;

import static com.currency.test.constants.SqlConstants.SELECT_BY_CCY;
import static com.currency.test.constants.SqlConstants.SELECT_BY_DATE;
import static com.currency.test.constants.SqlConstants.SELECT_BY_DATE_RANGE;

import com.currency.test.dao.CurrencyDao;
import com.currency.test.dao.CurrencyRepository;
import com.currency.test.dao.mappers.CurrencyExtractor;
import com.currency.test.entity.Currency;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CurrencyJdbcTemplateDao implements CurrencyRepository {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyJdbcTemplateDao.class);
    private JdbcTemplate jdbcTemplate;

    public CurrencyJdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Currency> findByCcy(String ccy) {
        logger.info("Request in DB for List of Currencies by CCY");
        return jdbcTemplate.query(SELECT_BY_CCY, new CurrencyExtractor(), ccy);
    }

    public List<Currency> findByDate(LocalDate date) {
        logger.info("Request in DB for List of Currencies by date");
        return jdbcTemplate.query(SELECT_BY_DATE, new CurrencyExtractor(), date);
    }

    public List<Currency> findByDateRange(String startDate, String endDate) {
        logger.info("Request in DB for List fo Currencies by date range");
        return jdbcTemplate.query(SELECT_BY_DATE_RANGE, new CurrencyExtractor(), startDate, endDate);
    }
}
