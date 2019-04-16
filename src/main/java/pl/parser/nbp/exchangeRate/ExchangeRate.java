package pl.parser.nbp.exchangeRate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class ExchangeRate {
    private String currencyCode;
    private Float currencyBuyRate;
    private Float currencySellRate;
}
