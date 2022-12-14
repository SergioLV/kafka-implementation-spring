package com.gremio.receiver.config;

import com.gremio.receiver.model.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kaka.bootstrap-server}")
    private String bootstrapServers;

    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Autowired
    public ProducerFactory<String, Sale> salesProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Autowired
    public ProducerFactory<String, CartStock> stockProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Autowired
    public ProducerFactory<String, Member> membersProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }
    @Autowired
    public ProducerFactory<String, FugitiveCart> fugitiveCartsProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Autowired
    public ProducerFactory<String, CartLocation> standardCartProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, Sale> salesKafkaTemplate(ProducerFactory<String, Sale> salesProducerFactory) {
        return new KafkaTemplate<>(salesProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, CartStock> stockKafkaTemplate(ProducerFactory<String, CartStock> stockProducerFactory) {
        return new KafkaTemplate<>(stockProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, Member> membersKafkaTemplate(ProducerFactory<String, Member> membersProducerFactory) {
        return new KafkaTemplate<>(membersProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, FugitiveCart> fugitiveCartsKafkaTemplate(ProducerFactory<String, FugitiveCart> fugitiveCartsProducerFactory) {
        return new KafkaTemplate<>(fugitiveCartsProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, CartLocation> standardCartKafkaTemplate(ProducerFactory<String, CartLocation> standardCartProducerFactory) {
        return new KafkaTemplate<>(standardCartProducerFactory());
    }
}
