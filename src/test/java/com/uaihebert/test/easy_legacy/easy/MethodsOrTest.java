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
import com.uaihebert.model.test.Manufacturer;
import com.uaihebert.model.test.Song;
import com.uaihebert.model.test.SongType;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodsOrTest extends AbstractTest {

    @Test
    public void isTwoOrIdsWorking() {
        final List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where s.id = 1 or s.id = 2", Song.class);

        assertTrue(songsFromJPQL.size() == 2);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);

        easyCriteria.orEquals("id", 1).orEquals("id", 2);

        final List<Song> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isTwoOrAttributesWorking() {
        final List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where s.id = 1 or s.length = 20", Song.class);

        assertTrue(songsFromJPQL.size() == 2);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);

        easyCriteria.orEquals("id", 1).orEquals("length", 20);

        final List<Song> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isThreeOrAttributesWorking() {
        final List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where s.id = 1 or s.length = 40 or s.artist = 'Group 1 Crew'", Song.class);

        assertTrue(songsFromJPQL.size() == 3);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);

        easyCriteria.orEquals("id", 1).orEquals("length", 40).orEquals("artist", "Group 1 Crew");

        final List<Song> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isThreeOrAttributesWithOneRepeatedWorking() {
        final List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where (s.id = 1) or (s.id = 2) or (s.length = 40) or (s.artist = 'Group 1 Crew')", Song.class);

        assertTrue(songsFromJPQL.size() == 4);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);

        easyCriteria.orEquals("id", 1, 2).orEquals("length", 40).orEquals("artist", "Group 1 Crew");

        final List<Song> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isOrAndOneEqualsWorking() {
        final List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where s.artist = 'Red' and (s.id = 11 or s.id = 12 or s.id = 13)", Song.class);

        assertTrue(songsFromJPQL.size() == 3);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);

        easyCriteria.andEquals("artist", "Red").orEquals("id", 11, 12, 13);

        final List<Song> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isOrAndTwoEqualsWorking() {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("type", SongType.ROCK);

        final List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where s.artist = 'Red' and s.type = :type and (s.id = 11 or s.id = 12 or s.id = 13)", Song.class, parameters);

        assertTrue(songsFromJPQL.size() == 3);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);

        easyCriteria.andEquals("artist", "Red").andEquals("type", SongType.ROCK).orEquals("id", 11, 12, 13);

        final List<Song> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isTwoOrGroupedInsideAndWithMoreAttributesWorking() {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("type", SongType.PRAISE);

        final List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where (s.totalDownloads = 20 or s.weight = 10.00) and (s.price = 20.00 or s.type = :type)", Song.class, parameters);

        assertTrue(songsFromJPQL.size() == 2);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);

        easyCriteria.orEquals("totalDownloads", 20L).orEquals("weight", 10.00f).orEquals(2, "price", 20.00d).orEquals(2, "type", SongType.PRAISE);

        final List<Song> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void addAndSeparatedByOr() {
        final List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where (s.id = 1 and s.name = 'Sing Out') or (s.id = 2 and s.name = 'Alive')", Song.class);

        assertTrue(songsFromJPQL.size() == 2);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);

        easyCriteria.addAndSeparatedByOr(1, "id", 1).addAndSeparatedByOr(1, "name", "Sing Out").addAndSeparatedByOr(2, "id", 2).addAndSeparatedByOr(2, "name", "Alive");

        final List<Song> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void addAndSeparatedByOrOtherParameters() {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("type", SongType.PRAISE);

        final List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where (s.totalDownloads = 20 and s.price = 20.00) or (s.weight = 10.00 and s.type = :type)", Song.class, parameters);
        assertTrue(songsFromJPQL.size() == 2);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);
        easyCriteria.addAndSeparatedByOr(1, "totalDownloads", 20L).addAndSeparatedByOr(1, "price", 20.00d).addAndSeparatedByOr(2, "weight", 10.00f).addAndSeparatedByOr(2, "type", SongType.PRAISE);

        final List<Song> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void addOrWithBigDecimal() {
        final List<Car> carsFromJPQL = getListFromJPQL("select c from Car c where c.weight = 20 or c.weight = 30", Car.class);

        assertTrue(carsFromJPQL.size() == 3);

        final EasyCriteria<Car> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Car.class);

        easyCriteria.orEquals("weight", new BigDecimal(20), new BigDecimal(30));

        final List<Car> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(carsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(carsFromJPQL.containsAll(easyCriteriaResult));
    }

    @Test
    public void isAbleToDoTheSameQuerySeveralTimes() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B')";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(easyCriteria.getResultList()));
        assertTrue(personsFromJPQL.containsAll(easyCriteria.getResultList()));
        assertTrue(personsFromJPQL.containsAll(easyCriteria.getResultList()));
        assertTrue(personsFromJPQL.containsAll(easyCriteria.getResultList()));
        assertTrue(personsFromJPQL.containsAll(easyCriteria.getResultList()));
        assertTrue(personsFromJPQL.containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isDifferentOrAndTwoEqualsWorking() {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("type", SongType.ROCK);

        final List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where s.artist = 'Red' and s.type = :type and (s.name = 'Breath Into Me' or s.id = 12)", Song.class, parameters);

        assertTrue(songsFromJPQL.size() == 2);

        final EasyCriteria<Song> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Song.class);

        easyCriteria.andEquals("artist", "Red").andEquals("type", SongType.ROCK).orEquals("id", 12).orEquals("name", "Breath Into Me");

        final List<Song> easyCriteriaResult = easyCriteria.getResultList();

        assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

        assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
    }
}