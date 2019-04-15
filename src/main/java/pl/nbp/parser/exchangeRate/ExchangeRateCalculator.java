package pl.nbp.parser.exchangeRate;

import java.util.List;
import java.util.function.ToDoubleFunction;

public class ExchangeRateCalculator {

    static Double calculateAverageRate (List<ExchangeRate> exchangeRates, ToDoubleFunction<ExchangeRate> exchangeRateToDoubleFunction) {
        return exchangeRates.stream()
                .mapToDouble(exchangeRateToDoubleFunction)
                .average()
                .getAsDouble();
    }

    public static Double calculateAverageBuyRate(List<ExchangeRate> exchangeRates) {
        return calculateAverageRate(exchangeRates, ExchangeRate::getCurrencyBuyRate);
    }

    public static Double calculateStandardDeviationOfSellRate(List<ExchangeRate> exchangeRates) {
        Double averageSellRate = calculateAverageRate(exchangeRates, ExchangeRate::getCurrencySellRate);
        return Math.sqrt(
                exchangeRates.stream()
                .mapToDouble(e -> e.getCurrencySellRate() - averageSellRate)
                .map(e -> Math.pow(e, 2))
                .average()
                .getAsDouble()
        );
    }
}
