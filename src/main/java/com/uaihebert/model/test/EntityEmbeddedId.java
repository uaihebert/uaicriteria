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
package com.uaihebert.model.test;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class EntityEmbeddedId {

    @EmbeddedId
    private EmbeddedIdDummy id;
    private String justString;
    @ManyToOne
    private JoinEntityEmbeddedId joinEntityEmbeddedId;

    public EntityEmbeddedId() {
    }

    public EntityEmbeddedId(final EmbeddedIdDummy id, final String justString) {
        this.id = id;
        this.justString = justString;
    }

    public EmbeddedIdDummy getId() {
        return id;
    }

    public void setId(final EmbeddedIdDummy id) {
        this.id = id;
    }

    public String getJustString() {
        return justString;
    }

    public void setJustString(final String justString) {
        this.justString = justString;
    }

    public JoinEntityEmbeddedId getJoinEntityEmbeddedId() {
        return joinEntityEmbeddedId;
    }

    public void setJoinEntityEmbeddedId(final JoinEntityEmbeddedId joinEntityEmbeddedId) {
        this.joinEntityEmbeddedId = joinEntityEmbeddedId;
    }
}