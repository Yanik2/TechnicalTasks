package com.currency.test.service;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyServiceTest {
//    @Mock
//    private RestTemplate restTemplateMock;
//
//    @Mock
//    private CurrencyRepository currencyRepositoryMock;
//
//    private static CurrencyDto[] currencyDtos;
//    private static Currency currency;
//
//    @InjectMocks
//    private CurrencyService testObject = new CurrencyServiceImpl();
//
//    @BeforeClass
//    public static void initCurrencyDtos() {
//        String[] ccys = {"USD", "RUB", "BTC", "EUR"};
//        currencyDtos = new CurrencyDto[4];
//        for(int i = 0; i < 4; i++) {
//            currencyDtos[i] = new CurrencyDto();
//            currencyDtos[i].setCcy(ccys[i]);
//        }
//        currency = new Currency();
//        currency.setCcy("USD");
//        currency.setBuy(20.0);
//        currency.setSale(10.0);
//        currency.setDate(LocalDate.now());
//    }
//
//    @Before
//    public void init() {
//        CurrencyDto[] arr = {new CurrencyDto()};
//        Mockito.when(currencyRepositoryMock.findByCcy(Mockito.anyString()))
//                .thenReturn(List.of(currency));
//        Mockito.when(currencyRepositoryMock.findByDate(LocalDate.now()))
//                .thenReturn(List.of(currency));
//        Mockito.when(currencyRepositoryMock.findByDateRange(Mockito.anyString(), Mockito.anyString()))
//                .thenReturn(List.of(currency));
//        Mockito.when(restTemplateMock.getForObject(Mockito.anyString(), Mockito.any()))
//                .thenReturn(currencyDtos);
//    }
//
//    @Test
//    public void getCurrenciesTest() {
//        List<CurrencyDto> list = testObject.getCurrencies();
//        assertEquals(4, list.size());
//        assertEquals(LocalDate.now(), list.get(0).getDate());
//        assertEquals(LocalDate.now(), list.get(1).getDate());
//        assertEquals(LocalDate.now(), list.get(2).getDate());
//        assertEquals(LocalDate.now(), list.get(3).getDate());
//    }
//
//    @Test
//    public void getAvailableCodesTest() {
//        List<String> list = testObject.getAvailableCodes();
//        assertTrue(list.containsAll(List.of("USD", "RUB", "EUR", "BTC")));
//    }
//
//    @Test
//    public void getCurrenciesByCcyTest() {
//        List<CurrencyDto> list = testObject.getCurrenciesByCcy("USD");
//        assertEquals(1, list.size());
//        assertEquals(LocalDate.now(), list.get(0).getDate());
//        assertEquals("USD", list.get(0).getCcy());
//        assertEquals(10.0, list.get(0).getSale());
//        assertEquals(20.0, list.get(0).getBuy());
//    }
//
//    @Test
//    public void getCurrenciesByDateTest() {
//        List<CurrencyDto> list = testObject.getCurrenciesByDate(LocalDate.now());
//        assertEquals(1, list.size());
//        assertEquals(LocalDate.now(), list.get(0).getDate());
//        assertEquals("USD", list.get(0).getCcy());
//        assertEquals(10.0, list.get(0).getSale());
//        assertEquals(20.0, list.get(0).getBuy());
//    }
//
//    @Test
//    public void getCurrenciesDateRange() {
//        Map<String, List<CurrencyDto>> map =
//                testObject.getCurrenciesDateRange(LocalDate.now().toString(), LocalDate.now().toString());
//        List<CurrencyDto> list = map.get(LocalDate.now().toString());
//        assertEquals(1, list.size());
//        assertEquals(LocalDate.now(), list.get(0).getDate());
//        assertEquals("USD", list.get(0).getCcy());
//        assertEquals(10.0, list.get(0).getSale());
//        assertEquals(20.0, list.get(0).getBuy());
//
//    }
}
