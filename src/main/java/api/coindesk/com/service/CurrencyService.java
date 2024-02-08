package api.coindesk.com.service;

import api.coindesk.com.entity.CurrencyEntity;
import api.coindesk.com.mapper.CurrencyMapper;
import api.coindesk.com.repository.CurrencyRepository;
import api.coindesk.com.vo.currency.Currency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * 幣別資料表維護
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CurrencyService {

    final CurrencyRepository repository;
    final CurrencyMapper mapper;
    public ResponseEntity<List<Currency>> getAll() {
        return new ResponseEntity<>(mapper.entityToCurrencyList(repository.findAll()), HttpStatus.OK);
    }

    public ResponseEntity<Currency> getCurrencyByCode(String code) {
        CurrencyEntity currency = Optional.ofNullable(repository.findByCode(code)).orElseThrow(() -> new NullPointerException("Currency Code NotFound = " + code));
        return new ResponseEntity<>(mapper.entityToCurrency(currency), HttpStatus.OK);
    }

    public ResponseEntity<Boolean> create(Currency createDto) {
        try {
            repository.save(mapper.currencyToEntity(createDto));
        } catch (Exception e) {
            log.error("Currency Create Error： {}", e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    public ResponseEntity<Currency> replace(String code, Currency replaceDto) {
        CurrencyEntity originalCurrency = Optional.ofNullable(repository.findByCode(code)).orElseThrow(() -> new NullPointerException("Currency Code NotFound = " + code));
        CurrencyEntity newCurrency = repository.save(mapper.toReplaceEntity(originalCurrency, replaceDto));
        return new ResponseEntity<>(mapper.entityToCurrency(newCurrency), HttpStatus.OK);
    }

    public ResponseEntity<Boolean> deleteCurrency(String code) {
        try {
            CurrencyEntity currency = Optional.ofNullable(repository.findByCode(code)).orElseThrow(() -> new NullPointerException("Currency Code NotFound = " + code));
            repository.delete(currency);
        } catch (Exception e) {
            log.error("Currency Delete Error： {}", e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
