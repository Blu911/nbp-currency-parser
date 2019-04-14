package pl.nbp.parser.exchangeRate;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ExchangeRate {
    private String currencyCode;
    private Float currencyBuyRate;
    private Float currencySellRate;
}
