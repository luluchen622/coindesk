package api.coindesk.com.vo.currency;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * 幣別資料表維護處理物件
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Currency {

    /** 幣別 */
    @NotBlank
    private String code;
    /** 幣別中文名稱 */
    private String currencyName;
}
