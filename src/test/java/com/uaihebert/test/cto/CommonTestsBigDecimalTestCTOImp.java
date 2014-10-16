package com.uaihebert.test.cto;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.test.easy_legacy.CommonTests;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

import java.math.BigDecimal;

public class CommonTestsBigDecimalTestCTOImp extends AbstractTest implements CommonTests {
    private static final BigDecimal VALUE_01 = new BigDecimal(1);
    private static final BigDecimal VALUE_04 = new BigDecimal(4);
    private static final BigDecimal VALUE_23 = new BigDecimal(23);
    private static final BigDecimal VALUE_32 = new BigDecimal(32);
    private static final BigDecimal VALUE_33 = new BigDecimal(33);
    private static final BigDecimal VALUE_34 = new BigDecimal(34);

    @Test
    public void isAddingOneOrEquals() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.orEquals("bigDecimalAttributeOne", VALUE_33).orEquals("bigDecimalAttributeOne", VALUE_23);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.orEquals("bigDecimalAttributeOne", VALUE_33).orEquals("bigDecimalAttributeOne", VALUE_23);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingOneWhereEquals() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andEquals("bigDecimalAttributeOne", VALUE_33).andEquals("booleanAttributeOne", true);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andEquals("bigDecimalAttributeOne", VALUE_33).andEquals("booleanAttributeOne", true);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isBetweenWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andBetween("bigDecimalAttributeOne", VALUE_23, VALUE_34);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andBetween("bigDecimalAttributeOne", VALUE_23, VALUE_34);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andGreaterOrEqualTo("bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andGreaterOrEqualTo("bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLessThanWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andLessThan("bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andLessThan("bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isLessOrEqualToWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andLessOrEqualTo("bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andLessOrEqualTo("bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isGreaterThanWorking() {
        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andGreaterThan("bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andGreaterThan("bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinBetweenWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andBetween("regularEntityOne.bigDecimalAttributeOne", VALUE_23, VALUE_34);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andBetween("regularEntityOne.bigDecimalAttributeOne", VALUE_23, VALUE_34);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andGreaterOrEqualTo("regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andGreaterOrEqualTo("regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinLessThanWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andLessThan("regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andLessThan("regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andLessThan("regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andLessThan("regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andGreaterThan("regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andGreaterThan("regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityThree");
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andBetween("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_23, VALUE_34);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree");
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andBetween("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_23, VALUE_34);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andGreaterOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andGreaterOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andLessThan("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andLessThan("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andLessOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andLessOrEqualTo("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_33);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andGreaterThan("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_32);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andGreaterThan("regularEntityThree.regularEntityTwo.regularEntityOne.bigDecimalAttributeOne", VALUE_32);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.id", VALUE_01).orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.id", VALUE_04);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.id", VALUE_01).orEquals("regularEntityThree.regularEntityTwo.regularEntityOne.id", VALUE_04);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}