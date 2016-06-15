package com.sahilm.support;

import org.springframework.test.context.ActiveProfilesResolver;
import org.springframework.test.context.support.DefaultActiveProfilesResolver;

public class TwitterServiceActiveProfileResolver implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> testClass) {
        final String systemSpringProfile = System.getProperty("spring.profiles.active");
        if (systemSpringProfile != null) {
            return new String[]{systemSpringProfile};
        }
        return new DefaultActiveProfilesResolver().resolve(testClass);
    }
}
