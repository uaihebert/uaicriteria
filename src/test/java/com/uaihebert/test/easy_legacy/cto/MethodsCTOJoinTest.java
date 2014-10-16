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
import com.uaihebert.model.test.Car;
import com.uaihebert.model.test.Dog;
import com.uaihebert.model.test.Manufacturer;
import com.uaihebert.model.test.Person;
import com.uaihebert.model.test.Product;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodsCTOJoinTest extends AbstractTest {

    @Test
    public void isInnerJoinWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("dogs");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("dogs");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(easyViewCTO.getResultList().size(), easyCriteriaResult.size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteriaResult));
    }

    @Test
    public void isWhereLeftJoinWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.leftJoin("dogs");
        easyCTO.andJoinEquals("dogs", "name", "Fire");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        // Workaround for OpenJPA
        easyCriteria.setDistinctTrue();
        easyCriteria.leftJoin("dogs");
        easyCriteria.andJoinEquals("dogs", "name", "Fire");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(easyViewCTO.getResultList().size(), easyCriteriaResult.size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteriaResult));
    }

    @Test
    public void isWhereInnerJoinFetchWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoinFetch("dogs");
        easyCTO.andJoinEquals("dogs", "name", "Fire");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoinFetch("dogs");
        easyCriteria.andJoinEquals("dogs", "name", "Fire");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        final List<Person> resultList = easyViewCTO.getResultList();

        assertEquals(resultList.size(), easyCriteriaResult.size());

        assertTrue(easyCriteriaResult.containsAll(resultList));
    }

    @Test
    public void isWhereJoinNotEqualsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoinFetch("dogs");
        easyCTO.setDistinctTrue();
        easyCTO.andJoinNotEquals("dogs", "name", "Fire");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoinFetch("dogs");
        easyCriteria.setDistinctTrue();
        easyCriteria.andJoinNotEquals("dogs", "name", "Fire");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        final List<Person> resultList = easyViewCTO.getResultList();
        assertEquals(resultList.size(), easyCriteriaResult.size());

        assertTrue(resultList.containsAll(easyCriteriaResult));
    }

    @Test
    public void isWhereLeftJoinFetchWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.leftJoinFetch("dogs");
        easyCTO.andJoinEquals("dogs", "name", "Dark");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("dogs");
        easyCriteria.andJoinEquals("dogs", "name", "Dark");

        final List<Person> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(easyViewCTO.getResultList().size(), easyCriteriaResult.size());

        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteriaResult));
    }

    @Test
    public void isJoinAttributeNullWorking() {
        final EasyCriteria easyCTO1 = EasyCriteriaFactory.createEasyCTO();
        easyCTO1.innerJoin("person");
        easyCTO1.andJoinAttributeIsNull("person", "nickName");

        EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO1);

        final EasyCriteria<Dog> carCriteria1 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        carCriteria1.innerJoin("person");
        carCriteria1.andJoinAttributeIsNull("person", "nickName");

        assertTrue(easyViewCTO.getResultList().size() == carCriteria1.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(carCriteria1.getResultList()));

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.innerJoin("person");
        easyCTO2.andJoinAttributeIsNull("person", "birthDayDate");

        easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO2);

        final EasyCriteria<Dog> carCriteria2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        carCriteria2.innerJoin("person");
        carCriteria2.andJoinAttributeIsNull("person", "birthDayDate");

        assertTrue(easyViewCTO.getResultList().size() == carCriteria2.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(carCriteria2.getResultList()));

        final EasyCriteria easyCTO3 = EasyCriteriaFactory.createEasyCTO();
        easyCTO3.innerJoin("person");
        easyCTO3.andJoinAttributeIsNull("person", "clothesInCloset");

        final EasyCriteria<Car> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO3);

        final EasyCriteria<Car> carCriteria3 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        carCriteria3.innerJoin("person");
        carCriteria3.andJoinAttributeIsNull("person", "clothesInCloset");

        assertTrue(easyViewCTO2.getResultList().size() == carCriteria3.getResultList().size());
        assertTrue(easyViewCTO2.getResultList().containsAll(carCriteria3.getResultList()));

        final EasyCriteria easyCTO4 = EasyCriteriaFactory.createEasyCTO();
        easyCTO4.innerJoin("person");
        easyCTO4.andJoinAttributeIsNull("person", "car");

        easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO4);

        final EasyCriteria<Dog> dogCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        dogCriteria.innerJoin("person");
        dogCriteria.andJoinAttributeIsNull("person", "car");

        assertTrue(easyViewCTO.getResultList().size() == dogCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(dogCriteria.getResultList()));
    }

    @Test
    public void isJoinAttributeNotNullWorking() {
        final EasyCriteria easyCTO1 = EasyCriteriaFactory.createEasyCTO();
        easyCTO1.innerJoin("person");
        easyCTO1.andJoinAttributeIsNotNull("person", "nickName");

        EasyCriteria<Car> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO1);

        final EasyCriteria<Car> carCriteria1 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        carCriteria1.innerJoin("person");
        carCriteria1.andJoinAttributeIsNotNull("person", "nickName");

        assertTrue(easyViewCTO.getResultList().size() == carCriteria1.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(carCriteria1.getResultList()));

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.innerJoin("person");
        easyCTO2.andJoinAttributeIsNotNull("person", "birthDayDate");

        easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO2);

        final EasyCriteria<Car> carCriteria2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        carCriteria2.innerJoin("person");
        carCriteria2.andJoinAttributeIsNotNull("person", "birthDayDate");

        assertTrue(easyViewCTO.getResultList().size() == carCriteria2.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(carCriteria2.getResultList()));

        final EasyCriteria easyCTO3 = EasyCriteriaFactory.createEasyCTO();
        easyCTO3.innerJoin("person");
        easyCTO3.andJoinAttributeIsNotNull("person", "clothesInCloset");

        final EasyCriteria<Car> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO3);

        final EasyCriteria<Car> carCriteria3 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        carCriteria3.innerJoin("person");
        carCriteria3.andJoinAttributeIsNotNull("person", "clothesInCloset");

        assertTrue(easyViewCTO2.getResultList().size() == carCriteria3.getResultList().size());
        assertTrue(easyViewCTO2.getResultList().containsAll(carCriteria3.getResultList()));

        final EasyCriteria easyCTO4 = EasyCriteriaFactory.createEasyCTO();
        easyCTO4.innerJoin("person");
        easyCTO4.andJoinAttributeIsNotNull("person", "car");

        final EasyCriteria<Dog> easyViewCTO3 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO4);

        final EasyCriteria<Dog> dogCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        dogCriteria.innerJoin("person");
        dogCriteria.andJoinAttributeIsNotNull("person", "car");

        assertTrue(easyViewCTO3.getResultList().size() == dogCriteria.getResultList().size());
        assertTrue(easyViewCTO3.getResultList().containsAll(dogCriteria.getResultList()));
    }

    @Test
    public void isJoinListIsEmptyWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("person");
        easyCTO.andJoinListIsEmpty("person", "dogs");

        final EasyCriteria<Car> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinListIsEmpty("person", "dogs");

        if (!isEclipseLink()) {
            assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
            assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        }
    }

    @Test
    public void isJoinCollectionIsEmptyWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("person");
        easyCTO.andJoinCollectionIsEmpty("person", "cats");

        final EasyCriteria<Car> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinCollectionIsEmpty("person", "cats");

        if (!isEclipseLink()) {
            assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
            assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        }
    }

    @Test
    public void isJoinSetIsEmptyWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("person");
        easyCTO.andJoinSetIsEmpty("person", "certifications");

        final EasyCriteria<Car> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinSetIsEmpty("person", "certifications");

        if (!isEclipseLink()) {
            assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
            assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
        }
    }

    @Test
    public void isJoinListIsNotEmptyWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        // Workaround for OpenJPA
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("person");
        easyCTO.andJoinListIsNotEmpty("person", "dogs");

        final EasyCriteria<Car> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinListIsNotEmpty("person", "dogs");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isJoinCollectionIsNotEmptyWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("person");
        easyCTO.andJoinCollectionIsNotEmpty("person", "cats");

        final EasyCriteria<Car> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinCollectionIsNotEmpty("person", "cats");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isJoinSetIsNotEmptyWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("person");
        easyCTO.andJoinSetIsNotEmpty("person", "certifications");

        final EasyCriteria<Car> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinSetIsNotEmpty("person", "certifications");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }


    @Test
    public void isMultipleJoin1LevelWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("car.color");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car.color");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoin2LevelsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("car.color.manufacturer");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car.color.manufacturer");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoin3LevelsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("car.color.manufacturer.products");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car.color.manufacturer.products");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoin4LevelsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("car.color.manufacturer.products.nickNames");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car.color.manufacturer.products.nickNames");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinFetch1LevelWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoinFetch("car.color");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoinFetch("car.color");

        final List<Person> result = easyCriteria.getResultList();
        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinFetch2LevelsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoinFetch("car.color.manufacturer");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoinFetch("car.color.manufacturer");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));

    }

    @Test
    public void isMultipleJoinFetch3LevelsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoinFetch("car.color.manufacturer.products");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoinFetch("car.color.manufacturer.products");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinFetch4LevelsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoinFetch("car.color.manufacturer.products.nickNames");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoinFetch("car.color.manufacturer.products.nickNames");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoin1LevelWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.leftJoin("car.color");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("car.color");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoin2LevelsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.leftJoin("car.color.manufacturer");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("car.color.manufacturer");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoin3LevelsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.leftJoin("car.color.manufacturer.products");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("car.color.manufacturer.products");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoin4LevelsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.leftJoin("car");
        easyCTO.leftJoin("car.color");
        easyCTO.leftJoin("car.color.manufacturer");
        easyCTO.leftJoin("car.color.manufacturer.products");
        easyCTO.leftJoin("car.color.manufacturer.products.nickNames");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.leftJoin("car");
        easyCriteria.leftJoin("car.color");
        easyCriteria.leftJoin("car.color.manufacturer");
        easyCriteria.leftJoin("car.color.manufacturer.products");
        easyCriteria.leftJoin("car.color.manufacturer.products.nickNames");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleMixedJoin4LevelsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("car");
        easyCTO.leftJoin("car.color");
        easyCTO.leftJoin("car.color.manufacturer");
        easyCTO.leftJoin("car.color.manufacturer.products");
        easyCTO.leftJoin("car.color.manufacturer.products.nickNames");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("car");
        easyCriteria.leftJoin("car.color");
        easyCriteria.leftJoin("car.color.manufacturer");
        easyCriteria.leftJoin("car.color.manufacturer.products");
        easyCriteria.leftJoin("car.color.manufacturer.products.nickNames");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoinFetch1LevelWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.leftJoinFetch("car.color");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("car.color");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoinFetch2LevelsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.leftJoinFetch("car.color.manufacturer");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("car.color.manufacturer");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoinFetch3LevelsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.leftJoinFetch("car.color.manufacturer.products");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("car.color.manufacturer.products");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));

    }

    @Test
    public void isMultipleLeftJoinFetch4LevelsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.leftJoinFetch("car.color.manufacturer.products.nickNames");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoinFetch("car.color.manufacturer.products.nickNames");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWhereWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("car.color");
        easyCTO.andEquals("car.color.name", "Red");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car.color");
        easyCriteria.andEquals("car.color.name", "Red");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoinLevel2WhereWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.leftJoin("car");
        easyCTO.innerJoin("car.color");
        easyCTO.andEquals("car.color.name", "Red");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("car");
        easyCriteria.innerJoin("car.color");
        easyCriteria.andEquals("car.color.name", "Red");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoinLevel3WhereWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.leftJoin("car.color.manufacturer");
        easyCTO.andEquals("car.color.manufacturer.name", "Company A");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("car.color.manufacturer");
        easyCriteria.andEquals("car.color.manufacturer.name", "Company A");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithEqualsInCollectionsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andEquals("products.nickNames.name", "NickName B");
        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andEquals("products.nickNames.name", "NickName B");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithEqualsBooleanWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andEquals("products.nickNames.justBoolean", true);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andEquals("products.nickNames.justBoolean", true);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithIsNullAttributeWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andIsNull("products.nickNames.justString");

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andIsNull("products.nickNames.justString");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithIsNotNullAttributeWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andIsNotNull("products.nickNames.justString");

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andIsNotNull("products.nickNames.justString");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithListIsEmptyAttributeWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andCollectionIsEmpty("products.nickNames.justList");

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsEmpty("products.nickNames.justList");

        final List<Manufacturer> result = easyCriteria.getResultList();

        // bug when we do is empty with sjoin
        if (!isEclipseLink()) {
            assertEquals(easyViewCTOList.size(), result.size());
            assertTrue(easyViewCTOList.containsAll(result));
        }
    }

    @Test
    public void isMultipleJoinWithListIsNotEmptyAttributeWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andCollectionIsNotEmpty("products.nickNames.justList");

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsNotEmpty("products.nickNames.justList");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithSetIsEmptyAttributeWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andCollectionIsEmpty("products.nickNames.justSet");

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsEmpty("products.nickNames.justSet");

        final List<Manufacturer> result = easyCriteria.getResultList();

        // bug when we do is empty with join
        if (!isEclipseLink()) {
            assertEquals(easyViewCTOList.size(), result.size());
            assertTrue(easyViewCTOList.containsAll(result));
        }
    }

    @Test
    public void isMultipleJoinWithSetIsNotEmptyAttributeWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andCollectionIsNotEmpty("products.nickNames.justSet");

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsNotEmpty("products.nickNames.justSet");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithCollectionIsEmptyAttributeWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andCollectionIsEmpty("products.nickNames.justCollection");

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsEmpty("products.nickNames.justCollection");

        final List<Manufacturer> result = easyCriteria.getResultList();

        // bug when we do is empty with join
        if (!isEclipseLink()) {
            assertEquals(easyViewCTOList.size(), result.size());
            assertTrue(easyViewCTOList.containsAll(result));
        }
    }

    @Test
    public void isMultipleJoinWithCollectionIsNotEmptyAttributeWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andCollectionIsNotEmpty("products.nickNames.justCollection");

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsNotEmpty("products.nickNames.justCollection");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithCollectionTestIsEmptyAttributeWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("nickNames.justCollection");
        easyCTO.andCollectionIsEmpty("nickNames.justCollection.autoRelationship");

        final EasyCriteria<Product> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Product.class, easyCTO);
        final List<Product> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Product> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Product.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("nickNames.justCollection");
        easyCriteria.andCollectionIsEmpty("nickNames.justCollection.autoRelationship");

        final List<Product> result = easyCriteria.getResultList();

        // bug when we do is empty with join
        if (!isEclipseLink()) {
            assertEquals(easyViewCTOList.size(), result.size());
            assertTrue(easyViewCTOList.containsAll(result));
        }
    }

    @Test
    public void isMultipleJoinWithOrWithAndWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isOrderByWithMultipleJoinWorking01() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("dogs");
        easyCTO.orderByAsc("dogs.name");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("dogs");
        easyCriteria.orderByAsc("dogs.name");

        final List<Person> result = easyCriteria.getResultList();

        final List<Person> resultList = easyViewCTO.getResultList();
        assertEquals(resultList.size(), result.size());

        for (int i = 0; i < resultList.size(); i++) {
            assertEquals(resultList.get(i), result.get(i));
        }
    }

    @Test
    public void isOrderByWithMultipleJoinWorking02() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");
        easyCTO.orderByDesc("products.nickNames.name");
        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");
        easyCriteria.orderByDesc("products.nickNames.name");

        final List<Manufacturer> result = easyCriteria.getResultList();

        final List resultList = easyViewCTO.getResultList();
        assertEquals(resultList.size(), result.size());

        for (int i = 0; i < resultList.size(); i++) {
            assertEquals(resultList.get(i), result.get(i));
        }
    }

    @Test
    public void isOrderByWithMultipleJoinWorking03() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(true, 1, "products.nickNames.name", "NickName A".toLowerCase())
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(true, 2, "products.nickNames.name", "NickName B".toLowerCase());
        easyCTO.orderByDesc("products.nickNames.name");
        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(true, 1, "products.nickNames.name", "NickName A".toLowerCase())
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(true, 2, "products.nickNames.name", "NickName B".toLowerCase());
        easyCriteria.orderByDesc("products.nickNames.name");

        final List<Manufacturer> result = easyCriteria.getResultList();

        final List resultList = easyViewCTO.getResultList();
        assertEquals(resultList.size(), result.size());

        for (int i = 0; i < resultList.size(); i++) {
            assertEquals(resultList.get(i), result.get(i));
        }
    }

    @Test
    public void isOrderByWithMultipleJoinWorking04() throws ParseException {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final Date date = formatter.parse("1/1/2001");
        final Calendar justCalendar = Calendar.getInstance();
        justCalendar.setTime(date);

        final Date date2 = formatter.parse("2/2/2002");
        final Calendar justCalendar2 = Calendar.getInstance();
        justCalendar2.setTime(date2);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justCalendarA", justCalendar);
        parameters.put("justCalendarB", justCalendar2);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.orEquals("products.nickNames.justCalendar", justCalendar, justCalendar2);
        easyCTO.orderByDesc("products.nickNames.name");
        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.justCalendar", justCalendar, justCalendar2);
        easyCriteria.orderByDesc("products.nickNames.name");

        final List<Manufacturer> result = easyCriteria.getResultList();

        final List resultList = easyViewCTO.getResultList();
        assertEquals(resultList.size(), result.size());

        for (int i = 0; i < resultList.size(); i++) {
            assertEquals(resultList.get(i), result.get(i));
        }
    }
}