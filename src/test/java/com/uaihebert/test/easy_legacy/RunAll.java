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
package com.uaihebert.test.easy_legacy;

import com.uaihebert.test.easy_legacy.suits.ImplementationCTOEclipseLinkTest;
import com.uaihebert.test.easy_legacy.suits.ImplementationCTOHibernateTest;
import com.uaihebert.test.easy_legacy.suits.ImplementationCTOOpenJPATest;
import com.uaihebert.test.easy_legacy.suits.ImplementationEclipseLinkTest;
import com.uaihebert.test.easy_legacy.suits.ImplementationHibernateTest;
import com.uaihebert.test.easy_legacy.suits.ImplementationOpenJPATest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ImplementationOpenJPATest.class,
        ImplementationEclipseLinkTest.class,
        ImplementationHibernateTest.class,
        ImplementationCTOEclipseLinkTest.class,
        ImplementationCTOHibernateTest.class,
        ImplementationCTOOpenJPATest.class
})
public class RunAll {

}
