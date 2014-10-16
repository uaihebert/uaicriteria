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
import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class MemberOfSubQueryTest extends AbstractTest {

    @Test
    public void isMemberOfWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final RegularEntityOne regularEntityOne = new RegularEntityOne();
        regularEntityOne.setId(1);

        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("regularEntityOne", regularEntityOne);

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityFive sub where :regularEntityOne member of sub.regularEntityOneList)";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class, paramMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityFive> subQuery = uaiCriteria.subQuery("id", RegularEntityFive.class);
        subQuery.andIsMemberOf(regularEntityOne, "regularEntityOneList");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isNotMemberOfWorking() {
        if (isBatoo() || isOpenJPA() || isEclipselink()) {
            // todo log this warning
            // with openJpa there is a diference in the executed SQL when we do the not member of in a subQuery
            // from a JPQL with member of outside a subQuery
            return;
        }

        final RegularEntityOne regularEntityOne = new RegularEntityOne();
        regularEntityOne.setId(1);

        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("regularEntityOne", regularEntityOne);

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityFive sub where :regularEntityOne not member of sub.regularEntityOneList)";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class, paramMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityFive> subQuery = uaiCriteria.subQuery("id", RegularEntityFive.class);
        subQuery.andIsNotMemberOf(regularEntityOne, "regularEntityOneList");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }
}