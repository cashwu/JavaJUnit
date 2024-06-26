package com.cashwu.javajunit.ch15;

import org.htmlunit.CollectingAlertHandler;
import org.htmlunit.MockWebConnection;
import org.htmlunit.Page;
import org.htmlunit.WebAssert;
import org.htmlunit.html.Html;
import org.htmlunit.html.HtmlForm;
import org.htmlunit.html.HtmlInput;
import org.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cash.wu
 * @since 2024/06/26
 */
public class FormTest extends ManagedWebClient {

    @Test
    void form() throws IOException {

        CollectingAlertHandler alertHandler = new CollectingAlertHandler();
        webClient.setAlertHandler(alertHandler);

        HtmlPage page = webClient.getPage("file:src/main/formtest.html");

        HtmlForm form = page.getFormByName("validated_form");

        HtmlInput input = form.getInputByName("in_text");
        input.setValueAttribute("typing ...");

        HtmlInput submitBtn = form.getInputByName("submit");

        HtmlPage resultPage = submitBtn.click();
        WebAssert.assertTitleEquals(resultPage, "Result");

        assertThat(alertHandler.getCollectedAlerts().isEmpty()).isTrue();
        assertThat(alertHandler.getCollectedAlerts()).isEmpty();
    }

    @Test
    void formAlert() throws IOException {

        CollectingAlertHandler alertHandler = new CollectingAlertHandler();
        webClient.setAlertHandler(alertHandler);

        HtmlPage page = webClient.getPage("file:src/main/formtest.html");
        HtmlForm form = page.getFormByName("validated_form");
        HtmlInput submitBtn = form.getInputByName("submit");

        HtmlPage resultPage = submitBtn.click();
        WebAssert.assertTitleEquals(resultPage, page.getTitleText());
        WebAssert.assertTextPresent(resultPage, page.asNormalizedText());

        List<String> collectedAlerts = alertHandler.getCollectedAlerts();

        List<String> expectedAlert = Collections.singletonList("Please enter a value.");

        assertThat(collectedAlerts).isEqualTo(expectedAlert);
    }

    @Test
    void windowConfirmTests() throws IOException {

        var html = "<html><head><title>hello</title></head>"
                + "<body onload='confirm(\"confirm message.\")'></body></html>";

        var url = new URL("http://page1/");

        MockWebConnection connection = new MockWebConnection();
        connection.setResponse(url, html);

        final ArrayList<String> confirmMessages = new ArrayList<>();

        webClient.setConfirmHandler((page, message) -> {
            confirmMessages.add(message);
            return true;
        });

        webClient.setWebConnection(connection);

        HtmlPage page = webClient.getPage(url);

        WebAssert.assertTitleEquals(page, "hello");

        assertThat(confirmMessages.toArray()).isEqualTo(new String[] {"confirm message."});
    }

    @Test
    void windowConfirmJsTests() throws IOException {

        //language=HTML
        String html = """
                <html lang="tw"><head><title>hello</title>
                <script>function go() {
                    alert(confirm('confirm message'));
                }
                </script></head>
                <body onload='go()'></body></html>""";

        var url = new URL("http://page1/");

        MockWebConnection connection = new MockWebConnection();
        connection.setResponse(url, html);

        final ArrayList<String> confirmMessages = new ArrayList<>();

        webClient.setAlertHandler(new CollectingAlertHandler());
        webClient.setConfirmHandler((page, message) -> {
            confirmMessages.add(message);
            return true;
        });

        webClient.setWebConnection(connection);

        HtmlPage page = webClient.getPage(url);
        WebAssert.assertTitleEquals(page, "hello");

        assertThat(confirmMessages.toArray()).isEqualTo(new String[] {"confirm message"});

        assertThat(((CollectingAlertHandler) webClient.getAlertHandler()).getCollectedAlerts()
                                                                         .toArray()).isEqualTo(
                new String[] {"true"});

    }

}
