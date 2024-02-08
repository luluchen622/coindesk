package api.coindesk.com.controller;

import api.coindesk.com.vo.currency.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * 幣別對應表資料
 */
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Transactional
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    @Description("測試呼叫新增幣別對應表資料API。")
    void create() throws Exception {
        // 新增的資料
        Currency currency = Currency.builder()
                .code("JPY")
                .currencyName("日圓")
                .build();

        String result = mockMvc.perform(post("/v1/currency").content(mapper.writeValueAsString(currency)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        // 新增成功回傳true
        assertThat(result)
                .isNotNull()
                .isEqualTo("true");
    }


    @Test
    @Description("測試呼叫查詢全部幣別對應表資料API")
    void getAll() throws Exception {
        mockMvc.perform(get("/v1/currency/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].code").value("USD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].currencyName").value("美元"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].code").value("GBP"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].currencyName").value("英鎊"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].code").value("EUR"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].currencyName").value("歐元"));
    }

    @Test
    @Description("測試呼叫查詢幣別對應表資料API，並顯示其內容。")
    void getCurrencyByCode() throws Exception {
        String testCurrency = "USD";
        String result = mockMvc.perform(get("/v1/currency/{code}", testCurrency).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        Currency currency = mapper.readValue(result, Currency.class);
        assertThat(currency)
                .isNotNull()
                .extracting(Currency::getCode, Currency::getCurrencyName)
                .contains(testCurrency, "美元");
    }

    @Test
    @Description("測試呼叫更新幣別對應表資料API，並顯示其內容。")
    void replace() throws Exception {
        String usd = "USD";
        // 新增的資料
        Currency replace = Currency.builder()
                .code(usd)
                .currencyName("修改美元名稱")
                .build();

        String result = mockMvc.perform(put("/v1/currency/{code}", usd).content(mapper.writeValueAsString(replace)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        // 轉換object
        Currency currencyResult = mapper.readValue(result, Currency.class);
        assertThat(currencyResult)
                .isNotNull()
                .extracting(Currency::getCode, Currency::getCurrencyName)
                .contains(usd, "修改美元名稱");
    }

    @Test
    @Description("測試呼叫刪除幣別對應表資料API。")
    void deleteCurrency() throws Exception {
        String result = mockMvc.perform(delete("/v1/currency/{code}", "USD").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        // 刪除成功回傳true
        assertThat(result)
                .isNotNull()
                .isEqualTo("true");
    }

    @Test
    @Description("反向-測試呼叫刪除幣別對應表資料API。")
    void deleteCurrencyErr() throws Exception {
        String result = mockMvc.perform(delete("/v1/currency/{code}", "AAA").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        // 刪除成功回傳true
        assertThat(result)
                .isNotNull()
                .isEqualTo("false");
    }
}