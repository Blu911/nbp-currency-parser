package pl.parser.nbp.inputArgs;

import java.time.LocalDate;

public class InputArgsParser {

    public static InputArgs parseInputArgs (String currencyCode, String startDate, String endDate) {
        InputArgs inputArgs = new InputArgs();
        inputArgs.setCurrencyCode(currencyCode);
        inputArgs.setStartDate(LocalDate.parse(startDate));
        inputArgs.setEndDate(LocalDate.parse(endDate));
        return inputArgs;
    }
}
