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
package com.uaihebert.uaicriteria.criteria;

import com.uaihebert.uaicriteria.base.element.BaseCriteria;
import com.uaihebert.uaicriteria.base.element.BasicCriteriaElements;
import com.uaihebert.uaicriteria.path.MultiSelectQueryPathCreator;
import com.uaihebert.uaicriteria.path.RegularQueryPathCreator;
import com.uaihebert.uaicriteria.subquery.SubQueryImp;

import java.util.List;

public class CriteriaCreator {
    private static final boolean DO_NOT_USE_LOWER_CASE = false;

    private final BasicCriteriaElements basicCriteriaElements;

    public CriteriaCreator(final BasicCriteriaElements basicCriteriaElements) {
        this.basicCriteriaElements = basicCriteriaElements;
    }

    public void andEquals(final boolean toLowerCase, final String attributeName, final Object value) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andEquals(toLowerCase, baseCriteria, attributeName, value);
        }
    }

    public void andNotEquals(final boolean toLowerCase, final String attributeName, final Object value) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andNotEquals(toLowerCase, baseCriteria, attributeName, value);
        }
    }

    public void innerJoin(final String joinName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.innerJoin(joinName, baseCriteria);
        }
    }

    public void innerJoinFetch(final String joinName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.innerJoinFetch(joinName, baseCriteria);
        }
    }

    public void leftJoinFetch(final String joinName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.leftJoinFetch(joinName, baseCriteria);
        }
    }

    public void leftJoin(final String joinName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.leftJoin(joinName, baseCriteria);
        }
    }

    public void orderByDesc(final String attributeName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.orderBy(attributeName, baseCriteria, CriteriaResultOrderBy.DESC);
        }
    }

    public void orderByAsc(final String attributeName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.orderBy(attributeName, baseCriteria, CriteriaResultOrderBy.ASC);
        }
    }

    public void setFirstResult(final int firstResult) {
        basicCriteriaElements.setFirstResult(firstResult);
    }

    public void setMaxResults(final int maxResults) {
        basicCriteriaElements.setMaxResults(maxResults);
    }

    public void addHint(final String key, final Object value) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andHint(baseCriteria, key, value);
        }
    }

    public void orEquals(final boolean toLowerCase, final String attributeName, final Object... valueArray) {
        orEquals(toLowerCase, BaseCriteria.DEFAULT_OR_PREDICATE_INDEX, attributeName, valueArray, CriteriaOrType.EQUALS);
    }

    public void orLike(final boolean toLowerCase, final String attributeName, final Object... valueArray) {
        orEquals(toLowerCase, BaseCriteria.DEFAULT_OR_PREDICATE_INDEX, attributeName, valueArray, CriteriaOrType.LIKE);
    }

    public void orNotLike(final boolean toLowerCase, final String attributeName, final Object... valueArray) {
        orEquals(toLowerCase, BaseCriteria.DEFAULT_OR_PREDICATE_INDEX, attributeName, valueArray, CriteriaOrType.NOT_LIKE);
    }

    public void orEquals(final boolean toLowerCase, final int index, final String attributeName, final Object[] valueArray, final CriteriaOrType orType) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.addOr(toLowerCase, index, baseCriteria, attributeName, valueArray, orType);
        }
    }

    public void addAndSeparatedByOr(final boolean toLowerCase, final int index, final String attributeName, final Object value) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.addAndSeparatedByOr(toLowerCase, index, baseCriteria, attributeName, value);
        }
    }

    public void andIsMemberOf(final Object value, final String collectionName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andIsMemberOf(baseCriteria, collectionName, value);
        }
    }

    public void andIsNotMemberOf(final Object value, final String collectionName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andIsNotMemberOf(baseCriteria, collectionName, value);
        }
    }

    public void andBetween(final boolean toLowerCase, final String attributeName, final Object valueA, final Object valueB) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andBetween(toLowerCase, baseCriteria, attributeName, valueA, valueB);
        }
    }

    public void andGreaterOrEqualTo(final boolean toLowerCase, final String attributeName, final Object value) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andGreaterOrEqualTo(toLowerCase, baseCriteria, attributeName, value);
        }
    }

    public void andLessThan(final boolean toLowerCase, final String attributeName, final Object value) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andLessThan(toLowerCase, baseCriteria, attributeName, value);
        }
    }

    public void andLessOrEqualTo(final boolean toLowerCase, final String attributeName, final Object value) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andLessOrEqualTo(toLowerCase, baseCriteria, attributeName, value);
        }
    }

    public void andGreaterThan(final boolean toLowerCase, final String attributeName, final Object value) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andGreaterThan(toLowerCase, baseCriteria, attributeName, value);
        }
    }

    public void orNotEquals(final boolean toLowerCase, final String attributeName, final Object[] valueArray) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.orNotEquals(toLowerCase, baseCriteria, attributeName, valueArray);
        }
    }

    public void andIsNull(final String attributeName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andIsNull(baseCriteria, attributeName);
        }
    }

    public void orIsNull(final String attributeName) {
        orEquals(DO_NOT_USE_LOWER_CASE, BaseCriteria.DEFAULT_OR_PREDICATE_INDEX, attributeName, null, CriteriaOrType.IS_NULL);
    }

    public void andIsNotNull(final String attributeName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andIsNotNull(baseCriteria, attributeName);
        }
    }

    public void orIsNotNull(final String attributeName) {
        orEquals(DO_NOT_USE_LOWER_CASE, BaseCriteria.DEFAULT_OR_PREDICATE_INDEX, attributeName, null, CriteriaOrType.IS_NOT_NULL);
    }

    public void andCollectionIsEmpty(final String collectionName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andCollectionIsEmpty(baseCriteria, collectionName);
        }
    }

    public void andCollectionIsNotEmpty(final String collectionName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andCollectionIsNotEmpty(baseCriteria, collectionName);
        }
    }

    public void setDistinctTrue() {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.setDistinctTrue(baseCriteria);
        }
    }

    public void andStringIn(final boolean toLowerCase, final String attributeName, final List<String> valueList) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andStringIn(toLowerCase, baseCriteria, attributeName, valueList);
        }
    }

    public void andStringNotIn(final boolean toLowerCase, final String attributeName, final List<String> valueList) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andStringNotIn(toLowerCase, baseCriteria, attributeName, valueList);
        }
    }

    public void andStringLike(final boolean toLowerCase, final String attributeName, final String value) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andStringLike(toLowerCase, baseCriteria, attributeName, value);
        }
    }

    public void andStringNotLike(final boolean toLowerCase, final String attributeName, final String value) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andStringNotLike(toLowerCase, baseCriteria, attributeName, value);
        }
    }

    public <E> void andAttributeIn(final String attributeName, final List<E> attributeList) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andAttributeIn(baseCriteria, attributeName, attributeList);
        }
    }

    public void andAttributeIn(final String attributeName, final SubQueryImp uaiSubQuery) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andAttributeIn(baseCriteria, attributeName, uaiSubQuery);
        }
    }

    public void andAttributeNotIn(final String attributeName, final SubQueryImp uaiSubQuery) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andAttributeNotIn(baseCriteria, attributeName, uaiSubQuery);
        }
    }

    public <E> void andAttributeNotIn(final String attributeName, final List<E> attributeList) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            RegularQueryPathCreator.andAttributeNotIn(baseCriteria, attributeName, attributeList);
        }
    }

    public void sum(final String... attributeNameArray) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.sum(baseCriteria, attributeNameArray);
        }
    }

    public void addMultiSelectSelectAttribute(final String... attributeNameArray) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.addMultiSelectSelectAttribute(baseCriteria, attributeNameArray);
        }
    }

    public void avg(final String... attributeNameArray) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.avg(baseCriteria, attributeNameArray);
        }
    }

    public void square(final String... attributeNameArray) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.square(baseCriteria, attributeNameArray);
        }
    }

    public <N extends Number> void sum(final String attributeName, final N number) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.sum(baseCriteria, attributeName, number);
        }
    }

    public <N extends Number> void sum(final N number, final String attributeName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.sum(baseCriteria, number, attributeName);
        }
    }

    public void groupBy(final String[] attributeName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.groupBy(baseCriteria, attributeName);
        }
    }

    public void diff(final String firstAttribute, final String secondAttribute) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.diff(baseCriteria, firstAttribute, secondAttribute);
        }
    }

    public <N extends Number> void diff(final String attributeName, final N number) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.diff(baseCriteria, attributeName, number);
        }
    }

    public <N extends Number> void diff(final N number, final String attributeName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.diff(baseCriteria, number, attributeName);
        }
    }

    public void multiply(final String firstAttribute, final String secondAttribute) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.multiply(baseCriteria, firstAttribute, secondAttribute);
        }
    }

    public <N extends Number> void multiply(final String attributeName, final N number) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.multiply(baseCriteria, attributeName, number);
        }
    }

    public <N extends Number> void multiply(final N number, final String attributeName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.multiply(baseCriteria, number, attributeName);
        }
    }

    public void divide(final String firstAttribute, final String secondAttribute) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.divide(baseCriteria, firstAttribute, secondAttribute);
        }
    }

    public <N extends Number> void divide(final String attributeName, final N number) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.divide(baseCriteria, attributeName, number);
        }
    }

    public <N extends Number> void divide(final N number, final String attributeName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.divide(baseCriteria, number, attributeName);
        }
    }

    public void module(final String firstAttribute, final String secondAttribute) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.module(baseCriteria, firstAttribute, secondAttribute);
        }
    }

    public void module(final String attributeName, final Integer number) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.module(baseCriteria, attributeName, number);
        }
    }

    public void module(final Integer number, final String attributeName) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.module(baseCriteria, number, attributeName);
        }
    }

    public void countAttribute(final String[] attributeArray) {
        final List<BaseCriteria> baseCriteriaList = basicCriteriaElements.getBaseCriteriaList();

        for (final BaseCriteria baseCriteria : baseCriteriaList) {
            MultiSelectQueryPathCreator.countAttribute(baseCriteria, attributeArray);
        }
    }
}