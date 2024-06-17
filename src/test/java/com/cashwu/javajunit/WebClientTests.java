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

    //    @Test
    //    void testGetContentOK() throws IOException {
    //
    //        var mockHttpURLConnection = new MockHttpURLConnection();;
    //        mockHttpURLConnection.setupGetInputStream(new ByteArrayInputStream("it works".getBytes()));
    //
    //        TestableWebClient webClient = new TestableWebClient();
    //        webClient.setHttpURLConnection(mockHttpURLConnection);
    //
    //        String content = webClient.getContent(new URL("https://example.com"));
    //        assertEquals("it works", content);
    //    }

    //    @Test
    //    void testGetContentOK() throws IOException {
    //
    //        var mockHttpURLConnection = new MockConnectionFactory();
    //        mockHttpURLConnection.setData(new ByteArrayInputStream("it works".getBytes()));
    //
    //        WebClient2 webClient = new WebClient2();
    //        String content = webClient.getContent(mockHttpURLConnection);
    //        assertEquals("it works", content);
    //    }

    @Test
    void testGetContentOK() throws IOException {

        var mockHttpURLConnection = new MockConnectionFactory();
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.setBuffer("it works");
        mockHttpURLConnection.setData(mockInputStream);

        WebClient2 webClient = new WebClient2();
        String content = webClient.getContent(mockHttpURLConnection);
        assertEquals("it works", content);
        mockInputStream.verify();
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


    private class MockInputStream extends InputStream {

        private String buffer;
        private int position = 0;
        private int closeCount = 0;

        public void setBuffer(String buffer) {
            this.buffer = buffer;
        }

        @Override
        public int read() throws IOException {
            if (position == this.buffer.length()) {
                return -1;
            }
            return this.buffer.charAt(position++);
        }

        public void close() throws IOException {
            closeCount++;
            super.close();
        }

        public void verify() {
            if (closeCount != 1) {
                throw new AssertionError("Expected 1 but got " + closeCount);
            }
        }
    }
}
