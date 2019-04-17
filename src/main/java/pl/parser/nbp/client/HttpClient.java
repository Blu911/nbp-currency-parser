package pl.parser.nbp.client;

import com.jcabi.http.request.JdkRequest;

import java.io.IOException;

/**
 * The {@code HttpClient} class contains a method for downloading
 * from entered URI the XML data.
 */
class HttpClient {

    /**
     * The {@code getStringFromUri} method uses {@link com.jcabi.http} client to
     * make an HTTP request and return an response content as a {@code String} value.
     *
     * @param uri a string of characters that unambiguously identifies a particular resource
     *            e.g "http://www.nbp.pl/kursy/xml/c073z070413.xml"
     * @return a string that contains resource content
     * @throws IOException on input error
     * @see IOException
     */
    static String getStringFromUri(String uri) throws IOException {
        return new String(new JdkRequest(uri).fetch().binary());
    }
}
