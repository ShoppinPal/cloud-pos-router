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

package com.sample.router.providers;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.AnnotationIntrospector.Pair;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

@Provider
public class ConfigureJackson implements ContextResolver<ObjectMapper> {

    final ObjectMapper combinedObjectMapper;

    public ConfigureJackson() {
        combinedObjectMapper = createCombinedObjectMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return combinedObjectMapper;
    }

    private static ObjectMapper createCombinedObjectMapper() {
        Pair combinedIntrospector = createJaxbJacksonAnnotationIntrospector();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, true);
		//objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		objectMapper.disable(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS);
		//objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        objectMapper.setDeserializationConfig(objectMapper.getDeserializationConfig().withAnnotationIntrospector(combinedIntrospector));
        objectMapper.setSerializationConfig(objectMapper.getSerializationConfig().withAnnotationIntrospector(combinedIntrospector));
        return objectMapper;
    }

    private static Pair createJaxbJacksonAnnotationIntrospector() {

        AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector();
        AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();

        return new AnnotationIntrospector.Pair(jacksonIntrospector, jaxbIntrospector);
    }
}