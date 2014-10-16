/*
 * Copyright 2012 uaiHebert Solucoes em Informatica
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
package com.uaihebert.test.easy_legacy;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityTwo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

public class Main2 extends com.uaihebert.test.AbstractTest {

    public static void main(final String[] args) throws Exception {
        beforeClass();

        final EntityManager em = entityManagerFactory.createEntityManager();

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final String JPQL = "select r from RegularEntityTwo r join r.regularEntityOne one where one.id in (select sub.id from RegularEntityOne sub where sub.stringAttribute = 'Just a String 02')";
        final TypedQuery<RegularEntityTwo> jpqlQuery = entityManager.createQuery(JPQL, RegularEntityTwo.class);

        System.out.println("-----------> " + jpqlQuery.getResultList().size());

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<RegularEntityTwo> criteriaQuery = criteriaBuilder.createQuery(RegularEntityTwo.class);
        final Root<RegularEntityTwo> root = criteriaQuery.from(RegularEntityTwo.class);
        criteriaQuery.select(root);

        final Subquery subquery = criteriaQuery.subquery(Long.class);
        final Root<RegularEntityOne> subFrom = subquery.from(RegularEntityOne.class);

        final Path stringPath = subFrom.get("stringAttribute");
        final Predicate compareByString = criteriaBuilder.equal(stringPath, "Just a String 02");

        final Path subFromPath = subFrom.get("id");
        subquery.select(subFromPath).where(compareByString);

        final Path rootId = root.get("id");
        final CriteriaBuilder.In inCondition = criteriaBuilder.in(rootId);
        criteriaQuery.where(inCondition.value(subquery));

        final TypedQuery<RegularEntityTwo> query = entityManager.createQuery(criteriaQuery);

        System.out.println("------------>" + query.getResultList());

        System.out.println();

        em.close();
        afterClass();
    }
}