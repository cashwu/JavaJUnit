package com.cashwu.javajunit;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author cash.wu
 * @since 2024/06/17
 */
public class MockConnectionFactory implements ConnectionFactory {

    private InputStream inputStream;

    public void setData(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStream getData() throws IOException {
        return inputStream;
    }
}
