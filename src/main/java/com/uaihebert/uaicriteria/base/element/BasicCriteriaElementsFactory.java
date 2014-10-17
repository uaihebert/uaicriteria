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

import com.uaihebert.uaicriteria.criteria.QueryType;

import javax.persistence.EntityManager;

public final class BasicCriteriaElementsFactory {

    private BasicCriteriaElementsFactory() {
    }

    public static <T> BasicCriteriaElements create(final EntityManager entityManager, final Class<T> entityClass, final QueryType queryType) {
        if (QueryType.TUPLE.equals(queryType)) {
            return createForMultiSelectQuery(entityManager, entityClass);
        }

        return createForRegularQuery(entityManager, entityClass);
    }

    public static <T> BasicCriteriaElements createSubQuery(final String selectedAttribute, final Class<T> subQueryClass, final BasicCriteriaElements basicCriteriaElements) {
        final BasicCriteriaElements subQueryBasicElements = createBasicElements(basicCriteriaElements.entityManager);

        final BaseCriteria subQueryBaseCriteria = BaseCriteriaFactory.createSubQueryBaseCriteria(basicCriteriaElements, selectedAttribute, subQueryClass);
        subQueryBasicElements.setSubQueryCriteria(subQueryBaseCriteria);

        return subQueryBasicElements;
    }

    private static <T> BasicCriteriaElements createForRegularQuery(final EntityManager entityManager, final Class<T> entityClass) {
        final BasicCriteriaElements basicCriteriaElements = createBasicElements(entityManager);

        final BaseCriteria<T> countBaseCriteria = BaseCriteriaFactory.createCountBaseCriteria(entityManager, entityClass);
        basicCriteriaElements.setCountCriteria(countBaseCriteria);

        final BaseCriteria<T> regularBaseCriteria = BaseCriteriaFactory.createRegularBaseCriteria(entityManager, entityClass);
        basicCriteriaElements.setRegularCriteria(regularBaseCriteria);

        return basicCriteriaElements;
    }

    private static <T> BasicCriteriaElements createForMultiSelectQuery(final EntityManager entityManager, final Class<T> entityClass) {
        final BasicCriteriaElements basicCriteriaElements = createBasicElements(entityManager);

        final BaseCriteria<T> multiSelectBaseCriteria = BaseCriteriaFactory.createMultiSelectBaseCriteria(entityManager, entityClass);
        basicCriteriaElements.setMultiSelectCriteria(multiSelectBaseCriteria);

        return basicCriteriaElements;
    }

    private static BasicCriteriaElements createBasicElements(final EntityManager entityManager) {
        final BasicCriteriaElements basicCriteriaElements = new BasicCriteriaElements();
        basicCriteriaElements.entityManager = entityManager;
        return basicCriteriaElements;
    }
}
