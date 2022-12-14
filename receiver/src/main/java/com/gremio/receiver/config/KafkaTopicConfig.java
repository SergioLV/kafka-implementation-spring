package com.gremio.receiver.config;

import com.gremio.receiver.business.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic salesTopic() {
        return TopicBuilder.name(Constants.KAFKA_SALES_TOPIC).partitions(2).build();
    }

    @Bean
    public NewTopic stocksTopic() {
        return TopicBuilder.name(Constants.KAFKA_STOCKS_TOPIC).partitions(2).build();
    }

    @Bean
    public NewTopic locationTopic() { return TopicBuilder.name(Constants.KAFKA_NEW_MEMBERS_TOPIC).partitions(2).build(); }

    @Bean
    public NewTopic newMembersTopic() {
        return TopicBuilder.name(Constants.KAFKA_LOCATION_TOPIC).partitions(2).build();
    }
}
