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

import com.uaihebert.uaicriteria.UaiCriteria;

import java.util.List;

public class EasyCriteriaImp<T> implements EasyCriteria<T> {

    private final UaiCriteria<T> uaiCriteria;

    public EasyCriteriaImp(final UaiCriteria<T> uaiCriteria) {
        this.uaiCriteria = uaiCriteria;
    }

    public List<T> getResultList() {
        return uaiCriteria.getResultList();
    }

    public T getSingleResult() {
        return uaiCriteria.getSingleResult();
    }

    public EasyCriteriaImp<T> andEquals(final String attributeName, final Object value) {
        uaiCriteria.andEquals(attributeName, value);
        return this;
    }

    public EasyCriteria<T> andEquals(final boolean toLowerCase, final String attributeName, final Object value) {
        uaiCriteria.andEquals(toLowerCase, attributeName, value.toString());
        return this;
    }

    public EasyCriteria<T> andNotEquals(final String attributeName, final Object value) {
        uaiCriteria.andNotEquals(attributeName, value);
        return this;
    }

    public EasyCriteria<T> andNotEquals(final boolean toLowerCase, final String attributeName, final Object value) {
        uaiCriteria.andNotEquals(toLowerCase, attributeName, value.toString());
        return this;
    }

    public EasyCriteria<T> orNotEquals(final String attributeName, final Object... values) {
        uaiCriteria.orNotEquals(attributeName, values);
        return this;
    }

    public EasyCriteria<T> orNotEquals(final boolean toLowerCase, final String attributeName, final Object... valueArray) {
        final String[] stringArray = convertToStringArray(valueArray);

        uaiCriteria.orNotEquals(toLowerCase, attributeName, stringArray);
        return this;
    }

    private String[] convertToStringArray(final Object[] valueArray) {
        final String[] stringArray = new String[valueArray.length];

        for (int i = 0; i < valueArray.length; i++) {
            stringArray[i] = valueArray[i].toString();
        }

        return stringArray;
    }

    public EasyCriteria<T> andGreaterThan(final String attributeName, final Object value) {
        uaiCriteria.andGreaterThan(attributeName, value);
        return this;
    }

    public EasyCriteria<T> andGreaterThan(final boolean toLowerCase, final String attributeName, final Object value) {
        uaiCriteria.andGreaterThan(toLowerCase, attributeName, value.toString());
        return this;
    }

    public EasyCriteria<T> andGreaterOrEqualTo(final String attributeName, final Object value) {
        uaiCriteria.andGreaterOrEqualTo(attributeName, value);
        return this;
    }

    public EasyCriteria<T> andGreaterOrEqualTo(final boolean toLowerCase, final String attributeName, final Object value) {
        uaiCriteria.andGreaterOrEqualTo(toLowerCase, attributeName, value.toString());
        return this;
    }

    public EasyCriteria<T> andLessThan(final String attributeName, final Object value) {
        uaiCriteria.andLessThan(attributeName, value);
        return this;
    }

    public EasyCriteria<T> andLessThan(final boolean toLowerCase, final String attributeName, final Object value) {
        uaiCriteria.andLessThan(toLowerCase, attributeName, value.toString());
        return this;
    }

    public EasyCriteria<T> andLessOrEqualTo(final String attributeName, final Object value) {
        uaiCriteria.andLessOrEqualTo(attributeName, value);
        return this;
    }

    public EasyCriteria<T> andLessOrEqualTo(final boolean toLowerCase, final String attributeName, final Object value) {
        uaiCriteria.andLessOrEqualTo(toLowerCase, attributeName, value.toString());
        return this;
    }

    public EasyCriteria<T> innerJoin(final String joinName) {
        uaiCriteria.innerJoin(joinName);
        return this;
    }

    public EasyCriteria<T> setDistinctTrue() {
        uaiCriteria.setDistinctTrue();
        return this;
    }

    public EasyCriteria<T> leftJoin(final String joinName) {
        uaiCriteria.leftJoin(joinName);
        return this;
    }

    public EasyCriteria<T> innerJoinFetch(final String joinName) {
        uaiCriteria.innerJoinFetch(joinName);
        return this;
    }

    public EasyCriteria<T> leftJoinFetch(final String joinName) {
        uaiCriteria.leftJoinFetch(joinName);
        return this;
    }

    public EasyCriteria<T> andBetween(final String attributeName, final Object valueA, final Object valueB) {
        uaiCriteria.andBetween(attributeName, valueA, valueB);
        return this;
    }

    public EasyCriteria<T> andBetween(final boolean toLowerCase, final String attributeName, final Object valueA, final Object valueB) {
        uaiCriteria.andBetween(toLowerCase, attributeName, valueA.toString(), valueB.toString());
        return this;
    }

    public EasyCriteria<T> andIsNull(final String attributeName) {
        uaiCriteria.andIsNull(attributeName);
        return this;
    }

    public EasyCriteria<T> andIsNotNull(final String attributeName) {
        uaiCriteria.andIsNotNull(attributeName);
        return this;
    }

    public EasyCriteria<T> andCollectionIsEmpty(final String collectionName) {
        uaiCriteria.andCollectionIsEmpty(collectionName);
        return this;
    }

    public EasyCriteria<T> andCollectionIsNotEmpty(final String collectionName) {
        uaiCriteria.andCollectionIsNotEmpty(collectionName);
        return this;
    }

    public EasyCriteria<T> andStringLike(final String attributeName, final String value) {
        uaiCriteria.andStringLike(attributeName, value);
        return this;
    }

    public EasyCriteria<T> andStringLike(final boolean toLowerCase, final String attributeName, final String value) {
        uaiCriteria.andStringLike(toLowerCase, attributeName, value);
        return this;
    }

    public EasyCriteria<T> andStringNotLike(final String attributeName, final String value) {
        uaiCriteria.andStringNotLike(attributeName, value);
        return this;
    }

    public EasyCriteria<T> andStringNotLike(final boolean toLowerCase, final String attributeName, final String value) {
        uaiCriteria.andStringNotLike(toLowerCase, attributeName, value);
        return this;
    }

    public EasyCriteria<T> andStringIn(final String attributeName, final List<String> values) {
        uaiCriteria.andStringIn(attributeName, values);
        return this;
    }

    public EasyCriteria<T> andStringIn(final boolean toLowerCase, final String attributeName, final List<String> values) {
        uaiCriteria.andStringIn(toLowerCase, attributeName, values);
        return this;
    }

    public EasyCriteria<T> andStringNotIn(final String attributeName, final List<String> values) {
        uaiCriteria.andStringNotIn(attributeName, values);
        return this;
    }

    public EasyCriteria<T> andStringNotIn(final boolean toLowerCase, final String attributeName, final List<String> values) {
        uaiCriteria.andStringNotIn(toLowerCase, attributeName, values);
        return this;
    }

    public EasyCriteria<T> orderByAsc(final String attributeName) {
        uaiCriteria.orderByAsc(attributeName);
        return this;
    }

    public EasyCriteria<T> orderByDesc(final String attributeName) {
        uaiCriteria.orderByDesc(attributeName);
        return this;
    }

    public EasyCriteria<T> setFirstResult(final Integer firstResult) {
        uaiCriteria.setFirstResult(firstResult);
        return this;
    }

    public EasyCriteria<T> setMaxResults(final Integer maxResults) {
        uaiCriteria.setMaxResults(maxResults);
        return this;
    }

    public EasyCriteria<T> andJoinEquals(final String joinName, final String attributeName, final Object value) {
        uaiCriteria.andEquals(joinName + "." + attributeName, value);
        return this;
    }

    public EasyCriteria<T> andJoinEquals(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        uaiCriteria.andEquals(toLowerCase, joinName + "." + attributeName, value.toString());
        return this;
    }

    public EasyCriteria<T> andJoinNotEquals(final String joinName, final String attributeName, final Object value) {
        uaiCriteria.andNotEquals(joinName + "." + attributeName, value);
        return this;
    }

    public EasyCriteria<T> andJoinNotEquals(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        uaiCriteria.andNotEquals(toLowerCase, joinName + "." + attributeName, value.toString());
        return this;
    }

    public EasyCriteria<T> andJoinGreaterThan(final String joinName, final String attributeName, final Object value) {
        uaiCriteria.andGreaterThan(joinName + "." + attributeName, value);
        return this;
    }

    public EasyCriteria<T> andJoinGreaterThan(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        uaiCriteria.andGreaterThan(toLowerCase, joinName + "." + attributeName, value.toString());
        return this;
    }

    public EasyCriteria<T> andJoinGreaterOrEqualTo(final String joinName, final String attributeName, final Object value) {
        uaiCriteria.andGreaterOrEqualTo(joinName + "." + attributeName, value);
        return this;
    }

    public EasyCriteria<T> andJoinGreaterOrEqualTo(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        uaiCriteria.andGreaterOrEqualTo(toLowerCase, joinName + "." + attributeName, value.toString());
        return this;
    }

    public EasyCriteria<T> andJoinLessThan(final String joinName, final String attributeName, final Object value) {
        uaiCriteria.andLessThan(joinName + "." + attributeName, value);
        return this;
    }

    public EasyCriteria<T> andJoinLessThan(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        uaiCriteria.andLessThan(toLowerCase, joinName + "." + attributeName, value.toString());
        return this;
    }

    public EasyCriteria<T> andJoinLessOrEqualTo(final String joinName, final String attributeName, final Object value) {
        uaiCriteria.andLessOrEqualTo(joinName + "." + attributeName, value);
        return this;
    }

    public EasyCriteria<T> andJoinLessOrEqualTo(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        uaiCriteria.andLessOrEqualTo(toLowerCase, joinName + "." + attributeName, value.toString());
        return this;
    }

    public EasyCriteria<T> andJoinBetween(final String joinName, final String attributeName, final Object valueA, final Object valueB) {
        uaiCriteria.andBetween(joinName + "." + attributeName, valueA, valueB);
        return this;
    }

    public EasyCriteria<T> andJoinBetween(final boolean toLowerCase, final String joinName, final String attributeName, final Object valueA, final Object valueB) {
        uaiCriteria.andBetween(toLowerCase, joinName + "." + attributeName, valueA.toString(), valueB.toString());
        return this;
    }

    public EasyCriteria<T> andJoinAttributeIsNull(final String joinName, final String attributeName) {
        uaiCriteria.andIsNull(joinName + "." + attributeName);
        return this;
    }

    public EasyCriteria<T> andJoinAttributeIsNotNull(final String joinName, final String attributeName) {
        uaiCriteria.andIsNotNull(joinName + "." + attributeName);
        return this;
    }

    public EasyCriteria<T> andJoinListIsEmpty(final String joinName, final String listName) {
        uaiCriteria.andCollectionIsEmpty(joinName + "." + listName);
        return this;
    }

    public EasyCriteria<T> andJoinCollectionIsEmpty(final String joinName, final String collectionName) {
        uaiCriteria.andCollectionIsEmpty(joinName + "." + collectionName);
        return this;
    }


    public EasyCriteria<T> andJoinCollectionIsNotEmpty(final String joinName, final String collectionName) {
        uaiCriteria.andCollectionIsNotEmpty(joinName + "." + collectionName);
        return this;
    }

    public EasyCriteria<T> andJoinStringLike(final String joinName, final String attributeName, final String value) {
        uaiCriteria.andStringLike(joinName + "." + attributeName, value);
        return this;
    }

    public EasyCriteria<T> andJoinStringLike(final boolean toLowerCase, final String joinName, final String attributeName, final String value) {
        uaiCriteria.andStringLike(toLowerCase, joinName + "." + attributeName, value);
        return this;
    }

    public EasyCriteria<T> andJoinSetIsEmpty(final String joinName, final String setName) {
        uaiCriteria.andCollectionIsEmpty(joinName + "." + setName);
        return this;
    }

    public EasyCriteria<T> andJoinListIsNotEmpty(final String joinName, final String listName) {
        uaiCriteria.andCollectionIsNotEmpty(joinName + "." + listName);
        return this;
    }

    public EasyCriteria<T> andJoinSetIsNotEmpty(final String joinName, final String setName) {
        uaiCriteria.andCollectionIsNotEmpty(joinName + "." + setName);
        return this;
    }

    public EasyCriteria<T> andJoinStringNotLike(final String joinName, final String attributeName, final String value) {
        uaiCriteria.andStringNotLike(joinName + "." + attributeName, value);
        return this;
    }

    public EasyCriteria<T> andJoinStringNotLike(final boolean toLowerCase, final String joinName, final String attributeName, final String value) {
        uaiCriteria.andStringNotLike(toLowerCase, joinName + "." + attributeName, value);
        return this;
    }

    public EasyCriteria<T> andJoinStringIn(final String joinName, final String attributeName, final List<String> values) {
        uaiCriteria.andStringIn(joinName + "." + attributeName, values);
        return this;
    }

    public EasyCriteria<T> andJoinStringIn(final boolean toLowerCase, final String joinName, final String attributeName, final List<String> values) {
        uaiCriteria.andStringIn(toLowerCase, joinName + "." + attributeName, values);
        return this;
    }

    public EasyCriteria<T> andJoinStringNotIn(final String joinName, final String attributeName, final List<String> values) {
        uaiCriteria.andStringNotIn(joinName + "." + attributeName, values);
        return this;
    }

    public EasyCriteria<T> andJoinStringNotIn(final boolean toLowerCase, final String joinName, final String attributeName, final List<String> values) {
        uaiCriteria.andStringNotIn(toLowerCase, joinName + "." + attributeName, values);
        return this;
    }

    public EasyCriteria<T> orEquals(final String attributeName, final Object... values) {
        uaiCriteria.orEquals(attributeName, values);
        return this;
    }

    public EasyCriteria<T> orEquals(final boolean toLowerCase, final String attributeName, final Object... values) {
        final String[] stringArray = convertToStringArray(values);

        uaiCriteria.orEquals(toLowerCase, attributeName, stringArray);

        return this;
    }

    public EasyCriteria<T> orEquals(final int index, final String attributeName, final Object... values) {
        uaiCriteria.orEquals(index, attributeName, values);
        return this;
    }

    public EasyCriteria<T> orEquals(final boolean toLowerCase, final int index, final String attributeName, final Object... values) {
        final String[] stringArray = convertToStringArray(values);

        uaiCriteria.orEquals(toLowerCase, index, attributeName, stringArray);

        return this;
    }

    public EasyCriteria<T> addAndSeparatedByOr(final int index, final String attributeName, final Object value) {
        uaiCriteria.addAndSeparatedByOr(index, attributeName, value);
        return this;
    }

    public EasyCriteria<T> addAndSeparatedByOr(final boolean toLowerCase, final int index, final String attributeName, final Object value) {
        uaiCriteria.addAndSeparatedByOr(toLowerCase, index, attributeName, value.toString());
        return this;
    }

    public Long count() {
        return uaiCriteria.count();
    }

    public EasyCriteria<T> addHint(final String key, final String value) {
        uaiCriteria.addHint(key, value);
        return this;
    }
}