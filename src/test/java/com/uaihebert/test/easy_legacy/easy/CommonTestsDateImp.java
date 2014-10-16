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
import com.uaihebert.model.test.Manufacturer;
import com.uaihebert.model.test.Person;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.test.easy_legacy.CommonTests;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommonTestsDateImp extends AbstractTest implements CommonTests {

    @Test
    public void isAddingOneOrEquals() {
        final Date firstJobDate = getFormattedDate("1/1/2015");
        final Date secondJobDate = getFormattedDate("1/1/2016");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("firstJobDate", firstJobDate);
        parameters.put("secondJobDate", secondJobDate);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.firstJobDate = :firstJobDate or p.firstJobDate = :secondJobDate", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 1);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("firstJobDate", firstJobDate, secondJobDate);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingOneWhereEquals() {
        final Date firstJobDate = getFormattedDate("1/1/2015");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("firstJobDate", firstJobDate);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.firstJobDate = :firstJobDate", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("firstJobDate", firstJobDate);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final Date firstJobDate = getFormattedDate("1/1/2015");
        final Date firstSoccerMatchDate = getFormattedDate("1/1/2013");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("firstJobDate", firstJobDate);
        parameters.put("firstSoccerMatchDate", firstSoccerMatchDate);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.firstJobDate = :firstJobDate and p.firstSoccerMatchDate = :firstSoccerMatchDate", Person.class,
                parameters);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("firstJobDate", firstJobDate).andEquals("firstSoccerMatchDate", firstSoccerMatchDate);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isBetweenWorking() {
        final Date valueA = getFormattedDate("1/1/2014");
        final Date valueB = getFormattedDate("1/1/2017");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("valueA", valueA);
        parameters.put("valueB", valueB);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.firstJobDate between :valueA and :valueB", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andBetween("firstJobDate", valueA, valueB);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterThanWorking() {
        final Date firstJobDate = getFormattedDate("01/01/2015");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("firstJobDate", firstJobDate);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.firstJobDate > :firstJobDate", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andGreaterThan("firstJobDate", firstJobDate);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final Date firstJobDate = getFormattedDate("01/01/2015");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("firstJobDate", firstJobDate);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.firstJobDate >= :firstJobDate", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andGreaterOrEqualTo("firstJobDate", firstJobDate);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessThanWorking() {
        final Date firstJobDate = getFormattedDate("01/01/2015");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("firstJobDate", firstJobDate);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.firstJobDate < :firstJobDate", Person.class, parameters);

        assertTrue(personsFromJPQL.size() == 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andLessThan("firstJobDate", firstJobDate);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessOrEqualToWorking() {
        final Date firstJobDate = getFormattedDate("01/01/2015");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("firstJobDate", firstJobDate);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.firstJobDate <= :firstJobDate", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andLessOrEqualTo("firstJobDate", firstJobDate);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final Date dateOfBirth = getFormattedDate("19/10/2005");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("dateOfBirth", dateOfBirth);

        final List<Person> personFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.dateOfBirth > :dateOfBirth", Person.class, parameters);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinGreaterThan("dogs", "dateOfBirth", dateOfBirth);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final Date valueA = getFormattedDate("1/1/1999");
        final Date valueB = getFormattedDate("1/1/2003");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("valueA", valueA);
        parameters.put("valueB", valueB);

        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justDate between :valueA and :valueB";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andBetween("products.nickNames.justDate", valueA, valueB);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));

    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final Date dateOfBirth = getFormattedDate("19/10/2005");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("dateOfBirth", dateOfBirth);

        final List<Person> personFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.dateOfBirth >= :dateOfBirth", Person.class, parameters);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinGreaterOrEqualTo("dogs", "dateOfBirth", dateOfBirth);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessThanWorking() {
        final Date dateOfBirth = getFormattedDate("19/10/2005");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("dateOfBirth", dateOfBirth);

        final List<Person> personFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.dateOfBirth < :dateOfBirth", Person.class, parameters);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinLessThan("dogs", "dateOfBirth", dateOfBirth);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final Date dateOfBirth = getFormattedDate("01/01/2009");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("dateOfBirth", dateOfBirth);

        final List<Person> personFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.dateOfBirth <= :dateOfBirth", Person.class, parameters);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinLessOrEqualTo("dogs", "dateOfBirth", dateOfBirth);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinBetweenWorking() {
        final Date valueA = getFormattedDate("1/1/2008");
        final Date valueB = getFormattedDate("1/1/2010");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("valueA", valueA);
        parameters.put("valueB", valueB);

        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d where d.dateOfBirth between :valueA and :valueB", Person.class, parameters);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("dogs");
        easyCriteria.andJoinBetween("dogs", "dateOfBirth", valueA, valueB);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isMultipleJoinWithBetweenToWorking() {
        final Date justDate = getFormattedDate("1/1/2001");
        final Date justDate2 = getFormattedDate("2/2/2002");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justDateA", justDate);
        parameters.put("justDateB", justDate2);

        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justDate between :justDateA and :justDateB";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andBetween("products.nickNames.justDate", justDate, justDate2);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final Date justDate = getFormattedDate("2/2/2002");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justDate", justDate);

        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justDate > :justDate";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterThan("products.nickNames.justDate", justDate);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final Date justDate = getFormattedDate("2/2/2002");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justDate", justDate);

        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justDate >= :justDate";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterOrEqualTo("products.nickNames.justDate", justDate);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final Date justDate = getFormattedDate("2/2/2002");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justDate", justDate);

        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justDate < :justDate";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessThan("products.nickNames.justDate", justDate);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final Date justDate = getFormattedDate("2/2/2002");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justDate", justDate);

        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justDate <= :justDate";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessOrEqualTo("products.nickNames.justDate", justDate);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final Date justDate = getFormattedDate("1/1/2001");
        final Date justDate2 = getFormattedDate("2/2/2002");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justDateA", justDate);
        parameters.put("justDateB", justDate2);

        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justDate = :justDateA or n.justDate = :justDateB";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.justDate", justDate, justDate2);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }
}