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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CriteriaConditionHolder {
    private static final int DEFAULT_ATTRIBUTE_INDEX = 0;

    private static final List<CriteriaConditionType> TYPES_THAT_ALLOWS_NULL_VALUE = Arrays.asList(
            CriteriaConditionType.COLLECTION_IS_NOT_EMPTY,
            CriteriaConditionType.COLLECTION_IS_EMPTY,
            CriteriaConditionType.AND_IS_NOT_NULL,
            CriteriaConditionType.AND_IS_NULL,
            CriteriaConditionType.OR_IS_NULL,
            CriteriaConditionType.OR_IS_NOT_NULL
    );

    private final Object[] valueArray;

    public final int attributeIndex;
    public final boolean toLowerCase;
    public final String attributeName;
    public final CriteriaConditionType criteriaConditionType;

    public CriteriaConditionHolder(final CriteriaConditionType criteriaConditionType, final Object... valueArray) {
        this(DEFAULT_ATTRIBUTE_INDEX, false, null, criteriaConditionType, valueArray);
    }

    public CriteriaConditionHolder(final String attributeName, final CriteriaConditionType criteriaConditionType, final Object... valueArray) {
        this(DEFAULT_ATTRIBUTE_INDEX, false, attributeName, criteriaConditionType, valueArray);
    }

    public CriteriaConditionHolder(final boolean toLowerCase, final String attributeName, final CriteriaConditionType criteriaConditionType, final Object... valueArray) {
        this(DEFAULT_ATTRIBUTE_INDEX, toLowerCase, attributeName, criteriaConditionType, valueArray);
    }

    public CriteriaConditionHolder(final int attributeIndex, final boolean toLowerCase, final String attributeName, final CriteriaConditionType criteriaConditionType, final Object... valueArray) {

        if (hasNullValue(valueArray) && nullNotAllowedForTheType(criteriaConditionType)) {
            throw new IllegalArgumentException("The value cannot be null");
        }

        this.attributeIndex = attributeIndex;
        this.toLowerCase = toLowerCase;
        this.valueArray = valueArray;
        this.attributeName = attributeName;
        this.criteriaConditionType = criteriaConditionType;
    }

    private boolean nullNotAllowedForTheType(final CriteriaConditionType criteriaConditionType) {
        return !TYPES_THAT_ALLOWS_NULL_VALUE.contains(criteriaConditionType);
    }

    private boolean hasNullValue(final Object[] valueArray) {
        if (valueArray.length == 0) {
            return true;
        }

        for (final Object aValue : valueArray) {
            if (aValue == null) {
                return true;
            }
        }

        return false;
    }

    public Object getValue() {
        return valueArray[0];
    }

    public Object getValue(final int valueIndex) {
        return valueArray[valueIndex];
    }

    public Object[] getValueArray() {
        return valueArray;
    }

    public String getValueAsString() {
        return getValue().toString();
    }

    public String[] getValueAsStringArray() {
        return convertToStringArray();
    }

    public List<String> getValueAsStringList() {
        final String[] stringArray = convertToStringArray();
        final List<String> result = new ArrayList<String>();

        Collections.addAll(result, stringArray);

        return result;
    }

    private String[] convertToStringArray() {
        final String[] stringArray = new String[valueArray.length];

        for (int i = 0; i < valueArray.length; i++) {
            stringArray[i] = valueArray[i].toString();
        }
        return stringArray;
    }

    public List getValueAsList() {
        final List list = new ArrayList();

        Collections.addAll(list, valueArray);

        return list;
    }
}