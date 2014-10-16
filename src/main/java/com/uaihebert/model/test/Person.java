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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Person {

    @Id
    private int id;
    private String name;
    private String nickName;
    private Integer clothesInCloset;
    private Integer shoesInCloset;
    private Long totalBooksOwned;
    private Long socialSecurityNumber;
    private boolean brazilian;
    private boolean japanese;
    private float weight;
    private float weightB;
    private double height;
    private double heightB;
    private BigDecimal justBigDecimalA;
    private BigDecimal justBigDecimalB;
    @Temporal(TemporalType.DATE)
    private Date firstJobDate;
    @Temporal(TemporalType.DATE)
    private Date firstSoccerMatchDate;
    @Temporal(TemporalType.DATE)
    private Calendar birthDayDate;
    @Temporal(TemporalType.DATE)
    private Calendar firstKissDate;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Car car;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Dog> dogs;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Certification> certifications;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Collection<Cat> cats;

    public Person() {

    }

    public Person(final int id, final String name, final String nickName,
                  final Integer clothesInCloset, final Calendar birthDayDate,
                  final Calendar firstKissDate, final float weight, final double height,
                  final Long socialSecurityNumber, final Integer shoesInCloset,
                  final Long totalBooksOwned, final Date firstJobDate, final Date firstSoccerMatchDate, final boolean brazilian,
                  final boolean japanese, final double heightB, final float weightB, final BigDecimal justBigDecimalA, final BigDecimal justBigDecimalB) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.clothesInCloset = clothesInCloset;
        this.birthDayDate = birthDayDate;
        this.firstKissDate = firstKissDate;
        this.weight = weight;
        this.height = height;
        this.socialSecurityNumber = socialSecurityNumber;
        this.shoesInCloset = shoesInCloset;
        this.totalBooksOwned = totalBooksOwned;
        this.firstJobDate = firstJobDate;
        this.firstSoccerMatchDate = firstSoccerMatchDate;
        this.brazilian = brazilian;
        this.japanese = japanese;
        this.heightB = heightB;
        this.weightB = weightB;
        this.justBigDecimalA = justBigDecimalA;
        this.justBigDecimalB = justBigDecimalB;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }

    public Integer getClothesInCloset() {
        return clothesInCloset;
    }

    public void setClothesInCloset(final Integer clothesInCloset) {
        this.clothesInCloset = clothesInCloset;
    }

    public Calendar getBirthDayDate() {
        return birthDayDate;
    }

    public void setBirthDayDate(final Calendar birthDayDate) {
        this.birthDayDate = birthDayDate;
    }

    public Calendar getFirstKissDate() {
        return firstKissDate;
    }

    public void setFirstKissDate(final Calendar firstKissDate) {
        this.firstKissDate = firstKissDate;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(final float weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(final double height) {
        this.height = height;
    }

    @Override
    public int hashCode() {
        return id * 33;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Person) {
            final Person person = (Person) obj;
            return person.id == id;
        }

        return false;
    }

    public Long getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(final Long socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Integer getShoesInCloset() {
        return shoesInCloset;
    }

    public void setShoesInCloset(final Integer shoesInCloset) {
        this.shoesInCloset = shoesInCloset;
    }

    public Long getTotalBooksOwned() {
        return totalBooksOwned;
    }

    public void setTotalBooksOwned(final Long totalBooksOwned) {
        this.totalBooksOwned = totalBooksOwned;
    }

    public Date getFirstJobDate() {
        return firstJobDate;
    }

    public void setFirstJobDate(final Date firstJobDate) {
        this.firstJobDate = firstJobDate;
    }

    public Date getFirstSoccerMatchDate() {
        return firstSoccerMatchDate;
    }

    public void setFirstSoccerMatchDate(final Date firstSoccerMatchDate) {
        this.firstSoccerMatchDate = firstSoccerMatchDate;
    }

    public boolean isBrazilian() {
        return brazilian;
    }

    public void setBrazilian(final boolean brazilian) {
        this.brazilian = brazilian;
    }

    public boolean isJapanese() {
        return japanese;
    }

    public void setJapanese(final boolean japanese) {
        this.japanese = japanese;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(final Car car) {
        this.car = car;
    }

    public List<Dog> getDogs() {
        if (dogs == null) {
            dogs = new ArrayList<Dog>();
        }

        return dogs;
    }

    public void setDogs(final List<Dog> dogs) {
        this.dogs = dogs;
    }

    public Set<Certification> getCertifications() {
        if (certifications == null) {
            certifications = new HashSet<Certification>();
        }

        return certifications;
    }

    public void setCertifications(final Set<Certification> certifications) {
        this.certifications = certifications;
    }

    public Collection<Cat> getCats() {
        if (cats == null) {
            cats = new ArrayList<Cat>();
        }

        return cats;
    }

    public void setCats(final Collection<Cat> cats) {
        this.cats = cats;
    }

    public double getHeightB() {
        return heightB;
    }

    public void setHeightB(final double heightB) {
        this.heightB = heightB;
    }

    public float getWeightB() {
        return weightB;
    }

    public void setWeightB(final float weightB) {
        this.weightB = weightB;
    }
}