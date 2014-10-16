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

import com.uaihebert.model.test.RegularEntityFive;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// There is a problem with Batoo and this kind of criteria. Check here for more details: http://stackoverflow.com/questions/24942520/how-to-use-criteria-isempty-with-batto
public class MethodsNotEmptyTest extends AbstractTest {

    @Test
    public void isEmptyWithListWorking() {
        if (isBatoo()) {
            // batoo is returning 0 when working with not empty.
            return;
        }

        final String query = "select r from RegularEntityFive r where r.regularEntityOneList is not empty ";

        final List<RegularEntityFive> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFive.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andCollectionIsNotEmpty("regularEntityOneList");

        final List<RegularEntityFive> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isEmptyWithSetWorking() {
        if (isBatoo()) {
            // batoo is returning 0 when working with not empty.
            return;
        }

        final String query = "select r from RegularEntityFive r where r.regularEntityTwoSet is not empty ";

        final List<RegularEntityFive> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFive.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andCollectionIsNotEmpty("regularEntityTwoSet");

        final List<RegularEntityFive> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isEmptyWithCollectionWorking() {
        if (isBatoo()) {
            // batoo is returning 0 when working with not empty.
            return;
        }

        final String query = "select r from RegularEntityFive r where r.regularEntityThreeCollection is not empty ";

        final List<RegularEntityFive> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFive.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);
        uaiCriteria.andCollectionIsNotEmpty("regularEntityThreeCollection");

        final List<RegularEntityFive> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }

    @Test
    public void isEmptyWithMapWorking() {
        if (isBatoo() || isHibernate()) {
            return;
        }

        final String query = "select r from RegularEntityFive r where r.stringMap is not empty ";

        final List<RegularEntityFive> resultFromJPQL = jpqlHelper.getListFromJPQL(query, RegularEntityFive.class);
        assertTrue(resultFromJPQL.size() > 0);

        final UaiCriteria<RegularEntityFive> uaiCriteria = createCriteria(RegularEntityFive.class);

        try {
            uaiCriteria.andCollectionIsNotEmpty("stringMap");
        } catch (final RuntimeException ex) {
            if (isBatoo()) {
                return;
            }

            throw ex;
        }

        final List<RegularEntityFive> criteriaList = uaiCriteria.getResultList();

        assertEquals(resultFromJPQL.size(), criteriaList.size());
        assertTrue(resultFromJPQL.containsAll(criteriaList));
    }
}