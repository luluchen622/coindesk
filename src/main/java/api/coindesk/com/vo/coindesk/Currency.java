package api.coindesk.com.vo.coindesk;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * coindesk api 幣別物件
 */
@Getter
@Setter
@ToString
public class Currency {
    private String code;
    private String symbol;
    private String rate;
    private String description;
    private BigDecimal rate_float;
}
