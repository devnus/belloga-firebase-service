package com.devnus.belloga.firebase.common.config;

import com.devnus.belloga.firebase.cloudMessagingToken.dto.EventCloudMessagingToken;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String AUTO_OFFSET_RESET;
    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private boolean AUTO_COMMIT;

    private Map<String, Object> configProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); //카프카에서 받은 키 역 직렬화
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class); //카프카에서 받은 값 역 직렬화
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, AUTO_COMMIT);
        return props;
    }

    @Bean
    ConsumerFactory<String,Object> consumerFactory(){
        JsonDeserializer<Object> deserializer = new JsonDeserializer<>(Object.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(configProps(), new StringDeserializer(), deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Object> containerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    ConsumerFactory<String, EventCloudMessagingToken.MessageBySubscribe> eventCloudMessagingTokenBySubscribeFactory(){
        JsonDeserializer<EventCloudMessagingToken.MessageBySubscribe> deserializer = new JsonDeserializer<>(EventCloudMessagingToken.MessageBySubscribe.class);

        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(configProps(), new StringDeserializer(), deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, EventCloudMessagingToken.MessageBySubscribe> eventCloudMessagingTokenBySubscribeListener(){
        ConcurrentKafkaListenerContainerFactory<String, EventCloudMessagingToken.MessageBySubscribe> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(eventCloudMessagingTokenBySubscribeFactory());
        return factory;
    }

    @Bean
    ConsumerFactory<String, EventCloudMessagingToken.MessageByToken> eventCloudMessagingTokenByTokenFactory(){
        JsonDeserializer<EventCloudMessagingToken.MessageByToken> deserializer = new JsonDeserializer<>(EventCloudMessagingToken.MessageByToken.class);

        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(configProps(), new StringDeserializer(), deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, EventCloudMessagingToken.MessageByToken> eventCloudMessagingTokenByTokenListener(){
        ConcurrentKafkaListenerContainerFactory<String, EventCloudMessagingToken.MessageByToken> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(eventCloudMessagingTokenByTokenFactory());
        return factory;
    }
}
