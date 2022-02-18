package com.currency.test;

import com.currency.test.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class CurrencyTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void currencyTest() throws Exception {
        this.mockMvc.perform(get("/currency/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("USD")))
                .andExpect(content().string(containsString("EUR")))
                .andExpect(content().string(containsString("RUR")))
                .andExpect(content().string(containsString("BTC")));
    }
    @Test
    public void codesTest() throws Exception {
        this.mockMvc.perform(get("/currency/codes"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("USD")))
                .andExpect(content().string(containsString("EUR")))
                .andExpect(content().string(containsString("RUR")))
                .andExpect(content().string(containsString("BTC")));
    }

    @Test
    public void historicalByCcyTest() throws Exception {
        this.mockMvc.perform(get("/currency/historical/byCcy?ccy=USD"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("USD")));
    }

    @Test
    public void historicalByDateTest() throws Exception {
        this.mockMvc.perform(get("/currency/historical/byDate?date=2021-01-01"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("2021-01-01")));
    }

    @Test
    public void historicalByDateRangeTest() throws Exception {
        this.mockMvc.perform(get("/currency/historical/dateRange?startDate=2021-01-02&endDate=2021-01-04"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("2021-01-03")));
    }
}
