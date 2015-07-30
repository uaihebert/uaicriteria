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

import com.uaihebert.uaicriteria.base.element.BasicCriteriaElements;
import com.uaihebert.uaicriteria.base.element.BasicCriteriaElementsFactory;
import com.uaihebert.uaicriteria.criteria.CriteriaCreator;
import com.uaihebert.uaicriteria.criteria.CriteriaOrType;
import com.uaihebert.uaicriteria.criteria.QueryType;
import com.uaihebert.uaicriteria.subquery.SubQueryImp;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;


public class UaiCriteriaImp<T> implements UaiCriteria<T> {
    private static final Logger LOG = Logger.getLogger("uaiCriteria");

    private static final boolean DO_NOT_APPLY_LOWER_CASE = false;

    private static final String IS_EMPTY_BATOO_MESSAGE = "There is a problem with Batoo and this kind of criteria. Check here for more details: http://stackoverflow.com/questions/24942520/how-to-use-criteria-isempty-with-batto";

    private final CriteriaCreator criteriaCreator;
    protected final BasicCriteriaElements basicCriteriaElements;

    public UaiCriteriaImp(final EntityManager entityManager, final Class<T> entityClass, final QueryType queryType) {
        basicCriteriaElements = BasicCriteriaElementsFactory.create(entityManager, entityClass, queryType);
        criteriaCreator = new CriteriaCreator(basicCriteriaElements);
    }

    protected UaiCriteriaImp(final String selectedAttribute, final Class<T> subQueryClass, final BasicCriteriaElements basicCriteriaElements) {
        this.basicCriteriaElements = BasicCriteriaElementsFactory.createSubQuery(selectedAttribute, subQueryClass, basicCriteriaElements);
        criteriaCreator = new CriteriaCreator(this.basicCriteriaElements);
    }

    @Override
    public List<T> getResultList() {
        final TypedQuery<T> query = basicCriteriaElements.getRegularQuery();

        return query.getResultList();
    }

    @Override
    public T getSingleResult() {
        final TypedQuery<T> query = basicCriteriaElements.getRegularQuery();

        return query.getSingleResult();
    }

    @Override
    public List getMultiSelectResult() {
        final TypedQuery<Object> query = basicCriteriaElements.getMultiSelectQuery();

        return query.getResultList();
    }

    @Override
    public <E> UaiCriteria<E> subQuery(final String subQueryResult, final Class<E> entityClass) {
        return new SubQueryImp<E>(subQueryResult, entityClass, basicCriteriaElements);
    }

    @Override
    public Long count() {
        return countRegularCriteria();
    }

    @Override
    public Long countRegularCriteria() {
        final TypedQuery<Long> query = basicCriteriaElements.getCountQuery();

        return query.getSingleResult();
    }

    @Override
    public UaiCriteria<T> countAttribute(final String... attributeNameArray) {
        criteriaCreator.countAttribute(attributeNameArray);
        return this;
    }

    @Override
    public UaiCriteria<T> andEquals(final String attributeName, final Object value) {
        criteriaCreator.andEquals(DO_NOT_APPLY_LOWER_CASE, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> andEquals(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaCreator.andEquals(toLowerCase, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> orEquals(final String attributeName, final Object... valueArray) {
        criteriaCreator.orEquals(DO_NOT_APPLY_LOWER_CASE, attributeName, valueArray);
        return this;
    }

    @Override
    public UaiCriteria<T> orEquals(final boolean toLowerCase, final String attributeName, final String... valueArray) {
        criteriaCreator.orEquals(toLowerCase, attributeName, valueArray);
        return this;
    }

    @Override
    public UaiCriteria<T> orEquals(final int index, final String attributeName, final Object... valueArray) {
        criteriaCreator.orEquals(DO_NOT_APPLY_LOWER_CASE, index, attributeName, valueArray, CriteriaOrType.EQUALS);
        return this;
    }

    @Override
    public UaiCriteria<T> orEquals(final boolean toLowerCase, final int index, final String attributeName, final String... valueArray) {
        criteriaCreator.orEquals(toLowerCase, index, attributeName, valueArray, CriteriaOrType.EQUALS);
        return this;
    }

    @Override
    public UaiCriteria<T> orStringLike(final String attributeName, final String... stringArray) {
        return orStringLike(DO_NOT_APPLY_LOWER_CASE, attributeName, stringArray);
    }

    @Override
    public UaiCriteria<T> orStringLike(final boolean toLowerCase, final String attributeName, final String... stringArray) {
        criteriaCreator.orLike(toLowerCase, attributeName, stringArray);
        return this;
    }

    @Override
    public UaiCriteria<T> orStringNotLike(final String attributeName, final String... stringArray) {
        return orStringNotLike(DO_NOT_APPLY_LOWER_CASE, attributeName, stringArray);
    }

    @Override
    public UaiCriteria<T> orStringNotLike(final boolean toLowerCase, final String attributeName, final String... stringArray) {
        criteriaCreator.orNotLike(toLowerCase, attributeName, stringArray);
        return this;
    }

    @Override
    public UaiCriteria<T> andIsMemberOf(final Object value, final String collectionName) {
        criteriaCreator.andIsMemberOf(value, collectionName);
        return this;
    }

    @Override
    public UaiCriteria<T> andIsNotMemberOf(final Object value, final String collectionName) {
        criteriaCreator.andIsNotMemberOf(value, collectionName);
        return this;
    }

    @Override
    public UaiCriteria<T> andNotEquals(final String attributeName, final Object value) {
        criteriaCreator.andNotEquals(DO_NOT_APPLY_LOWER_CASE, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> andNotEquals(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaCreator.andNotEquals(toLowerCase, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> orNotEquals(final String attributeName, final Object... valueArray) {
        criteriaCreator.orNotEquals(DO_NOT_APPLY_LOWER_CASE, attributeName, valueArray);
        return this;
    }

    @Override
    public UaiCriteria<T> orNotEquals(final boolean toLowerCase, final String attributeName, final String... valueArray) {
        criteriaCreator.orNotEquals(toLowerCase, attributeName, valueArray);
        return this;
    }

    @Override
    public UaiCriteria<T> andGreaterThan(final String attributeName, final Object value) {
        criteriaCreator.andGreaterThan(DO_NOT_APPLY_LOWER_CASE, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> andGreaterThan(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaCreator.andGreaterThan(toLowerCase, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> andGreaterOrEqualTo(final String attributeName, final Object value) {
        criteriaCreator.andGreaterOrEqualTo(DO_NOT_APPLY_LOWER_CASE, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> andGreaterOrEqualTo(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaCreator.andGreaterOrEqualTo(toLowerCase, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> andLessThan(final String attributeName, final Object value) {
        criteriaCreator.andLessThan(DO_NOT_APPLY_LOWER_CASE, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> andLessThan(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaCreator.andLessThan(toLowerCase, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> andLessOrEqualTo(final String attributeName, final Object value) {
        criteriaCreator.andLessOrEqualTo(DO_NOT_APPLY_LOWER_CASE, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> andLessOrEqualTo(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaCreator.andLessOrEqualTo(toLowerCase, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> innerJoin(final String joinName) {
        criteriaCreator.innerJoin(joinName);
        return this;
    }

    @Override
    public UaiCriteria<T> leftJoin(final String joinName) {
        criteriaCreator.leftJoin(joinName);
        return this;
    }

    @Override
    public UaiCriteria<T> innerJoinFetch(final String joinName) {
        criteriaCreator.innerJoinFetch(joinName);
        return this;
    }

    @Override
    public UaiCriteria<T> leftJoinFetch(final String joinName) {
        criteriaCreator.leftJoinFetch(joinName);
        return this;
    }

    @Override
    public UaiCriteria<T> setDistinctTrue() {
        criteriaCreator.setDistinctTrue();
        return this;
    }

    @Override
    public UaiCriteria<T> andBetween(final String attributeName, final Object valueA, final Object valueB) {
        criteriaCreator.andBetween(DO_NOT_APPLY_LOWER_CASE, attributeName, valueA, valueB);
        return this;
    }

    @Override
    public UaiCriteria<T> andBetween(final boolean toLowerCase, final String attributeName, final String valueA, final String valueB) {
        criteriaCreator.andBetween(toLowerCase, attributeName, valueA, valueB);
        return this;
    }

    @Override
    public UaiCriteria<T> andIsNull(final String attributeName) {
        criteriaCreator.andIsNull(attributeName);
        return this;
    }

    @Override
    public UaiCriteria<T> orIsNull(final String attributeName) {
        criteriaCreator.orIsNull(attributeName);
        return this;
    }

    @Override
    public UaiCriteria<T> andIsNotNull(final String attributeName) {
        criteriaCreator.andIsNotNull(attributeName);
        return this;
    }

    @Override
    public UaiCriteria<T> orIsNotNull(final String attributeName) {
        criteriaCreator.orIsNotNull(attributeName);
        return this;
    }

    @Override
    public UaiCriteria<T> orGreaterThan(String attributeName, Object value) {
        criteriaCreator.orGreaterThan(DO_NOT_APPLY_LOWER_CASE, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> orGreaterThan(boolean toLowerCase, String attributeName, String value) {
        criteriaCreator.orGreaterThan(toLowerCase, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> andCollectionIsEmpty(final String collectionName) {
        if (basicCriteriaElements.isBatooProvider()) {
            LOG.warning(IS_EMPTY_BATOO_MESSAGE);
        }

        criteriaCreator.andCollectionIsEmpty(collectionName);
        return this;
    }

    @Override
    public UaiCriteria<T> andCollectionIsNotEmpty(final String collectionName) {
        if (basicCriteriaElements.isBatooProvider()) {
            LOG.warning(IS_EMPTY_BATOO_MESSAGE);
        }

        criteriaCreator.andCollectionIsNotEmpty(collectionName);
        return this;
    }

    @Override
    public UaiCriteria<T> andStringLike(final String attributeName, final String value) {
        return andStringLike(DO_NOT_APPLY_LOWER_CASE, attributeName, value);
    }

    @Override
    public UaiCriteria<T> andStringLike(final boolean toLowerCase, final String attributeName, final String value) {
        if (basicCriteriaElements.isBatooProvider()) {
            LOG.warning("There is a problem with Batoo and this kind of criteria. it does not bring the results, but works with the other providers");
        }

        criteriaCreator.andStringLike(toLowerCase, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> andStringNotLike(final String attributeName, final String value) {
        return andStringNotLike(DO_NOT_APPLY_LOWER_CASE, attributeName, value);
    }

    @Override
    public UaiCriteria<T> andStringNotLike(final boolean toLowerCase, final String attributeName, final String value) {
        if (basicCriteriaElements.isBatooProvider()) {
            LOG.warning("There is a problem with Batoo and this kind of criteria. Check here for more details: http://stackoverflow.com/questions/24942520/how-to-use-criteria-isempty-with-batto");
        }

        criteriaCreator.andStringNotLike(toLowerCase, attributeName, value);
        return this;
    }

    @Override
    public <E> UaiCriteria<T> andAttributeIn(final String attributeName, final List<E> attributeList) {
        criteriaCreator.andAttributeIn(attributeName, attributeList);
        return this;
    }

    @Override
    public <E> UaiCriteria<T> andAttributeIn(final String attributeName, final UaiCriteria<E> uaiSubQuery) {
        if (!(uaiSubQuery instanceof SubQueryImp)) {
            throw new IllegalArgumentException("This is not an acceptable implementation of uaiSubQuery type. \n Are you sure that you passed the correct attribute here?");
        }

        criteriaCreator.andAttributeIn(attributeName, (SubQueryImp) uaiSubQuery);
        return this;
    }

    @Override
    public <E> UaiCriteria<T> andAttributeNotIn(final String attributeName, final List<E> attributeList) {
        criteriaCreator.andAttributeNotIn(attributeName, attributeList);
        return this;
    }

    @Override
    public <E> UaiCriteria<T> andAttributeNotIn(final String attributeName, final UaiCriteria<E> uaiSubQuery) {
        if (!(uaiSubQuery instanceof SubQueryImp)) {
            throw new IllegalArgumentException("This is not an acceptable implementation of uaiSubQuery type. \n Are you sure that you passed the correct attribute here?");
        }

        criteriaCreator.andAttributeNotIn(attributeName, (SubQueryImp) uaiSubQuery);
        return this;
    }

    @Override
    public UaiCriteria<T> andStringIn(final String attributeName, final List<String> values) {
        return andStringIn(DO_NOT_APPLY_LOWER_CASE, attributeName, values);
    }

    @Override
    public UaiCriteria<T> andStringIn(final boolean toLowerCase, final String attributeName, final List<String> values) {
        criteriaCreator.andStringIn(toLowerCase, attributeName, values);
        return this;
    }

    @Override
    public UaiCriteria<T> andStringNotIn(final String attributeName, final List<String> values) {
        return andStringNotIn(DO_NOT_APPLY_LOWER_CASE, attributeName, values);
    }

    @Override
    public UaiCriteria<T> andStringNotIn(final boolean toLowerCase, final String attributeName, final List<String> values) {
        criteriaCreator.andStringNotIn(toLowerCase, attributeName, values);
        return this;
    }

    @Override
    public UaiCriteria<T> orderByAsc(final String attributeName) {
        criteriaCreator.orderByAsc(attributeName);
        return this;
    }

    @Override
    public UaiCriteria<T> orderByDesc(final String attributeName) {
        criteriaCreator.orderByDesc(attributeName);
        return this;
    }

    @Override
    public UaiCriteria<T> setFirstResult(final Integer firstResult) {
        criteriaCreator.setFirstResult(firstResult);
        return this;
    }

    @Override
    public UaiCriteria<T> setMaxResults(final Integer maxResults) {
        criteriaCreator.setMaxResults(maxResults);
        return this;
    }

    @Override
    public UaiCriteria<T> addAndSeparatedByOr(final int index, final String attributeName, final Object value) {
        criteriaCreator.addAndSeparatedByOr(DO_NOT_APPLY_LOWER_CASE, index, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> addAndSeparatedByOr(final boolean toLowerCase, final int index, final String attributeName, final String value) {
        criteriaCreator.addAndSeparatedByOr(toLowerCase, index, attributeName, value);
        return this;
    }

    @Override
    public UaiCriteria<T> addHint(final String key, final String value) {
        criteriaCreator.addHint(key, value);
        return this;
    }

    @Override
    public UaiCriteria<T> sum(final String... attributeNameArray) {
        criteriaCreator.sum(attributeNameArray);
        return this;
    }

    @Override
    public <N extends Number> UaiCriteria<T> sum(final String attributeName, final N number) {
        criteriaCreator.sum(attributeName, number);
        return this;
    }

    @Override
    public <N extends Number> UaiCriteria<T> sum(final N number, final String attributeName) {
        criteriaCreator.sum(number, attributeName);
        return this;
    }

    @Override
    public UaiCriteria<T> diff(final String firstAttribute, final String secondAttribute) {
        criteriaCreator.diff(firstAttribute, secondAttribute);
        return this;
    }

    @Override
    public <N extends Number> UaiCriteria<T> diff(final String attributeName, final N number) {
        criteriaCreator.diff(attributeName, number);
        return this;
    }

    @Override
    public <N extends Number> UaiCriteria<T> diff(final N number, final String attributeName) {
        criteriaCreator.diff(number, attributeName);
        return this;
    }

    @Override
    public UaiCriteria<T> multiply(final String firstAttribute, final String secondAttribute) {
        criteriaCreator.multiply(firstAttribute, secondAttribute);
        return this;
    }

    @Override
    public <N extends Number> UaiCriteria<T> multiply(final String attributeName, final N number) {
        criteriaCreator.multiply(attributeName, number);
        return this;
    }

    @Override
    public <N extends Number> UaiCriteria<T> multiply(final N number, final String attributeName) {
        criteriaCreator.multiply(number, attributeName);
        return this;
    }

    @Override
    public UaiCriteria<T> divide(final String firstAttribute, final String secondAttribute) {
        criteriaCreator.divide(firstAttribute, secondAttribute);
        return this;
    }

    @Override
    public <N extends Number> UaiCriteria<T> divide(final String attributeName, final N number) {
        criteriaCreator.divide(attributeName, number);
        return this;
    }

    @Override
    public <N extends Number> UaiCriteria<T> divide(final N number, final String attributeName) {
        criteriaCreator.divide(number, attributeName);
        return this;
    }

    @Override
    public UaiCriteria<T> module(final String firstAttribute, final String secondAttribute) {
        criteriaCreator.module(firstAttribute, secondAttribute);
        return this;
    }

    @Override
    public UaiCriteria<T> module(final String attributeName, final Integer number) {
        criteriaCreator.module(attributeName, number);
        return this;
    }

    @Override
    public UaiCriteria<T> module(final Integer number, final String attributeName) {
        criteriaCreator.module(number, attributeName);
        return this;
    }

    @Override
    public UaiCriteria<T> average(final String... attributeNameArray) {
        criteriaCreator.avg(attributeNameArray);
        return this;
    }

    @Override
    public UaiCriteria<T> square(final String... attributeNameArray) {
        criteriaCreator.square(attributeNameArray);
        return this;
    }

    @Override
    public UaiCriteria<T> addMultiSelectAttribute(final String... attributeNameArray) {
        criteriaCreator.addMultiSelectSelectAttribute(attributeNameArray);
        return this;
    }

    @Override
    public UaiCriteria<T> groupBy(final String... attributeNameArray) {
        criteriaCreator.groupBy(attributeNameArray);
        return this;
    }
}