package api.coindesk.com.service;

import api.coindesk.com.vo.coindesk.CurrentPrice;
import api.coindesk.com.vo.coindesktransfer.CoinDeskTransfer;
import api.coindesk.com.vo.coindesktransfer.Currency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CoinDeskTransferService {

    final ApiService apiService;
    final CurrencyService currencyService;

    public ResponseEntity<CoinDeskTransfer> getCoinDeskTransferData() {
        try{
            CurrentPrice currentPrice = apiService.getApiJsonData("https://api.coindesk.com/v1/bpi/currentprice.json", CurrentPrice.class);
            CoinDeskTransfer coinDeskTransfer = new CoinDeskTransfer();
            // currentprice 資料轉換處理
            List<Currency> transferCurrencyList = currentPrice.getBpi().values().stream()
                    .filter(currency -> StringUtils.isNotBlank(currency.getCode()))
                    .map(currency -> {
                        Currency transferCurrency = new Currency();
                        transferCurrency.setCurrency(currency.getCode());
                        transferCurrency.setCurrencyName(Optional.ofNullable(Objects.requireNonNull(currencyService.getCurrencyByCode(currency.getCode()).getBody()).getCurrencyName()).orElse(""));
                        transferCurrency.setRate(currency.getRate_float());
                        return transferCurrency;
                    }).collect(Collectors.toList());

            coinDeskTransfer.setUpdateTime(transferStringIsoTime(currentPrice.getTime().getUpdatedISO()));
            coinDeskTransfer.setCurrencyList(transferCurrencyList);
            return new ResponseEntity<>(coinDeskTransfer, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCoinDeskTransferData Error = {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    /**
     * 轉換isoDate 為台灣時區 yyyy/MM/dd HH:mm:ss 的時間字串
     * @param isoDate
     * @return
     */
    protected String transferStringIsoTime(String isoDate){
        if(StringUtils.isBlank(isoDate)) {
            return "";
        }
        try{
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
            // 轉換為 ZonedDateTime
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(isoDate, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            // 將 ZonedDateTime 轉換為台灣時區時間，及符合預期格式
            return zonedDateTime.withZoneSameInstant(ZoneId.of("GMT+8")).toLocalDateTime().format(outputFormatter);
        } catch (Exception e) {
            log.error("transferStringIsoTime Error = {}", e.getMessage());
            return "";
        }
    }
}
