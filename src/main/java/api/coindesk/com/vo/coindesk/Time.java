package api.coindesk.com.vo.coindesk;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * coindesk api 物件
 */
@Getter
@Setter
@ToString
public class Time {
    private String updated;
    private String updatedISO;
    private String updateduk;
}
