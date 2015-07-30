package com.uaihebert.uaicriteria;

import java.util.List;

/**
 * Created by duclad on 7/29/15.
 */
 interface AndConditions<T> {

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
     UaiCriteria<T> andEquals(final String attributeName, final Object value);

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
     UaiCriteria<T> andEquals(final boolean toLowerCase, final String attributeName, final String value);


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
     UaiCriteria<T> andIsMemberOf(Object value, String collectionName);

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
     UaiCriteria<T> andIsNotMemberOf(Object value, String collectionName);

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
     UaiCriteria<T> andNotEquals(final String attributeName, final Object value);

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
     UaiCriteria<T> andNotEquals(final boolean toLowerCase, final String attributeName, final String value);


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
     UaiCriteria<T> andGreaterThan(final String attributeName, final Object value);

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
     UaiCriteria<T> andGreaterThan(final boolean toLowerCase, final String attributeName, final String value);

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
     UaiCriteria<T> andGreaterOrEqualTo(final String attributeName, final Object value);

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
     UaiCriteria<T> andGreaterOrEqualTo(final boolean toLowerCase, final String attributeName, final String value);

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
     UaiCriteria<T> andLessThan(final String attributeName, final Object value);

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
     UaiCriteria<T> andLessThan(final boolean toLowerCase, final String attributeName, final String value);

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
     UaiCriteria<T> andLessOrEqualTo(final String attributeName, final Object value);

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
     UaiCriteria<T> andLessOrEqualTo(final boolean toLowerCase, final String attributeName, final String value);

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
     UaiCriteria<T> andBetween(final String attributeName, final Object valueA, final Object valueB);

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
     UaiCriteria<T> andBetween(final boolean toLowerCase, final String attributeName, final String valueA, final String valueB);

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
     UaiCriteria<T> andIsNull(final String attributeName);

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
     UaiCriteria<T> andCollectionIsEmpty(final String collectionName);

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
     UaiCriteria<T> andCollectionIsNotEmpty(final String collectionName);

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
     UaiCriteria<T> andStringLike(final String attributeName, final String value);

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
     UaiCriteria<T> andStringLike(final boolean toLowerCase, final String attributeName, final String value);

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
     UaiCriteria<T> andStringNotLike(final String attributeName, final String value);

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
     UaiCriteria<T> andStringNotLike(final boolean toLowerCase, final String attributeName, final String value);

    /**
     * Will create a JPQL like below using the IN option:
     * <code>select p from Person p where p.age in :ageList/code>
     *
     * @param attributeName that will be used in the query
     * @param attributeList of values to be compared with the attribute
     * @param <E>           The attribute list class
     * @return the current UaiCriteria instance
     */
     <E> UaiCriteria<T> andAttributeIn(final String attributeName, final List<E> attributeList);

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
     <E> UaiCriteria<T> andAttributeIn(final String attributeName, final UaiCriteria<E> uaiSubQuery);

    /**
     * Will create a JPQL like below using the IN option:
     * <code>select p from Person p where p.age not in :ageList/code>
     *
     * @param attributeName that will be used in the query
     * @param attributeList of values to be compared with the attribute
     * @param <E>           The attribute list class
     * @return the current UaiCriteria instance
     */
     <E> UaiCriteria<T> andAttributeNotIn(final String attributeName, final List<E> attributeList);

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
     <E> UaiCriteria<T> andAttributeNotIn(final String attributeName, final UaiCriteria<E> uaiSubQuery);

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
     UaiCriteria<T> andStringIn(final String attributeName, final List<String> valueList);

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
     UaiCriteria<T> andStringIn(final boolean toLowerCase, final String attributeName, final List<String> valueList);

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
     UaiCriteria<T> andStringNotIn(final String attributeName, final List<String> valueList);

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
     UaiCriteria<T> andStringNotIn(final boolean toLowerCase, final String attributeName, final List<String> valueList);

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
     UaiCriteria<T> andIsNotNull(final String attributeName);

}
