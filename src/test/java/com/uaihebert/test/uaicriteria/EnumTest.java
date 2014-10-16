package com.uaihebert.test.uaicriteria;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.model.test.RegularEnum;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnumTest extends AbstractTest {

    @Test
    public void isNullWorkingWithEnum() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityOne r where r.regularEnum is null ";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andIsNull("regularEnum");

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isNotNullWorkingWithEnum() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityOne r where r.regularEnum is not null ";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andIsNotNull("regularEnum");

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isNullWithJoinWorkingWithEnum() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r join r.regularEntityOne r1 where r1.regularEnum is null ";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andIsNull("regularEntityOne.regularEnum");

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isNotNullWithJoinWorkingWithEnum() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r join r.regularEntityOne r1 where r1.regularEnum is not null ";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andIsNotNull("regularEntityOne.regularEnum");

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isAcceptingEnumWithInClause() {
        if (isBatoo()) {
            return;
        }

        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enumList", Arrays.asList(RegularEnum.VALUE_01, RegularEnum.VALUE_02));

        final String query = "select r1 from RegularEntityOne r1 where r1.regularEnum in :enumList ";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class, paramMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andAttributeIn("regularEnum", Arrays.asList(RegularEnum.VALUE_01, RegularEnum.VALUE_02));

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isAcceptingEnumWithInClauseUsingJoin() {
        if (isBatoo()) {
            return;
        }

        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enumList", Arrays.asList(RegularEnum.VALUE_01));

        final String query = "select r from RegularEntityTwo r join r.regularEntityOne r1 where r1.regularEnum in :enumList ";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class, paramMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andAttributeIn("regularEntityOne.regularEnum", Arrays.asList(RegularEnum.VALUE_01));

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isAcceptingEnumWithNotInClause() {
        if (isBatoo()) {
            return;
        }

        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enumList", Arrays.asList(RegularEnum.VALUE_01));

        final String query = "select r1 from RegularEntityOne r1 where r1.regularEnum not in :enumList ";
        final List<RegularEntityOne> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityOne.class, paramMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andAttributeNotIn("regularEnum", Arrays.asList(RegularEnum.VALUE_01));

        final List<RegularEntityOne> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isAcceptingEnumWithNotInClauseUsingJoin() {
        if (isBatoo()) {
            return;
        }

        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enumList", Arrays.asList(RegularEnum.VALUE_01));

        final String query = "select r from RegularEntityTwo r join r.regularEntityOne r1 where r1.regularEnum not in :enumList ";
        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class, paramMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andAttributeNotIn("regularEntityOne.regularEnum", Arrays.asList(RegularEnum.VALUE_01));

        final List<RegularEntityTwo> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }
}