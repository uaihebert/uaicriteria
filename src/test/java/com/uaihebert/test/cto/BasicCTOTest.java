package com.uaihebert.test.cto;

import com.uaihebert.model.test.FakeUaiCriteriaCTOImp;
import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BasicCTOTest extends AbstractTest {
    private static final long LONG_ID_ONE = 1L;

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfCountRegularCriteriaInvokedOnCTO() {
        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.countRegularCriteria();
    }


    @Test(expected = IllegalArgumentException.class)
    public void isValidatingIfInstanceFromInterfaceIsCorrect() {
        final UaiCriteria<String> uaiCriteriaCTO = new FakeUaiCriteriaCTOImp<String>();
        UaiCriteriaFactory.createQueryCriteria(getEntityManager(), String.class, uaiCriteriaCTO);
    }

    @Test
    public void isCreatingCriteria() {
        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = UaiCriteriaFactory.createQueryUaiCTO();
        final UaiCriteria<RegularEntityOne> uaiCriteria = UaiCriteriaFactory.createQueryCriteria(getEntityManager(), RegularEntityOne.class, uaiCriteriaCTO);

        assertNotNull(uaiCriteria);
    }

    @Test
    public void isListingAll() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isGettingSingleResult() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("id", LONG_ID_ONE);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("id", LONG_ID_ONE);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        assertEquals(uaiCriteria.getSingleResult(), uaiCriteriaCTO.getSingleResult());
    }

    @Test
    public void isNotEqualsWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andNotEquals("id", LONG_ID_ONE);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andNotEquals("id", LONG_ID_ONE);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingOneWhereEqualsBoolean() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("booleanAttributeOne", Boolean.TRUE);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("booleanAttributeOne", Boolean.TRUE);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingSequentialWhereEqualsBoolean() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("booleanAttributeOne", Boolean.TRUE).andEquals("id", LONG_ID_ONE);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("booleanAttributeOne", Boolean.TRUE).andEquals("id", LONG_ID_ONE);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isEqualsWorkingWithObject() {
        final RegularEntityTwo regularEntityTwo = new RegularEntityTwo();
        regularEntityTwo.setId(LONG_ID_ONE);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("regularEntityTwo", regularEntityTwo);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("regularEntityTwo", regularEntityTwo);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isSequentialEqualsWorkingWithObject() {
        final RegularEntityTwo regularEntityTwo = new RegularEntityTwo();
        regularEntityTwo.setId(LONG_ID_ONE);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("regularEntityTwo", regularEntityTwo).andEquals("id", LONG_ID_ONE);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("regularEntityTwo", regularEntityTwo).andEquals("id", LONG_ID_ONE);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isSequentialEqualsWorking() {
        final RegularEntityTwo regularEntityTwo = new RegularEntityTwo();
        regularEntityTwo.setId(LONG_ID_ONE);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("regularEntityTwo", regularEntityTwo).andEquals("id", LONG_ID_ONE).andEquals("booleanAttributeOne", Boolean.TRUE);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("regularEntityTwo", regularEntityTwo).andEquals("id", LONG_ID_ONE).andEquals("booleanAttributeOne", Boolean.TRUE);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isRaisingExceptionWhenJoinDoesNotExists() {
        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("regularEntityTwo.booleanAttributeOne", Boolean.TRUE);

        createCriteria(RegularEntityOne.class, cto);
    }

    @Test
    public void isEqualsWorkingWithJoinEntity() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.innerJoin("regularEntityTwo");
        uaiCriteria.andEquals("regularEntityTwo.id", LONG_ID_ONE);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityTwo");
        cto.andEquals("regularEntityTwo.id", LONG_ID_ONE);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isOrderByDescWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orderByDesc("id");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orderByDesc("id");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isSequentialOrderByDescWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orderByDesc("booleanAttributeTwo").orderByAsc("stringAttribute");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orderByDesc("booleanAttributeTwo").orderByAsc("stringAttribute");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isPaginationWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.setFirstResult(0);
        uaiCriteria.setMaxResults(1);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.setFirstResult(0);
        cto.setMaxResults(1);

        UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());

        uaiCriteria.setFirstResult(1);
        uaiCriteria.setMaxResults(1);

        cto.setFirstResult(1);
        cto.setMaxResults(1);

        uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingHints() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.addHint("any.hint", "any value");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addHint("any.hint", "any value");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}