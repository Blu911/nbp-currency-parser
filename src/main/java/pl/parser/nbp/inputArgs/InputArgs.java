package pl.parser.nbp.inputArgs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputArgs {
    private String currencyCode;
    private LocalDate startDate;
    private LocalDate endDate;
}
