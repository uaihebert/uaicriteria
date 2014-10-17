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
package com.uaihebert.uaicriteria.base.element;

import com.uaihebert.uaicriteria.wrapper.JoinWrapper;

import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseCriteria<T> {
    public static final int DEFAULT_OR_PREDICATE_INDEX = 0;

    private final Class<T> entityClass;

    private final Root<T> root;
    private final Subquery<T> subquery;
    private final CriteriaQuery<T> criteriaQuery;
    private final CriteriaBuilder criteriaBuilder;

    private final Map<String, String> createdHintMap = new HashMap<String, String>();
    private final Map<String, JoinWrapper> createdJoinWrapperMap = new HashMap<String, JoinWrapper>();

    private final List<Order> orderByList = new ArrayList<Order>();

    private final List<Predicate> createdPredicateList = new ArrayList<Predicate>();

    private final List<Expression> multiSelectSelectList = new ArrayList<Expression>();
    private final List<Expression> groupByList = new ArrayList<Expression>();

    private final Map<Integer, List<Predicate>> orPredicateMap = new HashMap<Integer, List<Predicate>>();
    private final Map<Integer, List<Predicate>> andSeparatedByOrPredicateMap = new HashMap<Integer, List<Predicate>>();

    public BaseCriteria(final Root<T> root, final CriteriaQuery<T> criteriaQuery, final CriteriaBuilder criteriaBuilder, final Class<T> entityClass) {
        this.root = root;
        this.subquery = null;
        this.criteriaQuery = criteriaQuery;
        this.criteriaBuilder = criteriaBuilder;
        this.entityClass = entityClass;
    }

    public BaseCriteria(final Root<T> root, final Subquery<T> subquery, final CriteriaBuilder criteriaBuilder, final Class<T> entityClass) {
        this.root = root;
        this.subquery = subquery;
        this.criteriaQuery = null;
        this.criteriaBuilder = criteriaBuilder;
        this.entityClass = entityClass;
    }

    public AbstractQuery<T> getCriteriaQuery() {
        if (subquery != null) {
            return subquery;
        }

        return criteriaQuery;
    }

    public Map<String, String> getCreatedHintMap() {
        final Map<String, String> result = new HashMap<String, String>(createdHintMap);

        createdHintMap.clear();

        return result;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public Subquery<T> getSubquery() {
        return subquery;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void addAndPredicate(final Predicate predicate) {
        createdPredicateList.add(predicate);
    }

    public void setUpCriteria() {
        addWhereConditions();
        addOrderByConditions();
        addGroupByValues();
    }

    private void addOrderByConditions() {
        if (orderByList.isEmpty()) {
            return;
        }

        final List<Order> ordinationList = new ArrayList<Order>(orderByList);

        final CriteriaQuery<T> convertedCriteriaQuery = getConvertedCriteriaQuery();

        convertedCriteriaQuery.orderBy(ordinationList);

        orderByList.clear();
    }

    public CriteriaQuery<T> getConvertedCriteriaQuery() {
        final AbstractQuery<T> abstractQuery = getCriteriaQuery();

        return (CriteriaQuery) abstractQuery;
    }

    private void addWhereConditions() {
        final List<Predicate> conditionList = extractAllConditions();

        if (conditionList.isEmpty()) {
            return;
        }

        final Predicate[] predicateArraySize = new Predicate[conditionList.size()];
        final Predicate[] predicateArray = conditionList.toArray(predicateArraySize);

        getCriteriaQuery().where(predicateArray);
    }

    private List<Predicate> extractAllConditions() {
        final List<Predicate> conditionList = new ArrayList<Predicate>();

        addWhereConditions(conditionList);
        addOrConditions(conditionList);
        addAndSeparatedByConditions(conditionList);

        return conditionList;
    }

    private void addAndSeparatedByConditions(final List<Predicate> conditionList) {
        if (andSeparatedByOrPredicateMap.isEmpty()) {
            return;
        }

        final List<Predicate> orPredicateList = convertCriteriaToAndOrPredicateList();

        final Predicate or = createOrPredicateFromList(orPredicateList);

        orPredicateList.clear();
        orPredicateList.add(or);

        conditionList.addAll(orPredicateList);
    }

    private Predicate createOrPredicateFromList(final List<Predicate> orPredicateList) {
        final Predicate[] predicateArraySize = new Predicate[orPredicateList.size()];
        final Predicate[] predicateArray = orPredicateList.toArray(predicateArraySize);

        return criteriaBuilder.or(predicateArray);
    }

    private List<Predicate> convertCriteriaToAndOrPredicateList() {
        final List<Predicate> orPredicateList = new ArrayList<Predicate>();

        for (final List<Predicate> predicateList : andSeparatedByOrPredicateMap.values()) {
            final Predicate and = createAndCondition(predicateList);
            final Predicate orPredicate = criteriaBuilder.or(and);

            orPredicateList.add(orPredicate);
        }
        return orPredicateList;
    }

    private Predicate createAndCondition(final List<Predicate> predicateList) {
        final Predicate[] predicateArraySize = new Predicate[predicateList.size()];
        final Predicate[] predicateArray = predicateList.toArray(predicateArraySize);

        return criteriaBuilder.and(predicateArray);
    }

    private void addOrConditions(final List<Predicate> conditionList) {
        if (orPredicateMap.isEmpty()) {
            return;
        }

        final List<Predicate> orPredicateList = new ArrayList<Predicate>();

        for (final List<Predicate> predicateList : orPredicateMap.values()) {
            final Predicate orPredicate = createOrPredicateFromList(predicateList);
            orPredicateList.add(orPredicate);
        }

        conditionList.addAll(orPredicateList);
    }

    private void addWhereConditions(final List<Predicate> conditionList) {
        conditionList.addAll(createdPredicateList);
    }

    public JoinWrapper addJoin(final String joinName, final JoinType joinType, final boolean isFetch) {
        final JoinWrapper join = new JoinWrapper(root);

        join.createJoinInRoot(joinName, joinType, isFetch);

        createdJoinWrapperMap.put(joinName, join);

        return join;
    }

    public JoinWrapper addJoinFromJoin(final String joinName, final JoinWrapper joinWrapper) {
        createdJoinWrapperMap.put(joinName, joinWrapper);

        return joinWrapper;
    }

    public JoinWrapper getJoin(final String joinName) {
        final JoinWrapper join = createdJoinWrapperMap.get(joinName);

        if (join == null) {
            throw new IllegalArgumentException("The requested Join: " + joinName + " was not found. " +
                    "\n Did you create the join by invoking any of the join methods?");
        }

        return join;
    }

    public Path getPath(final String requiredPath) {
        return root.get(requiredPath);
    }

    public void addHint(final String key, final String value) {
        createdHintMap.put(key, value);
    }

    public void addOrPredicate(final int index, final Predicate predicate) {
        final List<Predicate> predicateList = getOrPredicateList(index);
        predicateList.add(predicate);
    }

    private List<Predicate> getOrPredicateList(final int index) {
        List<Predicate> predicateList = orPredicateMap.get(index);

        if (predicateList == null) {
            predicateList = new ArrayList<Predicate>();
            orPredicateMap.put(index, predicateList);
        }

        return predicateList;
    }

    private List<Predicate> getAndSeparatedByOrPredicateList(final int index) {
        List<Predicate> predicateList = andSeparatedByOrPredicateMap.get(index);

        if (predicateList == null) {
            predicateList = new ArrayList<Predicate>();
            andSeparatedByOrPredicateMap.put(index, predicateList);
        }

        return predicateList;
    }

    public void addAndSeparatedByOr(final int index, final Predicate predicate) {
        final List<Predicate> predicateList = getAndSeparatedByOrPredicateList(index);
        predicateList.add(predicate);
    }

    public boolean hasJoin(final String currentJoin) {
        return createdJoinWrapperMap.containsKey(currentJoin);
    }

    public void addOrdination(final Order currentOrdination) {
        orderByList.add(currentOrdination);
    }

    public void setDistinctTrue() {
        getCriteriaQuery().distinct(true);
    }

    private CriteriaQuery<Long> getCountCriteriaQuery() {
        return (CriteriaQuery<Long>) criteriaQuery;
    }

    public void setCountSelect() {
        if (getCriteriaQuery().isDistinct()) {
            useCountDistinctInsteadOfQueryDistinct();
            return;
        }

        getCountCriteriaQuery().select(criteriaBuilder.count(root));
    }

    public void setMultiSelectSelect() {
        addMultiSelectMultiSelectValues();
    }

    private void addGroupByValues() {
        if (groupByList.isEmpty()) {
            return;
        }

        final Expression[] multiSelectGroupByArray = new Expression[groupByList.size()];
        groupByList.toArray(multiSelectGroupByArray);

        getCriteriaQuery().groupBy(multiSelectGroupByArray);
    }

    private void addMultiSelectMultiSelectValues() {
        if (multiSelectSelectList.isEmpty()) {
            throw new IllegalStateException("You want to extract a multiSelect query, but you have not added any attribute or method to the query");
        }

        final Selection[] multiSelectSelectArray = new Selection[multiSelectSelectList.size()];
        multiSelectSelectList.toArray(multiSelectSelectArray);

        getConvertedCriteriaQuery().multiselect(multiSelectSelectArray);
    }

    private void useCountDistinctInsteadOfQueryDistinct() {
        getCriteriaQuery().distinct(false);
        getCountCriteriaQuery().select(criteriaBuilder.countDistinct(root));
    }

    public void addMultiSelectOperationExpression(final Expression multiSelectPredicate) {
        multiSelectSelectList.add(multiSelectPredicate);
    }

    public void addMultiSelectPathException(final Expression multiSelectPredicate) {
        multiSelectSelectList.add(multiSelectPredicate);
    }

    public void groupBy(final Path attributeToGroup) {
        groupByList.add(attributeToGroup);
    }
}