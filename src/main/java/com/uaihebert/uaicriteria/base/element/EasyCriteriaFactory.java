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
package com.uaihebert.uaicriteria.base.element;

import com.uaihebert.cto.UaiCTOImp;
import com.uaihebert.model.EasyCTOImp;
import com.uaihebert.model.EasyCriteria;
import com.uaihebert.model.EasyCriteriaImp;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import javax.persistence.EntityManager;

/**
 * @author uaiHebert.com
 *         <p/>
 *         Class that will create an instance of the EasyCriteria
 */
public final class EasyCriteriaFactory {

    private EasyCriteriaFactory() {
    }

    /**
     * Creates a new instance of the EasyCriteria that
     * will generate a TypedQuery.
     *
     * @param entityManager Entity Manager implementation
     * @param entityClass   The class to be queried
     * @return a EasyCriteria instance.
     */
    public static <T> EasyCriteria<T> createQueryCriteria(final EntityManager entityManager, final Class<T> entityClass) {
        final UaiCriteria<T> uaiCriteria = UaiCriteriaFactory.createQueryCriteria(entityManager, entityClass);

        return new EasyCriteriaImp<T>(uaiCriteria);
    }

    /**
     * Method that will create an instance of EasyCriteria, but using the parameters
     * of a view Criteria Transfer Object.
     * <p/>
     * With the returned instance it is possible to fire the query.
     *
     * @param entityManager Entity Manager implementation
     * @param entityClass   The class to be queried
     * @return a EasyCriteria instance.
     */
    public static <T> EasyCriteria<T> createQueryCriteria(final EntityManager entityManager, final Class<T> entityClass, final EasyCriteria easyCTO) {
        if (!(easyCTO instanceof EasyCTOImp)) {
            throw new IllegalArgumentException("Should I be receiving an object like this? " + easyCTO);
        }

        final EasyCTOImp<T> easyCTOImp = (EasyCTOImp) easyCTO;

        final UaiCTOImp<T> uaiCriteriaImp = new UaiCTOImp(easyCTOImp.isDistinct(), easyCTOImp.getOrderByList(), easyCTOImp.getCriteriaConditionTypeList(), easyCTOImp.getJoinList());
        uaiCriteriaImp.setFirstResult(easyCTOImp.getFirstResult());
        uaiCriteriaImp.setMaxResults(easyCTOImp.getMaxResults());

        final UaiCriteria<T> uaiCriteria = UaiCriteriaFactory.createQueryCriteria(entityManager, entityClass, uaiCriteriaImp);

        return new EasyCriteriaImp<T>(uaiCriteria);
    }

    public static <T> EasyCriteria<T> createEasyCTO() {
        return new EasyCTOImp<T>();
    }
}