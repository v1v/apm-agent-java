/*
 * Licensed to Elasticsearch B.V. under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch B.V. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package co.elastic.apm.agent.servlet.helper;

import co.elastic.apm.agent.impl.transaction.TextHeaderGetter;

import javax.annotation.Nullable;
import java.util.Enumeration;

public abstract class CommonServletRequestHeaderGetter<T> implements TextHeaderGetter<T> {
    @Nullable
    @Override
    public String getFirstHeader(String headerName, T carrier) {
        return getHeader(headerName, carrier);
    }

    abstract String getHeader(String headerName, T carrier);

    @Override
    public <S> void forEach(String headerName, T carrier, S state, HeaderConsumer<String, S> consumer) {
        Enumeration<String> headers = getHeaders(headerName, carrier);
        while (headers.hasMoreElements()) {
            consumer.accept(headers.nextElement(), state);
        }
    }

    abstract Enumeration<String> getHeaders(String headerName, T carrier);

}
