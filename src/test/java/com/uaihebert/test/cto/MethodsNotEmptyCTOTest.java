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

import com.uaihebert.model.test.RegularEntityFive;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

// There is a problem with Batoo and this kind of criteria. Check here for more details: http://stackoverflow.com/questions/24942520/how-to-use-criteria-isempty-with-batto
public class MethodsNotEmptyCTOTest extends AbstractTest {

    @Test
    public void isEmptyWithListWorking() {
        if (isBatoo()) {
            // batoo is returning 0 when working with not empty.
            return;
        }

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andCollectionIsNotEmpty("regularEntityOneList");

        final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andCollectionIsNotEmpty("regularEntityOneList");

        final UaiCriteria<RegularEntityFive> uaiCriteriaCTO = createCriteria(RegularEntityFive.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isEmptyWithSetWorking() {
        if (isBatoo()) {
            // batoo is returning 0 when working with not empty.
            return;
        }

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andCollectionIsNotEmpty("regularEntityTwoSet");

        final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andCollectionIsNotEmpty("regularEntityTwoSet");

        final UaiCriteria<RegularEntityFive> uaiCriteriaCTO = createCriteria(RegularEntityFive.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isEmptyWithCollectionWorking() {
        if (isBatoo()) {
            // batoo is returning 0 when working with not empty.
            return;
        }

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andCollectionIsNotEmpty("regularEntityThreeCollection");

        final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andCollectionIsNotEmpty("regularEntityThreeCollection");

        final UaiCriteria<RegularEntityFive> uaiCriteriaCTO = createCriteria(RegularEntityFive.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}