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
package com.uaihebert.test.uaicriteria.multiselect;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.test.MultiSelectAbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AverageMultiSelectTest extends MultiSelectAbstractTest {

    @Test
    public void isAvgMethodInvokedWithOneParameterOnly() {
        if (isBatoo()) {
            return;
        }

        final String query = "select avg(r.id) from RegularEntityOne r";

        final List<Object> resultFromJPQL = jpqlHelper.getListFromJPQL(query, Object.class);

        if (isOpenJPA()) {
            workarroundForOpenJPA(resultFromJPQL);
            return;
        }

        final Double avgAverage = (Double) resultFromJPQL.get(0);

        assertTrue("making sure that the avg worked", avgAverage > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.average("id");

        final Double criteriaAvg = extractResult(uaiCriteria, Double.class);

        assertEquals("making sure that the avg has the same value", avgAverage, criteriaAvg);
    }

    private void workarroundForOpenJPA(final List<Object> resultFromJPQL) {
        //Open JPA is returnin Longs
        final Long avgJPQL = (Long) resultFromJPQL.get(0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.average("id");

        final Double criteriaAvg = extractResult(uaiCriteria, Double.class);

        assertEquals("making sure that the avg has the same value", Double.valueOf(avgJPQL.toString()), criteriaAvg);
    }

    @Test
    public void isAverageMethodInvokedWithTwoParameters() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r.id, avg(r.id) from RegularEntityOne r group by r.id";

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.addMultiSelectAttribute("id").average("id");
        uaiCriteria.groupBy("id");

        if (isEclipselink()) {
            validateResultWithVector(query, uaiCriteria);
            return;
        }

        final List jpqlList = jpqlHelper.getListFromJPQL(query, Object[].class);

        assertTrue("making sure that the avg worked", jpqlList.size() > 1);

        final List multiselectList = uaiCriteria.getMultiSelectResult();

        assertEquals("making sure that the avg has the same value", jpqlList.size(), multiselectList.size());

        validateListResult(jpqlList, multiselectList, OpenJpaIndexToConvert.SECOND);
    }

    @Test
    public void isMultiSelectWorkingWithSeveralGroupByAttributesAndAverageFunction() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r.id, avg(r.id), r.stringAttribute, r.floatAttributeOne, " +
                "r.dateAttributeTwo from RegularEntityOne r group by r.id, r.stringAttribute, " +
                "r.floatAttributeOne, r.dateAttributeTwo";

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.addMultiSelectAttribute("id")
                .average("id")
                .addMultiSelectAttribute("stringAttribute")
                .addMultiSelectAttribute("floatAttributeOne")
                .addMultiSelectAttribute("dateAttributeTwo");
        uaiCriteria.groupBy("id", "stringAttribute", "floatAttributeOne")
                .groupBy("dateAttributeTwo");

        if (isEclipselink()) {
            validateResultWithVector(query, uaiCriteria);
            return;
        }

        final List jpqlList = jpqlHelper.getListFromJPQL(query, Object[].class);

        assertTrue("making sure that the average worked", jpqlList.size() > 1);

        final List multiselectList = uaiCriteria.getMultiSelectResult();

        assertEquals("making sure that the average has the same value", jpqlList.size(), multiselectList.size());

        validateListResult(jpqlList, multiselectList, OpenJpaIndexToConvert.SECOND);
    }
}