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
package com.uaihebert.test.easy_legacy.cto;

import com.uaihebert.model.EasyCTOImp;
import com.uaihebert.model.EasyCriteria;
import com.uaihebert.model.test.Person;
import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import org.junit.Test;

public class MethodsCTOExceptionTest extends AbstractTest {

    @Test//(expected = IllegalArgumentException.class)
    public void isExceptionWithNullJoinTypeNull() {
        final EasyCriteria easyCriteria = EasyCriteriaFactory.createEasyCTO();
        final EasyCTOImp easyCTO = (EasyCTOImp) easyCriteria;
//        easyCTO.getConfigurations().getEasyAttributes().add(EasyAttribute.newInstance(null, null, null, null, false));

        final EasyCriteria<Person> queryCriteria = EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, easyCTO);
        queryCriteria.getResultList();
    }
}
