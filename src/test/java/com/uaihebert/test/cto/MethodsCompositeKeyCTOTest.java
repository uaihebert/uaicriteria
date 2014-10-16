package com.uaihebert.test.cto;

import com.uaihebert.model.test.EntityWithEmbeddedId;
import com.uaihebert.model.test.EntityWithEmbeddedIdJoinOneLevel;
import com.uaihebert.model.test.EntityWithEmbeddedIdJoinThreeLevel;
import com.uaihebert.model.test.RegularEmbeddedId;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.test.easy_legacy.CompositeKeyTests;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//  There is a bug for Batoo, it does not support composite key
//  https://github.com/BatooOrg/BatooJPA/issues/225

//  There is a OpenJPA bug for this: https://issues.apache.org/jira/browse/OPENJPA-1806
public class MethodsCompositeKeyCTOTest extends AbstractTest implements CompositeKeyTests {
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

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.orEquals("embeddedId", embeddedId, embeddedId2);

        final UaiCriteria<EntityWithEmbeddedId> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orEquals("embeddedId", embeddedId, embeddedId2);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedId.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingOneWhereEquals() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final RegularEmbeddedId embeddedId = new RegularEmbeddedId(1, "AAA");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("embeddedId1", embeddedId);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andEquals("embeddedId", embeddedId);

        final UaiCriteria<EntityWithEmbeddedId> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("embeddedId", embeddedId);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedId.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final RegularEmbeddedId embeddedId = new RegularEmbeddedId(1, "AAA");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("embeddedId1", embeddedId);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andEquals("embeddedId", embeddedId).andEquals("anyString", ANY_STRING_01);

        final UaiCriteria<EntityWithEmbeddedId> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("embeddedId", embeddedId).andEquals("anyString", ANY_STRING_01);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedId.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isBetweenWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andBetween("embeddedId.idString", ID_STRING_AAA, ID_STRING_CCC);

        final UaiCriteria<EntityWithEmbeddedId> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andBetween("embeddedId.idString", ID_STRING_AAA, ID_STRING_CCC);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedId.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andGreaterOrEqualTo("embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedId> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andGreaterOrEqualTo("embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedId.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLessThanWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andLessThan("embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedId> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andLessThan("embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedId.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLessOrEqualToWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andLessOrEqualTo("embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedId> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andLessOrEqualTo("embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedId.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isGreaterThanWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andGreaterThan("embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedId> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andGreaterThan("embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedId.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinBetweenWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedId");
        uaiCriteria.andBetween("entityWithEmbeddedId.embeddedId.idString", ID_STRING_AAA, ID_STRING_CCC);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("entityWithEmbeddedId");
        cto.andBetween("entityWithEmbeddedId.embeddedId.idString", ID_STRING_AAA, ID_STRING_CCC);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedId");
        uaiCriteria.andGreaterOrEqualTo("entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("entityWithEmbeddedId");
        cto.andGreaterOrEqualTo("entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinLessThanWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedId");
        uaiCriteria.andLessThan("entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("entityWithEmbeddedId");
        cto.andLessThan("entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedId");
        uaiCriteria.andLessOrEqualTo("entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("entityWithEmbeddedId");
        cto.andLessOrEqualTo("entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinGreaterThanWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedId");
        uaiCriteria.andGreaterThan("entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("entityWithEmbeddedId");
        cto.andGreaterThan("entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinOneLevel> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedIdJoinOneLevel.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId");
        uaiCriteria.andBetween("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idString", ID_STRING_AAA, ID_STRING_CCC);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId");
        cto.andBetween("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idString", ID_STRING_AAA, ID_STRING_CCC);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId");
        uaiCriteria.andGreaterOrEqualTo("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId");
        cto.andGreaterOrEqualTo("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId");
        uaiCriteria.andLessThan("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId");
        cto.andLessThan("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId");
        uaiCriteria.andLessOrEqualTo("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId");
        cto.andLessOrEqualTo("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId");
        uaiCriteria.andGreaterThan("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId");
        cto.andGreaterThan("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteria = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class);
        uaiCriteria.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId");
        uaiCriteria.orEquals("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);
        uaiCriteria.orEquals("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_03);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId");
        cto.orEquals("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_02);
        cto.orEquals("entityWithEmbeddedIdJoinTwoLevel.entityWithEmbeddedIdJoinOneLevel.entityWithEmbeddedId.embeddedId.idInteger", ID_INTEGER_03);

        final UaiCriteria<EntityWithEmbeddedIdJoinThreeLevel> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedIdJoinThreeLevel.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLikeWorking() {
        if (isBatoo() || isOpenJPA()) {
            return;
        }

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteria = createCriteria(EntityWithEmbeddedId.class);
        uaiCriteria.andStringLike("embeddedId.idString", "%A%");

        final UaiCriteria<EntityWithEmbeddedId> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andStringLike("embeddedId.idString", "%A%");

        final UaiCriteria<EntityWithEmbeddedId> uaiCriteriaCTO = createCriteria(EntityWithEmbeddedId.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}