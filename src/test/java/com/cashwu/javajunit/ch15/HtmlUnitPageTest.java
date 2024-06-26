package com.cashwu.javajunit.ch15;

import org.htmlunit.Page;
import org.htmlunit.html.HtmlListItem;
import org.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author cash.wu
 * @since 2024/06/26
 */
public class HtmlUnitPageTest extends ManagedWebClient {

    @Test
    void homePage() throws IOException {

        HtmlPage page = webClient.getPage("https://htmlunit.sourceforge.io");

        assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());

        assertThat(page.getTitleText()).isEqualTo("HtmlUnit – Welcome to HtmlUnit");

        String pageAsXml = page.asXml();

        assertTrue(pageAsXml.contains("<div class=\"container-fluid\">"));

        String pageAsText = page.asNormalizedText();
        assertThat(pageAsText.contains("Support for the HTTP and HTTPS protocols")).isTrue();
    }

    @Test
    void testClassNav() throws IOException {

        HtmlPage page = webClient.getPage("https://htmlunit.sourceforge.io/apidocs/index.html");

        HtmlPage packageFrame = (HtmlPage) page.getFrameByName("packageFrame").getEnclosedPage();

        HtmlListItem htmlListItem = (HtmlListItem) packageFrame.getElementsByTagName("li").item(0);

        assertThat(htmlListItem.getTextContent()).isEqualTo("AboutURLConnection");
    }

}
