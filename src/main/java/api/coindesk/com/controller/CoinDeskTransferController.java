package api.coindesk.com.controller;

import api.coindesk.com.vo.coindesktransfer.CoinDeskTransfer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * coindesk資料轉換後的幣別相關資訊
 */
@RestController
@RequestMapping("/v1/coindesk-transfer")
public class CoinDeskTransferController {

    /**
     * 取得coindesk資料轉換後的幣別相關資訊
     * @return
     */
    @GetMapping
    CoinDeskTransfer getCoinDeskTransferData(){
        return null;
    }
}
