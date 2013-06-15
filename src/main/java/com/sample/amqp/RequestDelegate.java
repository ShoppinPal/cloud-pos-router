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

	private static final long serialVersionUID = 2L;

	private String requestPath;
	private String requestMethod;
	private MultivaluedMapImpl requestQueryParamsMultivaluedMap;
	private String requestPayload;
	private String requestMimeType;

	public RequestDelegate() {
    }

	public RequestDelegate(String requestPath, String requestMethod,
			MultivaluedMapImpl requestQueryParamsMultivaluedMap,
			String requestPayload, String requestMimeType) {
		super();
		this.requestPath = requestPath;
		this.requestMethod = requestMethod;
		this.requestQueryParamsMultivaluedMap = requestQueryParamsMultivaluedMap;
		this.requestPayload = requestPayload;
		this.requestMimeType = requestMimeType;
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

	public MultivaluedMapImpl getRequestQueryParamsMultivaluedMap() {
		if (requestQueryParamsMultivaluedMap==null) {
			requestQueryParamsMultivaluedMap =
					new MultivaluedMapImpl();
		}
		return requestQueryParamsMultivaluedMap;
	}
	public void setRequestQueryParamsMultivaluedMap(
			MultivaluedMapImpl requestQueryParamsMultivaluedMap) {
		this.requestQueryParamsMultivaluedMap = requestQueryParamsMultivaluedMap;
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

	@Override
	public String toString() {
		return "RequestDelegate [requestPath=" + requestPath
				+ ",\n\n requestMethod=" + requestMethod
				+ ",\n\n requestQueryParamsMultivaluedMap=" + requestQueryParamsMultivaluedMap
				+ ",\n\n requestPayload=" + requestPayload
				+ ",\n\n requestMimeType=" + requestMimeType + "]";
	}
}
