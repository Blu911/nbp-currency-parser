package pl.parser.nbp.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code NbpClient} class contains a NBP URI and
 * several methods that allow to get names and content of
 * available XML files for the entered time period.
 */
public class NbpClient {

    private static final String NBP_URI = "http://www.nbp.pl/kursy/xml";

    /**
     * Creates a List of filenames for all XML files that are
     * available at NBP URI for the entered time period.
     *
     * @param startDate start date for searched time period, e.g 2016.04.12
     * @param endDate end date for searched time period, e.g 2016.04.15
     * @return a List of all filenames for the given time period
     * @throws IOException on input error
     * @see IOException
     */
    private static List<String> getAvailableFiles(LocalDate startDate, LocalDate endDate) throws IOException {
        int startDateYear = startDate.getYear();
        int endDateYear = endDate.getYear();
        List<String> availableFiles = new LinkedList<>();
        while (startDateYear <= endDateYear) {
            availableFiles.addAll(getAvailableFilesForYear(startDateYear));
            startDateYear++;
        }
        return availableFiles;
    }

    /**
     * Creates a List of filenames for all XML files that are
     * available at NBP URI for the entered year.
     *
     * @param year int value that represents a year e.g 2006
     * @return a List of all filenames for the given year
     * @throws IOException on input error
     * @see IOException
     */
    private static List<String> getAvailableFilesForYear(int year) throws IOException {
        int currentYear = LocalDate.now().getYear();
        String filename = year == currentYear ? "dir.txt" : String.format("dir%d.txt", year);
        return Arrays.asList(
                    HttpClient.getStringFromUri(
                             String.format("%s/%s", NBP_URI, filename)
                    ).split("\r\n")
        );
    }

    /**
     * Creates a List of XML filenames that contain "c" table of courses
     * and are available at NBP URI address for the entered time period.
     *
     * @param startDate start date for searched time period, e.g 2016.04.12
     * @param endDate end date for searched time period, e.g 2016.04.15
     * @return a List of filenames that contains "c" table of courses for the given time period
     * @throws IOException on input error
     * @see IOException
     */
    public static List<String> getFilenamesBetweenDates(LocalDate startDate, LocalDate endDate) throws IOException {
        List<String> availableFiles = getAvailableFiles(startDate, endDate);
        return availableFiles.stream()
                .filter(f -> f.contains("c"))
                .map(f -> f.length() > 11 ? f.substring(1) : f)
                .filter(f -> {
                    String fileDateString = f.substring(5, 11);
                    LocalDate fileDate = LocalDate.parse(fileDateString, DateTimeFormatter.ofPattern("yyMMdd"));
                    return !(fileDate.isAfter(endDate) || fileDate.isBefore(startDate));
                })
                .collect(Collectors.toList());
    }

    /**
     * The {@code getFileContent} method uses {@link HttpClient} class to
     * make an HTTP request to the specified NBP URI that consists
     * provided {@code filename} and returns a content of XML file
     * as a string value.
     *
     * @param filename name of file from which content will be downloaded
     * @return a string that contains XML file content
     * @throws IOException on input error
     * @see IOException
     */
    public static String getFileContent(String filename) throws IOException {
        return HttpClient.getStringFromUri(String.format("%s/%s.xml", NBP_URI, filename));
    }
}
