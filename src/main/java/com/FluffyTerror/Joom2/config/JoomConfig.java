package com.FluffyTerror.Joom2.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JoomConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
