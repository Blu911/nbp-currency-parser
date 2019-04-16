package pl.parser.nbp.exchangeRate;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tabela_kursow")
class ExchangeRateTable {
    @Setter
    private List<ExchangeRateItem> items;

    @XmlElement(name = "pozycja")
    List<ExchangeRateItem> getItems() {
        return items;
    }

    @Setter
    @XmlRootElement(name = "pozycja")
    public static class ExchangeRateItem {
        String currencyCode;
        String currencyBuyRate;
        String currencySellRate;

        @XmlElement(name = "kod_waluty")
        String getCurrencyCode() {
            return currencyCode;
        }

        @XmlElement(name = "kurs_kupna")
        String getCurrencyBuyRate() {
            return currencyBuyRate;
        }

        @XmlElement(name = "kurs_sprzedazy")
        String getCurrencySellRate() {
            return currencySellRate;
        }
    }
}
