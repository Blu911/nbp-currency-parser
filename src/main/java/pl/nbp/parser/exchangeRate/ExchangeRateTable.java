package pl.nbp.parser.exchangeRate;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tabela_kursow")
public class ExchangeRateTable {

    @Setter
    @XmlRootElement(name = "pozycja")
    public static class ExchangeRateItem {
        String currencyCode;
        String currencyBuyRate;
        String currencySellRate;

        @XmlElement(name = "kod_waluty")
        public String getCurrencyCode() {
            return currencyCode;
        }

        @XmlElement(name = "kurs_kupna")
        public String getCurrencyBuyRate() {
            return currencyBuyRate;
        }

        @XmlElement(name = "kurs_sprzedazy")
        public String getCurrencySellRate() {
            return currencySellRate;
        }
    }
    @Setter
    private List<ExchangeRateItem> items;

    @XmlElement(name = "pozycja")
    public List<ExchangeRateItem> getItems() {
        return items;
    }
}