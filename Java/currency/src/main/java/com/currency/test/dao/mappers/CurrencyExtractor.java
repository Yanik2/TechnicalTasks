package com.currency.test.dao.mappers;

import com.currency.test.dao.CurrencyDao;
import com.currency.test.entity.Currency;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class CurrencyExtractor implements ResultSetExtractor<List<Currency>> {
    @Override
    public List<Currency> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Currency> currencies = new ArrayList<>();

        while(rs.next()) {
            Currency c = new Currency();
            c.setId(rs.getLong("id"));
            c.setCcy(rs.getString("ccy"));
            c.setSale(rs.getDouble("sale"));
            c.setDate(rs.getDate("date").toLocalDate());
            c.setBuy(rs.getDouble("buy"));
            currencies.add(c);
        }
        return currencies;
    }
}
