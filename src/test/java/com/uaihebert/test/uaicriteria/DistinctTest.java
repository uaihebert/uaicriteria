/*
 * Copyright 2013 uaiHebert Solucoes em Informatica
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
 * */
package com.uaihebert.test.uaicriteria;

import com.uaihebert.model.test.RegularEntityFive;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DistinctTest extends AbstractTest {

    @Test
    public void assertThatDatabaseHasRepeatedResults() {

        if (isBatoo()) {
            // batoo is doing some kind of distinct in the query result,
            // but it is possible to find the 'distinct' clause in the created query
            return;
        }

        final String query = "select r from RegularEntityFive r left join r.regularEntityOneList t3 ";
        final String distinctQuery = "select distinct r from RegularEntityFive r left join r.regularEntityOneList t3 ";

        final List<RegularEntityFive> repeatedResultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFive.class);
        final List<RegularEntityFive> distinctResultFromJPQL = jpqlHelper.getListFromJPQL(distinctQuery, RegularEntityFive.class);

        assertNotSame(repeatedResultFromJPQL.size(), distinctResultFromJPQL.size());

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.leftJoin("regularEntityOneList");

        final List<RegularEntityFive> criteriaList = uaiCriteria.getResultList();

        assertEquals(repeatedResultFromJPQL.size(), criteriaList.size());
        assertTrue(repeatedResultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isDistinctWorking() {
        final String distinctQuery = "select distinct r from RegularEntityFive r " +
                "left join r.regularEntityOneList t3 ";
        final List<RegularEntityFive> resultFromJPQL = jpqlHelper.getListFromJPQL(distinctQuery, RegularEntityFive.class);

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.leftJoin("regularEntityOneList");
        uaiCriteria.setDistinctTrue();

        final List<RegularEntityFive> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }
}