package pl.nbp.parser.exchangeRate;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ExchangeRateFilterTest {

    @Test
    public void shouldFilterExchangeRatesByCurrencyCode() {
        List<ExchangeRate> givenExchangeRates = Arrays.asList(
                new ExchangeRate("GBP", 5.102f, 5.173f),
                new ExchangeRate("PLN", 1.043f, 1.104f),
                new ExchangeRate("USD", 3.432f, 3.498f),
                new ExchangeRate("EUR", 4.135f, 4.199f),
                new ExchangeRate("PLN", 1.213f, 1.245f),
                new ExchangeRate("EUR", 4.313f, 4.445f)
        );

        List<ExchangeRate> returnedExchangeRates = Arrays.asList(
                new ExchangeRate("EUR", 4.135f, 4.199f),
                new ExchangeRate("EUR", 4.313f, 4.445f)
        );

        List<ExchangeRate> filteredExchangeRates = ExchangeRateFilter.filterByCurrencyCode(givenExchangeRates, "EUR");

        assertThat(filteredExchangeRates, Matchers.is(returnedExchangeRates));
    }

    @Test
    public void shouldReturnEmptyListWhenFilteringEmptyList() {
        List<ExchangeRate> givenFilteredExchangeRates = ExchangeRateFilter.filterByCurrencyCode(new ArrayList<>(), "EUR");

        assertThat(givenFilteredExchangeRates,
                Matchers.allOf(
                        Matchers.instanceOf(List.class),
                        Matchers.empty()
                )
        );
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionForNullList() {
        ExchangeRateFilter.filterByCurrencyCode(null, "EUR");
    }
}