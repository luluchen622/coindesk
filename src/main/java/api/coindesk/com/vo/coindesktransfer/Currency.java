package api.coindesk.com.vo.coindesktransfer;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * coindesk資料轉換幣別物件
 */
@Getter
@Setter
public class Currency {

    /** 幣別 */
    private String currency;
    /** 幣別中文名稱 */
    private String currencyName;
    /** 幣別匯率 */
    private BigDecimal rate;
}
