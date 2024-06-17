package com.cashwu.javajunit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author cash.wu
 * @since 2024/06/17
 */
public class WebClient1 {

    public String getContent(URL url) throws IOException {
        URLConnection conn = createHttpURLConnection(url);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

        String line;
        StringBuilder content = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        return content.toString();
    }

    private static URLConnection createHttpURLConnection(URL url) throws IOException {
        return url.openConnection();
    }
}

