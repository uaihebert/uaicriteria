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
package com.uaihebert.test.easy_legacy.mock;

import com.uaihebert.model.EasyCriteria;

import java.util.List;

public class InvalidEasyCriteriaClass<T> implements EasyCriteria<T> {

    public List<T> getResultList() {
        return null;
    }


    public T getSingleResult() {
        return null;
    }


    public EasyCriteria<T> andEquals(final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andEquals(final boolean toLowerCase, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> orEquals(final String attributeName, final Object... values) {
        return null;
    }


    public EasyCriteria<T> orEquals(final boolean toLowerCase, final String attributeName, final Object... values) {
        return null;
    }


    public EasyCriteria<T> orEquals(final int index, final String attributeName, final Object... values) {
        return null;
    }


    public EasyCriteria<T> orEquals(final boolean toLowerCase, final int index, final String attributeName, final Object... values) {
        return null;
    }


    public EasyCriteria<T> andNotEquals(final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andNotEquals(final boolean toLowerCase, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> orNotEquals(final String attributeName, final Object... values) {
        return null;
    }


    public EasyCriteria<T> orNotEquals(final boolean toLowerCase, final String attributeName, final Object... values) {
        return null;
    }


    public EasyCriteria<T> andGreaterThan(final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andGreaterThan(final boolean toLowerCase, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andGreaterOrEqualTo(final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andGreaterOrEqualTo(final boolean toLowerCase, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andLessThan(final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andLessThan(final boolean toLowerCase, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andLessOrEqualTo(final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andLessOrEqualTo(final boolean toLowerCase, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> innerJoin(final String joinName) {
        return null;
    }


    public EasyCriteria<T> leftJoin(final String joinName) {
        return null;
    }


    public EasyCriteria<T> innerJoinFetch(final String joinName) {
        return null;
    }


    public EasyCriteria<T> leftJoinFetch(final String joinName) {
        return null;
    }


    public EasyCriteria<T> setDistinctTrue() {
        return null;
    }


    public EasyCriteria<T> andBetween(final String attributeName, final Object valueA, final Object valueB) {
        return null;
    }


    public EasyCriteria<T> andBetween(final boolean toLowerCase, final String attributeName, final Object valueA, final Object valueB) {
        return null;
    }


    public EasyCriteria<T> andIsNull(final String attributeName) {
        return null;
    }


    public EasyCriteria<T> andIsNotNull(final String attributeName) {
        return null;
    }


    public EasyCriteria<T> andCollectionIsEmpty(final String collectionName) {
        return null;
    }


    public EasyCriteria<T> andCollectionIsNotEmpty(final String collectionName) {
        return null;
    }


    public EasyCriteria<T> andStringLike(final String attributeName, final String value) {
        return null;
    }


    public EasyCriteria<T> andStringLike(final boolean toLowerCase, final String attributeName, final String value) {
        return null;
    }


    public EasyCriteria<T> andStringNotLike(final String attributeName, final String value) {
        return null;
    }


    public EasyCriteria<T> andStringNotLike(final boolean toLowerCase, final String attributeName, final String value) {
        return null;
    }


    public EasyCriteria<T> andStringIn(final String attributeName, final List<String> values) {
        return null;
    }


    public EasyCriteria<T> andStringIn(final boolean toLowerCase, final String attributeName, final List<String> values) {
        return null;
    }


    public EasyCriteria<T> andStringNotIn(final String attributeName, final List<String> values) {
        return null;
    }


    public EasyCriteria<T> andStringNotIn(final boolean toLowerCase, final String attributeName, final List<String> values) {
        return null;
    }


    public EasyCriteria<T> orderByAsc(final String attributeName) {
        return null;
    }


    public EasyCriteria<T> orderByDesc(final String attributeName) {
        return null;
    }


    public EasyCriteria<T> setFirstResult(final Integer firstResult) {
        return null;
    }


    public EasyCriteria<T> setMaxResults(final Integer maxResults) {
        return null;
    }


    public EasyCriteria<T> andJoinEquals(final String joinName, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andJoinEquals(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andJoinNotEquals(final String joinName, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andJoinNotEquals(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andJoinGreaterThan(final String joinName, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andJoinGreaterThan(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andJoinGreaterOrEqualTo(final String joinName, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andJoinGreaterOrEqualTo(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andJoinLessThan(final String joinName, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andJoinLessThan(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andJoinLessOrEqualTo(final String joinName, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andJoinLessOrEqualTo(final boolean toLowerCase, final String joinName, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> andJoinBetween(final String joinName, final String attributeName, final Object valueA, final Object valueB) {
        return null;
    }


    public EasyCriteria<T> andJoinBetween(final boolean toLowerCase, final String joinName, final String attributeName, final Object valueA, final Object valueB) {
        return null;
    }


    public EasyCriteria<T> andJoinAttributeIsNull(final String joinName, final String attributeName) {
        return null;
    }


    public EasyCriteria<T> andJoinAttributeIsNotNull(final String joinName, final String attributeName) {
        return null;
    }


    public EasyCriteria<T> andJoinListIsEmpty(final String joinName, final String listName) {
        return null;
    }


    public EasyCriteria<T> andJoinCollectionIsEmpty(final String joinName, final String collectionName) {
        return null;
    }


    public EasyCriteria<T> andJoinSetIsEmpty(final String joinName, final String setName) {
        return null;
    }


    public EasyCriteria<T> andJoinListIsNotEmpty(final String joinName, final String listName) {
        return null;
    }


    public EasyCriteria<T> andJoinCollectionIsNotEmpty(final String joinName, final String collectionName) {
        return null;
    }


    public EasyCriteria<T> andJoinSetIsNotEmpty(final String joinName, final String setName) {
        return null;
    }


    public EasyCriteria<T> andJoinStringLike(final String joinName, final String attributeName, final String value) {
        return null;
    }


    public EasyCriteria<T> andJoinStringLike(final boolean toLowerCase, final String joinName, final String attributeName, final String value) {
        return null;
    }


    public EasyCriteria<T> andJoinStringNotLike(final String joinName, final String attributeName, final String value) {
        return null;
    }


    public EasyCriteria<T> andJoinStringNotLike(final boolean toLowerCase, final String joinName, final String attributeName, final String value) {
        return null;
    }


    public EasyCriteria<T> andJoinStringIn(final String joinName, final String attributeName, final List<String> values) {
        return null;
    }


    public EasyCriteria<T> andJoinStringIn(final boolean toLowerCase, final String joinName, final String attributeName, final List<String> values) {
        return null;
    }


    public EasyCriteria<T> andJoinStringNotIn(final String joinName, final String attributeName, final List<String> values) {
        return null;
    }


    public EasyCriteria<T> andJoinStringNotIn(final boolean toLowerCase, final String joinName, final String attributeName, final List<String> values) {
        return null;
    }


    public EasyCriteria<T> addAndSeparatedByOr(final int index, final String attributeName, final Object value) {
        return null;
    }


    public EasyCriteria<T> addAndSeparatedByOr(final boolean toLowerCase, final int index, final String attributeName, final Object value) {
        return null;
    }


    public Long count() {
        return null;
    }

    public EasyCriteria<T> addHint(final String key, final String value) {
        return null;
    }
}
