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

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasicCriteriaElements<T> {
    private Integer maxResults;
    private Integer firstResult;

    EntityManager entityManager;

    private BaseCriteria countCriteria;
    private BaseCriteria multiselectCriteria;
    private BaseCriteria regularCriteria;
    private BaseCriteria subQueryCriteria;

    private final List<BaseCriteria> baseCriteriaList = new ArrayList<BaseCriteria>();

    public TypedQuery<T> getRegularQuery() {
        if (regularCriteria == null) {
            throw new IllegalStateException("You cannot get a regular query when you create a multiselect query");
        }

        regularCriteria.setUpCriteria();

        final TypedQuery typedQuery = entityManager.createQuery(regularCriteria.getConvertedCriteriaQuery());

        setPagination(typedQuery);
        setUpHintMap(typedQuery, regularCriteria);

        return typedQuery;
    }

    public BaseCriteria<T> getBaseCriteriaForSubQuery() {
        return regularCriteria;
    }

    public TypedQuery<Long> getCountQuery() {
        if (multiselectCriteria != null) {
            throw new IllegalStateException("You cannot get a count from multiselect query. \n If you want to count, you should use the method that will count an attribute");
        }

        countCriteria.setCountSelect();

        countCriteria.setUpCriteria();

        final TypedQuery<Long> typedQuery = entityManager.createQuery(countCriteria.getConvertedCriteriaQuery());

        setPagination(typedQuery);
        setUpHintMap(typedQuery, countCriteria);

        return typedQuery;
    }

    public TypedQuery<Object> getMultiSelectQuery() {
        if (multiselectCriteria == null) {
            throw new IllegalStateException("You cannot get a multiselect query when you create a regular query");
        }

        multiselectCriteria.setMultiSelectSelect();

        multiselectCriteria.setUpCriteria();

        final TypedQuery<Object> typedQuery = entityManager.createQuery(multiselectCriteria.getConvertedCriteriaQuery());

        setPagination(typedQuery);
        setUpHintMap(typedQuery, multiselectCriteria);

        return typedQuery;
    }

    private void setUpHintMap(final TypedQuery typedQuery, final BaseCriteria baseCriteria) {
        final Map<String, String> createdHintMap = baseCriteria.getCreatedHintMap();
        for (final Map.Entry<String, String> entry : createdHintMap.entrySet()) {
            typedQuery.setHint(entry.getKey(), entry.getValue());
        }
    }


    private void setPagination(final TypedQuery typedQuery) {
        if (firstResult != null) {
            typedQuery.setFirstResult(firstResult);
        }

        if (maxResults != null) {
            typedQuery.setMaxResults(maxResults);
        }
    }

    public List<BaseCriteria> getBaseCriteriaList() {
        return baseCriteriaList;
    }

    public void setFirstResult(final Integer firstResult) {
        this.firstResult = firstResult;
    }

    public void setMaxResults(final Integer maxResults) {
        this.maxResults = maxResults;
    }

    public boolean isBatooProvider() {
        return entityManager.toString().contains("batoo");
    }

    public Subquery getSubquery() {
        return subQueryCriteria.getSubquery();
    }

    public BaseCriteria getBaseSubCriteria() {
        return subQueryCriteria;
    }

    public void setCountCriteria(final BaseCriteria countCriteria) {
        this.countCriteria = countCriteria;
        baseCriteriaList.add(this.countCriteria);
    }

    public void setMultiSelectCriteria(final BaseCriteria multiselectCriteria) {
        this.multiselectCriteria = multiselectCriteria;
        baseCriteriaList.add(this.multiselectCriteria);
    }

    public void setRegularCriteria(final BaseCriteria regularCriteria) {
        this.regularCriteria = regularCriteria;
        baseCriteriaList.add(this.regularCriteria);
    }

    public void setSubQueryCriteria(final BaseCriteria subQueryCriteria) {
        this.subQueryCriteria = subQueryCriteria;
        baseCriteriaList.add(this.subQueryCriteria);
    }
}