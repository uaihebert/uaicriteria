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

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ComparisonSubQueryTest extends AbstractTest {

    @Test
    public void isGreaterThanWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.stringAttribute in (select sub.stringAttribute from RegularEntityTwo sub where sub.id > 2)";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityTwo> subQuery = uaiCriteria.subQuery("stringAttribute", RegularEntityTwo.class);
        subQuery.andGreaterThan("id", 2l);

        uaiCriteria.andAttributeIn("stringAttribute", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.stringAttribute in (select sub.stringAttribute from RegularEntityTwo sub where sub.id >= 2)";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityTwo> subQuery = uaiCriteria.subQuery("stringAttribute", RegularEntityTwo.class);
        subQuery.andGreaterOrEqualTo("id", 2l);

        uaiCriteria.andAttributeIn("stringAttribute", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isLessThanWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.stringAttribute in (select sub.stringAttribute from RegularEntityTwo sub where sub.id < 2)";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityTwo> subQuery = uaiCriteria.subQuery("stringAttribute", RegularEntityTwo.class);
        subQuery.andLessThan("id", 2l);

        uaiCriteria.andAttributeIn("stringAttribute", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isLessOrEqualToWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.stringAttribute in (select sub.stringAttribute from RegularEntityTwo sub where sub.id <= 2)";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityTwo> subQuery = uaiCriteria.subQuery("stringAttribute", RegularEntityTwo.class);
        subQuery.andLessOrEqualTo("id", 2l);

        uaiCriteria.andAttributeIn("stringAttribute", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isSubQueryWithLongAndLowerCaseWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where lower(sub.stringAttribute) = lower('Just a String 02'))";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andEquals(true, "stringAttribute", "Just a String 02");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isNotEqualsWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where sub.stringAttribute <> 'Just a String 02')";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andNotEquals("stringAttribute", "Just a String 02");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isNotEqualsWithLowerCaseWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where lower(sub.stringAttribute) <> lower('Just a String 02'))";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andNotEquals(true, "stringAttribute", "Just a String 02");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isStringWithLowerCaseGreaterThanWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where lower(sub.stringAttribute) > 'just a string 02')";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andGreaterThan(true, "stringAttribute", "Just a String 02");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isStringWithLowerCaseGreaterOrEqualToWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where lower(sub.stringAttribute) >= 'just a string 02')";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andGreaterOrEqualTo(true, "stringAttribute", "Just a String 02");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isStringWithLowerCaseLessThanWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where lower(sub.stringAttribute) < 'just a string 02')";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andLessThan(true, "stringAttribute", "Just a String 02");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isStringWithLowerCaseLessOrEqualToWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where lower(sub.stringAttribute) <= 'just a string 02')";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andLessOrEqualTo(true, "stringAttribute", "Just a String 02");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }
}