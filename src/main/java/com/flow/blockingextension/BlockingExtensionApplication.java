package com.flow.blockingextension;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableCaching
@SpringBootApplication
public class BlockingExtensionApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockingExtensionApplication.class, args);
    }

}
