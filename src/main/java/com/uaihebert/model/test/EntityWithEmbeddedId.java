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

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EntityWithEmbeddedId {

    @EmbeddedId
    private RegularEmbeddedId embeddedId;

    private String anyString;

    public RegularEmbeddedId getEmbeddedId() {
        return embeddedId;
    }

    public void setEmbeddedId(final RegularEmbeddedId embeddedId) {
        this.embeddedId = embeddedId;
    }

    public String getAnyString() {
        return anyString;
    }

    public void setAnyString(final String anyString) {
        this.anyString = anyString;
    }
}