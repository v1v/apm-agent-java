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
package co.elastic.apm.agent.servlet;

import net.bytebuddy.asm.Advice;

import javax.annotation.Nullable;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;

public abstract class JavaxServletVersionInstrumentation extends ServletVersionInstrumentation {

    public static class JavaxInit extends Init {

        public static class AdviceClass {
            @Advice.OnMethodEnter(suppress = Throwable.class, inline = false)
            @SuppressWarnings("Duplicates") // duplication is fine here as it allows to inline code
            public static void onEnter(@Advice.Argument(0) @Nullable ServletConfig servletConfig) {
                logServletVersion(JavaxUtil.getInfoFromServletContext(servletConfig));
            }
        }

        @Override
        public String servletVersionTypeMatcherClassName() {
            return getServletVersionTypeMatcherClassName();
        }

        @Override
        public String rootClassNameThatClassloaderCanLoad() {
            return getRootClassNameThatClassloaderCanLoad();
        }

        @Override
        String initMethodArgumentClassName() {
            return "javax.servlet.ServletConfig";
        }
    }

    public static class JavaxService extends Service {

        public static class AdviceClass {
            @Advice.OnMethodEnter(suppress = Throwable.class, inline = false)
            public static void onEnter(@Advice.This Servlet servlet) {
                logServletVersion(JavaxUtil.getInfoFromServletContext(servlet.getServletConfig()));
            }
        }

        @Override
        public String rootClassNameThatClassloaderCanLoad() {
            return getRootClassNameThatClassloaderCanLoad();
        }

        @Override
        String[] getServiceMethodArgumentClassNames() {
            return new String[]{"javax.servlet.ServletRequest", "javax.servlet.ServletResponse"};
        }

        @Override
        public String servletVersionTypeMatcherClassName() {
            return getServletVersionTypeMatcherClassName();
        }
    }

    private static String getServletVersionTypeMatcherClassName() {
        return "javax.servlet.Servlet";
    }

    private static String getRootClassNameThatClassloaderCanLoad() {
        return "javax.servlet.AsyncContext";
    }
}
