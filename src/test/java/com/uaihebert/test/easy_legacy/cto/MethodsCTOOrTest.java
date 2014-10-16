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
import com.uaihebert.model.test.Address;
import com.uaihebert.model.test.Car;
import com.uaihebert.model.test.Person;
import com.uaihebert.model.test.Song;
import com.uaihebert.model.test.SongType;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class MethodsCTOOrTest extends AbstractTest {

    @Test
    public void addAndSeparatedByOr() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.addAndSeparatedByOr(1, "id", 1).addAndSeparatedByOr(1, "name", "Sing Out").addAndSeparatedByOr(2, "id", 2).addAndSeparatedByOr(2, "name", "Alive");

        final EasyCriteria<Song> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.addAndSeparatedByOr(1, "id", 1).addAndSeparatedByOr(1, "name", "Sing Out").addAndSeparatedByOr(2, "id", 2).addAndSeparatedByOr(2, "name", "Alive");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void addAndSeparatedByOrOtherParameters() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.addAndSeparatedByOr(1, "totalDownloads", 20L).addAndSeparatedByOr(1, "price", 20.00d).addAndSeparatedByOr(2, "weight", 10.00f).addAndSeparatedByOr(2, "type", SongType.PRAISE);

        final EasyCriteria<Song> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);

        easyCriteria.addAndSeparatedByOr(1, "totalDownloads", 20L).addAndSeparatedByOr(1, "price", 20.00d).addAndSeparatedByOr(2, "weight", 10.00f).addAndSeparatedByOr(2, "type", SongType.PRAISE);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void addOrWithDecimal() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orEquals("weight", new BigDecimal(20), new BigDecimal(30));

        final EasyCriteria<Car> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class, easyCTO);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);
        easyCriteria.orEquals("weight", new BigDecimal(20), new BigDecimal(30));

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isTwoOrIdsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orEquals("id", 1).orEquals("id", 2);

        final EasyCriteria<Song> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);

        easyCriteria.orEquals("id", 1).orEquals("id", 2);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isTwoOrAttributesWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orEquals("id", 1).orEquals("length", 20);

        final EasyCriteria<Song> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.orEquals("id", 1).orEquals("length", 20);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isThreeOrAttributesWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orEquals("id", 1).orEquals("length", 40).orEquals("artist", "Group 1 Crew");

        final EasyCriteria<Song> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.orEquals("id", 1).orEquals("length", 40).orEquals("artist", "Group 1 Crew");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isThreeOrAttributesWithOneRepeatedWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orEquals("id", 1, 2).orEquals("length", 40).orEquals("artist", "Group 1 Crew");

        final EasyCriteria<Song> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.orEquals("id", 1, 2).orEquals("length", 40).orEquals("artist", "Group 1 Crew");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isOrAndOneEqualsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("artist", "Red").orEquals("id", 11, 12, 13);

        final EasyCriteria<Song> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.andEquals("artist", "Red").orEquals("id", 11, 12, 13);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isOrAndTwoEqualsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("artist", "Red").andEquals("type", SongType.ROCK).orEquals("id", 11, 12, 13);

        final EasyCriteria<Song> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);

        easyCriteria.andEquals("artist", "Red").andEquals("type", SongType.ROCK).orEquals("id", 11, 12, 13);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingObjectOrEquals() {
        final Address address = new Address();
        address.setId(1);

        final Address address2 = new Address();
        address2.setId(2);

        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orEquals("address", address, address2);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("address", address, address2);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingOneOrEqualsBoolean() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orEquals("brazilian", true, false);

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orEquals("brazilian", true, false);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isDifferentOrAndTwoEqualsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.andEquals("artist", "Red").andEquals("type", SongType.ROCK).orEquals("id", 12).orEquals("name", "Breath Into Me");

        final EasyCriteria<Song> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.andEquals("artist", "Red").andEquals("type", SongType.ROCK).orEquals("id", 12).orEquals("name", "Breath Into Me");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isTwoOrGroupedInsideAndWithMoreAttributesWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orEquals("totalDownloads", 20L).orEquals("weight", 10.00f).orEquals(2, "price", 20.00d).orEquals(2, "type", SongType.PRAISE);

        final EasyCriteria<Song> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class, easyCTO);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.orEquals("totalDownloads", 20L).orEquals("weight", 10.00f).orEquals(2, "price", 20.00d).orEquals(2, "type", SongType.PRAISE);

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isOrNotEqualsWorking() {
        final EasyCriteria easyCTO = EasyCriteriaFactory.createEasyCTO();
        easyCTO.orNotEquals("name", "Anna", "Mary");

        final EasyCriteria<Person> easyViewCTO = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);

        easyCriteria.orNotEquals("name", "Anna", "Mary");

        assertTrue(easyViewCTO.getResultList().size() == easyCriteria.getResultList().size());
        assertTrue(easyViewCTO.getResultList().containsAll(easyCriteria.getResultList()));
    }
}