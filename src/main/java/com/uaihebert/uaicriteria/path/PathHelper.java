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
package com.uaihebert.uaicriteria.path;

import com.uaihebert.uaicriteria.base.element.BaseCriteria;
import com.uaihebert.uaicriteria.wrapper.JoinWrapper;
import com.uaihebert.util.ReflectionUtil;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;

public final class PathHelper {
    private static final int TO_COMPARE_TO_LENGTH = 1;

    private PathHelper() {
    }

    public static Path extractPath(final BaseCriteria baseCriteria, final String attributeName) {
        if (StringPathBreaker.hasPath(attributeName)) {
            if (ReflectionUtil.isEmbeddedId(attributeName, baseCriteria.getEntityClass())) {
                return PathExtractor.extractIdPath(baseCriteria, StringPathBreaker.splitPath(attributeName));
            }

            return PathExtractor.extractPathWithJoin(baseCriteria, StringPathBreaker.splitPath(attributeName));
        }

        return baseCriteria.getPath(attributeName);
    }

    public static void createJoinPaths(final BaseCriteria baseCriteria, final String joinName, final JoinType joinType, final boolean isFetch) {
        if (StringPathBreaker.hasPath(joinName)) {
            addJoinWithPathInTheJoin(baseCriteria, StringPathBreaker.splitPath(joinName), 0, null, joinType, isFetch);
            return;
        }

        baseCriteria.addJoin(joinName, joinType, isFetch);
    }

    private static JoinWrapper addJoinWithPathInTheJoin(final BaseCriteria baseCriteria, final String[] fullJoinName, final int receivedCurrentIndex, final JoinWrapper currentJoin, final JoinType joinType, final boolean isFetch) {
        int currentIndex = receivedCurrentIndex;
        final int nextJoinIndex = currentIndex + 1;

        final String joinPathBasedOnIndex = StringPathBreaker.createPathAsString(fullJoinName, nextJoinIndex);

        if (baseCriteria.hasJoin(joinPathBasedOnIndex)) {
            return getExistedJoin(baseCriteria, fullJoinName, currentIndex, joinPathBasedOnIndex, joinType, isFetch);
        }

        final JoinWrapper join = createJoin(baseCriteria, currentJoin, fullJoinName, currentIndex, joinType, isFetch);

        if (hasReachedTheEndOfThePath(fullJoinName, currentIndex)) {
            return currentJoin;
        }

        return addJoinWithPathInTheJoin(baseCriteria, fullJoinName, ++currentIndex, join, joinType, isFetch);
    }

    private static JoinWrapper createJoin(final BaseCriteria baseCriteria, final JoinWrapper rootJoin, final String[] fullJoinName, final int currentIndex, final JoinType joinType, final boolean isFetch) {
        final String currentJoinPath = fullJoinName[currentIndex];
        final int nextJoinIndex = currentIndex + 1;

        final JoinWrapper join;

        if (rootJoin == null) {
            join = baseCriteria.addJoin(currentJoinPath, joinType, isFetch);
        } else {
            join = rootJoin.createJoinFromJoin(currentJoinPath, joinType);

            final String pathAsStringBasedOnIndex = StringPathBreaker.createPathAsString(fullJoinName, nextJoinIndex);

            baseCriteria.addJoinFromJoin(pathAsStringBasedOnIndex, join);
        }

        return join;
    }

    private static JoinWrapper getExistedJoin(final BaseCriteria baseCriteria, final String[] fullJoinName, final int receivedCurrentIndex, final String joinPathBasedOnIndex, final JoinType joinType, final boolean isFetch) {
        int currentIndex = receivedCurrentIndex;
        final JoinWrapper join = baseCriteria.getJoin(joinPathBasedOnIndex);

        return addJoinWithPathInTheJoin(baseCriteria, fullJoinName, ++currentIndex, join, joinType, isFetch);
    }

    private static boolean hasReachedTheEndOfThePath(final String[] fullJoinName, final int nextJoinPathIndex) {
        return (nextJoinPathIndex + TO_COMPARE_TO_LENGTH) == fullJoinName.length;
    }
}