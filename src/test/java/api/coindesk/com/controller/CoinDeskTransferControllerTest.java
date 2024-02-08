package api.coindesk.com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    @Description("測試呼叫資料轉換的API，並顯示其內容")
    void getCoinDeskTransferData() throws Exception {
        String result = mockMvc.perform(get("/v1/coindesk-transfer").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    }
}