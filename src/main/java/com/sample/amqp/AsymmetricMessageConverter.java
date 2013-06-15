/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sample.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author Gary Russell
 */
public class AsymmetricMessageConverter implements MessageConverter {

	private final MessageConverter inbound;

	private final MessageConverter outbound;

	private final String contentType;

	public AsymmetricMessageConverter(MessageConverter inbound,
			MessageConverter outbound, String contentType) {
		this.inbound = inbound;
		this.outbound = outbound;
		this.contentType = contentType;
	}

	@Override
	public Object fromMessage(Message message)
			throws MessageConversionException {
		return this.inbound.fromMessage(message);
	}

	@Override
	public Message toMessage(Object object, MessageProperties props)
			throws MessageConversionException {
		Message message = this.outbound.toMessage(object, props);
		//message.getMessageProperties().setContentType(this.contentType);
		return message;
	}

}