package api.coindesk.com.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 幣別資料表
 */
@Entity
@Table(name = "CURRENCY")
@Getter
@Setter
public class CurrencyEntity {

    /** 幣別 */
    @Id
    @Column(name = "CODE", length = 3, unique = true)
    private String code;

    /** 幣別中文名稱 */
    @Column(name = "CURRENCY_NAME")
    private String currencyName;

}
