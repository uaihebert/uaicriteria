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

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommonCTOTestsBigDecimalImp extends AbstractTest implements CommonTests {

    @Test
    public void isAddingOneOrEquals() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orEquals("justBigDecimalA", new BigDecimal(1), new BigDecimal(10));

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("justBigDecimalA", new BigDecimal(1), new BigDecimal(10));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingOneWhereEquals() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("justBigDecimalA", new BigDecimal(1));

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("justBigDecimalA", new BigDecimal(1));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingSequentialWhereEquals() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("justBigDecimalA", new BigDecimal(1)).andEquals("justBigDecimalB", new BigDecimal(10));

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andEquals("justBigDecimalA", new BigDecimal(1)).andEquals("justBigDecimalB", new BigDecimal(10));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isBetweenWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andBetween("justBigDecimalB", new BigDecimal(9), new BigDecimal(11));

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andBetween("justBigDecimalB", new BigDecimal(9), new BigDecimal(11));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isGreaterOrEqualToWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andGreaterOrEqualTo("justBigDecimalB", new BigDecimal(5));

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andGreaterOrEqualTo("justBigDecimalB", new BigDecimal(5));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isLessThanWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andLessThan("justBigDecimalB", new BigDecimal(5));

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andLessThan("justBigDecimalB", new BigDecimal(5));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isLessOrEqualToWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andLessOrEqualTo("justBigDecimalB", new BigDecimal(1));

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andLessOrEqualTo("justBigDecimalB", new BigDecimal(1));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isGreaterThanWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andGreaterThan("justBigDecimalB", new BigDecimal(5));

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.andGreaterThan("justBigDecimalB", new BigDecimal(5));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isJoinBetweenWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("person");
        easyCTO.andJoinBetween("person", "justBigDecimalA", new BigDecimal(0), new BigDecimal(1));

        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria<Dog> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class);
        easyCriteria.innerJoin("person");
        easyCriteria.andJoinBetween("person", "justBigDecimalA", new BigDecimal(0), new BigDecimal(1));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isJoinGreaterOrEqualToWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("car");
        easyCTO.andJoinGreaterOrEqualTo("car", "weight", new BigDecimal(20));

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car");
        easyCriteria.andJoinGreaterOrEqualTo("car", "weight", new BigDecimal(20));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isJoinLessThanWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("car");
        easyCTO.andJoinLessThan("car", "weight", new BigDecimal(30));

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car");
        easyCriteria.andJoinLessThan("car", "weight", new BigDecimal(30));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isJoinLessOrEqualToWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("car");
        easyCTO.andJoinLessOrEqualTo("car", "weight", new BigDecimal(30));

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car");
        easyCriteria.andJoinLessOrEqualTo("car", "weight", new BigDecimal(30));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isJoinGreaterThanWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("car");
        easyCTO.andJoinGreaterThan("car", "weight", new BigDecimal(1));

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car");
        easyCriteria.andJoinGreaterThan("car", "weight", new BigDecimal(1));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isMultipleJoinWithBetweenWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andBetween("products.nickNames.justBigDecimal", new BigDecimal(1), new BigDecimal(2));

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andBetween("products.nickNames.justBigDecimal", new BigDecimal(1), new BigDecimal(2));

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterThanWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andGreaterThan("products.nickNames.justBigDecimal", new BigDecimal(1));

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterThan("products.nickNames.justBigDecimal", new BigDecimal(1));

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithGreaterOrEqualToWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andGreaterOrEqualTo("products.nickNames.justBigDecimal", new BigDecimal(1));

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterOrEqualTo("products.nickNames.justBigDecimal", new BigDecimal(1));

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessThanWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andLessThan("products.nickNames.justBigDecimal", new BigDecimal(2));

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessThan("products.nickNames.justBigDecimal", new BigDecimal(2));

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithLessOrEqualToWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andLessOrEqualTo("products.nickNames.justBigDecimal", new BigDecimal(2));

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessOrEqualTo("products.nickNames.justBigDecimal", new BigDecimal(2));

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithOrWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.orEquals("products.nickNames.justBigDecimal", new BigDecimal(1), new BigDecimal(2));

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.justBigDecimal", new BigDecimal(1), new BigDecimal(2));

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }
}