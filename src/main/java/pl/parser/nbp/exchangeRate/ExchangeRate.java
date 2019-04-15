package pl.parser.nbp.exchangeRate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {
    private String currencyCode;
    private Float currencyBuyRate;
    private Float currencySellRate;
}
