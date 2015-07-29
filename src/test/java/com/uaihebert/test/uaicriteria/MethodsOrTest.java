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
package com.uaihebert.test.uaicriteria;

import com.uaihebert.model.test.RegularEntityFour;
import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodsOrTest extends AbstractTest {

    @Test
    public void isTwoOrIdsWorking() {
        final String query = "select r from RegularEntityOne r where (r.id = 1) or (r.id = 3)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("id", 1L).orEquals("id", 3L);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isTwoOrAttributesWorking() {
        final String query = "select r from RegularEntityOne r where (r.stringAttribute = 'Just a String 03') or (r.longAttributeOne = 33)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("stringAttribute", "Just a String 03").orEquals("longAttributeOne", 33L);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isMultipleOrOfTheSameValueWorking() {
        final String query = "select r from RegularEntityOne r where (r.id = 1) or (r.id = 2) or (r.stringAttribute = 'Just a String 03')";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 3);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("id", 1L, 2L).orEquals("stringAttribute", "Just a String 03");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isAddingOneOrAndEqualsAtSameTime() {
        final String query = "select r from RegularEntityOne r where r.booleanAttributeTwo = true and r.longAttributeTwo = 30 and (r.id = 1 or r.id = 2)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("booleanAttributeTwo", true).andEquals("longAttributeTwo", 30L).orEquals("id", 1L, 2L);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isAddingTwoGroupsOfOr() {
        final String query = "select r from RegularEntityOne r where (r.booleanAttributeTwo = true or r.longAttributeOne = 33 or r.stringAttribute = 'Just a String 03') and (r.id = 1 or r.id = 2)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals(0, "stringAttribute", "Just a String 03").orEquals(0, "booleanAttributeTwo", true).orEquals(0, "longAttributeTwo", 33L).orEquals(1, "id", 1L, 2L);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isAddingAndGroupedByOr_01() {
        final String query = "select r from RegularEntityOne r where (r.id = 1 and r.longAttributeOne = 33) or (r.booleanAttributeTwo = true and r.id = 3)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);

        uaiCriteria.addAndSeparatedByOr(0, "longAttributeOne", 33L).addAndSeparatedByOr(0, "id", 1L);
        uaiCriteria.addAndSeparatedByOr(1, "booleanAttributeTwo", true).addAndSeparatedByOr(1, "id", 3L);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isAddingAndGroupedByOr_02() {
        final String query = "select r from RegularEntityOne r where (r.stringAttribute = 'Just a String 02' and r.longAttributeOne = 33) or (r.booleanAttributeTwo = true and r.longAttributeTwo = 15)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);

        uaiCriteria.addAndSeparatedByOr(0, "stringAttribute", "Just a String 02").addAndSeparatedByOr(0, "longAttributeOne", 33);
        uaiCriteria.addAndSeparatedByOr(1, "booleanAttributeTwo", true).addAndSeparatedByOr(1, "longAttributeTwo", 15L);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isAddingAndGroupedByOrWithLowerCase() {
        final String query = "select r from RegularEntityOne r where (lower(r.stringAttribute) = 'just a string 02' and r.longAttributeOne = 33) or (r.booleanAttributeTwo = true and r.longAttributeTwo = 15)";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);

        uaiCriteria.addAndSeparatedByOr(true, 0, "stringAttribute", "Just a String 02").addAndSeparatedByOr(0, "longAttributeOne", 33);
        uaiCriteria.addAndSeparatedByOr(1, "booleanAttributeTwo", true).addAndSeparatedByOr(1, "longAttributeTwo", 15L);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrNotEqualsWorking() {
        final String query = "select r from RegularEntityFour r where r.id = 2 or r.stringAttribute <> 'Just a String 01'";

        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.orEquals("id", 2).orNotEquals("stringAttribute", "Just a String 01");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isDifferentOrAndTwoEqualsWorking() {
        final String query = "select r from RegularEntityOne r where r.id = 1 and r.stringAttribute = 'Just a String 02' " +
                " and (r.longAttributeOne = 33 or r.longAttributeTwo = -30)";

        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("id", 1).andEquals("stringAttribute", "Just a String 02");
        uaiCriteria.orEquals("longAttributeOne", 33L).orEquals("longAttributeTwo", -30);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isAbleToDoTheSameQuerySeveralTimes() {
        final String query = "select r from RegularEntityOne r where r.id = 1 and r.stringAttribute = 'Just a String 02' " +
                " and (r.longAttributeOne = 33 or r.longAttributeTwo = -30)";

        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("id", 1).andEquals("stringAttribute", "Just a String 02");
        uaiCriteria.orEquals("longAttributeOne", 33L).orEquals("longAttributeTwo", -30);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrLikeWorking() {
        final String query = "select r from RegularEntityOne r where r.stringAttribute like '%02%' or r.stringAttribute like '%01%'";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orStringLike("stringAttribute", "%02%").orStringLike("stringAttribute", "%01%");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrLikeWithLowerCaseWorking() {
        final String query = "select r from RegularEntityOne r where lower(r.stringAttribute) like '%a string 01%' or lower(r.stringAttribute) like '%a string 02%'";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertEquals("checking if the jpql result list size is valid", resultFromJPQL.size(), 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orStringLike(true, "stringAttribute", "%a string 01%").orStringLike(true, "stringAttribute", "%a string 02%");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrNotLikeWorking() {
        final String query = "select r from RegularEntityOne r where r.stringAttribute not like '%02%' or r.stringAttribute not like '%01%'";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orStringNotLike("stringAttribute", "%02%").orStringNotLike("stringAttribute", "%01%");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrNotLikeWithLowerCaseWorking() {
        final String query = "select r from RegularEntityOne r where lower(r.stringAttribute) not like '%a string 01%' or lower(r.stringAttribute) not like '%a string 02%'";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 2);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orStringNotLike(true, "stringAttribute", "%a string 01%").orStringNotLike(true, "stringAttribute", "%a string 02%");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrNullWorking() {
        final String query = "select r from RegularEntityOne r where r.stringAttribute is null or r.dateAttributeOne is null";

        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orIsNull("stringAttribute").orIsNull("dateAttributeOne");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrNotNullWorking() {
        final String query = "select r from RegularEntityOne r where r.stringAttribute is not null or r.dateAttributeOne is not null";

        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orIsNotNull("stringAttribute").orIsNotNull("dateAttributeOne");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isOrGreaterThanWorking() {
        final String query = "select r from RegularEntityFour r where r.stringAttribute <> 'Just a String 01' or r.id>1";

        final List<RegularEntityFour> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFour.class);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.andNotEquals("stringAttribute", "Just a String 01").orGreaterThan("id", 1L);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isMultipleOrGreaterThanWorking() {
        final String query = "select r from RegularEntityOne r where r.integerAttributeOne >3 or r.integerAttributeTwo>2";

        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertEquals(3, resultFromJPQL.size());

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orGreaterThan("integerAttributeOne", 3).orGreaterThan("integerAttributeTwo", 2);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }
}