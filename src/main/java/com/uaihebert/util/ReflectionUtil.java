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
package com.uaihebert.util;

import com.uaihebert.model.EntityPathHelper;

import javax.persistence.Embeddable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class ReflectionUtil {

    private static final int LAST_CLASS_OF_PATH = 2;
    private static final List<Class> ALLOWED_BAG_TYPES = new ArrayList<Class>();

    static {
        ALLOWED_BAG_TYPES.add(Set.class);
        ALLOWED_BAG_TYPES.add(List.class);
        ALLOWED_BAG_TYPES.add(Collection.class);
    }

    private ReflectionUtil() {
    }

    private static String getAttributeType(final Class<?> entityClass, final String attributeName) {
        return getAttributeType(entityClass, attributeName, false);
    }

    private static String getAttributeType(final Class<?> entityClass, final String attributeName, final boolean silently) {
        validateData(entityClass, attributeName);

        Field currentField = null;

        final String[] pathArray = EntityPathHelper.extractPaths(attributeName);

        Class<?> currentClass = entityClass;

        for (int i = 0; i < pathArray.length; i++) {
            final Field foundField = getField(currentClass, pathArray[i], silently);
            final int fieldPosition = i + 1;

            if (fieldPosition < pathArray.length) {
                currentClass = foundField.getType();

                if (currentClass.equals(List.class) || currentClass.equals(Set.class) || currentClass.equals(Collection.class)) {
                    currentClass = extractBagType(foundField);
                }
            } else {
                currentField = foundField;
            }
        }

        if (currentField != null) {
            return currentField.getType().getCanonicalName();
        }

        return null;
    }

    private static Class extractBagType(final Field foundField) {
        final Class currentClass;
        final Type genericType = foundField.getGenericType();

        final ParameterizedType aType = (ParameterizedType) genericType;
        final Type[] fieldArgTypes = aType.getActualTypeArguments();
        currentClass = (Class) fieldArgTypes[0];
        return currentClass;
    }

    private static void validateData(final Class<?> entityClass, final String attributeName) {
        if (entityClass == null) {
            throw new IllegalArgumentException("entityClass parameter cannot be null");
        }

        if (attributeName == null || attributeName.isEmpty()) {
            throw new IllegalArgumentException("attributeName parameter cannot be null");
        }
    }

    private static List<Field> getAllFields(final List<Field> fields, final Class<?> type) {
        Collections.addAll(fields, type.getDeclaredFields());

        if (type.getSuperclass() != null) {
            fields.addAll(getAllFields(fields, type.getSuperclass()));
        }
        return fields;
    }

    private static Field getField(final Class<?> entityClass, final String attributeName, final boolean silently) {
        final List<Field> fields = new ArrayList<Field>();

        getAllFields(fields, entityClass);

        final String internedName = attributeName.intern();
        for (final Field field : fields) {
            if (internedName.equals(field.getName())) {
                return field;
            }
        }

        if (silently) {
            return null;
        }

        throw new IllegalArgumentException("We could not find the parameter: " + attributeName + " in the given class: " + entityClass);
    }

    public static boolean isDouble(final Class<?> entityClass, final String attributeName) {
        final String attributeType = getAttributeType(entityClass, attributeName);

        return attributeType.toLowerCase().contains("double");
    }

    public static <T> boolean isFloat(final Class<T> entityClass, final String attributeName) {
        final String attributeType = getAttributeType(entityClass, attributeName);

        return attributeType.toLowerCase().contains("float");
    }

    public static <T> boolean isLong(final Class<T> entityClass, final String attributeName) {
        final String attributeType = getAttributeType(entityClass, attributeName);

        return attributeType.toLowerCase().contains("long");
    }

    private static <T> boolean isInteger(final Class<T> entityClass, final String attributeName) {
        final String attributeType = getAttributeType(entityClass, attributeName);

        return attributeType.toLowerCase().contains("integer") || "int".equalsIgnoreCase(attributeType);
    }

    public static <T> boolean isString(final Class<T> entityClass, final String attributeName) {
        final String attributeType = getAttributeType(entityClass, attributeName);

        return attributeType.toLowerCase().contains("string");
    }

    public static <T> boolean isDate(final Class<T> entityClass, final String attributeName) {
        final String attributeType = getAttributeType(entityClass, attributeName);

        return attributeType.toLowerCase().contains("util.date");
    }

    public static <T> boolean isCalendar(final Class<T> entityClass, final String attributeName) {
        final String attributeType = getAttributeType(entityClass, attributeName);

        return attributeType.toLowerCase().contains("calendar");
    }

    public static <T> boolean isLocalDate(final Class<T> entityClass, final String attributeName) {
        final String attributeType = getAttributeType(entityClass, attributeName);
        return attributeType.toLowerCase().matches(".*time\\.localdate$");
    }

    public static <T> boolean isLocalDateTime(final Class<T> entityClass, final String attributeName) {
        final String attributeType = getAttributeType(entityClass, attributeName);
        return attributeType.toLowerCase().matches(".*time\\.localdatetime$");
    }

    public static <T> boolean isLocalTime(final Class<T> entityClass, final String attributeName) {
        final String attributeType = getAttributeType(entityClass, attributeName);
        return attributeType.toLowerCase().matches(".*time\\.localtime$");
    }


    private static boolean isBagType(final Class<?> entityClass) {
        return ALLOWED_BAG_TYPES.contains(entityClass);
    }

    public static <T> boolean isList(final Class<T> entityClass, final String collectionName) {
        final String attributeType = getAttributeType(entityClass, collectionName);

        return attributeType.toLowerCase().contains("list");
    }

    public static <T> boolean isList(final Class<T> entityClass, final String collectionName, final boolean silently) {
        final String attributeType = getAttributeType(entityClass, collectionName, silently);

        return attributeType != null && attributeType.toLowerCase().contains("list");
    }

    public static <T> boolean isSet(final Class<T> entityClass, final String collectionName) {
        final String attributeType = getAttributeType(entityClass, collectionName);

        return attributeType.toLowerCase().contains("set");
    }

    public static <T> boolean isSet(final Class<T> entityClass, final String collectionName, final boolean silently) {
        final String attributeType = getAttributeType(entityClass, collectionName, silently);

        return attributeType != null && attributeType.toLowerCase().contains("set");
    }

    public static <T> boolean isMap(final Class<T> entityClass, final String collectionName) {
        final String attributeType = getAttributeType(entityClass, collectionName);

        return attributeType.toLowerCase().contains("map");
    }

    public static <T> boolean isMap(final Class<T> entityClass, final String collectionName, final boolean silently) {
        final String attributeType = getAttributeType(entityClass, collectionName, silently);

        return attributeType != null && attributeType.toLowerCase().contains("map");
    }

    public static <T> boolean isCollection(final Class<T> entityClass, final String collectionName) {
        final String attributeType = getAttributeType(entityClass, collectionName);

        return attributeType.toLowerCase().contains("collection");
    }

    public static <T> boolean isCollection(final Class<T> entityClass, final String collectionName, final boolean silently) {
        final String attributeType = getAttributeType(entityClass, collectionName, silently);

        return attributeType != null && attributeType.toLowerCase().contains("collection");
    }

    public static <T> boolean isBigDecimal(final Class<T> entityClass, final String attributeName) {
        final String attributeType = getAttributeType(entityClass, attributeName);

        return attributeType.toLowerCase().contains("bigdecimal");
    }

    public static boolean isEmbeddedId(final String path, final Class<?> entityClass) {
        final String[] paths = EntityPathHelper.extractPaths(path);
        final Class<?> attributeClass = getClassOfTheLastAttribute(paths, entityClass);

        final Annotation[] declaredAnnotations = attributeClass.getDeclaredAnnotations();

        for (final Annotation annotation : declaredAnnotations) {
            if (annotation instanceof Embeddable) {
                return true;
            }
        }

        return false;
    }

    public static Class getClassOfTheLastAttribute(final String[] fullPath, final Class<?> entityClass) {
        final String currentPosition = fullPath[0];
        final Field attributeField = getField(entityClass, currentPosition, false);
        final Class attributeClass = extractClassFromField(attributeField);

        if (fullPath.length > LAST_CLASS_OF_PATH) {
            final String constructedPath = EntityPathHelper.constructPathFromArray(1, fullPath.length, fullPath);
            return getClassOfTheLastAttribute(EntityPathHelper.extractPaths(constructedPath), attributeClass);
        }

        return attributeClass;
    }

    private static Class extractClassFromField(final Field attributeField) {
        if (ReflectionUtil.isBagType(attributeField.getType())) {
            final ParameterizedType collectionType = (ParameterizedType) attributeField.getGenericType();

            return (Class) collectionType.getActualTypeArguments()[0];
        }

        return attributeField.getType();
    }

    public static boolean isSupportedCollection(final Class entityClass, final String collectionName, final boolean silently) {
        return isCollection(entityClass, collectionName, silently) ||
                isSet(entityClass, collectionName, silently) ||
                isList(entityClass, collectionName, silently) ||
                isMap(entityClass, collectionName, silently);
    }

    public static boolean isNumber(final Class entityClass, final String attributeName) {
        return isDecimalType(entityClass, attributeName) ||
                isLong(entityClass, attributeName) ||
                isInteger(entityClass, attributeName);
    }

    private static boolean isDecimalType(final Class entityClass, final String attributeName) {
        return isBigDecimal(entityClass, attributeName) ||
                isDouble(entityClass, attributeName) ||
                isFloat(entityClass, attributeName);
    }
}
