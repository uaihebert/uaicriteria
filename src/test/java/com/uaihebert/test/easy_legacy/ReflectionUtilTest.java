/*
 * Copyright 2012 uaiHebert Solucoes em Informatica
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
package com.uaihebert.test.easy_legacy;

import com.uaihebert.model.EasyCriteria;
import com.uaihebert.model.test.Car;
import com.uaihebert.model.test.Person;
import com.uaihebert.model.test.RegularEntityFive;
import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.Song;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import com.uaihebert.util.ReflectionUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ReflectionUtilTest {
    private static EntityManagerFactory entityManagerFactory;

    @BeforeClass
    public static void createPersistenceUnit() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("QueryTesterEclipseLink");
//            entityManagerFactory = CodeGenerator.createEntityManagerFactoryForEclipseLink();
//            entityManagerFactory = CodeGenerator.createEntityManagerFactoryForOpenJPA();
        }

        CodeGenerator.generateData(entityManagerFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorWithUnknownAttributeOnEquals() {
        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.andEquals("invalidName", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorWithUnknownAttributeOnGreaterOrLessMethods() {
        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.andGreaterThan("invalidName", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnrRefectionUtilWithNullClass() {
        ReflectionUtil.isBigDecimal(null, "invalidName");
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnrRefectionUtilWithNullParameter() {
        ReflectionUtil.isBigDecimal(Integer.class, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnrRefectionUtilWithEmptyParameter() {
        ReflectionUtil.isBigDecimal(Integer.class, "          ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnrRefectionUtilWithInvalidParameter() {
        ReflectionUtil.isCalendar(Integer.class, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void isExceptionHappeningIfFieldIsNotFound() {
        ReflectionUtil.isBigDecimal(Car.class, "weight");
        ReflectionUtil.isBigDecimal(Car.class, "asd");
    }

    @Test
    public void isCheckingForListSilently() {
        final boolean firstTest = ReflectionUtil.isList(Person.class, "dogs");
        final boolean secondTest = ReflectionUtil.isList(Person.class, "dogs", false);
        assertEquals(firstTest, secondTest);

        final boolean thirdTest = ReflectionUtil.isList(Person.class, "dogs", true);
        assertEquals(secondTest, thirdTest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isCheckingForListSilentlyRaisingException() {
        ReflectionUtil.isList(Person.class, "fakeRelationShip", false);
    }

    @Test
    public void isCheckingForListSilentlyNotRaisingException() {
        boolean response = ReflectionUtil.isList(Person.class, "fakeRelationShip", true);
        assertNotEquals(true, response);

        response = ReflectionUtil.isList(Person.class, "nickName", true);
        assertNotEquals(true, response);
    }

    @Test
    public void isCheckingForSetSilently() {
        final boolean firstTest = ReflectionUtil.isSet(Person.class, "certifications");
        final boolean secondTest = ReflectionUtil.isSet(Person.class, "certifications", false);
        assertEquals(firstTest, secondTest);

        final boolean thirdTest = ReflectionUtil.isSet(Person.class, "certifications", true);
        assertEquals(secondTest, thirdTest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isCheckingForSetSilentlyRaisingException() {
        ReflectionUtil.isSet(Person.class, "fakeRelationShip", false);
    }

    @Test
    public void isCheckingForMapSilently() {
        final boolean firstTest = ReflectionUtil.isMap(RegularEntityFive.class, "stringMap");
        final boolean secondTest = ReflectionUtil.isMap(RegularEntityFive.class, "stringMap", false);
        assertEquals(firstTest, secondTest);

        final boolean thirdTest = ReflectionUtil.isMap(RegularEntityFive.class, "stringMap", true);
        assertEquals(secondTest, thirdTest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isCheckingForMapSilentlyRaisingException() {
        ReflectionUtil.isMap(RegularEntityFive.class, "fakeRelationShip", false);
    }

    @Test
    public void isCheckingForMapSilentlyNotRaisingException() {
        ReflectionUtil.isMap(RegularEntityOne.class, "fakeRelationShip", true);
    }

    @Test
    public void isCheckingForSetSilentlyNotRaisingException() {
        boolean response = ReflectionUtil.isSet(Person.class, "fakeRelationShip", true);
        assertNotEquals(true, response);

        response = ReflectionUtil.isSet(Person.class, "nickName", true);
        assertNotEquals(true, response);
    }

    @Test
    public void isCheckingForCollectionSilently() {
        final boolean firstTest = ReflectionUtil.isCollection(Person.class, "cats");
        final boolean secondTest = ReflectionUtil.isCollection(Person.class, "cats", false);
        assertEquals(firstTest, secondTest);

        final boolean thirdTest = ReflectionUtil.isCollection(Person.class, "cats", true);
        assertEquals(secondTest, thirdTest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isCheckingForCollectionNotSilently() {
        ReflectionUtil.isCollection(Person.class, "catsA");
    }

    @Test(expected = IllegalArgumentException.class)
    public void isCheckingForCollectionSilentlyRaisingException() {
        ReflectionUtil.isCollection(Person.class, "fakeRelationShip", false);
    }

    @Test
    public void isCheckingForCollectionSilentlyNotRaisingException() {
        boolean response = ReflectionUtil.isCollection(Person.class, "fakeRelationShip", true);
        assertNotEquals(true, response);

        response = ReflectionUtil.isCollection(Person.class, "nickName", true);
        assertNotEquals(true, response);
    }

    EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}