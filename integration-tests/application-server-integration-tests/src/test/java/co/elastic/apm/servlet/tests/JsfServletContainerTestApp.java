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
package co.elastic.apm.servlet.tests;

import co.elastic.apm.servlet.AbstractServletContainerIntegrationTest;

public class JsfServletContainerTestApp extends TestApp {
    public JsfServletContainerTestApp() {
        super("../jsf-app/jsf-app-standalone",
            "jsf-http-get.war",
            "jsf-http-get",
            "status.html",
            "jsf-http-get");
    }

    @Override
    public void test(AbstractServletContainerIntegrationTest test) throws Exception {
        new JsfApplicationServerTestApp().test(test);
    }
}
