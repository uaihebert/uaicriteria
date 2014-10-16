package com.uaihebert.test.cto;

import com.uaihebert.model.test.RegularEntityFive;
import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

public class MemberOfCTOTest extends AbstractTest {

    @Test
    public void isMemberOfWithEntityWorking() {
        if (isBatoo()) {
            return;
        }

        final RegularEntityOne regularEntityOne = new RegularEntityOne();
        regularEntityOne.setId(1);

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andIsMemberOf(regularEntityOne, "regularEntityOneList");

        final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andIsMemberOf(regularEntityOne, "regularEntityOneList");

        final UaiCriteria<RegularEntityFive> uaiCriteriaCTO = createCriteria(RegularEntityFive.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isNotMemberOfWithEntityWorking() {
        if (isBatoo() || isEclipselink()) {
            return;
        }

        final RegularEntityOne regularEntityOne = new RegularEntityOne();
        regularEntityOne.setId(1);

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andIsNotMemberOf(regularEntityOne, "regularEntityOneList");

        final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andIsNotMemberOf(regularEntityOne, "regularEntityOneList");

        final UaiCriteria<RegularEntityFive> uaiCriteriaCTO = createCriteria(RegularEntityFive.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMemberOfWithStringWorking() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andIsMemberOf("VALUE_01", "stringList");

        final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andIsMemberOf("VALUE_01", "stringList");

        final UaiCriteria<RegularEntityFive> uaiCriteriaCTO = createCriteria(RegularEntityFive.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isNotMemberOfWithStringWorking() {
        if (isBatoo() || isEclipselink()) {
            return;
        }

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andIsNotMemberOf("VALUE_01", "stringList");

        final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andIsNotMemberOf("VALUE_01", "stringList");

        final UaiCriteria<RegularEntityFive> uaiCriteriaCTO = createCriteria(RegularEntityFive.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}
