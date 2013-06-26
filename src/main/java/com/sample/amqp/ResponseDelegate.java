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

import java.io.Serializable;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.header.InBoundHeaders;

/**
 * @author Pulkit Singhal
 */
public class ResponseDelegate implements Serializable {

	private static final long serialVersionUID = 1L;

	private int responseStatus;
	private InBoundHeaders responseHeaders;
	private String responsePayload;

	public ResponseDelegate() {
    }

	public ResponseDelegate(
			int responseStatus,
			InBoundHeaders responseHeaders,
			String responsePayload) {
		super();
		this.responseStatus = responseStatus;
		this.responseHeaders = responseHeaders;
		this.responsePayload = responsePayload;
	}

	public ResponseDelegate(ClientResponse clientResponse) {
		this(clientResponse.getStatus(),
			(InBoundHeaders) clientResponse.getHeaders(),
			clientResponse.getEntity(String.class));
    }

	public int getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}

	public InBoundHeaders getResponseHeaders() {
		if (responseHeaders==null) {
			responseHeaders = new InBoundHeaders();
		}
		return responseHeaders;
	}

	public void setResponseHeaders(InBoundHeaders responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public String getResponsePayload() {
		return responsePayload;
	}

	public void setResponsePayload(String responsePayload) {
		this.responsePayload = responsePayload;
	}

	@Override
	public String toString() {
		return "ResponseDelegate [" + "\n\t"
				+ "responseStatus=" + responseStatus + "\n\t"
				+ "responseHeaders=" + responseHeaders + "\n\t"
				+ "responsePayload=" + responsePayload + "\n"
				+ "]";
	}
}
