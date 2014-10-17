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
package com.uaihebert.uaicriteria.subquery;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaImp;
import com.uaihebert.uaicriteria.base.element.BasicCriteriaElements;

import javax.persistence.criteria.Subquery;
import java.util.List;

public class SubQueryImp<T> extends UaiCriteriaImp<T> implements UaiCriteria<T> {

    public SubQueryImp(final String selectedAttribute, final Class<T> subQueryClass, final BasicCriteriaElements basicCriteriaElements) {
        super(selectedAttribute, subQueryClass, basicCriteriaElements);
    }

    @Override
    public List<T> getResultList() {
        throw getResultNoAllowed();
    }

    private IllegalStateException getResultNoAllowed() {
        return new IllegalStateException("Hello, I am a SubQuery. I do not have an entity manager do run the query. \n" +
                " To get the result of your query you should use the method of the root UaiCriteria, the one you used to create the SubQuery.");
    }

    @Override
    public T getSingleResult() {
        throw getResultNoAllowed();
    }

    @Override
    public List getMultiSelectResult() {
        throw getResultNoAllowed();
    }

    @Override
    public <E> UaiCriteria<E> subQuery(final String subQueryResult, final Class<E> entityClass) {
        throw nestedSubQueryNotAllowed();
    }

    private IllegalStateException nestedSubQueryNotAllowed() {
        return new IllegalStateException("Hello, I am a SubQuery. Right now is not possible to do sub-sub-query. \n" +
                getGetInTouchMessage());
    }

    private String getGetInTouchMessage() {
        return " Get in touch with the development team (uaicriteria.com) asking for this feature. ";
    }

    @Override
    public Long count() {
        return countRegularCriteria();
    }

    @Override
    public Long countRegularCriteria() {
        throw new IllegalStateException("Hello, I am a SubQuery. Right now is not possible to a count in a subquery. \n" +
                getGetInTouchMessage());
    }

    @Override
    public UaiCriteria<T> countAttribute(final String... attributeNameArray) {
        throw new IllegalStateException("Hello, I am a SubQuery. Right now is not possible to a count in a subquery. \n" +
                getGetInTouchMessage());
    }

    @Override
    public UaiCriteria<T> innerJoinFetch(final String joinName) {
        throw new IllegalStateException("Hello, I am a SubQuery. You are not allowed to fetch a result in a subquery. \n");
    }

    @Override
    public UaiCriteria<T> leftJoinFetch(final String joinName) {
        throw new IllegalStateException("Hello, I am a SubQuery. You are not allowed to fetch a result in a subquery. \n");
    }

    @Override
    public UaiCriteria<T> orderByAsc(final String attributeName) {
        throw new IllegalStateException("Hello, I am a SubQuery. You are not allowed to order by a result in a subquery. \n");
    }

    @Override
    public UaiCriteria<T> orderByDesc(final String attributeName) {
        throw new IllegalStateException("Hello, I am a SubQuery. You are not allowed to order by a result in a subquery. \n");
    }

    @Override
    public UaiCriteria<T> setFirstResult(final Integer firstResult) {
        throw new IllegalStateException("Hello, I am a SubQuery. You are not allowed to set the first result in a result in a subquery. \n");
    }

    @Override
    public UaiCriteria<T> setMaxResults(final Integer maxResults) {
        throw new IllegalStateException("Hello, I am a SubQuery. You are not allowed to set the max results in a result in a subquery. \n");
    }

    @Override
    public UaiCriteria<T> addHint(final String key, final String value) {
        throw new IllegalStateException("Hello, I am a SubQuery. If you want to add a hint, add it to the Original UaiCriteria that you instantiate. \n");
    }

    @Override
    public UaiCriteria<T> sum(final String... attributeNameArray) {
        return multiselectNotAvailable();
    }

    private UaiCriteria<T> multiselectNotAvailable() {
        throw new IllegalStateException("Hello, I am a SubQuery. Right now is not possible to do multiselect actions in a SubQuery. \n" +
                getGetInTouchMessage());
    }

    @Override
    public <E> UaiCriteria<T> andAttributeIn(final String attributeName, final UaiCriteria<E> uaiSubQuery) {
        throw nestedSubQueryNotAllowed();
    }

    @Override
    public <E> UaiCriteria<T> andAttributeNotIn(final String attributeName, final UaiCriteria<E> uaiSubQuery) {
        return null;
    }

    @Override
    public <N extends Number> UaiCriteria<T> sum(final String attributeName, final N number) {
        return multiselectNotAvailable();
    }

    @Override
    public <N extends Number> UaiCriteria<T> sum(final N number, final String attributeName) {
        return multiselectNotAvailable();
    }

    @Override
    public UaiCriteria<T> diff(final String firstAttribute, final String secondAttribute) {
        return multiselectNotAvailable();
    }

    @Override
    public <N extends Number> UaiCriteria<T> diff(final String attributeName, final N number) {
        return multiselectNotAvailable();
    }

    @Override
    public <N extends Number> UaiCriteria<T> diff(final N number, final String attributeName) {
        return multiselectNotAvailable();
    }

    @Override
    public UaiCriteria<T> multiply(final String firstAttribute, final String secondAttribute) {
        return multiselectNotAvailable();
    }

    @Override
    public <N extends Number> UaiCriteria<T> multiply(final String attributeName, final N number) {
        return multiselectNotAvailable();
    }

    @Override
    public <N extends Number> UaiCriteria<T> multiply(final N number, final String attributeName) {
        return multiselectNotAvailable();
    }

    @Override
    public UaiCriteria<T> divide(final String firstAttribute, final String secondAttribute) {
        return multiselectNotAvailable();
    }

    @Override
    public <N extends Number> UaiCriteria<T> divide(final String attributeName, final N number) {
        return multiselectNotAvailable();
    }

    @Override
    public <N extends Number> UaiCriteria<T> divide(final N number, final String attributeName) {
        return multiselectNotAvailable();
    }

    @Override
    public UaiCriteria<T> module(final String firstAttribute, final String secondAttribute) {
        return multiselectNotAvailable();
    }

    @Override
    public UaiCriteria<T> module(final String attributeName, final Integer number) {
        return multiselectNotAvailable();
    }

    @Override
    public UaiCriteria<T> module(final Integer number, final String attributeName) {
        return multiselectNotAvailable();
    }

    @Override
    public UaiCriteria<T> average(final String... attributeNameArray) {
        return multiselectNotAvailable();
    }

    @Override
    public UaiCriteria<T> square(final String... attributeNameArray) {
        return multiselectNotAvailable();
    }

    @Override
    public UaiCriteria<T> addMultiSelectAttribute(final String... attributeNameArray) {
        return multiselectNotAvailable();
    }

    @Override
    public UaiCriteria<T> groupBy(final String... attributeNameArray) {
        return multiselectNotAvailable();
    }

    public Subquery getSubQuery() {
        return basicCriteriaElements.getSubquery();
    }

    public void prepareSubQuery() {
        basicCriteriaElements.getBaseSubCriteria().setUpCriteria();
    }
}