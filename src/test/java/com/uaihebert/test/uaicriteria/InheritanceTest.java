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
package com.uaihebert.test.uaicriteria;

import com.uaihebert.model.test.InheritanceFamilyOneChildEntity;
import com.uaihebert.model.test.InheritanceFamilyOneEntity;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class InheritanceTest extends AbstractTest {

    @Test
    public void isFindingAttributeInRootClass() {
        final String query = "select r from InheritanceFamilyOneEntity r where r.rootString = 'InheritanceFamilyOneEntity 01'";
        final List<InheritanceFamilyOneEntity> resultFromJPQL = jpqlHelper.getListFromJPQL(query, InheritanceFamilyOneEntity.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<InheritanceFamilyOneEntity> uaiCriteria = createCriteria(InheritanceFamilyOneEntity.class);
        uaiCriteria.andEquals("rootString", "InheritanceFamilyOneEntity 01");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }

    @Test
    public void isFindingAttributeInRootClassFromTwoLevelChildEntity() {
        final String query = "select r from InheritanceFamilyOneChildEntity r where r.rootString = 'InheritanceFamilyOneEntity 06'";
        final List<InheritanceFamilyOneChildEntity> resultFromJPQL = jpqlHelper.getListFromJPQL(query, InheritanceFamilyOneChildEntity.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<InheritanceFamilyOneChildEntity> uaiCriteria = createCriteria(InheritanceFamilyOneChildEntity.class);
        uaiCriteria.andEquals("rootString", "InheritanceFamilyOneEntity 06");

        validateTestLists(resultFromJPQL, uaiCriteria.getResultList());
    }
}
