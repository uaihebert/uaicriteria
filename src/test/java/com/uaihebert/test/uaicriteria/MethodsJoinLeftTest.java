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

import com.uaihebert.model.test.RegularEntityFour;
import com.uaihebert.model.test.RegularEntityThree;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodsJoinLeftTest extends AbstractTest {
    private static final String VALUE_STRING_01 = "Just a String 01";

    @Test
    public void isMultipleJoin1LevelWorking() {
        final String query = "select t2 from RegularEntityTwo t2 left join t2.regularEntityOne t1  where t1.stringAttribute = '" + VALUE_STRING_01 + "'";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.leftJoin("regularEntityOne");
        uaiCriteria.andEquals("regularEntityOne.stringAttribute", VALUE_STRING_01);

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoin2LevelWorking() {
        final String query = "select t3 " +
                "from RegularEntityThree t3 " +
                "left join t3.regularEntityTwo t2 " +
                "left join t2.regularEntityOne t1 " +
                " where t1.stringAttribute = '" + VALUE_STRING_01 + "'";

        final List<RegularEntityThree> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityThree.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityThree> uaiCriteria = createCriteria(RegularEntityThree.class);
        uaiCriteria.leftJoin("regularEntityTwo.regularEntityOne");
        uaiCriteria.andEquals("regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_01);

        final List<RegularEntityThree> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoin3LevelWorking() {
        final String query = "select t4 " +
                "from RegularEntityFour t4 " +
                "left join t4.regularEntityThree t3 " +
                "left join t3.regularEntityTwo t2 " +
                "left join t2.regularEntityOne t1 " +
                " where t1.stringAttribute = '" + VALUE_STRING_01 + "'";

        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.leftJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andEquals("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_01);

        final List<RegularEntityFour> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }
}