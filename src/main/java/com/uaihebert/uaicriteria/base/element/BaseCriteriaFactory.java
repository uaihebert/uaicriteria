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

import com.uaihebert.uaicriteria.path.PathHelper;
import com.uaihebert.util.ReflectionUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

public final class BaseCriteriaFactory {
    private BaseCriteriaFactory() {
    }

    public static <T> BaseCriteria<T> createRegularBaseCriteria(final EntityManager entityManager, final Class<T> entityClass) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        final Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);

        return new BaseCriteria(root, criteriaQuery, criteriaBuilder, entityClass);
    }

    public static <T> BaseCriteria<T> createCountBaseCriteria(final EntityManager entityManager, final Class<T> entityClass) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        final Root<T> root = criteriaQuery.from(entityClass);

        return new BaseCriteria(root, criteriaQuery, criteriaBuilder, entityClass);
    }

    public static <T> BaseCriteria<T> createMultiSelectBaseCriteria(final EntityManager entityManager, final Class<T> entityClass) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
        final Root<T> root = criteriaQuery.from(entityClass);

        return new BaseCriteria(root, criteriaQuery, criteriaBuilder, entityClass);
    }

    public static <T> BaseCriteria createSubQueryBaseCriteria(final BasicCriteriaElements basicCriteriaElements, final String selectedAttribute, final Class<T> subQueryClass) {
        final BaseCriteria baseForSubQuery = basicCriteriaElements.getBaseCriteriaForSubQuery();

        final Class selectedAttributeClass = extractClassFromAttribute(selectedAttribute, subQueryClass);

        final Subquery subquery = baseForSubQuery.getCriteriaQuery().subquery(selectedAttributeClass);

        final Root subQueryRoot = subquery.from(subQueryClass);

        final BaseCriteria subCriteria = new BaseCriteria(subQueryRoot, subquery, baseForSubQuery.getCriteriaBuilder(), subQueryClass);

        final Path subSelectSelectedAttribute = PathHelper.extractPath(subCriteria, selectedAttribute);

        subquery.select(subSelectSelectedAttribute);

        return subCriteria;
    }

    private static <T> Class extractClassFromAttribute(final String selectedAttribute, final Class<T> subQueryClass) {
        final String[] attributeArray = {selectedAttribute};

        return ReflectionUtil.getClassOfTheLastAttribute(attributeArray, subQueryClass);
    }
}