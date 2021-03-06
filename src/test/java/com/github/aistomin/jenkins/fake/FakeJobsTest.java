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

import com.github.aistomin.jenkins.Job;
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
public final class FakeJobsTest {

    /**
     * Can create fake jobs using fake XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithXml() throws Exception {
        final String xml = "<jobs></jobs>";
        MatcherAssert.assertThat(
            new FakeJobs(new XmlString(xml)).xml(), new IsEqual<>(xml)
        );
    }

    /**
     * Can create fake jobs using fake list of jobs.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithJobs() throws Exception {
        final List<Job> expected = new ArrayList<>(2);
        expected.add(new FakeJob());
        expected.add(new FakeJob());
        final Iterator<Job> got = new FakeJobs(expected).iterator();
        MatcherAssert.assertThat(got.next(), new IsEqual<>(expected.get(0)));
        MatcherAssert.assertThat(got.next(), new IsEqual<>(expected.get(1)));
        MatcherAssert.assertThat(got.hasNext(), new IsEqual<>(false));
    }

    /**
     * Can read fake jobs' XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXml() throws Exception {
        final String xml = new FakeJobs().xml();
        MatcherAssert.assertThat(
            xml.contains(
                "<displayName>test-different-builds-job</displayName>"
            ), new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            xml.contains("<displayName>test-disabled-job</displayName>"),
            new IsEqual<>(true)
        );
    }

    /**
     * Can iterate through jobs.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanIterate() throws Exception {
        final Iterator<Job> iterator = new FakeJobs().iterator();
        MatcherAssert.assertThat(
            iterator.next(), new IsInstanceOf(FakeJob.class)
        );
        MatcherAssert.assertThat(
            iterator.next(), new IsInstanceOf(FakeJob.class)
        );
        MatcherAssert.assertThat(
            iterator.next(), new IsInstanceOf(FakeJob.class)
        );
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<>(false)
        );
    }

    /**
     * Can find job by name.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanFindByName() throws Exception {
        final List<Job> jobs = new ArrayList<>(2);
        jobs.add(
            new FakeJob(
                new XmlString("<job><displayName>job1</displayName></job>"),
                new DoNothing()
            )
        );
        final String name = "job2";
        jobs.add(
            new FakeJob(
                new XmlString("<job><displayName>job2</displayName></job>"),
                new DoNothing()
            )
        );
        final Iterator<Job> found = new FakeJobs(jobs).findByName(name);
        MatcherAssert.assertThat(
            found.next().name(), new IsEqual<>(name)
        );
        MatcherAssert.assertThat(found.hasNext(), new IsEqual<>(false));
    }
}
