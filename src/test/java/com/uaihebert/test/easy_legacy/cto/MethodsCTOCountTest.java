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
package com.uaihebert.test.easy_legacy.cto;

import com.uaihebert.model.EasyCriteria;
import com.uaihebert.model.test.Manufacturer;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodsCTOCountTest extends AbstractTest {

    @Test
    public void isAbleToDoTheSameCountSeveralTimes() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

        assertTrue(easyViewCTO.getResultList().size() > 0);
        assertTrue(easyCriteria.getResultList().size() > 0);

        assertEquals(easyViewCTO.count(), easyCriteria.count());
        assertEquals(easyViewCTO.count(), easyCriteria.count());
        assertEquals(easyViewCTO.count(), easyCriteria.count());
        assertEquals(easyViewCTO.count(), easyCriteria.count());
        assertEquals(easyViewCTO.count(), easyCriteria.count());
    }

    @Test
    public void isAbleToDoTheSameQueryAndCountSeveralTimes() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

        assertTrue(easyViewCTO.getResultList().size() > 0);
        assertTrue(easyCriteria.getResultList().size() > 0);

        assertEquals(easyViewCTO.getResultList().size(), easyCriteria.getResultList().size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        assertEquals(easyViewCTO.count(), easyCriteria.count());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        assertEquals(easyViewCTO.count(), easyCriteria.count());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        assertEquals(easyViewCTO.count(), easyCriteria.count());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        assertEquals(easyViewCTO.count(), easyCriteria.count());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        assertEquals(easyViewCTO.count(), easyCriteria.count());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        assertEquals(easyViewCTO.count(), easyCriteria.count());
    }

    @Test(expected = IllegalStateException.class)
    public void isCountThrowingException() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.count();
    }

    @Test
    public void isAddingHints() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.addHint("some.hint", "true");

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        assertTrue(easyCriteria.count() > 0);
    }
}