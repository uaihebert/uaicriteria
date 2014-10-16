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
import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class MemberOfTest extends AbstractTest {

    @Test
    public void isMemberOfWithEntityWorking() {
        if (isBatoo()) {
            return;
        }

        final RegularEntityOne regularEntityOne = new RegularEntityOne();
        regularEntityOne.setId(1);

        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("regularEntityOne", regularEntityOne);

        final String query = "select sub from RegularEntityFive sub where :regularEntityOne member of sub.regularEntityOneList";

        final List<RegularEntityFive> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFive.class, paramMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andIsMemberOf(regularEntityOne, "regularEntityOneList");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isNotMemberOfWithEntityWorking() {
        if (isEclipselink() || isBatoo()) {
            return;
        }

        final RegularEntityOne regularEntityOne = new RegularEntityOne();
        regularEntityOne.setId(1);

        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("regularEntityOne", regularEntityOne);

        final String query = "select r from RegularEntityFive r where :regularEntityOne not member of r.regularEntityOneList";

        final List<RegularEntityFive> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFive.class, paramMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andIsNotMemberOf(regularEntityOne, "regularEntityOneList");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isMemberOfWithStringWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityFive r where 'VALUE_01' member of r.stringList";

        final List<RegularEntityFive> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFive.class);
        assertTrue(resultFromJPQL.size() > 0);


        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andIsMemberOf("VALUE_01", "stringList");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isNotMemberOfWithStringWorking() {
        if (isEclipselink() || isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityFive r where 'VALUE_01' not member of r.stringList";

        final List<RegularEntityFive> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFive.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andIsNotMemberOf("VALUE_01", "stringList");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }
}
