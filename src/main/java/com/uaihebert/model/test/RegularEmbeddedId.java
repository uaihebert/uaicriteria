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

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RegularEmbeddedId implements Serializable {
    private int idInteger;
    private String idString;

    public RegularEmbeddedId() {
    }

    public RegularEmbeddedId(final int idInteger, final String idString) {
        this.idInteger = idInteger;
        this.idString = idString;
    }

    public int getIdInteger() {
        return idInteger;
    }

    public void setIdInteger(final int idInteger) {
        this.idInteger = idInteger;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(final String idString) {
        this.idString = idString;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final RegularEmbeddedId that = (RegularEmbeddedId) o;

        if (idInteger != that.idInteger) return false;
        if (!idString.equals(that.idString)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idInteger;
        result = 31 * result + idString.hashCode();
        return result;
    }
}
