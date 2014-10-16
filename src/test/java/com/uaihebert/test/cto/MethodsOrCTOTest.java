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
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

public class MethodsOrCTOTest extends AbstractTest {

    @Test
    public void isTwoOrIdsWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("id", 1L).orEquals("id", 3L);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orEquals("id", 1L).orEquals("id", 3L);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isTwoOrAttributesWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("stringAttribute", "Just a String 03").orEquals("longAttributeOne", 33L);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orEquals("stringAttribute", "Just a String 03").orEquals("longAttributeOne", 33L);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleOrOfTheSameValueWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("id", 1L, 2L).orEquals("stringAttribute", "Just a String 03");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orEquals("id", 1L, 2L).orEquals("stringAttribute", "Just a String 03");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingOneOrAndEqualsAtSameTime() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("booleanAttributeTwo", true).andEquals("longAttributeTwo", 30L).orEquals("id", 1L, 2L);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("booleanAttributeTwo", true).andEquals("longAttributeTwo", 30L).orEquals("id", 1L, 2L);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingTwoGroupsOfOr() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals(0, "stringAttribute", "Just a String 03").orEquals(0, "booleanAttributeTwo", true).orEquals(0, "longAttributeTwo", 33L).orEquals(1, "id", 1L, 2L);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orEquals(0, "stringAttribute", "Just a String 03").orEquals(0, "booleanAttributeTwo", true).orEquals(0, "longAttributeTwo", 33L).orEquals(1, "id", 1L, 2L);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingAndGroupedByOr_01() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.addAndSeparatedByOr(0, "longAttributeOne", 33L).addAndSeparatedByOr(0, "id", 1L);
        uaiCriteria.addAndSeparatedByOr(1, "booleanAttributeTwo", true).addAndSeparatedByOr(1, "id", 3L);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addAndSeparatedByOr(0, "longAttributeOne", 33L).addAndSeparatedByOr(0, "id", 1L);
        cto.addAndSeparatedByOr(1, "booleanAttributeTwo", true).addAndSeparatedByOr(1, "id", 3L);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingAndGroupedByOr_02() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.addAndSeparatedByOr(0, "stringAttribute", "Just a String 02").addAndSeparatedByOr(0, "longAttributeOne", 33);
        uaiCriteria.addAndSeparatedByOr(1, "booleanAttributeTwo", true).addAndSeparatedByOr(1, "longAttributeTwo", 15L);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addAndSeparatedByOr(0, "stringAttribute", "Just a String 02").addAndSeparatedByOr(0, "longAttributeOne", 33);
        cto.addAndSeparatedByOr(1, "booleanAttributeTwo", true).addAndSeparatedByOr(1, "longAttributeTwo", 15L);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingAndGroupedByOrWithLowerCase() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.addAndSeparatedByOr(true, 0, "stringAttribute", "Just a String 02").addAndSeparatedByOr(0, "longAttributeOne", 33);
        uaiCriteria.addAndSeparatedByOr(1, "booleanAttributeTwo", true).addAndSeparatedByOr(1, "longAttributeTwo", 15L);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addAndSeparatedByOr(true, 0, "stringAttribute", "Just a String 02").addAndSeparatedByOr(0, "longAttributeOne", 33);
        cto.addAndSeparatedByOr(1, "booleanAttributeTwo", true).addAndSeparatedByOr(1, "longAttributeTwo", 15L);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isOrNotEqualsWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("id", 2).orNotEquals("stringAttribute", "Just a String 01");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orEquals("id", 2).orNotEquals("stringAttribute", "Just a String 01");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isDifferentOrAndTwoEqualsWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("id", 1).andEquals("stringAttribute", "Just a String 02");
        uaiCriteria.orEquals("longAttributeOne", 33L).orEquals("longAttributeTwo", -30);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("id", 1).andEquals("stringAttribute", "Just a String 02");
        cto.orEquals("longAttributeOne", 33L).orEquals("longAttributeTwo", -30);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAbleToDoTheSameQuerySeveralTimes() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("id", 1).andEquals("stringAttribute", "Just a String 02");
        uaiCriteria.orEquals("longAttributeOne", 33L).orEquals("longAttributeTwo", -30);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("id", 1).andEquals("stringAttribute", "Just a String 02");
        cto.orEquals("longAttributeOne", 33L).orEquals("longAttributeTwo", -30);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isOrLikeWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orStringLike("stringAttribute", "%02%").orStringLike("stringAttribute", "%01%");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orStringLike("stringAttribute", "%02%").orStringLike("stringAttribute", "%01%");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isOrLikeWithLowerCaseWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orStringLike(true, "stringAttribute", "%a string 01%").orStringLike(true, "stringAttribute", "%a string 02%");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orStringLike(true, "stringAttribute", "%a string 01%").orStringLike(true, "stringAttribute", "%a string 02%");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isOrNotLikeWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orStringNotLike("stringAttribute", "%02%").orStringNotLike("stringAttribute", "%01%");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orStringNotLike("stringAttribute", "%02%").orStringNotLike("stringAttribute", "%01%");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isOrNotLikeWithLowerCaseWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orStringNotLike(true, "stringAttribute", "%a string 01%").orStringNotLike(true, "stringAttribute", "%a string 02%");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orStringNotLike(true, "stringAttribute", "%a string 01%").orStringNotLike(true, "stringAttribute", "%a string 02%");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isOrNullWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orIsNull("stringAttribute").orIsNull("dateAttributeOne");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orIsNull("stringAttribute").orIsNull("dateAttributeOne");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isOrNotNullWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orIsNotNull("stringAttribute").orIsNotNull("dateAttributeOne");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orIsNotNull("stringAttribute").orIsNotNull("dateAttributeOne");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}