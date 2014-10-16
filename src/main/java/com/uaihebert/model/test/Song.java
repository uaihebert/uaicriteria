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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Song {

    @Id
    private int id;

    private String name;

    private String artist;

    private int length;
    private Long totalDownloads;
    private float weight;
    private double price;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Temporal(TemporalType.DATE)
    private Calendar creationDate;

    @Enumerated(EnumType.STRING)
    private SongType type;

    public Song() {

    }

    public Song(final int id, final Calendar creationDate, final String name, final String artist, final int length, final Long totalDownloads, final float weight, final double price, final Date releaseDate, final SongType type) {
        this.creationDate = creationDate;
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.length = length;
        this.totalDownloads = totalDownloads;
        this.weight = weight;
        this.price = price;
        this.releaseDate = releaseDate;
        this.type = type;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Calendar creationDate) {
        this.creationDate = creationDate;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(final String group) {
        this.artist = group;
    }

    public int getLength() {
        return length;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    public Long getTotalDownloads() {
        return totalDownloads;
    }

    public void setTotalDownloads(final Long totalDownloads) {
        this.totalDownloads = totalDownloads;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(final float weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(final Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Song) {
            final Song song = (Song) obj;
            return song.id == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public SongType getType() {
        return type;
    }

    public void setType(final SongType type) {
        this.type = type;
    }
}