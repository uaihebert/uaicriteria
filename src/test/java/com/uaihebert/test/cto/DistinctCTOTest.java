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

public class DistinctCTOTest extends AbstractTest {

    @Test
    public void assertThatDatabaseHasRepeatedResults() {
        if (isBatoo()) {
            // batoo is doing some kind of distinct in the query result,
            // but it is possible to find the 'distinct' clause in the created query
            return;
        }

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.leftJoin("regularEntityOneList");

        final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.leftJoin("regularEntityOneList");

        final UaiCriteria<RegularEntityFive> uaiCriteriaCTO = createCriteria(RegularEntityFive.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }

    @Test
    public void isDistinctWorking() {
        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.leftJoin("regularEntityOneList");
        uaiCriteria.setDistinctTrue();

        final UaiCriteria<RegularEntityFive> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.leftJoin("regularEntityOneList");
        cto.setDistinctTrue();

        final UaiCriteria<RegularEntityFive> uaiCriteriaCTO = createCriteria(RegularEntityFive.class, cto);

        validateTestLists(uaiCriteria.getResultList(), uaiCriteriaCTO.getResultList());
    }
}