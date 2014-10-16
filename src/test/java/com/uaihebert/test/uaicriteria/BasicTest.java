package com.uaihebert.test.uaicriteria;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityThree;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


public class BasicTest extends AbstractTest {

    @Test
    public void isCreatingCriteria() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        assertNotNull(uaiCriteria);
    }

    @Test
    public void verifyIsThereIsDataInDatabase() {
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL("select r from RegularEntityOne r", RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);
    }

    @Test
    public void isListingAll() {
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL("select r from RegularEntityOne r", RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isGettingSingleResult() {
        final RegularEntityOne entityFromJpql = jpqlHelper.getSingleResult("select r from RegularEntityOne r where r.id = 1", RegularEntityOne.class);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        final RegularEntityOne entityFromCriteria = uaiCriteria.andEquals("id", 1L).getSingleResult();

        assertEquals(entityFromJpql, entityFromCriteria);
    }

    @Test
    public void isNotEqualsWorking() {
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL("select r from RegularEntityOne r where r.stringAttribute <> 'Just a String'", RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        final List<RegularEntityOne> criteriaList = uaiCriteria.andNotEquals("stringAttribute", "Just a String").getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isAddingOneWhereEqualsBoolean() {
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL("select r from RegularEntityOne r where r.booleanAttributeOne = true", RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        final List<RegularEntityOne> criteriaList = uaiCriteria.andEquals("booleanAttributeOne", true).getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isAddingSequentialWhereEqualsBoolean() {
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL("select r from RegularEntityOne r where r.booleanAttributeOne = true and r.booleanAttributeTwo = true", RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        final List<RegularEntityOne> criteriaList = uaiCriteria.andEquals("booleanAttributeOne", true)
                .andEquals("booleanAttributeTwo", true)
                .getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isEqualsWorkingWithObject() {
        final String query = "select r from RegularEntityOne r where r.regularEntityTwo.id = 1";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final RegularEntityTwo regularEntityTwo = new RegularEntityTwo();
        regularEntityTwo.setId(1L);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("regularEntityTwo", regularEntityTwo);

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isSequentialEqualsWorkingWithObject() {
        final String query = "select r from RegularEntityOne r where r.regularEntityTwo.id = 1 and r.regularEntityThree.id = 1";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final RegularEntityTwo regularEntityTwo = new RegularEntityTwo();
        regularEntityTwo.setId(1L);

        final RegularEntityThree regularEntityThree = new RegularEntityThree();
        regularEntityThree.setId(1L);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("regularEntityTwo", regularEntityTwo).andEquals("regularEntityThree", regularEntityThree);

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isSequentialEqualsWorking() {
        final String query = "select r from RegularEntityOne r where r.regularEntityTwo.id = 1 and r.booleanAttributeOne = true and r.regularEntityThree.id = 1";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final RegularEntityTwo regularEntityTwo = new RegularEntityTwo();
        regularEntityTwo.setId(1L);

        final RegularEntityThree regularEntityThree = new RegularEntityThree();
        regularEntityThree.setId(1L);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("regularEntityTwo", regularEntityTwo).andEquals("regularEntityThree", regularEntityThree).andEquals("booleanAttributeOne", true);

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isEqualsWorkingWithJoinEntity() {
        final String query = "select r from RegularEntityOne r join r.regularEntityTwo rt where rt.id = 1";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.innerJoin("regularEntityTwo").andEquals("regularEntityTwo.id", 1L);
        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isRaisingExceptionWhenJoinDoesNotExists() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.innerJoin("regularEntityTwo").andEquals("regularEntityThree.id", 1L);
    }

    @Test
    public void isOrderByDescWorking() {
        final String query = "select r from RegularEntityOne r order by r.id desc";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 1);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orderByDesc("id");

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));

        for (int i = 0; i < resultFromJPQL.size(); i++) {
            assertEquals(resultFromJPQL.get(i), criteriaList.get(i));
        }
    }

    @Test
    public void isSequentialOrderByDescWorking() {
        final String query = "select r from RegularEntityOne r order by r.booleanAttributeTwo desc, r.stringAttribute asc";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 1);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orderByDesc("booleanAttributeTwo").orderByAsc("stringAttribute");

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));

        for (int i = 0; i < resultFromJPQL.size(); i++) {
            assertEquals(resultFromJPQL.get(i), criteriaList.get(i));
        }
    }

    @Test
    public void isPaginationWorking() {
        final String query = "select r from RegularEntityOne r order by r.id asc";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 1);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.setFirstResult(0);
        uaiCriteria.setMaxResults(1);

        final List<RegularEntityOne> criteriaList = new LinkedList<RegularEntityOne>(uaiCriteria.getResultList());

        assertEquals(1, criteriaList.size());
        assertEquals(resultFromJPQL.get(0), criteriaList.get(0));

        uaiCriteria.setFirstResult(1);
        uaiCriteria.setMaxResults(1);

        criteriaList.clear();
        criteriaList.addAll(uaiCriteria.getResultList());

        assertEquals(1, criteriaList.size());
        assertEquals(resultFromJPQL.get(1), criteriaList.get(0));
    }

    @Test
    public void isAddingHints() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.addHint("some.value", "true");

        final List<RegularEntityOne> criteriaList = new LinkedList<RegularEntityOne>(uaiCriteria.getResultList());
        assertTrue(criteriaList.size() > 0);
    }
}