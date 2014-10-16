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
package com.uaihebert.test;

import com.uaihebert.test.helper.DataGenerationHelper;
import com.uaihebert.test.helper.DateUtil;
import com.uaihebert.test.helper.EntityManagerFactoryHelper;
import com.uaihebert.test.helper.JpqlHelper;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractTest {
    protected static String CURRENT_PERSISTENCE_UNIT;
    protected static EntityManagerFactory entityManagerFactory;
    protected JpqlHelper jpqlHelper;
    private EntityManager entityManager;

    @BeforeClass
    public static void beforeClass() {
        entityManagerFactory = EntityManagerFactoryHelper.createEntityManagerFactory(CURRENT_PERSISTENCE_UNIT);

        DataGenerationHelper.createDataInDatabase(entityManagerFactory);
    }

    @AfterClass
    public static void afterClass() {
        if (entityManagerFactory == null) {
            return;
        }

        entityManagerFactory.close();

        entityManagerFactory = null;
    }

    @Before
    public void before() {
        entityManager = entityManagerFactory.createEntityManager();
        jpqlHelper = new JpqlHelper(entityManager);
    }

    @After
    public void after() {
        entityManager.close();
    }

    protected <T> UaiCriteria<T> createCriteria(final Class<T> classToUse) {
        return UaiCriteriaFactory.createQueryCriteria(entityManager, classToUse);
    }

    protected <T> UaiCriteria<T> createTupleCriteria(final Class<T> classToUse) {
        return UaiCriteriaFactory.createTupleCriteria(entityManager, classToUse);
    }

    protected <T> UaiCriteria<T> createCriteria(final Class<T> classToUse, final UaiCriteria<T> uaiCTO) {
        return UaiCriteriaFactory.createQueryCriteria(entityManager, classToUse, uaiCTO);
    }

    protected <T> UaiCriteria<T> createTupleCriteria(final Class<T> classToUse, final UaiCriteria<T> uaiCTO) {
        return UaiCriteriaFactory.createTupleCriteria(entityManager, classToUse, uaiCTO);
    }

    protected Date getFormattedDate(final String date) {
        return DateUtil.getFormattedDate(date);
    }

    protected Calendar getFormattedCalendar(final String date) {
        return DateUtil.getFormattedCalendar(date);
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected boolean isOpenJPA() {
        return entityManager.toString().contains("openjpa");
    }

    protected boolean isBatoo() {
        return entityManager.toString().contains("batoo");
    }

    protected boolean isHibernate() {
        return entityManager.toString().contains("hibernate");
    }

    protected boolean isEclipselink() {
        return entityManager.toString().contains("eclipse.persistence");
    }

    protected <T> void validateTestLists(final List<T> listOne, final List<T> listTwo) {
        assertFalse("assert that list[One] is not empty", listOne.isEmpty());
        assertFalse("assert that list[Two] is not empty", listTwo.isEmpty());

        assertSame("assert that both result lists has the same size -->", listOne.size(), listTwo.size());

        for (int i = 0; i < listOne.size(); i++) {
            assertEquals(listOne.get(i), listTwo.get(i));
        }
    }
}