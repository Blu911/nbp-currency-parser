package pl.parser.nbp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.parser.nbp.exchangeRate.ExchangeRateDataResolver;
import pl.parser.nbp.inputArgs.InputArgs;
import pl.parser.nbp.inputArgs.InputArgsParser;

import java.io.IOException;

public class MainClass {
    public static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateDataResolver.class);

    public static void main(String[] args) {
        InputArgs inputArgs = InputArgsParser.parseInputArgs(args[0], args[1], args[2]);
        System.out.format("Data entered: %s %s %s\n",
                inputArgs.getCurrencyCode(),
                inputArgs.getStartDate(),
                inputArgs.getEndDate());
        try {
            ExchangeRateDataResolver.ExchangeRateData exchangeRateData = ExchangeRateDataResolver.resolve(inputArgs);
            System.out.format("%.4f\n", exchangeRateData.getCurrencyAverageBuyRate());
            System.out.format("%.4f\n", exchangeRateData.getCurrencyStandardDeviationSellRate());
        } catch (IOException e) {
            LOGGER.error("Error while parsing input args");
        }
    }
}
