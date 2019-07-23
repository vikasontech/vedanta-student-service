/*
 * Copyright (C) 2019  Vikas Kumar Verma
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.vedanta.vidiyalay.student_service.message.broker.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.List;

@Configuration
@Slf4j
public class RedisConfiguration {

    @Bean
    PatternTopic studentTopic() {
        return new PatternTopic(PatternTopics.STUDENT_CREATED.name());
    }

    @Bean
    PatternTopic emailTopic() {
        return new PatternTopic(PatternTopics.EMAIL.name());
    }

    @Bean
    RedisTemplate<String, Object> objectRedisTemplate(RedisConnectionFactory connectionFactory) {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        final Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}

//@Configuration
class RedisListenerConfig {

    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory redisConnectionFactory,
            List<MessageListenerAdapter> messageListenerAdapters,
            List<PatternTopic> patternTopics) {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        final Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        messageListenerAdapters.forEach(e -> {
            e.setSerializer(serializer);
            container.addMessageListener(e, patternTopics);
        });
        container.afterPropertiesSet();
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MessageListener receiver) {
        return new MessageListenerAdapter(receiver);
    }

}
