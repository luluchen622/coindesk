package api.coindesk.com.mapper;

import api.coindesk.com.entity.CurrencyEntity;
import api.coindesk.com.vo.currency.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CurrencyMapper {

    List<Currency> entityToCurrencyList(List<CurrencyEntity> entities);

    Currency entityToCurrency(CurrencyEntity entity);

    CurrencyEntity toReplaceEntity(@MappingTarget CurrencyEntity entity, Currency replaceDto);

    CurrencyEntity currencyToEntity(Currency createDto);
}
