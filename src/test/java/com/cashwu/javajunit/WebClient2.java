package com.cashwu.javajunit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author cash.wu
 * @since 2024/06/17
 */
public class WebClient2 {


    public String getContent(ConnectionFactory connectionFactory) throws IOException {

        String webContent;
        StringBuilder content = new StringBuilder();
        try(InputStream is = connectionFactory.getData()) {
            int count;
            while (-1 != (count = is.read())) {
                content.append(new String(Character.toChars(count)));
            }

            webContent = content.toString();
        }

        return webContent;
    }

    private static URLConnection createHttpURLConnection(URL url) throws IOException {
        return url.openConnection();
    }
}


