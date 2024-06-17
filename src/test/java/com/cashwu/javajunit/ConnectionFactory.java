package com.cashwu.javajunit;

import java.io.IOException;
import java.io.InputStream;

public interface ConnectionFactory {
    InputStream getData() throws IOException;
}
