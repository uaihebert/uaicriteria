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
package com.uaihebert.test.helper;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public class JpqlHelper {
    private final EntityManager entityManager;

    public JpqlHelper(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T> List<T> getListFromJPQL(final String query, final Class<T> classToUse) {
        return getListFromJPQL(query, classToUse, null);
    }

    public <T> T getSingleResult(final String query, final Class<T> classToUse) {
        final TypedQuery<T> typedQuery = entityManager.createQuery(query, classToUse);
        return typedQuery.getSingleResult();
    }

    public <T> List<T> getListFromJPQL(final String query, final Class<T> classToUse, final Map<String, Object> parameters) {
        final TypedQuery<T> typedQuery = entityManager.createQuery(query, classToUse);

        populateQueryParameters(typedQuery, parameters);

        return typedQuery.getResultList();
    }

    private <T> void populateQueryParameters(final TypedQuery<T> query, final Map<String, Object> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return;
        }

        for (final Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
}
