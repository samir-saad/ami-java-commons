package org.ssaad.ami.common.metrics.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MicroMeterConfig implements MeterRegistryCustomizer {
    @Autowired
    ConfigurableApplicationContext appContext;

    // Default tags
    @Value("${spring.application.name:unknown}")
    private String applicationName;

    @Value("${spring.profiles.active:unknown}")
    private String applicationProfile;

    @Value("${NAMESPACE:unknown}")
    private String namespace;

    @Value("${HOSTNAME:unknown}")
    private String hostname;

    // Custom tags
    // application.metrics.custom-tags={'KEY1': 'value1', 'KEY2': 'value3', 'KEY3': 'value5'}
    @Value("#{${application.metrics.custom-tags}}")
    private Map<String, String> customTags;

    @Override
    public void customize(MeterRegistry registry) {

        // Add default tags
        registry.config().commonTags("app", applicationName);
        registry.config().commonTags("profile", applicationProfile);
        registry.config().commonTags("namespace", namespace);
        registry.config().commonTags("hostname", hostname);
        // For kubernetes
        registry.config().commonTags("pod", hostname);

        // Add custom tags
        if (customTags != null) {
            customTags.forEach(
                    (key, value) -> registry.config().commonTags(key, value)
            );
        }
    }
}
