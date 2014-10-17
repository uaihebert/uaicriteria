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
import com.uaihebert.uaicriteria.predicate.MultiSelectQueryPredicateCreator;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;

public final class MultiSelectQueryPathCreator {

    private MultiSelectQueryPathCreator() {
    }

    public static void sum(final BaseCriteria baseCriteria, final String... attributeNameArray) {
        for (final String attributeName : attributeNameArray) {
            final Path path = PathHelper.extractPath(baseCriteria, attributeName);

            final Expression sumPredicate = MultiSelectQueryPredicateCreator.sum(baseCriteria.getCriteriaBuilder(), path);

            baseCriteria.addMultiSelectOperationExpression(sumPredicate);
        }
    }

    public static void addMultiSelectSelectAttribute(final BaseCriteria baseCriteria, final String... attributeNameArray) {
        for (final String attributeName : attributeNameArray) {
            final Path path = PathHelper.extractPath(baseCriteria, attributeName);
            baseCriteria.addMultiSelectPathException(path);
        }
    }

    public static void avg(final BaseCriteria baseCriteria, final String... attributeNameArray) {
        for (final String attributeName : attributeNameArray) {
            final Path path = PathHelper.extractPath(baseCriteria, attributeName);

            final Expression avgPredicate = MultiSelectQueryPredicateCreator.avg(baseCriteria.getCriteriaBuilder(), path);

            baseCriteria.addMultiSelectOperationExpression(avgPredicate);
        }
    }

    public static void square(final BaseCriteria baseCriteria, final String... attributeNameArray) {
        for (final String attributeName : attributeNameArray) {
            final Path path = PathHelper.extractPath(baseCriteria, attributeName);

            final Expression squarePredicate = MultiSelectQueryPredicateCreator.square(baseCriteria.getCriteriaBuilder(), path);

            baseCriteria.addMultiSelectOperationExpression(squarePredicate);
        }
    }

    public static <N extends Number> void sum(final BaseCriteria baseCriteria, final String attributeName, final N number) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.sum(baseCriteria.getCriteriaBuilder(), path, number);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static <N extends Number> void sum(final BaseCriteria baseCriteria, final N number, final String attributeName) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.sum(baseCriteria.getCriteriaBuilder(), number, path);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static void groupBy(final BaseCriteria baseCriteria, final String[] attributeNameArray) {
        for (final String attributeName : attributeNameArray) {
            final Path path = PathHelper.extractPath(baseCriteria, attributeName);
            baseCriteria.groupBy(path);
        }
    }

    public static void diff(final BaseCriteria baseCriteria, final String firstAttribute, final String secondAttribute) {
        final Path firstPath = PathHelper.extractPath(baseCriteria, firstAttribute);
        final Path secondPath = PathHelper.extractPath(baseCriteria, secondAttribute);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.diff(baseCriteria.getCriteriaBuilder(), firstPath, secondPath);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static <N extends Number> void diff(final BaseCriteria baseCriteria, final String attributeName, final N number) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.diff(baseCriteria.getCriteriaBuilder(), path, number);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static <N extends Number> void diff(final BaseCriteria baseCriteria, final N number, final String attributeName) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.diff(baseCriteria.getCriteriaBuilder(), number, path);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static void multiply(final BaseCriteria baseCriteria, final String firstAttribute, final String secondAttribute) {
        final Path firstPath = PathHelper.extractPath(baseCriteria, firstAttribute);
        final Path secondPath = PathHelper.extractPath(baseCriteria, secondAttribute);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.multiply(baseCriteria.getCriteriaBuilder(), firstPath, secondPath);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static <N extends Number> void multiply(final BaseCriteria baseCriteria, final String attributeName, final N number) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.multiply(baseCriteria.getCriteriaBuilder(), path, number);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static <N extends Number> void multiply(final BaseCriteria baseCriteria, final N number, final String attributeName) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.multiply(baseCriteria.getCriteriaBuilder(), number, path);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static void divide(final BaseCriteria baseCriteria, final String firstAttribute, final String secondAttribute) {
        final Path firstPath = PathHelper.extractPath(baseCriteria, firstAttribute);
        final Path secondPath = PathHelper.extractPath(baseCriteria, secondAttribute);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.divide(baseCriteria.getCriteriaBuilder(), firstPath, secondPath);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static <N extends Number> void divide(final BaseCriteria baseCriteria, final String attributeName, final N number) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.divide(baseCriteria.getCriteriaBuilder(), path, number);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static <N extends Number> void divide(final BaseCriteria baseCriteria, final N number, final String attributeName) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.divide(baseCriteria.getCriteriaBuilder(), number, path);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static void module(final BaseCriteria baseCriteria, final String firstAttribute, final String secondAttribute) {
        final Path firstPath = PathHelper.extractPath(baseCriteria, firstAttribute);
        final Path secondPath = PathHelper.extractPath(baseCriteria, secondAttribute);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.module(baseCriteria.getCriteriaBuilder(), firstPath, secondPath);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static void module(final BaseCriteria baseCriteria, final String attributeName, final Integer number) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.module(baseCriteria.getCriteriaBuilder(), path, number);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static void module(final BaseCriteria baseCriteria, final Integer number, final String attributeName) {
        final Path path = PathHelper.extractPath(baseCriteria, attributeName);

        final Expression sumPredicate = MultiSelectQueryPredicateCreator.module(baseCriteria.getCriteriaBuilder(), number, path);

        baseCriteria.addMultiSelectOperationExpression(sumPredicate);
    }

    public static void countAttribute(final BaseCriteria baseCriteria, final String[] attributeArray) {
        for (final String attributeName : attributeArray) {
            final Path path = PathHelper.extractPath(baseCriteria, attributeName);

            final Expression countPredicate = MultiSelectQueryPredicateCreator.count(baseCriteria.getCriteriaBuilder(), path);

            baseCriteria.addMultiSelectOperationExpression(countPredicate);
        }
    }
}