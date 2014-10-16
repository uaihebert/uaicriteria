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

public class CommonTestsIntegerImp extends AbstractTest implements CommonTests {

    @Test
    public void isAddingOneOrEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.clothesInCloset = 44 or p.clothesInCloset = 33", Person.class);
        assertTrue(personsFromJPQL.size() > 1);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("clothesInCloset", 44, 33);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));

    }

    @Test
    public void isAddingOneWhereEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.clothesInCloset = 44", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("clothesInCloset", 44);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));

    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset = 10 and p.clothesInCloset = 33", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("shoesInCloset", 10).andEquals("clothesInCloset", 33);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isBetweenWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset between 9 and 21", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andBetween("shoesInCloset", 9, 21);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset >= 10", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andGreaterOrEqualTo("shoesInCloset", 10);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset < 20", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andLessThan("shoesInCloset", 20);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset <= 20", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andLessOrEqualTo("shoesInCloset", 20);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset > 10", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andGreaterThan("shoesInCloset", 10);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinBetweenWorking() {
        final List<Dog> personsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.clothesInCloset between 30 and 35", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinBetween("person", "clothesInCloset", 30, 35);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.toysTotal >= 5", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinGreaterOrEqualTo("dogs", "toysTotal", 5);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.toysTotal < 13", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinLessThan("dogs", "toysTotal", 13);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.toysTotal <= 13", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinLessOrEqualTo("dogs", "toysTotal", 13);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.dogs d where d.toysTotal > 5", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        // Workaround for OpenJPA
        if (isOpenJPA()) {
            easyCriteria.setDistinctTrue();
        }

        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinGreaterThan("dogs", "toysTotal", 5);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.id between 1 and 2";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andBetween("products.nickNames.id", 1, 2);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.id >= 1";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterOrEqualTo("products.nickNames.id", 1);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.id < 2";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessThan("products.nickNames.id", 2);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.id <= 2";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessOrEqualTo("products.nickNames.id", 2);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.id > 1";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterThan("products.nickNames.id", 1);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.id = 1 or n.id = 2";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.id", 1, 2);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));

    }
}