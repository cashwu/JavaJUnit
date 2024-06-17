package com.cashwu.javajunit;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author cash.wu
 * @since 2024/06/17
 */
public class TestableWebClient extends WebClient1{

    private HttpURLConnection connection;

    public void setHttpURLConnection(HttpURLConnection connection) {

        this.connection = connection;
    }

    public HttpURLConnection createHttpURLConnection(URL url) throws IOException {
        return this.connection;
    }
}
