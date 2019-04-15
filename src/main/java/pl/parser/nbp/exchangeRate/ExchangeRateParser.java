package pl.parser.nbp.exchangeRate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

public class ExchangeRateParser {

    public static List<ExchangeRate> parseToList(String content) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ExchangeRateTable.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader stringReader = new StringReader(content);

        Object unmarshal = unmarshaller.unmarshal(stringReader);
        ExchangeRateTable exchangeRateTable = (ExchangeRateTable) unmarshal;

        return exchangeRateTable.getItems().stream().map(exchangeRateItem -> {
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setCurrencyCode(exchangeRateItem.getCurrencyCode());
            exchangeRate.setCurrencyBuyRate(new Float(exchangeRateItem.getCurrencyBuyRate().replace(",", ".")));
            exchangeRate.setCurrencySellRate(new Float(exchangeRateItem.getCurrencySellRate().replace(",", ".")));
            return exchangeRate;
        }).collect(Collectors.toList());
    }
}
