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
package com.uaihebert.cto;

import com.uaihebert.uaicriteria.UaiCriteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public final class UaiCTOImp<T> implements UaiCriteria<T> {
    private static final boolean DO_NOT_APPLY_LOWER_CASE = false;

    private static final boolean ORDER_BY_ASC = false;
    private static final boolean ORDER_BY_DESC = true;
    final List<JoinHolder> joinList;
    final List<OrderByHolder> orderByList;
    final List<CriteriaConditionHolder> criteriaConditionTypeList;
    final List<String> groupByList = new ArrayList<String>();
    final List<String> multiselectAttributeList = new ArrayList<String>();
    boolean distinct;
    private Integer firstResult;
    private Integer maxResults;


    public UaiCTOImp() {
        orderByList = new ArrayList<OrderByHolder>();
        criteriaConditionTypeList = new ArrayList<CriteriaConditionHolder>();
        joinList = new ArrayList<JoinHolder>();
    }


    public UaiCTOImp(final boolean distinct, final List<OrderByHolder> orderByList, final List<CriteriaConditionHolder> criteriaConditionTypeList, final List<JoinHolder> joinList) {
        this.distinct = distinct;
        this.orderByList = orderByList;
        this.criteriaConditionTypeList = criteriaConditionTypeList;
        this.joinList = joinList;
    }


    @Override
    public List<T> getResultList() {
        throw resultQueryNotAllowedOnCTO();
    }

    private IllegalStateException resultQueryNotAllowedOnCTO() {
        return new IllegalStateException("Hello, I am a Criteria Transfer Object (CTO) Only. I do not have an entity manager do run the query. \n" +
                " To use a CTO correctly there is an other method in the UaiCriteriaFactory: \n" +
                " UaiCriteriaFactory.createQueryCriteria(EntityManager , Class<T>, UaiCriteria)" +
                " The last parameter is where you should pass the CTO");
    }


    @Override
    public T getSingleResult() {
        throw resultQueryNotAllowedOnCTO();
    }


    @Override
    public List getMultiSelectResult() {
        throw resultQueryNotAllowedOnCTO();
    }


    @Override
    public Long count() {
        throw resultQueryNotAllowedOnCTO();
    }


    @Override
    public Long countRegularCriteria() {
        throw resultQueryNotAllowedOnCTO();
    }


    @Override
    public <E> UaiCriteria<E> subQuery(final String subQueryResult, final Class<E> entityClass) {
        throw subQueryNotAllowed();
    }

    private IllegalStateException subQueryNotAllowed() {
        return new IllegalStateException("Hello, sub query is not allowed in CTO [YET]. If you need you this function, please talk with us in the site uaicriteria.com");
    }


    @Override
    public UaiCriteria<T> countAttribute(final String... attributeNameArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.COUNT_ATTRIBUTE, attributeNameArray));
        return this;
    }


    @Override
    public UaiCriteria<T> andEquals(final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.EQUAL, value));
        return this;
    }


    @Override
    public UaiCriteria<T> andEquals(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.EQUAL, value));
        return this;
    }


    @Override
    public UaiCriteria<T> orEquals(final String attributeName, final Object... valueArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.OR_EQUAL, valueArray));
        return this;
    }


    @Override
    public UaiCriteria<T> orStringLike(final String attributeName, final String... stringArray) {
        return orStringLike(DO_NOT_APPLY_LOWER_CASE, attributeName, stringArray);
    }


    @Override
    public UaiCriteria<T> orStringLike(final boolean toLowerCase, final String attributeName, final String... stringArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.OR_LIKE, stringArray));
        return this;
    }


    @Override
    public UaiCriteria<T> orStringNotLike(final String attributeName, final String... stringArray) {
        return orStringNotLike(DO_NOT_APPLY_LOWER_CASE, attributeName, stringArray);
    }


    @Override
    public UaiCriteria<T> orStringNotLike(final boolean toLowerCase, final String attributeName, final String... stringArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.OR_NOT_LIKE, stringArray));
        return this;
    }


    @Override
    public UaiCriteria<T> andIsMemberOf(final Object value, final String collectionName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(collectionName, CriteriaConditionType.IS_MEMBER_OF, value));
        return this;
    }


    @Override
    public UaiCriteria<T> andIsNotMemberOf(final Object value, final String collectionName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(collectionName, CriteriaConditionType.IS_NOT_MEMBER_OF, value));
        return this;
    }


    @Override
    public UaiCriteria<T> orEquals(final boolean toLowerCase, final String attributeName, final String... valueArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.OR_EQUAL, valueArray));
        return this;
    }


    @Override
    public UaiCriteria<T> orEquals(final int index, final String attributeName, final Object... valueArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(index, DO_NOT_APPLY_LOWER_CASE, attributeName, CriteriaConditionType.OR_EQUAL, valueArray));
        return this;
    }


    @Override
    public UaiCriteria<T> orEquals(final boolean toLowerCase, final int index, final String attributeName, final String... valueArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(index, toLowerCase, attributeName, CriteriaConditionType.OR_EQUAL, valueArray));
        return this;
    }


    @Override
    public UaiCriteria<T> andNotEquals(final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.NOT_EQUAL, value));
        return this;
    }


    @Override
    public UaiCriteria<T> andNotEquals(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.NOT_EQUAL, value));
        return this;
    }


    @Override
    public UaiCriteria<T> orNotEquals(final String attributeName, final Object... valueArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.OR_NOT_EQUAL, valueArray));
        return this;
    }


    @Override
    public UaiCriteria<T> orNotEquals(final boolean toLowerCase, final String attributeName, final String... valueArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.OR_NOT_EQUAL, valueArray));
        return this;
    }


    @Override
    public UaiCriteria<T> andGreaterThan(final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.AND_GREATER_THAN, value));
        return this;
    }


    @Override
    public UaiCriteria<T> andGreaterThan(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.AND_GREATER_THAN, value));
        return this;
    }


    @Override
    public UaiCriteria<T> andGreaterOrEqualTo(final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.GREATER_OR_EQUAL_TO, value));
        return this;
    }


    @Override
    public UaiCriteria<T> andGreaterOrEqualTo(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.GREATER_OR_EQUAL_TO, value));
        return this;
    }


    @Override
    public UaiCriteria<T> andLessThan(final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.LESS_THAN, value));
        return this;
    }


    @Override
    public UaiCriteria<T> andLessThan(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.LESS_THAN, value));
        return this;
    }


    @Override
    public UaiCriteria<T> andLessOrEqualTo(final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.LESS_OR_EQUAL_TO, value));
        return this;
    }


    @Override
    public UaiCriteria<T> andLessOrEqualTo(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.LESS_OR_EQUAL_TO, value));
        return this;
    }


    @Override
    public UaiCriteria<T> innerJoin(final String joinName) {
        joinList.add(new JoinHolder(joinName, JoinHolderType.INNER));
        return this;
    }


    @Override
    public UaiCriteria<T> leftJoin(final String joinName) {
        joinList.add(new JoinHolder(joinName, JoinHolderType.LEFT));
        return this;
    }


    @Override
    public UaiCriteria<T> innerJoinFetch(final String joinName) {
        joinList.add(new JoinHolder(joinName, JoinHolderType.INNER_FETCH));
        return this;
    }


    @Override
    public UaiCriteria<T> leftJoinFetch(final String joinName) {
        joinList.add(new JoinHolder(joinName, JoinHolderType.LEFT_FETCH));
        return this;
    }


    @Override
    public UaiCriteria<T> setDistinctTrue() {
        distinct = true;
        return this;
    }


    @Override
    public UaiCriteria<T> andBetween(final String attributeName, final Object valueA, final Object valueB) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.BETWEEN, valueA, valueB));
        return this;
    }


    @Override
    public UaiCriteria<T> andBetween(final boolean toLowerCase, final String attributeName, final String valueA, final String valueB) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.BETWEEN, valueA, valueB));
        return this;
    }


    @Override
    public UaiCriteria<T> andIsNull(final String attributeName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.AND_IS_NULL));
        return this;
    }


    @Override
    public UaiCriteria<T> orIsNull(final String attributeName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.OR_IS_NULL));
        return this;
    }


    @Override
    public UaiCriteria<T> orIsNotNull(final String attributeName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.OR_IS_NOT_NULL));
        return this;
    }

    @Override
    public UaiCriteria<T> orGreaterThan(String attributeName, Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.OR_GREATER_THAN, value));
        return this;
    }

    @Override
    public UaiCriteria<T> orGreaterThan(boolean toLowerCase, String attributeName, String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.OR_GREATER_THAN, value));
        return this;
    }


    @Override
    public UaiCriteria<T> andIsNotNull(final String attributeName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.AND_IS_NOT_NULL));
        return this;
    }


    @Override
    public UaiCriteria<T> andCollectionIsEmpty(final String collectionName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(collectionName, CriteriaConditionType.COLLECTION_IS_EMPTY));
        return this;
    }


    @Override
    public UaiCriteria<T> andCollectionIsNotEmpty(final String collectionName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(collectionName, CriteriaConditionType.COLLECTION_IS_NOT_EMPTY));
        return this;
    }


    @Override
    public UaiCriteria<T> andStringLike(final String attributeName, final String value) {
        return andStringLike(DO_NOT_APPLY_LOWER_CASE, attributeName, value);
    }


    @Override
    public UaiCriteria<T> andStringLike(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.STRING_LIKE, value));
        return this;
    }


    @Override
    public UaiCriteria<T> andStringNotLike(final String attributeName, final String value) {
        return andStringNotLike(DO_NOT_APPLY_LOWER_CASE, attributeName, value);
    }


    @Override
    public <E> UaiCriteria<T> andAttributeIn(final String attributeName, final List<E> attributeList) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.AND_ATTRIBUTE_IN, attributeList.toArray()));
        return this;
    }


    @Override
    public <E> UaiCriteria<T> andAttributeIn(final String attributeName, final UaiCriteria<E> uaiSubQuery) {
        throw subQueryNotAllowed();
    }


    @Override
    public <E> UaiCriteria<T> andAttributeNotIn(final String attributeName, final List<E> attributeList) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.AND_ATTRIBUTE_NOT_IN, attributeList.toArray()));
        return this;
    }


    @Override
    public <E> UaiCriteria<T> andAttributeNotIn(final String attributeName, final UaiCriteria<E> uaiSubQuery) {
        throw subQueryNotAllowed();
    }


    @Override
    public UaiCriteria<T> andStringNotLike(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.STRING_NOT_LIKE, value));
        return this;
    }


    @Override
    public UaiCriteria<T> andStringIn(final String attributeName, final List<String> valueList) {
        return andStringIn(DO_NOT_APPLY_LOWER_CASE, attributeName, valueList);
    }


    @Override
    public UaiCriteria<T> andStringIn(final boolean toLowerCase, final String attributeName, final List<String> valueList) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.STRING_IN, valueList.toArray()));
        return this;
    }


    @Override
    public UaiCriteria<T> andStringNotIn(final String attributeName, final List<String> valueList) {
        return andStringNotIn(DO_NOT_APPLY_LOWER_CASE, attributeName, valueList);
    }


    @Override
    public UaiCriteria<T> andStringNotIn(final boolean toLowerCase, final String attributeName, final List<String> valueList) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.STRING_NOT_IN, valueList.toArray()));
        return this;
    }


    @Override
    public UaiCriteria<T> orderByAsc(final String attributeName) {
        orderByList.add(new OrderByHolder(ORDER_BY_ASC, attributeName));
        return this;
    }


    @Override
    public UaiCriteria<T> orderByDesc(final String attributeName) {
        orderByList.add(new OrderByHolder(ORDER_BY_DESC, attributeName));
        return this;
    }


    @Override
    public UaiCriteria<T> setFirstResult(final Integer firstResult) {
        this.firstResult = firstResult;
        return this;
    }


    @Override
    public UaiCriteria<T> setMaxResults(final Integer maxResults) {
        this.maxResults = maxResults;
        return this;
    }


    @Override
    public UaiCriteria<T> addAndSeparatedByOr(final int index, final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(index, DO_NOT_APPLY_LOWER_CASE, attributeName, CriteriaConditionType.AND_SEPARATED_BY_OR, value));
        return this;
    }


    @Override
    public UaiCriteria<T> addAndSeparatedByOr(final boolean toLowerCase, final int index, final String attributeName, final String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(index, toLowerCase, attributeName, CriteriaConditionType.AND_SEPARATED_BY_OR, value));
        return this;
    }


    @Override
    public UaiCriteria<T> addHint(final String key, final String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.HINT, key, value));
        return this;
    }


    @Override
    public UaiCriteria<T> sum(final String... attributeNameArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.SUM_ATTRIBUTE_ONLY, attributeNameArray));
        return this;
    }


    @Override
    public <N extends Number> UaiCriteria<T> sum(final String attributeName, final N number) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.SUM_ATTRIBUTE_WITH_NUMBER, attributeName, number));
        return this;
    }


    @Override
    public <N extends Number> UaiCriteria<T> sum(final N number, final String attributeName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.SUM_NUMBER_WITH_ATTRIBUTE, attributeName, number));
        return this;
    }


    @Override
    public UaiCriteria<T> diff(final String firstAttribute, final String secondAttribute) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.DIFF_ATTRIBUTE_ONLY, firstAttribute, secondAttribute));
        return this;
    }


    @Override
    public <N extends Number> UaiCriteria<T> diff(final String attributeName, final N number) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.DIFF_ATTRIBUTE_WITH_NUMBER, attributeName, number));
        return this;
    }


    @Override
    public <N extends Number> UaiCriteria<T> diff(final N number, final String attributeName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.DIFF_NUMBER_WITH_ATTRIBUTE, attributeName, number));
        return this;
    }


    @Override
    public UaiCriteria<T> multiply(final String firstAttribute, final String secondAttribute) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.MULTIPLY_ATTRIBUTE_ONLY, firstAttribute, secondAttribute));
        return this;
    }


    @Override
    public <N extends Number> UaiCriteria<T> multiply(final String attributeName, final N number) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.MULTIPLY_ATTRIBUTE_WITH_NUMBER, attributeName, number));
        return this;
    }


    @Override
    public <N extends Number> UaiCriteria<T> multiply(final N number, final String attributeName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.MULTIPLY_NUMBER_WITH_ATTRIBUTE, attributeName, number));
        return this;
    }


    @Override
    public UaiCriteria<T> divide(final String firstAttribute, final String secondAttribute) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.DIV_ATTRIBUTE_ONLY, firstAttribute, secondAttribute));
        return this;
    }


    @Override
    public <N extends Number> UaiCriteria<T> divide(final String attributeName, final N number) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.DIV_ATTRIBUTE_WITH_NUMBER, attributeName, number));
        return this;
    }


    @Override
    public <N extends Number> UaiCriteria<T> divide(final N number, final String attributeName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.DIV_NUMBER_WITH_ATTRIBUTE, attributeName, number));
        return this;
    }


    @Override
    public UaiCriteria<T> module(final String firstAttribute, final String secondAttribute) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.MODULE_ATTRIBUTE_ONLY, firstAttribute, secondAttribute));
        return this;
    }


    @Override
    public UaiCriteria<T> module(final String attributeName, final Integer number) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.MODULE_ATTRIBUTE_WITH_NUMBER, attributeName, number));
        return this;
    }


    @Override
    public UaiCriteria<T> module(final Integer number, final String attributeName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.MODULE_NUMBER_WITH_ATTRIBUTE, attributeName, number));
        return this;
    }


    @Override
    public UaiCriteria<T> average(final String... attributeNameArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.AVG, attributeNameArray));
        return this;
    }


    @Override
    public UaiCriteria<T> square(final String... attributeNameArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.SQUARE, attributeNameArray));
        return this;
    }


    @Override
    public UaiCriteria<T> addMultiSelectAttribute(final String... attributeNameArray) {
        multiselectAttributeList.addAll(Arrays.asList(attributeNameArray));
        return this;
    }


    @Override
    public UaiCriteria<T> groupBy(final String... attributeNameArray) {
        groupByList.addAll(Arrays.asList(attributeNameArray));
        return this;
    }

    Integer getFirstResult() {
        return firstResult;
    }

    Integer getMaxResults() {
        return maxResults;
    }
}
