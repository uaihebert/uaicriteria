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
package com.uaihebert.uaicriteria;

import java.util.List;

/**
 * With a implementation of this interface you will be able to execute JPA criteria actions
 *
 * @param <T> the root entity class
 */
public interface UaiCriteria<T> {
    /**
     * Will use the EntityManager.getResultList() method to return the data.
     *
     * @return a list of objects
     */
    public List<T> getResultList();

    /**
     * Will use the EntityManager.getSingleResult() method to return the data. <br/> <br/>
     * <p/>
     * <b> The JPA might raise the NonUniqueResultException or the NoResultException</b>
     *
     * @return just one object
     */
    public T getSingleResult();

    /**
     * Will get a multiSelect result from a query. A multiSelect query is when you want to select attributes only, and not an Entity.
     * A sample of multiSelect query could be: <code>select avg(p.age), p.gender from Person p group by p.gender</code>
     * <p/>
     * The result will change according to the JPA Provider that you are using, and to the type of the result.
     * <p/>
     * You will need to check the type of the returned result.
     * <p/>
     * If you do:
     * <code>
     * UaiCriteria<Person> uaiCriteria = UaiCriteriaFactory.createMultiSelectCriteria(entityManager, Person.class);
     * uaiCriteria.average("age");
     * </code>
     * <p/>
     * When you do uaiCriteria.getMultiSelectResult().get(0) a class java.lang.Double would be returned
     * <p/>
     * But if you do:
     * <code>
     * UaiCriteria<Person> uaiCriteria = UaiCriteriaFactory.createMultiSelectCriteria(entityManager, Person.class);
     * uaiCriteria.average("age");
     * uaiCriteria.addMultiSelectAttribute("gender");
     * uaiCriteria.groupBy("gender");
     * </code>
     * <p/>
     * When you do uaiCriteria.getMultiSelectResult().get(0) a Object[] would be returned
     *
     * @return A list with the multiSelect result
     */
    public List getMultiSelectResult();

    /**
     * Will create a SubQuery to be used. You can use a SubQuery like br.com.m4u.csu.rest.api.model
     * <code>
     * final UaiCriteria<Department> uaiCriteria = UaiCriteriaFactory.createQueryCriteria(entityManager, Department.class);
     * uaiCriteria.innerJoin("employeeList");
     * <p/>
     * final UaiCriteria<Person> subQuery = uaiCriteria.subQuery("id", Person.class); // id is the result of the sub query
     * subQuery.andEquals("name", "Joseph");
     * <p/>
     * uaiCriteria.andAttributeIn("employeeList.id", subQuery);
     * </code>
     *
     * @param subQueryResult the attribute that will be returned by subQuery
     * @param entityClass    The entity that will run the subQuery
     * @param <E>            The entity type
     * @return the current uaiCriteria
     */
    public <E> UaiCriteria<E> subQuery(final String subQueryResult, final Class<E> entityClass);

    /**
     * Method that uses the "=" of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name = 'Joseph'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andEquals("person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andEquals(final String attributeName, final Object value);

    /**
     * Method that uses the "=" of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name = 'Joseph'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like: <br/>
     * <code>andEquals(true, "person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param attributeName the class attribute name
     * @param value         string to be used in the query
     * @return the current UaiCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     */
    public UaiCriteria<T> andEquals(final boolean toLowerCase, final String attributeName, final String value);

    /**
     * Method that uses the "OR" of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name = 'Joseph' OR p.name = 'Mary' OR p.name = 'Mark'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orEquals("person.dog.name", "Minhoca", "Pipoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param attributeName the class attribute name
     * @param valueArray    to be used in the query
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> orEquals(final String attributeName, final Object... valueArray);

    /**
     * Method that uses the "OR" of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name = 'Joseph' OR p.name = 'Mary' OR p.name = 'Mark'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orEquals(true, "person.dog.name", "Minhoca", "Pipoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param attributeName the class attribute name
     * @param valueArray    to be used in the query
     * @return the current UaiCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     */
    public UaiCriteria<T> orEquals(final boolean toLowerCase, final String attributeName, final String... valueArray);

    /**
     * Method that uses "OR" combined with "AND" expression of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p<br/>
     * where p.name = 'Joseph' OR p.name = 'Mary'<br/>
     * and p.age = 33 or p.age = 43</code> <br/><br/>
     * <p/>
     * To create the query above would be created with the methods: <br/>
     * <code>
     * uaiCriteria.orEquals(1, "name", "Joseph").orEquals(1, "name", "Mary").orEquals(2, "age", "33").orEquals(2, "age", "43");
     * </code><br/><br/>
     * For each group of "ors" an index is used. <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orEquals(1, "person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * If your implementation is Hibernate, you should not use attribute long, use Long instead. There is a open bug, please vote for it: https://hibernate.onjira.com/browse/HHH-7985  <br/><br/>
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param index         the grouped or order
     * @param attributeName the class attribute name
     * @param valueArray    to be used in the query
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> orEquals(int index, final String attributeName, final Object... valueArray);

    /**
     * Method that uses "OR" combined with "AND" expression of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p<br/>
     * where p.name = 'Joseph' OR p.name = 'Mary'<br/>
     * and p.age = 33 or p.age = 43</code> <br/><br/>
     * <p/>
     * To create the query above would be created with the methods: <br/>
     * <code>
     * uaiCriteria.orEquals(1, "name", "Joseph").orEquals(1, "name", "Mary").orEquals(2, "age", "33").orEquals(2, "age", "43");
     * </code><br/><br/>
     * For each group of "ors" an index is used. <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orEquals(true, 1, "person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * If your implementation is Hibernate, you should not use attribute long, use Long instead. There is a open bug, please vote for it: https://hibernate.onjira.com/browse/HHH-7985  <br/><br/>
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param index         the grouped or order
     * @param attributeName the class attribute name
     * @param valueArray    to be used in the query
     * @return the current UaiCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     */
    public UaiCriteria<T> orEquals(final boolean toLowerCase, int index, final String attributeName, final String... valueArray);

    /**
     * Methods that will create a OR condition with a Like function. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name like 'Joseph%' or p.name like '%Mary%'</code> <br/><br/>
     * <p/>
     * <p/>
     * To use it, you could do like: uaiCriteria.orStringLike("name", "Joseph%", "%Mary%");
     * <p/>
     * You should add the % symbol to the string.
     *
     * @param attributeName will be used in the JPQL
     * @param stringArray   of values to be used in the condition
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> orStringLike(final String attributeName, String... stringArray);

    /**
     * Methods that will create a OR condition with a Like function and with LowerCase. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where lower(p.name) like 'Joseph%' or lower(p.name) like '%Mary%'</code> <br/><br/>
     * <p/>
     * <p/>
     * To use it, you could do like: uaiCriteria.orStringLike(true, "name", "Joseph%", "%Mary%");
     * <p/>
     * the boolean attribute equals to <b>true</b> will make the String lowered, the values above will be handled like: "joseph%", "%mary%"
     * <p/>
     * You should add the & symbol to the string
     *
     * @param attributeName will be used in the JPQL
     * @param stringArray   of values to be used in the condition
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> orStringLike(final boolean toLowerCase, final String attributeName, String... stringArray);

    /**
     * Methods that will create a OR condition with a Like function. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name not like 'Joseph%' or p.name not like '%Mary%'</code> <br/><br/>
     * <p/>
     * <p/>
     * To use it, you could do like: uaiCriteria.orStringNotLike("name", "Joseph%", "%Mary%");
     * <p/>
     * You should add the % symbol to the string.
     *
     * @param attributeName will be used in the JPQL
     * @param stringArray   of values to be used in the condition
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> orStringNotLike(final String attributeName, String... stringArray);

    /**
     * Methods that will create a OR condition with a Like function and with LowerCase. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where lower(p.name) not like 'Joseph%' or lower(p.name) not like '%Mary%'</code> <br/><br/>
     * <p/>
     * <p/>
     * To use it, you could do like: uaiCriteria.orStringNotLike(true, "name", "Joseph%", "%Mary%");
     * <p/>
     * the boolean attribute equals to <b>true</b> will make the String lowered, the values above will be handled like: "joseph%", "%mary%"
     * <p/>
     * You should add the & symbol to the string
     *
     * @param attributeName will be used in the JPQL
     * @param stringArray   of values to be used in the condition
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> orStringNotLike(final boolean toLowerCase, final String attributeName, String... stringArray);

    /**
     * Methods that will create a OR condition with a Like function and with LowerCase. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where :email member of p.emailList</code> <br/><br/>
     * <p/>
     * <p/>
     * To use it, you could do like: uaiCriteria.andIsMemberOf("email", "emailList");
     *
     * @param value          the object to be used in the comparison
     * @param collectionName collection that will be checked if it contains the attribute
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andIsMemberOf(Object value, String collectionName);

    /**
     * Methods that will create a OR condition with a Like function and with LowerCase. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where :email member of p.emailList</code> <br/><br/>
     * <p/>
     * <p/>
     * To use it, you could do like: uaiCriteria.andIsMemberOf("email", "emailList");
     *
     * @param value          the object to be used in the comparison
     * @param collectionName collection that will be checked if it contains the attribute
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andIsNotMemberOf(Object value, String collectionName);

    /**
     * Method that uses the "<>" of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name <> 'Joseph'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andNotEquals("person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andNotEquals(final String attributeName, final Object value);

    /**
     * Method that uses the "<>" of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name <> 'Joseph'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andNotEquals(true, "person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current UaiCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     */
    public UaiCriteria<T> andNotEquals(final boolean toLowerCase, final String attributeName, final String value);

    /**
     * Method that uses the "<>" with OR of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name <> 'Anna' or p.name <> 'Mary'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orNotEquals("person.dog.name", "Minhoca", "Pipoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param attributeName the class attribute name
     * @param valueArray    to be used in the query
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> orNotEquals(final String attributeName, final Object... valueArray);

    /**
     * Method that uses the "<>" with OR of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name <> 'Anna' or p.name <> 'Mary'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orNotEquals(true, "person.dog.name", "Minhoca", "Pipoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param attributeName the class attribute name
     * @param valueArray    to be used in the query
     * @return the current UaiCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     */
    public UaiCriteria<T> orNotEquals(final boolean toLowerCase, final String attributeName, final String... valueArray);

    /**
     * Method that uses the ">" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.weight > 10.4d</code> <br/><br/>
     * <p/>
     * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andGreaterThan("person.dog.age", 33)</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andGreaterThan(final String attributeName, final Object value);

    /**
     * Method that uses the ">" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.weight > 10.4d</code> <br/><br/>
     * <p/>
     * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andGreaterThan(true, "person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current UaiCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     */
    public UaiCriteria<T> andGreaterThan(final boolean toLowerCase, final String attributeName, final String value);

    /**
     * Method that uses the ">=" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.weight >= 10.4d</code> <br/><br/>
     * <p/>
     * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andGreaterOrEqualTo("person.dog.age", 33)</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andGreaterOrEqualTo(final String attributeName, final Object value);

    /**
     * Method that uses the ">=" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.weight >= 10.4d</code> <br/><br/>
     * <p/>
     * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andGreaterOrEqualTo(true, "person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current UaiCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     */
    public UaiCriteria<T> andGreaterOrEqualTo(final boolean toLowerCase, final String attributeName, final String value);

    /**
     * Method that uses the "<" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.weight < 10.4d</code> <br/><br/>
     * <p/>
     * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andLessThan("person.dog.age", 33)</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andLessThan(final String attributeName, final Object value);

    /**
     * Method that uses the "<" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.weight < 10.4d</code> <br/><br/>
     * <p/>
     * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andLessThan(true, "person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current UaiCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     */
    public UaiCriteria<T> andLessThan(final boolean toLowerCase, final String attributeName, final String value);

    /**
     * Method that uses the "<=" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.weight < 10.4d</code> <br/><br/>
     * <p/>
     * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andLessOrEqualTo("person.dog.age", 33)</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andLessOrEqualTo(final String attributeName, final Object value);

    /**
     * Method that uses the "<=" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.weight < 10.4d</code> <br/><br/>
     * <p/>
     * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andLessOrEqualTo(true, "person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current UaiCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     */
    public UaiCriteria<T> andLessOrEqualTo(final boolean toLowerCase, final String attributeName, final String value);


    /**
     * Will do a inner join with a class relationship. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p inner join p.dogs d</code> <br/><br/>
     * <p/>
     * <b>If your implementation is OpenJPA, use the setDistinctTrue(), vote this bug: https://issues.apache.org/jira/browse/OPENJPA-2333</b>
     * <p/>
     * Be careful when using different kind of joins (INNER and LEFT in the same query).
     * Some implementations has some problems with it, even with JPQL.
     *
     * @param joinName the relationship to be joined
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> innerJoin(final String joinName);

    /**
     * Will do a left join with a class relationship. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p left join p.dogs d</code> <br/><br/>
     * <p/>
     * <b>If your implementation is OpenJPA, use the setDistinctTrue(), vote this bug: https://issues.apache.org/jira/browse/OPENJPA-2333</b>
     * <p/>
     * Be careful when using different kind of joins (INNER and LEFT in the same query).
     * Some implementations has some problems with it, even with JPQL.
     *
     * @param joinName the relationship to be joined
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> leftJoin(final String joinName);

    /**
     * Will do a inner join fetch with a class relationship.A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p inner join fetch p.dogs d</code> <br/><br/>
     * <p/>
     * Be careful when using different kind of joins (INNER and LEFT in the same query).
     * Some implementations has some problems with it, even with JPQL.
     *
     * @param joinName the relationship to be joined
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> innerJoinFetch(final String joinName);

    /**
     * Will do a inner join fetch with a class relationship.A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p inner join fetch p.dogs d</code> <br/><br/>
     * <p/>
     * Be careful when using different kind of joins (INNER and LEFT in the same query).
     * Some implementations has some problems with it, even with JPQL.
     *
     * @param joinName the relationship to be joined
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> leftJoinFetch(final String joinName);

    /**
     * Will use the distinct word in the query. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select distinct p from Person p</code> <br/><br/>
     *
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> setDistinctTrue();

    /**
     * Method that uses the "between" of the JPQL with object attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.age between 19 and 29</code> <br/><br/>
     * <p/>
     * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andBetween("person.dog.age", 33, 34)</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param attributeName the class attribute name
     * @param valueA        the first number (smaller value)
     * @param valueB        the last number (greater value)
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andBetween(final String attributeName, final Object valueA, final Object valueB);

    /**
     * Method that uses the "between" of the JPQL with object attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.age between 19 and 29</code> <br/><br/>
     * <p/>
     * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andBetween(true, "person.dog.name", "M", "O")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param attributeName the class attribute name
     * @param valueA        the first string value
     * @param valueB        the last string value
     * @return the current UaiCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     */
    public UaiCriteria<T> andBetween(final boolean toLowerCase, final String attributeName, final String valueA, final String valueB);

    /**
     * Will check if a field is null.<br/><br/> A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name is null</code> <br/><br/>
     * <p/>
     * <b> The JPA will raise exception if this method is used with a Collection </b>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andIsNull("person.dog.name")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     *
     * @param attributeName the class attribute name
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andIsNull(final String attributeName);

    /**
     * Will check if a field is null.<br/><br/> A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name is null or p.age is null</code> <br/><br/>
     * <p/>
     * <b> The JPA will raise exception if this method is used with a Collection </b>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orIsNull("person.dog.name")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     *
     * @param attributeName the class attribute name
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> orIsNull(final String attributeName);

    /**
     * Will check if a field is not null.<br/><br/> A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name is not null</code> <br/><br/>
     * <p/>
     * <b> The JPA will raise exception if this method is used with a Collection </b>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andIsNotNull("person.dog.name")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     *
     * @param attributeName the class attribute name
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andIsNotNull(final String attributeName);

    /**
     * Will check if a field is not null.<br/><br/> A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name is not null or p.age is not null</code> <br/><br/>
     * <p/>
     * <b> The JPA will raise exception if this method is used with a Collection </b>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orIsNotNull("person.dog.name")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     *
     * @param attributeName the class attribute name
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> orIsNotNull(final String attributeName);

    /**
     * Will check if a Collection is empty.<br/><br/>A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.dogs is empty</code> <br/><br/>
     * <p/>
     * This method should be used with java.util.Map, java.util.Collection, java.util.Set and java.util.List <br/>
     * <p/>
     * <b> The JPA may raise exception if this method is used with a non collection attribute or bring unexpected results </b>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andCollectionIsEmpty("person.dogs")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <br/><br/>
     * If you are using EclipseLink prior to version 2.5, use setDistinctTrue() as workaround of this bug: https://bugs.eclipse.org/bugs/show_bug.cgi?id=386354<br/>
     *
     * @param collectionName the class Collection name
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andCollectionIsEmpty(final String collectionName);

    /**
     * Will check if a Collection is not empty.<br/><br/>A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.dogs is not empty</code> <br/><br/>
     * <p/>
     * This method should be used with java.util.Map, java.util.Collection, java.util.Set and java.util.List <br/>
     * <p/>
     * <b> The JPA may raise exception if this method is used with a non collection attribute or bring unexpected results </b>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andCollectionIsNotEmpty("person.dogs")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <br/><br/>
     * If you are using EclipseLink prior to version 2.5, use setDistinctTrue() as workaround of this bug: https://bugs.eclipse.org/bugs/show_bug.cgi?id=386354<br/>
     *
     * @param collectionName the class Collection name
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andCollectionIsNotEmpty(final String collectionName);

    /**
     * Will do a String Like. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name like 'Mar%'</code> <br/><br/>
     * <p/>
     * <b> You must add the % symbol like:</b>
     * <ul>
     * <li>Mar%</li>
     * <li>%ar</li>
     * <li>%ar%</li>
     * </ul>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andStringLike("person.dogs.name", "%Minhoca%")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     *
     * @param attributeName the class attribute name
     * @param value         value to be used as parameter
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andStringLike(final String attributeName, final String value);

    /**
     * Will do a String Like. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name like 'Mar%'</code> <br/><br/>
     * <p/>
     * <b> You must add the % symbol like:</b>
     * <ul>
     * <li>Mar%</li>
     * <li>%ar</li>
     * <li>%ar%</li>
     * </ul>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andStringLike(true, "person.dogs.name", "%Minhoca%")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param attributeName the class attribute name
     * @param value         value to be used as parameter
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andStringLike(final boolean toLowerCase, final String attributeName, final String value);

    /**
     * Will do a String Not Like. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name not like 'Mar%'</code> <br/><br/>
     * <p/>
     * <b> You must add the % symbol like:</b>
     * <ul>
     * <li>Mar%</li>
     * <li>%ar</li>
     * <li>%ar%</li>
     * </ul>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andStringNotLike("person.dogs.name", "%Minhoca%")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     *
     * @param attributeName the class attribute name
     * @param value         value to be used as parameter
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andStringNotLike(final String attributeName, final String value);

    /**
     * Will do a String Not Like. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name not like 'Mar%'</code> <br/><br/>
     * <p/>
     * <b> You must add the % symbol like:</b>
     * <ul>
     * <li>Mar%</li>
     * <li>%ar</li>
     * <li>%ar%</li>
     * </ul>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andStringNotLike(true,"person.dogs.name", "%Minhoca%")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param attributeName the class attribute name
     * @param value         value to be used as parameter
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andStringNotLike(final boolean toLowerCase, final String attributeName, final String value);

    /**
     * Will create a JPQL like below using the IN option:
     * <code>select p from Person p where p.age in :ageList/code>
     *
     * @param attributeName that will be used in the query
     * @param attributeList of values to be compared with the attribute
     * @param <E>           The attribute list class
     * @return the current UaiCriteria instance
     */
    public <E> UaiCriteria<T> andAttributeIn(final String attributeName, final List<E> attributeList);

    /**
     * It will create a JPQL with a customized subQuery. To create a subQuery you should use the {@link #subQuery(String, Class)} method.
     * A subQuery like below could be created:
     * <code>
     * select p from Person p
     * where p.id in (
     * select p.id from Email e
     * where e.status = 'NOT_READ'
     * )
     * </code>
     * <p/>
     * This feature is not allowed in CTO/MultiSelect query [YET]
     *
     * @param attributeName will be returned in the subQuery
     * @param uaiSubQuery   an instance of UaiCriteria to be used as subQuery. You can create one using the method {@link #subQuery(String, Class)}
     * @param <E>           The subType query class
     * @return the current UaiCriteria instance
     */
    public <E> UaiCriteria<T> andAttributeIn(final String attributeName, final UaiCriteria<E> uaiSubQuery);

    /**
     * Will create a JPQL like below using the IN option:
     * <code>select p from Person p where p.age not in :ageList/code>
     *
     * @param attributeName that will be used in the query
     * @param attributeList of values to be compared with the attribute
     * @param <E>           The attribute list class
     * @return the current UaiCriteria instance
     */
    public <E> UaiCriteria<T> andAttributeNotIn(final String attributeName, final List<E> attributeList);

    /**
     * It will create a JPQL with a customized subQuery. To create a subQuery you should use the {@link #subQuery(String, Class)} method.
     * A subQuery like below could be created:
     * <code>
     * select p from Person p
     * where p.id in (
     * select p.id from Email e
     * where e.status = 'NOT_READ'
     * )
     * </code>
     * <p/>
     * This feature is not allowed in CTO/MultiSelect query [YET]
     *
     * @param attributeName will be returned in the subQuery
     * @param uaiSubQuery   an instance of UaiCriteria to be used as subQuery. You can create one using the method {@link #subQuery(String, Class)}
     * @param <E>           The subType query class
     * @return the current UaiCriteria instance
     */
    public <E> UaiCriteria<T> andAttributeNotIn(final String attributeName, final UaiCriteria<E> uaiSubQuery);

    /**
     * Will do a String in. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name in ('A', 'B')</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andStringIn("person.dogs.name", names)</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     *
     * @param attributeName the class attribute name
     * @param valueList     value to be used as parameter
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andStringIn(final String attributeName, final List<String> valueList);

    /**
     * Will do a String in. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name in ('A', 'B')</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andStringIn(true, "person.dogs.name", names)</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param attributeName the class attribute name
     * @param valueList     value to be used as parameter
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andStringIn(final boolean toLowerCase, final String attributeName, final List<String> valueList);

    /**
     * Will do a String not in. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name not in ('A', 'B')</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andStringNotIn("person.dogs.name", names)</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     *
     * @param attributeName the class attribute name
     * @param valueList     value to be used as parameter
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andStringNotIn(final String attributeName, final List<String> valueList);

    /**
     * Will do a String not in. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name not in ('A', 'B')</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andStringNotIn(true, "person.dogs.name", names)</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param attributeName the class attribute name
     * @param valueList     value to be used as parameter
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> andStringNotIn(final boolean toLowerCase, final String attributeName, final List<String> valueList);

    /**
     * Will order your query result. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p order by p.name asc</code> <br/><br/>
     *
     * @param attributeName the attribute name
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> orderByAsc(final String attributeName);

    /**
     * Will order your query result. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p order by p.name desc</code> <br/><br/>
     *
     * @param attributeName the attribute name
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> orderByDesc(final String attributeName);

    /**
     * Set the first Result of the query result
     *
     * @param firstResult the first result index
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> setFirstResult(final Integer firstResult);

    /**
     * Set the max result to be returned
     *
     * @param maxResults the max results to be returned
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> setMaxResults(final Integer maxResults);

    /**
     * Method that will use "AND" separated with "OR" expression of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p<br/>
     * where p.name = 'Joseph' and p.age = 33<br/>
     * or p.name = 'Mary' and p.age = 43</code> <br/><br/>
     * <p/>
     * The query above would be created with the methods: <br/>
     * <code>
     * uaiCriteria.addAndSeparatedByOr(1, "name", "Joseph").addAndSeparatedByOr(1, "age", "33").addAndSeparatedByOr(2, "name", "Mary").addAndSeparatedByOr(2, "age", "43");
     * </code><br/><br/>
     * For each group of "ors" an index is used. <br/><br/>
     * <p/>
     * If your implementation is Hibernate, you should not use attribute long, use Long instead. There is a open bug, please vote for it: https://hibernate.onjira.com/browse/HHH-7985 <br/><br/>
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/><br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param index         the grouped or order
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> addAndSeparatedByOr(final int index, final String attributeName, final Object value);

    /**
     * Method that will use "AND" separated with "OR" expression of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p<br/>
     * where p.name = 'Joseph' and p.age = 33<br/>
     * or p.name = 'Mary' and p.age = 43</code> <br/><br/>
     * <p/>
     * The query above would be created with the methods: <br/>
     * <code>
     * uaiCriteria.addAndSeparatedByOr(1, "name", "Joseph").addAndSeparatedByOr(1, "age", "33").addAndSeparatedByOr(2, "name", "Mary").addAndSeparatedByOr(2, "age", "43");
     * </code><br/><br/>
     * For each group of "ors" an index is used. <br/><br/>
     * <p/>
     * If your implementation is Hibernate, you should not use attribute long, use Long instead. There is a open bug, please vote for it: https://hibernate.onjira.com/browse/HHH-7985 <br/><br/>
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/><br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     *
     * @param toLowerCase   it will do a lowerCase of a String
     * @param index         the grouped or order
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current UaiCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     */
    public UaiCriteria<T> addAndSeparatedByOr(final boolean toLowerCase, final int index, final String attributeName, final String value);

    /**
     * @deprecated {@link #countRegularCriteria()}
     */
    @Deprecated
    public Long count();

    /**
     * Method that will count the query result,
     * but it ignores the valueList defined in setMaxResults and setFirstResult.
     * <br/>
     * To do a count with setMaxResults and setFirstResult it would be necessary to do a subQuery, and subQueries with count are not supported <b>[YET]</b>.
     * <br/>
     * But this is not a problem at all. You could just do the query with max and first result and later:
     * <code> uaiCriteria.setMaxResults(maxResults);
     * List<Person> persons = uaiCriteria.getResultList();
     * <p/>
     * if(persons >= maxResults){
     * count = uaiCriteria.count();
     * } else{
     * count = person.size();
     * }
     *
     * @return total of results of the created criteria
     * </code>
     */
    public Long countRegularCriteria();

    /**
     * Will count an attribute, but the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select count(p.id) from Person p </code>
     *
     * @param attributeNameArray attribute to be counted
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> countAttribute(final String... attributeNameArray);

    /**
     * Add a hint to the query that will be created.
     *
     * @param key   hint key
     * @param value hint value
     */
    public UaiCriteria<T> addHint(final String key, final String value);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select sum(p.id) from Person p </code>
     *
     * @param attributeNameArray attribute to be counted
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> sum(final String... attributeNameArray);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select p.id + 10 from Person p </code>
     *
     * @param attributeName the attribute name to be summed
     * @param number        a number to add to the attribute
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> sum(final String attributeName, final N number);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select p.id + 10 from Person p </code>
     *
     * @param number        a number to add to the attribute
     * @param attributeName the attribute name to be summed
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> sum(final N number, final String attributeName);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select p.age - p.firstKissAge from Person p </code>
     *
     * @param firstAttribute  to be used
     * @param secondAttribute to be used
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> diff(final String firstAttribute, final String secondAttribute);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select p.age - 10 from Person p </code>
     *
     * @param attributeName the attribute name
     * @param number        a number to be used
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> diff(final String attributeName, final N number);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select 10 - p.age from Person p </code>
     *
     * @param number        a number to be used
     * @param attributeName the attribute name
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> diff(final N number, final String attributeName);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select p.age * p.firstKissAge from Person p </code>
     *
     * @param firstAttribute  to be used
     * @param secondAttribute to be used
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> multiply(final String firstAttribute, final String secondAttribute);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select p.age * 10 from Person p </code>
     *
     * @param attributeName the attribute name
     * @param number        a number to be used
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> multiply(final String attributeName, final N number);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select 10 * p.age from Person p </code>
     *
     * @param number        a number to be used
     * @param attributeName the attribute name
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> multiply(final N number, final String attributeName);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select p.age / p.firstKissAge from Person p </code>
     *
     * @param firstAttribute  to be used
     * @param secondAttribute to be used
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> divide(final String firstAttribute, final String secondAttribute);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select p.age / 10 from Person p </code>
     *
     * @param attributeName the attribute name
     * @param number        a number to be used
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> divide(final String attributeName, final N number);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select 10 / p.age from Person p </code>
     *
     * @param number        a number to be used
     * @param attributeName the attribute name
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> divide(final N number, final String attributeName);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select p.age % p.firstKissAge from Person p </code>
     *
     * @param firstAttribute  to be used
     * @param secondAttribute to be used
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> module(final String firstAttribute, final String secondAttribute);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select p.age % 10 from Person p </code>
     *
     * @param attributeName the attribute name
     * @param number        a number to be used
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> module(final String attributeName, final Integer number);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select 10 % p.age from Person p </code>
     *
     * @param number        a number to be used
     * @param attributeName the attribute name
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> module(final Integer number, final String attributeName);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select avg(p.age) from Person p </code>
     *
     * @param attributeNameArray the attribute name
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> average(final String... attributeNameArray);

    /**
     * Will execute a JPQL function, the return will be an multiSelect query. The JPQL would be like:
     * <p/>
     * <code>select sqrt(p.age) from Person p </code>
     *
     * @param attributeNameArray the attribute name
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> square(final String... attributeNameArray);

    /**
     * Will execute a JPQL by select the returned attributes. The JPQL would be like:
     * <p/>
     * <code>select p.age, p.name, p.gender from Person p </code>
     *
     * @param attributeNameArray attributes to be returned
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> addMultiSelectAttribute(final String... attributeNameArray);

    /**
     * Will execute a group by in a query. The JPQL would be like:
     * <p/>
     * <code>
     * select avg(p.age), p.gender
     * from Person p
     * group by p.gender
     * </code>
     *
     * @param attributeNameArray attributes to be grouped
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> groupBy(final String... attributeNameArray);
}