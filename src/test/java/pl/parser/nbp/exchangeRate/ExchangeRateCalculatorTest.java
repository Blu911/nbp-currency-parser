package pl.parser.nbp.exchangeRate;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ExchangeRateCalculatorTest {

    @Test
    public void shouldReturnAverageCurrencyBuyRate() {
        List<ExchangeRate> givenExchangeRates = Arrays.asList(
                new ExchangeRate("GBP", 5.102f, 5.173f),
                new ExchangeRate("PLN", 1.043f, 1.104f),
                new ExchangeRate("USD", 3.432f, 3.498f),
                new ExchangeRate("EUR", 4.135f, 4.199f),
                new ExchangeRate("PLN", 1.213f, 1.245f),
                new ExchangeRate("EUR", 4.313f, 4.445f)
        );

        Double returnedAverageBuyRate = ExchangeRateCalculator.calculateAverageBuyRate(givenExchangeRates);

        assertThat(returnedAverageBuyRate, Matchers.closeTo(3.206, 0.001));
    }

    @Test
    public void shouldReturnAverageCurrencySellRate() {
        List<ExchangeRate> givenExchangeRates = Arrays.asList(
                new ExchangeRate("GBP", 5.102f, 5.173f),
                new ExchangeRate("PLN", 1.043f, 1.104f),
                new ExchangeRate("USD", 3.432f, 3.498f),
                new ExchangeRate("EUR", 4.135f, 4.199f),
                new ExchangeRate("PLN", 1.213f, 1.245f),
                new ExchangeRate("EUR", 4.313f, 4.445f)
        );

        Double returnedAverageSellRate = ExchangeRateCalculator.calculateAverageRate(givenExchangeRates, ExchangeRate::getCurrencySellRate);

        assertThat(returnedAverageSellRate, Matchers.closeTo(3.277, 0.001));
    }

    @Test
    public void shouldReturnStandardDeviation() {
        List<ExchangeRate> givenExchangeRates = Arrays.asList(
                new ExchangeRate("GBP", 5.102f, 5.173f),
                new ExchangeRate("PLN", 1.043f, 1.104f),
                new ExchangeRate("USD", 3.432f, 3.498f),
                new ExchangeRate("EUR", 4.135f, 4.199f),
                new ExchangeRate("PLN", 1.213f, 1.245f),
                new ExchangeRate("EUR", 4.313f, 4.445f)
        );

        Double returnedDeviationOfSellRate = ExchangeRateCalculator.calculateStandardDeviationOfSellRate(givenExchangeRates);

        assertThat(returnedDeviationOfSellRate, Matchers.closeTo(1.565, 0.001));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionForEmptyList() {
        ExchangeRateCalculator.calculateAverageBuyRate(new ArrayList<>());
        ExchangeRateCalculator.calculateStandardDeviationOfSellRate(new ArrayList<>());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionForNullList() {
        ExchangeRateCalculator.calculateAverageBuyRate(null);
        ExchangeRateCalculator.calculateStandardDeviationOfSellRate(null);
    }

}