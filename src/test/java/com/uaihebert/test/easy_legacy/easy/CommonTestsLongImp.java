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
package com.uaihebert.test.easy_legacy.easy;

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

public class CommonTestsLongImp extends AbstractTest implements CommonTests {

    @Test
    public void isAddingOneOrEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.socialSecurityNumber = 1928371 or p.socialSecurityNumber = 98723487", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("socialSecurityNumber", 1928371L, 98723487L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.totalBooksOwned > 20", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andGreaterThan("totalBooksOwned", 20L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.totalBooksOwned >= 20", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andGreaterOrEqualTo("totalBooksOwned", 20L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.totalBooksOwned < 30", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andLessThan("totalBooksOwned", 30L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.totalBooksOwned <= 20", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andLessOrEqualTo("totalBooksOwned", 20L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isBetweenWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.totalBooksOwned between 19 and 31", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andBetween("totalBooksOwned", 19L, 31L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.dogs d where d.fleasTotal > 5", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        // Workaround for OpenJPA
        if (isOpenJPA()) {
            easyCriteria.setDistinctTrue();
        }

        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinGreaterThan("dogs", "fleasTotal", 5L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.fleasTotal >= 5", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinGreaterOrEqualTo("dogs", "fleasTotal", 5L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.fleasTotal < 13", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinLessThan("dogs", "fleasTotal", 13L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.fleasTotal <= 13", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinLessOrEqualTo("dogs", "fleasTotal", 13L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinBetweenWorking() {
        final List<Dog> personsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.totalBooksOwned between 19 and 31", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinBetween("person", "totalBooksOwned", 19L, 31L);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justLong > 1";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterThan("products.nickNames.justLong", 1L);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justLong >= 1";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterOrEqualTo("products.nickNames.justLong", 1L);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justLong < 2";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessThan("products.nickNames.justLong", 2L);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justLong <= 2";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessOrEqualTo("products.nickNames.justLong", 2L);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justLong between 1 and 2";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andBetween("products.nickNames.justLong", 1L, 2L);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justLong = 1 or n.justLong = 2";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.justLong", 1L, 2L);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isAddingOneWhereEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.socialSecurityNumber = 1928371", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("socialSecurityNumber", 1928371L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.totalBooksOwned = 20 and p.socialSecurityNumber = 1928371", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andEquals("totalBooksOwned", 20L).andEquals("socialSecurityNumber", 1928371L);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

}