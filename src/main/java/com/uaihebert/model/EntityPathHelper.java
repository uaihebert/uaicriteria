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
package com.uaihebert.model;

public final class EntityPathHelper {

    private EntityPathHelper() {
    }

    /**
     * Method that will extract a array of paths from a String
     *
     * @param path path as String with a path like "a.b.d"
     * @return an array of paths
     */
    public static String[] extractPaths(final String path) {
        return path.split("\\.");
    }

    /**
     * Method that will transform an array of path into a String
     *
     * @param firstIndex first position that will be used in the array
     * @param finalIndex the last position that will be used in the array
     * @param paths      paths to be returned
     * @return Path as String
     */
    public static String constructPathFromArray(final int firstIndex, final int finalIndex, final String[] paths) {

        final StringBuilder result = new StringBuilder();

        for (int i = firstIndex; i < finalIndex; i++) {
            if (!(result.length() == 0)) {
                result.append(".");
            }

            result.append(paths[i]);
        }

        return result.toString();
    }
}