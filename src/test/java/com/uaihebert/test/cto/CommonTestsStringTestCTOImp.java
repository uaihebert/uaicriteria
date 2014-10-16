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

import com.uaihebert.model.test.RegularEntityFive;
import com.uaihebert.model.test.RegularEntityFour;
import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.test.easy_legacy.CommonTests;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

import java.util.Arrays;

public class CommonTestsStringTestCTOImp extends AbstractTest implements CommonTests {

    private static final String VALUE_STRING_0 = "Just a String 0";
    private static final String VALUE_STRING_01 = "Just a String 01";
    private static final String VALUE_STRING_02 = "Just a String 02";
    private static final String VALUE_STRING_03 = "Just a String 03";

    @Test
    public void isAddingOneWhereEquals() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingOneOrEquals() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("stringAttribute", VALUE_STRING_01).orEquals("stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orEquals("stringAttribute", VALUE_STRING_01).orEquals("stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("stringAttribute", VALUE_STRING_01).andEquals("id", 2);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("stringAttribute", VALUE_STRING_01).andEquals("id", 2);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isBetweenWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andBetween("stringAttribute", VALUE_STRING_0, VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andBetween("stringAttribute", VALUE_STRING_0, VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isBetweenLowerCaseWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andBetween(true, "stringAttribute", VALUE_STRING_0, VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andBetween(true, "stringAttribute", VALUE_STRING_0, VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andGreaterOrEqualTo("stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andGreaterOrEqualTo("stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLessThanWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andLessThan("stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andLessThan("stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLessOrEqualToWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andLessOrEqualTo("stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andLessOrEqualTo("stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isGreaterThanWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andGreaterThan("stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andGreaterThan("stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinBetweenWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andBetween("regularEntityOne.stringAttribute", VALUE_STRING_0, VALUE_STRING_02);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andBetween("regularEntityOne.stringAttribute", VALUE_STRING_0, VALUE_STRING_02);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andGreaterOrEqualTo("regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andGreaterOrEqualTo("regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinLessThanWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andLessThan("regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andLessThan("regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andLessOrEqualTo("regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andLessOrEqualTo("regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andGreaterThan("regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andGreaterThan("regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree");
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andBetween("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_0, VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree");
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andBetween("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_0, VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andGreaterOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andGreaterOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andLessThan("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andLessThan("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andLessOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andLessOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andGreaterThan("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andGreaterThan("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_02);
        uaiCriteria.orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_03);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_02);
        cto.orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_03);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isInWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andStringIn("stringAttribute", Arrays.asList(VALUE_STRING_01, VALUE_STRING_02));

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andStringIn("stringAttribute", Arrays.asList(VALUE_STRING_01, VALUE_STRING_02));

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isInWithLowerCaseWorking() {
        if (isBatoo()) {
            // there is a problem in batoo JPQL, it does not parse a JPQL with in+lower
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andStringIn(true, "stringAttribute", Arrays.asList(VALUE_STRING_01, VALUE_STRING_03));

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andStringIn(true, "stringAttribute", Arrays.asList(VALUE_STRING_01, VALUE_STRING_03));

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isNotInWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andStringNotIn("stringAttribute", Arrays.asList(VALUE_STRING_01, VALUE_STRING_02));

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andStringNotIn("stringAttribute", Arrays.asList(VALUE_STRING_01, VALUE_STRING_02));

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isNotInWithLowerCaseWorking() {
        if (isBatoo()) {
            // there is a problem in batoo JPQL, it does not parse a JPQL with in+lower
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andStringNotIn(true, "stringAttribute", Arrays.asList(VALUE_STRING_01, VALUE_STRING_03));

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andStringNotIn(true, "stringAttribute", Arrays.asList(VALUE_STRING_01, VALUE_STRING_03));

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLikeWorking() {
        if (isBatoo()) {
            // batoo is not finding any results
            // the test will only check if there is no exception
            final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
            cto.leftJoin("regularEntityOneList");
            cto.andStringLike("regularEntityOneList.stringAttribute", "%01%");

            final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class, cto);
            uaiCriteria.getResultList();

            return;
        }

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.leftJoin("regularEntityOneList");
        uaiCriteria.andStringLike("regularEntityOneList.stringAttribute", "%01%");

        final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.leftJoin("regularEntityOneList");
        cto.andStringLike("regularEntityOneList.stringAttribute", "%01%");

        final UaiCriteria<RegularEntityFive> uaiCriteriaCTO = createCriteria(RegularEntityFive.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLikeWithLowerCaseWorking() {
        if (isBatoo()) {
            // batoo is not finding any results
            // the test will only check if there is no exception
            final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
            cto.leftJoin("regularEntityOneList");
            cto.andStringLike(true, "regularEntityOneList.stringAttribute", "%" + VALUE_STRING_01 + "%");

            final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class, cto);
            uaiCriteria.getResultList();

            return;
        }

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.leftJoin("regularEntityOneList");
        uaiCriteria.andStringLike(true, "regularEntityOneList.stringAttribute", "%" + VALUE_STRING_01 + "%");

        final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.leftJoin("regularEntityOneList");
        cto.andStringLike(true, "regularEntityOneList.stringAttribute", "%" + VALUE_STRING_01 + "%");

        final UaiCriteria<RegularEntityFive> uaiCriteriaCTO = createCriteria(RegularEntityFive.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isNotLikeWorking() {
        if (isBatoo()) {
            // batoo is not finding any results
            // the test will only check if there is no exception
            final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
            cto.leftJoin("regularEntityOneList");
            cto.andStringNotLike("regularEntityOneList.stringAttribute", "%01%");

            final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class, cto);
            uaiCriteria.getResultList();

            return;
        }

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.leftJoin("regularEntityOneList");
        uaiCriteria.andStringNotLike("regularEntityOneList.stringAttribute", "%01%");

        final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.leftJoin("regularEntityOneList");
        cto.andStringNotLike("regularEntityOneList.stringAttribute", "%01%");

        final UaiCriteria<RegularEntityFive> uaiCriteriaCTO = createCriteria(RegularEntityFive.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isNotLikeWithLowerCaseWorking() {
        if (isBatoo()) {
            // batoo is not finding any results
            // the test will only check if there is no exception
            final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
            cto.leftJoin("regularEntityOneList");
            cto.andStringNotLike(true, "regularEntityOneList.stringAttribute", "%" + VALUE_STRING_01 + "%");

            final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class, cto);
            uaiCriteria.getResultList();

            return;
        }

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.leftJoin("regularEntityOneList");
        uaiCriteria.andStringNotLike(true, "regularEntityOneList.stringAttribute", "%" + VALUE_STRING_01 + "%");

        final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.leftJoin("regularEntityOneList");
        cto.andStringNotLike(true, "regularEntityOneList.stringAttribute", "%" + VALUE_STRING_01 + "%");

        final UaiCriteria<RegularEntityFive> uaiCriteriaCTO = createCriteria(RegularEntityFive.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}