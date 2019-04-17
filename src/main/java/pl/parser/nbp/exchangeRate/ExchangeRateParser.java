package pl.parser.nbp.exchangeRate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code ExchangeRateParser} class contains a method for parsing
 * downloaded from NBP XML file content into a List of {@code ExchangeRate} objects.
 */
class ExchangeRateParser {

    /**
     * Uses {@link JAXBContext} class to unmarshal XML data from the specified Reader
     * and return the resulting content tree in a form of {@code ExchangeRateTable}
     * class object that is then mapped into a List of {@code ExchangeRate} objects.
     *
     * @param content a string which contains XML data to be parsed
     * @return a list of {@link ExchangeRate} objects
     * @throws JAXBException when JAXB is unable to call its methods
     * @see JAXBException
     */
    static List<ExchangeRate> parseToList(String content) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ExchangeRateTable.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader stringReader = new StringReader(content);

        ExchangeRateTable exchangeRateTable = (ExchangeRateTable) unmarshaller.unmarshal(stringReader);

        return exchangeRateTable.getItems().stream().map(exchangeRateItem -> {
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setCurrencyCode(exchangeRateItem.getCurrencyCode());
            exchangeRate.setCurrencyBuyRate(new Float(exchangeRateItem.getCurrencyBuyRate().replace(",", ".")));
            exchangeRate.setCurrencySellRate(new Float(exchangeRateItem.getCurrencySellRate().replace(",", ".")));
            return exchangeRate;
        }).collect(Collectors.toList());
    }
}
