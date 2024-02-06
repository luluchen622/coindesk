package tw.com.coindesk.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

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
