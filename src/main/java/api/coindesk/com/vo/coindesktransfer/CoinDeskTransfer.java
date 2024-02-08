package api.coindesk.com.vo.coindesktransfer;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * coindesk資料轉換後的幣別相關資訊物件
 */
@Getter
@Setter
public class CoinDeskTransfer {

    /** 更新時間 1990/01/01 00:00:00 */
    private String updateTime;
    /** 幣別相關資訊 (幣別，幣別中文名稱，以及匯率) */
    private List<Currency> currencyList;

}
