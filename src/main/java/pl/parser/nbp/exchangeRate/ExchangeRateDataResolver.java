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

public class ExchangeRateDataResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateDataResolver.class);

    @AllArgsConstructor
    @Getter
    public static class ExchangeRateData {
        private final Double currencyAverageBuyRate;
        private final Double currencyStandardDeviationSellRate;
    }

    public static ExchangeRateData resolve (InputArgs inputArgs) throws IOException {
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
