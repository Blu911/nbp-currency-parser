package pl.parser.nbp.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class NbpClient {
    private static final String NBP_URI = "http://www.nbp.pl/kursy/xml";

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

    private static List<String> getAvailableFilesForYear(int startDateYear) throws IOException {
        int currentYear = LocalDate.now().getYear();
        String filename = startDateYear == currentYear ? "dir.txt" : String.format("dir%d.txt", startDateYear);
        return Arrays.asList(HttpClient.getStringFromUri(String.format("%s/%s", NBP_URI, filename)).split("\r\n"));
    }

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

    public static String getFileContent(String filename) throws IOException {
        return HttpClient.getStringFromUri(String.format("%s/%s.xml", NBP_URI, filename));
    }
}
