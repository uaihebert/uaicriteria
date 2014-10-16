package com.uaihebert.test.cto;

import com.uaihebert.model.test.RegularEntityFour;
import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

public class MethodsCaseCTOTest extends AbstractTest {
    private static final String VALUE_STRING_01 = "Just a String 01";
    private static final String VALUE_STRING_02 = "Just a String 02";

    @Test
    public void isAndEqualsWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals(true, "stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals(true, "stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAndNotEqualsWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.andNotEquals(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andNotEquals(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isOrEqualsWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("stringAttribute", VALUE_STRING_01).orEquals(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orEquals("stringAttribute", VALUE_STRING_01).orEquals(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isOrEqualsWithIndexWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.orEquals(true, 0, "stringAttribute", VALUE_STRING_02).orEquals(0, "id", 33);
        uaiCriteria.orEquals(1, "stringAttribute", VALUE_STRING_01).orEquals(1, "id", 2L);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orEquals(true, 0, "stringAttribute", VALUE_STRING_02).orEquals(0, "id", 33);
        cto.orEquals(1, "stringAttribute", VALUE_STRING_01).orEquals(1, "id", 2L);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isNotEqualsWithLowerCaseWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.orNotEquals(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orNotEquals(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void testIsGreaterThanWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.andGreaterThan(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andGreaterThan(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isGreaterThanOrEqualToWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.andGreaterOrEqualTo(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andGreaterOrEqualTo(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLessThanWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.andLessThan(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andLessThan(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLessOrEqualToWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.andLessOrEqualTo(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andLessOrEqualTo(true, "stringAttribute", VALUE_STRING_02);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}