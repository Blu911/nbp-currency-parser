package pl.nbp.parser.inputArgs;

import org.junit.Test;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class InputArgsParserTest {

    @Test
    public void shouldParseInputArgs() {
        InputArgs givenArgs = new InputArgs(
                "USD",
                LocalDate.of(2019,4,10),
                LocalDate.of(2019,4,13)
        );

        InputArgs inputArgs = InputArgsParser.parseInputArgs("USD", "2019-04-10", "2019-04-13");

        assertEquals(inputArgs, givenArgs);
    }
}