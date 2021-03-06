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
package com.github.aistomin.xml;

/**
 * XML string.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class XmlString implements Xml {

    /**
     * XML string content.
     */
    private final transient String string;

    /**
     * Ctor.
     * @param xml XML string content.
     */
    public XmlString(final String xml) {
        this.string = xml;
    }

    @Override
    public String content() throws Exception {
        return this.string;
    }

    @Override
    public String field(final String xpath) throws Exception {
        return new XPath(xpath).valueFrom(this.content()).trim();
    }
}
