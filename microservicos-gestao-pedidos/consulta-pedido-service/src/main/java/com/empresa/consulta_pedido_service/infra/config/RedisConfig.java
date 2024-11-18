package com.empresa.consulta_pedido_service.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

    @Autowired
    private Environment env;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(this.env.getProperty("redis-host"));
        redisConfig.setPort(Integer.valueOf(this.env.getProperty("redis-port")));
        redisConfig.setPassword(RedisPassword.of(this.env.getProperty("redis-password"))); // Substitua pela sua senha

        return new LettuceConnectionFactory(redisConfig);
    }
}
