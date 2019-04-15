package pl.parser.nbp.exchangeRate;

import org.hamcrest.Matchers;
import org.junit.Test;
import pl.parser.nbp.inputArgs.InputArgs;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class ExchangeRateDataResolverTest {

    @Test
    public void shouldReturnExchangeRateDataForGivenArgs() throws IOException {
        InputArgs givenInputArgs = new InputArgs(
                "EUR",
                LocalDate.of(2019, 4, 8),
                LocalDate.of(2019, 4, 12)
        );

        ExchangeRateDataResolver.ExchangeRateData resultExchangeRateData =
                ExchangeRateDataResolver.resolve(givenInputArgs);

        assertThat(
                resultExchangeRateData,
                Matchers.allOf(
                        Matchers.hasProperty(
                                "currencyAverageBuyRate",
                                Matchers.closeTo(4.242, 0.001)
                        ),
                        Matchers.hasProperty(
                                "currencyStandardDeviationSellRate",
                                Matchers.closeTo(0.003, 0.001)
                        )
                )
        );
    }
}