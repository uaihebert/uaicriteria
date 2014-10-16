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
import com.uaihebert.model.test.Manufacturer;
import com.uaihebert.model.test.Person;
import com.uaihebert.model.test.Product;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.test.easy_legacy.CodeGenerator;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MethodsCountTest extends AbstractTest {

    @Test
    public void isCountWithoutParametersWorking() {
        final String query = "select count(m) from Manufacturer m ";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isExecutingQueryAndCountWithoutParametersWorking() {
        final String query = "select count(m) from Manufacturer m ";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);

        assertSame(personsFromJPQL.get(0), easyCriteria.count());
        final List<Manufacturer> resultList = easyCriteria.getResultList();

        assertEquals(resultList.size(), easyCriteria.count().intValue());
    }

    @Test
    public void isCountingAndGettingResultWithParameters1() {
        final List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.car ca join ca.color co where co.name = 'Red'", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("car.color");
        easyCriteria.andEquals("car.color.name", "Red");

        assertEquals(personsFromJPQL.size(), easyCriteria.count().intValue());

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isCountingAndGettingResultWithParameters2() {
        final String query = "select p from Person p " +
                "left join p.car ca " +
                "left join ca.color co " +
                "left join co.manufacturer manu " +
                "where manu.name = 'Company A'";
        final List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.leftJoin("car.color.manufacturer");
        easyCriteria.andEquals("car.color.manufacturer.name", "Company A");

        assertEquals(personsFromJPQL.size(), easyCriteria.count().intValue());

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());
        assertTrue(personsFromJPQL.containsAll(result));
    }

    @Test
    public void isCountingAndGettingResultWithParameters3() {
        final String query = "select count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.id > 1";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterThan("products.nickNames.id", 1);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters4() {
        final String query = "select count(distinct m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justDouble >= 1d";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterOrEqualTo("products.nickNames.justDouble", 1d);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters5() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justLong < 2";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessThan("products.nickNames.justLong", 2L);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters6() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justFloat <= 2F";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessOrEqualTo("products.nickNames.justFloat", 2F);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters7() {
        final String query = "select count(distinct m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justBigDecimal > 1";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterThan("products.nickNames.justBigDecimal", new BigDecimal(1));

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters8() throws ParseException {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final Date justDate = formatter.parse("2/2/2002");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justDate", justDate);

        final String query = "select count(distinct m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justDate >= :justDate";

        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class, parameters);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andGreaterOrEqualTo("products.nickNames.justDate", justDate);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters9() throws ParseException {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final Date date = formatter.parse("2/2/2002");
        final Calendar justCalendar = Calendar.getInstance();
        justCalendar.setTime(date);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("justCalendar", justCalendar);

        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justCalendar < :justCalendar";

        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class, parameters);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessThan("products.nickNames.justCalendar", justCalendar);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters10() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name <= '" + CodeGenerator.NICKNAME_B_NAME + "'";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andLessOrEqualTo("products.nickNames.name", CodeGenerator.NICKNAME_B_NAME);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters11() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justBoolean = true";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andEquals("products.nickNames.justBoolean", true);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters12() throws ParseException {
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

        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justCalendar between :justCalendarA and :justCalendarB";

        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class, parameters);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andBetween("products.nickNames.justCalendar", justCalendar, justCalendar2);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters13() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justString is null";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andIsNull("products.nickNames.justString");

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters14() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justString is not null";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andIsNotNull("products.nickNames.justString");

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters15() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justList is not empty";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsNotEmpty("products.nickNames.justList");

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters16() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justSet is empty";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andCollectionIsEmpty("products.nickNames.justSet");

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters17() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name like '% B'";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringLike("products.nickNames.name", "% B");

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters18() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name not like '% B'";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringNotLike("products.nickNames.name", "% B");

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters19() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where lower(n.name) like '% b'";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringLike(true, "products.nickNames.name", "% B".toLowerCase());

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters20() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where lower(n.name) not like '% b'";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringNotLike(true, "products.nickNames.name", "% B".toLowerCase());

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters21() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name in ('NickName A', 'NickName B')";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final List<String> names = new ArrayList<String>();
        names.add("NickName A");
        names.add("NickName B");

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringIn("products.nickNames.name", names);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters22() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.name not in ('NickName A', 'NickName B')";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final List<String> names = new ArrayList<String>();
        names.add("NickName A");
        names.add("NickName B");

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringNotIn("products.nickNames.name", names);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters23() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where lower(n.name) in ('nickname a', 'nickname b')";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final List<String> names = new ArrayList<String>();
        names.add("NickName A".toLowerCase());
        names.add("NickName B".toLowerCase());

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringIn(true, "products.nickNames.name", names);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters24() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where lower(n.name) not in ('nickname a', 'nickname b')";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final List<String> names = new ArrayList<String>();
        names.add("NickName A");
        names.add("NickName B");

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.andStringNotIn(true, "products.nickNames.name", names);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters25() {
        final String query = "select count(distinct p) from Product p " +
                "join p.nickNames n " +
                "join n.justCollection j " +
                "where j.autoRelationship is empty";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Product> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Product.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("nickNames.justCollection");
        easyCriteria.andCollectionIsEmpty("nickNames.justCollection.autoRelationship");

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters26() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.id = 1 or n.id = 2";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.id", 1, 2);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters27() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where lower(n.name) = 'nickname a' or lower(n.name) = 'nickname b'";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals(true, "products.nickNames.name", "NickName A".toLowerCase(), "NickName B".toLowerCase());

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters28() throws ParseException {
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

        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justCalendar = :justCalendarA or n.justCalendar = :justCalendarB";

        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class, parameters);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.justCalendar", justCalendar, justCalendar2);

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters29() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B')";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isCountingAndGettingResultWithParameters30() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where (n.id = 1 and lower(n.name) = 'nickname a') or (n.id = 2 and lower(n.name) = 'nickname b')";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(true, 1, "products.nickNames.name", "NickName A".toLowerCase())
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(true, 2, "products.nickNames.name", "NickName B".toLowerCase());

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isAbleToDoTheSameCountSeveralTimes() {
        final String query = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B')";
        final List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
        assertTrue(personsFromJPQL.get(0) > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
        assertEquals(personsFromJPQL.get(0), easyCriteria.count());
    }

    @Test
    public void isAbleToDoTheSameQueryAndCountSeveralTimes() {
        final String query = "select distinct m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B')";
        final List<Manufacturer> personsFromQuery = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromQuery.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.setDistinctTrue();
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromQuery.size(), result.size());

        final String countQuery = "select distinct count(m) from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B')";
        final List<Long> personsFromTotal = getListFromJPQL(countQuery, Long.class);
        assertTrue(personsFromTotal.get(0) > 0);

        assertEquals(personsFromTotal.get(0), easyCriteria.count());
        assertTrue(personsFromQuery.containsAll(easyCriteria.getResultList()));
        assertEquals(personsFromTotal.get(0), easyCriteria.count());
        assertTrue(personsFromQuery.containsAll(easyCriteria.getResultList()));
        assertEquals(personsFromTotal.get(0), easyCriteria.count());
        assertTrue(personsFromQuery.containsAll(easyCriteria.getResultList()));
        assertEquals(personsFromTotal.get(0), easyCriteria.count());
        assertTrue(personsFromQuery.containsAll(easyCriteria.getResultList()));
        assertEquals(personsFromTotal.get(0), easyCriteria.count());
        assertTrue(personsFromQuery.containsAll(easyCriteria.getResultList()));
    }

    @Test
    public void isAddingHints() {
        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.addHint("some.hint", "true");
        assertTrue(easyCriteria.count() > 0);
    }
}