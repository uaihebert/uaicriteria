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

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommonTestsBigDecimalTestImp extends AbstractTest implements CommonTests {
    private static final BigDecimal VALUE_01 = new BigDecimal(1);
    private static final BigDecimal VALUE_04 = new BigDecimal(4);
    private static final BigDecimal VALUE_23 = new BigDecimal(23);
    private static final BigDecimal VALUE_32 = new BigDecimal(32);
    private static final BigDecimal VALUE_33 = new BigDecimal(33);
    private static final BigDecimal VALUE_34 = new BigDecimal(34);

    @Test
    public void isAddingOneOrEquals() {
        final String query = "select r from RegularEntityOne r where (r.bigDecimalAttributeOne = 33) or (r.bigDecimalAttributeOne = 23)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("bigDecimalAttributeOne", VALUE_33).orEquals("bigDecimalAttributeOne", VALUE_23);

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isAddingOneWhereEquals() {
        final String query = "select r from RegularEntityOne r where r.bigDecimalAttributeOne = 33";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("bigDecimalAttributeOne", VALUE_33);

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final String query = "select r from RegularEntityOne r where r.bigDecimalAttributeOne = 33 and r.booleanAttributeOne = true";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("bigDecimalAttributeOne", VALUE_33).andEquals("booleanAttributeOne", true);

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isBetweenWorking() {
        final String query = "select r from RegularEntityOne r where r.bigDecimalAttributeOne between 23 and 34";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andBetween("bigDecimalAttributeOne", VALUE_23, VALUE_34);

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final String query = "select r from RegularEntityOne r where r.bigDecimalAttributeOne >= 33";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 1);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andGreaterOrEqualTo("bigDecimalAttributeOne", VALUE_33);

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isLessThanWorking() {
        final String query = "select r from RegularEntityOne r where r.bigDecimalAttributeOne < 33";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andLessThan("bigDecimalAttributeOne", VALUE_33);

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isLessOrEqualToWorking() {
        final String query = "select r from RegularEntityOne r where r.bigDecimalAttributeOne <= 33";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andLessOrEqualTo("bigDecimalAttributeOne", VALUE_33);

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isGreaterThanWorking() {
        final String query = "select r from RegularEntityOne r where r.bigDecimalAttributeOne > 33";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andGreaterThan("bigDecimalAttributeOne", VALUE_33);

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isJoinBetweenWorking() {
        final String query = "select t from RegularEntityTwo t join t.regularEntityOne r where r.bigDecimalAttributeOne between 23 and 34";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andBetween("regularEntityOne.bigDecimalAttributeOne", VALUE_23, VALUE_34);

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final String query = "select t from RegularEntityTwo t join t.regularEntityOne r where r.bigDecimalAttributeOne >= 33";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andGreaterOrEqualTo("regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isJoinLessThanWorking() {
        final String query = "select t from RegularEntityTwo t join t.regularEntityOne r where r.bigDecimalAttributeOne < 33";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andLessThan("regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final String query = "select t from RegularEntityTwo t join t.regularEntityOne r where r.bigDecimalAttributeOne <= 33";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andLessOrEqualTo("regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final String query = "select t from RegularEntityTwo t join t.regularEntityOne r where r.bigDecimalAttributeOne > 33";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andGreaterThan("regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final String query = "select t4 from RegularEntityFour t4 join t4.regularEntityThree t3 join t3.regularEntityTwo t2 join t2.regularEntityOne r where r.bigDecimalAttributeOne between 23 and 34";
        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree");
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andBetween("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_23, VALUE_34);

        final List<RegularEntityFour> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final String query = "select t4 from RegularEntityFour t4 join t4.regularEntityThree t3 join t3.regularEntityTwo t2 join t2.regularEntityOne r where r.bigDecimalAttributeOne >= 33";
        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andGreaterOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final List<RegularEntityFour> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final String query = "select t4 from RegularEntityFour t4 join t4.regularEntityThree t3 join t3.regularEntityTwo t2 join t2.regularEntityOne r where r.bigDecimalAttributeOne < 33";
        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andLessThan("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final List<RegularEntityFour> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final String query = "select t4 from RegularEntityFour t4 join t4.regularEntityThree t3 join t3.regularEntityTwo t2 join t2.regularEntityOne r where r.bigDecimalAttributeOne <= 33";
        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andLessOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final List<RegularEntityFour> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final String query = "select t4 from RegularEntityFour t4 join t4.regularEntityThree t3 join t3.regularEntityTwo t2 join t2.regularEntityOne r where r.bigDecimalAttributeOne > 32";
        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andGreaterThan("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_32);

        final List<RegularEntityFour> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final String query = "select t4 from RegularEntityFour t4 join t4.regularEntityThree t3 join t3.regularEntityTwo t2 join t2.regularEntityOne r where r.id = 1 or r.id = 4";
        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.id", VALUE_01).orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.id", VALUE_04);

        final List<RegularEntityFour> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }
}