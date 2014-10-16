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

import com.uaihebert.model.EasyCriteria;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class AbstractTest {

    private static final String PU_OPENJPA = "QueryTesterOpenJPA";
    private static final String PU_HIBERNATE = "QueryTesterHibernate";
    private static final String PU_ECLIPSELINK = "QueryTesterEclipseLink";
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    protected static void createEntityManagerFactoryForHibernate() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PU_HIBERNATE);
    }

    protected static void createEntityManagerFactoryForEclipseLink() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PU_ECLIPSELINK);
    }

    protected static void createEntityManagerFactoryForOpenJPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PU_OPENJPA);
    }

    @BeforeClass
    public static void createPersistenceUnit() {
        if (getEntityManagerFactory() == null) {
//			createEntityManagerFactoryForHibernate();
//            createEntityManagerFactoryForEclipseLink();
            createEntityManagerFactoryForOpenJPA();
        }

        generateData();
    }

    protected static void generateData() {
        if (!getEntityManagerFactory().isOpen()) {
            createEntityManagerFactoryForHibernate();
        }

        CodeGenerator.generateData(getEntityManagerFactory());
    }

    static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    protected static void finishFactory() {
        if (getEntityManagerFactory() != null && getEntityManagerFactory().isOpen()) {
            getEntityManagerFactory().close();
        }

        entityManagerFactory = null;
    }

    @Before
    public void beforeTest() {
        if (entityManager == null) {
            entityManager = getEntityManagerFactory().createEntityManager();
        }
    }

    @After
    public void finishTest() {
        entityManager.close();
        entityManager = null;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected boolean isEclipseLink() {
        return getEntityManager().toString().contains("eclipse");
    }

    protected boolean isOpenJPA() {
        return getEntityManager().toString().contains("openjpa");
    }

    protected <T> EasyCriteria<T> createCriteria(final Class<T> classToUse) {
        final EntityManager em = getEntityManager();

        return EasyCriteriaFactory.createQueryCriteria(em, classToUse);
    }

    protected <T> List<T> getListFromJPQL(final String query, final Class<T> classToUse) {
        return getListFromJPQL(query, classToUse, null);
    }

    protected <T> List<T> getListFromJPQL(final String query, final Class<T> classToUse,
                                          final Map<String, Object> parameters) {
        final EntityManager em = getEntityManager();

        final TypedQuery<T> typedQuery = em.createQuery(query, classToUse);

        if (parameters != null) {
            populateQueryParameters(typedQuery, parameters);
        }

        return typedQuery.getResultList();
    }

    <T> void populateQueryParameters(final TypedQuery<T> query,
                                     final Map<String, Object> parameters) {
        for (final Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    protected Date getFormattedDate(final String date) {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return formatter.parse(date);
        } catch (final ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}