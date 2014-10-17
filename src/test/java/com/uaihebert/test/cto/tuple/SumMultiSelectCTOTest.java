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
package com.uaihebert.test.cto.multiSelect;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.test.MultiSelectAbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumMultiSelectCTOTest extends MultiSelectAbstractTest {

    @Test
    public void isSumMethodInvokedWithOneParameterOnly() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.sum("id");

        final Long uaiCriteriaSum = extractResult(uaiCriteria, Long.class);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.sum("id");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createMultiSelectCriteria(RegularEntityOne.class, cto);

        final Long uaiCTOSum = extractResult(uaiCriteriaCTO, Long.class);

        assertEquals("making sure that the sum has the same value", uaiCriteriaSum, uaiCTOSum);
    }

    @Test
    public void isSumMethodInvokedWithTwoParameters() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.addMultiSelectAttribute("id").sum("id");
        uaiCriteria.groupBy("id");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addMultiSelectAttribute("id").sum("id");
        cto.groupBy("id");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createMultiSelectCriteria(RegularEntityOne.class, cto);

        validateListResult(uaiCriteria.getMultiSelectResult(), uaiCriteriaCTO.getMultiSelectResult());
    }

    @Test
    public void isMultiSelectWorkingWithSeveralGroupByAttributesAndSumFunction() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");
        uaiCriteria.sum("id");
        uaiCriteria.groupBy("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");
        cto.sum("id");
        cto.groupBy("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createMultiSelectCriteria(RegularEntityOne.class, cto);

        validateListResult(uaiCriteria.getMultiSelectResult(), uaiCriteriaCTO.getMultiSelectResult());
    }

    @Test
    public void isAddingAttributeToANumber() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");
        uaiCriteria.sum("id", 10);
        uaiCriteria.groupBy("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");
        cto.sum("id", 10);
        cto.groupBy("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createMultiSelectCriteria(RegularEntityOne.class, cto);

        validateListResult(uaiCriteria.getMultiSelectResult(), uaiCriteriaCTO.getMultiSelectResult());
    }

    @Test
    public void isAddingNumberToAAttribute() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");
        uaiCriteria.sum(10, "id");
        uaiCriteria.groupBy("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");
        cto.sum(10, "id");
        cto.groupBy("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createMultiSelectCriteria(RegularEntityOne.class, cto);

        validateListResult(uaiCriteria.getMultiSelectResult(), uaiCriteriaCTO.getMultiSelectResult());
    }
}