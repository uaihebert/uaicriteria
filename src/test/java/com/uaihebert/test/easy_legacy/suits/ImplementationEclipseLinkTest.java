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
package com.uaihebert.test.easy_legacy.suits;

import com.uaihebert.test.easy_legacy.AbstractTest;
import com.uaihebert.test.easy_legacy.ReflectionUtilTest;
import com.uaihebert.test.easy_legacy.easy.BasicTest;
import com.uaihebert.test.easy_legacy.easy.CommonTestsBigDecimalImp;
import com.uaihebert.test.easy_legacy.easy.CommonTestsCalendarImp;
import com.uaihebert.test.easy_legacy.easy.CommonTestsDateImp;
import com.uaihebert.test.easy_legacy.easy.CommonTestsDoubleImp;
import com.uaihebert.test.easy_legacy.easy.CommonTestsFloatImp;
import com.uaihebert.test.easy_legacy.easy.CommonTestsIntegerImp;
import com.uaihebert.test.easy_legacy.easy.CommonTestsLongImp;
import com.uaihebert.test.easy_legacy.easy.CommonTestsStringImp;
import com.uaihebert.test.easy_legacy.easy.MethodsCaseTest;
import com.uaihebert.test.easy_legacy.easy.MethodsCompositeKeyTest;
import com.uaihebert.test.easy_legacy.easy.MethodsCountTest;
import com.uaihebert.test.easy_legacy.easy.MethodsExceptionTest;
import com.uaihebert.test.easy_legacy.easy.MethodsJoinTest;
import com.uaihebert.test.easy_legacy.easy.MethodsNullEmptyTest;
import com.uaihebert.test.easy_legacy.easy.MethodsOrTest;
import com.uaihebert.test.easy_legacy.easy.MethodsOrderByTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BasicTest.class,
        CommonTestsBigDecimalImp.class,
        CommonTestsCalendarImp.class,
        CommonTestsDateImp.class,
        CommonTestsDoubleImp.class,
        CommonTestsFloatImp.class,
        CommonTestsIntegerImp.class,
        CommonTestsLongImp.class,
        CommonTestsStringImp.class,
        MethodsCaseTest.class,
        MethodsCompositeKeyTest.class,
        MethodsCountTest.class,
        MethodsExceptionTest.class,
        MethodsJoinTest.class,
        MethodsNullEmptyTest.class,
        MethodsOrderByTest.class,
        MethodsOrTest.class,
        ReflectionUtilTest.class
})
public class ImplementationEclipseLinkTest extends AbstractTest {

    @BeforeClass
    public static void createPersistenceUnit() {
        createEntityManagerFactoryForEclipseLink();
        generateData();
    }

    @AfterClass
    public static void closePersistenceUnit() {
        finishFactory();
    }
}