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
package com.uaihebert.test.uaicriteria.subquery;

import com.uaihebert.model.test.RegularEntityOne;
import com.uaihebert.model.test.RegularEntityTwo;
import com.uaihebert.test.AbstractTest;
import com.uaihebert.uaicriteria.UaiCriteria;
import org.junit.Test;

public class MultiSelectNotAvailableSubQueryTest extends AbstractTest {

    @Test(expected = IllegalStateException.class)
    public void isAverageRaisingException() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.average("");
    }

    @Test(expected = IllegalStateException.class)
    public void isSquareRaisingException() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.square("");
    }

    @Test(expected = IllegalStateException.class)
    public void isAddMultiSelectSelectAttributeRaisingException() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.addMultiSelectAttribute("");
    }

    @Test(expected = IllegalStateException.class)
    public void isGroupByRaisingException() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.groupBy("");
    }

    @Test(expected = IllegalStateException.class)
    public void isSumRaisingException01() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.sum("");
    }

    @Test(expected = IllegalStateException.class)
    public void isSumRaisingException02() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.sum("", 1);
    }

    @Test(expected = IllegalStateException.class)
    public void isSumRaisingException03() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.sum(2, "");
    }

    @Test(expected = IllegalStateException.class)
    public void isDiffRaisingException01() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.diff("", "");
    }

    @Test(expected = IllegalStateException.class)
    public void isDiffRaisingException02() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.diff("", 1);
    }

    @Test(expected = IllegalStateException.class)
    public void isDiffRaisingException03() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.diff(1, "");
    }

    @Test(expected = IllegalStateException.class)
    public void isMultiplyRaisingException01() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.multiply("", "");
    }

    @Test(expected = IllegalStateException.class)
    public void isMultiplyRaisingException02() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.multiply("", 1);
    }

    @Test(expected = IllegalStateException.class)
    public void isMultiplyRaisingException03() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.multiply(1, "");
    }

    @Test(expected = IllegalStateException.class)
    public void isDivideRaisingException01() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.divide("", "");
    }

    @Test(expected = IllegalStateException.class)
    public void isDivideRaisingException02() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.divide("", 1);
    }

    @Test(expected = IllegalStateException.class)
    public void isDivideRaisingException03() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.divide(1, "");
    }

    @Test(expected = IllegalStateException.class)
    public void isModuleRaisingException01() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.module("", "");
    }

    @Test(expected = IllegalStateException.class)
    public void isModuleRaisingException02() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.module("", 1);
    }

    @Test(expected = IllegalStateException.class)
    public void isModuleRaisingException03() {
        final UaiCriteria<RegularEntityTwo> uaiCriteria = createCriteria(RegularEntityTwo.class);

        final UaiCriteria<RegularEntityOne> subQuery = uaiCriteria.subQuery("id", RegularEntityOne.class);

        subQuery.module(1, "");
    }
}