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

import static org.junit.Assert.assertTrue;

public class BasicTupleTest extends TupleAbstractTest {

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfTupleInvokedWithoutAttributesOrExpressions() {
        if (isBatoo()) {
            throw new IllegalStateException();
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.getTupleResult();
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfInvokingRegularQueryFromTupleQuery() {
        if (isBatoo()) {
            throw new IllegalStateException();
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.getResultList();
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfInvokingTupleQueryFromRegularQuery() {
        if (isBatoo()) {
            throw new IllegalStateException();
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.getTupleResult();
    }

    @Test
    public void isAddingGroupByToRegularQuery() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityOne r group by r.id";

        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);

        assertTrue("making sure that the sum worked", resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.groupBy("id");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isTupleWorkingWithSeveralAttributes() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r.id, r.stringAttribute, r.floatAttributeOne, " +
                "r.dateAttributeTwo from RegularEntityOne r group by r.id, r.stringAttribute, " +
                "r.floatAttributeOne, r.dateAttributeTwo";

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.addTupleSelectAttribute("id")
                .addTupleSelectAttribute("stringAttribute")
                .addTupleSelectAttribute("floatAttributeOne")
                .addTupleSelectAttribute("dateAttributeTwo");

        if (isEclipselink()) {
            validateResultWithVector(query, uaiCriteria);
            return;
        }

        validateResultAsList(query, uaiCriteria);
    }

    @Test
    public void isAddingSumToANumber() {
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
}