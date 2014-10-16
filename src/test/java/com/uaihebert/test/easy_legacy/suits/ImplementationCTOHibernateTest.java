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

import com.uaihebert.test.easy_legacy.ReflectionUtilTest;
import com.uaihebert.test.easy_legacy.cto.BasicCTOTest;
import com.uaihebert.test.easy_legacy.cto.CommonCTOTestsBigDecimalImp;
import com.uaihebert.test.easy_legacy.cto.CommonCTOTestsCalendarImp;
import com.uaihebert.test.easy_legacy.cto.CommonCTOTestsDateImp;
import com.uaihebert.test.easy_legacy.cto.CommonCTOTestsDoubleImp;
import com.uaihebert.test.easy_legacy.cto.CommonCTOTestsFloatImp;
import com.uaihebert.test.easy_legacy.cto.CommonCTOTestsIntegerImp;
import com.uaihebert.test.easy_legacy.cto.CommonCTOTestsLongImp;
import com.uaihebert.test.easy_legacy.cto.CommonCTOTestsStringImp;
import com.uaihebert.test.easy_legacy.cto.MethodsCTOCaseTest;
import com.uaihebert.test.easy_legacy.cto.MethodsCTOCompositeKeyTest;
import com.uaihebert.test.easy_legacy.cto.MethodsCTOCountTest;
import com.uaihebert.test.easy_legacy.cto.MethodsCTOExceptionTest;
import com.uaihebert.test.easy_legacy.cto.MethodsCTOJoinTest;
import com.uaihebert.test.easy_legacy.cto.MethodsCTOOrTest;
import com.uaihebert.test.easy_legacy.cto.MethodsCTOOrderByTest;
import com.uaihebert.test.easy_legacy.cto.MethodsNullEmptyTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BasicCTOTest.class,
        CommonCTOTestsBigDecimalImp.class,
        CommonCTOTestsCalendarImp.class,
        CommonCTOTestsDateImp.class,
        CommonCTOTestsDoubleImp.class,
        CommonCTOTestsFloatImp.class,
        CommonCTOTestsIntegerImp.class,
        CommonCTOTestsLongImp.class,
        CommonCTOTestsStringImp.class,
        MethodsCTOCaseTest.class,
        MethodsCTOCompositeKeyTest.class,
        MethodsCTOCountTest.class,
        MethodsCTOExceptionTest.class,
        MethodsCTOJoinTest.class,
        MethodsCTOOrderByTest.class,
        MethodsCTOOrTest.class,
        MethodsNullEmptyTest.class,
        ReflectionUtilTest.class
})
public class ImplementationCTOHibernateTest extends BasicCTOTest {

    @BeforeClass
    public static void createPersistenceUnit() {
        createEntityManagerFactoryForHibernate();
        generateData();
    }

    @AfterClass
    public static void closePersistenceUnit() {
        finishFactory();
    }
}