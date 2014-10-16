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

import static org.junit.Assert.assertEquals;

public class SquareTupleCTOTest extends TupleAbstractTest {

    @Test
    public void isSquareMethodInvokedWithOneParameterOnly() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.square("id");

        final Double criteriaSquare = extractResult(uaiCriteria, Double.class);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.square("id");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createTupleCriteria(RegularEntityOne.class, cto);

        final Double criteriaSquareCTO = extractResult(uaiCriteriaCTO, Double.class);

        assertEquals("making sure that the square has the same value", criteriaSquare, criteriaSquareCTO);
    }

    @Test
    public void isSumMethodInvokedWithTwoParameters() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.addTupleSelectAttribute("id").square("id");
        uaiCriteria.groupBy("id");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addTupleSelectAttribute("id").square("id");
        cto.groupBy("id");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createTupleCriteria(RegularEntityOne.class, cto);

        validateListResult(uaiCriteria.getTupleResult(), uaiCriteriaCTO.getTupleResult());
    }

    @Test
    public void isTupleWorkingWithSeveralGroupByAttributesAndSumFunction() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.addTupleSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo")
                .square("id");
        uaiCriteria.groupBy("id", "stringAttribute", "floatAttributeOne")
                .groupBy("dateAttributeTwo");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addTupleSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo")
                .square("id");
        cto.groupBy("id", "stringAttribute", "floatAttributeOne")
                .groupBy("dateAttributeTwo");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createTupleCriteria(RegularEntityOne.class, cto);

        validateListResult(uaiCriteria.getTupleResult(), uaiCriteriaCTO.getTupleResult());

    }
}