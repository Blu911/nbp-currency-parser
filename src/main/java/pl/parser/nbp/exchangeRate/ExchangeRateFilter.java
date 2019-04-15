package pl.parser.nbp.exchangeRate;

import java.util.List;
import java.util.stream.Collectors;

public class ExchangeRateFilter {

    public static List<ExchangeRate> filterByCurrencyCode (List<ExchangeRate> exchangeRates, String currencyCode) {
        return exchangeRates.stream()
                .filter(e -> e.getCurrencyCode().equals(currencyCode))
                .collect(Collectors.toList());
    }
}
