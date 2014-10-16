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
package com.uaihebert.cto;

import com.uaihebert.uaicriteria.UaiCriteriaImp;

public enum JoinHolderType {
    INNER, LEFT, INNER_FETCH, LEFT_FETCH;

    public <T> void createJoin(final UaiCriteriaImp<T> uaiCriteria, final JoinHolder holder) {
        if (INNER.equals(this)) {
            uaiCriteria.innerJoin(holder.joinName);
            return;
        }

        if (LEFT.equals(this)) {
            uaiCriteria.leftJoin(holder.joinName);
            return;
        }

        if (INNER_FETCH.equals(this)) {
            uaiCriteria.innerJoinFetch(holder.joinName);
            return;
        }

        uaiCriteria.leftJoinFetch(holder.joinName);
    }
}