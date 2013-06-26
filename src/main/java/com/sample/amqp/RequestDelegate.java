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

import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author Pulkit Singhal
 */
public class RequestDelegate implements Serializable {

	private static final long serialVersionUID = 4L;

	private String requestPath;
	private String requestMethod;
	private MultivaluedMapImpl requestQueryParams;
	private MultivaluedMapImpl requestHeaders;
	private String requestPayload;
	private String requestMimeType;
	private boolean base64Encoded;

	public RequestDelegate() {
    }

	public RequestDelegate(
			MultivaluedMapImpl requestHeaders,
			String requestPath,
			String requestMethod)
	{
		this(requestHeaders,requestPath,requestMethod,null,null,null,false);
	}

	public RequestDelegate(
			MultivaluedMapImpl requestHeaders,
			String requestPath,
			String requestMethod,
			MultivaluedMapImpl requestQueryParams,
			String requestPayload,
			String requestMimeType)
	{
		this(requestHeaders,requestPath,requestMethod,requestQueryParams,requestPayload,requestMimeType,false);
	}

	public RequestDelegate(
			MultivaluedMapImpl requestHeaders,
			String requestPath,
			String requestMethod,
			MultivaluedMapImpl requestQueryParams,
			String requestPayload,
			String requestMimeType,
			boolean base64Encoded)
	{
		super();
		this.requestPath = requestPath;
		this.requestMethod = requestMethod;
		this.requestQueryParams = requestQueryParams;
		this.requestHeaders = requestHeaders;
		this.requestPayload = requestPayload;
		this.requestMimeType = requestMimeType;
		this.base64Encoded = base64Encoded;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public MultivaluedMapImpl getRequestQueryParams() {
		if (requestQueryParams == null) {
			requestQueryParams = new MultivaluedMapImpl();
		}
		return requestQueryParams;
	}

	public void setRequestQueryParams(MultivaluedMapImpl requestQueryParams) {
		this.requestQueryParams = requestQueryParams;
	}

	public MultivaluedMapImpl getRequestHeaders() {
		if (requestHeaders == null) {
			requestHeaders = new MultivaluedMapImpl();
		}
		return requestHeaders;
	}

	public void setRequestHeaders(MultivaluedMapImpl requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public String getRequestPayload() {
		return requestPayload;
	}

	public void setRequestPayload(String requestPayload) {
		this.requestPayload = requestPayload;
	}

	public String getRequestMimeType() {
		return requestMimeType;
	}

	public void setRequestMimeType(String requestMimeType) {
		this.requestMimeType = requestMimeType;
	}

	public boolean isBase64Encoded() {
		return base64Encoded;
	}

	public void setBase64Encoded(boolean base64Encoded) {
		this.base64Encoded = base64Encoded;
	}

	@Override
	public String toString() {
		return "RequestDelegate [" + "\n\t"
				+ "requestPath=" + requestPath + "\n\t"
				+ "requestMethod=" + requestMethod + "\n\t"
				+ "requestQueryParams=" + requestQueryParams + "\n\t"
				+ "requestHeaders=" + requestHeaders + "\n\t"
				+ "requestPayload=" + requestPayload + "\n\t"
				+ "requestMimeType=" + requestMimeType + "\n\t"
				+ "base64Encoded=" + base64Encoded + "\n"
				+ "]";
	}
}
