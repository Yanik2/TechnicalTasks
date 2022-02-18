package com.currency.test.service.impl;

import com.currency.test.dto.CurrencyDto;
import com.currency.test.repository.CurrencyRepository;
import com.currency.test.entity.Currency;
import com.currency.test.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private static final String API_URI = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<CurrencyDto> getCurrencies() {
        return Arrays.stream(restTemplate.getForObject(API_URI, CurrencyDto[].class))
                .map(r -> {
                    r.setDate(LocalDate.now());
                    return r;
                }).collect(Collectors.toList());
    }

    public List<String> getAvailableCodes() {
        return Arrays.stream(restTemplate.getForObject(API_URI, CurrencyDto[].class))
                .collect(ArrayList::new, (list, item) -> list.add(item.getCcy()), ArrayList::addAll);
    }

    public List<CurrencyDto> getCurrenciesByCcy(String ccy) {
        return toDtoList(currencyRepository.findByCcy(ccy));
    }

    public List<CurrencyDto> getCurrenciesByDate(LocalDate date) {
        return toDtoList(currencyRepository.findByDate(date));
    }

    public Map<String, List<CurrencyDto>> getCurrenciesDateRange(String startDate, String endDate) {
        return currencyRepository.findByDateRange(startDate, endDate)
                .stream().map(this::toDto)
                .collect(Collectors.groupingBy(c -> c.getDate().toString(),
                        TreeMap::new, Collectors.toList()));
    }

    private List<CurrencyDto> toDtoList(List<Currency> currs) {
        return currs.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private CurrencyDto toDto(Currency curr) {
        CurrencyDto dto = new CurrencyDto();
        dto.setDate(curr.getDate());
        dto.setBuy(curr.getBuy());
        dto.setSale(curr.getSale());
        dto.setCcy(curr.getCcy());
        return dto;
    }
}
