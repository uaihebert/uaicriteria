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

public final class StringPathBreaker {
    private static final int LAST_DOT_POSITION = 1;

    private StringPathBreaker() {
    }

    public static boolean hasPath(final String path) {
        return path.split("\\.").length > 1;
    }

    public static String[] splitPath(final String path) {
        return path.split("\\.");
    }

    public static String createPathAsString(final String[] stringArray, final int endAtIndex) {
        String result = "";

        for (int i = 0; i < endAtIndex; i++) {
            result += stringArray[i] + ".";
        }

        return result.substring(0, result.length() - LAST_DOT_POSITION);
    }

    public static String[] removeLastPath(final String[] pathArray) {
        final String[] result = new String[pathArray.length - 1];

        System.arraycopy(pathArray, 0, result, 0, pathArray.length - 1);

        return result;
    }
}