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
package com.uaihebert.uaicriteria;

import com.uaihebert.cto.UaiCTOImp;
import com.uaihebert.cto.UaiCriteriaBuilder;
import com.uaihebert.uaicriteria.criteria.QueryType;

import javax.persistence.EntityManager;

public final class UaiCriteriaFactory {

    private UaiCriteriaFactory() {
    }

    public static <T> UaiCriteria<T> createQueryCriteria(final EntityManager entityManager, final Class<T> classToUse) {
        return createQueryCriteria(entityManager, classToUse, null, QueryType.REGULAR);
    }

    public static <T> UaiCriteria<T> createTupleCriteria(final EntityManager entityManager, final Class<T> classToUse) {
        return createQueryCriteria(entityManager, classToUse, null, QueryType.TUPLE);
    }

    public static <T> UaiCriteria<T> createQueryCriteria(final EntityManager entityManager, final Class<T> classToUse, final UaiCriteria<T> uaiCTO) {
        return createQueryCriteria(entityManager, classToUse, uaiCTO, QueryType.REGULAR);
    }

    public static <T> UaiCriteria<T> createTupleCriteria(final EntityManager entityManager, final Class<T> classToUse, final UaiCriteria<T> uaiCTO) {
        return createQueryCriteria(entityManager, classToUse, uaiCTO, QueryType.TUPLE);
    }

    private static <T> UaiCriteria<T> createQueryCriteria(final EntityManager entityManager, final Class<T> classToUse, final UaiCriteria<T> uaiCTO, final QueryType queryType) {
        if (uaiCTO != null) {
            if (!(uaiCTO instanceof UaiCTOImp)) {
                throw new IllegalArgumentException("Should I be receiving an object like this? " + uaiCTO);
            }

            return UaiCriteriaBuilder.createUaiCriteriaFromCTO(uaiCTO, entityManager, classToUse, queryType);
        }

        return new UaiCriteriaImp<T>(entityManager, classToUse, queryType);
    }

    public static <T> UaiCriteria<T> createQueryUaiCTO() {
        return new UaiCTOImp<T>();
    }
}