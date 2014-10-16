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
import com.uaihebert.model.test.Song;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.test.easy_legacy.CodeGenerator;
import com.uaihebert.test.easy_legacy.CommonTests;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommonTestsStringImp extends AbstractTest implements CommonTests {

    @Test
    public void isAddingOneOrEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name = '" + CodeGenerator.PERSON01_NAME + "' or p.name = '" + CodeGenerator.PERSON02_NAME + "'", Person.class);
        assertTrue(personsFromJPQL.size() > 1);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.orEquals("name", CodeGenerator.PERSON01_NAME, CodeGenerator.PERSON02_NAME);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name = '" + CodeGenerator.PERSON01_NAME + "'" +
                "and p.nickName = '" + CodeGenerator.PERSON01_NICKNAME + "'", Person.class);
        assertTrue(personsFromJPQL.size() == 1);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andEquals("name", CodeGenerator.PERSON01_NAME).andEquals("nickName", CodeGenerator.PERSON01_NICKNAME);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingOneWhereEquals() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name = '" + CodeGenerator.PERSON01_NAME + "'", Person.class);
        assertTrue(personsFromJPQL.size() == 1);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andEquals("name", CodeGenerator.PERSON01_NAME);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinNotLikeExceptionWorking() {
        List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) not like 'm%'", Dog.class);

        EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinStringNotLike(true, "person", "name", "M%");
        assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));

        dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) not like '%y'", Dog.class);

        easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);

        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinStringNotLike(true, "person", "name", "%y");
        assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
    }

    @Test
    public void isJoinNotInExceptionWorking() {
        final List<String> names = new ArrayList<String>();
        names.add("Mary");
        names.add("John");

        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) not in ('mary', 'john')", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinStringNotIn(true, "person", "name", names);
        assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
    }

    @Test
    public void isJoinBetweenWorking() {
        final List<Dog> personsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name between 'A' and 'L'", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinBetween("person", "name", "A", "L");

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name > 'Mary'", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinGreaterThan("person", "name", "Mary");

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name >= 'Mary'", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinGreaterOrEqualTo("person", "name", "Mary");

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessThanWorking() {
        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name < 'Mary'", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinLessThan("person", "name", "Mary");

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name <= 'Mary'", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinLessOrEqualTo("person", "name", "Mary");

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinInWorking() {
        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name in ('Mary', 'John')", Dog.class);

        final List<String> names = new ArrayList<String>();
        names.add("Mary");
        names.add("John");

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinStringIn("person", "name", names);
        assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
    }

    @Test
    public void isJoinNotInWorking() {
        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name not in ('Mary', 'John')", Dog.class);

        final List<String> names = new ArrayList<String>();
        names.add("Mary");
        names.add("John");

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinStringNotIn("person", "name", names);
        assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
    }

    @Test
    public void isJoinLikeWorking() {
        List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name like 'M%'", Dog.class);

        EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinStringLike("person", "name", "M%");
        assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));

        dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name like '%y'", Dog.class);

        easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinStringLike("person", "name", "%y");
        assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
    }

    @Test
    public void isJoinNotLikeWorking() {
        List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name not like 'M%'", Dog.class);

        EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinStringNotLike("person", "name", "M%");
        assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));

        dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name not like '%y'", Dog.class);

        easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);

        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinStringNotLike("person", "name", "%y");
        assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name = 'NickName A' or n.name = 'NickName B'";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.name", "NickName A", "NickName B");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name between 'NickName A' and 'NickName B'";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andBetween("products.nickNames.name", CodeGenerator.NICKNAME_A_NAME, CodeGenerator.NICKNAME_B_NAME);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name > '" + CodeGenerator.NICKNAME_B_NAME + "'";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterThan("products.nickNames.name", CodeGenerator.NICKNAME_B_NAME);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name >= '" + CodeGenerator.NICKNAME_B_NAME + "'";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterOrEqualTo("products.nickNames.name", CodeGenerator.NICKNAME_B_NAME);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name < '" + CodeGenerator.NICKNAME_B_NAME + "'";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessThan("products.nickNames.name", CodeGenerator.NICKNAME_B_NAME);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));

    }

    @Test
    public void isMultipleJoinWithStringLessThanWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name < '" + CodeGenerator.NICKNAME_B_NAME + "'";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessThan("products.nickNames.name", CodeGenerator.NICKNAME_B_NAME);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name <= '" + CodeGenerator.NICKNAME_B_NAME + "'";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessOrEqualTo("products.nickNames.name", CodeGenerator.NICKNAME_B_NAME);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLikeAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name like '% B'";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringLike("products.nickNames.name", "% B");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithNotLikeAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name not like '% B'";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringNotLike("products.nickNames.name", "% B");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithInAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name in ('NickName A', 'NickName B')";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final List<String> names = new ArrayList<String>();
        names.add("NickName A");
        names.add("NickName B");

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringIn("products.nickNames.name", names);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithNotInAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name not in ('NickName A', 'NickName B')";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final List<String> names = new ArrayList<String>();
        names.add("NickName A");
        names.add("NickName B");

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringNotIn("products.nickNames.name", names);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isBetweenWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name between 'A' and 'L'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andBetween("name", "A", "L");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name > 'John'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andGreaterThan("name", "John");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name >= 'John'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andGreaterOrEqualTo("name", "John");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessThanWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name < 'John'", Person.class);
        assertTrue(personsFromJPQL.size() == 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andLessThan("name", "John");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessOrEqualToWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name <= 'John'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andLessOrEqualTo("name", "John");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isInWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name in ('John', 'Mary')", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final List<String> names = new ArrayList<String>();
        names.add("John");
        names.add("Mary");

        final EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andStringIn("name", names);
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isNotInWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name not in ('John', 'Mary')", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final List<String> names = new ArrayList<String>();
        names.add("John");
        names.add("Mary");

        final EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andStringNotIn("name", names);
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isLikeWorking() {
        List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name like 'M%'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andStringLike("name", "M%");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));

        personsFromJPQL = getListFromJPQL("select p from Person p where p.name like '%y'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andStringLike("name", "%y");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isNotLikeWorking() {
        List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name not like 'M%'", Person.class);

        EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andStringNotLike("name", "M%");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));

        personsFromJPQL = getListFromJPQL("select p from Person p where p.name not like '%y'", Person.class);

        personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andStringNotLike("name", "%y");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isTwoOrGroupedInsideAndWithIntWorking() {
        final List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where (s.id = 1 or s.id = 2) and (s.name = 'Sing Out' or s.name = 'Alive')", Song.class);
        assertTrue(songsFromJPQL.size() == 2);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.orEquals("id", 1).orEquals("id", 2).orEquals(2, "name", "Sing Out").orEquals(2, "name", "Alive");

        final List<Song> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
    }
}