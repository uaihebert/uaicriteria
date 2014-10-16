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
import com.uaihebert.model.test.Song;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.test.easy_legacy.CodeGenerator;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MethodsCTOCaseTest extends AbstractTest {

    @Test
    public void isAndEqualsLowerCaseWorking() {
        final EasyCriteria<Person> criteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        criteria.andEquals("name", CodeGenerator.PERSON01_NAME);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals(true, "name", CodeGenerator.PERSON01_NAME.toLowerCase());

        final EasyCriteria<Person> criteriaCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        assertTrue(criteria.getResultList().containsAll(criteriaCTO.getResultList()));
    }

    @Test
    public void isAndNotEqualsLowerCaseWorking() {
        final EasyCriteria<Person> criteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        criteria.andNotEquals(true, "name", CodeGenerator.PERSON01_NAME.toLowerCase());

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andNotEquals(true, "name", CodeGenerator.PERSON01_NAME.toLowerCase());

        final EasyCriteria<Person> criteriaCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        assertTrue(criteria.getResultList().containsAll(criteriaCTO.getResultList()));
    }

    @Test
    public void isOrEqualsLowerCaseWorking() {
        final EasyCriteria<Person> criteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        criteria.orEquals("name", CodeGenerator.PERSON01_NAME);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orEquals(true, "name", CodeGenerator.PERSON01_NAME.toLowerCase());

        final EasyCriteria<Person> criteriaCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        assertTrue(criteria.getResultList().containsAll(criteriaCTO.getResultList()));
    }

    @Test
    public void isOrEqualsIndexLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orEquals("id", "1").orEquals("id", "2").orEquals(2, "name", "Sing Out").orEquals(2, "name", "Alive");

        final EasyCriteria<Song> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.orEquals("id", "1").orEquals("id", "2").orEquals(true, 2, "name", "Sing Out".toLowerCase()).orEquals(true, 2, "name", "Alive".toLowerCase());

        final EasyCriteria<Song> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isOrNotEqualsLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orNotEquals("name", "Anna", "Mary");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.orNotEquals(true, "name", "Anna".toLowerCase(), "Mary".toLowerCase());

        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isGreaterThanLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andGreaterThan("name", "John");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.andGreaterThan(true, "name", "John".toLowerCase());

        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isGreaterOrEqualToLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andGreaterOrEqualTo("name", "John");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.andGreaterOrEqualTo(true, "name", "John".toLowerCase());
        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isLessThanLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andLessThan("name", "John");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.andLessThan(true, "name", "John".toLowerCase());

        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);
        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isLessOrEqualToLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andLessOrEqualTo("name", "John");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.andLessOrEqualTo(true, "name", "John".toLowerCase());
        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isBetweenLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andBetween("name", "A", "L");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.andBetween(true, "name", "A".toLowerCase(), "L".toLowerCase());
        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);

        assertFalse(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isLikeLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andStringLike("name", "M%");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.andStringLike(true, "name", "M%".toLowerCase());
        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isLikeNotLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andStringNotLike("name", "M%");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.andStringNotLike(true, "name", "M%".toLowerCase());
        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isInLowerCaseWorking() {
        List<String> names = new ArrayList<String>();
        names.add("John");
        names.add("Mary");

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andStringIn("name", names);
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        names = new ArrayList<String>();
        names.add("John".toLowerCase());
        names.add("Mary".toLowerCase());

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.andStringIn(true, "name", names);
        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isNotInLowerCaseWorking() {
        List<String> names = new ArrayList<String>();
        names.add("John");
        names.add("Mary");

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andStringNotIn("name", names);

        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        names = new ArrayList<String>();
        names.add("John".toLowerCase());
        names.add("Mary".toLowerCase());

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.andStringNotIn(true, "name", names);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);
        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isJoinEqualsLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoinFetch("dogs");
        easyCTO.andJoinEquals("dogs", "name", "Fire");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.setDistinctTrue();
        easyCTO2.innerJoinFetch("dogs");
        easyCTO2.andJoinEquals(true, "dogs", "name", "Fire".toLowerCase());

        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);
        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isJoinNotEqualsLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoinFetch("dogs");
        easyCTO.andJoinNotEquals("dogs", "name", "Fire");
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.setDistinctTrue();
        easyCTO2.innerJoinFetch("dogs");
        easyCTO2.andJoinNotEquals(true, "dogs", "name", "Fire".toLowerCase());
        final EasyCriteria<Person> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isJoinGreaterThanLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("person");
        easyCTO.andJoinGreaterThan("person", "name", "Mary");
        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.setDistinctTrue();
        easyCTO2.innerJoin("person");
        easyCTO2.andJoinGreaterThan(true, "person", "name", "Mary".toLowerCase());
        final EasyCriteria<Dog> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isJoinGreaterOrEqualToLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("person");
        easyCTO.andJoinGreaterOrEqualTo("person", "name", "Mary");
        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.setDistinctTrue();
        easyCTO2.innerJoin("person");
        easyCTO2.andJoinGreaterOrEqualTo(true, "person", "name", "Mary".toLowerCase());
        final EasyCriteria<Dog> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isJoinLessThanLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("person");
        easyCTO.andJoinLessThan("person", "name", "Mary");
        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.setDistinctTrue();
        easyCTO2.innerJoin("person");
        easyCTO2.andJoinLessThan(true, "person", "name", "Mary".toLowerCase());
        final EasyCriteria<Dog> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isJoinLessOrEqualToLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("person");
        easyCTO.andJoinLessOrEqualTo("person", "name", "Mary");
        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.setDistinctTrue();
        easyCTO2.innerJoin("person");
        easyCTO2.andJoinLessOrEqualTo(true, "person", "name", "Mary".toLowerCase());
        final EasyCriteria<Dog> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isJoinBetweenLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("person");
        easyCTO.andJoinBetween("person", "name", "A", "L");
        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.innerJoin("person");
        easyCTO2.andJoinBetween(true, "person", "name", "A".toLowerCase(), "L".toLowerCase());
        final EasyCriteria<Dog> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isJoinLikeLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("person");
        easyCTO.andJoinStringLike("person", "name", "M%");
        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.innerJoin("person");
        easyCTO2.andJoinStringLike(true, "person", "name", "m%");
        final EasyCriteria<Dog> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isJoinNotLikeLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("person");
        easyCTO.andJoinStringNotLike("person", "name", "M%");
        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.innerJoin("person");
        easyCTO2.andJoinStringNotLike(true, "person", "name", "m%");
        final EasyCriteria<Dog> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isJoinStringInLowerCaseWorking() {
        final List<String> names = new ArrayList<String>();
        names.add("Mary");
        names.add("John");
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("person");
        easyCTO.andJoinStringIn("person", "name", names);
        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final List<String> names2 = new ArrayList<String>();
        names2.add("Mary".toLowerCase());
        names2.add("John".toLowerCase());

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.innerJoin("person");
        easyCTO2.andJoinStringIn(true, "person", "name", names2);
        final EasyCriteria<Dog> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isJoinStringNotInLowerCaseWorking() {
        final List<String> names = new ArrayList<String>();
        names.add("Mary");
        names.add("John");
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("person");
        easyCTO.andJoinStringNotIn("person", "name", names);
        final EasyCriteria<Dog> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO);

        final List<String> names2 = new ArrayList<String>();
        names2.add("Mary");
        names2.add("John");

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.innerJoin("person");
        easyCTO2.andJoinStringNotIn(true, "person", "name", names2);
        final EasyCriteria<Dog> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Dog.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void addAndSeparatedByOrLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.addAndSeparatedByOr(1, "id", 1).addAndSeparatedByOr(1, "name", "Sing Out").addAndSeparatedByOr(2, "id", 2).addAndSeparatedByOr(2, "name", "Alive");
        final EasyCriteria<Song> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO);

        final EasyCriteria easyCTO2 = EasyCriteriaFactory.createEasyCTO();
        easyCTO2.addAndSeparatedByOr(1, "id", 1).addAndSeparatedByOr(true, 1, "name", "Sing Out".toLowerCase()).addAndSeparatedByOr(2, "id", 2).addAndSeparatedByOr(true, 2, "name", "Alive".toLowerCase());
        final EasyCriteria<Song> easyViewCTO2 = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO2);

        assertTrue(easyViewCTO.getResultList().containsAll(easyViewCTO2.getResultList()));
    }

    @Test
    public void isMultipleLeftJoinLevel3LowerCaseWhereWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.leftJoin("car.color.manufacturer");
        easyCTO.andEquals(true, "car.color.manufacturer.name", "Company B".toLowerCase());
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("car.color.manufacturer");
        easyCriteria.andEquals(true, "car.color.manufacturer.name", "Company B".toLowerCase());

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleLeftJoinLevel1LowerCaseWhereWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.innerJoin("car");
        easyCTO.andEquals(true, "car.name", "Dark Horse".toLowerCase());
        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        final List<Person> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("car");
        easyCriteria.andEquals(true, "car.name", "Dark Horse".toLowerCase());

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithEqualsLowerCaseInCollectionsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andEquals(true, "products.nickNames.name", "NickName B".toLowerCase());
        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andEquals(true, "products.nickNames.name", "NickName B".toLowerCase());

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithStringLikeLowerCaseAttributeWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andStringLike(true, "products.nickNames.name", "% B".toLowerCase());

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringLike(true, "products.nickNames.name", "% B".toLowerCase());

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithStringNotLikeLowerCaseAttributeWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andStringNotLike(true, "products.nickNames.name", "% B".toLowerCase());

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringNotLike(true, "products.nickNames.name", "% B".toLowerCase());

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithStringInLowerCaseAttributeWorking() {
        final List<String> names = new ArrayList<String>();
        names.add("NickName A".toLowerCase());
        names.add("NickName B".toLowerCase());

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andStringIn(true, "products.nickNames.name", names);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringIn(true, "products.nickNames.name", names);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithStringNotInLowerCaseAttributeWorking() {
        final List<String> names = new ArrayList<String>();
        names.add("NickName A");
        names.add("NickName B");

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.andStringNotIn(true, "products.nickNames.name", names);

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringNotIn(true, "products.nickNames.name", names);

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithOrStringLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.orEquals(true, "products.nickNames.name", "NickName A".toLowerCase(), "NickName B".toLowerCase());

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals(true, "products.nickNames.name", "NickName A".toLowerCase(), "NickName B".toLowerCase());

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }

    @Test
    public void isMultipleJoinWithOrWithAndLowerCaseWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.setDistinctTrue();
        easyCTO.innerJoin("products.nickNames");
        easyCTO.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(true, 1, "products.nickNames.name", "NickName A".toLowerCase())
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(true, 2, "products.nickNames.name", "NickName B".toLowerCase());

        final EasyCriteria<Manufacturer> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class, easyCTO);
        final List<Manufacturer> easyViewCTOList = easyViewCTO.getResultList();

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(true, 1, "products.nickNames.name", "NickName A".toLowerCase())
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(true, 2, "products.nickNames.name", "NickName B".toLowerCase());

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(easyViewCTOList.size(), result.size());
        assertTrue(easyViewCTOList.containsAll(result));
    }
}
