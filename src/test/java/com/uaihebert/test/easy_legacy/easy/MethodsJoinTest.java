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
import com.uaihebert.model.test.Product;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MethodsJoinTest extends AbstractTest {

    @Test
    public void isWhereInnerJoinWorking() {
        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d inner join d.person p where p.name = 'John'", Dog.class);
        assertTrue(dogsFromJPQL.size() > 0);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinEquals("person", "name", "John");

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isMultipleJoin1LevelWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.car ca join ca.color co", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car.color");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoin2LevelsWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.car ca join ca.color co join co.manufacturer nanu", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car.color.manufacturer");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoin3LevelsWorking() {
        final String query = "select p from Person p " +
                "join p.car ca " +
                "join ca.color co " +
                "join co.manufacturer manu " +
                "join manu.products pro";
        final List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car.color.manufacturer.products");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoin4LevelsWorking() {
        final String query = "select p from Person p " +
                "join p.car ca " +
                "join ca.color co " +
                "join co.manufacturer manu " +
                "join manu.products pro " +
                "join pro.nickNames ni  ";
        final List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car.color.manufacturer.products.nickNames");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinFetch1LevelWorking() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoinFetch("car.color");

        final List<Person> result = easyCriteria.getResultList();
        assertTrue(result.size() > 0);
    }

    @Test
    public void isMultipleJoinFetch2LevelsWorking() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoinFetch("car.color.manufacturer");

        final List<Person> result = easyCriteria.getResultList();
        assertTrue(result.size() > 0);
    }

    @Test
    public void isMultipleJoinFetch3LevelsWorking() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoinFetch("car.color.manufacturer.products");

        final List<Person> result = easyCriteria.getResultList();
        assertTrue(result.size() > 0);
    }

    @Test
    public void isMultipleJoinFetch4LevelsWorking() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoinFetch("car.color.manufacturer.products.nickNames");

        final List<Person> result = easyCriteria.getResultList();
        assertTrue(result.size() > 0);
    }

    @Test
    public void isMultipleLeftJoin1LevelWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p left join p.car ca left join ca.color co", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("car.color");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoin2LevelsWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p left join p.car ca left join ca.color co left join co.manufacturer nanu", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("car.color.manufacturer");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoin3LevelsWorking() {
        final String query = "select p from Person p " +
                "left join p.car ca " +
                "left join ca.color co " +
                "left join co.manufacturer manu " +
                "left join manu.products pro";
        final List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("car.color.manufacturer.products");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoin4LevelsWorking() {
        final String query = "select distinct p from Person p " +
                "left join p.car ca " +
                "left join ca.color co " +
                "left join co.manufacturer manu " +
                "left join manu.products pro " +
                "left join pro.nickNames ni  ";
        final List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.leftJoin("car");
        easyCriteria.leftJoin("car.color");
        easyCriteria.leftJoin("car.color.manufacturer");
        easyCriteria.leftJoin("car.color.manufacturer.products");
        easyCriteria.leftJoin("car.color.manufacturer.products.nickNames");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleMixedJoin4LevelsWorking() {
        final String query = "select distinct p from Person p " +
                "inner join p.car ca " +
                "left join ca.color co " +
                "left join co.manufacturer manu " +
                "left join manu.products pro " +
                "left join pro.nickNames ni  ";
        final List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("car");
        easyCriteria.leftJoin("car.color");
        easyCriteria.leftJoin("car.color.manufacturer");
        easyCriteria.leftJoin("car.color.manufacturer.products");
        easyCriteria.leftJoin("car.color.manufacturer.products.nickNames");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoinFetch1LevelWorking() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("car.color");

        final List<Person> result = easyCriteria.getResultList();
        assertTrue(result.size() > 0);
    }

    @Test
    public void isMultipleLeftJoinFetch2LevelsWorking() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("car.color.manufacturer");

        final List<Person> result = easyCriteria.getResultList();
        assertTrue(result.size() > 0);
    }

    @Test
    public void isMultipleLeftJoinFetch3LevelsWorking() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("car.color.manufacturer.products");

        final List<Person> result = easyCriteria.getResultList();
        assertTrue(result.size() > 0);
    }

    @Test
    public void isMultipleLeftJoinFetch4LevelsWorking() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("car.color.manufacturer.products.nickNames");

        final List<Person> result = easyCriteria.getResultList();
        assertTrue(result.size() > 0);
    }

    @Test
    public void isMultipleJoinWhereWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.car ca join ca.color co where co.name = 'Red'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car.color");
        easyCriteria.andEquals("car.color.name", "Red");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoinLevel2WhereWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p left join p.car ca join ca.color co where co.name = 'Red'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("car");
        easyCriteria.innerJoin("car.color");
        easyCriteria.andEquals("car.color.name", "Red");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoinLevel3WhereWorking() {
        final String query = "select p from Person p " +
                "left join p.car ca " +
                "left join ca.color co " +
                "left join co.manufacturer manu " +
                "where manu.name = 'Company A'";
        final List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("car.color.manufacturer");
        easyCriteria.andEquals("car.color.manufacturer.name", "Company A");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoinLevel3LowerCaseWhereWorking() {
        final String query = "select p from Person p " +
                "left join p.car ca " +
                "left join ca.color co " +
                "left join co.manufacturer manu " +
                "where lower(manu.name) = 'company b'";
        final List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("car.color.manufacturer");
        easyCriteria.andEquals(true, "car.color.manufacturer.name", "Company B".toLowerCase());

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoinLevel1LowerCaseWhereWorking() {
        final String query = "select p from Person p " +
                "join p.car c " +
                "where lower(c.name) = lower('Dark Horse')";
        final List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car");
        easyCriteria.andEquals(true, "car.name", "Dark Horse".toLowerCase());

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithEqualsInCollectionsWorking() {
        final String query = "select m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name = 'NickName B'";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andEquals("products.nickNames.name", "NickName B");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithEqualsLowerCaseInCollectionsWorking() {
        final String query = "select m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where lower(n.name) = lower('NickName B')";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andEquals(true, "products.nickNames.name", "NickName B".toLowerCase());

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithEqualsBooleanWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justBoolean = true";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andEquals("products.nickNames.justBoolean", true);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithIsNullAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justString is null";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andIsNull("products.nickNames.justString");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithIsNotNullAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justString is not null";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andIsNotNull("products.nickNames.justString");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithListIsEmptyAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justList is empty";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsEmpty("products.nickNames.justList");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithListIsNotEmptyAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justList is not empty";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsNotEmpty("products.nickNames.justList");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithSetIsEmptyAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justSet is empty";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsEmpty("products.nickNames.justSet");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithSetIsNotEmptyAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justSet is not empty";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsNotEmpty("products.nickNames.justSet");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithCollectionIsEmptyAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justCollection is empty";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsEmpty("products.nickNames.justCollection");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithCollectionIsNotEmptyAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justCollection is not empty";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsNotEmpty("products.nickNames.justCollection");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithCollectionTestIsEmptyAttributeWorking() {
        final String query = "select distinct p from Product p " +
                "join p.nickNames n " +
                "join n.justCollection j " +
                "where j.autoRelationship is empty";
        final List<Product> personsFromJPQL = getListFromJPQL(query, Product.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Product> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Product.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("nickNames.justCollection");
        easyCriteria.andCollectionIsEmpty("nickNames.justCollection.autoRelationship");

        final List<Product> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithOrWithAndWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B')";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithOrWithAndLowerCaseWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where (n.id = 1 and lower(n.name) = 'nickname a') or (n.id = 2 and lower(n.name) = 'nickname b')";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(true, 1, "products.nickNames.name", "NickName A".toLowerCase())
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(true, 2, "products.nickNames.name", "NickName B".toLowerCase());

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isWhereLeftJoinWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p left join p.dogs d where d.name = 'Fire'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        // Workaround for OpenJPA
        if (isOpenJPA()) {
            easyCriteria.setDistinctTrue();
        }

        easyCriteria.leftJoin("dogs");
        easyCriteria.andJoinEquals("dogs", "name", "Fire");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isWhereInnerJoinFetchWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.dogs d where d.name = 'Fire'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        if (isOpenJPA()) {
            easyCriteria.setDistinctTrue();
        }

        easyCriteria.innerJoinFetch("dogs");
        easyCriteria.andJoinEquals("dogs", "name", "Fire");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isWhereJoinNotEqualsWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.dogs d where d.name <> 'Fire'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        if (isOpenJPA()) {
            easyCriteria.setDistinctTrue();
        }

        easyCriteria.innerJoinFetch("dogs");
        easyCriteria.andJoinNotEquals("dogs", "name", "Fire");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(easyCriteriaResult.containsAll(personsFromJPQL));
    }

    @Test
    public void isWhereLeftJoinFetchWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p, IN(p.dogs) d  where d.name = 'Dark'", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("dogs");
        easyCriteria.andJoinEquals("dogs", "name", "Dark");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isInnerJoinWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.dogs d", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("dogs");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isInnerJoinWithDistinctWorking() {
        final List<Person> personsFromJPQLWithOutDistinct = getListFromJPQL("select p from Person p join p.dogs d", Person.class);

        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d", Person.class);

        assertTrue(personsFromJPQL.size() > 0);
        assertTrue(personsFromJPQL.size() < personsFromJPQLWithOutDistinct.size());

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("dogs");
        easyCriteria.setDistinctTrue();

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertNotEquals(personsFromJPQLWithOutDistinct.size(), easyCriteriaResult.size());

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLeftJoinWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p left join p.dogs d", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("dogs");
        easyCriteria.setDistinctTrue();

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLeftJoinWithDistinctWorking() {
        final List<Person> personsFromJPQLWithOutDistinct = getListFromJPQL("select p from Person p left join p.dogs d", Person.class);

        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p left join p.dogs d", Person.class);

        assertTrue(personsFromJPQL.size() > 0);
        assertTrue(personsFromJPQL.size() < personsFromJPQLWithOutDistinct.size());


        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("dogs");
        easyCriteria.setDistinctTrue();

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertNotEquals(personsFromJPQLWithOutDistinct.size(), easyCriteriaResult.size());

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isInnerJoinFetchWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p inner join fetch p.dogs", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoinFetch("dogs");
        easyCriteria.setDistinctTrue();

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLeftJoinFetchWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p left join fetch p.dogs", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("dogs");
        easyCriteria.setDistinctTrue();

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }
}