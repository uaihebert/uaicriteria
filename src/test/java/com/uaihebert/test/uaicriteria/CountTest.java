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

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CountTest extends AbstractTest {

    @Test
    public void isCountWithoutParametersWorking() {
        final String query = "select count(r) from RegularEntityOne r";
        final List<Long> resultFromJPQL = jpqlHelper.getListFromJPQL(query, Long.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);

        assertEquals(resultFromJPQL.get(0), uaiCriteria.count());
    }

    @Test
    public void isCountWithParametersWorking() {
        final String query = "select count(r) from RegularEntityOne r where r.id = 1";
        final List<Long> resultFromJPQL = jpqlHelper.getListFromJPQL(query, Long.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("id", 1L);

        assertEquals(resultFromJPQL.get(0), uaiCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultListResults() {
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL("select r from RegularEntityOne r", RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);

        assertEquals(resultFromJPQL.size(), uaiCriteria.count().intValue());

        final List<RegularEntityOne> resultFromCriteria = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultFromCriteria.size());
        assertTrue(resultFromJPQL.containsAll(resultFromCriteria));
    }

    @Test
    public void isCountingAndGettingResultListAfterWithParameter() {
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL("select r from RegularEntityOne r where r.id = 1", RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("id", 1L);

        assertEquals(resultFromJPQL.size(), uaiCriteria.count().intValue());

        final List<RegularEntityOne> resultFromCriteria = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultFromCriteria.size());
        assertTrue(resultFromJPQL.containsAll(resultFromCriteria));
    }

    @Test
    public void isCountingWithDistinct() {
        final String query = "select distinct count(r) from RegularEntityOne r";
        final List<Long> resultFromJPQL = jpqlHelper.getListFromJPQL(query, Long.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.setDistinctTrue();

        assertEquals(resultFromJPQL.get(0), uaiCriteria.count());
    }

    @Test
    public void isCountingWithDistinctWithParameters() {
        final String query = "select distinct count(r) from RegularEntityOne r where r.id = 1";
        final List<Long> resultFromJPQL = jpqlHelper.getListFromJPQL(query, Long.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.setDistinctTrue().andEquals("id", 1L);

        assertEquals(resultFromJPQL.get(0), uaiCriteria.count());
    }

    @Test
    public void isNewCountMethodWorking() {
        final String query = "select count(r) from RegularEntityOne r";
        final List<Long> resultFromJPQL = jpqlHelper.getListFromJPQL(query, Long.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);

        assertEquals(resultFromJPQL.get(0), uaiCriteria.countRegularCriteria());
    }
}