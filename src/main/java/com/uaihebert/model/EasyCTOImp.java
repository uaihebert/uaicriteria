/*
 * Copyright 2012 uaiHebert Solucoes em Informatica
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

import com.uaihebert.cto.CriteriaConditionHolder;
import com.uaihebert.cto.CriteriaConditionType;
import com.uaihebert.cto.JoinHolder;
import com.uaihebert.cto.JoinHolderType;
import com.uaihebert.cto.OrderByHolder;

import java.util.ArrayList;
import java.util.List;

public class EasyCTOImp<T> implements EasyCriteria<T> {
    private static final boolean DO_NOT_APPLY_LOWER_CASE = false;

    private static final boolean ORDER_BY_ASC = false;
    private static final boolean ORDER_BY_DESC = true;
    private final List<OrderByHolder> orderByList = new ArrayList<OrderByHolder>();
    private final List<CriteriaConditionHolder> criteriaConditionTypeList = new ArrayList<CriteriaConditionHolder>();
    private final List<JoinHolder> joinList = new ArrayList<JoinHolder>();
    private boolean distinct;
    private Integer firstResult;
    private Integer maxResults;

    public List<T> getResultList() {
        throw queryNotAllowedInCTO();
    }

    private IllegalStateException queryNotAllowedInCTO() {
        return new IllegalStateException("Hello, I am a Criteria Transfer Object (CTO) Only. I do not have an entity manager do run the query. \n" +
                " To use a CTO correctly there is an other method in the EasyCriteriaFactory: \n" +
                " UaiCriteriaFactory.createQueryCriteria(EntityManager , Class<T>, UaiCriteria)" +
                " The last parameter is where you should pass the CTO");
    }

    public T getSingleResult() {
        throw queryNotAllowedInCTO();
    }

    public Long count() {
        throw queryNotAllowedInCTO();
    }

    public EasyCriteria<T> andEquals(final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.EQUAL, value));
        return this;
    }

    public EasyCriteria<T> andEquals(final boolean toLowerCase, final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.EQUAL, value));
        return this;
    }

    public EasyCriteria<T> orEquals(final String attributeName, final Object... valueArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.OR_EQUAL, valueArray));
        return this;
    }

    public EasyCriteria<T> orEquals(final boolean toLowerCase, final String attributeName, final Object... valueArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.OR_EQUAL, valueArray));
        return this;
    }

    public EasyCriteria<T> orEquals(final int index, final String attributeName, final Object... valueArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(index, DO_NOT_APPLY_LOWER_CASE, attributeName, CriteriaConditionType.OR_EQUAL, valueArray));
        return this;
    }

    public EasyCriteria<T> orEquals(final boolean toLowerCase, final int index, final String attributeName, final Object... valueArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(index, toLowerCase, attributeName, CriteriaConditionType.OR_EQUAL, valueArray));
        return this;
    }

    public EasyCriteria<T> andNotEquals(final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.NOT_EQUAL, value));
        return this;
    }

    public EasyCriteria<T> andNotEquals(final boolean toLowerCase, final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.NOT_EQUAL, value));
        return this;
    }

    public EasyCriteria<T> orNotEquals(final String attributeName, final Object... valueArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.OR_NOT_EQUAL, valueArray));
        return this;
    }

    public EasyCriteria<T> orNotEquals(final boolean toLowerCase, final String attributeName, final Object... valueArray) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.OR_NOT_EQUAL, valueArray));
        return this;
    }

    public EasyCriteria<T> andGreaterThan(final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.AND_GREATER_THAN, value));
        return this;
    }

    public EasyCriteria<T> andGreaterThan(final boolean toLowerCase, final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.AND_GREATER_THAN, value));
        return this;
    }

    public EasyCriteria<T> andGreaterOrEqualTo(final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.GREATER_OR_EQUAL_TO, value));
        return this;
    }

    public EasyCriteria<T> andGreaterOrEqualTo(final boolean toLowerCase, final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.GREATER_OR_EQUAL_TO, value));
        return this;
    }

    public EasyCriteria<T> andLessThan(final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.LESS_THAN, value));
        return this;
    }

    public EasyCriteria<T> andLessThan(final boolean toLowerCase, final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.LESS_THAN, value));
        return this;
    }

    public EasyCriteria<T> andLessOrEqualTo(final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.LESS_OR_EQUAL_TO, value));
        return this;
    }

    public EasyCriteria<T> andLessOrEqualTo(final boolean toLowerCase, final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.LESS_OR_EQUAL_TO, value));
        return this;
    }

    public EasyCriteria<T> innerJoin(final String joinName) {
        joinList.add(new JoinHolder(joinName, JoinHolderType.INNER));
        return this;
    }

    public EasyCriteria<T> leftJoin(final String joinName) {
        joinList.add(new JoinHolder(joinName, JoinHolderType.LEFT));
        return this;
    }

    public EasyCriteria<T> innerJoinFetch(final String joinName) {
        joinList.add(new JoinHolder(joinName, JoinHolderType.INNER_FETCH));
        return this;
    }

    public EasyCriteria<T> leftJoinFetch(final String joinName) {
        joinList.add(new JoinHolder(joinName, JoinHolderType.LEFT_FETCH));
        return this;
    }

    public EasyCriteria<T> setDistinctTrue() {
        distinct = true;
        return this;
    }

    public EasyCriteria<T> andBetween(final String attributeName, final Object valueA, final Object valueB) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.BETWEEN, valueA, valueB));
        return this;
    }

    public EasyCriteria<T> andBetween(final boolean toLowerCase, final String attributeName, final Object valueA, final Object valueB) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.BETWEEN, valueA, valueB));
        return this;
    }

    public EasyCriteria<T> andIsNull(final String attributeName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.AND_IS_NULL));
        return this;
    }

    public EasyCriteria<T> andIsNotNull(final String attributeName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(attributeName, CriteriaConditionType.AND_IS_NOT_NULL));
        return this;
    }

    public EasyCriteria<T> andCollectionIsEmpty(final String collectionName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(collectionName, CriteriaConditionType.COLLECTION_IS_EMPTY));
        return this;
    }

    public EasyCriteria<T> andCollectionIsNotEmpty(final String collectionName) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(collectionName, CriteriaConditionType.COLLECTION_IS_NOT_EMPTY));
        return this;
    }

    public EasyCriteria<T> andStringLike(final String attributeName, final String value) {
        return andStringLike(DO_NOT_APPLY_LOWER_CASE, attributeName, value);
    }

    public EasyCriteria<T> andStringLike(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.STRING_LIKE, value));
        return this;
    }

    public EasyCriteria<T> andStringNotLike(final String attributeName, final String value) {
        return andStringNotLike(DO_NOT_APPLY_LOWER_CASE, attributeName, value);
    }

    public EasyCriteria<T> andStringNotLike(final boolean toLowerCase, final String attributeName, final String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.STRING_NOT_LIKE, value));
        return this;
    }

    public EasyCriteria<T> andStringIn(final String attributeName, final List<String> valueList) {
        return andStringIn(DO_NOT_APPLY_LOWER_CASE, attributeName, valueList);
    }

    public EasyCriteria<T> andStringIn(final boolean toLowerCase, final String attributeName, final List<String> valueList) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.STRING_IN, valueList.toArray()));
        return this;
    }

    public EasyCriteria<T> andStringNotIn(final String attributeName, final List<String> valueList) {
        return andStringNotIn(DO_NOT_APPLY_LOWER_CASE, attributeName, valueList);
    }

    public EasyCriteria<T> andStringNotIn(final boolean toLowerCase, final String attributeName, final List<String> valueList) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(toLowerCase, attributeName, CriteriaConditionType.STRING_NOT_IN, valueList.toArray()));
        return this;
    }

    public EasyCriteria<T> orderByAsc(final String attributeName) {
        orderByList.add(new OrderByHolder(ORDER_BY_ASC, attributeName));
        return this;
    }

    public EasyCriteria<T> orderByDesc(final String attributeName) {
        orderByList.add(new OrderByHolder(ORDER_BY_DESC, attributeName));
        return this;
    }

    public EasyCriteria<T> andJoinEquals(final String joinName, final String attributeName, final Object value) {
        return andEquals(joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinEquals(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        return andEquals(toLowerCase, joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinNotEquals(final String joinName, final String attributeName, final Object value) {
        return andNotEquals(joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinNotEquals(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        return andNotEquals(toLowerCase, joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinGreaterThan(final String joinName, final String attributeName, final Object value) {
        return andGreaterThan(joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinGreaterThan(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        return andGreaterThan(toLowerCase, joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinGreaterOrEqualTo(final String joinName, final String attributeName, final Object value) {
        return andGreaterOrEqualTo(joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinGreaterOrEqualTo(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        return andGreaterOrEqualTo(toLowerCase, joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinLessThan(final String joinName, final String attributeName, final Object value) {
        return andLessThan(joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinLessThan(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        return andLessThan(toLowerCase, joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinLessOrEqualTo(final String joinName, final String attributeName, final Object value) {
        return andLessOrEqualTo(joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinLessOrEqualTo(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        return andLessOrEqualTo(toLowerCase, joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinBetween(final String joinName, final String attributeName, final Object valueA, final Object valueB) {
        return andBetween(joinName + "." + attributeName, valueA, valueB);
    }

    public EasyCriteria<T> andJoinBetween(final boolean toLowerCase, final String joinName, final String attributeName, final Object valueA, final Object valueB) {
        return andBetween(toLowerCase, joinName + "." + attributeName, valueA, valueB);
    }

    public EasyCriteria<T> andJoinAttributeIsNull(final String joinName, final String attributeName) {
        return andIsNull(joinName + "." + attributeName);
    }

    public EasyCriteria<T> andJoinAttributeIsNotNull(final String joinName, final String attributeName) {
        return andIsNotNull(joinName + "." + attributeName);
    }

    public EasyCriteria<T> andJoinListIsEmpty(final String joinName, final String listName) {
        return andCollectionIsEmpty(joinName + "." + listName);
    }

    public EasyCriteria<T> andJoinCollectionIsEmpty(final String joinName, final String collectionName) {
        return andCollectionIsEmpty(joinName + "." + collectionName);
    }

    public EasyCriteria<T> andJoinSetIsEmpty(final String joinName, final String setName) {
        return andCollectionIsEmpty(joinName + "." + setName);
    }

    public EasyCriteria<T> andJoinListIsNotEmpty(final String joinName, final String listName) {
        return andCollectionIsNotEmpty(joinName + "." + listName);
    }

    public EasyCriteria<T> andJoinCollectionIsNotEmpty(final String joinName, final String collectionName) {
        return andCollectionIsNotEmpty(joinName + "." + collectionName);
    }

    public EasyCriteria<T> andJoinSetIsNotEmpty(final String joinName, final String setName) {
        return andCollectionIsNotEmpty(joinName + "." + setName);
    }

    public EasyCriteria<T> andJoinStringLike(final String joinName, final String attributeName, final String value) {
        return andStringLike(joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinStringLike(final boolean toLowerCase, final String joinName, final String attributeName, final String value) {
        return andStringLike(toLowerCase, joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinStringNotLike(final String joinName, final String attributeName, final String value) {
        return andStringNotLike(joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinStringNotLike(final boolean toLowerCase, final String joinName, final String attributeName, final String value) {
        return andStringNotLike(toLowerCase, joinName + "." + attributeName, value);
    }

    public EasyCriteria<T> andJoinStringIn(final String joinName, final String attributeName, final List<String> valueList) {
        return andStringIn(joinName + "." + attributeName, valueList);
    }

    public EasyCriteria<T> andJoinStringIn(final boolean toLowerCase, final String joinName, final String attributeName, final List<String> valueList) {
        return andStringIn(joinName + "." + attributeName, valueList);
    }

    public EasyCriteria<T> andJoinStringNotIn(final String joinName, final String attributeName, final List<String> valueList) {
        return andStringNotIn(joinName + "." + attributeName, valueList);
    }

    public EasyCriteria<T> andJoinStringNotIn(final boolean toLowerCase, final String joinName, final String attributeName, final List<String> valueList) {
        return andStringNotIn(toLowerCase, joinName + "." + attributeName, valueList);
    }

    public EasyCriteria<T> addAndSeparatedByOr(final int index, final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(index, DO_NOT_APPLY_LOWER_CASE, attributeName, CriteriaConditionType.AND_SEPARATED_BY_OR, value));
        return this;
    }

    public EasyCriteria<T> addAndSeparatedByOr(final boolean toLowerCase, final int index, final String attributeName, final Object value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(index, toLowerCase, attributeName, CriteriaConditionType.AND_SEPARATED_BY_OR, value));
        return this;
    }

    public EasyCriteria<T> addHint(final String key, final String value) {
        criteriaConditionTypeList.add(new CriteriaConditionHolder(CriteriaConditionType.HINT, key, value));
        return this;
    }

    public EasyCriteria<T> setFirstResult(final Integer firstResult) {
        this.firstResult = firstResult;
        return this;
    }

    public EasyCriteria<T> setMaxResults(final Integer maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    public Integer getFirstResult() {
        return firstResult;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<OrderByHolder> getOrderByList() {
        return orderByList;
    }

    public List<CriteriaConditionHolder> getCriteriaConditionTypeList() {
        return criteriaConditionTypeList;
    }

    public List<JoinHolder> getJoinList() {
        return joinList;
    }
}