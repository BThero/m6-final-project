package com.example.demo;

import com.example.demo.models.Currency;
import com.example.demo.models.CurrencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CurrencyRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Currency("Euro")));
            log.info("Preloading " + repository.save(new Currency("Dollar")));
            log.info("Preloading " + repository.save(new Currency("Tenge")));
        };
    }
}
