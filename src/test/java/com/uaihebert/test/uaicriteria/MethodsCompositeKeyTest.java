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

import com.uaihebert.model.test.EntityWithEmbeddedId;
import com.uaihebert.model.test.EntityWithEmbeddedIdJoinOneLevel;
import com.uaihebert.model.test.EntityWithEmbeddedIdJoinThreeLevel;
import com.uaihebert.model.test.RegularEmbeddedId;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.test.easy_legacy.CompositeKeyTests;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//  There is a bug for Batoo, it does not support composite key
//  https://github.com/BatooOrg/BatooJPA/issues/225

//  There is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
public class MethodsCompositeKeyTest extends AbstractTest implements CompositeKeyTests {
    private static final String ANY_STRING_01 = "Any String 01";

    private static final String ID_STRING_AAA = "AAA";
    private static final String ID_STRING_CCC = "CCC";

    private static final int ID_INTEGER_02 = 2;
    private static final int ID_INTEGER_03 = 3;

    @Test
    public void isAddingOneOrEquals() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final RegularEmbeddedId embeddedId = new RegularEmbeddedId(1, "AAA");
        final RegularEmbeddedId embeddedId2 = new RegularEmbeddedId(2, "BBB");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("embeddedId1", embeddedId);
        parameters.put("embeddedId2", embeddedId2);

        final String query = "select r from EntityWithEmbeddedId r where r.embeddedId = :embeddedId1 or r.embeddedId = :embeddedId2";

        final List<EntityWithEmbeddedId> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedId.class, parameters);
        assertTrue(resultFromJPQL.size() == 2);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.orEquals("embeddedId", embeddedId, embeddedId2);

        final List<EntityWithEmbeddedId> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isAddingOneWhereEquals() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final RegularEmbeddedId embeddedId = new RegularEmbeddedId(1, "AAA");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("embeddedId1", embeddedId);

        final String query = "select r from EntityWithEmbeddedId r where r.embeddedId = :embeddedId1";

        final List<EntityWithEmbeddedId> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedId.class, parameters);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andEquals("embeddedId", embeddedId);

        final List<EntityWithEmbeddedId> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final RegularEmbeddedId embeddedId = new RegularEmbeddedId(1, "AAA");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("embeddedId1", embeddedId);

        final String query = "select r from EntityWithEmbeddedId r where r.embeddedId = :embeddedId1 and r.anyString = '" + ANY_STRING_01 + "'";

        final List<EntityWithEmbeddedId> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedId.class, parameters);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andEquals("embeddedId", embeddedId).andEquals("anyString", ANY_STRING_01);

        final List<EntityWithEmbeddedId> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isBetweenWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select r from EntityWithEmbeddedId r where r.embeddedId.idString between '" + ID_STRING_AAA + "' and '" + ID_STRING_CCC + "'";

        final List<EntityWithEmbeddedId> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andBetween("embeddedId.idString", ID_STRING_AAA, ID_STRING_CCC);

        final List<EntityWithEmbeddedId> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select r from EntityWithEmbeddedId r where r.embeddedId.idInteger >= " + ID_INTEGER_02;

        final List<EntityWithEmbeddedId> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andGreaterOrEqualTo("embeddedId.idInteger", ID_INTEGER_02);

        final List<EntityWithEmbeddedId> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isLessThanWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select r from EntityWithEmbeddedId r where r.embeddedId.idInteger < " + ID_INTEGER_02;

        final List<EntityWithEmbeddedId> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andLessThan("embeddedId.idInteger", ID_INTEGER_02);

        final List<EntityWithEmbeddedId> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isLessOrEqualToWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select r from EntityWithEmbeddedId r where r.embeddedId.idInteger <= " + ID_INTEGER_02;

        final List<EntityWithEmbeddedId> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andLessOrEqualTo("embeddedId.idInteger", ID_INTEGER_02);

        final List<EntityWithEmbeddedId> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));

    }

    @Test
    public void isGreaterThanWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select r from EntityWithEmbeddedId r where r.embeddedId.idInteger > " + ID_INTEGER_02;

        final List<EntityWithEmbeddedId> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andGreaterThan("embeddedId.idInteger", ID_INTEGER_02);

        final List<EntityWithEmbeddedId> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isJoinBetweenWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select r from EntityWithEmbeddedIdJoinOneLevel r join r.entityWithEmbeddedId e where e.embeddedId.idString between '" + ID_STRING_AAA + "' and '" + ID_STRING_CCC + "'";

        final List<EntityWithEmbeddedIdJoinOneLevel> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedIdJoinOneLevel.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedId");
        uaiCriteria.andBetween("entityWithEmbeddedId.embeddedId.idString", ID_STRING_AAA, ID_STRING_CCC);

        final List<EntityWithEmbeddedIdJoinOneLevel> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select r from EntityWithEmbeddedIdJoinOneLevel r join r.entityWithEmbeddedId e where e.embeddedId.idInteger >= " + ID_INTEGER_02;

        final List<EntityWithEmbeddedIdJoinOneLevel> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedIdJoinOneLevel.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedId");
        uaiCriteria.andGreaterOrEqualTo("entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final List<EntityWithEmbeddedIdJoinOneLevel> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));

    }

    @Test
    public void isJoinLessThanWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select r from EntityWithEmbeddedIdJoinOneLevel r join r.entityWithEmbeddedId e where e.embeddedId.idInteger < " + ID_INTEGER_02;

        final List<EntityWithEmbeddedIdJoinOneLevel> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedIdJoinOneLevel.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedId");
        uaiCriteria.andLessThan("entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final List<EntityWithEmbeddedIdJoinOneLevel> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select r from EntityWithEmbeddedIdJoinOneLevel r join r.entityWithEmbeddedId e where e.embeddedId.idInteger <= " + ID_INTEGER_02;

        final List<EntityWithEmbeddedIdJoinOneLevel> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedIdJoinOneLevel.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedId");
        uaiCriteria.andLessOrEqualTo("entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final List<EntityWithEmbeddedIdJoinOneLevel> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select r from EntityWithEmbeddedIdJoinOneLevel r join r.entityWithEmbeddedId e where e.embeddedId.idInteger > " + ID_INTEGER_02;

        final List<EntityWithEmbeddedIdJoinOneLevel> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedIdJoinOneLevel.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedId");
        uaiCriteria.andGreaterThan("entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final List<EntityWithEmbeddedIdJoinOneLevel> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select j3 from EntityWithEmbeddedIdJoinThreeLevel j3 " +
                "join j3.entityWithEmbeddedIdJoinTwoLevel j2 " +
                "join j2.entityWithEmbeddedIdJoinOneLevel j1 " +
                "join j1.entityWithEmbeddedId r " +
                "where r.embeddedId.idString between '" + ID_STRING_AAA + "' and '" + ID_STRING_CCC + "'";

        final List<EntityWithEmbeddedIdJoinThreeLevel> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedIdJoinThreeLevel.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId");
        uaiCriteria.andBetween("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idString", ID_STRING_AAA, ID_STRING_CCC);

        final List<EntityWithEmbeddedIdJoinThreeLevel> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select j3 from EntityWithEmbeddedIdJoinThreeLevel j3 " +
                "join j3.entityWithEmbeddedIdJoinTwoLevel j2 " +
                "join j2.entityWithEmbeddedIdJoinOneLevel j1 " +
                "join j1.entityWithEmbeddedId r " +
                "where  r.embeddedId.idInteger >= " + ID_INTEGER_02;

        final List<EntityWithEmbeddedIdJoinThreeLevel> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedIdJoinThreeLevel.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId");
        uaiCriteria.andGreaterOrEqualTo("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final List<EntityWithEmbeddedIdJoinThreeLevel> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select j3 from EntityWithEmbeddedIdJoinThreeLevel j3 " +
                "join j3.entityWithEmbeddedIdJoinTwoLevel j2 " +
                "join j2.entityWithEmbeddedIdJoinOneLevel j1 " +
                "join j1.entityWithEmbeddedId r " +
                "where  r.embeddedId.idInteger < " + ID_INTEGER_02;

        final List<EntityWithEmbeddedIdJoinThreeLevel> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedIdJoinThreeLevel.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId");
        uaiCriteria.andLessThan("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final List<EntityWithEmbeddedIdJoinThreeLevel> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select j3 from EntityWithEmbeddedIdJoinThreeLevel j3 " +
                "join j3.entityWithEmbeddedIdJoinTwoLevel j2 " +
                "join j2.entityWithEmbeddedIdJoinOneLevel j1 " +
                "join j1.entityWithEmbeddedId r " +
                "where  r.embeddedId.idInteger <= " + ID_INTEGER_02;

        final List<EntityWithEmbeddedIdJoinThreeLevel> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedIdJoinThreeLevel.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId");
        uaiCriteria.andLessOrEqualTo("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final List<EntityWithEmbeddedIdJoinThreeLevel> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select j3 from EntityWithEmbeddedIdJoinThreeLevel j3 " +
                "join j3.entityWithEmbeddedIdJoinTwoLevel j2 " +
                "join j2.entityWithEmbeddedIdJoinOneLevel j1 " +
                "join j1.entityWithEmbeddedId r " +
                "where  r.embeddedId.idInteger > " + ID_INTEGER_02;

        final List<EntityWithEmbeddedIdJoinThreeLevel> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedIdJoinThreeLevel.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId");
        uaiCriteria.andGreaterThan("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final List<EntityWithEmbeddedIdJoinThreeLevel> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select j3 from EntityWithEmbeddedIdJoinThreeLevel j3 " +
                "join j3.entityWithEmbeddedIdJoinTwoLevel j2 " +
                "join j2.entityWithEmbeddedIdJoinOneLevel j1 " +
                "join j1.entityWithEmbeddedId r " +
                "where r.embeddedId.idInteger = " + ID_INTEGER_02 + " or r.embeddedId.idInteger = " + ID_INTEGER_03;

        final List<EntityWithEmbeddedIdJoinThreeLevel> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedIdJoinThreeLevel.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId");
        uaiCriteria.orEquals("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);
        uaiCriteria.orEquals("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_03);

        final List<EntityWithEmbeddedIdJoinThreeLevel> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isLikeWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final String query = "select r from EntityWithEmbeddedId r where r.embeddedId.idString like '%A%' ";

        final List<EntityWithEmbeddedId> resultFromJPQL = jpqlHelper.getListFromJPQL(query, EntityWithEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andStringLike("embeddedId.idString", "%A%");

        final List<EntityWithEmbeddedId> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }
}