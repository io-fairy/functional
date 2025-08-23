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

import com.iofairy.tcf.Try;

import java.time.ZoneId;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Time Zone<br>
 * 时区
 *
 * @since 0.6.0
 */
public class TZ {
    /**
     * All Available Zone Ids.
     */
    public static final Set<ZoneId> ZONE_IDS = ZoneId.getAvailableZoneIds().stream().map(TZ::getZoneId).filter(Objects::nonNull).collect(Collectors.toSet());
    /**
     * Default Time Zone
     */
    public static final ZoneId DEFAULT_ZONE = Try.tcf(() -> ZoneId.systemDefault(), false);
    /**
     * <b>上海(+08:00)</b> Time Zone
     */
    public static final ZoneId SHANGHAI = getZoneId("Asia/Shanghai");
    /**
     * <b>重庆(+08:00)</b> Time Zone
     */
    public static final ZoneId CHONGQING = getZoneId("Asia/Chongqing");
    /**
     * <b>重庆(+08:00)</b> Time Zone
     */
    public static final ZoneId CHUNGKING = getZoneId("Asia/Chungking");
    /**
     * <b>哈尔滨(+08:00)</b> Time Zone
     */
    public static final ZoneId HARBIN = getZoneId("Asia/Harbin");
    /**
     * <b>香港(+08:00)</b> Time Zone
     */
    public static final ZoneId HONG_KONG = getZoneId("Asia/Hong_Kong");
    /**
     * <b>香港(+08:00)</b> Time Zone
     */
    public static final ZoneId HONGKONG = getZoneId("Hongkong");
    /**
     * <b>澳门(+08:00)</b> Time Zone
     */
    public static final ZoneId MACAU = getZoneId("Asia/Macau");
    /**
     * <b>台北(+08:00)</b> Time Zone
     */
    public static final ZoneId TAIPEI = getZoneId("Asia/Taipei");
    /**
     * <b>乌鲁木齐(+06:00)</b> Time Zone
     */
    public static final ZoneId URUMQI = getZoneId("Asia/Urumqi");
    /**
     * <b>喀什(+06:00)</b> Time Zone
     */
    public static final ZoneId KASHGAR = getZoneId("Asia/Kashgar");
    /**
     * <b>PRC(+08:00)</b> Time Zone
     */
    public static final ZoneId PRC = getZoneId("PRC");
    /**
     * <b>英国伦敦(+00:00)</b> Time Zone
     */
    public static final ZoneId LONDON = getZoneId("Europe/London");
    /**
     * <b>爱尔兰都柏林(+00:00)</b> Time Zone
     */
    public static final ZoneId DUBLIN = getZoneId("Europe/Dublin");
    /**
     * <b>德国柏林(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId BERLIN = getZoneId("Europe/Berlin");
    /**
     * <b>俄罗斯莫斯科(+03:00)</b> Time Zone
     */
    public static final ZoneId MOSCOW = getZoneId("Europe/Moscow");
    /**
     * <b>法国巴黎(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId PARIS = getZoneId("Europe/Paris");
    /**
     * <b>美国纽约(-05:00，夏令时：-04:00)</b> Time Zone
     */
    public static final ZoneId NEW_YORK = getZoneId("America/New_York");
    /**
     * <b>美国洛杉矶(-08:00，夏令时：-07:00)</b> Time Zone
     */
    public static final ZoneId LOS_ANGELES = getZoneId("America/Los_Angeles");
    /**
     * <b>夏威夷州檀香山（火奴鲁鲁）(-10:00)</b> Time Zone
     */
    public static final ZoneId HONOLULU = getZoneId("Pacific/Honolulu");
    /**
     * <b>阿联酋迪拜(+04:00)</b> Time Zone
     */
    public static final ZoneId DUBAI = getZoneId("Asia/Dubai");
    /**
     * <b>澳大利亚首都堪培拉(+11:00，夏令时：+10:00)</b> Time Zone
     */
    public static final ZoneId CANBERRA = getZoneId("Australia/Canberra");
    /**
     * <b>澳大利亚悉尼(+11:00，夏令时：+10:00)</b> Time Zone
     */
    public static final ZoneId SYDNEY = getZoneId("Australia/Sydney");
    /**
     * <b>耶路撒冷(+02:00，夏令时：+03:00)</b> Time Zone
     */
    public static final ZoneId JERUSALEM = getZoneId("Asia/Jerusalem");
    /**
     * <b>意大利首都罗马(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId ROME = getZoneId("Europe/Rome");
    /**
     * <b>印度尼西亚首都雅加达(+07:00)</b> Time Zone
     */
    public static final ZoneId JAKARTA = getZoneId("Asia/Jakarta");
    /**
     * <b>智利复活节岛(-05:00，夏令时：-06:00)</b> Time Zone
     */
    public static final ZoneId CHILE_EASTERISLAND = getZoneId("Chile/EasterIsland");
    /**
     * <b>智利大陆(-03:00，夏令时：-04:00)</b> Time Zone
     */
    public static final ZoneId CHILE_CONTINENTAL = getZoneId("Chile/Continental");
    /**
     * <b>智利首都圣地亚哥(-03:00，夏令时：-04:00)</b> Time Zone
     */
    public static final ZoneId SANTIAGO = getZoneId("America/Santiago");
    /**
     * <b>捷克首都布拉格(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId PRAGUE = getZoneId("Europe/Prague");
    /**
     * <b>荷兰首都阿姆斯特丹(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId AMSTERDAM = getZoneId("Europe/Amsterdam");
    /**
     * <b>泰国首都曼谷(+07:00)</b> Time Zone
     */
    public static final ZoneId BANGKOK = getZoneId("Asia/Bangkok");
    /**
     * <b>日本(+09:00)</b> Time Zone
     */
    public static final ZoneId JAPAN = getZoneId("Japan");
    /**
     * <b>韩国首都首尔(+09:00)</b> Time Zone
     */
    public static final ZoneId SEOUL = getZoneId("Asia/Seoul");
    /**
     * <b>印度加尔各答(+05:30)</b> Time Zone
     */
    public static final ZoneId KOLKATA = getZoneId("Asia/Kolkata");
    /**
     * <b>GMT(+00:00)</b> Time Zone
     */
    public static final ZoneId GMT = getZoneId("GMT");
    /**
     * <b>UTC(+00:00)</b> Time Zone
     */
    public static final ZoneId UTC = getZoneId("UTC");
    /**
     * <b>Etc/UTC(+00:00)</b> Time Zone
     */
    public static final ZoneId ETC_UTC = getZoneId("Etc/UTC");
    /**
     * <b>UCT(+00:00)</b> Time Zone
     */
    public static final ZoneId UCT = getZoneId("UCT");
    /**
     * <b>GMT0(+00:00)</b> Time Zone
     */
    public static final ZoneId GMT0 = getZoneId("GMT0");
    /**
     * <b>WET（西欧时间）(+00:00，夏令时：+01:00)</b> Time Zone
     */
    public static final ZoneId WET = getZoneId("WET");
    /**
     * <b>EET（东欧时间）(+02:00，夏令时：+03:00)</b> Time Zone
     */
    public static final ZoneId EET = getZoneId("EET");
    /**
     * <b>CET（欧洲中部时间）(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId CET = getZoneId("CET");
    /**
     * <b>GB（英国时间，等同于：Europe/London）(+00:00)</b> Time Zone
     */
    public static final ZoneId GB = getZoneId("GB");
    /**
     * <b>GB-Eire（爱尔兰时间，等同于：Europe/London）(+00:00)</b> Time Zone
     */
    public static final ZoneId GB_EIRE = getZoneId("GB-Eire");
    /**
     * <b>NZ（新西兰时间，等同于：Pacific/Auckland）(+13:00)</b> Time Zone
     */
    public static final ZoneId NZ = getZoneId("NZ");
    /**
     * <b>NZ-CHAT（查塔姆群岛时间，等同于：Pacific/Chatham）(+13:00)</b> Time Zone
     */
    public static final ZoneId NZ_CHAT = getZoneId("NZ-CHAT");
    /**
     * <b>MET（中欧时间: Middle European Time）(+01:00)</b> Time Zone
     */
    public static final ZoneId MET = getZoneId("MET");
    /**
     * <b>ROK（韩国时间，等同于：Asia/Seoul）(+09:00)</b> Time Zone
     */
    public static final ZoneId ROK = getZoneId("ROK");
    /**
     * <b>W-SU（俄罗斯莫斯科，等同于：Europe/Moscow）(+03:00)</b> Time Zone
     */
    public static final ZoneId W_SU = getZoneId("W-SU");
    /**
     * <b>Universal（等同于：Etc/UTC）(+00:00)</b> Time Zone
     */
    public static final ZoneId UNIVERSAL = getZoneId("Universal");
    /**
     * <b>Zulu（等同于：Etc/UTC）(+00:00)</b> Time Zone
     */
    public static final ZoneId ZULU = getZoneId("Zulu");


    private static ZoneId getZoneId(final String zoneIdName) {
        return Try.tcf(() -> ZoneId.of(zoneIdName), false);
    }


}
