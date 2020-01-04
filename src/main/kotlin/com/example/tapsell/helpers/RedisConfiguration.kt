package com.example.tapsell.helpers

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.RedisSerializationContext


@Configuration
class RedisConfiguration(
        @Value("\${spring.redis.host}")
        private val host: String,
        @Value("\${spring.redis.port}")
        private val port: Int
) {

    @Bean
    fun reactiveRedisConnectionFactory(): ReactiveRedisConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun reactiveRedisTemplate(): ReactiveRedisTemplate<String, String> {
        return ReactiveRedisTemplate(reactiveRedisConnectionFactory(), RedisSerializationContext.string())
    }

}