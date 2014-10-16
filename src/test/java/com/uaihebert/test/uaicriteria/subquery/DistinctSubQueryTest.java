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

import com.uaihebert.model.test.RegularEntityFive;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class DistinctSubQueryTest extends AbstractTest {

    @Test
    public void isDistinctWorking() {
        if (isBatoo() || isEclipselink()) {
            // eclipse link is not doing the join here
            return;
        }

        final String distinctQuery = "select distinct r.id from RegularEntityFive r " +
                "left join r.regularEntityOneList t3 ";


        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + distinctQuery + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityFive> subQuery = uaiCriteria.subQuery("id", RegularEntityFive.class);
        subQuery.leftJoin("regularEntityOneList");
        subQuery.setDistinctTrue();

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }
}