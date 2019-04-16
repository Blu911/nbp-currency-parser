package pl.parser.nbp.client;

import com.jcabi.http.request.JdkRequest;

import java.io.IOException;

class HttpClient {
    static String getStringFromUri(String uri) throws IOException {
        return new String(new JdkRequest(uri).fetch().binary());
    }
}
