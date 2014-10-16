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

public class DiffTupleTest extends TupleAbstractTest {

    @Test
    public void isMethodInvokedWithTwoParameters() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r.id, (r.id - r.integerAttributeOne) from RegularEntityOne r";

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.addTupleSelectAttribute("id").diff("id", "integerAttributeOne");

        if (isEclipselink()) {
            validateResultWithVector(query, uaiCriteria);
            return;
        }

        validateResultAsList(query, uaiCriteria);
    }

    @Test
    public void isTupleWorkingWithSeveralAttributesAndFunction() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r.id, (r.id - r.integerAttributeOne), r.stringAttribute, r.floatAttributeOne, " +
                "r.dateAttributeTwo from RegularEntityOne r ";

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.addTupleSelectAttribute("id")
                .diff("id", "integerAttributeOne")
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
    public void isDiffNumberFromAttribute() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r.id, (r.id - 10), r.stringAttribute, r.floatAttributeOne, " +
                "r.dateAttributeTwo from RegularEntityOne r ";

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.addTupleSelectAttribute("id")
                .diff("id", 10L)
                .addTupleSelectAttribute("stringAttribute", "floatAttributeOne")
                .addTupleSelectAttribute("dateAttributeTwo");

        if (isEclipselink()) {
            validateResultWithVector(query, uaiCriteria);
            return;
        }

        validateResultAsList(query, uaiCriteria);
    }

    @Test
    public void isDiffAttributeFromNumber() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r.id, (10 - r.id), r.stringAttribute, r.floatAttributeOne, " +
                "r.dateAttributeTwo from RegularEntityOne r ";

        final UaiCriteria<RegularEntityOne> uaiCriteria = createTupleCriteria(RegularEntityOne.class);
        uaiCriteria.addTupleSelectAttribute("id")
                .diff(10L, "id")
                .addTupleSelectAttribute("stringAttribute", "floatAttributeOne")
                .addTupleSelectAttribute("dateAttributeTwo");

        if (isEclipselink()) {
            validateResultWithVector(query, uaiCriteria);
            return;
        }

        validateResultAsList(query, uaiCriteria);
    }
}