package com.cashwu.javajunit.ch15.htmlunit;

import org.htmlunit.BrowserVersion;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlListItem;
import org.htmlunit.html.HtmlPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cash.wu
 * @since 2024/06/26
 */
public class JavaDocPageAllBrowserTests {

    private static List<BrowserVersion[]> getBrowserVersions() {
        return Arrays.asList(new BrowserVersion[][] {{BrowserVersion.FIREFOX},
                                                     {BrowserVersion.CHROME},
                                                     {BrowserVersion.EDGE},
                                                     {BrowserVersion.BEST_SUPPORTED}});
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    void classNav(BrowserVersion browserVersion) throws IOException {

        System.out.println("browserVersion - " + browserVersion.getNickname());

        WebClient webClient = new WebClient(browserVersion);

        HtmlPage page = webClient.getPage("https://htmlunit.sourceforge.io/apidocs/index.html");

        HtmlPage packageFrame = (HtmlPage) page.getFrameByName("packageFrame").getEnclosedPage();

        HtmlListItem htmlListItem = (HtmlListItem) packageFrame.getElementsByTagName("li").item(0);

        assertThat(htmlListItem.getTextContent()).isEqualTo("AboutURLConnection");

    }
}
