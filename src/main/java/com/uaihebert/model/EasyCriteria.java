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
package com.uaihebert.model;

import java.util.List;

/**
 * @author uaiHebert.com
 *         Class to be queried. Can be used like EasyCriteria<Dog> easyCriteria
 * @deprecated As of release 3.0, replaced by {@link com.uaihebert.uaicriteria.UaiCriteria}
 */
@Deprecated
public interface EasyCriteria<T> {

    /**
     * @return a list of objects
     * @deprecated Will use the EntityManager.getResultList() method to return the data.
     */
    @Deprecated
    public List<T> getResultList();

    /**
     * @return just one object
     * @deprecated Will use the EntityManager.getSingleResult() method to return the data. <br/> <br/>
     * <p/>
     * <b> The JPA might raise the NonUniqueResultException or the NoResultException</b>
     */
    @Deprecated
    public T getSingleResult();

    /**
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @deprecated Method that uses the "=" of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name = 'Joseph'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andEquals("person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     */
    @Deprecated
    public EasyCriteria<T> andEquals(String attributeName, Object value);

    /**
     * @param toLowerCase   will lower case of the String
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     * @deprecated Method that uses the "=" of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name = 'Joseph'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like: <br/>
     * <code>andEquals(true, "person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     */
    @Deprecated
    public EasyCriteria<T> andEquals(boolean toLowerCase, String attributeName, Object value);

    /**
     * @param attributeName the class attribute name
     * @param valueArray    to be used in the query
     * @return the current EasyCriteria instance
     * @deprecated Method that uses the "OR" of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name = 'Joseph' OR p.name = 'Mary' OR p.name = 'Mark'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orEquals("person.dog.name", "Minhoca", "Pipoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     */
    @Deprecated
    public EasyCriteria<T> orEquals(String attributeName, Object... valueArray);

    /**
     * @param toLowerCase   will lower case of the String
     * @param attributeName the class attribute name
     * @param valueArray    to be used in the query
     * @return the current EasyCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     * @deprecated Method that uses the "OR" of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name = 'Joseph' OR p.name = 'Mary' OR p.name = 'Mark'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orEquals(true, "person.dog.name", "Minhoca", "Pipoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     */
    @Deprecated
    public EasyCriteria<T> orEquals(boolean toLowerCase, String attributeName, Object... valueArray);

    /**
     * @param index         the grouped or order
     * @param attributeName the class attribute name
     * @param valueArray    to be used in the query
     * @return the current EasyCriteria instance
     * @deprecated Method that uses "OR" combined with "AND" expression of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p<br/>
     * where p.name = 'Joseph' OR p.name = 'Mary'<br/>
     * and p.age = 33 or p.age = 43</code> <br/><br/>
     * <p/>
     * To create the query above would be created with the methods: <br/>
     * <code>
     * easyCriteria.orEquals(1, "name", "Joseph").orEquals(1, "name", "Mary").orEquals(2, "age", "33").orEquals(2, "age", "43");
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
     */
    @Deprecated
    public EasyCriteria<T> orEquals(int index, String attributeName, Object... valueArray);

    /**
     * @param toLowerCase   will lower case of the String
     * @param index         the grouped or order
     * @param attributeName the class attribute name
     * @param valueArray    to be used in the query
     * @return the current EasyCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     * @deprecated Method that uses "OR" combined with "AND" expression of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p<br/>
     * where p.name = 'Joseph' OR p.name = 'Mary'<br/>
     * and p.age = 33 or p.age = 43</code> <br/><br/>
     * <p/>
     * To create the query above would be created with the methods: <br/>
     * <code>
     * easyCriteria.orEquals(1, "name", "Joseph").orEquals(1, "name", "Mary").orEquals(2, "age", "33").orEquals(2, "age", "43");
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
     */
    @Deprecated
    public EasyCriteria<T> orEquals(boolean toLowerCase, int index, String attributeName, Object... valueArray);

    /**
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @deprecated Method that uses the "<>" of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name <> 'Joseph'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andNotEquals("person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     */
    @Deprecated
    public EasyCriteria<T> andNotEquals(String attributeName, Object value);

    /**
     * @param toLowerCase   will lower case of the String
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     * @deprecated Method that uses the "<>" of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name <> 'Joseph'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andNotEquals(true, "person.dog.name", "Minhoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     */
    @Deprecated
    public EasyCriteria<T> andNotEquals(boolean toLowerCase, String attributeName, Object value);

    /**
     * @param attributeName the class attribute name
     * @param valueArray    to be used in the query
     * @return the current EasyCriteria instance
     * @deprecated Method that uses the "<>" with OR of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name <> 'Anna' or p.name <> 'Mary'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orNotEquals("person.dog.name", "Minhoca", "Pipoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     */
    @Deprecated
    public EasyCriteria<T> orNotEquals(String attributeName, Object... valueArray);

    /**
     * @param toLowerCase   will lower case of the String
     * @param attributeName the class attribute name
     * @param valueArray    to be used in the query
     * @return the current EasyCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     * @deprecated Method that uses the "<>" with OR of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name <> 'Anna' or p.name <> 'Mary'</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orNotEquals(true, "person.dog.name", "Minhoca", "Pipoca")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     */
    @Deprecated
    public EasyCriteria<T> orNotEquals(boolean toLowerCase, String attributeName, Object... valueArray);

    /**
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @deprecated Method that uses the ">" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andGreaterThan(String attributeName, Object value);

    /**
     * @param toLowerCase   will lower case of the String
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     * @deprecated Method that uses the ">" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andGreaterThan(boolean toLowerCase, String attributeName, Object value);

    /**
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @deprecated Method that uses the ">=" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andGreaterOrEqualTo(String attributeName, Object value);

    /**
     * @param toLowerCase   will lower case of the String
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     * @deprecated Method that uses the ">=" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andGreaterOrEqualTo(boolean toLowerCase, String attributeName, Object value);

    /**
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @deprecated Method that uses the "<" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andLessThan(String attributeName, Object value);

    /**
     * @param toLowerCase   will lower case of the String
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     * @deprecated Method that uses the "<" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andLessThan(boolean toLowerCase, String attributeName, Object value);

    /**
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @deprecated Method that uses the "<=" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andLessOrEqualTo(String attributeName, Object value);

    /**
     * @param toLowerCase   will lower case of the String
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     * @deprecated Method that uses the "<=" of the JPQL with Double attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andLessOrEqualTo(boolean toLowerCase, String attributeName, Object value);


    /**
     * @param joinName the relationship to be joined
     * @return the current EasyCriteria instance
     * @deprecated Will do a inner join with a class relationship. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p inner join p.dogs d</code> <br/><br/>
     * <p/>
     * <b>If your implementation is OpenJPA, use the setDistinctTrue(), vote this bug: https://issues.apache.org/jira/browse/OPENJPA-2333</b>
     * <p/>
     * Be careful when using different kind of joins (INNER and LEFT in the same query).
     * Some implementations has some problems with it, even with JPQL.
     */
    @Deprecated
    public EasyCriteria<T> innerJoin(String joinName);

    /**
     * @param joinName the relationship to be joined
     * @return the current EasyCriteria instance
     * @deprecated Will do a left join with a class relationship. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p left join p.dogs d</code> <br/><br/>
     * <p/>
     * <b>If your implementation is OpenJPA, use the setDistinctTrue(), vote this bug: https://issues.apache.org/jira/browse/OPENJPA-2333</b>
     * <p/>
     * Be careful when using different kind of joins (INNER and LEFT in the same query).
     * Some implementations has some problems with it, even with JPQL.
     */
    @Deprecated
    public EasyCriteria<T> leftJoin(String joinName);

    /**
     * @param joinName the relationship to be joined
     * @return the current EasyCriteria instance
     * @deprecated Will do a inner join fetch with a class relationship.A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p inner join fetch p.dogs d</code> <br/><br/>
     * <p/>
     * Be careful when using different kind of joins (INNER and LEFT in the same query).
     * Some implementations has some problems with it, even with JPQL.
     */
    @Deprecated
    public EasyCriteria<T> innerJoinFetch(String joinName);

    /**
     * @param joinName the relationship to be joined
     * @return the current EasyCriteria instance
     * @deprecated Will do a inner join fetch with a class relationship.A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p inner join fetch p.dogs d</code> <br/><br/>
     * <p/>
     * Be careful when using different kind of joins (INNER and LEFT in the same query).
     * Some implementations has some problems with it, even with JPQL.
     */
    @Deprecated
    public EasyCriteria<T> leftJoinFetch(String joinName);

    /**
     * @return the current EasyCriteria instance
     * @deprecated Will use the distinct word in the query. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select distinct p from Person p</code> <br/><br/>
     */
    @Deprecated
    public EasyCriteria<T> setDistinctTrue();

    /**
     * @param attributeName the class attribute name
     * @param valueA        the first number (smaller value)
     * @param valueB        the last number (greater value)
     * @return the current EasyCriteria instance
     * @deprecated Method that uses the "between" of the JPQL with object attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andBetween(String attributeName, Object valueA, Object valueB);

    /**
     * @param toLowerCase   will lower case of the String
     * @param attributeName the class attribute name
     * @param valueA        the first number
     * @param valueB        the last number
     * @return the current EasyCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     * @deprecated Method that uses the "between" of the JPQL with object attributes. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andBetween(boolean toLowerCase, String attributeName, Object valueA, Object valueB);

    /**
     * @param attributeName the class attribute name
     * @return the current EasyCriteria instance
     * @deprecated Will check if a field is null.<br/><br/> A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name is null</code> <br/><br/>
     * <p/>
     * <b> The JPA will raise exception if this method is used with a Collection </b>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andIsNull("person.dog.name")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     */
    @Deprecated
    public EasyCriteria<T> andIsNull(String attributeName);

    /**
     * @param attributeName the class attribute name
     * @return the current EasyCriteria instance
     * @deprecated Will check if a field is not null.<br/><br/> A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name is not null</code> <br/><br/>
     * <p/>
     * <b> The JPA will raise exception if this method is used with a Collection </b>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andIsNotNull("person.dog.name")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     */
    @Deprecated
    public EasyCriteria<T> andIsNotNull(String attributeName);

    /**
     * @param collectionName the class Collection name
     * @return the current EasyCriteria instance
     * @deprecated Will check if a Collection is empty.<br/><br/>A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.dogs is empty</code> <br/><br/>
     * <p/>
     * This method should be used with java.util.Collection, java.util.Set and java.util.List <br/>
     * <p/>
     * <b> The JPA may raise exception if this method is used with a non collection attribute or bring unexpected results </b>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andCollectionIsEmpty("person.dogs")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <br/><br/>
     * If you are using EclipseLink prior to version 2.5, use setDistinctTrue() as workaround of this bug: https://bugs.eclipse.org/bugs/show_bug.cgi?id=386354<br/>
     */
    @Deprecated
    public EasyCriteria<T> andCollectionIsEmpty(String collectionName);

    /**
     * @param collectionName the class Collection name
     * @return the current EasyCriteria instance
     * @deprecated Will check if a Collection is not empty.<br/><br/>A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.dogs is not empty</code> <br/><br/>
     * <p/>
     * This method should be used with java.util.Collection, java.util.Set and java.util.List <br/>
     * <p/>
     * <b> The JPA may raise exception if this method is used with a non collection attribute or bring unexpected results </b>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andCollectionIsNotEmpty("person.dogs")</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     * <br/><br/>
     * If you are using EclipseLink prior to version 2.5, use setDistinctTrue() as workaround of this bug: https://bugs.eclipse.org/bugs/show_bug.cgi?id=386354<br/>
     */
    @Deprecated
    public EasyCriteria<T> andCollectionIsNotEmpty(String collectionName);

    /**
     * @param attributeName the class attribute name
     * @param value         value to be used as parameter
     * @return the current EasyCriteria instance
     * @deprecated Will do a String Like. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andStringLike(String attributeName, String value);

    /**
     * @param toLowerCase   will lower case of the String
     * @param attributeName the class attribute name
     * @param value         value to be used as parameter
     * @return the current EasyCriteria instance
     * @deprecated Will do a String Like. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andStringLike(boolean toLowerCase, String attributeName, String value);

    /**
     * @param attributeName the class attribute name
     * @param value         value to be used as parameter
     * @return the current EasyCriteria instance
     * @deprecated Will do a String Not Like. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andStringNotLike(String attributeName, String value);

    /**
     * @param toLowerCase   will lower case of the String
     * @param attributeName the class attribute name
     * @param value         value to be used as parameter
     * @return the current EasyCriteria instance
     * @deprecated Will do a String Not Like. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
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
     */
    @Deprecated
    public EasyCriteria<T> andStringNotLike(boolean toLowerCase, String attributeName, String value);

    /**
     * @param attributeName the class attribute name
     * @param values        value to be used as parameter
     * @return the current EasyCriteria instance
     * @deprecated Will do a String in. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name in ('A', 'B')</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andStringIn("person.dogs.name", names)</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     */
    @Deprecated
    public EasyCriteria<T> andStringIn(String attributeName, List<String> values);

    /**
     * @param toLowerCase   will lower case of the String
     * @param attributeName the class attribute name
     * @param values        value to be used as parameter
     * @return the current EasyCriteria instance
     * @deprecated Will do a String in. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name in ('A', 'B')</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andStringIn(true, "person.dogs.name", names)</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     */
    @Deprecated
    public EasyCriteria<T> andStringIn(boolean toLowerCase, String attributeName, List<String> values);

    /**
     * @param attributeName the class attribute name
     * @param values        value to be used as parameter
     * @return the current EasyCriteria instance
     * @deprecated Will do a String not in. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name not in ('A', 'B')</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andStringNotIn("person.dogs.name", names)</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     */
    @Deprecated
    public EasyCriteria<T> andStringNotIn(String attributeName, List<String> values);

    /**
     * @param toLowerCase   will lower case of the String
     * @param attributeName the class attribute name
     * @param values        value to be used as parameter
     * @return the current EasyCriteria instance
     * @deprecated Will do a String not in. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.name not in ('A', 'B')</code> <br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>andStringNotIn(true, "person.dogs.name", names)</code><br/>
     * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
     */
    @Deprecated
    public EasyCriteria<T> andStringNotIn(boolean toLowerCase, String attributeName, List<String> values);

    /**
     * @param attributeName the attribute name
     * @return the current EasyCriteria instance
     * @deprecated Will order your query result. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p order by p.name asc</code> <br/><br/>
     */
    @Deprecated
    public EasyCriteria<T> orderByAsc(String attributeName);

    /**
     * @param attributeName the attribute name
     * @return the current EasyCriteria instance
     * @deprecated Will order your query result. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p order by p.name desc</code> <br/><br/>
     */
    @Deprecated
    public EasyCriteria<T> orderByDesc(String attributeName);

    /**
     * @param firstResult the first result index
     * @return the current EasyCriteria instance
     * @deprecated Set the first Result of the query result
     */
    @Deprecated
    public EasyCriteria<T> setFirstResult(Integer firstResult);

    /**
     * @param maxResults the max results to be returned
     * @return the current EasyCriteria instance
     * @deprecated Set the max result to be returned
     */
    @Deprecated
    public EasyCriteria<T> setMaxResults(Integer maxResults);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andEquals(String, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinEquals(String joinName, String attributeName, Object value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andEquals(boolean, String, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinEquals(boolean toLowerCase, String joinName, String attributeName, Object value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andNotEquals(String, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinNotEquals(String joinName, String attributeName, Object value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andNotEquals(boolean, String, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinNotEquals(boolean toLowerCase, String joinName, String attributeName, Object value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andGreaterThan(String, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinGreaterThan(String joinName, String attributeName, Object value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andGreaterThan(boolean, String, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinGreaterThan(boolean toLowerCase, String joinName, String attributeName, Object value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andGreaterOrEqualTo(String, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinGreaterOrEqualTo(String joinName, String attributeName, Object value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andGreaterOrEqualTo(boolean, String, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinGreaterOrEqualTo(boolean toLowerCase, String joinName, String attributeName, Object value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andLessThan(String, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinLessThan(String joinName, String attributeName, Object value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andLessThan(boolean, String, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinLessThan(boolean toLowerCase, String joinName, String attributeName, Object value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andLessOrEqualTo(String, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinLessOrEqualTo(String joinName, String attributeName, Object value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andLessOrEqualTo(boolean, String, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinLessOrEqualTo(boolean toLowerCase, String joinName, String attributeName, Object value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andBetween(String, Object, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinBetween(String joinName, String attributeName, Object valueA, Object valueB);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andBetween(boolean, String, Object, Object)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinBetween(boolean toLowerCase, String joinName, String attributeName, Object valueA, Object valueB);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andIsNull(String)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinAttributeIsNull(String joinName, String attributeName);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andIsNotNull(String)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinAttributeIsNotNull(String joinName, String attributeName);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andCollectionIsEmpty(String)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinListIsEmpty(String joinName, String listName);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andCollectionIsEmpty(String)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinCollectionIsEmpty(String joinName, String collectionName);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andCollectionIsEmpty(String)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinSetIsEmpty(String joinName, String setName);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andCollectionIsNotEmpty(String)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinListIsNotEmpty(String joinName, String listName);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andCollectionIsNotEmpty(String)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinCollectionIsNotEmpty(String joinName, String collectionName);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andCollectionIsNotEmpty(String)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinSetIsNotEmpty(String joinName, String setName);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andStringLike(String, String)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinStringLike(String joinName, String attributeName, String value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andStringLike(boolean, String, String)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinStringLike(boolean toLowerCase, String joinName, String attributeName, String value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andStringNotLike(String, String)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinStringNotLike(String joinName, String attributeName, String value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andStringNotLike(boolean, String, String)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinStringNotLike(boolean toLowerCase, String joinName, String attributeName, String value);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andStringIn(String, java.util.List)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinStringIn(String joinName, String attributeName, List<String> values);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andStringIn(boolean, String, java.util.List)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinStringIn(boolean toLowerCase, String joinName, String attributeName, List<String> values);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andStringNotIn(String, java.util.List)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinStringNotIn(String joinName, String attributeName, List<String> values);

    /**
     * @deprecated As of release 3.0, replaced by {@link #andStringNotIn(boolean, String, java.util.List)}
     * Will be removed in a future version.
     */
    @Deprecated
    public EasyCriteria<T> andJoinStringNotIn(boolean toLowerCase, String joinName, String attributeName, List<String> values);

    /**
     * @param index         the grouped or order
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @deprecated Method that will use "AND" separated with "OR" expression of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p<br/>
     * where p.name = 'Joseph' and p.age = 33<br/>
     * or p.name = 'Mary' and p.age = 43</code> <br/><br/>
     * <p/>
     * The query above would be created with the methods: <br/>
     * <code>
     * easyCriteria.addAndSeparatedByOr(1, "name", "Joseph").addAndSeparatedByOr(1, "age", "33").addAndSeparatedByOr(2, "name", "Mary").addAndSeparatedByOr(2, "age", "43");
     * </code><br/><br/>
     * For each group of "ors" an index is used. <br/><br/>
     * <p/>
     * If your implementation is Hibernate, you should not use attribute long, use Long instead. There is a open bug, please vote for it: https://hibernate.onjira.com/browse/HHH-7985 <br/><br/>
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/><br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     */
    @Deprecated
    public EasyCriteria<T> addAndSeparatedByOr(int index, String attributeName, Object value);

    /**
     * @param toLowerCase   will lower case of the String
     * @param index         the grouped or order
     * @param attributeName the class attribute name
     * @param value         to be used in the query
     * @return the current EasyCriteria instance
     * @throws IllegalArgumentException if the value is not String an exception will be thrown
     * @deprecated Method that will use "AND" separated with "OR" expression of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p<br/>
     * where p.name = 'Joseph' and p.age = 33<br/>
     * or p.name = 'Mary' and p.age = 43</code> <br/><br/>
     * <p/>
     * The query above would be created with the methods: <br/>
     * <code>
     * easyCriteria.addAndSeparatedByOr(1, "name", "Joseph").addAndSeparatedByOr(1, "age", "33").addAndSeparatedByOr(2, "name", "Mary").addAndSeparatedByOr(2, "age", "43");
     * </code><br/><br/>
     * For each group of "ors" an index is used. <br/><br/>
     * <p/>
     * If your implementation is Hibernate, you should not use attribute long, use Long instead. There is a open bug, please vote for it: https://hibernate.onjira.com/browse/HHH-7985 <br/><br/>
     * <p/>
     * This method can be used with any kind of "simple" attribute.<br/><br/>
     * <b>This method should not be used with
     * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
     */
    @Deprecated
    public EasyCriteria<T> addAndSeparatedByOr(boolean toLowerCase, int index, String attributeName, Object value);

    /**
     * @return total of results of the created criteria
     * @deprecated Method that will count the query result,
     * but it ignores the values defined in setMaxResults and setFirstResult.
     * <br/>
     * To do a count with setMaxResults and setFirstResult it would be necessary to do a subquery, and subqueries are not supported <b>yet</b>.
     * <br/>
     * But this is not a problem at all. You could just do the query with max and first result and later:
     * <code> easyCriteria.setMaxResults(maxResults);
     * List<Person> persons = easyCriteria.getResultList();
     * <p/>
     * if(persons >= maxResults){
     * count = easyCriteria.count();
     * } else{
     * count = person.size();
     * }
     * </code>
     */
    @Deprecated
    public Long count();

    /**
     * @param key   hint key
     * @param value hint value
     * @deprecated Add a hint to the query that will be created.
     */
    @Deprecated
    public EasyCriteria<T> addHint(String key, String value);
}