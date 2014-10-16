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

import com.uaihebert.model.test.Manufacturer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Main extends AbstractTest {

    public static void main(final String[] args) throws Exception {
        createEntityManagerFactoryForOpenJPA();
        CodeGenerator.generateData(getEntityManagerFactory());

        final EntityManager em = getEntityManagerFactory().createEntityManager();

        final EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        final Root<Manufacturer> root = criteriaQuery.from(Manufacturer.class);
        criteriaQuery.select(criteriaBuilder.count(root));


        final TypedQuery<Long> countQuery = entityManager.createQuery(criteriaQuery);
        System.out.println(countQuery.getSingleResult());

        em.close();
        getEntityManagerFactory().close();
    }
}