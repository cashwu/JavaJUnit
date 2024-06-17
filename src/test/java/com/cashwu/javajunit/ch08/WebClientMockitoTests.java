package com.cashwu.javajunit.ch08;

import com.cashwu.javajunit.ConnectionFactory;
import com.cashwu.javajunit.WebClient2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

/**
 * @author cash.wu
 * @since 2024/06/17
 */
@ExtendWith(MockitoExtension.class)
public class WebClientMockitoTests {

    @Mock
    private ConnectionFactory factory;

    @Mock
    private InputStream mockStream;

    @Test
    void getContent() throws IOException {

        when(factory.getData()).thenReturn(mockStream);
        when(mockStream.read()).thenReturn((int) 'w').thenReturn((int) 'o').thenReturn((int) 'r')
                               .thenReturn((int) 'k').thenReturn((int) 's').thenReturn(-1);

        WebClient2 webClient2 = new WebClient2();

        String content = webClient2.getContent(factory);

        assertEquals("works", content);
    }

    @Test
    void getContentCannotClose() throws IOException {

//        when(factory.getData()).thenReturn(mockStream);
//        when(mockStream.read()).thenReturn(-1);
//
//        doThrow(new IOException("cannot close"))
//                .when(mockStream).close();
//
//        WebClient2 webClient2 = new WebClient2();
//        String content = webClient2.getContent(factory);
//
//        assertNull(content);
    }


}
