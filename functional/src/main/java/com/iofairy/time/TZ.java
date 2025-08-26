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
     * <b>澳门(+08:00)</b> Time Zone
     */
    public static final ZoneId MACAO = getZoneId("Asia/Macao");
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
     * <b>英国➤伦敦(+00:00)</b> Time Zone
     */
    public static final ZoneId LONDON = getZoneId("Europe/London");
    /**
     * <b>爱尔兰➤都柏林(+00:00)</b> Time Zone
     */
    public static final ZoneId DUBLIN = getZoneId("Europe/Dublin");
    /**
     * <b>冰岛(+00:00)</b> Time Zone
     */
    public static final ZoneId ICELAND = getZoneId("Iceland");
    /**
     * <b>冰岛➤雷克雅未克(+00:00)</b> Time Zone
     */
    public static final ZoneId REYKJAVIK = getZoneId("Atlantic/Reykjavik");
    /**
     * <b>德国➤柏林(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId BERLIN = getZoneId("Europe/Berlin");
    /**
     * <b>俄罗斯➤莫斯科(+03:00)</b> Time Zone
     */
    public static final ZoneId MOSCOW = getZoneId("Europe/Moscow");
    /**
     * <b>法国➤巴黎(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId PARIS = getZoneId("Europe/Paris");
    /**
     * <b>梵蒂冈(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId VATICAN = getZoneId("Europe/Vatican");
    /**
     * <b>希腊➤雅典(+02:00，夏令时：+03:00)</b> Time Zone
     */
    public static final ZoneId ATHENS = getZoneId("Europe/Athens");
    /**
     * <b>意大利➤首都罗马(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId ROME = getZoneId("Europe/Rome");
    /**
     * <b>捷克➤首都布拉格(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId PRAGUE = getZoneId("Europe/Prague");
    /**
     * <b>荷兰➤首都阿姆斯特丹(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId AMSTERDAM = getZoneId("Europe/Amsterdam");
    /**
     * <b>奥地利➤维也纳(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId VIENNA = getZoneId("Europe/Vienna");
    /**
     * <b>瑞士➤苏黎世(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId ZURICH = getZoneId("Europe/Zurich");
    /**
     * <b>白俄罗斯➤首都明斯克(+03:00)</b> Time Zone
     */
    public static final ZoneId MINSK = getZoneId("Europe/Minsk");
    /**
     * <b>波兰(+01:00，夏令时：+02:00)</b> Time Zone
     */
    public static final ZoneId POLAND = getZoneId("Poland");
    /**
     * <b>土耳其(+03:00)</b> Time Zone
     */
    public static final ZoneId TURKEY = getZoneId("Turkey");
    /**
     * <b>土耳其➤伊斯坦布尔(+03:00)</b> Time Zone
     */
    public static final ZoneId ISTANBUL_EUROPE = getZoneId("Europe/Istanbul");
    /**
     * <b>土耳其➤伊斯坦布尔(+03:00)</b> Time Zone
     */
    public static final ZoneId ISTANBUL = getZoneId("Asia/Istanbul");
    /**
     * <b>美国➤纽约(-05:00，夏令时：-04:00)</b> Time Zone
     */
    public static final ZoneId NEW_YORK = getZoneId("America/New_York");
    /**
     * <b>美国➤洛杉矶(-08:00，夏令时：-07:00)</b> Time Zone
     */
    public static final ZoneId LOS_ANGELES = getZoneId("America/Los_Angeles");
    /**
     * <b>夏威夷州檀香山（火奴鲁鲁）(-10:00)</b> Time Zone
     */
    public static final ZoneId HONOLULU = getZoneId("Pacific/Honolulu");
    /**
     * <b>夏威夷岛(-10:00)</b> Time Zone
     */
    public static final ZoneId HAWAII = getZoneId("US/Hawaii");
    /**
     * <b>加拿大➤多伦多(-05:00，夏令时：-04:00)</b> Time Zone
     */
    public static final ZoneId TORONTO = getZoneId("America/Toronto");
    /**
     * <b>巴哈马➤首都拿骚(-05:00，夏令时：-04:00)</b> Time Zone
     */
    public static final ZoneId NASSAU = getZoneId("America/Nassau");
    /**
     * <b>智利➤首都圣地亚哥(-04:00，夏令时：-03:00)</b> Time Zone
     */
    public static final ZoneId SANTIAGO = getZoneId("America/Santiago");
    /**
     * <b>智利➤复活节岛(-06:00，夏令时：-05:00)</b> Time Zone
     */
    public static final ZoneId EASTERISLAND = getZoneId("Chile/EasterIsland");
    /**
     * <b>智利➤复活节岛(-06:00，夏令时：-05:00)</b> Time Zone
     */
    public static final ZoneId EASTER = getZoneId("Pacific/Easter");
    /**
     * <b>智利➤大陆(-04:00，夏令时：-03:00)</b> Time Zone
     */
    public static final ZoneId CONTINENTAL = getZoneId("Chile/Continental");
    /**
     * <b>澳大利亚➤首都堪培拉(+10:00，夏令时：+11:00)</b> Time Zone
     */
    public static final ZoneId CANBERRA = getZoneId("Australia/Canberra");
    /**
     * <b>澳大利亚➤悉尼(+10:00，夏令时：+11:00)</b> Time Zone
     */
    public static final ZoneId SYDNEY = getZoneId("Australia/Sydney");
    /**
     * <b>阿联酋➤迪拜(+04:00)</b> Time Zone
     */
    public static final ZoneId DUBAI = getZoneId("Asia/Dubai");
    /**
     * <b>以色列(+02:00，夏令时：+03:00)</b> Time Zone
     */
    public static final ZoneId ISRAEL = getZoneId("Israel");
    /**
     * <b>耶路撒冷(+02:00，夏令时：+03:00)</b> Time Zone
     */
    public static final ZoneId JERUSALEM = getZoneId("Asia/Jerusalem");
    /**
     * <b>印度尼西亚➤首都雅加达(+07:00)</b> Time Zone
     */
    public static final ZoneId JAKARTA = getZoneId("Asia/Jakarta");
    /**
     * <b>泰国➤首都曼谷(+07:00)</b> Time Zone
     */
    public static final ZoneId BANGKOK = getZoneId("Asia/Bangkok");
    /**
     * <b>也门➤亚丁(+03:00)</b> Time Zone
     */
    public static final ZoneId ADEN = getZoneId("Asia/Aden");
    /**
     * <b>日本(+09:00)</b> Time Zone
     */
    public static final ZoneId JAPAN = getZoneId("Japan");
    /**
     * <b>日本➤东京(+09:00)</b> Time Zone
     */
    public static final ZoneId TOKYO = getZoneId("Asia/Tokyo");
    /**
     * <b>新加坡(+08:00)</b> Time Zone
     */
    public static final ZoneId SINGAPORE = getZoneId("Singapore");
    /**
     * <b>新加坡(+08:00)</b> Time Zone
     */
    public static final ZoneId SINGAPORE_ASIA = getZoneId("Asia/Singapore");
    /**
     * <b>马来西亚➤吉隆坡(+08:00)</b> Time Zone
     */
    public static final ZoneId KUALA_LUMPUR = getZoneId("Asia/Kuala_Lumpur");
    /**
     * <b>韩国➤首都首尔(+09:00)</b> Time Zone
     */
    public static final ZoneId SEOUL = getZoneId("Asia/Seoul");
    /**
     * <b>朝鲜➤平壤(+08:30)</b> Time Zone
     */
    public static final ZoneId PYONGYANG = getZoneId("Asia/Pyongyang");
    /**
     * <b>印度➤加尔各答(+05:30)</b> Time Zone
     */
    public static final ZoneId KOLKATA = getZoneId("Asia/Kolkata");
    /**
     * <b>印度➤加尔各答(+05:30)</b> Time Zone
     */
    public static final ZoneId CALCUTTA = getZoneId("Asia/Calcutta");
    /**
     * <b>越南➤胡志明市(+07:00)</b> Time Zone
     */
    public static final ZoneId HO_CHI_MINH = getZoneId("Asia/Ho_Chi_Minh");
    /**
     * <b>蒙古➤乌兰巴托(+08:00)</b> Time Zone
     */
    public static final ZoneId ULAN_BATOR = getZoneId("Asia/Ulan_Bator");
    /**
     * <b>蒙古➤乌兰巴托(+08:00)</b> Time Zone
     */
    public static final ZoneId ULAANBAATAR = getZoneId("Asia/Ulaanbaatar");
    /**
     * <b>缅甸➤仰光(+06:30)</b> Time Zone
     */
    public static final ZoneId YANGON = getZoneId("Asia/Yangon");
    /**
     * <b>柬埔寨➤金边(+07:00)</b> Time Zone
     */
    public static final ZoneId PHNOM_PENH = getZoneId("Asia/Phnom_Penh");
    /**
     * <b>巴基斯坦➤卡拉奇港(+05:00)</b> Time Zone
     */
    public static final ZoneId KARACHI = getZoneId("Asia/Karachi");
    /**
     * <b>菲律宾➤马尼拉(+08:00)</b> Time Zone
     */
    public static final ZoneId MANILA = getZoneId("Asia/Manila");
    /**
     * <b>东帝汶➤帝力(+09:00)</b> Time Zone
     */
    public static final ZoneId DILI = getZoneId("Asia/Dili");
    /**
     * <b>沙特阿拉伯➤利雅得(+03:00)</b> Time Zone
     */
    public static final ZoneId RIYADH = getZoneId("Asia/Riyadh");
    /**
     * <b>埃及(+02:00)</b> Time Zone
     */
    public static final ZoneId EGYPT = getZoneId("Egypt");
    /**
     * <b>埃及➤开罗(+02:00)</b> Time Zone
     */
    public static final ZoneId CAIRO = getZoneId("Africa/Cairo");
    /**
     * <b>巴布亚新几内亚➤莫尔兹比港(+10:00)</b> Time Zone
     */
    public static final ZoneId PORT_MORESBY = getZoneId("Pacific/Port_Moresby");
    /**
     * <b>格林威治标准时间(GMT)(+00:00)</b> Time Zone
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
     * <b>格林威治(Greenwich)(+00:00)</b> Time Zone
     */
    public static final ZoneId GREENWICH = getZoneId("Greenwich");
    /**
     * <b>格林威治(Etc/Greenwich)(+00:00)</b> Time Zone
     */
    public static final ZoneId ETC_GREENWICH = getZoneId("Etc/Greenwich");
    /**
     * <b>UCT(+00:00)</b> Time Zone
     */
    public static final ZoneId UCT = getZoneId("UCT");
    /**
     * <b>GMT0(+00:00)</b> Time Zone
     */
    public static final ZoneId GMT0 = getZoneId("GMT0");
    /**
     * <b>Etc/GMT0(+00:00)</b> Time Zone
     */
    public static final ZoneId ETC_GMT0 = getZoneId("Etc/GMT0");
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
     * <b>Eire（爱尔兰时间，等同于：Europe/London）(+00:00)</b> Time Zone
     */
    public static final ZoneId EIRE = getZoneId("Eire");
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
     * <b>Etc/Universal(+00:00)</b> Time Zone
     */
    public static final ZoneId ETC_UNIVERSAL = getZoneId("Etc/Universal");
    /**
     * <b>Zulu（等同于：Etc/UTC）(+00:00)</b> Time Zone
     */
    public static final ZoneId ZULU = getZoneId("Zulu");
    /**
     * <b>Etc/Zulu（等同于：Etc/UTC）(+00:00)</b> Time Zone
     */
    public static final ZoneId ETC_ZULU = getZoneId("Etc/Zulu");


    private static ZoneId getZoneId(final String zoneIdName) {
        return Try.tcf(() -> ZoneId.of(zoneIdName), false);
    }


}
