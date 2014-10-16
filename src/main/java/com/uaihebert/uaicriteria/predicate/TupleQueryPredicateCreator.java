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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;

public final class TupleQueryPredicateCreator extends AbstractPredicateCreator {

    public static Expression sum(final CriteriaBuilder criteriaBuilder, final Path<Number> path) {
        return criteriaBuilder.sum(path);
    }

    public static Expression avg(final CriteriaBuilder criteriaBuilder, final Path<Number> path) {
        return criteriaBuilder.avg(path);
    }

    public static Expression square(final CriteriaBuilder criteriaBuilder, final Path<Number> path) {
        return criteriaBuilder.sqrt(path);
    }

    public static <N extends Number> Expression sum(final CriteriaBuilder criteriaBuilder, final Path path, final N number) {
        return criteriaBuilder.sum(path, number);
    }

    public static <N extends Number> Expression sum(final CriteriaBuilder criteriaBuilder, final N number, final Path path) {
        return criteriaBuilder.sum(number, path);
    }

    public static Expression diff(final CriteriaBuilder criteriaBuilder, final Path<Number> firstPath, final Path<Number> secondAttribute) {
        return criteriaBuilder.diff(firstPath, secondAttribute);
    }

    public static <N extends Number> Expression diff(final CriteriaBuilder criteriaBuilder, final Path<Number> path, final N number) {
        return criteriaBuilder.diff(path, number);
    }

    public static <N extends Number> Expression diff(final CriteriaBuilder criteriaBuilder, final N number, final Path path) {
        return criteriaBuilder.diff(number, path);
    }

    public static Expression multiply(final CriteriaBuilder criteriaBuilder, final Path<Number> firstPath, final Path<Number> secondAttribute) {
        return criteriaBuilder.prod(firstPath, secondAttribute);
    }

    public static <N extends Number> Expression multiply(final CriteriaBuilder criteriaBuilder, final Path<Number> path, final N number) {
        return criteriaBuilder.prod(path, number);
    }

    public static <N extends Number> Expression multiply(final CriteriaBuilder criteriaBuilder, final N number, final Path path) {
        return criteriaBuilder.prod(number, path);
    }

    public static Expression divide(final CriteriaBuilder criteriaBuilder, final Path<Number> firstPath, final Path<Number> secondAttribute) {
        return criteriaBuilder.quot(firstPath, secondAttribute);
    }

    public static <N extends Number> Expression divide(final CriteriaBuilder criteriaBuilder, final Path<Number> path, final N number) {
        return criteriaBuilder.quot(path, number);
    }

    public static <N extends Number> Expression divide(final CriteriaBuilder criteriaBuilder, final N number, final Path path) {
        return criteriaBuilder.quot(number, path);
    }

    public static Expression module(final CriteriaBuilder criteriaBuilder, final Path<Integer> firstPath, final Path<Integer> secondAttribute) {
        return criteriaBuilder.mod(firstPath, secondAttribute);
    }

    public static Expression module(final CriteriaBuilder criteriaBuilder, final Path<Integer> path, final Integer number) {
        return criteriaBuilder.mod(path, number);
    }

    public static Expression module(final CriteriaBuilder criteriaBuilder, final Integer number, final Path<Integer> path) {
        return criteriaBuilder.mod(number, path);
    }

    public static Expression count(final CriteriaBuilder criteriaBuilder, final Path path) {
        return criteriaBuilder.count(path);
    }
}