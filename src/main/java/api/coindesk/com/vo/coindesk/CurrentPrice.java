package api.coindesk.com.vo.coindesk;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * coindesk api 物件
 */
@Getter
@Setter
@ToString
public class CurrentPrice {

    private Time time;
    private String disclaimer;
    private String chartName;
    private Map<String, Currency> bpi;

}
