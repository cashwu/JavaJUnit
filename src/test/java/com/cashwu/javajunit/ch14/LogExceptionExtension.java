package com.cashwu.javajunit.ch14;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cash.wu
 * @since 2024/06/25
 */
public class LogExceptionExtension implements TestExecutionExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(LogExceptionExtension.class);

    @Override
    public void handleTestExecutionException(ExtensionContext context,
                                             Throwable throwable) throws Throwable {

        if (throwable instanceof RuntimeException) {
            log.warn(throwable.getMessage(), throwable);

            return;
        }

        throw throwable;
    }
}
