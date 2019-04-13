package pl.nbp.parser.inputArgs;

import java.time.LocalDate;
import java.util.Objects;

public class InputArgs {
    private String currencyCode;
    private LocalDate startDate;
    private LocalDate endDate;

    public InputArgs() {

    }

    public InputArgs(String currencyCode, LocalDate startDate, LocalDate endDate) {
        this.currencyCode = currencyCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputArgs inputArgs = (InputArgs) o;
        return Objects.equals(currencyCode, inputArgs.currencyCode) &&
                Objects.equals(startDate, inputArgs.startDate) &&
                Objects.equals(endDate, inputArgs.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyCode, startDate, endDate);
    }
}
