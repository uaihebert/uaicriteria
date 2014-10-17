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
package com.uaihebert.model.test;

import com.uaihebert.uaicriteria.UaiCriteria;

import java.util.List;

public class FakeUaiCriteriaCTOImp<T> implements UaiCriteria<T> {

    @Override
    public List<T> getResultList() {
        return null;
    }

    @Override
    public T getSingleResult() {
        return null;
    }

    @Override
    public List getMultiSelectResult() {
        return null;
    }

    @Override
    public <E> UaiCriteria<E> subQuery(final String subQueryResult, final Class<E> entityClass) {
        return null;
    }

    @Override
    public UaiCriteria<T> andEquals(final String attributeName, final Object value) {
        return null;
    }

    @Override
    public UaiCriteria<T> andEquals(final boolean toLowerCase, final String attributeName, final String value) {
        return null;
    }

    @Override
    public UaiCriteria<T> orEquals(final String attributeName, final Object... valueArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> orStringLike(final String attributeName, final String... stringArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> orStringLike(final boolean toLowerCase, final String attributeName, final String... stringArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> orStringNotLike(final String attributeName, final String... stringArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> orStringNotLike(final boolean toLowerCase, final String attributeName, final String... stringArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> andIsMemberOf(final Object value, final String collectionName) {
        return null;
    }

    @Override
    public UaiCriteria<T> andIsNotMemberOf(final Object value, final String collectionName) {
        return null;
    }

    @Override
    public UaiCriteria<T> orEquals(final boolean toLowerCase, final String attributeName, final String... valueArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> orEquals(final int index, final String attributeName, final Object... valueArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> orEquals(final boolean toLowerCase, final int index, final String attributeName, final String... valueArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> andNotEquals(final String attributeName, final Object value) {
        return null;
    }

    @Override
    public UaiCriteria<T> andNotEquals(final boolean toLowerCase, final String attributeName, final String value) {
        return null;
    }

    @Override
    public UaiCriteria<T> orNotEquals(final String attributeName, final Object... valueArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> orNotEquals(final boolean toLowerCase, final String attributeName, final String... valueArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> andGreaterThan(final String attributeName, final Object value) {
        return null;
    }

    @Override
    public UaiCriteria<T> andGreaterThan(final boolean toLowerCase, final String attributeName, final String value) {
        return null;
    }

    @Override
    public UaiCriteria<T> andGreaterOrEqualTo(final String attributeName, final Object value) {
        return null;
    }

    @Override
    public UaiCriteria<T> andGreaterOrEqualTo(final boolean toLowerCase, final String attributeName, final String value) {
        return null;
    }

    @Override
    public UaiCriteria<T> andLessThan(final String attributeName, final Object value) {
        return null;
    }

    @Override
    public UaiCriteria<T> andLessThan(final boolean toLowerCase, final String attributeName, final String value) {
        return null;
    }

    @Override
    public UaiCriteria<T> andLessOrEqualTo(final String attributeName, final Object value) {
        return null;
    }

    @Override
    public UaiCriteria<T> andLessOrEqualTo(final boolean toLowerCase, final String attributeName, final String value) {
        return null;
    }

    @Override
    public UaiCriteria<T> innerJoin(final String joinName) {
        return null;
    }

    @Override
    public UaiCriteria<T> leftJoin(final String joinName) {
        return null;
    }

    @Override
    public UaiCriteria<T> innerJoinFetch(final String joinName) {
        return null;
    }

    @Override
    public UaiCriteria<T> leftJoinFetch(final String joinName) {
        return null;
    }

    @Override
    public UaiCriteria<T> setDistinctTrue() {
        return null;
    }

    @Override
    public UaiCriteria<T> andBetween(final String attributeName, final Object valueA, final Object valueB) {
        return null;
    }

    @Override
    public UaiCriteria<T> andBetween(final boolean toLowerCase, final String attributeName, final String valueA, final String valueB) {
        return null;
    }

    @Override
    public UaiCriteria<T> andIsNull(final String attributeName) {
        return null;
    }

    @Override
    public UaiCriteria<T> andIsNotNull(final String attributeName) {
        return null;
    }

    @Override
    public UaiCriteria<T> andCollectionIsEmpty(final String collectionName) {
        return null;
    }

    @Override
    public UaiCriteria<T> andCollectionIsNotEmpty(final String collectionName) {
        return null;
    }

    @Override
    public UaiCriteria<T> andStringLike(final String attributeName, final String value) {
        return null;
    }

    @Override
    public UaiCriteria<T> andStringLike(final boolean toLowerCase, final String attributeName, final String value) {
        return null;
    }

    @Override
    public UaiCriteria<T> andStringNotLike(final String attributeName, final String value) {
        return null;
    }

    @Override
    public <E> UaiCriteria<T> andAttributeIn(final String attributeName, final List<E> attributeList) {
        return null;
    }

    @Override
    public <E> UaiCriteria<T> andAttributeIn(final String attributeName, final UaiCriteria<E> uaiSubQuery) {
        return null;
    }

    @Override
    public <E> UaiCriteria<T> andAttributeNotIn(final String attributeName, final List<E> attributeList) {
        return null;
    }

    @Override
    public <E> UaiCriteria<T> andAttributeNotIn(final String attributeName, final UaiCriteria<E> uaiSubQuery) {
        return null;
    }

    @Override
    public UaiCriteria<T> andStringNotLike(final boolean toLowerCase, final String attributeName, final String value) {
        return null;
    }

    @Override
    public UaiCriteria<T> andStringIn(final String attributeName, final List<String> valueList) {
        return null;
    }

    @Override
    public UaiCriteria<T> andStringIn(final boolean toLowerCase, final String attributeName, final List<String> valueList) {
        return null;
    }

    @Override
    public UaiCriteria<T> andStringNotIn(final String attributeName, final List<String> valueList) {
        return null;
    }

    @Override
    public UaiCriteria<T> andStringNotIn(final boolean toLowerCase, final String attributeName, final List<String> valueList) {
        return null;
    }

    @Override
    public UaiCriteria<T> orderByAsc(final String attributeName) {
        return null;
    }

    @Override
    public UaiCriteria<T> orderByDesc(final String attributeName) {
        return null;
    }

    @Override
    public UaiCriteria<T> setFirstResult(final Integer firstResult) {
        return null;
    }

    @Override
    public UaiCriteria<T> setMaxResults(final Integer maxResults) {
        return null;
    }

    @Override
    public UaiCriteria<T> addAndSeparatedByOr(final int index, final String attributeName, final Object value) {
        return null;
    }

    @Override
    public UaiCriteria<T> addAndSeparatedByOr(final boolean toLowerCase, final int index, final String attributeName, final String value) {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public Long countRegularCriteria() {
        return null;
    }

    @Override
    public UaiCriteria<T> countAttribute(final String... attributeNameArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> addHint(final String key, final String value) {
        return null;
    }

    @Override
    public UaiCriteria<T> sum(final String... attributeNameArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> diff(final String firstAttribute, final String secondAttribute) {
        return null;
    }

    @Override
    public <N extends Number> UaiCriteria<T> sum(final String attributeName, final N number) {
        return null;
    }

    @Override
    public <N extends Number> UaiCriteria<T> sum(final N number, final String attributeName) {
        return null;
    }

    @Override
    public <N extends Number> UaiCriteria<T> diff(final String attributeName, final N number) {
        return null;
    }

    @Override
    public <N extends Number> UaiCriteria<T> diff(final N number, final String attributeName) {
        return null;
    }

    @Override
    public UaiCriteria<T> multiply(final String firstAttribute, final String secondAttribute) {
        return null;
    }

    @Override
    public <N extends Number> UaiCriteria<T> multiply(final String attributeName, final N number) {
        return null;
    }

    @Override
    public <N extends Number> UaiCriteria<T> multiply(final N number, final String attributeName) {
        return null;
    }

    @Override
    public UaiCriteria<T> divide(final String firstAttribute, final String secondAttribute) {
        return null;
    }

    @Override
    public <N extends Number> UaiCriteria<T> divide(final String attributeName, final N number) {
        return null;
    }

    @Override
    public <N extends Number> UaiCriteria<T> divide(final N number, final String attributeName) {
        return null;
    }

    @Override
    public UaiCriteria<T> module(final String firstAttribute, final String secondAttribute) {
        return null;
    }

    @Override
    public UaiCriteria<T> module(final String attributeName, final Integer number) {
        return null;
    }

    @Override
    public UaiCriteria<T> module(final Integer number, final String attributeName) {
        return null;
    }

    @Override
    public UaiCriteria<T> average(final String... attributeNameArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> square(final String... attributeNameArray) {
        return null;
    }

    @Override
    public UaiCriteria<T> addMultiSelectAttribute(final String... attributeName) {
        return null;
    }

    @Override
    public UaiCriteria<T> groupBy(final String... attributeName) {
        return null;
    }

    @Override
    public UaiCriteria<T> orIsNull(final String attributeName) {
        return null;
    }

    @Override
    public UaiCriteria<T> orIsNotNull(final String attributeName) {
        return null;
    }
}