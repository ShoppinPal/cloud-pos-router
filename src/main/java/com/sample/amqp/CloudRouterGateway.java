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

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.support.RabbitGatewaySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Pulkit Singhal
 */
public class CloudRouterGateway extends RabbitGatewaySupport {

	@Autowired @Qualifier("requestQueue") private Queue requestQueue;

	public Object send(RequestDelegate requestDelegate) {
		return getRabbitTemplate().convertSendAndReceive(
				requestQueue.getName(), // for default exchange, the queue name is same as routingKey
				requestDelegate);
	}

}
