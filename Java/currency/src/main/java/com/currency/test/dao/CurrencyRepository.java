package com.currency.test.dao;

import com.currency.test.entity.Currency;
import java.time.LocalDate;
import java.util.List;

public interface CurrencyRepository {
    List<Currency> findByCcy(String ccy);
    List<Currency> findByDate(LocalDate date);
    List<Currency> findByDateRange(String startDate, String endDate);
}
