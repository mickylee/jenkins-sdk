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
package com.github.aistomin.jenkins.real;

import com.github.aistomin.jenkins.JobDetails;
import com.github.aistomin.xml.Xml1;

/**
 * Jenkins job details like: display name, description etc.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealJobDetails implements JobDetails {

    /**
     * XML content of build details.
     */
    private final transient Xml1 content;

    /**
     * Ctor.
     *
     * @param xml XML content of build details.
     */
    public RealJobDetails(final Xml1 xml) {
        this.content = xml;
    }

    /**
     * Job's display name.
     *
     * @return Display name.
     * @throws Exception If error occurred.
     */
    public String displayName() throws Exception {
        return this.content.field("//job/displayName/text()");
    }

    /**
     * Job's description.
     *
     * @return Description.
     * @throws Exception If error occurred.
     */
    public String description() throws Exception {
        return this.content.field("//job/description/text()");
    }

    /**
     * Is job buildable?
     *
     * @return Is job buildable.
     * @throws Exception If error occurred.
     */
    public Boolean buildable() throws Exception {
        return Boolean.parseBoolean(
            this.content.field("//job/buildable/text()")
        );
    }
}
