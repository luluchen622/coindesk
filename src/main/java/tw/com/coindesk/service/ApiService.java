package tw.com.coindesk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@Slf4j
public class ApiService {

    /**
     * 共用Get API 請求，將json mapping 轉為物件
     * @param apiUrl
     * @param clazz
     * @return Object , null
     * @param <T>
     */
    public <T> T getApiJsonData(String apiUrl, Class<T> clazz) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // 發送請求
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpStatus.OK.value()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                ObjectMapper objectMapper = new ObjectMapper();
                // 將 JSON 資料轉換為Object
                return objectMapper.readValue(response.toString(), clazz);
            } else {
                log.error("Failed to get API. Response code: {}", responseCode);
            }
            connection.disconnect();

        } catch (IOException e) {
            log.error("ApiService Error = ", e);
        }
        return null;
    }
}
