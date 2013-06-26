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

package com.sample.router.resources;

/**
 *   This class publishes a RequestDelegate object in JSON format to RabbitMQ.
 *   A intranet-pos-agent which is subscribed to a rquest queue, picks it up
 *   and posts back a reply, which is then read by this class.
 * 
 * @author pulkitsinghal
 */

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sample.amqp.CloudRouterGateway;
import com.sample.amqp.RequestDelegate;
import com.sample.amqp.ResponseDelegate;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author Pulkit Singhal
 */
@Path("/amqp")
@Component
public class CloudPosRouter {

	@Value("${example.path}")
	private String examplePath;

	@Value("${example.method}")
	private String exampleMethod;

	// load RabbitMQ configuration provided by Spring
    @Autowired private AmqpTemplate amqpTemplate;
    @Autowired @Qualifier("requestQueue") private Queue requestQueue;
    @Autowired private CloudRouterGateway cloudRouterGateway;

	@Context
    private HttpServletRequest request;

	@GET
	@Path("/example")
	public String delegateRequestExample(@Context UriInfo allUri)
	{
		RequestDelegate requestDelegate = new RequestDelegate(
				(MultivaluedMapImpl) allUri.getQueryParameters(),
				examplePath,
				exampleMethod);
		ResponseDelegate response = cloudRouterGateway.send(requestDelegate);
		return response.getResponsePayload();
	}
}