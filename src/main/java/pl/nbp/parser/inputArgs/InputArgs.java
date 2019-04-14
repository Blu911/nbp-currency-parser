package pl.nbp.parser.inputArgs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class InputArgs {
    private String currencyCode;
    private LocalDate startDate;
    private LocalDate endDate;
}
