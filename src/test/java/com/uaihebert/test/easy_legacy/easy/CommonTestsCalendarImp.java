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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommonTestsCalendarImp extends AbstractTest implements CommonTests {

    @Test
    public void isAddingOneOrEquals() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(date);

        final Date date2 = getFormattedDate("1/1/2002");
        final Calendar dateOfBirth2 = Calendar.getInstance();
        dateOfBirth2.setTime(date2);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", dateOfBirth);
        parameters.put("dateOfBirth2", dateOfBirth2);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate = :birthDayDate or p.birthDayDate = :dateOfBirth2", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 1);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("birthDayDate", dateOfBirth, dateOfBirth2);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final Date birthDay = getFormattedDate("1/1/2001");
        final Date kissDate = getFormattedDate("1/1/2011");

        final Calendar birthDayCalendar = Calendar.getInstance();
        birthDayCalendar.setTime(birthDay);

        final Calendar firstKiss = Calendar.getInstance();
        firstKiss.setTime(kissDate);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDay", birthDayCalendar);
        parameters.put("firstKiss", firstKiss);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate = :birthDay and p.firstKissDate = :firstKiss", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("birthDayDate", birthDayCalendar).andEquals("firstKissDate", firstKiss);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingOneWhereEquals() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", dateOfBirth);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate = :birthDayDate", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("birthDayDate", dateOfBirth);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isBetweenWorking() {
        final Date dateA = getFormattedDate("5/5/2000");
        final Date dateB = getFormattedDate("5/5/2003");
        final Calendar valueA = Calendar.getInstance();
        valueA.setTime(dateA);
        final Calendar valueB = Calendar.getInstance();
        valueB.setTime(dateB);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("valueA", valueA);
        parameters.put("valueB", valueB);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate between :valueA and :valueB", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andBetween("birthDayDate", valueA, valueB);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterThanWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar birthDayDate = Calendar.getInstance();
        birthDayDate.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", birthDayDate);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate > :birthDayDate", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andGreaterThan("birthDayDate", birthDayDate);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar birthDayDate = Calendar.getInstance();
        birthDayDate.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", birthDayDate);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate >= :birthDayDate", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andGreaterOrEqualTo("birthDayDate", birthDayDate);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessThanWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar birthDayDate = Calendar.getInstance();
        birthDayDate.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", birthDayDate);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate < :birthDayDate", Person.class, parameters);

        assertTrue(personsFromJPQL.size() == 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andLessThan("birthDayDate", birthDayDate);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessOrEqualToWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar birthDayDate = Calendar.getInstance();
        birthDayDate.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", birthDayDate);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate <= :birthDayDate", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andLessOrEqualTo("birthDayDate", birthDayDate);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", dateOfBirth);

        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.birthDayDate > :birthDayDate", Dog.class, parameters);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinGreaterThan("person", "birthDayDate", dateOfBirth);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", dateOfBirth);

        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.birthDayDate >= :birthDayDate", Dog.class, parameters);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinGreaterOrEqualTo("person", "birthDayDate", dateOfBirth);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessThanWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", dateOfBirth);

        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.birthDayDate < :birthDayDate", Dog.class, parameters);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinLessThan("person", "birthDayDate", dateOfBirth);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", dateOfBirth);

        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.birthDayDate <= :birthDayDate", Dog.class, parameters);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinLessOrEqualTo("person", "birthDayDate", dateOfBirth);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinBetweenWorking() {
        final Date dateA = getFormattedDate("1/1/2000");
        final Date dateB = getFormattedDate("5/5/2002");
        final Calendar valueA = Calendar.getInstance();
        valueA.setTime(dateA);
        final Calendar valueB = Calendar.getInstance();
        valueB.setTime(dateB);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("valueA", valueA);
        parameters.put("valueB", valueB);

        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.birthDayDate between :valueA and :valueB", Dog.class, parameters);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinBetween("person", "birthDayDate", valueA, valueB);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final Date date = getFormattedDate("2/2/2002");
        final Calendar justCalendar = Calendar.getInstance();
        justCalendar.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justCalendar", justCalendar);

        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justCalendar > :justCalendar";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterThan("products.nickNames.justCalendar", justCalendar);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final Date date = getFormattedDate("2/2/2002");
        final Calendar justCalendar = Calendar.getInstance();
        justCalendar.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justCalendar", justCalendar);

        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justCalendar >= :justCalendar";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterOrEqualTo("products.nickNames.justCalendar", justCalendar);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final Date date = getFormattedDate("2/2/2002");
        final Calendar justCalendar = Calendar.getInstance();
        justCalendar.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justCalendar", justCalendar);

        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justCalendar < :justCalendar";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessThan("products.nickNames.justCalendar", justCalendar);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final Date date = getFormattedDate("2/2/2002");
        final Calendar justCalendar = Calendar.getInstance();
        justCalendar.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justCalendar", justCalendar);

        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justCalendar <= :justCalendar";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessOrEqualTo("products.nickNames.justCalendar", justCalendar);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar justCalendar = Calendar.getInstance();
        justCalendar.setTime(date);

        final Date date2 = getFormattedDate("2/2/2002");
        final Calendar justCalendar2 = Calendar.getInstance();
        justCalendar2.setTime(date2);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justCalendarA", justCalendar);
        parameters.put("justCalendarB", justCalendar2);

        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justCalendar between :justCalendarA and :justCalendarB";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andBetween("products.nickNames.justCalendar", justCalendar, justCalendar2);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar justCalendar = Calendar.getInstance();
        justCalendar.setTime(date);

        final Date date2 = getFormattedDate("2/2/2002");
        final Calendar justCalendar2 = Calendar.getInstance();
        justCalendar2.setTime(date2);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justCalendarA", justCalendar);
        parameters.put("justCalendarB", justCalendar2);

        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justCalendar = :justCalendarA or n.justCalendar = :justCalendarB";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.justCalendar", justCalendar, justCalendar2);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }
}