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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Entity
public class RegularEntityOne {

    @Id
    private long id;

    private String stringAttribute;

    private Long longAttributeOne;
    private Long longAttributeTwo;

    private Float floatAttributeOne;
    private Float floatAttributeTwo;

    private Integer integerAttributeOne;
    private Integer integerAttributeTwo;

    private Boolean booleanAttributeOne;
    private Boolean booleanAttributeTwo;

    @Temporal(TemporalType.DATE)
    private Calendar calendarAttributeOne;

    @Temporal(TemporalType.DATE)
    private Calendar calendarAttributeTwo;

    @Temporal(TemporalType.DATE)
    private Date dateAttributeOne;

    @Temporal(TemporalType.DATE)
    private Date dateAttributeTwo;

    private BigDecimal bigDecimalAttributeOne;
    private BigDecimal bigDecimalAttributeTwo;

    private Double doubleAttributeOne;
    private Double doubleAttributeTwo;

    @Enumerated(EnumType.STRING)
    private RegularEnum regularEnum;

    @OneToOne
    private RegularEntityTwo regularEntityTwo;

    @OneToOne
    private RegularEntityThree regularEntityThree;

    @OneToOne
    private RegularEntityFour regularEntityFour;

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

    public Long getLongAttributeOne() {
        return longAttributeOne;
    }

    public void setLongAttributeOne(final Long longAttributeOne) {
        this.longAttributeOne = longAttributeOne;
    }

    public Long getLongAttributeTwo() {
        return longAttributeTwo;
    }

    public void setLongAttributeTwo(final Long longAttributeTwo) {
        this.longAttributeTwo = longAttributeTwo;
    }

    public Float getFloatAttributeOne() {
        return floatAttributeOne;
    }

    public void setFloatAttributeOne(final Float floatAttributeOne) {
        this.floatAttributeOne = floatAttributeOne;
    }

    public Float getFloatAttributeTwo() {
        return floatAttributeTwo;
    }

    public void setFloatAttributeTwo(final Float floatAttributeTwo) {
        this.floatAttributeTwo = floatAttributeTwo;
    }

    public Integer getIntegerAttributeOne() {
        return integerAttributeOne;
    }

    public void setIntegerAttributeOne(final Integer integerAttributeOne) {
        this.integerAttributeOne = integerAttributeOne;
    }

    public Integer getIntegerAttributeTwo() {
        return integerAttributeTwo;
    }

    public void setIntegerAttributeTwo(final Integer integerAttributeTwo) {
        this.integerAttributeTwo = integerAttributeTwo;
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

    public Calendar getCalendarAttributeOne() {
        return calendarAttributeOne;
    }

    public void setCalendarAttributeOne(final Calendar calendarAttributeOne) {
        this.calendarAttributeOne = calendarAttributeOne;
    }

    public Calendar getCalendarAttributeTwo() {
        return calendarAttributeTwo;
    }

    public void setCalendarAttributeTwo(final Calendar calendarAttributeTwo) {
        this.calendarAttributeTwo = calendarAttributeTwo;
    }

    public Date getDateAttributeOne() {
        return dateAttributeOne;
    }

    public void setDateAttributeOne(final Date dateAttributeOne) {
        this.dateAttributeOne = dateAttributeOne;
    }

    public Date getDateAttributeTwo() {
        return dateAttributeTwo;
    }

    public void setDateAttributeTwo(final Date dateAttributeTwo) {
        this.dateAttributeTwo = dateAttributeTwo;
    }

    public BigDecimal getBigDecimalAttributeOne() {
        return bigDecimalAttributeOne;
    }

    public void setBigDecimalAttributeOne(final BigDecimal bigDecimalAttributeOne) {
        this.bigDecimalAttributeOne = bigDecimalAttributeOne;
    }

    public BigDecimal getBigDecimalAttributeTwo() {
        return bigDecimalAttributeTwo;
    }

    public void setBigDecimalAttributeTwo(final BigDecimal bigDecimalAttributeTwo) {
        this.bigDecimalAttributeTwo = bigDecimalAttributeTwo;
    }

    public Double getDoubleAttributeOne() {
        return doubleAttributeOne;
    }

    public void setDoubleAttributeOne(final Double doubleAttributeOne) {
        this.doubleAttributeOne = doubleAttributeOne;
    }

    public Double getDoubleAttributeTwo() {
        return doubleAttributeTwo;
    }

    public void setDoubleAttributeTwo(final Double doubleAttributeTwo) {
        this.doubleAttributeTwo = doubleAttributeTwo;
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

    public RegularEntityFour getRegularEntityFour() {
        return regularEntityFour;
    }

    public void setRegularEntityFour(final RegularEntityFour regularEntityFour) {
        this.regularEntityFour = regularEntityFour;
    }

    public RegularEnum getRegularEnum() {
        return regularEnum;
    }

    public void setRegularEnum(final RegularEnum regularEnum) {
        this.regularEnum = regularEnum;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final RegularEntityOne that = (RegularEntityOne) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}