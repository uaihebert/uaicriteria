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
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class JoinEntityEmbeddedId {

    @Id
    private int id;

    private String justString;

    @OneToMany(mappedBy = "joinEntityEmbeddedId")
    private List<EntityEmbeddedId> entityEmbeddedIdList;

    public List<EntityEmbeddedId> getEntityEmbeddedIdList() {
        if (entityEmbeddedIdList == null) {
            entityEmbeddedIdList = new ArrayList<EntityEmbeddedId>();
        }

        return entityEmbeddedIdList;
    }

    public void setEntityEmbeddedIdList(final List<EntityEmbeddedId> entityEmbeddedId) {
        this.entityEmbeddedIdList = entityEmbeddedId;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getJustString() {
        return justString;
    }

    public void setJustString(final String justString) {
        this.justString = justString;
    }
}
