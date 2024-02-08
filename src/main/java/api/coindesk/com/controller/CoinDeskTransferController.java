package api.coindesk.com.controller;

import api.coindesk.com.service.CoinDeskTransferService;
import api.coindesk.com.vo.coindesktransfer.CoinDeskTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * coindesk資料轉換後的幣別相關資訊
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/coindesk-transfer")
public class CoinDeskTransferController {

    final CoinDeskTransferService service;

    /**
     * 取得coindesk資料轉換後的幣別相關資料
     */
    @GetMapping
    ResponseEntity<CoinDeskTransfer> getCoinDeskTransferData(){
        return service.getCoinDeskTransferData();
    }
}
