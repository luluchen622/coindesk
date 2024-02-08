package api.coindesk.com.vo.currency;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/**
 * 幣別資料表維護處理物件
 */
@Getter
@Setter
@Builder
public class Currency {

    /** 幣別 */
    private String code;
    /** 幣別中文名稱 */
    private String currencyName;
}
