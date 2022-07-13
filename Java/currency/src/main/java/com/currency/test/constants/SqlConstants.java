package com.currency.test.constants;

public class SqlConstants {
    public static final String SELECT_BY_DATE = "select * from currency where date = ?";
    public static final String SELECT_BY_CCY = "select * from currency where ccy = ?";
    public static final String SELECT_BY_DATE_RANGE = "select * from currency where date > ? " +
            "and date < ? order by date";
}
