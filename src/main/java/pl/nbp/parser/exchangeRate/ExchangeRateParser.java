package pl.nbp.parser.exchangeRate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

public class ExchangeRateParser {

    public static List<ExchangeRate> parseToList(String content) throws JAXBException {
        Unmarshaller unmarshaller = JAXBContext.newInstance(ExchangeRateTable.class).createUnmarshaller();
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
