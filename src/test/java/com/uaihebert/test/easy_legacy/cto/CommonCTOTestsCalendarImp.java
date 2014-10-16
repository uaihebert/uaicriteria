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
package com.uaihebert.test.easy_legacy.cto;

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

public class CommonCTOTestsCalendarImp extends AbstractTest implements CommonTests {

    @Test
    public void isAddingOneOrEquals() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(date);

        final Date date2 = getFormattedDate("1/1/2002");
        final Calendar dateOfBirth2 = Calendar.getInstance();
        dateOfBirth2.setTime(date2);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orEquals("birthDayDate", dateOfBirth, dateOfBirth2);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("birthDayDate", dateOfBirth, dateOfBirth2);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingOneWhereEquals() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(date);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("birthDayDate", dateOfBirth);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("birthDayDate", dateOfBirth);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final Date birthDay = getFormattedDate("1/1/2001");
        final Date kissDate = getFormattedDate("1/1/2011");

        final Calendar birthDayCalendar = Calendar.getInstance();
        birthDayCalendar.setTime(birthDay);

        final Calendar firstKiss = Calendar.getInstance();
        firstKiss.setTime(kissDate);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("birthDayDate", birthDayCalendar).andEquals("firstKissDate", firstKiss);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("birthDayDate", birthDayCalendar).andEquals("firstKissDate", firstKiss);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isBetweenWorking() {
        final Date dateA = getFormattedDate("5/5/2000");
        final Date dateB = getFormattedDate("5/5/2003");
        final Calendar valueA = Calendar.getInstance();
        valueA.setTime(dateA);
        final Calendar valueB = Calendar.getInstance();
        valueB.setTime(dateB);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andBetween("birthDayDate", valueA, valueB);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andBetween("birthDayDate", valueA, valueB);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isGreaterThanWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar birthDayDate = Calendar.getInstance();
        birthDayDate.setTime(date);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andGreaterThan("birthDayDate", birthDayDate);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andGreaterThan("birthDayDate", birthDayDate);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar birthDayDate = Calendar.getInstance();
        birthDayDate.setTime(date);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andGreaterOrEqualTo("birthDayDate", birthDayDate);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andGreaterOrEqualTo("birthDayDate", birthDayDate);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isLessThanWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar birthDayDate = Calendar.getInstance();
        birthDayDate.setTime(date);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andLessThan("birthDayDate", birthDayDate);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andLessThan("birthDayDate", birthDayDate);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isLessOrEqualToWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar birthDayDate = Calendar.getInstance();
        birthDayDate.setTime(date);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andLessOrEqualTo("birthDayDate", birthDayDate);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andLessOrEqualTo("birthDayDate", birthDayDate);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
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

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("person");
        easyCTO.andJoinBetween("person", "birthDayDate", valueA, valueB);

        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinBetween("person", "birthDayDate", valueA, valueB);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(easyViewCTO.getResultList().size(), easyCriteriaResult.size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", dateOfBirth);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("person");
        easyCTO.andJoinGreaterThan("person", "birthDayDate", dateOfBirth);

        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinGreaterThan("person", "birthDayDate", dateOfBirth);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(easyViewCTO.getResultList().size(), easyCriteriaResult.size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", dateOfBirth);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("person");
        easyCTO.andJoinGreaterOrEqualTo("person", "birthDayDate", dateOfBirth);

        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinGreaterOrEqualTo("person", "birthDayDate", dateOfBirth);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(easyViewCTO.getResultList().size(), easyCriteriaResult.size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessThanWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", dateOfBirth);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("person");
        easyCTO.andJoinLessThan("person", "birthDayDate", dateOfBirth);

        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinLessThan("person", "birthDayDate", dateOfBirth);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(easyViewCTO.getResultList().size(), easyCriteriaResult.size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final Date date = getFormattedDate("1/1/2001");
        final Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("birthDayDate", dateOfBirth);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("person");
        easyCTO.andJoinLessOrEqualTo("person", "birthDayDate", dateOfBirth);

        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinLessOrEqualTo("person", "birthDayDate", dateOfBirth);

        final List<Dog> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(easyViewCTO.getResultList().size(), easyCriteriaResult.size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteriaResult));
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

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andBetween("products.nickNames.justCalendar", justCalendar, justCalendar2);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andBetween("products.nickNames.justCalendar", justCalendar, justCalendar2);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final Date date = getFormattedDate("2/2/2002");
        final Calendar justCalendar = Calendar.getInstance();
        justCalendar.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justCalendar", justCalendar);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andGreaterThan("products.nickNames.justCalendar", justCalendar);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterThan("products.nickNames.justCalendar", justCalendar);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final Date date = getFormattedDate("2/2/2002");
        final Calendar justCalendar = Calendar.getInstance();
        justCalendar.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justCalendar", justCalendar);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andGreaterOrEqualTo("products.nickNames.justCalendar", justCalendar);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterOrEqualTo("products.nickNames.justCalendar", justCalendar);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final Date date = getFormattedDate("2/2/2002");
        final Calendar justCalendar = Calendar.getInstance();
        justCalendar.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justCalendar", justCalendar);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andLessThan("products.nickNames.justCalendar", justCalendar);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessThan("products.nickNames.justCalendar", justCalendar);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final Date date = getFormattedDate("2/2/2002");
        final Calendar justCalendar = Calendar.getInstance();
        justCalendar.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justCalendar", justCalendar);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andLessOrEqualTo("products.nickNames.justCalendar", justCalendar);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessOrEqualTo("products.nickNames.justCalendar", justCalendar);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
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

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.orEquals("products.nickNames.justCalendar", justCalendar, justCalendar2);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.justCalendar", justCalendar, justCalendar2);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }
}