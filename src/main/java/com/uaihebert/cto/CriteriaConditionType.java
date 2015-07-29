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

public enum CriteriaConditionType {
    EQUAL {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            if (holder.toLowerCase) {
                uaiCriteria.andEquals(holder.toLowerCase, holder.attributeName, holder.getValueAsString());
                return;
            }

            uaiCriteria.andEquals(holder.attributeName, holder.getValue());
        }
    },
    NOT_EQUAL {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            if (holder.toLowerCase) {
                uaiCriteria.andNotEquals(holder.toLowerCase, holder.attributeName, holder.getValueAsString());
                return;
            }

            uaiCriteria.andNotEquals(holder.attributeName, holder.getValue());
        }
    },
    OR_EQUAL {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            if (holder.toLowerCase) {
                uaiCriteria.orEquals(holder.toLowerCase, holder.attributeIndex, holder.attributeName, holder.getValueAsStringArray());
                return;
            }

            uaiCriteria.orEquals(holder.attributeIndex, holder.attributeName, holder.getValueArray());
        }
    },
    OR_LIKE {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.orStringLike(holder.toLowerCase, holder.attributeName, holder.getValueAsStringArray());
        }
    },
    OR_NOT_LIKE {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.orStringNotLike(holder.toLowerCase, holder.attributeName, holder.getValueAsStringArray());
        }
    },
    OR_NOT_EQUAL {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            if (holder.toLowerCase) {
                uaiCriteria.orNotEquals(holder.toLowerCase, holder.attributeName, holder.getValueAsStringArray());
                return;
            }

            uaiCriteria.orNotEquals(holder.attributeName, holder.getValueArray());
        }
    },
    AND_GREATER_THAN {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            if (holder.toLowerCase) {
                uaiCriteria.andGreaterThan(holder.toLowerCase, holder.attributeName, holder.getValueAsString());
                return;
            }

            uaiCriteria.andGreaterThan(holder.attributeName, holder.getValue());
        }
    },
    OR_GREATER_THAN {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            if (holder.toLowerCase) {
                uaiCriteria.orGreaterThan(holder.toLowerCase, holder.attributeName, holder.getValueAsString());
                return;
            }

            uaiCriteria.orGreaterThan(holder.attributeName, holder.getValue());
        }
    },
    LESS_THAN {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            if (holder.toLowerCase) {
                uaiCriteria.andLessThan(holder.toLowerCase, holder.attributeName, holder.getValueAsString());
                return;
            }

            uaiCriteria.andLessThan(holder.attributeName, holder.getValue());
        }
    },
    GREATER_OR_EQUAL_TO {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            if (holder.toLowerCase) {
                uaiCriteria.andGreaterOrEqualTo(holder.toLowerCase, holder.attributeName, holder.getValueAsString());
                return;
            }

            uaiCriteria.andGreaterOrEqualTo(holder.attributeName, holder.getValue());
        }
    },
    LESS_OR_EQUAL_TO {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            if (holder.toLowerCase) {
                uaiCriteria.andLessOrEqualTo(holder.toLowerCase, holder.attributeName, holder.getValueAsString());
                return;
            }

            uaiCriteria.andLessOrEqualTo(holder.attributeName, holder.getValue());
        }
    },
    BETWEEN {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final Object valueA = holder.getValue(0);
            final Object valueB = holder.getValue(1);

            if (holder.toLowerCase) {
                uaiCriteria.andBetween(holder.toLowerCase, holder.attributeName, valueA.toString(), valueB.toString());
                return;
            }

            uaiCriteria.andBetween(holder.attributeName, valueA, valueB);
        }
    },
    AND_ATTRIBUTE_IN {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.andAttributeIn(holder.attributeName, holder.getValueAsList());
        }
    },
    AND_ATTRIBUTE_NOT_IN {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.andAttributeNotIn(holder.attributeName, holder.getValueAsList());
        }
    },
    AND_IS_NULL {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.andIsNull(holder.attributeName);
        }
    },
    AND_IS_NOT_NULL {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.andIsNotNull(holder.attributeName);
        }
    },
    OR_IS_NULL {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.orIsNull(holder.attributeName);
        }
    },
    OR_IS_NOT_NULL {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.orIsNotNull(holder.attributeName);
        }
    },
    STRING_IN {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.andStringIn(holder.toLowerCase, holder.attributeName, holder.getValueAsStringList());
        }
    },
    STRING_NOT_IN {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.andStringNotIn(holder.toLowerCase, holder.attributeName, holder.getValueAsStringList());
        }
    },
    STRING_LIKE {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.andStringLike(holder.toLowerCase, holder.attributeName, holder.getValueAsString());
        }
    },
    STRING_NOT_LIKE {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.andStringNotLike(holder.toLowerCase, holder.attributeName, holder.getValueAsString());
        }
    },
    COLLECTION_IS_EMPTY {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.andCollectionIsEmpty(holder.attributeName);
        }
    },
    COLLECTION_IS_NOT_EMPTY {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.andCollectionIsNotEmpty(holder.attributeName);
        }
    },
    AND_SEPARATED_BY_OR {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            if (holder.toLowerCase) {
                uaiCriteria.addAndSeparatedByOr(holder.toLowerCase, holder.attributeIndex, holder.attributeName, holder.getValueAsString());
                return;
            }

            uaiCriteria.addAndSeparatedByOr(holder.attributeIndex, holder.attributeName, holder.getValue());
        }
    },
    IS_MEMBER_OF {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.andIsMemberOf(holder.getValue(), holder.attributeName);
        }
    },
    IS_NOT_MEMBER_OF {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.andIsNotMemberOf(holder.getValue(), holder.attributeName);
        }
    },
    HINT {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final Object[] values = holder.getValueArray();
            uaiCriteria.addHint(values[0].toString(), values[1].toString());
        }
    },
    AVG {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.average(holder.getValueAsStringArray());
        }
    },
    DIFF_ATTRIBUTE_ONLY {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final String[] valueAsStringArray = holder.getValueAsStringArray();
            uaiCriteria.diff(valueAsStringArray[0], valueAsStringArray[1]);
        }
    },
    DIFF_ATTRIBUTE_WITH_NUMBER {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final Object[] valueArray = holder.getValueArray();
            uaiCriteria.diff(valueArray[0].toString(), (Number) valueArray[1]);
        }
    },
    DIFF_NUMBER_WITH_ATTRIBUTE {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final Object[] valueArray = holder.getValueArray();
            uaiCriteria.diff((Number) valueArray[1], valueArray[0].toString());
        }
    },
    DIV_ATTRIBUTE_ONLY {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final String[] valueAsStringArray = holder.getValueAsStringArray();
            uaiCriteria.divide(valueAsStringArray[0], valueAsStringArray[1]);
        }
    },
    DIV_ATTRIBUTE_WITH_NUMBER {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final Object[] valueArray = holder.getValueArray();
            uaiCriteria.divide(valueArray[0].toString(), (Number) valueArray[1]);
        }
    },
    DIV_NUMBER_WITH_ATTRIBUTE {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final Object[] valueArray = holder.getValueArray();
            uaiCriteria.divide((Number) valueArray[1], valueArray[0].toString());
        }
    },
    MODULE_ATTRIBUTE_ONLY {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final String[] valueAsStringArray = holder.getValueAsStringArray();
            uaiCriteria.module(valueAsStringArray[0], valueAsStringArray[1]);
        }
    },
    MODULE_ATTRIBUTE_WITH_NUMBER {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final Object[] valueArray = holder.getValueArray();
            uaiCriteria.module(valueArray[0].toString(), (Integer) valueArray[1]);
        }
    },
    MODULE_NUMBER_WITH_ATTRIBUTE {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final Object[] valueArray = holder.getValueArray();
            uaiCriteria.module((Integer) valueArray[1], valueArray[0].toString());
        }
    },
    MULTIPLY_ATTRIBUTE_ONLY {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final String[] valueAsStringArray = holder.getValueAsStringArray();
            uaiCriteria.multiply(valueAsStringArray[0], valueAsStringArray[1]);
        }
    },
    MULTIPLY_ATTRIBUTE_WITH_NUMBER {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final Object[] valueArray = holder.getValueArray();
            uaiCriteria.multiply(valueArray[0].toString(), (Number) valueArray[1]);
        }
    },
    MULTIPLY_NUMBER_WITH_ATTRIBUTE {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final Object[] valueArray = holder.getValueArray();
            uaiCriteria.multiply((Number) valueArray[1], valueArray[0].toString());
        }
    },
    SUM_ATTRIBUTE_WITH_NUMBER {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final Object[] valueArray = holder.getValueArray();
            uaiCriteria.sum(valueArray[0].toString(), (Number) valueArray[1]);
        }
    },
    SUM_ATTRIBUTE_ONLY {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final String[] valueAsStringArray = holder.getValueAsStringArray();
            uaiCriteria.sum(valueAsStringArray);
        }
    },
    SUM_NUMBER_WITH_ATTRIBUTE {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            final Object[] valueArray = holder.getValueArray();
            uaiCriteria.sum((Number) valueArray[1], valueArray[0].toString());
        }
    },
    COUNT_ATTRIBUTE {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.countAttribute(holder.getValueAsStringArray());
        }
    },
    SQUARE {
        @Override
        public <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria) {
            uaiCriteria.square(holder.getValueAsStringArray());
        }
    };

    public abstract <T> void createCondition(final CriteriaConditionHolder holder, final UaiCriteriaImp<T> uaiCriteria);
}