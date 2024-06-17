package com.cashwu.javajunit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author cash.wu
 * @since 2024/06/17
 */
public class HttpURLConnectionFactory implements ConnectionFactory {

    private final URL url;

    public HttpURLConnectionFactory(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getData() throws IOException {
        URLConnection urlConnection = this.url.openConnection();
        return urlConnection.getInputStream();
    }
}
