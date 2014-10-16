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

import com.uaihebert.model.EntityPathHelper;
import com.uaihebert.uaicriteria.base.element.BaseCriteria;
import com.uaihebert.uaicriteria.wrapper.JoinWrapper;

import javax.persistence.criteria.Path;

public final class PathExtractor {
    private static final int ID_IN_ROOT_CLASS = 2;

    private PathExtractor() {
    }

    public static Path extractPathWithJoin(final BaseCriteria baseCriteria, final String[] pathArray) {
        final String[] pathWithoutAttribute = StringPathBreaker.removeLastPath(pathArray);

        final String pathAsString = StringPathBreaker.createPathAsString(pathWithoutAttribute, pathWithoutAttribute.length);

        final JoinWrapper join = baseCriteria.getJoin(pathAsString);

        return join.getPath(pathArray[pathArray.length - 1]);
    }

    public static Path extractIdPath(final BaseCriteria baseCriteria, final String[] pathArray) {
        final int idAttributeNameIndex = pathArray.length - 1;
        final String embeddedIdAttributeName = pathArray[idAttributeNameIndex];

        if (pathArray.length == ID_IN_ROOT_CLASS) {
            final Path embeddedId = PathHelper.extractPath(baseCriteria, pathArray[0]);

            return extractPathFromPath(embeddedId, embeddedIdAttributeName);
        }

        final String joinWithoutIdAttribute = EntityPathHelper.constructPathFromArray(0, pathArray.length - 2, pathArray);

        final JoinWrapper join = baseCriteria.getJoin(joinWithoutIdAttribute);

        final String embeddedAttributeName = EntityPathHelper.constructPathFromArray(pathArray.length - 2, pathArray.length - 1, pathArray);

        final Path embeddedId = join.getPath(embeddedAttributeName);

        return extractPathFromPath(embeddedId, embeddedIdAttributeName);
    }

    private static Path extractPathFromPath(final Path embeddedId, final String embeddedIdAttributeName) {
        return embeddedId.get(embeddedIdAttributeName);
    }
}
