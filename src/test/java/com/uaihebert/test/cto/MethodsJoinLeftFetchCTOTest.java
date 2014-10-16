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

// some providers do not support nested joins fetch in JPQL
// http://www.coderanch.com/t/570828/ORM/databases/Recursive-fetch-join-recursively-fetching
public class MethodsJoinLeftFetchCTOTest extends AbstractTest {
    private static final String VALUE_STRING_01 = "Just a String 01";

    @Test
    public void isMultipleJoin1LevelWorking() {
        if (isBatoo()) {
            // some providers do not support nested joins fetch in JPQL
            // http://www.coderanch.com/t/570828/ORM/databases/Recursive-fetch-join-recursively-fetching
            return;
        }

        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);
        uaiCriteria.leftJoinFetch("regularEntityOne");
        uaiCriteria.andEquals("regularEntityOne.stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityTwo> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.leftJoinFetch("regularEntityOne");
        cto.andEquals("regularEntityOne.stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityTwo> uaiCriteriaCTO = createCriteria(RegularEntityTwo.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoin2LevelWorking() {
        if (isBatoo()) {
            // some providers do not support nested joins fetch in JPQL
            // http://www.coderanch.com/t/570828/ORM/databases/Recursive-fetch-join-recursively-fetching
            return;
        }

        final UaiCriteria<RegularEntityThree> uaiCriteria = createCriteria(RegularEntityThree.class);
        uaiCriteria.leftJoinFetch("regularEntityTwo.regularEntityOne");
        uaiCriteria.andEquals("regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityThree> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.leftJoinFetch("regularEntityTwo.regularEntityOne");
        cto.andEquals("regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityThree> uaiCriteriaCTO = createCriteria(RegularEntityThree.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isMultipleJoin3LevelWorking() {
        if (isBatoo()) {
            // some providers do not support nested joins fetch in JPQL
            // http://www.coderanch.com/t/570828/ORM/databases/Recursive-fetch-join-recursively-fetching
            return;
        }

        final UaiCriteria<RegularEntityFour> uaiCriteria = createCriteria(RegularEntityFour.class);
        uaiCriteria.leftJoinFetch("regularEntityThree.regularEntityTwo.regularEntityOne");
        uaiCriteria.andEquals("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityFour> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.leftJoinFetch("regularEntityThree.regularEntityTwo.regularEntityOne");
        cto.andEquals("regularEntityThree.regularEntityTwo.regularEntityOne.stringAttribute", VALUE_STRING_01);

        final UaiCriteria<RegularEntityFour> uaiCriteriaCTO = createCriteria(RegularEntityFour.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}