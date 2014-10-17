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
package com.uaihebert.test.cto.multiselect;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.test.MultiSelectAbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

public class BasicMultiSelectCTOTest extends MultiSelectAbstractTest {

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfGetMultiSelectResultIsInvokedFromCTO() {
        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.getMultiSelectResult();
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfMultiSelectInvokedWithoutAttributesOrExpressions() {
        if (isBatoo()) {
            throw new IllegalStateException();
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.getMultiSelectResult();
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
    public void isMultiSelectWorkingWithSeveralAttributes() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createMultiSelectCriteria(RegularEntityOne.class, cto);

        validateListResult(uaiCriteria.getMultiSelectResult(), uaiCriteriaCTO.getMultiSelectResult());
    }
}