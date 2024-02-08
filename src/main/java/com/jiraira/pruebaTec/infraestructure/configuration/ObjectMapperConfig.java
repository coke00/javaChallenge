package com.jiraira.pruebaTec.infraestructure.configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jiraira.pruebaTec.infraestructure.configuration.CustomBigDecimalSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class ObjectMapperConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        timeFormatter(mapper);
        SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimal.class, new CustomBigDecimalSerializer());
        mapper.registerModule(module);

        return mapper;
    }

    private static void timeFormatter(ObjectMapper mapper) {
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.configOverride(LocalDateTime.class).setFormat(JsonFormat.Value.forPattern("yyyy-MM-dd HH:mm:ss"));  // Define un formato personalizado.
    }

}
