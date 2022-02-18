package com.currency.test.controller;

import com.currency.test.dto.CurrencyDto;
import com.currency.test.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/currency")
public class MainController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/")
    public List<CurrencyDto> currency() {
        return currencyService.getCurrencies();
    }

    @GetMapping("/codes")
    public List<String> currencyCodes() {
        return currencyService.getAvailableCodes();
    }

    @GetMapping("/historical/byCcy")
    public List<CurrencyDto> historicalCurrenciesByCcy(@RequestParam String ccy) {
        return currencyService.getCurrenciesByCcy(ccy);
    }

    @GetMapping("/historical/byDate")
    public List<CurrencyDto> historicalCurrenciesByDate(@RequestParam String date) {
        return currencyService.getCurrenciesByDate(LocalDate.parse(date));
    }

    @GetMapping("/historical/dateRange")
    public Map<String, List<CurrencyDto>> historicalCurrenciesDateRange(@RequestParam String startDate,
                                                                  @RequestParam String endDate) {
        return currencyService.getCurrenciesDateRange(startDate, endDate);
    }
}
