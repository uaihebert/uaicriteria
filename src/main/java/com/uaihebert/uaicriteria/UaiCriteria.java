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
public interface UaiCriteria<T> extends OrConditions<T>, AndConditions<T> {
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
     * Will get a multiselect result from a query. A multiselect query is when you want to select attributes only, and not an Entity.
     * A sample of multiselect query could be: <code>select avg(p.age), p.gender from Person p group by p.gender</code>
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
     * @return A list with the multiselect result
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
     * Will do a inner join with a class relationship. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p inner join p.dogs d</code> <br/><br/>
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
     * Will count an attribute, but the return will be an multiselect query. The JPQL would be like:
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
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select sum(p.id) from Person p </code>
     *
     * @param attributeNameArray attribute to be counted
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> sum(final String... attributeNameArray);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select p.id + 10 from Person p </code>
     *
     * @param attributeName the attribute name to be summed
     * @param number        a number to add to the attribute
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> sum(final String attributeName, final N number);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select p.id + 10 from Person p </code>
     *
     * @param number        a number to add to the attribute
     * @param attributeName the attribute name to be summed
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> sum(final N number, final String attributeName);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select p.age - p.firstKissAge from Person p </code>
     *
     * @param firstAttribute  to be used
     * @param secondAttribute to be used
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> diff(final String firstAttribute, final String secondAttribute);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select p.age - 10 from Person p </code>
     *
     * @param attributeName the attribute name
     * @param number        a number to be used
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> diff(final String attributeName, final N number);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select 10 - p.age from Person p </code>
     *
     * @param number        a number to be used
     * @param attributeName the attribute name
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> diff(final N number, final String attributeName);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select p.age * p.firstKissAge from Person p </code>
     *
     * @param firstAttribute  to be used
     * @param secondAttribute to be used
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> multiply(final String firstAttribute, final String secondAttribute);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select p.age * 10 from Person p </code>
     *
     * @param attributeName the attribute name
     * @param number        a number to be used
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> multiply(final String attributeName, final N number);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select 10 * p.age from Person p </code>
     *
     * @param number        a number to be used
     * @param attributeName the attribute name
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> multiply(final N number, final String attributeName);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select p.age / p.firstKissAge from Person p </code>
     *
     * @param firstAttribute  to be used
     * @param secondAttribute to be used
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> divide(final String firstAttribute, final String secondAttribute);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select p.age / 10 from Person p </code>
     *
     * @param attributeName the attribute name
     * @param number        a number to be used
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> divide(final String attributeName, final N number);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select 10 / p.age from Person p </code>
     *
     * @param number        a number to be used
     * @param attributeName the attribute name
     * @return the current UaiCriteria instance
     */
    public <N extends Number> UaiCriteria<T> divide(final N number, final String attributeName);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select p.age % p.firstKissAge from Person p </code>
     *
     * @param firstAttribute  to be used
     * @param secondAttribute to be used
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> module(final String firstAttribute, final String secondAttribute);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select p.age % 10 from Person p </code>
     *
     * @param attributeName the attribute name
     * @param number        a number to be used
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> module(final String attributeName, final Integer number);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select 10 % p.age from Person p </code>
     *
     * @param number        a number to be used
     * @param attributeName the attribute name
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> module(final Integer number, final String attributeName);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
     * <p/>
     * <code>select avg(p.age) from Person p </code>
     *
     * @param attributeNameArray the attribute name
     * @return the current UaiCriteria instance
     */
    public UaiCriteria<T> average(final String... attributeNameArray);

    /**
     * Will execute a JPQL function, the return will be an multiselect query. The JPQL would be like:
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