/*
 * Copyright (C) 2021 iofairy, <https://github.com/io-fairy/functional>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iofairy.time;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Date;

import static com.iofairy.validator.Preconditions.*;

/**
 * ChronoInterval
 *
 * @since 0.6.0
 */
public interface ChronoInterval extends TemporalAmount, Serializable {

    /**
     * Adds to the specified date object.
     *
     * @param date date
     * @return Date
     */
    default Date addTo(Date date) {
        checkNullNPE(date, args("date"));
        return Date.from((Instant) this.addTo(date.toInstant()));
    }

    /**
     * Adds to the specified calendar object.
     *
     * @param calendar calendar
     * @return Calendar
     */
    default Calendar addTo(Calendar calendar) {
        checkNullNPE(calendar, args("calendar"));
        return DateTime.from(this.addTo(DateTime.from(calendar).getZonedDateTime())).toCalendar(calendar.getTimeZone().toZoneId());
    }


    /**
     * Adds this {@link ChronoInterval} to specified temporal object.
     *
     * @param temporal temporal
     * @param <T>      Temporal type
     * @return temporal
     */
    default <T extends Temporal> T plusTo(T temporal) {
        @SuppressWarnings("unchecked")
        T t = (T) this.addTo(temporal);
        return t;
    }

    /**
     * Subtracts this object from the specified date object.
     *
     * @param date date
     * @return Date
     */
    default Date subtractFrom(Date date) {
        checkNullNPE(date, args("date"));
        return Date.from((Instant) this.subtractFrom(date.toInstant()));
    }

    /**
     * Subtracts this object from the specified calendar object.
     *
     * @param calendar calendar
     * @return Calendar
     */
    default Calendar subtractFrom(Calendar calendar) {
        checkNullNPE(calendar, args("calendar"));
        return DateTime.from(this.subtractFrom(DateTime.from(calendar).getZonedDateTime())).toCalendar(calendar.getTimeZone().toZoneId());
    }

    /**
     * Subtracts this {@link ChronoInterval} from specified temporal object.
     *
     * @param temporal temporal
     * @param <T>      Temporal type
     * @return temporal
     */
    default <T extends Temporal> T minusFrom(T temporal) {
        @SuppressWarnings("unchecked")
        T t = (T) this.subtractFrom(temporal);
        return t;
    }

}
