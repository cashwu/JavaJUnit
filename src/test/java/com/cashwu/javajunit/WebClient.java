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
public class WebClient {

    public String getContent(URL url) throws IOException {
        URLConnection conn = url.openConnection();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

        String line;
        StringBuilder content = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        return content.toString();
    }
}
