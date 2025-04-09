package com.devanand.lockersystem.locker.config;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;

import com.devanand.lockersystem.locker.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

/*  @Bean
  public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory redisConnectionFactory, ObjectMapper objectMapper) {
    RedisTemplate<String, User> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    template.setKeySerializer(new StringRedisSerializer());

    ObjectMapper cacheObjectMapper = objectMapper.copy();
    cacheObjectMapper.activateDefaultTyping(cacheObjectMapper.getPolymorphicTypeValidator(),
        ObjectMapper.DefaultTyping.NON_FINAL,
        PROPERTY);
    Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<>( cacheObjectMapper,User.class);


    template.setValueSerializer(serializer);
    return template;
  }*/

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);

    // Use Jackson JSON serializer for values
    template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());

    // Use StringRedisSerializer for keys
    template.setKeySerializer(new StringRedisSerializer());

    // Use Jackson JSON serializer for hash values
    template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

    return template;
  }
}