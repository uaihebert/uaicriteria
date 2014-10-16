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

import com.uaihebert.model.test.RegularEntityFour;
import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.test.easy_legacy.CommonTests;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

public class CommonTestsLongTestCTOImp extends AbstractTest implements CommonTests {
    private static final Long VALUE_23 = 23L;
    private static final Long VALUE_32 = 32L;
    private static final Long VALUE_33 = 33L;
    private static final Long VALUE_34 = 34L;

    @Test
    public void isAddingOneOrEquals() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("longAttributeOne", VALUE_33).orEquals("longAttributeOne", VALUE_23);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orEquals("longAttributeOne", VALUE_33).orEquals("longAttributeOne", VALUE_23);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingOneWhereEquals() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("longAttributeOne", VALUE_33).andEquals("booleanAttributeOne", true);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("longAttributeOne", VALUE_33).andEquals("booleanAttributeOne", true);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isBetweenWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andBetween("longAttributeOne", VALUE_23, VALUE_34);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andBetween("longAttributeOne", VALUE_23, VALUE_34);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andGreaterOrEqualTo("longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andGreaterOrEqualTo("longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLessThanWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andLessThan("longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andLessThan("longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLessOrEqualToWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andLessOrEqualTo("longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andLessOrEqualTo("longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isGreaterThanWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andGreaterThan("longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andGreaterThan("longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinBetweenWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andBetween("regularEntityOne.longAttributeOne", VALUE_23, VALUE_34);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andBetween("regularEntityOne.longAttributeOne", VALUE_23, VALUE_34);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andGreaterOrEqualTo("regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andGreaterOrEqualTo("regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinLessThanWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andLessThan("regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andLessThan("regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andLessOrEqualTo("regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andLessOrEqualTo("regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andGreaterThan("regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andGreaterThan("regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree");
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andBetween("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_23, VALUE_34);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree");
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andBetween("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_23, VALUE_34);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andGreaterOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andGreaterOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());

    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andLessThan("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andLessThan("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andLessOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andLessOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andGreaterThan("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_32);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andGreaterThan("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_32);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_33).orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_23);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_33).orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.longAttributeOne", VALUE_23);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}