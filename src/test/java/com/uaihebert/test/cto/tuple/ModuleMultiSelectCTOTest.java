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
package com.uaihebert.test.cto.multiSelect;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.test.MultiSelectAbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import org.junit.Test;

public class ModuleMultiSelectCTOTest extends MultiSelectAbstractTest {

    @Test
    public void isMethodInvokedWithTwoParameters() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.addMultiSelectAttribute("id").module("id", "integerAttributeOne");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addMultiSelectAttribute("id").module("id", "integerAttributeOne");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createMultiSelectCriteria(RegularEntityOne.class, cto);

        validateListResult(uaiCriteria.getMultiSelectResult(), uaiCriteriaCTO.getMultiSelectResult());
    }

    @Test
    public void isMultiSelectWorkingWithSeveralAttributesAndFunction() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo")
                .module("id", "integerAttributeOne");


        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo")
                .module("id", "integerAttributeOne");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createMultiSelectCriteria(RegularEntityOne.class, cto);

        validateListResult(uaiCriteria.getMultiSelectResult(), uaiCriteriaCTO.getMultiSelectResult());
    }

    @Test
    public void ismoduleNumberFromAttribute() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo")
                .module("id", 10);


        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo")
                .module("id", 10);

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createMultiSelectCriteria(RegularEntityOne.class, cto);

        validateListResult(uaiCriteria.getMultiSelectResult(), uaiCriteriaCTO.getMultiSelectResult());
    }

    @Test
    public void ismoduleAttributeFromNumber() {
        if (isBatoo()) {
            return;
        }

        final UaiCriteria<RegularEntityOne> uaiCriteria = createMultiSelectCriteria(RegularEntityOne.class);
        uaiCriteria.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo")
                .module(10, "id");

        final UaiCriteria<RegularEntityOne> cto = UaiCriteriaFactory.createQueryUaiCTO();
        cto.addMultiSelectAttribute("id", "stringAttribute", "floatAttributeOne", "dateAttributeTwo")
                .module(10, "id");

        final UaiCriteria<RegularEntityOne> uaiCriteriaCTO = createMultiSelectCriteria(RegularEntityOne.class, cto);

        validateListResult(uaiCriteria.getMultiSelectResult(), uaiCriteriaCTO.getMultiSelectResult());
    }
}