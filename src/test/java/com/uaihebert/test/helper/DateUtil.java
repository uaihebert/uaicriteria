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
package com.uaihebert.test.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date getFormattedDate(final String date) {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return formatter.parse(date);
        } catch (final ParseException e) {
            throw new RuntimeException("Error when parsing the date: " + e);
        }
    }

    public static Calendar getFormattedCalendar(final String date) {
        final Date formattedDate = getFormattedDate(date);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(formattedDate);

        return calendar;
    }
}
