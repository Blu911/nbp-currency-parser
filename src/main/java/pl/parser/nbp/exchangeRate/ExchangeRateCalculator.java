package pl.parser.nbp.exchangeRate;

import java.util.List;
import java.util.function.ToDoubleFunction;
/**
 * The {@code ExchangeRateCalculator} class contains methods for calculating
 * average buy rate and standard deviation of sell rate for given
 * list of {@code ExchangeRate} objects.
 */
class ExchangeRateCalculator {

    /**
     * Returns an average rate for specified list of {@code ExchangeRate} objects.
     *
     * @param exchangeRates a list of {@code ExchangeRate} objects
     * @param exchangeRateToDoubleFunction a function that parses given object to Double object
     * @return an average rate in a Double value
     */
    static Double calculateAverageRate(List<ExchangeRate> exchangeRates, ToDoubleFunction<ExchangeRate> exchangeRateToDoubleFunction) {
        return exchangeRates.stream()
                .mapToDouble(exchangeRateToDoubleFunction)
                .average()
                .getAsDouble();
    }

    /**
     * Returns an average buy rate for specified list of {@code ExchangeRate} objects
     * by using {@code calculateAverageRate} method.
     *
     * @param exchangeRates a list of {@code ExchangeRate} objects
     * @return an average buy rate in a Double value
     */
    static Double calculateAverageBuyRate(List<ExchangeRate> exchangeRates) {
        return calculateAverageRate(exchangeRates, ExchangeRate::getCurrencyBuyRate);
    }

    /**
     * Returns a standard deviation of sell rate for specified list of
     * {@code ExchangeRate} objects by using {@code calculateAverageRate} method.
     *
     * @param exchangeRates a list of {@code ExchangeRate} objects
     * @return a standard deviation of sell rate
     */
    static Double calculateStandardDeviationOfSellRate(List<ExchangeRate> exchangeRates) {
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
