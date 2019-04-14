package pl.nbp.parser.inputArgs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class InputArgs {
    private String currencyCode;
    private LocalDate startDate;
    private LocalDate endDate;
}
