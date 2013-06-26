/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample.amqp;

import static java.lang.System.getenv;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Pulkit Singhal
 */
@Configuration
public class RabbitConfiguration {

	@Value("${default.exchange.request.queue.name}")
	private String requestQueueName;

	@Value("${default.exchange.response.queue.name}")
	private String responseQueueName;

	@Bean
    public ConnectionFactory connectionFactory() {
        final URI ampqUrl;
        try {
            ampqUrl = new URI(getEnvOrThrow("CLOUDAMQP_URL"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        final CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUsername(ampqUrl.getUserInfo().split(":")[0]);
        factory.setPassword(ampqUrl.getUserInfo().split(":")[1]);
        factory.setHost(ampqUrl.getHost());
        factory.setPort(ampqUrl.getPort());
        factory.setVirtualHost(ampqUrl.getPath().substring(1));
        factory.setPublisherReturns(true);

        return factory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean(name="rabbitTemplate")
    public RabbitTemplate rabbitTemplate() {
    	RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
    	rabbitTemplate.setMessageConverter(jsonMessageConverter());
    	rabbitTemplate.setReplyQueue(responseQueue());
    	rabbitTemplate.setReplyTimeout(60000);
    	return rabbitTemplate;
    }

    /**
     * http://static.springsource.org/spring-amqp/docs/1.2.x/reference/html/amqp.html#d4e383
     * Two implementations are available:
     * a) JsonMessageConverter which uses the org.codehaus.jackson 1.x library and
     * b) Jackson2JsonMessageConverter which uses the com.fasterxml.jackson 2.x library.
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
    	return new JsonMessageConverter();
    }

    /**
     * Let's assume that the queue name is the same as the routing_key  
     */
    @Bean
    public Queue requestQueue() {
        return new Queue(requestQueueName);
    }

    @Bean
    public Queue responseQueue() {
        return new Queue(responseQueueName);
    }

    @Bean
    public CloudRouterGateway cloudRouterGateway() {
    	CloudRouterGateway gateway = new CloudRouterGateway();
        gateway.setRabbitTemplate(rabbitTemplate());
        return gateway;
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConcurrentConsumers(5);
        container.setConnectionFactory(connectionFactory());
        container.setQueues(responseQueue());
        container.setMessageListener(rabbitTemplate());
        return container;
    }

    private static String getEnvOrThrow(String name) {
        final String env = getenv(name);
        if (env == null) {
            throw new IllegalStateException("Environment variable [" + name + "] is not set.");
        }
        return env;
    }

}
