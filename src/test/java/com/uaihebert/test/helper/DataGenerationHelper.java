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

import com.uaihebert.model.test.EntityWithEmbeddedId;
import com.uaihebert.model.test.EntityWithEmbeddedIdJoinOneLevel;
import com.uaihebert.model.test.EntityWithEmbeddedIdJoinThreeLevel;
import com.uaihebert.model.test.EntityWithEmbeddedIdJoinTwoLevel;
import com.uaihebert.model.test.InheritanceFamilyOneChildEntity;
import com.uaihebert.model.test.InheritanceFamilyOneEntity;
import com.uaihebert.model.test.InheritanceFamilyTwoEntity;
import com.uaihebert.model.test.InheritanceRootEntity;
import com.uaihebert.model.test.RegularEmbeddedId;
import com.uaihebert.model.test.RegularEntityFive;
import com.uaihebert.model.test.RegularEntityFour;
import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityThree;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.model.test.RegularEnum;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public final class DataGenerationHelper {
    public static void createDataInDatabase(final EntityManagerFactory entityManagerFactory) {
        if (hasDataAlready(entityManagerFactory)) {
            return;
        }

        createRegularEntityInDatabase(entityManagerFactory.createEntityManager());
        createRelationshipBetweenEntities(entityManagerFactory.createEntityManager());

        createInheritanceEntityInDataBase(entityManagerFactory.createEntityManager());
    }

    private static void createInheritanceEntityInDataBase(final EntityManager entityManager) {
        entityManager.getTransaction().begin();

        final InheritanceRootEntity inheritanceRootEntity = new InheritanceRootEntity();
        inheritanceRootEntity.setRootString("Root String 01");
        inheritanceRootEntity.setId(1);

        final InheritanceFamilyOneEntity inheritanceFamilyOneEntity01 = new InheritanceFamilyOneEntity();
        inheritanceFamilyOneEntity01.setRootString("InheritanceFamilyOneEntity 01");
        inheritanceFamilyOneEntity01.setId(3);

        final InheritanceFamilyOneEntity inheritanceFamilyOneEntity02 = new InheritanceFamilyOneEntity();
        inheritanceFamilyOneEntity02.setRootString("InheritanceFamilyOneEntity 02");
        inheritanceFamilyOneEntity02.setId(4);

        final InheritanceFamilyTwoEntity inheritanceFamilyTwoEntity = new InheritanceFamilyTwoEntity();
        inheritanceFamilyTwoEntity.setId(5);

        final InheritanceFamilyOneChildEntity inheritanceFamilyOneChildEntity = new InheritanceFamilyOneChildEntity();
        inheritanceFamilyOneChildEntity.setId(6);
        inheritanceFamilyOneChildEntity.setRootString("InheritanceFamilyOneEntity 06");

        entityManager.persist(inheritanceRootEntity);
        entityManager.persist(inheritanceFamilyOneEntity01);
        entityManager.persist(inheritanceFamilyOneEntity02);
        entityManager.persist(inheritanceFamilyTwoEntity);
        entityManager.persist(inheritanceFamilyOneChildEntity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void createRelationshipBetweenEntities(final EntityManager entityManager) {
        entityManager.getTransaction().begin();

        final RegularEntityOne regularEntityOne01 = entityManager.find(RegularEntityOne.class, 1L);
        final RegularEntityOne regularEntityOne02 = entityManager.find(RegularEntityOne.class, 2L);
        final RegularEntityOne regularEntityOne03 = entityManager.find(RegularEntityOne.class, 3L);
        final RegularEntityOne regularEntityOne05 = entityManager.find(RegularEntityOne.class, 5L);

        final RegularEntityTwo regularEntityTwo01 = entityManager.find(RegularEntityTwo.class, 1L);
        final RegularEntityTwo regularEntityTwo02 = entityManager.find(RegularEntityTwo.class, 2L);
        final RegularEntityTwo regularEntityTwo03 = entityManager.find(RegularEntityTwo.class, 3L);
        final RegularEntityTwo regularEntityTwo04 = entityManager.find(RegularEntityTwo.class, 4L);

        final RegularEntityThree regularEntityThree01 = entityManager.find(RegularEntityThree.class, 1L);
        final RegularEntityThree regularEntityThree02 = entityManager.find(RegularEntityThree.class, 2L);
        final RegularEntityThree regularEntityThree03 = entityManager.find(RegularEntityThree.class, 3L);

        final RegularEntityFour regularEntityFour01 = entityManager.find(RegularEntityFour.class, 1L);
        final RegularEntityFour regularEntityFour02 = entityManager.find(RegularEntityFour.class, 2L);
        final RegularEntityFour regularEntityFour03 = entityManager.find(RegularEntityFour.class, 3L);

        final RegularEntityFive regularEntityFive01 = entityManager.find(RegularEntityFive.class, 1L);

        regularEntityOne01.setRegularEntityTwo(regularEntityTwo01);
        regularEntityOne01.setRegularEntityThree(regularEntityThree01);
        regularEntityOne01.setRegularEntityFour(regularEntityFour01);

        regularEntityOne02.setRegularEntityTwo(regularEntityTwo02);
        regularEntityOne02.setRegularEntityThree(regularEntityThree02);
        regularEntityOne02.setRegularEntityFour(regularEntityFour02);

        regularEntityOne03.setRegularEntityTwo(regularEntityTwo03);
        regularEntityOne03.setRegularEntityThree(regularEntityThree03);
        regularEntityOne03.setRegularEntityFour(regularEntityFour03);

        regularEntityTwo01.setRegularEntityOne(regularEntityOne01);
        regularEntityTwo01.setRegularEntityThree(regularEntityThree01);
        regularEntityTwo01.setRegularEntityFour(regularEntityFour01);

        regularEntityTwo02.setRegularEntityOne(regularEntityOne02);
        regularEntityTwo02.setRegularEntityThree(regularEntityThree02);
        regularEntityTwo02.setRegularEntityFour(regularEntityFour02);

        regularEntityTwo03.setRegularEntityOne(regularEntityOne03);
        regularEntityTwo03.setRegularEntityThree(regularEntityThree03);
        regularEntityTwo03.setRegularEntityFour(regularEntityFour03);

        regularEntityThree01.setRegularEntityOne(regularEntityOne01);
        regularEntityThree01.setRegularEntityTwo(regularEntityTwo01);
        regularEntityThree01.setRegularEntityFour(regularEntityFour01);

        regularEntityThree02.setRegularEntityOne(regularEntityOne02);
        regularEntityThree02.setRegularEntityTwo(regularEntityTwo02);
        regularEntityThree02.setRegularEntityFour(regularEntityFour02);

        regularEntityThree03.setRegularEntityOne(regularEntityOne03);
        regularEntityThree03.setRegularEntityTwo(regularEntityTwo03);
        regularEntityThree03.setRegularEntityFour(regularEntityFour03);

        regularEntityFour01.setRegularEntityOne(regularEntityOne01);
        regularEntityFour01.setRegularEntityTwo(regularEntityTwo01);
        regularEntityFour01.setRegularEntityThree(regularEntityThree01);

        regularEntityFour02.setRegularEntityOne(regularEntityOne02);
        regularEntityFour02.setRegularEntityTwo(regularEntityTwo02);
        regularEntityFour02.setRegularEntityThree(regularEntityThree02);

        regularEntityFour03.setRegularEntityOne(regularEntityOne03);
        regularEntityFour03.setRegularEntityTwo(regularEntityTwo03);
        regularEntityFour03.setRegularEntityThree(regularEntityThree03);

        regularEntityOne02.setRegularEntityTwo(regularEntityTwo02);
        regularEntityTwo02.setRegularEntityOne(regularEntityOne02);

        regularEntityOne05.setRegularEntityTwo(regularEntityTwo04);
        regularEntityTwo04.setRegularEntityOne(regularEntityOne05);

        regularEntityFive01.setRegularEntityOneList(Arrays.asList(regularEntityOne01, regularEntityOne02, regularEntityOne03));
        regularEntityFive01.setRegularEntityTwoSet(new HashSet(Arrays.asList(regularEntityTwo01, regularEntityTwo02, regularEntityTwo03)));
        regularEntityFive01.setRegularEntityThreeCollection(new HashSet(Arrays.asList(regularEntityThree01, regularEntityThree02, regularEntityThree03)));

        if (isNotBatoo(entityManager)) {
            doCompositeEntityRelationship(entityManager);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void doCompositeEntityRelationship(final EntityManager entityManager) {
        final EntityWithEmbeddedIdJoinThreeLevel entityWithEmbeddedIdJoinThreeLevel01 = entityManager.find(EntityWithEmbeddedIdJoinThreeLevel.class, 1L);
        final EntityWithEmbeddedIdJoinThreeLevel entityWithEmbeddedIdJoinThreeLevel02 = entityManager.find(EntityWithEmbeddedIdJoinThreeLevel.class, 2L);
        final EntityWithEmbeddedIdJoinThreeLevel entityWithEmbeddedIdJoinThreeLevel03 = entityManager.find(EntityWithEmbeddedIdJoinThreeLevel.class, 3L);

        final EntityWithEmbeddedIdJoinTwoLevel entityWithEmbeddedIdJoinTwoLevel01 = entityManager.find(EntityWithEmbeddedIdJoinTwoLevel.class, 1L);
        final EntityWithEmbeddedIdJoinTwoLevel entityWithEmbeddedIdJoinTwoLevel02 = entityManager.find(EntityWithEmbeddedIdJoinTwoLevel.class, 2L);
        final EntityWithEmbeddedIdJoinTwoLevel entityWithEmbeddedIdJoinTwoLevel03 = entityManager.find(EntityWithEmbeddedIdJoinTwoLevel.class, 3L);


        final EntityWithEmbeddedIdJoinOneLevel entityWithEmbeddedIdJoinOneLevel01 = entityManager.find(EntityWithEmbeddedIdJoinOneLevel.class, 1L);
        final EntityWithEmbeddedIdJoinOneLevel entityWithEmbeddedIdJoinOneLevel02 = entityManager.find(EntityWithEmbeddedIdJoinOneLevel.class, 2L);
        final EntityWithEmbeddedIdJoinOneLevel entityWithEmbeddedIdJoinOneLevel03 = entityManager.find(EntityWithEmbeddedIdJoinOneLevel.class, 3L);

        final EntityWithEmbeddedId entityWithEmbeddedId01 = entityManager.find(EntityWithEmbeddedId.class, new RegularEmbeddedId(1, "AAA"));
        final EntityWithEmbeddedId entityWithEmbeddedId02 = entityManager.find(EntityWithEmbeddedId.class, new RegularEmbeddedId(2, "BBB"));
        final EntityWithEmbeddedId entityWithEmbeddedId03 = entityManager.find(EntityWithEmbeddedId.class, new RegularEmbeddedId(3, "CCC"));

        entityWithEmbeddedIdJoinOneLevel01.setEntityWithEmbeddedId(entityWithEmbeddedId01);
        entityWithEmbeddedIdJoinOneLevel02.setEntityWithEmbeddedId(entityWithEmbeddedId02);
        entityWithEmbeddedIdJoinOneLevel03.setEntityWithEmbeddedId(entityWithEmbeddedId03);

        entityWithEmbeddedIdJoinTwoLevel01.setEntityWithEmbeddedIdJoinOneLevel(entityWithEmbeddedIdJoinOneLevel01);
        entityWithEmbeddedIdJoinTwoLevel02.setEntityWithEmbeddedIdJoinOneLevel(entityWithEmbeddedIdJoinOneLevel02);
        entityWithEmbeddedIdJoinTwoLevel03.setEntityWithEmbeddedIdJoinOneLevel(entityWithEmbeddedIdJoinOneLevel03);

        entityWithEmbeddedIdJoinThreeLevel01.setEntityWithEmbeddedIdJoinTwoLevel(entityWithEmbeddedIdJoinTwoLevel01);
        entityWithEmbeddedIdJoinThreeLevel02.setEntityWithEmbeddedIdJoinTwoLevel(entityWithEmbeddedIdJoinTwoLevel02);
        entityWithEmbeddedIdJoinThreeLevel03.setEntityWithEmbeddedIdJoinTwoLevel(entityWithEmbeddedIdJoinTwoLevel03);
    }

    private static boolean hasDataAlready(final EntityManagerFactory entityManagerFactory) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final TypedQuery<RegularEntityOne> query = entityManager.createQuery("select r from RegularEntityOne r", RegularEntityOne.class);
        return !query.getResultList().isEmpty();
    }

    private static void createRegularEntityInDatabase(final EntityManager entityManager) {
        entityManager.getTransaction().begin();

        final RegularEntityOne regularEntityOne01 = new RegularEntityOne();
        regularEntityOne01.setId(1L);
        regularEntityOne01.setBooleanAttributeOne(true);
        regularEntityOne01.setBooleanAttributeTwo(true);
        regularEntityOne01.setLongAttributeOne(33L);
        regularEntityOne01.setLongAttributeTwo(30L);
        regularEntityOne01.setDoubleAttributeOne(33d);
        regularEntityOne01.setFloatAttributeOne(33f);
        regularEntityOne01.setIntegerAttributeOne(33);
        regularEntityOne01.setBigDecimalAttributeOne(new BigDecimal((33)));
        regularEntityOne01.setBigDecimalAttributeTwo(new BigDecimal((43)));
        regularEntityOne01.setStringAttribute("Just a String 02");
        regularEntityOne01.setCalendarAttributeOne(DateUtil.getFormattedCalendar("10/10/2010"));
        regularEntityOne01.setDateAttributeOne(DateUtil.getFormattedDate("10/10/2010"));
        regularEntityOne01.setRegularEnum(RegularEnum.VALUE_01);


        final RegularEntityOne regularEntityOne02 = new RegularEntityOne();
        regularEntityOne02.setId(2L);
        regularEntityOne02.setLongAttributeOne(23L);
        regularEntityOne02.setLongAttributeTwo(30L);
        regularEntityOne02.setBooleanAttributeOne(false);
        regularEntityOne02.setBooleanAttributeTwo(true);
        regularEntityOne02.setDoubleAttributeOne(23d);
        regularEntityOne02.setFloatAttributeOne(23f);
        regularEntityOne02.setIntegerAttributeOne(23);
        regularEntityOne02.setBigDecimalAttributeOne(new BigDecimal((23)));
        regularEntityOne02.setBigDecimalAttributeTwo(new BigDecimal((10)));
        regularEntityOne02.setStringAttribute("Just a String 01");
        regularEntityOne02.setCalendarAttributeOne(DateUtil.getFormattedCalendar("09/09/2009"));
        regularEntityOne02.setDateAttributeOne(DateUtil.getFormattedDate("09/09/2009"));
        regularEntityOne02.setRegularEnum(RegularEnum.VALUE_02);

        final RegularEntityOne regularEntityOne03 = new RegularEntityOne();
        regularEntityOne03.setId(3L);
        regularEntityOne03.setLongAttributeOne(53L);
        regularEntityOne03.setLongAttributeTwo(15L);
        regularEntityOne03.setDoubleAttributeOne(53d);
        regularEntityOne03.setFloatAttributeOne(53f);
        regularEntityOne03.setIntegerAttributeOne(53);
        regularEntityOne03.setBigDecimalAttributeOne(new BigDecimal((53)));
        regularEntityOne03.setBigDecimalAttributeTwo(new BigDecimal((63)));
        regularEntityOne03.setBooleanAttributeOne(false);
        regularEntityOne03.setBooleanAttributeTwo(true);
        regularEntityOne03.setStringAttribute("Just a String 03");
        regularEntityOne03.setCalendarAttributeOne(DateUtil.getFormattedCalendar("11/11/2011"));
        regularEntityOne03.setDateAttributeOne(DateUtil.getFormattedDate("11/11/2011"));

        final RegularEntityOne regularEntityOne04 = new RegularEntityOne();
        regularEntityOne04.setId(4L);
        regularEntityOne04.setStringAttribute("Just a String 04 - without relationship");

        final RegularEntityOne regularEntityOne05 = new RegularEntityOne();
        regularEntityOne05.setId(5L);

        final RegularEntityTwo regularEntityTwo01 = new RegularEntityTwo();
        regularEntityTwo01.setId(1L);
        regularEntityTwo01.setBooleanAttributeOne(false);
        regularEntityTwo01.setBooleanAttributeTwo(true);
        regularEntityTwo01.setStringAttribute("Just a String");

        final RegularEntityTwo regularEntityTwo02 = new RegularEntityTwo();
        regularEntityTwo02.setId(2L);
        regularEntityTwo02.setBooleanAttributeOne(false);
        regularEntityTwo02.setBooleanAttributeTwo(true);
        regularEntityTwo02.setStringAttribute("Just a String 02");

        final RegularEntityTwo regularEntityTwo03 = new RegularEntityTwo();
        regularEntityTwo03.setId(3L);
        regularEntityTwo03.setBooleanAttributeOne(false);
        regularEntityTwo03.setBooleanAttributeTwo(true);
        regularEntityTwo03.setStringAttribute("Just a String 02");

        final RegularEntityTwo regularEntityTwo04 = new RegularEntityTwo();
        regularEntityTwo04.setId(4L);

        final RegularEntityTwo regularEntityTwo05 = new RegularEntityTwo();
        regularEntityTwo05.setId(5L);

        final RegularEntityThree regularEntityThree01 = new RegularEntityThree();
        regularEntityThree01.setId(1L);
        regularEntityThree01.setBooleanAttributeOne(false);
        regularEntityThree01.setBooleanAttributeTwo(true);
        regularEntityThree01.setStringAttribute("Just a String 01");

        final RegularEntityThree regularEntityThree02 = new RegularEntityThree();
        regularEntityThree02.setId(2L);
        regularEntityThree02.setBooleanAttributeOne(false);
        regularEntityThree02.setBooleanAttributeTwo(true);
        regularEntityThree02.setStringAttribute("Just a String 02");

        final RegularEntityThree regularEntityThree03 = new RegularEntityThree();
        regularEntityThree03.setId(3L);
        regularEntityThree03.setBooleanAttributeOne(false);
        regularEntityThree03.setBooleanAttributeTwo(true);
        regularEntityThree03.setStringAttribute("Just a String 04");

        final RegularEntityFour regularEntityFour01 = new RegularEntityFour();
        regularEntityFour01.setStringAttribute("Just a String 01");
        regularEntityFour01.setId(1L);
        regularEntityFour01.setBooleanAttributeOne(false);
        regularEntityFour01.setBooleanAttributeTwo(true);

        final RegularEntityFour regularEntityFour02 = new RegularEntityFour();
        regularEntityFour02.setStringAttribute("just a string 02");
        regularEntityFour02.setId(2L);
        regularEntityFour02.setBooleanAttributeOne(true);
        regularEntityFour02.setBooleanAttributeTwo(true);

        final RegularEntityFour regularEntityFour03 = new RegularEntityFour();
        regularEntityFour03.setId(3L);
        regularEntityFour03.setStringAttribute("Just a String 03");
        regularEntityFour03.setBooleanAttributeOne(true);
        regularEntityFour03.setBooleanAttributeTwo(true);

        final RegularEntityFive regularEntityFive01 = new RegularEntityFive();
        regularEntityFive01.setId(1L);

        regularEntityFive01.setStringList(Arrays.asList("VALUE_01", "VALUE_02"));

        final HashMap<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("A", "A");
        stringMap.put("B", "B");

        regularEntityFive01.setStringMap(stringMap);

        final RegularEntityFive regularEntityFive02 = new RegularEntityFive();
        regularEntityFive02.setId(2L);

        entityManager.persist(regularEntityOne01);
        entityManager.persist(regularEntityOne02);
        entityManager.persist(regularEntityOne03);
        entityManager.persist(regularEntityOne04);
        entityManager.persist(regularEntityOne05);

        entityManager.persist(regularEntityTwo01);
        entityManager.persist(regularEntityTwo02);
        entityManager.persist(regularEntityTwo03);
        entityManager.persist(regularEntityTwo04);
        entityManager.persist(regularEntityTwo05);

        entityManager.persist(regularEntityThree01);
        entityManager.persist(regularEntityThree02);
        entityManager.persist(regularEntityThree03);

        entityManager.persist(regularEntityFour01);
        entityManager.persist(regularEntityFour02);
        entityManager.persist(regularEntityFour03);

        entityManager.persist(regularEntityFive01);
        entityManager.persist(regularEntityFive02);

        if (isNotBatoo(entityManager)) {
            createCompositeEntities(entityManager);
        }

        //without the flush the open jpa will crash when running the run all suit
        entityManager.flush();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void createCompositeEntities(final EntityManager entityManager) {
        final EntityWithEmbeddedIdJoinOneLevel entityWithEmbeddedIdJoinOneLevel01 = new EntityWithEmbeddedIdJoinOneLevel();
        entityWithEmbeddedIdJoinOneLevel01.setId(1);

        final EntityWithEmbeddedIdJoinOneLevel entityWithEmbeddedIdJoinOneLevel02 = new EntityWithEmbeddedIdJoinOneLevel();
        entityWithEmbeddedIdJoinOneLevel02.setId(2);

        final EntityWithEmbeddedIdJoinOneLevel entityWithEmbeddedIdJoinOneLevel03 = new EntityWithEmbeddedIdJoinOneLevel();
        entityWithEmbeddedIdJoinOneLevel03.setId(3);

        final EntityWithEmbeddedId entityWithEmbeddedId01 = new EntityWithEmbeddedId();
        entityWithEmbeddedId01.setEmbeddedId(new RegularEmbeddedId(1, "AAA"));
        entityWithEmbeddedId01.setAnyString("Any String 01");

        final EntityWithEmbeddedId entityWithEmbeddedId02 = new EntityWithEmbeddedId();
        entityWithEmbeddedId02.setEmbeddedId(new RegularEmbeddedId(2, "BBB"));
        entityWithEmbeddedId02.setAnyString("Any String 02");

        final EntityWithEmbeddedId entityWithEmbeddedId03 = new EntityWithEmbeddedId();
        entityWithEmbeddedId03.setEmbeddedId(new RegularEmbeddedId(3, "CCC"));
        entityWithEmbeddedId03.setAnyString("Any String 03");

        final EntityWithEmbeddedIdJoinTwoLevel entityWithEmbeddedIdJoinTwoLevel01 = new EntityWithEmbeddedIdJoinTwoLevel();
        entityWithEmbeddedIdJoinTwoLevel01.setId(1L);

        final EntityWithEmbeddedIdJoinTwoLevel entityWithEmbeddedIdJoinTwoLevel02 = new EntityWithEmbeddedIdJoinTwoLevel();
        entityWithEmbeddedIdJoinTwoLevel02.setId(2L);

        final EntityWithEmbeddedIdJoinTwoLevel entityWithEmbeddedIdJoinTwoLevel03 = new EntityWithEmbeddedIdJoinTwoLevel();
        entityWithEmbeddedIdJoinTwoLevel03.setId(3L);

        final EntityWithEmbeddedIdJoinThreeLevel entityWithEmbeddedIdJoinThreeLevel01 = new EntityWithEmbeddedIdJoinThreeLevel();
        entityWithEmbeddedIdJoinThreeLevel01.setId(1L);

        final EntityWithEmbeddedIdJoinThreeLevel entityWithEmbeddedIdJoinThreeLevel02 = new EntityWithEmbeddedIdJoinThreeLevel();
        entityWithEmbeddedIdJoinThreeLevel02.setId(2L);

        final EntityWithEmbeddedIdJoinThreeLevel entityWithEmbeddedIdJoinThreeLevel03 = new EntityWithEmbeddedIdJoinThreeLevel();
        entityWithEmbeddedIdJoinThreeLevel03.setId(3L);

        entityManager.persist(entityWithEmbeddedIdJoinOneLevel01);
        entityManager.persist(entityWithEmbeddedIdJoinOneLevel02);
        entityManager.persist(entityWithEmbeddedIdJoinOneLevel03);

        entityManager.persist(entityWithEmbeddedId01);
        entityManager.persist(entityWithEmbeddedId02);
        entityManager.persist(entityWithEmbeddedId03);

        entityManager.persist(entityWithEmbeddedIdJoinTwoLevel01);
        entityManager.persist(entityWithEmbeddedIdJoinTwoLevel02);
        entityManager.persist(entityWithEmbeddedIdJoinTwoLevel03);

        entityManager.persist(entityWithEmbeddedIdJoinThreeLevel01);
        entityManager.persist(entityWithEmbeddedIdJoinThreeLevel02);
        entityManager.persist(entityWithEmbeddedIdJoinThreeLevel03);
    }

    private static boolean isNotBatoo(final EntityManager entityManager) {
        return !entityManager.toString().contains("batoo");
    }
}
