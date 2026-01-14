package com.fitness.activityservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic createActivityTopic(){
        return TopicBuilder.name("activity-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }

}
