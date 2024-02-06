package tw.com.coindesk.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import tw.com.coindesk.domain.CurrentPrice;

@SpringBootTest
@Slf4j
class ApiServiceTest {

    @Autowired
    private ApiService apiService;

    @Test
    @Description("測試呼叫 coindesk API，並顯示其內容")
    void getCoindeskApiJsonData() {
        CurrentPrice currentPrice = apiService.getApiJsonData("https://api.coindesk.com/v1/bpi/currentprice.json", CurrentPrice.class);
        log.info("currentPrice= {}", currentPrice.toString());
    }
}