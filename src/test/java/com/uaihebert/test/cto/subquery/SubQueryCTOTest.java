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
package com.uaihebert.test.cto.subquery;

import com.uaihebert.model.test.FakeUaiCriteriaCTOImp;
import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

public class SubQueryCTOTest extends AbstractTest {

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfSubQueryIsInvoked() {
        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.subQuery("", Object.class);

    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfAttributeInWithSubQueryIsInvoked() {
        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andAttributeIn("", new FakeUaiCriteriaCTOImp());
    }

    @Test(expected = IllegalStateException.class)
    public void isRaisingExceptionIfAttributeInWithSubQueryIsInvokedAttributeNotIn() {
        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.andAttributeNotIn("", new FakeUaiCriteriaCTOImp());
    }
}
