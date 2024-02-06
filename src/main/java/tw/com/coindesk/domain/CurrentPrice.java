package tw.com.coindesk.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class CurrentPrice {

    private Time time;
    private String disclaimer;
    private String chartName;
    private Map<String, Currency> bpi;

}
