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
package com.uaihebert.test.uaicriteria.multiSelect;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.test.MultiSelectAbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CountMultiSelectTest extends MultiSelectAbstractTest {

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionWithRegularCountWithMultiSelectCriteria() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);

        uaiCriteria.countRegularCriteria();
    }

    @Test
    public void isCountingAttributeWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select count(r.id) from RegularEntityOne r";

        final List<Object> resultFromJPQL = jpqlHelper.getListFromJPQL(query, Object.class);
        final Long jpqlCount = (Long) resultFromJPQL.get(0);

        assertTrue("making sure that the count worked", jpqlCount > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.countAttribute("id");

        final Long criteriaCount = (Long) uaiCriteria.getMultiSelectResult().get(0);

        assertEquals("making sure that the count has the same value", jpqlCount, criteriaCount);
    }

    @Test
    public void isMultiSelectWorkingWithSeveralGroupByAttributesAndSumFunction() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r.id, count(r.id), r.stringAttribute, r.floatAttributeOne, " +
                "r.dateAttributeTwo from RegularEntityOne r group by r.id, r.stringAttribute, " +
                "r.floatAttributeOne, r.dateAttributeTwo";

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.addMultiSelectAttribute("id")
                .countAttribute("id")
                .addMultiSelectAttribute("stringAttribute")
                .addMultiSelectAttribute("floatAttributeOne")
                .addMultiSelectAttribute("dateAttributeTwo");
        uaiCriteria.groupBy("id", "stringAttribute", "floatAttributeOne")
                .groupBy("dateAttributeTwo");

        if (isEclipselink()) {
            validateResultWithVector(query, uaiCriteria);
            return;
        }

        validateResultAsList(query, uaiCriteria);
    }
}