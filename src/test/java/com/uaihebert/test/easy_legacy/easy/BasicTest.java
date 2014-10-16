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
package com.uaihebert.test.easy_legacy.easy;

import com.uaihebert.model.EasyCriteria;
import com.uaihebert.model.test.Address;
import com.uaihebert.model.test.Car;
import com.uaihebert.model.test.Manufacturer;
import com.uaihebert.model.test.Person;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BasicTest extends AbstractTest {

    @Test
    public void isListingAll() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isGettingSingleResult() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.id = 1", Person.class);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.andEquals("id", 1);

        final Person person = easyCriteria.getSingleResult();

        assertTrue(personsFromJPQL.size() == 1);

        assertTrue(personsFromJPQL.get(0).equals(person));
    }

    @Test
    public void isWhereNotEqualsWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name <> 'Anna'", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andNotEquals("name", "Anna");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isOrNotEqualsWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name <> 'Anna' or p.name <> 'Mary'", Person.class);

        assertTrue(personsFromJPQL.size() > 1);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orNotEquals("name", "Anna", "Mary");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingOneWhereEqualsBoolean() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.brazilian = true", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("brazilian", true);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingOneOrEqualsBoolean() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.brazilian = true or p.brazilian = false", Person.class);

        assertTrue(personsFromJPQL.size() > 1);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("brazilian", true, false);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingSequentialWhereEqualsBoolean() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.brazilian = true and p.japanese = false", Person.class);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("brazilian", true).andEquals("japanese", false);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingObjectWhereEquals() {

        final Address address = new Address();
        address.setId(1);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("address", address);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.address = :address", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("address", address);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingObjectOrEquals() {

        final Address address = new Address();
        address.setId(1);

        final Address address2 = new Address();
        address2.setId(2);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("address", address);
        parameters.put("address2", address2);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.address = :address or p.address = :address2", Person.class, parameters);

        assertTrue(personsFromJPQL.size() > 1);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("address", address, address2);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingSequentialObjectsWhereEquals() {

        final Address address = new Address();
        address.setId(1);
        final Car car = new Car();
        car.setId(1);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("address", address);
        parameters.put("car", car);

        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.address = :address and p.car = :car", Person.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("address", address).andEquals("car", car);

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAddingSequentialWhereEquals() throws ParseException {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final Date buildingDate = formatter.parse("1/1/1990");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("buildingDate", buildingDate);

        final List<Address> addressesFromJPQL = getListFromJPQL(
                "select a from Address a where a.isOld = false and a.isYellow = true and a.id = 2 and a.streetName = 'Street B' and a.buildingDate = :buildingDate", Address.class,
                parameters);
        assertTrue(addressesFromJPQL.size() > 0);
        final EasyCriteria<Address> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Address.class);

        easyCriteria.andEquals("id", 2).andEquals("buildingDate", buildingDate).andEquals("isOld", false).andEquals("streetName", "Street B").andEquals("isYellow", true);

        final List<Address> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(addressesFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(addressesFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isOrderByWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p order by p.shoesInCloset asc, p.name desc", Person.class);

        final EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.orderByAsc("shoesInCloset").orderByDesc("name");

        final List<Person> easyCriteriaResult = personCriteria.getResultList();

        assertTrue(easyCriteriaResult.size() == personsFromJPQL.size());
        assertTrue(easyCriteriaResult.containsAll(personsFromJPQL));

        for (int i = 0; i < personsFromJPQL.size(); i++) {
            assertEquals(personsFromJPQL.get(i), easyCriteriaResult.get(i));
        }
    }

    @Test
    public void isPaginationWorking() {
        final EntityManager em = getEntityManager();

        final TypedQuery<Person> query = em.createQuery("select d from com.uaihebert.model.test.Person d", Person.class);
        query.setFirstResult(1);
        query.setMaxResults(1);

        final List<Person> personsFirstResult = query.getResultList();
        query.setFirstResult(2);
        query.setMaxResults(1);

        final List<Person> personsSecondResult = query.getResultList();
        assertFalse(personsFirstResult.get(0).equals(personsSecondResult.get(0)));
        assertFalse(personsSecondResult.containsAll(personsFirstResult));

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.setFirstResult(1);
        easyCriteria.setMaxResults(1);

        List<Person> persons = easyCriteria.getResultList();
        assertEquals(persons.size(), personsFirstResult.size());
        assertTrue(persons.containsAll(personsFirstResult));

        easyCriteria.setFirstResult(2);
        easyCriteria.setMaxResults(1);
        persons = easyCriteria.getResultList();

        assertEquals(persons.size(), personsSecondResult.size());
        assertTrue(persons.containsAll(personsSecondResult));
    }

    @Test
    public void isAddingHints() {
        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.addHint("some.hint", "true");
        assertFalse(easyCriteria.getResultList().isEmpty());
    }
}