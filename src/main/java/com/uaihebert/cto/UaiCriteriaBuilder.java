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
package com.uaihebert.cto;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaImp;
import com.uaihebert.uaicriteria.criteria.QueryType;

import javax.persistence.EntityManager;
import java.util.Arrays;

public final class UaiCriteriaBuilder {

    private UaiCriteriaBuilder() {
    }

    public static <T> UaiCriteria<T> createUaiCriteriaFromCTO(final UaiCriteria<T> uaiCTO, final EntityManager entityManager, final Class<T> entityClass, final QueryType queryType) {
        final UaiCriteriaImp<T> uaiCriteria = new UaiCriteriaImp<T>(entityManager, entityClass, queryType);

        final UaiCTOImp<T> uaiCTOImp = (UaiCTOImp) uaiCTO;

        setPagination(uaiCTOImp, uaiCriteria);

        configureJoins(uaiCTOImp, uaiCriteria);

        addTupleAttributes(uaiCTOImp, uaiCriteria);

        addCriteriaConditions(uaiCTOImp, uaiCriteria);

        configureGroupBy(uaiCTOImp, uaiCriteria);

        orderCriteria(uaiCTOImp, uaiCriteria);

        if (uaiCTOImp.distinct) {
            uaiCriteria.setDistinctTrue();
        }

        return uaiCriteria;
    }

    private static <T> void addTupleAttributes(final UaiCTOImp<T> uaiCTOImp, final UaiCriteriaImp<T> uaiCriteria) {
        uaiCriteria.addTupleSelectAttribute(Arrays.copyOf(uaiCTOImp.tupleAttributeList.toArray(), uaiCTOImp.tupleAttributeList.toArray().length, String[].class));
    }

    private static <T> void configureGroupBy(final UaiCTOImp<T> uaiCTOImp, final UaiCriteriaImp<T> uaiCriteria) {
        uaiCriteria.groupBy(Arrays.copyOf(uaiCTOImp.groupByList.toArray(), uaiCTOImp.groupByList.toArray().length, String[].class));
    }

    private static <T> void setPagination(final UaiCTOImp<T> uaiCTOImp, final UaiCriteriaImp<T> uaiCriteria) {
        if (uaiCTOImp.getFirstResult() != null) {
            uaiCriteria.setFirstResult(uaiCTOImp.getFirstResult());
            uaiCTOImp.setFirstResult(null);
        }

        if (uaiCTOImp.getMaxResults() != null) {
            uaiCriteria.setMaxResults(uaiCTOImp.getMaxResults());
            uaiCTOImp.setMaxResults(null);
        }
    }

    private static <T> void orderCriteria(final UaiCTOImp<T> uaiCTOImp, final UaiCriteriaImp<T> uaiCriteria) {
        for (final OrderByHolder holder : uaiCTOImp.orderByList) {
            if (holder.isDesc) {
                uaiCriteria.orderByDesc(holder.attributeName);
                continue;
            }

            uaiCriteria.orderByAsc(holder.attributeName);
        }
    }

    private static <T> void configureJoins(final UaiCTOImp<T> uaiCTOImp, final UaiCriteriaImp<T> uaiCriteria) {
        for (final JoinHolder holder : uaiCTOImp.joinList) {
            holder.joinHolderType.createJoin(uaiCriteria, holder);
        }
    }

    private static <T> void addCriteriaConditions(final UaiCTOImp<T> uaiCTOImp, final UaiCriteriaImp<T> uaiCriteria) {
        for (final CriteriaConditionHolder holder : uaiCTOImp.criteriaConditionTypeList) {
            holder.criteriaConditionType.createCondition(holder, uaiCriteria);
        }
    }
}