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
import com.uaihebert.model.test.Person;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class MethodsNullEmptyTest extends AbstractTest {

    @Test
    public void isNullWorking() {
        List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.nickName is null", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria1 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria1.andIsNull("nickName");
        assertTrue(personCriteria1.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria1.getResultList().containsAll(personsFromJPQL));

        personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate is null", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria2.andIsNull("birthDayDate");
        assertTrue(personCriteria2.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria2.getResultList().containsAll(personsFromJPQL));

        personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset is null", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria3 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria3.andIsNull("shoesInCloset");
        assertTrue(personCriteria3.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria3.getResultList().containsAll(personsFromJPQL));

        personsFromJPQL = getListFromJPQL("select p from Person p where p.car is null", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria4 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria4.andIsNull("car");
        assertTrue(personCriteria4.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria4.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isNotNullWorking() {
        List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.nickName is not null", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria1 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria1.andIsNotNull("nickName");
        assertTrue(personCriteria1.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria1.getResultList().containsAll(personsFromJPQL));

        personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate is not null", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria2.andIsNotNull("birthDayDate");
        assertTrue(personCriteria2.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria2.getResultList().containsAll(personsFromJPQL));

        personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset is not null", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria3 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria3.andIsNotNull("shoesInCloset");
        assertTrue(personCriteria3.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria3.getResultList().containsAll(personsFromJPQL));

        personsFromJPQL = getListFromJPQL("select p from Person p where p.car is not null", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria4 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria4.andIsNotNull("car");
        assertTrue(personCriteria4.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria4.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isListIsEmptyWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.dogs is empty", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andCollectionIsEmpty("dogs");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isCollectionIsEmptyWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.cats is empty", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andCollectionIsEmpty("cats");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isSetIsEmptyWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.certifications is empty", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andCollectionIsEmpty("certifications");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isListIsNotEmptyWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.dogs is not empty", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andCollectionIsNotEmpty("dogs");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isCollectionIsNotEmptyWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.cats is not empty", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andCollectionIsNotEmpty("cats");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isSetIsNotEmptyWorking() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.certifications is not empty", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> personCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        personCriteria.andCollectionIsNotEmpty("certifications");
        assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
        assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
    }

    @Test
    public void isJoinAttributeNullWorking() {
        List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.nickName is null", Car.class);

        final EasyCriteria<Car> carCriteria1 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        carCriteria1.innerJoin("person");
        carCriteria1.andJoinAttributeIsNull("person", "nickName");
        assertTrue(carCriteria1.getResultList().size() == carsFromJPQL.size());
        assertTrue(carCriteria1.getResultList().containsAll(carsFromJPQL));

        carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.birthDayDate is null", Car.class);

        final EasyCriteria<Car> carCriteria2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        carCriteria2.innerJoin("person");
        carCriteria2.andJoinAttributeIsNull("person", "birthDayDate");
        assertTrue(carCriteria2.getResultList().size() == carsFromJPQL.size());
        assertTrue(carCriteria2.getResultList().containsAll(carsFromJPQL));

        carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.clothesInCloset is null", Car.class);

        final EasyCriteria<Car> carCriteria3 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        carCriteria3.innerJoin("person");
        carCriteria3.andJoinAttributeIsNull("person", "clothesInCloset");
        assertTrue(carCriteria3.getResultList().size() == carsFromJPQL.size());
        assertTrue(carCriteria3.getResultList().containsAll(carsFromJPQL));

        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.car is null", Dog.class);

        final EasyCriteria<Dog> dogCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        dogCriteria.innerJoin("person");
        dogCriteria.andJoinAttributeIsNull("person", "car");
        assertTrue(dogCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(dogCriteria.getResultList().containsAll(dogsFromJPQL));
    }

    @Test
    public void isJoinAttributeNotNullWorking() {
        List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.nickName is not null", Car.class);

        final EasyCriteria<Car> carCriteria1 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        carCriteria1.innerJoin("person");
        carCriteria1.andJoinAttributeIsNotNull("person", "nickName");
        assertTrue(carCriteria1.getResultList().size() == carsFromJPQL.size());
        assertTrue(carCriteria1.getResultList().containsAll(carsFromJPQL));

        carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.birthDayDate is not null", Car.class);

        final EasyCriteria<Car> carCriteria2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        carCriteria2.innerJoin("person");
        carCriteria2.andJoinAttributeIsNotNull("person", "birthDayDate");
        assertTrue(carCriteria2.getResultList().size() == carsFromJPQL.size());
        assertTrue(carCriteria2.getResultList().containsAll(carsFromJPQL));

        carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.clothesInCloset is not null", Car.class);

        final EasyCriteria<Car> carCriteria3 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        carCriteria3.innerJoin("person");
        carCriteria3.andJoinAttributeIsNotNull("person", "clothesInCloset");
        assertTrue(carCriteria3.getResultList().size() == carsFromJPQL.size());
        assertTrue(carCriteria3.getResultList().containsAll(carsFromJPQL));

        final List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.car is not null", Dog.class);

        final EasyCriteria<Dog> dogCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        dogCriteria.innerJoin("person");
        dogCriteria.andJoinAttributeIsNotNull("person", "car");
        assertTrue(dogCriteria.getResultList().size() == dogsFromJPQL.size());
        assertTrue(dogCriteria.getResultList().containsAll(dogsFromJPQL));
    }

    @Test
    public void isJoinListIsEmptyWorking() {
        final List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.dogs is empty", Car.class);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinListIsEmpty("person", "dogs");

        assertTrue(easyCriteria.getResultList().size() == carsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(carsFromJPQL));
    }

    @Test
    public void isJoinCollectionIsEmptyWorking() {
        final List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.cats is empty", Car.class);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinCollectionIsEmpty("person", "cats");

        assertTrue(easyCriteria.getResultList().size() == carsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(carsFromJPQL));
    }

    @Test
    public void isJoinSetIsEmptyWorking() {
        final List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.certifications is empty", Car.class);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinSetIsEmpty("person", "certifications");

        assertTrue(easyCriteria.getResultList().size() == carsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(carsFromJPQL));
    }

    @Test
    public void isJoinListIsNotEmptyWorking() {
        final List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.dogs is not empty", Car.class);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinListIsNotEmpty("person", "dogs");

        assertTrue(easyCriteria.getResultList().size() == carsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(carsFromJPQL));
    }

    @Test
    public void isJoinCollectionIsNotEmptyWorking() {
        final List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.cats is not empty", Car.class);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinCollectionIsNotEmpty("person", "cats");

        assertTrue(easyCriteria.getResultList().size() == carsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(carsFromJPQL));
    }

    @Test
    public void isJoinSetIsNotEmptyWorking() {
        final List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.certifications is not empty", Car.class);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinSetIsNotEmpty("person", "certifications");

        assertTrue(easyCriteria.getResultList().size() == carsFromJPQL.size());
        assertTrue(easyCriteria.getResultList().containsAll(carsFromJPQL));
    }
}