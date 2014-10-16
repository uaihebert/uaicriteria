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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Dog {

    @Id
    private int id;

    private String name;
    private double weight;
    private float hairSize;
    private long fleasTotal;
    private int toysTotal;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfFirstPuppyBirth;

    @ManyToOne
    private Person person;

    public Dog() {

    }

    public Dog(final int id, final String name, final double weight, final Date dateOfBirth, final float hairSize, final long fleasTotal, final int toysTotal) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
        this.hairSize = hairSize;
        this.fleasTotal = fleasTotal;
        this.toysTotal = toysTotal;
    }

    public static void main(final String[] args) {

        System.out.println(new Date());
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(final double weight) {
        this.weight = weight;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(final Person person) {
        this.person = person;
    }

    public Date getDateOfFirstPuppyBirth() {
        return dateOfFirstPuppyBirth;
    }

    public void setDateOfFirstPuppyBirth(final Date dateOfFirstPuppyBirth) {
        this.dateOfFirstPuppyBirth = dateOfFirstPuppyBirth;
    }

    public float getHairSize() {
        return hairSize;
    }

    public void setHairSize(final float hairSize) {
        this.hairSize = hairSize;
    }

    public long getFleasTotal() {
        return fleasTotal;
    }

    public void setFleasTotal(final long fleasTotal) {
        this.fleasTotal = fleasTotal;
    }

    public int getToysTotal() {
        return toysTotal;
    }

    public void setToysTotal(final int toysTotal) {
        this.toysTotal = toysTotal;
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Dog) {
            final Dog dog = (Dog) obj;
            return dog.getId() == getId();
        }

        return false;
    }
}