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
import com.uaihebert.model.test.Car;
import com.uaihebert.model.test.Dog;
import com.uaihebert.model.test.Manufacturer;
import com.uaihebert.model.test.Person;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.test.easy_legacy.CommonTests;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommonTestsBigDecimalImp extends AbstractTest implements CommonTests {

    @Test
    public void isGreaterThanWorking() {
        final List<Car> carsFromJPQL = getListFromJPQL("select c from Car c where c.weight > 20", Car.class);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);

        easyCriteria.andGreaterThan("weight", new BigDecimal(20));

        final List<Car> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(carsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(carsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final List<Car> carsFromJPQL = getListFromJPQL("select c from Car c where c.weight >= 20", Car.class);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);

        easyCriteria.andGreaterOrEqualTo("weight", new BigDecimal(20));

        final List<Car> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(carsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(carsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessThanWorking() {
        final List<Car> carsFromJPQL = getListFromJPQL("select c from Car c where c.weight < 30", Car.class);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);

        easyCriteria.andLessThan("weight", new BigDecimal(30));

        final List<Car> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(carsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(carsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessOrEqualToWorking() {
        final List<Car> carsFromJPQL = getListFromJPQL("select c from Car c where c.weight <= 30", Car.class);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);

        easyCriteria.andLessOrEqualTo("weight", new BigDecimal(30));

        final List<Car> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(carsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(carsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.car c where c.weight > 20", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car");

        easyCriteria.andJoinGreaterThan("car", "weight", new BigDecimal(20));

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.car c where c.weight >= 20", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car");

        easyCriteria.andJoinGreaterOrEqualTo("car", "weight", new BigDecimal(20));

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.car c where c.weight < 30", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car");

        easyCriteria.andJoinLessThan("car", "weight", new BigDecimal(30));

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.car c where c.weight <= 30", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car");

        easyCriteria.andJoinLessOrEqualTo("car", "weight", new BigDecimal(30));

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justBigDecimal > 1";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterThan("products.nickNames.justBigDecimal", new BigDecimal(1));

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justBigDecimal >= 1";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterOrEqualTo("products.nickNames.justBigDecimal", new BigDecimal(1));

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justBigDecimal < 2";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessThan("products.nickNames.justBigDecimal", new BigDecimal(2));

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justBigDecimal <= 2";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessOrEqualTo("products.nickNames.justBigDecimal", new BigDecimal(2));

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justBigDecimal between 1f and 2f";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andBetween("products.nickNames.justBigDecimal", new BigDecimal(1), new BigDecimal(2));

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justBigDecimal = 1 or n.justBigDecimal = 2";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.justBigDecimal", new BigDecimal(1), new BigDecimal(2));

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isAddingOneOrEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.justBigDecimalA = 1 or p.justBigDecimalA = 10", Person.class);
        assertTrue(personsFromJPQL.size() > 1);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("justBigDecimalA", 1, 10);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingOneWhereEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.justBigDecimalA = 1", Person.class);
        assertTrue(personsFromJPQL.size() > 1);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("justBigDecimalA", 1);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.justBigDecimalA = 1 and p.weight = 10", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("justBigDecimalA", 1).andEquals("weight", 10d);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isBetweenWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.justBigDecimalA between 0 and 11", Person.class);

        assertTrue(personsFromJPQL.size() > 1);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andBetween("justBigDecimalA", new BigDecimal(0), new BigDecimal(10));

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinBetweenWorking() {
        final List<Dog> personsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.justBigDecimalA between 0 and 11", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinBetween("person", "justBigDecimalA", new BigDecimal(0), new BigDecimal(11));

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void addBetweenWithDecimal() {
        final List<Car> carsFromJPQL = getListFromJPQL("select c from Car c where c.weight between 19 and 31", Car.class);

        assertTrue(carsFromJPQL.size() == 3);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);

        easyCriteria.andBetween("weight", new BigDecimal(20), new BigDecimal(30));

        final List<Car> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(carsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(carsFromJPQL.containsAll(easyCriteriaResult));
    }
}