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
import com.uaihebert.model.test.Dog;
import com.uaihebert.model.test.Manufacturer;
import com.uaihebert.model.test.Person;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.test.easy_legacy.CommonTests;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommonCTOTestsLongImp extends AbstractTest implements CommonTests {

    @Test
    public void isAddingOneWhereEquals() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("socialSecurityNumber", 1928371L);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("socialSecurityNumber", 1928371L);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("totalBooksOwned", 20L).andEquals("socialSecurityNumber", 1928371L);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("totalBooksOwned", 20L).andEquals("socialSecurityNumber", 1928371L);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));

    }

    @Test
    public void isAddingOneOrEquals() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orEquals("socialSecurityNumber", 1928371L, 98723487L);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("socialSecurityNumber", 1928371L, 98723487L);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isJoinBetweenWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("person");
        easyCTO.andJoinBetween("person", "totalBooksOwned", 19L, 31L);

        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinBetween("person", "totalBooksOwned", 19L, 31L);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(easyViewCTO.getResultList().size(), easyCriteriaResult.size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("dogs");
        easyCTO.andJoinGreaterThan("dogs", "fleasTotal", 5L);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinGreaterThan("dogs", "fleasTotal", 5L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(easyViewCTO.getResultList().size(), easyCriteriaResult.size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("dogs");
        easyCTO.andJoinGreaterOrEqualTo("dogs", "fleasTotal", 5L);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinGreaterOrEqualTo("dogs", "fleasTotal", 5L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(easyViewCTO.getResultList().size(), easyCriteriaResult.size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessThanWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("dogs");
        easyCTO.andJoinLessThan("dogs", "fleasTotal", 13L);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinLessThan("dogs", "fleasTotal", 13L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(easyViewCTO.getResultList().size(), easyCriteriaResult.size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("dogs");
        easyCTO.andJoinLessOrEqualTo("dogs", "fleasTotal", 13L);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinLessOrEqualTo("dogs", "fleasTotal", 13L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(easyViewCTO.getResultList().size(), easyCriteriaResult.size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteriaResult));
    }

    @Test
    public void isBetweenWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andBetween("totalBooksOwned", 19L, 31L);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andBetween("totalBooksOwned", 19L, 31L);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isGreaterThanWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andGreaterThan("totalBooksOwned", 20L);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andGreaterThan("totalBooksOwned", 20L);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andGreaterOrEqualTo("totalBooksOwned", 20L);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andGreaterOrEqualTo("totalBooksOwned", 20L);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isLessThanWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andLessThan("totalBooksOwned", 30L);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andLessThan("totalBooksOwned", 30L);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isLessOrEqualToWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andLessOrEqualTo("totalBooksOwned", 20L);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andLessOrEqualTo("totalBooksOwned", 20L);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andGreaterThan("products.nickNames.justLong", 1L);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterThan("products.nickNames.justLong", 1L);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andGreaterOrEqualTo("products.nickNames.justLong", 1L);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterOrEqualTo("products.nickNames.justLong", 1L);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andLessThan("products.nickNames.justLong", 2L);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessThan("products.nickNames.justLong", 2L);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andLessOrEqualTo("products.nickNames.justLong", 2L);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessOrEqualTo("products.nickNames.justLong", 2L);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andBetween("products.nickNames.justLong", 1L, 2L);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andBetween("products.nickNames.justLong", 1L, 2L);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.orEquals("products.nickNames.justLong", 1L, 2L);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.justLong", 1L, 2L);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }
}