package com.bench.practice.order.configuration;

import org.springframework.cloud.vault.config.SecretBackendConfigurer;
import org.springframework.cloud.vault.config.VaultConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VaultConfiguration {
    @Bean
    public VaultConfigurer configurer() {
        return new VaultConfigurer() {


            @Override
            public void addSecretBackends(SecretBackendConfigurer configurer) {
                configurer.add("secret/order-service");

                configurer.registerDefaultKeyValueSecretBackends(false);
            }
        };
    }
}
