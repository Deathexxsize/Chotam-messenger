package com.deathexxsize.TheTwitterKiller.config;

import com.deathexxsize.TheTwitterKiller.dto.authDTOs.VerificationData;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, VerificationData> verificationRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, VerificationData> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Настройка ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        // Сериализатор значений с ObjectMapper в конструкторе
        Jackson2JsonRedisSerializer<VerificationData> serializer =
                new Jackson2JsonRedisSerializer<>(mapper, VerificationData.class);

        // Ключи как строки
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Для хэшей
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisTemplate<Integer, User> userJsonRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Integer, User> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // int -> string
        template.setKeySerializer(new GenericToStringSerializer(Integer.class));

        // user -> JSON
        Jackson2JsonRedisSerializer<User> valueSerializer = new Jackson2JsonRedisSerializer<>(User.class);
        template.setValueSerializer(valueSerializer);

        template.afterPropertiesSet();
        return template;
    }


}
