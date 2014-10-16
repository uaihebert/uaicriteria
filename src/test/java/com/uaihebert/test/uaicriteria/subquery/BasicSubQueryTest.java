package com.uaihebert.test.uaicriteria.subquery;

import com.uaihebert.model.test.RegularEntityFive;
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

import static org.junit.Assert.assertTrue;

public class BasicSubQueryTest extends AbstractTest {

    @Test
    public void isSubQueryWithLongWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (select sub.id from RegularEntityOne sub where sub.stringAttribute = 'Just a String 02')";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andEquals("stringAttribute", "Just a String 02");

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isSubQueryNotInWithLongWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id not in (select sub.id from RegularEntityOne sub where sub.stringAttribute = 'Just a String 02')";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andEquals("stringAttribute", "Just a String 02");

        uaiCriteria.andAttributeNotIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isSubQueryWithListWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityFive r inner join r.regularEntityOneList rl" +
                " where rl.id in (select sub.id from RegularEntityOne sub where sub.stringAttribute = 'Just a String 02')";

        final List<RegularEntityFive> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFive.class);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.innerJoin("regularEntityOneList");

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andEquals("stringAttribute", "Just a String 02");

        uaiCriteria.andAttributeIn("regularEntityOneList.id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isSubQueryWithStringWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.stringAttribute in (select sub.stringAttribute from RegularEntityOne sub where sub.id = 1)";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("stringAttribute", RegularEntityOne.class);
        subQuery.andEquals("id", 1l);

        uaiCriteria.andAttributeIn("stringAttribute", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isSubQueryWithDoubleWithJoinWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r join r.regularEntityOne one where one.doubleAttributeOne in " +
                "(select sub.doubleAttributeOne from RegularEntityOne sub where sub.stringAttribute = 'Just a String 02')";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("doubleAttributeOne", RegularEntityOne.class);
        subQuery.andEquals("stringAttribute", "Just a String 02");

        uaiCriteria.andAttributeIn("regularEntityOne.doubleAttributeOne", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isSubQueryWithBigDecimalWithJoinWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r join r.regularEntityOne one where one.doubleAttributeOne in " +
                "(select sub.bigDecimalAttributeOne from RegularEntityOne sub where sub.stringAttribute = 'Just a String 02')";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("bigDecimalAttributeOne", RegularEntityOne.class);
        subQuery.andEquals("stringAttribute", "Just a String 02");

        uaiCriteria.andAttributeIn("regularEntityOne.bigDecimalAttributeOne", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isSubQueryWithLongWithJoinWorking() {
        if (isBatoo()) {
            return;
        }

        final String query = "select r from RegularEntityTwo r join r.regularEntityOne one where one.id in " +
                "(select sub.id from RegularEntityOne sub where sub.stringAttribute = 'Just a String 02')";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class);
        assertTrue(resultFromJPQL.size() == 1);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andEquals("stringAttribute", "Just a String 02");

        uaiCriteria.andAttributeIn("regularEntityOne.id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isAttributeInWorking() {
        if (isBatoo()) {
            return;
        }

        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enumList", Arrays.asList(RegularEnum.VALUE_01, RegularEnum.VALUE_02));

        final String subQueryJPQL;

        if (isEclipselink()) {
            subQueryJPQL = "select r1.id from RegularEntityOne r1 where r1.regularEnum in :enumList";
        } else {
            subQueryJPQL = "select r1.id from RegularEntityOne r1 where r1.regularEnum in (:enumList)";
        }

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class, paramMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andAttributeIn("regularEnum", Arrays.asList(RegularEnum.VALUE_01, RegularEnum.VALUE_02));

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isAttributeNotInWorking() {
        if (isBatoo()) {
            return;
        }

        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enumList", Arrays.asList(RegularEnum.VALUE_01));

        final String subQueryJPQL = "select r1.id from RegularEntityOne r1 where r1.regularEnum not in :enumList";

        final String query = "select r from RegularEntityTwo r " +
                " where r.id in (" + subQueryJPQL + ")";

        final List<RegularEntityTwo> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityTwo.class, paramMap);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);
        subQuery.andAttributeNotIn("regularEnum", Arrays.asList(RegularEnum.VALUE_01));

        uaiCriteria.andAttributeIn("id", subQuery);

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }
}