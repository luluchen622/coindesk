package api.coindesk.com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import api.coindesk.com.vo.currency.Currency;

import java.util.List;

/**
 * 幣別資料表維護
 */
@RestController
@RequestMapping("/v1/currency")
public class CurrencyController {

    /**
     * 取得全部幣別資料
     * @return
     */
    @GetMapping("/all")
    ResponseEntity<List<Currency>> getAll() {
        return null;
    }

    /**
     * 查詢單筆幣別資料
     * @param id
     * @return
     */
    @GetMapping("/{code}")
    ResponseEntity<Currency> getOne(@PathVariable("code") String code) {
        return null;
    }

    /**
     * 新增單筆幣別資料
     * @param dto
     * @return
     */
    @PostMapping
    ResponseEntity<Boolean> create(@Validated @RequestBody Currency dto) {
        return null;
    }

    /**
     * 修改單筆幣別資料
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{code}")
    ResponseEntity<Currency> replace(@PathVariable("code") String code, @Validated @RequestBody Currency dto) {
        return null;
    }

    /**
     * 刪除單筆幣別資料
     * @param id
     * @return
     */
    @DeleteMapping("/{code}")
    ResponseEntity<Boolean> delete(@PathVariable("code") Long code) {
        return null;
    }

}
