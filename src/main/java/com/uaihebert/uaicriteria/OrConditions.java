package com.uaihebert.uaicriteria;

/**
 * With implementations of this interface you will be able to add OR conditions to your criteria
 *
 * Created by duclad on 7/29/15.
 */
 interface OrConditions<T> {

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
     UaiCriteria<T> orEquals(final String attributeName, final Object... valueArray);

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
     UaiCriteria<T> orEquals(final boolean toLowerCase, final String attributeName, final String... valueArray);

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
     UaiCriteria<T> orEquals(int index, final String attributeName, final Object... valueArray);

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
     UaiCriteria<T> orEquals(final boolean toLowerCase, int index, final String attributeName, final String... valueArray);

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
     UaiCriteria<T> orStringLike(final String attributeName, String... stringArray);

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
     UaiCriteria<T> orStringLike(final boolean toLowerCase, final String attributeName, String... stringArray);

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
     UaiCriteria<T> orStringNotLike(final String attributeName, String... stringArray);

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
     UaiCriteria<T> orStringNotLike(final boolean toLowerCase, final String attributeName, String... stringArray);

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
     UaiCriteria<T> orNotEquals(final String attributeName, final Object... valueArray);

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
     UaiCriteria<T> orNotEquals(final boolean toLowerCase, final String attributeName, final String... valueArray);

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
     UaiCriteria<T> orIsNull(final String attributeName);

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
     UaiCriteria<T> orIsNotNull(final String attributeName);

    /**
     * Method that uses the ">" of the JPQL. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.weight > 10.4d or p.age>30</code> <br/><br/>
     * <p/>
     * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orGreaterThan("person.dog.age", 3)</code><br/>
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
     UaiCriteria<T> orGreaterThan(final String attributeName, final Object value);

    /**
     * Method that uses the ">" of the JPQL with. A JPQL like br.com.m4u.csu.rest.api.model might be created: <br/><br/>
     * <code>select p from Person p where p.weight > 10.4d or p.name>'A'</code> <br/><br/>
     * <p/>
     * You can use this method with a string<br/><br/>
     * <p/>
     * You could use it to do conditions with joins like:<br/>
     * <code>orGreaterThan(true, "person.dog.name", "Minhoca")</code><br/>
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
     UaiCriteria<T> orGreaterThan(final boolean toLowerCase, final String attributeName, final String value);

}
