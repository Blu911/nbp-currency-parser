package pl.parser.nbp.inputArgs;

import java.time.LocalDate;

/**
 * The {@code InputArgsParser} class contains a method for parsing
 * arguments entered by user into {@code InputArgs} object.
 */
public class InputArgsParser {

    /**
     * Constructs a new {@code InputArgs} object and sets the values
     * of its fields by parsing inputted by user {@code String} arguments
     * into required classes.
     *
     * @param currencyCode a currency code in ISO 4217 format e.g: USD, EUR, PLN, GBP
     * @param startDate start date in ISO 8601 format e.g: 2005-01-02
     * @param endDate end date in ISO 8601 format e.g: 2005-01-02
     *
     * @return a {@link InputArgs} object
     */
    public static InputArgs parseInputArgs (String currencyCode, String startDate, String endDate) {
        InputArgs inputArgs = new InputArgs();
        inputArgs.setCurrencyCode(currencyCode);
        inputArgs.setStartDate(LocalDate.parse(startDate));
        inputArgs.setEndDate(LocalDate.parse(endDate));
        return inputArgs;
    }
}
