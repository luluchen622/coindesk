package tw.com.coindesk.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Time {
    private String updated;
    private String updatedISO;
    private String updateduk;
}
