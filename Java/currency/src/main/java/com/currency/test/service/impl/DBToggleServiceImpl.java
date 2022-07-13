package com.currency.test.service.impl;

import com.currency.test.dao.CurrencyDao;
import com.currency.test.service.DBToggleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DBToggleServiceImpl implements DBToggleService {
    private static final Logger logger = LoggerFactory.getLogger(DBToggleServiceImpl.class);
    private CurrencyDao currencyDao;

    public DBToggleServiceImpl(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    @Override
    public void switchDataAccess(Boolean isJdbcEnabled) {
        currencyDao.setIsJdbcEnabled(isJdbcEnabled);
        logger.info("switch db toggle isJdbcEnabled to " + isJdbcEnabled);
    }
}
