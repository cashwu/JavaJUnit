package com.cashwu.javajunit;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * @author cash.wu
 * @since 2024/06/17
 */
public class MockHttpURLConnection extends HttpURLConnection {
    private ByteArrayInputStream inputStream;

    /**
     * Constructs a URL connection to the specified URL. A connection to
     * the object referenced by the URL is not created.
     *
     * @param url the specified URL.
     */
    protected MockHttpURLConnection(URL url) {
        super(url);
    }

    public MockHttpURLConnection() throws MalformedURLException {
        super(new URL("http://localhost"));
    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }

    @Override
    public void connect() throws IOException {
    }

    public void setupGetInputStream(ByteArrayInputStream inputStream) throws IOException {
        this.inputStream = inputStream;

    }
}
