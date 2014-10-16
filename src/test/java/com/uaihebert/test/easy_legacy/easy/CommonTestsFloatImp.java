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

public class CommonTestsFloatImp extends AbstractTest implements CommonTests {

    @Test
    public void isAddingOneOrEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight = 10 or p.weight = 11", Person.class);
        assertTrue(personsFromJPQL.size() > 1);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("weight", 10f, 11f);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight >= 11.00f", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andGreaterOrEqualTo("weight", 11.00f);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight < 11.00f", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andLessThan("weight", 11.00f);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight <= 10.50f", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andLessOrEqualTo("weight", 10.50f);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight > 10.50f", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andGreaterThan("weight", 10.50f);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingOneWhereEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight = 10", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("weight", 10f);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }


    public void isAddingSequentialWhereEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight = 5 and p.height = 11", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andEquals("weight", 5d).andEquals("height", 11d);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isBetweenWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight between 9 and 12", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andBetween("weight", 9f, 12f);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.hairSize > 5", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinGreaterThan("dogs", "hairSize", 5.00f);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.hairSize >= 5", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinGreaterOrEqualTo("dogs", "hairSize", 5.00f);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.hairSize < 13", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinLessThan("dogs", "hairSize", 13f);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.hairSize <= 13", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinLessOrEqualTo("dogs", "hairSize", 13f);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinBetweenWorking() {
        final List<Dog> personsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.weight between 9 and 12", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinBetween("person", "weight", 9f, 12f);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justFloat > 1F";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterThan("products.nickNames.justFloat", 1F);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justFloat >= 1F";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterOrEqualTo("products.nickNames.justFloat", 1F);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justFloat < 2F";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessThan("products.nickNames.justFloat", 2F);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justFloat <= 2F";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessOrEqualTo("products.nickNames.justFloat", 2F);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justFloat between 1f and 2f";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andBetween("products.nickNames.justFloat", 1f, 2f);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justFloat = 1f or n.justFloat = 2f";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.justFloat", 1f, 2f);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }
}
