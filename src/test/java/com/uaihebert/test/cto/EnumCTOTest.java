package com.uaihebert.test.cto;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.model.test.RegularEnum;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

import java.util.Arrays;

public class EnumCTOTest extends AbstractTest {

    @Test
    public void isNullWorkingWithEnum() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andIsNull("regularEnum");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andIsNull("regularEnum");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isNotNullWorkingWithEnum() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andIsNotNull("regularEnum");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andIsNotNull("regularEnum");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isNullWithJoinWorkingWithEnum() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andIsNull("regularEntityOne.regularEnum");

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andIsNull("regularEntityOne.regularEnum");

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isNotNullWithJoinWorkingWithEnum() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andIsNotNull("regularEntityOne.regularEnum");

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andIsNotNull("regularEntityOne.regularEnum");

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAcceptingEnumWithInClause() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andAttributeIn("regularEnum", Arrays.asList(RegularEnum.VALUE_01, RegularEnum.VALUE_02));

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andAttributeIn("regularEnum", Arrays.asList(RegularEnum.VALUE_01, RegularEnum.VALUE_02));

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAcceptingEnumWithInClauseUsingJoin() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andAttributeIn("regularEntityOne.regularEnum", Arrays.asList(RegularEnum.VALUE_01));

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andAttributeIn("regularEntityOne.regularEnum", Arrays.asList(RegularEnum.VALUE_01));

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAcceptingEnumWithNotInClause() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createCriteria(RegularEntityOne.class);
        uaiCriteria.andAttributeNotIn("regularEnum", Arrays.asList(RegularEnum.VALUE_01));

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andAttributeNotIn("regularEnum", Arrays.asList(RegularEnum.VALUE_01));

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createCriteria(RegularEntityOne.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isAcceptingEnumWithNotInClauseUsingJoin() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andAttributeNotIn("regularEntityOne.regularEnum", Arrays.asList(RegularEnum.VALUE_01));

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andAttributeNotIn("regularEntityOne.regularEnum", Arrays.asList(RegularEnum.VALUE_01));

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}