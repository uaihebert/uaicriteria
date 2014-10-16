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
package com.uaihebert.test.uaicriteria.subquery;

import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class JoinSubQueryTest extends AbstractTest {
    private static final String VALUE_STRING_01 = "Just a String 01";

    @Test
    public void isInnerJoinWorking() {
        if (isBatoo()) {
            return;
        }

        final String subQueryJPQL = "select t2.id from RegularEntityTwo t2 inner join t2.regularEntityOne t1 where t1.stringAttribute = '" + VALUE_STRING_01 + "'";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityTwo> subQuery = uaiCriteria.subQuery("id", RegularEntityTwo.class);
        subQuery.innerJoin("regularEntityOne");
        subQuery.andEquals("regularEntityOne.stringAttribute", VALUE_STRING_01);

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isLeftJoinWorking() {
        if (isBatoo()) {
            return;
        }

        final String subQueryJPQL = "select t2.id from RegularEntityTwo t2 left join t2.regularEntityOne t1 where t1.stringAttribute = '" + VALUE_STRING_01 + "'";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityTwo> subQuery = uaiCriteria.subQuery("id", RegularEntityTwo.class);
        subQuery.leftJoin("regularEntityOne");
        subQuery.andEquals("regularEntityOne.stringAttribute", VALUE_STRING_01);

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test(expected = IllegalStateException.class)
    public void isInnerFetchJoinRaisingException() {
        if (isBatoo()) {
            throw new IllegalStateException();
        }

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityTwo> subQuery = uaiCriteria.subQuery("id", RegularEntityTwo.class);
        subQuery.innerJoinFetch("regularEntityOne");
    }

    @Test(expected = IllegalStateException.class)
    public void isLeftFetchJoinRaisingException() {
        if (isBatoo()) {
            throw new IllegalStateException();
        }

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityTwo> subQuery = uaiCriteria.subQuery("id", RegularEntityTwo.class);
        subQuery.leftJoinFetch("regularEntityOne");
    }
}