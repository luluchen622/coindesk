package api.coindesk.com.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.*;

/**
 * coindesk資料轉換的API
 */
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class CoinDeskTransferControllerTest {

    @Test
    @Description("測試呼叫資料轉換的API，並顯示其內容")
    void getCoinDeskTransferData() {
    }
}