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
package com.uaihebert.test.suits;

import com.uaihebert.test.AbstractTest;
import com.uaihebert.test.cto.BasicCTOTest;
import com.uaihebert.test.cto.CommonTestsBigDecimalTestCTOImp;
import com.uaihebert.test.cto.CommonTestsCalendarTestCTOImp;
import com.uaihebert.test.cto.CommonTestsDateTestCTOImp;
import com.uaihebert.test.cto.CommonTestsDoubleTestCTOImp;
import com.uaihebert.test.cto.CommonTestsFloatTestCTOImp;
import com.uaihebert.test.cto.CommonTestsIntegerTestCTOImp;
import com.uaihebert.test.cto.CommonTestsLongTestCTOImp;
import com.uaihebert.test.cto.CommonTestsStringTestCTOImp;
import com.uaihebert.test.cto.CountCTOTest;
import com.uaihebert.test.cto.CriteriaConditionHolderTest;
import com.uaihebert.test.cto.DistinctCTOTest;
import com.uaihebert.test.cto.EnumCTOTest;
import com.uaihebert.test.cto.MemberOfCTOTest;
import com.uaihebert.test.cto.MethodsCTOExceptionTest;
import com.uaihebert.test.cto.MethodsCaseCTOTest;
import com.uaihebert.test.cto.MethodsCompositeKeyCTOTest;
import com.uaihebert.test.cto.MethodsEmptyCTOTest;
import com.uaihebert.test.cto.MethodsJoinInnerCTOTest;
import com.uaihebert.test.cto.MethodsJoinInnerFetchCTOTest;
import com.uaihebert.test.cto.MethodsJoinLeftCTOTest;
import com.uaihebert.test.cto.MethodsJoinLeftFetchCTOTest;
import com.uaihebert.test.cto.MethodsNotEmptyCTOTest;
import com.uaihebert.test.cto.MethodsNotNullCTOTest;
import com.uaihebert.test.cto.MethodsNullCTOTest;
import com.uaihebert.test.cto.MethodsOrCTOTest;
import com.uaihebert.test.cto.multiselect.AverageMultiSelectCTOTest;
import com.uaihebert.test.cto.multiselect.BasicMultiSelectCTOTest;
import com.uaihebert.test.cto.multiselect.CountMultiSelectCTOTest;
import com.uaihebert.test.cto.multiselect.DiffMultiSelectCTOTest;
import com.uaihebert.test.cto.multiselect.DivideMultiSelectCTOTest;
import com.uaihebert.test.cto.multiselect.ModuleMultiSelectCTOTest;
import com.uaihebert.test.cto.multiselect.MultiplyMultiSelectCTOTest;
import com.uaihebert.test.cto.multiselect.SquareMultiSelectCTOTest;
import com.uaihebert.test.cto.multiselect.SumMultiSelectCTOTest;
import com.uaihebert.test.cto.subquery.SubQueryCTOTest;
import com.uaihebert.test.uaicriteria.BasicTest;
import com.uaihebert.test.uaicriteria.CommonTestsBigDecimalTestImp;
import com.uaihebert.test.uaicriteria.CommonTestsCalendarTestImp;
import com.uaihebert.test.uaicriteria.CommonTestsDateTestImp;
import com.uaihebert.test.uaicriteria.CommonTestsDoubleTestImp;
import com.uaihebert.test.uaicriteria.CommonTestsFloatTestImp;
import com.uaihebert.test.uaicriteria.CommonTestsIntegerTestImp;
import com.uaihebert.test.uaicriteria.CommonTestsLongTestImp;
import com.uaihebert.test.uaicriteria.CommonTestsStringTestImp;
import com.uaihebert.test.uaicriteria.CountTest;
import com.uaihebert.test.uaicriteria.DistinctTest;
import com.uaihebert.test.uaicriteria.EnumTest;
import com.uaihebert.test.uaicriteria.MemberOfTest;
import com.uaihebert.test.uaicriteria.MethodsCaseTest;
import com.uaihebert.test.uaicriteria.MethodsCompositeKeyTest;
import com.uaihebert.test.uaicriteria.MethodsEmptyTest;
import com.uaihebert.test.uaicriteria.MethodsJoinInnerFetchTest;
import com.uaihebert.test.uaicriteria.MethodsJoinInnerTest;
import com.uaihebert.test.uaicriteria.MethodsJoinLeftFetchTest;
import com.uaihebert.test.uaicriteria.MethodsJoinLeftTest;
import com.uaihebert.test.uaicriteria.MethodsNotEmptyTest;
import com.uaihebert.test.uaicriteria.MethodsNotNullTest;
import com.uaihebert.test.uaicriteria.MethodsNullTest;
import com.uaihebert.test.uaicriteria.MethodsOrTest;
import com.uaihebert.test.uaicriteria.PrivateConstructorTest;
import com.uaihebert.test.uaicriteria.RegularQueryPathCreatorTest;
import com.uaihebert.test.uaicriteria.multiselect.AverageMultiSelectTest;
import com.uaihebert.test.uaicriteria.multiselect.BasicMultiSelectTest;
import com.uaihebert.test.uaicriteria.multiselect.CountMultiSelectTest;
import com.uaihebert.test.uaicriteria.multiselect.DiffMultiSelectTest;
import com.uaihebert.test.uaicriteria.multiselect.DivideMultiSelectTest;
import com.uaihebert.test.uaicriteria.multiselect.ModuleMultiSelectTest;
import com.uaihebert.test.uaicriteria.multiselect.MultiplyMultiSelectTest;
import com.uaihebert.test.uaicriteria.multiselect.SquareMultiSelectTest;
import com.uaihebert.test.uaicriteria.multiselect.SumMultiSelectTest;
import com.uaihebert.test.uaicriteria.subquery.BasicSubQueryTest;
import com.uaihebert.test.uaicriteria.subquery.BetweenSubQueryTest;
import com.uaihebert.test.uaicriteria.subquery.ComparisonSubQueryTest;
import com.uaihebert.test.uaicriteria.subquery.DistinctSubQueryTest;
import com.uaihebert.test.uaicriteria.subquery.ExceptionSubQueryTest;
import com.uaihebert.test.uaicriteria.subquery.JoinSubQueryTest;
import com.uaihebert.test.uaicriteria.subquery.MemberOfSubQueryTest;
import com.uaihebert.test.uaicriteria.subquery.MultiSelectNotAvailableSubQueryTest;
import com.uaihebert.test.uaicriteria.subquery.NullEmptySubQueryTest;
import com.uaihebert.test.uaicriteria.subquery.OrSubQueryTest;
import com.uaihebert.test.uaicriteria.subquery.StringSubQueryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BasicTest.class,
        PrivateConstructorTest.class,
        CommonTestsBigDecimalTestImp.class,
        CommonTestsCalendarTestImp.class,
        CommonTestsDateTestImp.class,
        CommonTestsDoubleTestImp.class,
        CommonTestsFloatTestImp.class,
        CommonTestsIntegerTestImp.class,
        CommonTestsLongTestImp.class,
        CommonTestsStringTestImp.class,
        CountTest.class,
        MethodsCaseTest.class,
        MethodsEmptyTest.class,
        MethodsJoinInnerFetchTest.class,
        MethodsJoinInnerTest.class,
        MethodsJoinLeftFetchTest.class,
        MethodsJoinLeftTest.class,
        MethodsNotEmptyTest.class,
        MethodsNotNullTest.class,
        MethodsNullTest.class,
        MethodsOrTest.class,
        RegularQueryPathCreatorTest.class,
        DistinctTest.class,
        BasicCTOTest.class,
        CommonTestsBigDecimalTestCTOImp.class,
        CommonTestsCalendarTestCTOImp.class,
        CommonTestsDateTestCTOImp.class,
        CommonTestsDoubleTestCTOImp.class,
        CommonTestsFloatTestCTOImp.class,
        CommonTestsIntegerTestCTOImp.class,
        CommonTestsLongTestCTOImp.class,
        CommonTestsStringTestCTOImp.class,
        CountCTOTest.class,
        DistinctCTOTest.class,
        MethodsCaseCTOTest.class,
        MethodsEmptyCTOTest.class,
        MethodsJoinInnerCTOTest.class,
        MethodsJoinInnerFetchCTOTest.class,
        MethodsJoinLeftCTOTest.class,
        MethodsJoinLeftFetchCTOTest.class,
        MethodsNotEmptyCTOTest.class,
        MethodsNotNullCTOTest.class,
        MethodsNullCTOTest.class,
        MethodsOrCTOTest.class,
        MethodsCTOExceptionTest.class,
        MethodsCompositeKeyTest.class,
        MethodsCompositeKeyCTOTest.class,
        CriteriaConditionHolderTest.class,
        MemberOfCTOTest.class,
        MemberOfTest.class,
        EnumTest.class,
        EnumCTOTest.class,
        SumMultiSelectTest.class,
        SumMultiSelectCTOTest.class,
        AverageMultiSelectTest.class,
        AverageMultiSelectCTOTest.class,
        DiffMultiSelectTest.class,
        DiffMultiSelectCTOTest.class,
        DivideMultiSelectTest.class,
        DivideMultiSelectCTOTest.class,
        MultiplyMultiSelectTest.class,
        MultiplyMultiSelectCTOTest.class,
        ModuleMultiSelectTest.class,
        ModuleMultiSelectCTOTest.class,
        BasicMultiSelectTest.class,
        BasicMultiSelectCTOTest.class,
        CountMultiSelectTest.class,
        CountMultiSelectCTOTest.class,
        SquareMultiSelectTest.class,
        SquareMultiSelectCTOTest.class,
        BasicSubQueryTest.class,
        ComparisonSubQueryTest.class,
        OrSubQueryTest.class,
        MemberOfSubQueryTest.class,
        JoinSubQueryTest.class,
        DistinctSubQueryTest.class,
        BetweenSubQueryTest.class,
        NullEmptySubQueryTest.class,
        StringSubQueryTest.class,
        ExceptionSubQueryTest.class,
        MultiSelectNotAvailableSubQueryTest.class,
        SubQueryCTOTest.class
})
public class UaiCriteriaAbstractSuit extends AbstractTest {
}
