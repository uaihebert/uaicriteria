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
public class EntityWithEmbeddedIdJoinThreeLevel {

    @Id
    private long id;
    private String stringAttribute;

    @OneToOne
    private EntityWithEmbeddedIdJoinTwoLevel entityWithEmbeddedIdJoinTwoLevel;

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

    public EntityWithEmbeddedIdJoinTwoLevel getEntityWithEmbeddedIdJoinTwoLevel() {
        return entityWithEmbeddedIdJoinTwoLevel;
    }

    public void setEntityWithEmbeddedIdJoinTwoLevel(final EntityWithEmbeddedIdJoinTwoLevel entityWithEmbeddedIdJoinTwoLevel) {
        this.entityWithEmbeddedIdJoinTwoLevel = entityWithEmbeddedIdJoinTwoLevel;
    }
}