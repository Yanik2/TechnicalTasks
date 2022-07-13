package com.currency.test.dao;

import com.currency.test.dao.impl.CurrencyJdbcTemplateDao;
import com.currency.test.entity.Currency;
import com.currency.test.dao.impl.CurrencyJpaDao;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CurrencyDao {
    private CurrencyRepository currencyJpaDao;
    private CurrencyRepository currencyJdbcTemplateDao;
    private Boolean isJdbcEnabled = false;

    public CurrencyDao(CurrencyRepository currencyJpaDao,
                       CurrencyRepository currencyJdbcTemplateDao) {
        this.currencyJpaDao = currencyJpaDao;
        this.currencyJdbcTemplateDao = currencyJdbcTemplateDao;
    }

    public List<Currency> findByCcy(String ccy) {
        return isJdbcEnabled ? findByCcyJdbcTemplate(ccy) : currencyJpaDao.findByCcy(ccy);
    }

    public List<Currency> findByDate(LocalDate date) {
        return isJdbcEnabled ? findByDateJdbcTemplate(date) : currencyJpaDao.findByDate(date);
    }

    public List<Currency> findByDateRange(String startDate, String endDate) {
        return isJdbcEnabled ? findByDateRangeJdbcTemplate(startDate, endDate) :
                currencyJpaDao.findByDateRange(startDate, endDate);
    }

    public void setIsJdbcEnabled(Boolean isJdbcEnabled) {
        this.isJdbcEnabled = isJdbcEnabled;
    }

    private List<Currency> findByCcyJdbcTemplate(String ccy) {
        return currencyJdbcTemplateDao.findByCcy(ccy);
    }

    private List<Currency> findByDateJdbcTemplate(LocalDate date) {
        return currencyJdbcTemplateDao.findByDate(date);
    }

    private List<Currency> findByDateRangeJdbcTemplate(String startDate, String endDate) {
        return currencyJdbcTemplateDao.findByDateRange(startDate, endDate);
    }
}
