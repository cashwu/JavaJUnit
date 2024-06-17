package com.cashwu.javajunit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author cash.wu
 * @since 2024/06/17
 */
public class WebClientTests {

    @BeforeEach
    void setUp() {
        URL.setURLStreamHandlerFactory(new StubURLStreamHandlerFactory());
    }

    @Test
    void testGetContentOK() throws IOException {

        WebClient webClient = new WebClient();

        String content = webClient.getContent(new URL("https://example.com"));
        assertEquals("it works", content);
    }

    private static class StubURLStreamHandlerFactory implements URLStreamHandlerFactory {
        @Override
        public URLStreamHandler createURLStreamHandler(String protocol) {
            return new StubHttpURLStreamHandler();
        }

        private static class StubHttpURLStreamHandler extends URLStreamHandler {
            @Override
            protected URLConnection openConnection(URL url) throws IOException {
                return new StubHttpURLConnection(url);
            }
        }
    }


    private static class StubHttpURLConnection extends URLConnection {
        private boolean isInput = true;

        public StubHttpURLConnection(URL url) {
            super(url);
        }

        @Override
        public void connect() throws IOException {
        }

        public void setDoInput(boolean input) {
            isInput = input;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            if (!isInput) {
                throw new ProtocolException("doInput = false, pls call setDoInput(true) first");
            }

            return new ByteArrayInputStream("it works".getBytes());
        }

    }
}
