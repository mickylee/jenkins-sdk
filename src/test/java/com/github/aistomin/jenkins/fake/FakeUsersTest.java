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
package com.github.aistomin.jenkins.fake;

import com.github.aistomin.jenkins.User;
import com.github.aistomin.jenkins.Users;
import com.github.aistomin.xml.XmlString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 * Test for FakeJobs class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeUsersTest {

    /**
     * Can create FakeUsers with default constructor.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanConstruct() throws Exception {
        final Users users = new FakeUsers();
        MatcherAssert.assertThat(
            users.xml(), new IsInstanceOf(String.class)
        );
        MatcherAssert.assertThat(
            users.iterator(), new IsInstanceOf(Iterator.class)
        );
    }

    /**
     * Can read Jenkins' users XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXml() throws Exception {
        final String xml = new FakeUsers(
            new XmlString(
                "<people><fullName>Integration Test</fullName></people>"
            )
        ).xml();
        MatcherAssert.assertThat(
            xml.startsWith("<people>"), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.contains("<fullName>Integration Test</fullName>"),
            new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.endsWith("</people>"), new IsEqual<Boolean>(true)
        );
    }

    /**
     * Can iterate through users.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanIterate() throws Exception {
        final List<User> users = new ArrayList<User>(2);
        users.add(new FakeUser("test1"));
        users.add(new FakeUser("test2"));
        final Iterator<User> iterator = new FakeUsers(users).iterator();
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            iterator.next().username(),
            new IsEqual<String>(users.get(0).username())
        );
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            iterator.next().username(),
            new IsEqual<String>(users.get(1).username())
        );
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<Boolean>(false)
        );
    }
}
