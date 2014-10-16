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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class RegularEntityFour {
    @Id
    private long id;
    private String stringAttribute;
    private Boolean booleanAttributeOne;
    private Boolean booleanAttributeTwo;

    @OneToOne(mappedBy = "regularEntityFour")
    private RegularEntityOne regularEntityOne;

    @OneToOne(mappedBy = "regularEntityFour")
    private RegularEntityTwo regularEntityTwo;

    @OneToOne(mappedBy = "regularEntityFour")
    private RegularEntityThree regularEntityThree;

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

    public RegularEntityOne getRegularEntityOne() {
        return regularEntityOne;
    }

    public void setRegularEntityOne(final RegularEntityOne regularEntityOne) {
        this.regularEntityOne = regularEntityOne;
    }

    public RegularEntityTwo getRegularEntityTwo() {
        return regularEntityTwo;
    }

    public void setRegularEntityTwo(final RegularEntityTwo regularEntityTwo) {
        this.regularEntityTwo = regularEntityTwo;
    }

    public RegularEntityThree getRegularEntityThree() {
        return regularEntityThree;
    }

    public void setRegularEntityThree(final RegularEntityThree regularEntityThree) {
        this.regularEntityThree = regularEntityThree;
    }
}