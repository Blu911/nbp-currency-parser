package pl.nbp.parser.client;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class NbpClientTest {
    @Test
    public void shouldReturnFilesWithinInputtedDates() throws IOException {
        List<String> givenFiles = Arrays.asList(
                "c070z190409",
                "c071z190410",
                "c072z190411",
                "c073z190412"
        );

        List<String> returnedFiles = NbpClient.getFilenamesBetweenDates(
                LocalDate.of(2019, 4, 9),
                LocalDate.of(2019, 4, 12)
        );

        assertThat(returnedFiles, Matchers.is(givenFiles));
    }

    @Test
    public void shouldReturnFilesWithinInputtedDatesWhenYearsDiffer() throws IOException {
        List<String> givenFiles = Arrays.asList(
                "c250z181227",
                "c251z181228",
                "c252z181231",
                "c002z190103",
                "c003z190104"
        );

        List<String> returnedFiles = NbpClient.getFilenamesBetweenDates(
                LocalDate.of(2018, 12, 27),
                LocalDate.of(2019, 1, 4)
        );

        assertThat(returnedFiles, Matchers.is(givenFiles));
    }

    @Test
    public void shouldReturnExpectedFile() throws IOException {
        String givenTableNumber = "001/C/NBP/2019";

        String returnedFileContent = NbpClient.getFileContent("c001z190102");
        String returnedTableNumber = returnedFileContent.split("\r\n")[2];

        assertThat(returnedTableNumber.trim(), Matchers.containsString(givenTableNumber));
    }
}