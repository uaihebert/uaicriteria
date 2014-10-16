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
package com.uaihebert.test.easy_legacy.easy;

import com.uaihebert.model.EasyCriteria;
import com.uaihebert.model.test.EmbeddedIdDummy;
import com.uaihebert.model.test.EntityEmbeddedId;
import com.uaihebert.model.test.JoinEntityEmbeddedId;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.test.easy_legacy.CompositeKeyTests;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodsCompositeKeyTest extends AbstractTest implements CompositeKeyTests {

    @Test
    public void isAddingOneOrEquals() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final EmbeddedIdDummy idDummy = new EmbeddedIdDummy(1, "01 Key 1");
        final EmbeddedIdDummy idDummy2 = new EmbeddedIdDummy(2, "02 Key 2");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDummy1", idDummy);
        parameters.put("idDummy2", idDummy2);

        final String query = "select e from EntityEmbeddedId e " +
                "where e.id = :idDummy1 or e.id = :idDummy2";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class, parameters);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.orEquals("id", idDummy, idDummy2);

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isAddingOneWhereEquals() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final EmbeddedIdDummy idDummy = new EmbeddedIdDummy(1, "01 Key 1");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDummy", idDummy);
        final String query = "select e from EntityEmbeddedId e " +
                "where e.id = :idDummy ";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class, parameters);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.andEquals("id", idDummy);

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final EmbeddedIdDummy idDummy = new EmbeddedIdDummy(1, "01 Key 1");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDummy", idDummy);
        final String query = "select e from EntityEmbeddedId e " +
                "where e.id = :idDummy and e.justString = 'A'";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class, parameters);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.andEquals("id", idDummy).andEquals("justString", "A");

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isBetweenWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from EntityEmbeddedId e " +
                "where e.id.idString between '01 Key 1' and '03 Key 3'";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.andBetween("id.idString", "01 Key 1", "03 Key 3");

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from EntityEmbeddedId e " +
                "where e.id.idInteger >= 1";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.andGreaterOrEqualTo("id.idInteger", 1);

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isLessThanWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from EntityEmbeddedId e " +
                "where e.id.idInteger < 2";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.andLessThan("id.idInteger", 2);

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isLessOrEqualToWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from EntityEmbeddedId e " +
                "where e.id.idInteger <= 2";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.andLessOrEqualTo("id.idInteger", 2);

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isGreaterThanWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from EntityEmbeddedId e " +
                "where e.id.idInteger > 1";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.andGreaterThan("id.idInteger", 1);

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isJoinBetweenWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select j from JoinEntityEmbeddedId j " +
                "join j.entityEmbeddedIdList ee " +
                " where ee.id.idString between '01' and '03'";
        final List<JoinEntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, JoinEntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<JoinEntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), JoinEntityEmbeddedId.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("entityEmbeddedIdList.id");
        easyCriteria.andBetween("entityEmbeddedIdList.id.idString", "01", "03");

        final List<JoinEntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from JoinEntityEmbeddedId e " +
                "join fetch e.entityEmbeddedIdList f " +
                "where f.id.idInteger >= 2";
        final List<JoinEntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, JoinEntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<JoinEntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), JoinEntityEmbeddedId.class);
        easyCriteria.innerJoin("entityEmbeddedIdList");
        easyCriteria.andGreaterOrEqualTo("entityEmbeddedIdList.id.idInteger", 2);

        final List<JoinEntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isJoinLessThanWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from JoinEntityEmbeddedId e " +
                "join fetch e.entityEmbeddedIdList f " +
                "where f.id.idInteger < 2";
        final List<JoinEntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, JoinEntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<JoinEntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), JoinEntityEmbeddedId.class);
        easyCriteria.innerJoin("entityEmbeddedIdList");
        easyCriteria.andLessThan("entityEmbeddedIdList.id.idInteger", 2);

        final List<JoinEntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from JoinEntityEmbeddedId e " +
                "join fetch e.entityEmbeddedIdList f " +
                "where f.id.idInteger <= 2";
        final List<JoinEntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, JoinEntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<JoinEntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), JoinEntityEmbeddedId.class);
        easyCriteria.innerJoin("entityEmbeddedIdList");
        easyCriteria.andLessOrEqualTo("entityEmbeddedIdList.id.idInteger", 2);

        final List<JoinEntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from JoinEntityEmbeddedId e " +
                "join fetch e.entityEmbeddedIdList f " +
                "where f.id.idInteger > 2";
        final List<JoinEntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, JoinEntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<JoinEntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), JoinEntityEmbeddedId.class);
        easyCriteria.innerJoin("entityEmbeddedIdList");
        easyCriteria.andGreaterThan("entityEmbeddedIdList.id.idInteger", 2);

        final List<JoinEntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from EntityEmbeddedId e " +
                "join e.joinEntityEmbeddedId j " +
                "join j.entityEmbeddedIdList ee " +
                " where ee.id.idString between '01' and '03'";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("joinEntityEmbeddedId.entityEmbeddedIdList");
        easyCriteria.andBetween("joinEntityEmbeddedId.entityEmbeddedIdList.id.idString", "01", "03");

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from EntityEmbeddedId e " +
                "join e.joinEntityEmbeddedId j " +
                "join j.entityEmbeddedIdList ee " +
                " where ee.id.idInteger > 2";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("joinEntityEmbeddedId.entityEmbeddedIdList");
        easyCriteria.andGreaterThan("joinEntityEmbeddedId.entityEmbeddedIdList.id.idInteger", 2);

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from EntityEmbeddedId e " +
                "join e.joinEntityEmbeddedId j " +
                "join j.entityEmbeddedIdList ee " +
                " where ee.id.idInteger < 2";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("joinEntityEmbeddedId.entityEmbeddedIdList");
        easyCriteria.andLessThan("joinEntityEmbeddedId.entityEmbeddedIdList.id.idInteger", 2);

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from EntityEmbeddedId e " +
                "join e.joinEntityEmbeddedId j " +
                "join j.entityEmbeddedIdList ee " +
                " where ee.id.idInteger <= 2";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("joinEntityEmbeddedId.entityEmbeddedIdList");
        easyCriteria.andLessOrEqualTo("joinEntityEmbeddedId.entityEmbeddedIdList.id.idInteger", 2);

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from EntityEmbeddedId e " +
                "join e.joinEntityEmbeddedId j " +
                "join j.entityEmbeddedIdList ee " +
                " where ee.id.idInteger >= 2";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("joinEntityEmbeddedId.entityEmbeddedIdList");
        easyCriteria.andGreaterOrEqualTo("joinEntityEmbeddedId.entityEmbeddedIdList.id.idInteger", 2);

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        if (isOpenJPA()) {
            // there is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
            return;
        }

        final String query = "select e from EntityEmbeddedId e " +
                "join e.joinEntityEmbeddedId j " +
                "join j.entityEmbeddedIdList ee " +
                " where ee.id.idInteger = 1 or ee.id.idInteger = 3";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("joinEntityEmbeddedId.entityEmbeddedIdList");
        easyCriteria.orEquals("joinEntityEmbeddedId.entityEmbeddedIdList.id.idInteger", 1, 3);

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }

    @Test
    public void isLikeWorking() {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDummy", "%ey 3%");
        final String query = "select e from EntityEmbeddedId e " +
                "where e.id.idString like :idDummy ";
        final List<EntityEmbeddedId> resultFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class, parameters);
        assertTrue(resultFromJPQL.size() > 0);

        final EasyCriteria<EntityEmbeddedId> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), EntityEmbeddedId.class);
        easyCriteria.andStringLike("id.idString", "%ey 3%");

        final List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), resultList.size());
        assertTrue(resultFromJPQL.containsAll(resultList));
    }
}