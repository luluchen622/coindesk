package api.coindesk.com.controller;

import api.coindesk.com.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import api.coindesk.com.vo.currency.Currency;

import java.util.List;

/**
 * 幣別資料表維護
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/currency")
public class CurrencyController {

    final CurrencyService service;

    /**
     * 取得全部幣別資料
     * @return
     */
    @GetMapping("/all")
    ResponseEntity<List<Currency>> getAll() {
        return service.getAll();
    }

    /**
     * 查詢單筆幣別資料
     * @param id
     * @return
     */
    @GetMapping("/{code}")
    ResponseEntity<Currency> getOne(@PathVariable("code") String code) {
        return service.getOne(code);
    }

    /**
     * 新增單筆幣別資料
     * @param dto
     * @return
     */
    @PostMapping
    ResponseEntity<Boolean> create(@Validated @RequestBody Currency dto) {
        return service.create(dto);
    }

    /**
     * 修改單筆幣別資料
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{code}")
    ResponseEntity<Currency> replace(@PathVariable("code") String code, @Validated @RequestBody Currency dto) {
        return service.replace(code, dto);
    }

    /**
     * 刪除單筆幣別資料
     * @param id
     * @return
     */
    @DeleteMapping("/{code}")
    ResponseEntity<Boolean> deleteCurrency(@PathVariable("code") String code) {
        return service.deleteCurrency(code);
    }

}
