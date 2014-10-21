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

import com.uaihebert.model.test.FakeUaiCriteriaCTOImp;
import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

public class ExceptionSubQueryTest extends AbstractTest {

    @Test(expected = IllegalArgumentException.class)
    public void isRaisingExceptionIfSubQueryWithWrongImplementationInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        uaiCriteria.andAttributeIn("any", new FakeUaiCriteriaCTOImp());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isRaisingExceptionIfSubQueryWithWrongImplementationInvokedNotIn() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        uaiCriteria.andAttributeNotIn("any", new FakeUaiCriteriaCTOImp());
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfGetResultListIsInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.getResultList();
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfGetSingResultIsInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.getSingleResult();
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfGetMultiSelectResultIsInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.getMultiSelectResult();
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfNestedSubQueryIsInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.subQuery(null, Object.class);
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfAttributeInWithSubSubQueryInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.andAttributeIn("id", subQuery);
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfAttributeNotInWithSubSubQueryInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.andAttributeNotIn("id", subQuery);
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfOrderByAscWithSubQueryInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.orderByAsc("id");
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfOrderByDescWithSubQueryInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.orderByDesc("id");
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfAddHintWithSubQueryInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.addHint("", "");
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfCountAttributeWithSubQueryInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.countAttribute();
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfCountWithSubQueryInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.count();
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfCountRegularCriteriaWithSubQueryInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.countRegularCriteria();
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfFirstResultInWithSubQueryInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.setFirstResult(0);
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfMaxResultsInWithSubQueryInvoked() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.setMaxResults(0);
    }
}