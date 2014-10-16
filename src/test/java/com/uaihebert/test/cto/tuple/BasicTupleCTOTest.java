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
package com.uaihebert.test.cto.tuple;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.test.TupleAbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

public class BasicTupleCTOTest extends TupleAbstractTest {

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfGetTupleResultIsInvokedFromCTO() {
        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.getTupleResult();
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfTupleInvokedWithoutAttributesOrExpressions() {
        if (isBatoo()) {
            throw new IllegalStateException();
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.getTupleResult();
    }

    @Test
    public void isAddingGroupByToRegularQuery() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.groupBy("id");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.groupBy("id");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isTupleWorkingWithSeveralAttributes() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.addTupleSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addTupleSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createTupleCriteria(RegularEntityOne.class, cto);

        validateListResult(uaiCriteria.getTupleResult(), uaiCriteriaCTO.getTupleResult());
    }
}