package api.coindesk.com.controller;

import api.coindesk.com.service.CurrencyService;
import api.coindesk.com.vo.coindesktransfer.CoinDeskTransfer;
import api.coindesk.com.vo.currency.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * coindesk資料轉換的API
 */
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class CoinDeskTransferControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Mock
    private CurrencyService currencyService;

    @BeforeEach
    void mockCurrencyData() {
        Currency usdCurrency = new Currency("USD", "美元");
        Currency gbpCurrency = new Currency("GBP", "英鎊");
        Currency eurCurrency = new Currency("EUR", "歐元");

        when(currencyService.getCurrencyByCode("USD")).thenReturn(new ResponseEntity<>(usdCurrency, HttpStatus.OK));
        when(currencyService.getCurrencyByCode("GBP")).thenReturn(new ResponseEntity<>(gbpCurrency, HttpStatus.OK));
        when(currencyService.getCurrencyByCode("EUR")).thenReturn(new ResponseEntity<>(eurCurrency, HttpStatus.OK));
    }

    @Test
    @Description("測試呼叫資料轉換的API，並顯示其內容")
    void getCoinDeskTransferData() throws Exception {
        String result = mockMvc.perform(get("/v1/coindesk-transfer").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("result= {}", result);

        CoinDeskTransfer coinDeskTransfer = mapper.readValue(result, CoinDeskTransfer.class);
        assertThat(coinDeskTransfer)
                .isNotNull()
                .extracting(CoinDeskTransfer::getUpdateTime)
                .isNotNull();

        assertThat(coinDeskTransfer.getCurrencyList())
                .isNotEmpty()
                .extracting(api.coindesk.com.vo.coindesktransfer.Currency::getCurrency, api.coindesk.com.vo.coindesktransfer.Currency::getCurrencyName)
                .contains(
                        tuple("USD", "美元"),
                        tuple("GBP", "英鎊"),
                        tuple("EUR", "歐元")
                );
    }
}