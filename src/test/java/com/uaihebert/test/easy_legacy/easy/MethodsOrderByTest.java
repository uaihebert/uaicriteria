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

public class MethodsOrderByTest extends AbstractTest {

    @Test
    public void isOrderByWithMultipleJoinWorking01() {
        final List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.dogs d order by d.name", Person.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Person> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class);
        easyCriteria.innerJoin("dogs");
        easyCriteria.orderByAsc("dogs.name");

        final List<Person> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());

        for (int i = 0; i < personsFromJPQL.size(); i++) {
            assertEquals(personsFromJPQL.get(i), result.get(i));
        }
    }

    @Test
    public void isOrderByWithMultipleJoinWorking02() {
        final String query = "select m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B') " +
                "order by n.name DESC";
        final List<Manufacturer> personsFromQuery = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromQuery.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");
        easyCriteria.orderByDesc("products.nickNames.name");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromQuery.size(), result.size());

        for (int i = 0; i < personsFromQuery.size(); i++) {
            assertEquals(personsFromQuery.get(i), result.get(i));
        }
    }

    @Test
    public void isOrderByWithMultipleJoinWorking03() {
        final String query = "select m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where (n.id = 1 and lower(n.name) = 'nickname a') or (n.id = 2 and lower(n.name) = 'nickname b') " +
                "order by n.name desc";
        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
                .addAndSeparatedByOr(true, 1, "products.nickNames.name", "NickName A".toLowerCase())
                .addAndSeparatedByOr(2, "products.nickNames.id", 2)
                .addAndSeparatedByOr(true, 2, "products.nickNames.name", "NickName B".toLowerCase());
        easyCriteria.orderByDesc("products.nickNames.name");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());

        for (int i = 0; i < personsFromJPQL.size(); i++) {
            assertEquals(personsFromJPQL.get(i), result.get(i));
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

        final String query = "select m from Manufacturer m " +
                "join m.products p " +
                "join p.nickNames n " +
                "where n.justCalendar = :justCalendarA or n.justCalendar = :justCalendarB " +
                "order by n.name desc";

        final List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
        assertTrue(personsFromJPQL.size() > 0);

        final EasyCriteria<Manufacturer> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Manufacturer.class);
        easyCriteria.innerJoin("products.nickNames");
        easyCriteria.orEquals("products.nickNames.justCalendar", justCalendar, justCalendar2);
        easyCriteria.orderByDesc("products.nickNames.name");

        final List<Manufacturer> result = easyCriteria.getResultList();

        assertEquals(personsFromJPQL.size(), result.size());

        for (int i = 0; i < personsFromJPQL.size(); i++) {
            assertEquals(personsFromJPQL.get(i), result.get(i));
        }
    }
}
