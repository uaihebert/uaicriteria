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
import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.test.easy_legacy.CommonTests;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommonTestsDateTestImp extends AbstractTest implements CommonTests {

    private Map<String, Object> createDateParameterMap(final String... dateArray) {
        final Map<String, Object> parameters = new HashMap<String, Object>();

        for (int i = 0; i < dateArray.length; i++) {
            final Date formattedDateOne = getFormattedDate(dateArray[i]);
            parameters.put("dateAttribute" + i, formattedDateOne);
        }

        return parameters;
    }

    @Test
    public void isAddingOneOrEquals() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010", "09/09/2009");

        final String query = "select r from RegularEntityOne r where (r.dateAttributeOne = :dateAttribute0) or (r.dateAttributeOne = :dateAttribute1)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class, parameterMap);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("dateAttributeOne", parameterMap.get("dateAttribute0")).orEquals("dateAttributeOne", parameterMap.get("dateAttribute1"));

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));

    }

    @Test
    public void isAddingOneWhereEquals() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select r from RegularEntityOne r where (r.dateAttributeOne = :dateAttribute0)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class, parameterMap);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select r from RegularEntityOne r where (r.dateAttributeOne = :dateAttribute0) and (r.id = 1)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class, parameterMap);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("dateAttributeOne", parameterMap.get("dateAttribute0")).andEquals("id", 1);

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isBetweenWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("09/09/2009", "11/10/2010");

        final String query = "select r from RegularEntityOne r where (r.dateAttributeOne between :dateAttribute0 and :dateAttribute1)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class, parameterMap);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andBetween("dateAttributeOne", parameterMap.get("dateAttribute0"), parameterMap.get("dateAttribute1"));

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select r from RegularEntityOne r where (r.dateAttributeOne >= :dateAttribute0)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class, parameterMap);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andGreaterOrEqualTo("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isLessThanWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select r from RegularEntityOne r where (r.dateAttributeOne < :dateAttribute0)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class, parameterMap);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andLessThan("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isLessOrEqualToWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select r from RegularEntityOne r where (r.dateAttributeOne <= :dateAttribute0)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class, parameterMap);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andLessOrEqualTo("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isGreaterThanWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select r from RegularEntityOne r where (r.dateAttributeOne > :dateAttribute0)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class, parameterMap);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andGreaterThan("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));

    }

    @Test
    public void isJoinBetweenWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("09/09/2009", "11/10/2010");

        final String query = "select t2 from RegularEntityTwo t2 join t2.regularEntityOne r where (r.dateAttributeOne between :dateAttribute0 and :dateAttribute1)";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class, parameterMap);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andBetween("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"), parameterMap.get("dateAttribute1"));

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select t2 from RegularEntityTwo t2 join t2.regularEntityOne r where (r.dateAttributeOne >= :dateAttribute0)";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class, parameterMap);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andGreaterOrEqualTo("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isJoinLessThanWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select t2 from RegularEntityTwo t2 join t2.regularEntityOne r where (r.dateAttributeOne < :dateAttribute0)";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class, parameterMap);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andLessThan("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select t2 from RegularEntityTwo t2 join t2.regularEntityOne r where (r.dateAttributeOne <= :dateAttribute0)";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class, parameterMap);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andLessOrEqualTo("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select t2 from RegularEntityTwo t2 join t2.regularEntityOne r where (r.dateAttributeOne > :dateAttribute0)";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class, parameterMap);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andGreaterThan("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("09/09/2009", "11/10/2010");

        final String query = "select t4 from RegularEntityFour t4 join t4.regularEntityThree t3 join t3.regularEntityTwo t2 join t2.regularEntityOne r where (r.dateAttributeOne between :dateAttribute0 and :dateAttribute1)";
        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class, parameterMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andBetween("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"), parameterMap.get("dateAttribute1"));

        final List<RegularEntityFour> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select t4 from RegularEntityFour t4 join t4.regularEntityThree t3 join t3.regularEntityTwo t2 join t2.regularEntityOne r where (r.dateAttributeOne >= :dateAttribute0)";
        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class, parameterMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andGreaterOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final List<RegularEntityFour> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select t4 from RegularEntityFour t4 join t4.regularEntityThree t3 join t3.regularEntityTwo t2 join t2.regularEntityOne r where (r.dateAttributeOne < :dateAttribute0)";
        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class, parameterMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andLessThan("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final List<RegularEntityFour> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select t4 from RegularEntityFour t4 join t4.regularEntityThree t3 join t3.regularEntityTwo t2 join t2.regularEntityOne r where (r.dateAttributeOne <= :dateAttribute0)";
        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class, parameterMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andLessOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final List<RegularEntityFour> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));

    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final String query = "select t4 from RegularEntityFour t4 join t4.regularEntityThree t3 join t3.regularEntityTwo t2 join t2.regularEntityOne r where (r.dateAttributeOne > :dateAttribute0)";
        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class, parameterMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andGreaterThan("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final List<RegularEntityFour> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010", "11/11/2011");

        final String query = "select t4 from RegularEntityFour t4 join t4.regularEntityThree t3 join t3.regularEntityTwo t2 join t2.regularEntityOne r where (r.dateAttributeOne = :dateAttribute0) or (r.dateAttributeOne = :dateAttribute1)";
        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class, parameterMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"), parameterMap.get("dateAttribute1"));

        final List<RegularEntityFour> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }
}
