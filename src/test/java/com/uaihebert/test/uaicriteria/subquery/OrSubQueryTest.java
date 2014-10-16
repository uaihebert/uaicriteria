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

public class OrSubQueryTest extends AbstractTest {

    @Test
    public void isOrWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where ((sub.stringAttribute = 'Just a String 02') or (sub.stringAttribute = 'Just a String 01')))";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.orEquals("stringAttribute", "Just a String 02", "Just a String 01");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrLowerCaseWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where ((lower(sub.stringAttribute) = 'just a string 02') or (lower(sub.stringAttribute) = 'just a string 01')))";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.orEquals(true, "stringAttribute", "Just a String 02", "Just a String 01");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrWithIndexWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in ( " +
                "select sub.id from RegularEntityOne sub where (" +
                "(sub.stringAttribute = 'Just a String 02') or (sub.stringAttribute = 'Just a String 01')) " +
                "and ((sub.id = 1) or (sub.id = 2))  " +
                ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.orEquals(1, "stringAttribute", "Just a String 02", "Just a String 01");
        subQuery.orEquals(2, "id", 1, 2);

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrWithIndexAndLowerCaseWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in ( " +
                "select sub.id from RegularEntityOne sub where (" +
                "(lower(sub.stringAttribute) = lower('Just a String 02')) or (lower(sub.stringAttribute) = lower('Just a String 01'))) " +
                "and ((sub.id = 1) or (sub.id = 2))  " +
                ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.orEquals(true, 1, "stringAttribute", "Just a String 02", "Just a String 01");
        subQuery.orEquals(2, "id", 1, 2);

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrStringLike() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where ((sub.stringAttribute like '%String 02%') or (sub.stringAttribute like '%String 01%')))";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.orStringLike("stringAttribute", "%String 02%", "%String 01%");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrStringLikeWithLowerCase() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where ((lower(sub.stringAttribute) like '%string 02%') or (lower(sub.stringAttribute) like '%string 01%')))";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.orStringLike(true, "stringAttribute", "%String 02%", "%String 01%");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrStringNotLike() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where ((sub.stringAttribute not like '%String 02%') or (sub.stringAttribute not like '%String 01%')))";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.orStringNotLike("stringAttribute", "%String 02%", "%String 01%");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrStringNotLikeWithLowerCase() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where ((lower(sub.stringAttribute) not like '%string 02%') or (lower(sub.stringAttribute) not like '%string 01%')))";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.orStringNotLike(true, "stringAttribute", "%String 02%", "%String 01%");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrNotEqualsWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where ((sub.stringAttribute <> 'Just a String 02') or (sub.stringAttribute <> 'Just a String 01')))";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.orNotEquals("stringAttribute", "Just a String 02", "Just a String 01");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrNotEqualsWithLowerCaseWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where ((lower(sub.stringAttribute) <> 'just a string 02') or (lower(sub.stringAttribute) <> 'just a string 01')))";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.orNotEquals(true, "stringAttribute", "Just a String 02", "Just a String 01");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void andSeparatedByOrWorking() {
        if (isBatoo()) {
            return;
        }

        final String subQueryJPQL = "select sub.id from RegularEntityOne sub where (sub.stringAttribute = 'Just a String 02' and sub.longAttributeOne = 33) or (sub.booleanAttributeTwo = true and sub.longAttributeTwo = 15)";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.addAndSeparatedByOr(0, "stringAttribute", "Just a String 02").addAndSeparatedByOr(0, "longAttributeOne", 33);
        subQuery.addAndSeparatedByOr(1, "booleanAttributeTwo", true).addAndSeparatedByOr(1, "longAttributeTwo", 15L);

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void andSeparatedByOrLowerCaseWorking() {
        if (isBatoo()) {
            return;
        }

        final String subQueryJPQL = "select sub.id from RegularEntityOne sub where (lower(sub.stringAttribute) = 'just a string 02' and sub.longAttributeOne = 33) or (sub.booleanAttributeTwo = true and sub.longAttributeTwo = 15)";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.addAndSeparatedByOr(true, 0, "stringAttribute", "Just a String 02").addAndSeparatedByOr(0, "longAttributeOne", 33);
        subQuery.addAndSeparatedByOr(1, "booleanAttributeTwo", true).addAndSeparatedByOr(1, "longAttributeTwo", 15L);

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }
}