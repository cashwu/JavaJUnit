package com.cashwu.javajunit.ch14;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.IOException;
import java.util.Properties;

/**
 * @author cash.wu
 * @since 2024/06/25
 */
public class ExecutionContextExtension implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {

        Properties properties = new Properties();
        String executionContext;
        try {
            properties.load(ExecutionContextExtension.class.getClassLoader().getResourceAsStream(
                    "context.properties"));
            executionContext = properties.getProperty("context");

            if (!"regular".equals(executionContext) && !"low".equals(executionContext)) {
                return ConditionEvaluationResult.disabled(
                        "test disabled outside regular and low contexts");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ConditionEvaluationResult.enabled(
                "test enabled oin the " + executionContext + " context");
    }
}
