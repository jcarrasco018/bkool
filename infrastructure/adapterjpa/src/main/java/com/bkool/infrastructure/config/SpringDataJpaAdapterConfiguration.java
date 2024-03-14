package com.bkool.infrastructure.config;


import com.bkool.domain.persistence.BikePersistencePort;
import com.bkool.infrastructure.ports.BikePersistenceAdapter;
import com.bkool.infrastructure.repositories.BikeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDataJpaAdapterConfiguration {


    @Bean
    public BikePersistencePort getBikePersistencePort(BikeRepository bikeRepository) {
        return new BikePersistenceAdapter(bikeRepository);
    }
}
