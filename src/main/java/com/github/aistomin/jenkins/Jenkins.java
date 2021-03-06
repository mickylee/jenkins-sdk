/**
 * Copyright (c) 2016, Istomin Andrei
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
package com.github.aistomin.jenkins;

/**
 * Jenkins instance.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Jenkins extends ApiObject {

    /**
     * All the jobs of this Jenkins instance.
     *
     * @return Jobs.
     * @throws Exception If reading jobs was not successful.
     */
    Jobs jobs() throws Exception;

    /**
     * All the registered Jenkins' users.
     *
     * @return Users.
     * @throws Exception If reading users was not successful.
     */
    Users users() throws Exception;

    /**
     * Jenkins' version.
     *
     * @return Version.
     * @throws Exception If reading users was not successful.
     */
    String version() throws Exception;

    /**
     * Restart Jenkins.
     * @throws Exception If reading users was not successful.
     */
    void restart() throws Exception;
}
