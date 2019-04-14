package pl.nbp.parser.exchangeRate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode
public class ExchangeRate {
    private String currencyCode;
    private Float currencyBuyRate;
    private Float currencySellRate;
}
