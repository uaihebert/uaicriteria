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

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class NullEmptySubQueryTest extends AbstractTest {

    @Test
    public void isNullWorking() {
        if (isBatoo()) {
            return;
        }

        final String subQueryJPQL = "select r.id from RegularEntityOne r where r.stringAttribute is null";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andIsNull("stringAttribute");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void orIsNullWorking() {
        if (isBatoo()) {
            return;
        }

        final String subQueryJPQL = "select r.id from RegularEntityOne r where r.stringAttribute is null or r.dateAttributeOne is null";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.orIsNull("stringAttribute").orIsNull("dateAttributeOne");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isNotNullWorking() {
        if (isBatoo()) {
            return;
        }

        final String subQueryJPQL = "select r.id from RegularEntityOne r where r.stringAttribute is not null";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andIsNotNull("stringAttribute");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void orIsNotNullWorking() {
        if (isBatoo()) {
            return;
        }

        final String subQueryJPQL = "select r.id from RegularEntityOne r where r.stringAttribute is not null or r.dateAttributeOne is not null";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.orIsNotNull("stringAttribute").orIsNotNull("dateAttributeOne");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isEmptyWorking() {
        if (isBatoo()) {
            // just to check if there is no warning
            final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
            final UaiCriteria<RegularEntityFive> subQuery = uaiCriteria.subQuery("id", RegularEntityFive.class);

            try {
                subQuery.andCollectionIsEmpty("regularEntityOneList");
                fail("batoo is raising subQuery runtime exception, we are not prepared if it works with batoo!");
            } catch (final Exception ex) {
                // raising exception with batoo + subQuery
                return;
            }

            return;
        }

        final String subQueryJPQL = "select r.id from RegularEntityFive r where r.regularEntityOneList is empty";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityFive> subQuery = uaiCriteria.subQuery("id", RegularEntityFive.class);
        subQuery.andCollectionIsEmpty("regularEntityOneList");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isNotEmptyWorking() {
        if (isBatoo()) {

            // just to check if there is no warning
            final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
            final UaiCriteria<RegularEntityFive> subQuery = uaiCriteria.subQuery("id", RegularEntityFive.class);

            try {
                subQuery.andCollectionIsNotEmpty("regularEntityOneList");
                fail("batoo is raising subQuery runtime exception, we are not prepared if it works with batoo!");
            } catch (final Exception ex) {
                // raising exception with batoo + subQuery
                return;
            }
        }

        final String subQueryJPQL = "select r.id from RegularEntityFive r where r.regularEntityOneList is not empty";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityFive> subQuery = uaiCriteria.subQuery("id", RegularEntityFive.class);
        subQuery.andCollectionIsNotEmpty("regularEntityOneList");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }
}