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
package com.uaihebert.model.test;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
public class RegularEntityFive {

    @Id
    private long id;
    private String stringAttribute;
    private Boolean booleanAttributeOne;
    private Boolean booleanAttributeTwo;

    @ElementCollection
    private List<String> stringList;

    @ElementCollection
    private Map<String, String> stringMap;

    @OneToMany
    private List<RegularEntityOne> regularEntityOneList;

    @OneToMany
    private Set<RegularEntityTwo> regularEntityTwoSet;

    @OneToMany
    private Collection<RegularEntityThree> regularEntityThreeCollection;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getStringAttribute() {
        return stringAttribute;
    }

    public void setStringAttribute(final String stringAttribute) {
        this.stringAttribute = stringAttribute;
    }

    public Boolean getBooleanAttributeOne() {
        return booleanAttributeOne;
    }

    public void setBooleanAttributeOne(final Boolean booleanAttributeOne) {
        this.booleanAttributeOne = booleanAttributeOne;
    }

    public Boolean getBooleanAttributeTwo() {
        return booleanAttributeTwo;
    }

    public void setBooleanAttributeTwo(final Boolean booleanAttributeTwo) {
        this.booleanAttributeTwo = booleanAttributeTwo;
    }

    public List<RegularEntityOne> getRegularEntityOneList() {
        return regularEntityOneList;
    }

    public void setRegularEntityOneList(final List<RegularEntityOne> regularEntityOneList) {
        this.regularEntityOneList = regularEntityOneList;
    }

    public Set<RegularEntityTwo> getRegularEntityTwoSet() {
        return regularEntityTwoSet;
    }

    public void setRegularEntityTwoSet(final Set<RegularEntityTwo> regularEntityTwoSet) {
        this.regularEntityTwoSet = regularEntityTwoSet;
    }

    public Collection<RegularEntityThree> getRegularEntityThreeCollection() {
        return regularEntityThreeCollection;
    }

    public void setRegularEntityThreeCollection(final Collection<RegularEntityThree> regularEntityThreeCollection) {
        this.regularEntityThreeCollection = regularEntityThreeCollection;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(final List<String> stringList) {
        this.stringList = stringList;
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public void setStringMap(final Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final RegularEntityFive that = (RegularEntityFive) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}