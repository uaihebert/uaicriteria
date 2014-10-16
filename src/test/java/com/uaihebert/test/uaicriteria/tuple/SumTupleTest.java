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
package com.uaihebert.test.uaicriteria.tuple;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.test.TupleAbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SumTupleTest extends TupleAbstractTest {

    @Test
    public void isSumMethodInvokedWithOneParameterOnly() {
        if (isBatoo()) {
            return;
        }

        final String query = "select sum(r.id) from RegularEntityOne r";

        final List<Object> resultFromJPQL = jpqlHelper.getListFromJPQL(query, Object.class);
        final Long jpqlSum = (Long) resultFromJPQL.get(0);

        assertTrue("making sure that the sum worked", jpqlSum > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.sum("id");

        final Long criteriaSum = extractResult(uaiCriteria, Long.class);

        assertEquals("making sure that the sum has the same value", jpqlSum, criteriaSum);
    }

    @Test
    public void isSumMethodInvokedWithTwoParameters() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r.id, sum(r.id) from RegularEntityOne r group by r.id";

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.addTupleSelectAttribute("id").sum("id");
        uaiCriteria.groupBy("id");

        if (isEclipselink()) {
            validateResultWithVector(query, uaiCriteria);
            return;
        }

        validateResultAsList(query, uaiCriteria);
    }

    @Test
    public void isTupleWorkingWithSeveralGroupByAttributesAndSumFunction() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r.id, sum(r.id), r.stringAttribute, r.floatAttributeOne, " +
                "r.dateAttributeTwo from RegularEntityOne r group by r.id, r.stringAttribute, " +
                "r.floatAttributeOne, r.dateAttributeTwo";

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.addTupleSelectAttribute("id")
                .sum("id")
                .addTupleSelectAttribute("stringAttribute")
                .addTupleSelectAttribute("floatAttributeOne")
                .addTupleSelectAttribute("dateAttributeTwo");
        uaiCriteria.groupBy("id", "stringAttribute", "floatAttributeOne")
                .groupBy("dateAttributeTwo");


        if (isEclipselink()) {
            validateResultWithVector(query, uaiCriteria);
            return;
        }

        validateResultAsList(query, uaiCriteria);
    }

    @Test
    public void isAddingAttributeToANumber() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r.id, sum(r.id+10), r.stringAttribute, r.floatAttributeOne, " +
                "r.dateAttributeTwo from RegularEntityOne r group by r.id, r.stringAttribute, " +
                "r.floatAttributeOne, r.dateAttributeTwo";

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.addTupleSelectAttribute("id")
                .sum("id", 10)
                .addTupleSelectAttribute("stringAttribute", "floatAttributeOne")
                .addTupleSelectAttribute("dateAttributeTwo");
        uaiCriteria.groupBy("id", "stringAttribute", "floatAttributeOne")
                .groupBy("dateAttributeTwo");

        if (isEclipselink()) {
            validateResultWithVector(query, uaiCriteria);
            return;
        }

        validateResultAsList(query, uaiCriteria);
    }

    @Test
    public void isAddingNumberToAAttribute() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r.id, sum(10 + r.id), r.stringAttribute, r.floatAttributeOne, " +
                "r.dateAttributeTwo from RegularEntityOne r group by r.id, r.stringAttribute, " +
                "r.floatAttributeOne, r.dateAttributeTwo";

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.addTupleSelectAttribute("id")
                .sum(10, "id")
                .addTupleSelectAttribute("stringAttribute", "floatAttributeOne")
                .addTupleSelectAttribute("dateAttributeTwo");
        uaiCriteria.groupBy("id", "stringAttribute", "floatAttributeOne")
                .groupBy("dateAttributeTwo");

        if (isEclipselink()) {
            validateResultWithVector(query, uaiCriteria);
            return;
        }

        validateResultAsList(query, uaiCriteria);
    }
}