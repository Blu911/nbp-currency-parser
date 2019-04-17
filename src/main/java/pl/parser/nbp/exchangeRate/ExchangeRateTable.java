package pl.parser.nbp.exchangeRate;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The {@code ExchangeRateTable} class represents the content
 * of "table of courses" marker in downloaded NBP XML file.
 */
@XmlRootElement(name = "tabela_kursow")
class ExchangeRateTable {

    /** The list is used for {@code ExchangeRateItem} objects storage. */
    @Setter
    private List<ExchangeRateItem> items;

    @XmlElement(name = "pozycja")
    List<ExchangeRateItem> getItems() {
        return items;
    }

    /**
     * The {@code ExchangeRateItem} class represents the content
     * of "position" marker in downloaded NBP XML file.
     */
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
