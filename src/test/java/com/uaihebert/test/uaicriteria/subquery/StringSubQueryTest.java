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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class StringSubQueryTest extends AbstractTest {
    private static final String VALUE_STRING_01 = "Just a String 01";
    private static final String VALUE_STRING_02 = "Just a String 02";

    @Test
    public void isLikeWorking() {
        if (isBatoo()) {
            // batoo is not finding any results
            // the test will only check if there is no exception
            final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
            final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
            subQuery.andStringLike("stringAttribute", "%02%");
            return;
        }

        final String subQueryJPQL = "select r.id from RegularEntityOne r where r.stringAttribute like '%String 02%'";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andStringLike("stringAttribute", "%02%");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isLowerCaseLikeWorking() {
        if (isBatoo()) {
            // batoo is not finding any results
            // the test will only check if there is no exception
            final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
            final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
            subQuery.andStringLike("stringAttribute", "%02%");
            return;
        }

        final String subQueryJPQL = "select r.id from RegularEntityOne r where lower(r.stringAttribute) like '%string 02%'";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andStringLike(true, "stringAttribute", "%02%");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isNotLikeWorking() {
        if (isBatoo()) {
            // batoo is not finding any results
            // the test will only check if there is no exception
            final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
            final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
            subQuery.andStringNotLike("stringAttribute", "%02%");
            return;
        }


        final String subQueryJPQL = "select r.id from RegularEntityOne r where r.stringAttribute not like '%String 02%'";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andStringNotLike("stringAttribute", "%02%");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isNotLowerLikeWorking() {
        if (isBatoo()) {
            // batoo is not finding any results
            // the test will only check if there is no exception
            final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
            final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
            subQuery.andStringNotLike("stringAttribute", "%02%");
            return;
        }


        final String subQueryJPQL = "select r.id from RegularEntityOne r where lower(r.stringAttribute) not like '%string 02%'";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andStringNotLike(true, "stringAttribute", "%02%");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isStringInWorking() {
        if (isBatoo()) {
            return;
        }

        final String subQueryJPQL = "select r.id from RegularEntityOne r where r.stringAttribute in ('Just a String 01', 'Just a String 02')";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andStringIn("stringAttribute", Arrays.asList(VALUE_STRING_01, VALUE_STRING_02));

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isStringInLowerCaseWorking() {
        if (isBatoo()) {
            return;
        }

        final String subQueryJPQL = "select r.id from RegularEntityOne r where lower(r.stringAttribute) in ('just a string 01', 'just a string 02')";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andStringIn(true, "stringAttribute", Arrays.asList(VALUE_STRING_01, VALUE_STRING_02));

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isStringNotInWorking() {
        if (isBatoo()) {
            return;
        }

        final String subQueryJPQL = "select r.id from RegularEntityOne r where r.stringAttribute not in ('Just a String 01', 'Just a String 02')";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andStringNotIn("stringAttribute", Arrays.asList(VALUE_STRING_01, VALUE_STRING_02));

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isStringNotInLowerCaseWorking() {
        if (isBatoo()) {
            return;
        }

        final String subQueryJPQL = "select r.id from RegularEntityOne r where lower(r.stringAttribute) not in ('just a string 01', 'just a string 02')";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andStringNotIn(true, "stringAttribute", Arrays.asList(VALUE_STRING_01, VALUE_STRING_02));

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }
}