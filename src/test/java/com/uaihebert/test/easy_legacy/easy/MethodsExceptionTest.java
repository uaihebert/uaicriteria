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
import com.uaihebert.model.test.Person;
import com.uaihebert.model.test.Song;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.test.easy_legacy.mock.InvalidEasyCriteriaClass;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import javassist.CannotCompileException;
import org.junit.Test;

import static org.junit.Assert.fail;

public class MethodsExceptionTest extends AbstractTest {

    @Test(expected = IllegalArgumentException.class)
    public void isThrowingExceptionWhenPassingWrongArgumentToFabric() {
        EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, new InvalidEasyCriteriaClass());
    }

    @Test
    public void isThrowingExceptionWhenNotAllowedMethodsOfCTOInvoked() {
        final EasyCriteria<Person> easyCTO = EasyCriteriaFactory.createEasyCTO();

        try {
            easyCTO.getResultList();
            fail("an exception should happen before getting in here");
        } catch (final IllegalStateException ex) {

        }

        try {
            easyCTO.getSingleResult();
            fail("an exception should happen before getting in here");
        } catch (final IllegalStateException ex) {

        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void isWithErrorWithNotAllowedAttributeGreaterThan() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andGreaterThan("car", new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isWithErrorWithNotAllowedAttributeGreaterOrEqualTo() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andGreaterOrEqualTo("car", new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isWithErrorWithNotAllowedAttributeLessThan() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andLessThan("car", new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isWithErrorWithNotAllowedAttributeLessOrEqualTo() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andLessOrEqualTo("car", new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isWithErrorWithNotAllowedAttributeBetween() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andBetween("car", 1, 2);
    }


    @Test(expected = IllegalArgumentException.class)
    public void errorOnUnknownClassInnerJoin() throws CannotCompileException {
        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.innerJoin("invalidClass");
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnUnknownClassLeftJoin() throws CannotCompileException {
        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.leftJoin("invalidClass");
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnUnknownClassInnerFetchJoin() throws CannotCompileException {
        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.innerJoinFetch("invalidClass");
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnUnknownClassLeftFetchJoin() throws CannotCompileException {
        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.leftJoinFetch("invalidClass");
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnUnknownJoinAttributeGreaterThan() throws CannotCompileException {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoinFetch("dogs");
        easyCriteria.andJoinGreaterThan("dogs", "person", "Fire");
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnUnknownJoinAttributeGreaterOrEqualTo() throws CannotCompileException {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("car");
        easyCriteria.andJoinGreaterOrEqualTo("car", "person", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnUnknownJoinAttributeLessThan() throws CannotCompileException {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("car");
        easyCriteria.andJoinLessThan("car", "person", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnUnknownJoinAttributeLessOrEqualTo() throws CannotCompileException {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("car");
        easyCriteria.andJoinLessOrEqualTo("car", "person", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnNotAllowedCollectionIsEmpty() throws CannotCompileException {
        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.andCollectionIsEmpty("person");
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnNotAllowedCollectionIsNotEmpty() throws CannotCompileException {
        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.andCollectionIsNotEmpty("person");
    }

    @Test(expected = IllegalArgumentException.class)
    public void errorOnJoinBetweenNotAllowedType() throws CannotCompileException {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("car");
        easyCriteria.andJoinBetween("car", "person", null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isExceptionThrownWhenJoinAttributeIsNotFound() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andJoinEquals("dogs", "name", "Dark");
    }

    @Test(expected = IllegalArgumentException.class)
    public void isExceptionThrownWhenMultipleJoinsIsEmpty() {
        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.andEquals("color.manufacturer", "Dark");
    }

    @Test(expected = IllegalArgumentException.class)
    public void isExceptionThrownWhenJoinIsNotFound() {
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car");
        easyCriteria.andJoinEquals("dogs", "name", "Dark");
    }

    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentException() {
        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andEquals("color.manufacturer", "Dark");
    }
}
