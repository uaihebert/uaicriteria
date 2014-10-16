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
package com.uaihebert.test.cto;

import com.uaihebert.model.test.RegularEntityFour;
import com.uaihebert.model.test.RegularEntityThree;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

public class MethodsJoinInnerCTOTest extends AbstractTest {
    private static final String VALUE_STRING_01 = "Just a String 01";

    @Test
    public void isMultipleJoin1LevelWorking() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.innerJoin("regularEntityOne");
        uaiCriteria.andEquals("regularEntityOne.stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityOne");
        cto.andEquals("regularEntityOne.stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoin2LevelWorking() {
        final UaiCriteria<RegularEntityThree> uaiCriteria = createCriteria(RegularEntityThree.class);
        uaiCriteria.innerJoin("regularEntityTwo.regularEntityOne");
        uaiCriteria.andEquals("regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityThree> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityTwo.regularEntityOne");
        cto.andEquals("regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityThree> uaiCriteriaCTO = createCriteria(RegularEntityThree.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoin3LevelWorking() {
        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andEquals("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.innerJoin("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andEquals("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}