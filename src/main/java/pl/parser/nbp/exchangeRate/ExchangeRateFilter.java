package pl.parser.nbp.exchangeRate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code ExchangeRateFilter} class contains a method for filtering
 * the List of {@code ExchangeRate} objects by {@code currency code}.
 */
class ExchangeRateFilter {

    /**
     * Creates a List of {@code ExchangeRate} objects that contains only
     * exchange rates for only the specified currency.
     *
     * @param exchangeRates a list of {@link ExchangeRate} objects
     * @param currencyCode a currency code in ISO 4217 format e.g: USD, EUR, PLN, GBP
     * @return a list of filtered {@link ExchangeRate} objects
     */
    static List<ExchangeRate> filterByCurrencyCode(List<ExchangeRate> exchangeRates, String currencyCode) {
        return exchangeRates.stream()
                .filter(e -> e.getCurrencyCode().equals(currencyCode))
                .collect(Collectors.toList());
    }
}
