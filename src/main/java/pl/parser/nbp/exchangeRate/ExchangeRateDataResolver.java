package pl.parser.nbp.exchangeRate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.parser.nbp.client.NbpClient;
import pl.parser.nbp.inputArgs.InputArgs;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code ExchangeRateDataResolver} class contains {@code ExchangeRateData}
 * class that stores currency average buy rate and standard deviation sell rate.
 * The class also provides {@code resolve} method which based on specified
 * {@code InputArgs} object returns {@code ExchangeRateData} object.
 */
public class ExchangeRateDataResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateDataResolver.class);

    @AllArgsConstructor
    @Getter
    public static class ExchangeRateData {
        private final Double currencyAverageBuyRate;
        private final Double currencyStandardDeviationSellRate;
    }

    /**
     * The {@code resolve} method first uses {@link NbpClient} to obtain
     * filenames for entered time period. Then the method downloads and with
     * {@link ExchangeRateParser} parses the XML files' content to a list of
     * {@code ExchangeRate} objects. The list is then filtered by
     * {@link ExchangeRateFilter} based on entered currency code. In the end
     * this method returns {@code ExchangeRateData} object that contains
     * calculated by {@link ExchangeRateCalculator} currency average buy rate
     * and standard deviation sell rate.
     *
     * @param inputArgs an object that represents arguments entered by user
     * @return {@code ExchangeRateData} object with currency average buy rate and standard deviation sell rate
     * @throws IOException on input error
     * @see IOException
     */
    public static ExchangeRateData resolve(InputArgs inputArgs) throws IOException {
        List<String> filenamesBetweenDates = NbpClient.getFilenamesBetweenDates(
                inputArgs.getStartDate(),
                inputArgs.getEndDate()
        );

        List<ExchangeRate> exchangeRates = filenamesBetweenDates.stream().flatMap(filename -> {
            try {
                return ExchangeRateParser.parseToList(NbpClient.getFileContent(filename)).stream();
            } catch (IOException e) {
                LOGGER.error("IO error during parsing of '{}' file", filename);
            } catch (JAXBException e) {
                LOGGER.error("XML parse error during parsing of '{}' file", filename);
            }
            return null;
        }).collect(Collectors.toList());

        List<ExchangeRate> filteredExchangeRates = ExchangeRateFilter.filterByCurrencyCode(exchangeRates, inputArgs.getCurrencyCode());

        return new ExchangeRateData(
                ExchangeRateCalculator.calculateAverageBuyRate(filteredExchangeRates),
                ExchangeRateCalculator.calculateStandardDeviationOfSellRate(filteredExchangeRates)
        );
    }
}
