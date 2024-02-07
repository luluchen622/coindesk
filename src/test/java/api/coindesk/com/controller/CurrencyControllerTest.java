package api.coindesk.com.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;


/**
 * 幣別對應表資料
 */
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class CurrencyControllerTest {

    @Test
    @Description("測試呼叫查詢全部幣別對應表資料API")
    void getAll() {
    }

    @Test
    @Description("測試呼叫查詢幣別對應表資料API，並顯示其內容。")
    void getOne() {
    }

    @Test
    @Description("測試呼叫新增幣別對應表資料API。")
    void create() {
    }

    @Test
    @Description("測試呼叫更新幣別對應表資料API，並顯示其內容。")
    void replace() {
    }

    @Test
    @Description("測試呼叫刪除幣別對應表資料API。")
    void delete() {
    }
}