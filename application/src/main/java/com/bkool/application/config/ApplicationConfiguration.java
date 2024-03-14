package com.bkool.application.config;


import com.bkool.application.adapter.BikeServiceAdapter;
import com.bkool.application.api.BikeService;
import com.bkool.domain.persistence.BikePersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public BikeService getBikeService(BikePersistencePort bikePersistencePort){
        return new BikeServiceAdapter(bikePersistencePort);
    }
}
