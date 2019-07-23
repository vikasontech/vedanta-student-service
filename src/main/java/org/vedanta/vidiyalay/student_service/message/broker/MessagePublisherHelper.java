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

package org.vedanta.vidiyalay.student_service.message.broker;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.vedanta.vidiyalay.student_service.message.broker.config.PatternTopics;
import org.vedanta.vidiyalay.student_service.service.mock.EmailVM;
import org.vedanta.vidiyalay.student_service.web.rest.vm.StudentNewAdmissionVM;

import java.io.Serializable;

@Component
public class MessagePublisherHelper {
    private final MessagePublisher messagePublisher;

    MessagePublisherHelper(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    public void publishToStudentTopic(StudentNewAdmissionVM studentNewAdmissionVM) {
        messagePublisher.publisher(PatternTopics.STUDENT_CREATED, studentNewAdmissionVM);
    }

    public void publishToMailTopic(EmailVM emailVM) {
        messagePublisher.publisher(PatternTopics.EMAIL, emailVM);
    }
    public void setMessagePublisher(PatternTopics patternTopics, Serializable message) {
        messagePublisher.publisher(patternTopics, message);
    }
}

@Component
class MessagePublisher {
    private final RedisTemplate redisTemplate;

    MessagePublisher(@Qualifier("objectRedisTemplate") RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    void publisher(PatternTopics patternTopics, Serializable obj) {
        redisTemplate.convertAndSend(patternTopics.name(), obj);
    }
}
