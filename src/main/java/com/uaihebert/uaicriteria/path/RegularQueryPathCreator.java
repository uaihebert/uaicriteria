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
package com.uaihebert.uaicriteria.path;

import com.uaihebert.uaicriteria.base.element.BaseCriteria;
import com.uaihebert.uaicriteria.criteria.CriteriaOrType;
import com.uaihebert.uaicriteria.criteria.CriteriaResultOrderBy;
import com.uaihebert.uaicriteria.predicate.RegularQueryPredicateCreator;
import com.uaihebert.uaicriteria.subquery.SubQueryImp;
import com.uaihebert.util.ReflectionUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;

public final class RegularQueryPathCreator extends AbstractPathCreator {
    private static final boolean FETCH_ENABLED = true;
    private static final boolean FETCH_DISABLED = false;

    private RegularQueryPathCreator() {
    }

    public static void andEquals(final boolean toLowerCase, final BaseCriteria baseCriteria, final String attributeName, final Object value) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Predicate equal = RegularQueryPredicateCreator.createEqualPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, value);

        finishWithAndPredicate(baseCriteria, equal);
    }

    public static void andNotEquals(final boolean toLowerCase, final BaseCriteria baseCriteria, final String attributeName, final Object value) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Predicate notEqualsPredicate = RegularQueryPredicateCreator.createEqualPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, value).not();

        finishWithAndPredicate(baseCriteria, notEqualsPredicate);
    }

    public static void innerJoin(final String joinName, final BaseCriteria baseCriteria) {
        PathHelper.createJoinPaths(baseCriteria, joinName, JoinType.INNER, FETCH_DISABLED);
    }

    public static void innerJoinFetch(final String joinName, final BaseCriteria baseCriteria) {
        PathHelper.createJoinPaths(baseCriteria, joinName, JoinType.INNER, FETCH_ENABLED);
    }

    public static void leftJoinFetch(final String joinName, final BaseCriteria baseCriteria) {
        PathHelper.createJoinPaths(baseCriteria, joinName, JoinType.LEFT, FETCH_ENABLED);
    }

    public static void leftJoin(final String joinName, final BaseCriteria baseCriteria) {
        PathHelper.createJoinPaths(baseCriteria, joinName, JoinType.LEFT, FETCH_DISABLED);
    }

    public static void orderBy(final String attributeName, final BaseCriteria baseCriteria, final CriteriaResultOrderBy criteriaResultOrderBy) {
        final CriteriaBuilder criteriaBuilder = baseCriteria.getCriteriaBuilder();

        final Path path = PathHelper.extractPath(baseCriteria, attributeName);
        final Order currentOrdination = criteriaResultOrderBy.createOrdination(path, criteriaBuilder);

        baseCriteria.addOrdination(currentOrdination);
    }

    public static void andHint(final BaseCriteria baseCriteria, final String key, final String value) {
        baseCriteria.addHint(key, value);
    }

    public static void addOr(final boolean toLowerCase, final int index, final BaseCriteria baseCriteria, final String attributeName, final Object[] valueArray, final CriteriaOrType orType) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        if (valueArray == null) {
            final Predicate predicate = createEqualForOr(toLowerCase, baseCriteria, path, null, orType);
            final Predicate andPredicate = createAndPredicate(baseCriteria, predicate);

            baseCriteria.addOrPredicate(index, andPredicate);
            return;
        }

        for (final Object value : valueArray) {
            final Comparable typedGreaterThan = getTypedValue(baseCriteria.getEntityClass(), attributeName, value);
            final Predicate predicate = createEqualForOr(toLowerCase, baseCriteria, path, typedGreaterThan, orType);
            final Predicate andPredicate = createAndPredicate(baseCriteria, predicate);

            baseCriteria.addOrPredicate(index, andPredicate);
        }
    }

    private static Predicate createEqualForOr(final boolean toLowerCase, final BaseCriteria baseCriteria, final Path path, final Comparable value, final CriteriaOrType orType) {
        if (CriteriaOrType.LIKE.equals(orType)) {
            return RegularQueryPredicateCreator.createLikePredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, value.toString());
        }

        if (CriteriaOrType.NOT_LIKE.equals(orType)) {
            final Predicate predicate = RegularQueryPredicateCreator.createLikePredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, value.toString());
            return predicate.not();
        }

        if (CriteriaOrType.IS_NULL.equals(orType)) {
            return RegularQueryPredicateCreator.createIsNullPredicate(baseCriteria.getCriteriaBuilder(), path);
        }

        if (CriteriaOrType.IS_NOT_NULL.equals(orType)) {
            final Predicate isNullPredicate = RegularQueryPredicateCreator.createIsNullPredicate(baseCriteria.getCriteriaBuilder(), path);
            return isNullPredicate.not();
        }

        if (CriteriaOrType.GREATER_THAN.equals(orType)) {
            return RegularQueryPredicateCreator.createGreaterThanPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, value);
        }

        return RegularQueryPredicateCreator.createEqualPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, value);
    }

    public static void addAndSeparatedByOr(final boolean toLowerCase, final int index, final BaseCriteria baseCriteria, final String attributeName, final Object value) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Predicate predicate = RegularQueryPredicateCreator.createEqualPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, value);
        final Predicate andPredicate = createAndPredicate(baseCriteria, predicate);

        baseCriteria.addAndSeparatedByOr(index, andPredicate);
    }

    public static void andIsMemberOf(final BaseCriteria baseCriteria, final String collectionName, final Object value) {
        final Path path = PathHelper.extractPath(baseCriteria, collectionName);

        final Predicate predicate = RegularQueryPredicateCreator.createAndIsMemberOf(baseCriteria.getCriteriaBuilder(), path, value);
        final Predicate andPredicate = createAndPredicate(baseCriteria, predicate);

        baseCriteria.addAndPredicate(andPredicate);
    }

    public static void andIsNotMemberOf(final BaseCriteria baseCriteria, final String collectionName, final Object value) {
        final Path path = PathHelper.extractPath(baseCriteria, collectionName);

        final Predicate predicate = RegularQueryPredicateCreator.createAndIsMemberOf(baseCriteria.getCriteriaBuilder(), path, value);
        final Predicate notPredicate = predicate.not();
        final Predicate andPredicate = createAndPredicate(baseCriteria, notPredicate);

        baseCriteria.addAndPredicate(andPredicate);
    }

    public static void andBetween(final boolean toLowerCase, final BaseCriteria baseCriteria, final String attributeName, final Object valueA, final Object valueB) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Comparable typedGreaterThan = getTypedValue(baseCriteria.getEntityClass(), attributeName, valueA);
        final Predicate equalPredicateA = RegularQueryPredicateCreator.createGreaterOrEqualToPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, typedGreaterThan);

        final Comparable typedLessOrEqualTo = getTypedValue(baseCriteria.getEntityClass(), attributeName, valueB);
        final Predicate equalPredicateB = RegularQueryPredicateCreator.createLessOrEqualToPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, typedLessOrEqualTo);

        finishWithAndPredicate(baseCriteria, equalPredicateA, equalPredicateB);
    }

    public static void andGreaterOrEqualTo(final boolean toLowerCase, final BaseCriteria baseCriteria, final String attributeName, final Object value) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Comparable typedValue = getTypedValue(baseCriteria.getEntityClass(), attributeName, value);
        final Predicate equalPredicate = RegularQueryPredicateCreator.createGreaterOrEqualToPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, typedValue);

        finishWithAndPredicate(baseCriteria, equalPredicate);
    }

    public static void andLessThan(final boolean toLowerCase, final BaseCriteria baseCriteria, final String attributeName, final Object value) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Comparable typedValue = getTypedValue(baseCriteria.getEntityClass(), attributeName, value);
        final Predicate equalPredicate = RegularQueryPredicateCreator.createLessThanPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, typedValue);

        finishWithAndPredicate(baseCriteria, equalPredicate);
    }

    public static void andLessOrEqualTo(final boolean toLowerCase, final BaseCriteria baseCriteria, final String attributeName, final Object value) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Comparable typedValue = getTypedValue(baseCriteria.getEntityClass(), attributeName, value);
        final Predicate equalPredicate = RegularQueryPredicateCreator.createLessOrEqualToPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, typedValue);

        finishWithAndPredicate(baseCriteria, equalPredicate);
    }

    public static void andGreaterThan(final boolean toLowerCase, final BaseCriteria baseCriteria, final String attributeName, final Object value) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Comparable typedValue = getTypedValue(baseCriteria.getEntityClass(), attributeName, value);
        final Predicate equalPredicate = RegularQueryPredicateCreator.createGreaterThanPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, typedValue);

        finishWithAndPredicate(baseCriteria, equalPredicate);
    }

    public static void orGreaterThan(final boolean toLowerCase, final BaseCriteria baseCriteria, final String attributeName, final Object value) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Comparable typedValue = getTypedValue(baseCriteria.getEntityClass(), attributeName, value);
        final Predicate equalPredicate = RegularQueryPredicateCreator.createGreaterThanPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, typedValue);

        finishWithAndPredicate(baseCriteria, equalPredicate);
    }

    public static void orNotEquals(final boolean toLowerCase, final BaseCriteria baseCriteria, final String attributeName, final Object[] valueArray) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        for (final Object value : valueArray) {
            final Predicate equalPredicate = RegularQueryPredicateCreator.createEqualPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, value);
            final Predicate notEqualPredicate = equalPredicate.not();
            final Predicate andPredicate = createAndPredicate(baseCriteria, notEqualPredicate);

            baseCriteria.addOrPredicate(BaseCriteria.DEFAULT_OR_PREDICATE_INDEX, andPredicate);
        }
    }

    public static void andIsNull(final BaseCriteria baseCriteria, final String attributeName) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);
        final Predicate isNullPredicate = RegularQueryPredicateCreator.createIsNullPredicate(baseCriteria.getCriteriaBuilder(), path);

        finishWithAndPredicate(baseCriteria, isNullPredicate);
    }

    public static void andIsNotNull(final BaseCriteria baseCriteria, final String attributeName) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);
        final Predicate isNullPredicate = RegularQueryPredicateCreator.createIsNullPredicate(baseCriteria.getCriteriaBuilder(), path);
        final Predicate notEqualPredicate = isNullPredicate.not();

        finishWithAndPredicate(baseCriteria, notEqualPredicate);
    }

    public static void andCollectionIsEmpty(final BaseCriteria baseCriteria, final String collectionName) {
        if (!ReflectionUtil.isSupportedCollection(baseCriteria.getEntityClass(), collectionName, true)) {
            throw new IllegalArgumentException("The attribute: " + collectionName + " is not a valid collection. The supported types are: List, Collection, Map or Set");
        }

        final Path path = PathHelper.extractPath(baseCriteria, collectionName);
        final Predicate isEmptyPredicate = RegularQueryPredicateCreator.createIsEmptyPredicate(baseCriteria.getCriteriaBuilder(), path);

        finishWithAndPredicate(baseCriteria, isEmptyPredicate);
    }

    public static void andCollectionIsNotEmpty(final BaseCriteria baseCriteria, final String collectionName) {
        if (!ReflectionUtil.isSupportedCollection(baseCriteria.getEntityClass(), collectionName, true)) {
            throw new IllegalArgumentException("The attribute: " + collectionName + " is not a valid collection. The supported types are: List, Collection, Map or Set");
        }

        final Path path = PathHelper.extractPath(baseCriteria, collectionName);
        final Predicate isEmptyPredicate = RegularQueryPredicateCreator.createIsEmptyPredicate(baseCriteria.getCriteriaBuilder(), path);
        final Predicate isNotEmptyPredicate = isEmptyPredicate.not();

        finishWithAndPredicate(baseCriteria, isNotEmptyPredicate);
    }

    public static void setDistinctTrue(final BaseCriteria baseCriteria) {
        baseCriteria.setDistinctTrue();
    }

    public static void andStringIn(final boolean toLowerCase, final BaseCriteria baseCriteria, final String attributeName, final List<String> valueList) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Predicate inPredicate = RegularQueryPredicateCreator.createStringInPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, valueList);

        finishWithAndPredicate(baseCriteria, inPredicate);
    }

    public static void andStringNotIn(final boolean toLowerCase, final BaseCriteria baseCriteria, final String attributeName, final List<String> valueList) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Predicate inPredicate = RegularQueryPredicateCreator.createStringInPredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, valueList);
        final Predicate notInPredicate = inPredicate.not();

        finishWithAndPredicate(baseCriteria, notInPredicate);
    }

    public static void andStringLike(final boolean toLowerCase, final BaseCriteria baseCriteria, final String attributeName, final String value) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Predicate likePredicate = RegularQueryPredicateCreator.createLikePredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, value);

        finishWithAndPredicate(baseCriteria, likePredicate);
    }

    public static void andStringNotLike(final boolean toLowerCase, final BaseCriteria baseCriteria, final String attributeName, final String value) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Predicate likePredicate = RegularQueryPredicateCreator.createLikePredicate(toLowerCase, baseCriteria.getCriteriaBuilder(), path, value);
        final Predicate notLikePredicate = likePredicate.not();

        finishWithAndPredicate(baseCriteria, notLikePredicate);
    }

    public static <E> void andAttributeIn(final BaseCriteria baseCriteria, final String attributeName, final List<E> attributeList) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Predicate inPredicate = RegularQueryPredicateCreator.createAttributeInPredicate(path, attributeList);

        finishWithAndPredicate(baseCriteria, inPredicate);
    }

    public static void andAttributeIn(final BaseCriteria baseCriteria, final String attributeName, final SubQueryImp uaiSubQuery) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Predicate inPredicate = RegularQueryPredicateCreator.createAttributeInPredicate(baseCriteria.getCriteriaBuilder(), path, uaiSubQuery);

        finishWithAndPredicate(baseCriteria, inPredicate);
    }

    public static void andAttributeNotIn(final BaseCriteria baseCriteria, final String attributeName, final SubQueryImp uaiSubQuery) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Predicate inPredicate = RegularQueryPredicateCreator.createAttributeInPredicate(baseCriteria.getCriteriaBuilder(), path, uaiSubQuery);

        finishWithAndPredicate(baseCriteria, inPredicate.not());
    }

    public static <E> void andAttributeNotIn(final BaseCriteria baseCriteria, final String attributeName, final List<E> attributeList) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Predicate inPredicate = RegularQueryPredicateCreator.createAttributeInPredicate(path, attributeList);
        final Predicate notInPredicate = inPredicate.not();

        finishWithAndPredicate(baseCriteria, notInPredicate);
    }
}