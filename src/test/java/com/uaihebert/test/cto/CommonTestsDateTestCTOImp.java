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
package com.uaihebert.test.cto;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.test.easy_legacy.CommonTests;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonTestsDateTestCTOImp extends AbstractTest implements CommonTests {

    private Map<String, Object> createDateParameterMap(final String... dateArray) {
        final Map<String, Object> parameters = new HashMap<String, Object>();

        for (int i = 0; i < dateArray.length; i++) {
            final Date formattedDateOne = getFormattedDate(dateArray[i]);
            parameters.put("dateAttribute" + i, formattedDateOne);
        }

        return parameters;
    }

    @Test
    public void isAddingOneOrEquals() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010", "09/09/2009");

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("dateAttributeOne", parameterMap.get("dateAttribute0")).orEquals("dateAttributeOne", parameterMap.get("dateAttribute1"));

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orEquals("dateAttributeOne", parameterMap.get("dateAttribute0")).orEquals("dateAttributeOne", parameterMap.get("dateAttribute1"));

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingOneWhereEquals() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("dateAttributeOne", parameterMap.get("dateAttribute0")).andEquals("id", 1);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("dateAttributeOne", parameterMap.get("dateAttribute0")).andEquals("id", 1);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());

    }

    @Test
    public void isBetweenWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("09/09/2009", "11/10/2010");

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andBetween("dateAttributeOne", parameterMap.get("dateAttribute0"), parameterMap.get("dateAttribute1"));

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andBetween("dateAttributeOne", parameterMap.get("dateAttribute0"), parameterMap.get("dateAttribute1"));

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andGreaterOrEqualTo("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andGreaterOrEqualTo("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLessThanWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andLessThan("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andLessThan("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLessOrEqualToWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andLessOrEqualTo("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andLessOrEqualTo("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isGreaterThanWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andGreaterThan("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andGreaterThan("dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinBetweenWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("09/09/2009", "11/10/2010");

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andBetween("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"), parameterMap.get("dateAttribute1"));

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andBetween("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"), parameterMap.get("dateAttribute1"));

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andGreaterOrEqualTo("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andGreaterOrEqualTo("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinLessThanWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andLessThan("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andLessThan("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andLessOrEqualTo("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andLessOrEqualTo("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andGreaterThan("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andGreaterThan("regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("09/09/2009", "11/10/2010");

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andBetween("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"), parameterMap.get("dateAttribute1"));

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andBetween("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"), parameterMap.get("dateAttribute1"));

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());

    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andGreaterOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andGreaterOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andLessThan("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andLessThan("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andLessOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andLessOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010");

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andGreaterThan("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andGreaterThan("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"));

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final Map<String, Object> parameterMap = createDateParameterMap("10/10/2010", "11/11/2011");

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"), parameterMap.get("dateAttribute1"));

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.dateAttributeOne", parameterMap.get("dateAttribute0"), parameterMap.get("dateAttribute1"));

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}
