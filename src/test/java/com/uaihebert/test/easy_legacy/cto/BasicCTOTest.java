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
package com.uaihebert.test.easy_legacy.cto;

import com.uaihebert.model.EasyCriteria;
import com.uaihebert.model.test.Address;
import com.uaihebert.model.test.Car;
import com.uaihebert.model.test.Manufacturer;
import com.uaihebert.model.test.Person;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BasicCTOTest extends AbstractTest {

    @Test
    public void isCreatingClass() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        assertNotNull(easyCTO);
    }

    @Test
    public void isGettingResultList() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();

        final EasyCriteria<Car> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO);
        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingOneAndEquals() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("id", 1);

        final EasyCriteria<Car> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO);
        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.andEquals("id", 1);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isGettingSingleResult() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("id", 1);

        final EasyCriteria<Car> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO);
        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.andEquals("id", 1);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getSingleResult().equals(easyCriteria.getSingleResult()));
    }

    @Test
    public void isWhereNotEqualsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andNotEquals("name", "Anna");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andNotEquals("name", "Anna");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingOneWhereEqualsBoolean() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("brazilian", true);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("brazilian", true);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingSequentialWhereEqualsBoolean() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("brazilian", true).andEquals("japanese", false);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("brazilian", true).andEquals("japanese", false);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingObjectWhereEquals() {
        final Address address = new Address();
        address.setId(1);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("address", address);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("address", address);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingSequentialObjectsWhereEquals() {
        final Address address = new Address();
        address.setId(1);
        final Car car = new Car();
        car.setId(1);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("address", address).andEquals("car", car);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("address", address).andEquals("car", car);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }


    @Test
    public void isAddingSequentialWhereEquals() throws ParseException {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final Date buildingDate = formatter.parse("1/1/1990");

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("id", 2).andEquals("buildingDate", buildingDate).andEquals("isOld", false).andEquals("streetName", "Street B").andEquals("isYellow", true);

        final EasyCriteria<Address> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Address.class, easyCTO);

        final EasyCriteria<Address> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Address.class);

        easyCriteria.andEquals("id", 2).andEquals("buildingDate", buildingDate).andEquals("isOld", false).andEquals("streetName", "Street B").andEquals("isYellow", true);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isListIsEmptyWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andCollectionIsEmpty("dogs");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andCollectionIsEmpty("dogs");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isCollectionIsEmptyWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andCollectionIsEmpty("cats");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andCollectionIsEmpty("cats");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isSetIsEmptyWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andCollectionIsEmpty("certifications");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andCollectionIsEmpty("certifications");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isListIsNotEmptyWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andCollectionIsNotEmpty("dogs");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andCollectionIsNotEmpty("dogs");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isCollectionIsNotEmptyWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andCollectionIsNotEmpty("cats");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andCollectionIsNotEmpty("cats");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isSetIsNotEmptyWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andCollectionIsNotEmpty("certifications");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andCollectionIsNotEmpty("certifications");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }


    @Test
    public void isPaginationWorking() {
        EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setFirstResult(1);
        easyCTO.setMaxResults(1);

        EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        List<Person> personsCTO = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.setFirstResult(1);
        easyCriteria.setMaxResults(1);

        List<Person> persons = easyCriteria.getResultList();
        assertEquals(persons.size(), personsCTO.size());
        assertTrue(persons.containsAll(personsCTO));

        easyCriteria.setFirstResult(2);
        easyCriteria.setMaxResults(1);
        persons = easyCriteria.getResultList();

        easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setFirstResult(2);
        easyCTO.setMaxResults(1);

        easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        personsCTO = easyViewCTO.getResultList();
        assertEquals(persons.size(), personsCTO.size());
        assertTrue(persons.containsAll(personsCTO));
    }

    @Test
    public void isAbleToDoTheSameQuerySeveralTimes() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

        assertTrue(easyViewCTO.getResultList().size() > 0);
        assertTrue(easyCriteria.getResultList().size() > 0);

        assertEquals(easyViewCTO.getResultList().size(), easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingHints() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.addHint("some.hint", "true");

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        assertFalse(easyCriteria.getResultList().isEmpty());
    }
}