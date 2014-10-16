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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class NickName {

    @Id
    private int id;
    private String name;
    private String justString;
    private long justLong;
    private float justFloat;
    private double justDouble;
    private boolean justBoolean;
    private BigDecimal justBigDecimal;

    @Temporal(TemporalType.DATE)
    private Date justDate;

    @Temporal(TemporalType.DATE)
    private Calendar justCalendar;

    @ManyToMany
    @JoinTable(name = "NICKNAME_DUMMY_COLLECTION", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "dummy_id"))
    private Collection<DummyEntity> justCollection;

    @ManyToMany
    @JoinTable(name = "NICKNAME_DUMMY_LIST", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "dummy_id"))
    private List<DummyEntity> justList;

    @ManyToMany
    @JoinTable(name = "NICKNAME_DUMMY_SET", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "dummy_id"))
    private Set<DummyEntity> justSet;

    public NickName() {
    }

    public NickName(final int id, final String name, final String justString, final double justDouble, final long justLong, final float justFloat, final BigDecimal justBigDecimal, final Date justDate, final Calendar justCalendar, final boolean justBoolean) {
        this.id = id;
        this.name = name;
        this.justString = justString;
        this.justDouble = justDouble;
        this.justLong = justLong;
        this.justFloat = justFloat;
        this.justBigDecimal = justBigDecimal;
        this.justDate = justDate;
        this.justCalendar = justCalendar;
        this.justBoolean = justBoolean;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setJustSet(final Set<DummyEntity> justSet) {
        this.justSet = justSet;
    }

    public void setJustList(final List<DummyEntity> justList) {
        this.justList = justList;
    }

    public void setJustCollection(final Collection<DummyEntity> justCollection) {
        this.justCollection = justCollection;
    }
}