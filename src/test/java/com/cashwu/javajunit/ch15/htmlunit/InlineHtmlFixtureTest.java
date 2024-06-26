package com.cashwu.javajunit.ch15.htmlunit;

import org.htmlunit.MockWebConnection;
import org.htmlunit.WebAssert;
import org.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cash.wu
 * @since 2024/06/26
 */
public class InlineHtmlFixtureTest extends ManagedWebClient {

    @Test
    void inlineHtmlFixtureTest() throws IOException {

        final var expectedTitle = "Hello 1!";

        var html = "<html><head><title>" + expectedTitle + "</title></head></html>";

        MockWebConnection connection = new MockWebConnection();
        connection.setDefaultResponse(html);

        webClient.setWebConnection(connection);

        HtmlPage page = webClient.getPage("https://page");

        WebAssert.assertTitleEquals(page, expectedTitle);
    }

    @Test
    void inlineHtmlFixtureTests() throws IOException {
        final URL pageUrl1 = new URL("https://page1/");
        final URL pageUrl2 = new URL("https://page2/");
        final URL pageUrl3 = new URL("https://page3/");

        MockWebConnection connection = new MockWebConnection();
        connection.setResponse(pageUrl1, "<html><head><title>Hello 1!</title></head></html>");
        connection.setResponse(pageUrl2, "<html><head><title>Hello 2!</title></head></html>");
        connection.setResponse(pageUrl3, "<html><head><title>Hello 3!</title></head></html>");

        webClient.setWebConnection(connection);

        HtmlPage page1 = webClient.getPage(pageUrl1);
        WebAssert.assertTitleEquals(page1, "Hello 1!");

        HtmlPage page2 = webClient.getPage(pageUrl2);
        WebAssert.assertTitleEquals(page2, "Hello 2!");

        HtmlPage page3 = webClient.getPage(pageUrl3);
        WebAssert.assertTitleEquals(page3, "Hello 3!");
    }

}
