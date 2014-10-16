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
import com.uaihebert.model.test.Person;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MethodsNullEmptyTest extends AbstractTest {

    @Test
    public void isNullWorking() {
        final EasyCriteria easyCTO1 = EasyCriteriaFactory.createEasyCTO();
        easyCTO1.andIsNull("nickName");

        final EasyCriteria<Person> easyViewCTO1 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO1);

        final EasyCriteria<Person> personCriteria1 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria1.andIsNull("nickName");

        assertTrue(easyViewCTO1.getResultList().size() == personCriteria1.getResultList().size());
        assertTrue(easyViewCTO1.getResultList().containsAll(personCriteria1.getResultList()));

        final EasyCriteria<Person> personCriteria2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria2.andIsNull("nickName");

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.andIsNull("nickName");

        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);

        assertTrue(easyViewCTO2.getResultList().size() == personCriteria2.getResultList().size());
        assertTrue(easyViewCTO2.getResultList().containsAll(personCriteria2.getResultList()));

        final EasyCriteria easyCTO3 = EasyCriteriaFactory.createEasyCTO();
        easyCTO3.andIsNull("shoesInCloset");

        final EasyCriteria<Person> easyViewCTO3 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO3);

        final EasyCriteria<Person> personCriteria3 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria3.andIsNull("shoesInCloset");

        assertTrue(easyViewCTO3.getResultList().size() == personCriteria3.getResultList().size());
        assertTrue(easyViewCTO3.getResultList().containsAll(personCriteria3.getResultList()));

        final EasyCriteria easyCTO4 = EasyCriteriaFactory.createEasyCTO();
        easyCTO4.andIsNull("car");

        final EasyCriteria<Person> easyViewCTO4 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO4);

        final EasyCriteria<Person> personCriteria4 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria4.andIsNull("car");

        assertTrue(easyViewCTO4.getResultList().size() == personCriteria4.getResultList().size());
        assertTrue(easyViewCTO4.getResultList().containsAll(personCriteria4.getResultList()));
    }

    @Test
    public void isNotNullWorking() {
        final EasyCriteria easyCTO1 = EasyCriteriaFactory.createEasyCTO();
        easyCTO1.andIsNotNull("nickName");

        final EasyCriteria<Person> easyViewCTO1 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO1);

        final EasyCriteria<Person> personCriteria1 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria1.andIsNotNull("nickName");

        assertTrue(easyViewCTO1.getResultList().size() == personCriteria1.getResultList().size());
        assertTrue(easyViewCTO1.getResultList().containsAll(personCriteria1.getResultList()));

        final EasyCriteria<Person> personCriteria2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria2.andIsNotNull("nickName");

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.andIsNotNull("nickName");

        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);

        assertTrue(easyViewCTO2.getResultList().size() == personCriteria2.getResultList().size());
        assertTrue(easyViewCTO2.getResultList().containsAll(personCriteria2.getResultList()));

        final EasyCriteria easyCTO3 = EasyCriteriaFactory.createEasyCTO();
        easyCTO3.andIsNotNull("shoesInCloset");

        final EasyCriteria<Person> easyViewCTO3 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO3);

        final EasyCriteria<Person> personCriteria3 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria3.andIsNotNull("shoesInCloset");

        assertTrue(easyViewCTO3.getResultList().size() == personCriteria3.getResultList().size());
        assertTrue(easyViewCTO3.getResultList().containsAll(personCriteria3.getResultList()));

        final EasyCriteria easyCTO4 = EasyCriteriaFactory.createEasyCTO();
        easyCTO4.andIsNotNull("car");

        final EasyCriteria<Person> easyViewCTO4 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO4);

        final EasyCriteria<Person> personCriteria4 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria4.andIsNotNull("car");

        assertTrue(easyViewCTO4.getResultList().size() == personCriteria4.getResultList().size());
        assertTrue(easyViewCTO4.getResultList().containsAll(personCriteria4.getResultList()));
    }

}
