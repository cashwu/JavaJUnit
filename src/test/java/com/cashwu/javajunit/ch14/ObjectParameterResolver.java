package com.cashwu.javajunit.ch14;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * @author cash.wu
 * @since 2024/06/25
 */
public class ObjectParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws
                                                                        ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(Person.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws
                                                                      ParameterResolutionException {
        Person person = new Person();
        person.setName("cash");
        return person;
    }
}
