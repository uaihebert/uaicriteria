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
package com.uaihebert.uaicriteria.predicate;

import com.uaihebert.uaicriteria.subquery.SubQueryImp;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public final class RegularQueryPredicateCreator extends AbstractPredicateCreator {

    private RegularQueryPredicateCreator() {
        super();
    }

    public static Predicate createEqualPredicate(final boolean toLowerCase, final CriteriaBuilder criteriaBuilder, final Path path, final Object value) {
        if (toLowerCase) {
            final String valueAsString = (String) value;
            final Expression<String> lower = criteriaBuilder.lower(path);

            return criteriaBuilder.equal(lower, valueAsString.toLowerCase());
        }

        return criteriaBuilder.equal(path, value);
    }

    public static <Y extends Comparable<? super Y>> Predicate createGreaterThanPredicate(final boolean toLowerCase, final CriteriaBuilder criteriaBuilder, final Expression<? extends Y> path, final Y value) {
        if (toLowerCase) {
            final String loweredValue = createLoweredValue(value);

            final Expression<String> loweredExpression = createLoweredExpression(criteriaBuilder, path);

            return criteriaBuilder.greaterThan(loweredExpression, loweredValue);
        }

        return criteriaBuilder.greaterThan(path, value);
    }

    public static <Y extends Comparable<? super Y>> Predicate createGreaterOrEqualToPredicate(final boolean toLowerCase, final CriteriaBuilder criteriaBuilder, final Expression<? extends Y> path, final Y value) {
        if (toLowerCase) {
            final String loweredValue = createLoweredValue(value);

            final Expression<String> loweredExpression = createLoweredExpression(criteriaBuilder, path);

            return criteriaBuilder.greaterThanOrEqualTo(loweredExpression, loweredValue);
        }

        return criteriaBuilder.greaterThanOrEqualTo(path, value);
    }

    public static <Y extends Comparable<? super Y>> Predicate createLessThanPredicate(final boolean toLowerCase, final CriteriaBuilder criteriaBuilder, final Expression<? extends Y> path, final Y value) {
        if (toLowerCase) {
            final String loweredValue = createLoweredValue(value);

            final Expression<String> loweredExpression = createLoweredExpression(criteriaBuilder, path);

            return criteriaBuilder.lessThan(loweredExpression, loweredValue);
        }

        return criteriaBuilder.lessThan(path, value);
    }

    public static <Y extends Comparable<? super Y>> Predicate createLessOrEqualToPredicate(final boolean toLowerCase, final CriteriaBuilder criteriaBuilder, final Expression<? extends Y> path, final Y value) {
        if (toLowerCase) {
            final String loweredValue = createLoweredValue(value);

            final Expression<String> loweredExpression = createLoweredExpression(criteriaBuilder, path);

            return criteriaBuilder.lessThanOrEqualTo(loweredExpression, loweredValue);
        }

        return criteriaBuilder.lessThanOrEqualTo(path, value);
    }

    public static Predicate createIsNullPredicate(final CriteriaBuilder criteriaBuilder, final Path path) {
        return criteriaBuilder.isNull(path);
    }

    public static Predicate createIsEmptyPredicate(final CriteriaBuilder criteriaBuilder, final Path path) {
        return criteriaBuilder.isEmpty(path);
    }

    public static Predicate createLikePredicate(final boolean toLowerCase, final CriteriaBuilder criteriaBuilder, final Path path, final String value) {
        if (toLowerCase) {
            final String loweredValue = createLoweredValue(value);

            final Expression<String> loweredExpression = createLoweredExpression(criteriaBuilder, path);

            return criteriaBuilder.like(loweredExpression, loweredValue);
        }

        return criteriaBuilder.like(path, value);
    }

    public static Predicate createStringInPredicate(final boolean toLowerCase, final CriteriaBuilder criteriaBuilder, final Path path, final List<String> valueList) {
        if (toLowerCase) {
            final List<String> loweredList = new ArrayList<String>();

            for (final String value : valueList) {
                loweredList.add(value.toLowerCase());
            }

            final Expression<String> loweredExpression = createLoweredExpression(criteriaBuilder, path);

            return loweredExpression.in(loweredList);
        }

        return path.in(valueList);
    }

    public static Predicate createAndIsMemberOf(final CriteriaBuilder criteriaBuilder, final Path path, final Object value) {
        return criteriaBuilder.isMember(value, path);
    }

    public static <E> Predicate createAttributeInPredicate(final Path path, final List<E> attributeList) {
        return path.in(attributeList);
    }

    public static Predicate createAttributeInPredicate(final CriteriaBuilder criteriaBuilder, final Path path, final SubQueryImp uaiSubQuery) {
        uaiSubQuery.prepareSubQuery();

        return criteriaBuilder.in(path).value(uaiSubQuery.getSubQuery());
    }
}