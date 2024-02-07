package api.coindesk.com.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import api.coindesk.com.vo.coindesk.CurrentPrice;

@SpringBootTest
@Slf4j
class ApiServiceTest {

    @Autowired
    private ApiService apiService;

    @Test
    @Description("測試呼叫 coindesk API，並顯示其內容")
    void getCoindeskApiJsonData() {
        CurrentPrice currentPrice = apiService.getApiJsonData("https://api.coindesk.com/v1/bpi/currentprice.json", CurrentPrice.class);
        Assertions.assertNotNull(currentPrice);
        log.info("currentPrice= {}", currentPrice.toString());
    }
}