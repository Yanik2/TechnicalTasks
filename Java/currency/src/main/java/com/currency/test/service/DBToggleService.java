package com.currency.test.service;

public interface DBToggleService {
    void switchDataAccess(Boolean isJdbcEnabled);
}
