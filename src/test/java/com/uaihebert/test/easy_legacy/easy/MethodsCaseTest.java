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
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MethodsCaseTest extends AbstractTest {

    @Test
    public void isOrNotEqualsLowerCaseWorking() {
        EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.orNotEquals("name", CodeGenerator.PERSON01_NAME);
        final List<Person> resultList = easyCriteria.getResultList();

        easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.orNotEquals(true, "name", CodeGenerator.PERSON01_NAME);

        assertEquals(easyCriteria.getResultList().size(), resultList.size());
    }

    @Test
    public void isOrEqualsWithIndexLowerCaseWorking() {
        EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.orEquals(1, "name", CodeGenerator.PERSON01_NAME.toLowerCase())
                .orEquals(2, "name", CodeGenerator.PERSON02_NAME.toLowerCase());

        assertEquals(0, easyCriteria.getResultList().size());

        easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.orEquals(1, "id", 1)
                .orEquals(1, "id", 2)
                .orEquals(2, "name", CodeGenerator.PERSON01_NAME.toLowerCase())
                .orEquals(true, 2, "name", CodeGenerator.PERSON02_NAME.toLowerCase());

        assertEquals(1, easyCriteria.getResultList().size());

        easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.orEquals(1, "id", 1)
                .orEquals(1, "id", 2)
                .orEquals(true, 2, "name", CodeGenerator.PERSON01_NAME.toLowerCase())
                .orEquals(true, 2, "name", CodeGenerator.PERSON02_NAME.toLowerCase());

        assertEquals(2, easyCriteria.getResultList().size());
    }

    @Test
    public void isNotAndEqualsWithLowerCaseWorking() {
        EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("name", CodeGenerator.PERSON02_NAME);
        assertEquals(1, easyCriteria.getResultList().size());

        easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        final TypedQuery<Person> query = getEntityManager().createQuery("select p from Person p", Person.class);

        easyCriteria.andNotEquals("name", CodeGenerator.PERSON02_NAME.toLowerCase());
        assertEquals(query.getResultList().size(), easyCriteria.getResultList().size());

        easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andNotEquals(true, "name", CodeGenerator.PERSON02_NAME);
        assertEquals(query.getResultList().size() - 1, easyCriteria.getResultList().size());
    }

    @Test
    public void isJoinLikeLowerCaseWorking() {
        List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) like 'm%'", Dog.class);

        EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinStringLike(true, "person", "name", "M%");
        assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));

        dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) like '%y'", Dog.class);

        easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinStringLike(true, "person", "name", "%y");
        assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
    }

    @Test
    public void isJoinInCaseWorking() {
        final List<String> names = new ArrayList<String>();
        names.add("Mary");
        names.add("John");

        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) in ('mary', 'john')", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinStringIn(true, "person", "name", names);
        assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
    }

    @Test
    public void isEqualsLowerCaseWorking() {
        EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andEquals("name", CodeGenerator.PERSON01_NAME.toLowerCase());

        assertEquals(0, easyCriteria.getResultList().size());

        easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andEquals(true, "name", CodeGenerator.PERSON01_NAME.toLowerCase());

        assertNotEquals(0, easyCriteria.getResultList().size());
    }

    @Test
    public void isOrEqualsWithLowerCaseWorking() {
        EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("name", CodeGenerator.PERSON01_NAME.toLowerCase(), CodeGenerator.PERSON02_NAME.toLowerCase());
        assertEquals(0, easyCriteria.getResultList().size());

        easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals(true, "name", CodeGenerator.PERSON01_NAME.toLowerCase(), CodeGenerator.PERSON02_NAME.toLowerCase());
        assertEquals(2, easyCriteria.getResultList().size());
    }

    @Test
    public void addAndSeparatedByOrLowerCase() {
        final List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where (s.id = 1 and lower(s.name) = 'sing out') or (s.id = 2 and lower(s.name) = 'alive')", Song.class);
        assertTrue(songsFromJPQL.size() == 2);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.addAndSeparatedByOr(1, "id", 1).addAndSeparatedByOr(true, 1, "name", "Sing Out".toLowerCase()).addAndSeparatedByOr(2, "id", 2).addAndSeparatedByOr(true, 2, "name", "Alive".toLowerCase());

        final List<Song> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinBetweenLowerCaseWorking() {
        final List<Dog> personsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) between 'a' and 'l'", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinBetween(true, "person", "name", "a", "l");

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessThanLowerCaseWorking() {
        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) < 'mary'", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinLessThan(true, "person", "name", "Mary");

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessOrEqualToLowerCaseWorking() {
        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) <= 'mary'", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinLessOrEqualTo(true, "person", "name", "Mary");

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterThanLowerCaseWorking() {
        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) > 'mary'", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinGreaterThan(true, "person", "name", "Mary");

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterOrEqualToLowerCaseWorking() {
        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) >= 'mary'", Dog.class);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinGreaterOrEqualTo(true, "person", "name", "Mary");

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isMultipleJoinWithOrLowerCaseWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where lower(n.name) = 'nickname a' or lower(n.name) = 'nickname b'";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals(true, "products.nickNames.name", "NickName A".toLowerCase(), "NickName B".toLowerCase());

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLikeLowerCaseAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where lower(n.name) like '% b'";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringLike(true, "products.nickNames.name", "% B".toLowerCase());

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithNotLikeLowerCaseAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where lower(n.name) not like '% b'";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringNotLike(true, "products.nickNames.name", "% B".toLowerCase());

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithInLowerCaseAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where lower(n.name) in ('nickname a', 'nickname b')";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final List<String> names = new ArrayList<String>();
        names.add("NickName A".toLowerCase());
        names.add("NickName B".toLowerCase());

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringIn(true, "products.nickNames.name", names);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithNotInLowerCaseAttributeWorking() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where lower(n.name) not in ('nickname a', 'nickname b')";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final List<String> names = new ArrayList<String>();
        names.add("NickName A");
        names.add("NickName B");

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringNotIn(true, "products.nickNames.name", names);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isGreaterThanLowerCaseWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where LOWER(p.name) > LOWER('John')", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andGreaterThan(true, "name", "John");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGreaterOrEqualToLowerCaseWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where LOWER(p.name) >= 'john'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andGreaterOrEqualTo(true, "name", "john");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessThanLowerCaseWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where LOWER(p.name) < 'john'", Person.class);
        assertTrue(personsFromJPQL.size() == 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andLessThan(true, "name", "John");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLessOrEqualToLowerCaseWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where lower(p.name) <= 'john'", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andLessOrEqualTo(true, "name", "John");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isBetweenLowerCaseWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where lower(p.name) between 'a' and 'l'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andBetween(true, "name", "A".toLowerCase(), "L".toLowerCase());

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isLikeLowerCaseWorking() {
        List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where lower(p.name) like 'm%'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andStringLike(true, "name", "m%");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));

        personsFromJPQL = getListFromJPQL("select p from Person p where lower(p.name) like '%y'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andStringLike(true, "name", "%y");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isNotLikeLowerCaseWorking() {
        List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where lower(p.name) not like 'm%'", Person.class);

        EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andStringNotLike(true, "name", "m%");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));

        personsFromJPQL = getListFromJPQL("select p from Person p where lower(p.name) not like '%y'", Person.class);

        personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andStringNotLike(true, "name", "%y");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isInLowerCaseWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where lower(p.name) in ('john', 'mary')", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final List<String> names = new ArrayList<String>();
        names.add("John");
        names.add("Mary");

        final EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andStringIn(true, "name", names);
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isNotInLowerCaseWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where LOWER(p.name) not in ('john', 'mary')", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final List<String> names = new ArrayList<String>();
        names.add("John");
        names.add("Mary");

        final EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andStringNotIn(true, "name", names);
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isWhereInnerJoinLowerCaseLowerCaseWorking() {
        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d inner join d.person p where lower(p.name) = 'john'", Dog.class);
        assertTrue(dogsFromJPQL.size() > 0);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinEquals(true, "person", "name", "John");

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isWhereJoinNotEqualsLowerCaseWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.dogs d where lower(d.name) <> 'fire'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        if (isOpenJPA()) {
            easyCriteria.setDistinctTrue();
        }

        easyCriteria.innerJoinFetch("dogs");
        easyCriteria.andJoinNotEquals(true, "dogs", "name", "Fire");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(easyCriteriaResult.containsAll(personsFromJPQL));
    }
}