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

import com.iofairy.top.S;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.iofairy.time.DateTimeSeparator.*;
import static com.iofairy.pattern.Pattern.*;

/**
 * 时间格式化解析类
 *
 * @since 0.6.0
 */
public class DateTimePattern {
    /*
     * DASH:  d   -
     * DOT:   o   .
     * SLASH: s   /
     * COLON: c   :
     * ZH:    z   年月日时分秒毫秒
     */
    public static final Pattern Y               = Pattern.compile("\\d{3,4}");                                                                    // yyyy
    public static final Pattern YM              = Pattern.compile("\\d{6}");                                                                      // yyyyMM
    public static final Pattern YMD             = Pattern.compile("\\d{8}");                                                                      // yyyyMMdd
    public static final Pattern YMDH            = Pattern.compile("\\d{10}");                                                                     // yyyyMMddHH
    public static final Pattern YMDHM           = Pattern.compile("\\d{12}");                                                                     // yyyyMMddHHmm
    public static final Pattern YMDHMS          = Pattern.compile("\\d{14}");                                                                     // yyyyMMddHHmmss
    public static final Pattern YMDHMSS         = Pattern.compile("\\d{17}");                                                                     // yyyyMMddHHmmssSSS
    public static final Pattern MdD             = Pattern.compile("\\d{1,2}-\\d{1,2}");                                                           // M-d
    public static final Pattern MsD             = Pattern.compile("\\d{1,2}/\\d{1,2}");                                                           // M/d
    public static final Pattern MoD             = Pattern.compile("\\d{1,2}\\.\\d{1,2}");                                                         // M.d
    public static final Pattern HcM             = Pattern.compile("\\d{1,2}:\\d{1,2}");                                                           // H:m
    public static final Pattern Yz              = Pattern.compile("\\d{3,4}年");                                                                  // yyyy年
    public static final Pattern MzDz            = Pattern.compile("\\d{1,2}月\\d{1,2}日");                                                        // M月d日
    public static final Pattern MzSz            = Pattern.compile("\\d{1,2}分\\d{1,2}秒");                                                        // m分s秒
    public static final Pattern HzMz            = Pattern.compile("\\d{1,2}时\\d{1,2}分");                                                        // H时m分
    public static final Pattern HoMz            = Pattern.compile("\\d{1,2}点\\d{1,2}分");                                                        // H点m分
    public static final Pattern McSS            = Pattern.compile("\\d{1,2}:\\d{1,2}\\.\\d{1,3}");                                               // m:s.S
    public static final Pattern HcMcS           = Pattern.compile("\\d{1,2}:\\d{1,2}:\\d{1,2}");                                                 // H:m:s
    public static final Pattern MdDH            = Pattern.compile("\\d{1,2}-\\d{1,2} \\d{1,2}");                                                 // M-d H
    public static final Pattern MsDH            = Pattern.compile("\\d{1,2}/\\d{1,2} \\d{1,2}");                                                 // M/d H
    public static final Pattern MoDH            = Pattern.compile("\\d{1,2}\\.\\d{1,2} \\d{1,2}");                                               // M.d H
    public static final Pattern YdM             = Pattern.compile("\\d{3,4}-\\d{1,2}");                                                          // yyyy-M
    public static final Pattern YsM             = Pattern.compile("\\d{3,4}/\\d{1,2}");                                                          // yyyy/M
    public static final Pattern YoM             = Pattern.compile("\\d{3,4}\\.\\d{1,2}");                                                        // yyyy.M
    public static final Pattern HzMzSz          = Pattern.compile("\\d{1,2}时\\d{1,2}分\\d{1,2}秒");                                              // H时m分s秒
    public static final Pattern HoMzSz          = Pattern.compile("\\d{1,2}点\\d{1,2}分\\d{1,2}秒");                                              // H点m分s秒
    public static final Pattern MzDzHz          = Pattern.compile("\\d{1,2}月\\d{1,2}日\\d{1,2}时");                                              // M月d日H时
    public static final Pattern MzDzHo          = Pattern.compile("\\d{1,2}月\\d{1,2}日\\d{1,2}点");                                              // M月d日H点
    public static final Pattern HcMcSS          = Pattern.compile("\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}");                                      // H:m:s.S
    public static final Pattern MdDHcM          = Pattern.compile("\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}");                                        // M-d H:m
    public static final Pattern MsDHcM          = Pattern.compile("\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}");                                        // M/d H:m
    public static final Pattern MoDHcM          = Pattern.compile("\\d{1,2}\\.\\d{1,2} \\d{1,2}:\\d{1,2}");                                      // M.d H:m
    public static final Pattern YzMz            = Pattern.compile("\\d{3,4}年\\d{1,2}月");                                                        // yyyy年M月
    public static final Pattern MzSzSz          = Pattern.compile("\\d{1,2}分\\d{1,2}秒\\d{1,3}毫秒");                                            // m分s秒S毫秒
    public static final Pattern YdMdD           = Pattern.compile("\\d{3,4}-\\d{1,2}-\\d{1,2}");                                                // yyyy-M-d
    public static final Pattern YsMsD           = Pattern.compile("\\d{3,4}/\\d{1,2}/\\d{1,2}");                                                // yyyy/M/d
    public static final Pattern YoMoD           = Pattern.compile("\\d{3,4}\\.\\d{1,2}\\.\\d{1,2}");                                            // yyyy.M.d
    public static final Pattern MzDzHzMz        = Pattern.compile("\\d{1,2}月\\d{1,2}日\\d{1,2}时\\d{1,2}分");                                   // M月d日H时m分
    public static final Pattern MzDzHoMz        = Pattern.compile("\\d{1,2}月\\d{1,2}日\\d{1,2}点\\d{1,2}分");                                   // M月d日H点m分
    public static final Pattern MdDHcMcS        = Pattern.compile("\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}");                              // M-d H:m:s
    public static final Pattern MsDHcMcS        = Pattern.compile("\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}");                              // M/d H:m:s
    public static final Pattern MoDHcMcS        = Pattern.compile("\\d{1,2}\\.\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}");                            // M.d H:m:s
    public static final Pattern YzMzDz          = Pattern.compile("\\d{3,4}年\\d{1,2}月\\d{1,2}日");                                             // yyyy年M月d日
    public static final Pattern HzMzSzSz        = Pattern.compile("\\d{1,2}时\\d{1,2}分\\d{1,2}秒\\d{1,3}毫秒");                                 // H时m分s秒S毫秒
    public static final Pattern HoMzSzSz        = Pattern.compile("\\d{1,2}点\\d{1,2}分\\d{1,2}秒\\d{1,3}毫秒");                                 // H点m分s秒S毫秒
    public static final Pattern YdMdDH          = Pattern.compile("\\d{3,4}-\\d{1,2}-\\d{1,2} \\d{1,2}");                                      // yyyy-M-d H
    public static final Pattern YsMsDH          = Pattern.compile("\\d{3,4}/\\d{1,2}/\\d{1,2} \\d{1,2}");                                      // yyyy/M/d H
    public static final Pattern YoMoDH          = Pattern.compile("\\d{3,4}\\.\\d{1,2}\\.\\d{1,2} \\d{1,2}");                                  // yyyy.M.d H
    public static final Pattern MzDzHzMzSz      = Pattern.compile("\\d{1,2}月\\d{1,2}日\\d{1,2}时\\d{1,2}分\\d{1,2}秒");                        // M月d日H时m分s秒
    public static final Pattern MzDzHoMzSz      = Pattern.compile("\\d{1,2}月\\d{1,2}日\\d{1,2}点\\d{1,2}分\\d{1,2}秒");                        // M月d日H点m分s秒
    public static final Pattern MdDHcMcSS       = Pattern.compile("\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}");                 // M-d H:m:s.S
    public static final Pattern MsDHcMcSS       = Pattern.compile("\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}");                 // M/d H:m:s.S
    public static final Pattern MoDHcMcSS       = Pattern.compile("\\d{1,2}\\.\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}");               // M.d H:m:s.S
    public static final Pattern YzMzDzHz        = Pattern.compile("\\d{3,4}年\\d{1,2}月\\d{1,2}日\\d{1,2}时");                                 // yyyy年M月d日H时
    public static final Pattern YzMzDzHo        = Pattern.compile("\\d{3,4}年\\d{1,2}月\\d{1,2}日\\d{1,2}点");                                 // yyyy年M月d日H点
    public static final Pattern YdMdDHcM        = Pattern.compile("\\d{3,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}");                            // yyyy-M-d H:m
    public static final Pattern YsMsDHcM        = Pattern.compile("\\d{3,4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}");                            // yyyy/M/d H:m
    public static final Pattern YoMoDHcM        = Pattern.compile("\\d{3,4}\\.\\d{1,2}\\.\\d{1,2} \\d{1,2}:\\d{1,2}");                        // yyyy.M.d H:m
    public static final Pattern MzDzHzMzSzSz    = Pattern.compile("\\d{1,2}月\\d{1,2}日\\d{1,2}时\\d{1,2}分\\d{1,2}秒\\d{1,3}毫秒");            // M月d日H时m分s秒S毫秒
    public static final Pattern MzDzHoMzSzSz    = Pattern.compile("\\d{1,2}月\\d{1,2}日\\d{1,2}点\\d{1,2}分\\d{1,2}秒\\d{1,3}毫秒");            // M月d日H点m分s秒S毫秒
    public static final Pattern YzMzDzHzMz      = Pattern.compile("\\d{3,4}年\\d{1,2}月\\d{1,2}日\\d{1,2}时\\d{1,2}分");                       // yyyy年M月d日H时m分
    public static final Pattern YzMzDzHoMz      = Pattern.compile("\\d{3,4}年\\d{1,2}月\\d{1,2}日\\d{1,2}点\\d{1,2}分");                       // yyyy年M月d日H点m分
    public static final Pattern YdMdDHcMcS      = Pattern.compile("\\d{3,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}");                  // yyyy-M-d H:m:s
    public static final Pattern YsMsDHcMcS      = Pattern.compile("\\d{3,4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}");                  // yyyy/M/d H:m:s
    public static final Pattern YoMoDHcMcS      = Pattern.compile("\\d{3,4}\\.\\d{1,2}\\.\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}");              // yyyy.M.d H:m:s
    public static final Pattern YzMzDzHzMzSz    = Pattern.compile("\\d{3,4}年\\d{1,2}月\\d{1,2}日\\d{1,2}时\\d{1,2}分\\d{1,2}秒");             // yyyy年M月d日H时m分s秒
    public static final Pattern YzMzDzHoMzSz    = Pattern.compile("\\d{3,4}年\\d{1,2}月\\d{1,2}日\\d{1,2}点\\d{1,2}分\\d{1,2}秒");             // yyyy年M月d日H点m分s秒
    public static final Pattern YdMdDHcMcSS     = Pattern.compile("\\d{3,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}");       // yyyy-M-d H:m:s.S
    public static final Pattern YsMsDHcMcSS     = Pattern.compile("\\d{3,4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}");       // yyyy/M/d H:m:s.S
    public static final Pattern YoMoDHcMcSS     = Pattern.compile("\\d{3,4}\\.\\d{1,2}\\.\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}");   // yyyy.M.d H:m:s.S
    public static final Pattern YzMzDzHzMzSzSz  = Pattern.compile("\\d{3,4}年\\d{1,2}月\\d{1,2}日\\d{1,2}时\\d{1,2}分\\d{1,2}秒\\d{1,3}毫秒");  // yyyy年M月d日H时m分s秒S毫秒
    public static final Pattern YzMzDzHoMzSzSz  = Pattern.compile("\\d{3,4}年\\d{1,2}月\\d{1,2}日\\d{1,2}点\\d{1,2}分\\d{1,2}秒\\d{1,3}毫秒");  // yyyy年M月d日H点m分s秒S毫秒
    public static final Pattern YMDTHMS         = Pattern.compile("\\d{8}T\\d{6}");                                                          // yyyyMMddTHHmmss
    public static final Pattern YMDTHMSS        = Pattern.compile("\\d{8}T\\d{9}");                                                          // yyyyMMddTHHmmssSSS
    public static final Pattern YdMdDTHcMcS     = Pattern.compile("\\d{3,4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}");                  // yyyy-M-dTH:m:s
    public static final Pattern YsMsDTHcMcS     = Pattern.compile("\\d{3,4}/\\d{1,2}/\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}");                  // yyyy/M/dTH:m:s
    public static final Pattern YoMoDTHcMcS     = Pattern.compile("\\d{3,4}\\.\\d{1,2}\\.\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}");              // yyyy.M.dTH:m:s
    public static final Pattern YdMdDTHcMcSS    = Pattern.compile("\\d{3,4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}");       // yyyy-M-dTH:m:s.S
    public static final Pattern YsMsDTHcMcSS    = Pattern.compile("\\d{3,4}/\\d{1,2}/\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}");       // yyyy/M/dTH:m:s.S
    public static final Pattern YoMoDTHcMcSS    = Pattern.compile("\\d{3,4}\\.\\d{1,2}\\.\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}");   // yyyy.M.dTH:m:s.S
    public static final Pattern YdMdDTHcMcSZ    = Pattern.compile("\\d{3,4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}Z");                  // yyyy-M-dTH:m:s
    public static final Pattern YsMsDTHcMcSZ    = Pattern.compile("\\d{3,4}/\\d{1,2}/\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}Z");                  // yyyy/M/dTH:m:s
    public static final Pattern YoMoDTHcMcSZ    = Pattern.compile("\\d{3,4}\\.\\d{1,2}\\.\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}Z");              // yyyy.M.dTH:m:s
    public static final Pattern YdMdDTHcMcSSZ   = Pattern.compile("\\d{3,4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}Z");       // yyyy-M-dTH:m:s.S
    public static final Pattern YsMsDTHcMcSSZ   = Pattern.compile("\\d{3,4}/\\d{1,2}/\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}Z");       // yyyy/M/dTH:m:s.S
    public static final Pattern YoMoDTHcMcSSZ   = Pattern.compile("\\d{3,4}\\.\\d{1,2}\\.\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}Z");   // yyyy.M.dTH:m:s.S
    public static final Pattern YdMdDHcMcSZo    = Pattern.compile("\\d{3,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2} \\[.+ .\\d{2}:\\d{2}]");                  // yyyy-M-d H:m:s [VV xxx]
    public static final Pattern YsMsDHcMcSZo    = Pattern.compile("\\d{3,4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2} \\[.+ .\\d{2}:\\d{2}]");                  // yyyy/M/d H:m:s
    public static final Pattern YoMoDHcMcSZo    = Pattern.compile("\\d{3,4}\\.\\d{1,2}\\.\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2} \\[.+ .\\d{2}:\\d{2}]");              // yyyy.M.d H:m:s
    public static final Pattern YdMdDHcMcSSZo   = Pattern.compile("\\d{3,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3} \\[.+ .\\d{2}:\\d{2}]");       // yyyy-M-d H:m:s.S
    public static final Pattern YsMsDHcMcSSZo   = Pattern.compile("\\d{3,4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3} \\[.+ .\\d{2}:\\d{2}]");       // yyyy/M/d H:m:s.S
    public static final Pattern YoMoDHcMcSSZo   = Pattern.compile("\\d{3,4}\\.\\d{1,2}\\.\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3} \\[.+ .\\d{2}:\\d{2}]");   // yyyy.M.d H:m:s.S
    public static final Pattern YdMdDHcMcSOs    = Pattern.compile("\\d{3,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2} \\[.\\d{2}:\\d{2}]");                  // yyyy-M-d H:m:s
    public static final Pattern YsMsDHcMcSOs    = Pattern.compile("\\d{3,4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2} \\[.\\d{2}:\\d{2}]");                  // yyyy/M/d H:m:s
    public static final Pattern YoMoDHcMcSOs    = Pattern.compile("\\d{3,4}\\.\\d{1,2}\\.\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2} \\[.\\d{2}:\\d{2}]");              // yyyy.M.d H:m:s
    public static final Pattern YdMdDHcMcSSOs   = Pattern.compile("\\d{3,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3} \\[.\\d{2}:\\d{2}]");       // yyyy-M-d H:m:s.S
    public static final Pattern YsMsDHcMcSSOs   = Pattern.compile("\\d{3,4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3} \\[.\\d{2}:\\d{2}]");       // yyyy/M/d H:m:s.S
    public static final Pattern YoMoDHcMcSSOs   = Pattern.compile("\\d{3,4}\\.\\d{1,2}\\.\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3} \\[.\\d{2}:\\d{2}]");   // yyyy.M.d H:m:s.S

    public static final Pattern HzMzSzSSSz        = Pattern.compile("\\d{1,2}时\\d{1,2}分\\d{1,2}秒\\d{3}毫秒");                                 // H时m分s秒S毫秒
    public static final Pattern HoMzSzSSSz        = Pattern.compile("\\d{1,2}点\\d{1,2}分\\d{1,2}秒\\d{3}毫秒");                                 // H点m分s秒S毫秒
    public static final Pattern YzMzDzHzMzSzSSSz  = Pattern.compile("\\d{3,4}年\\d{1,2}月\\d{1,2}日\\d{1,2}时\\d{1,2}分\\d{1,2}秒\\d{3}毫秒");  // yyyy年M月d日H时m分s秒S毫秒
    public static final Pattern YzMzDzHoMzSzSSSz  = Pattern.compile("\\d{3,4}年\\d{1,2}月\\d{1,2}日\\d{1,2}点\\d{1,2}分\\d{1,2}秒\\d{3}毫秒");  // yyyy年M月d日H点m分s秒S毫秒
    /*
     * 通用时间格式化（FORMAT）字符串
     */
    public static final String FM_YM                = "yyyyMM";
    public static final String FM_YMD               = "yyyyMMdd";
    public static final String FM_YMDH              = "yyyyMMddHH";
    public static final String FM_YMDHM             = "yyyyMMddHHmm";
    public static final String FM_YMDHMS            = "yyyyMMddHHmmss";
    public static final String FM_YMDHMSS           = "yyyyMMddHHmmssSSS";
    public static final String FM_YMDTHMS           = "yyyyMMdd'T'HHmmss";
    public static final String FM_YMDTHMSS          = "yyyyMMdd'T'HHmmssSSS";
    /*
     * 专用于 SimpleDateFormat 的时间格式化（FORMAT）字符串
     */
    public static final String FM_Y                 = "yyyy";
    public static final String FM_MdD               = "MM-dd";
    public static final String FM_MsD               = "MM/dd";
    public static final String FM_MoD               = "MM.dd";
    public static final String FM_HcM               = "HH:mm";
    public static final String FM_Yz                = "yyyy年";
    public static final String FM_MzDz              = "MM月dd日";
    public static final String FM_MzSz              = "mm分ss秒";
    public static final String FM_HzMz              = "HH时mm分";
    public static final String FM_HoMz              = "HH点mm分";
    public static final String FM_McSS              = "mm:ss.SSS";
    public static final String FM_HcMcS             = "HH:mm:ss";
    public static final String FM_MdDH              = "MM-dd HH";
    public static final String FM_MsDH              = "MM/dd HH";
    public static final String FM_MoDH              = "MM.dd HH";
    public static final String FM_YdM               = "yyyy-MM";
    public static final String FM_YsM               = "yyyy/MM";
    public static final String FM_YoM               = "yyyy.MM";
    public static final String FM_HzMzSz            = "HH时mm分ss秒";
    public static final String FM_HoMzSz            = "HH点mm分ss秒";
    public static final String FM_MzDzHz            = "MM月dd日HH时";
    public static final String FM_MzDzHo            = "MM月dd日HH点";
    public static final String FM_HcMcSS            = "HH:mm:ss.SSS";
    public static final String FM_MdDHcM            = "MM-dd HH:mm";
    public static final String FM_MsDHcM            = "MM/dd HH:mm";
    public static final String FM_MoDHcM            = "MM.dd HH:mm";
    public static final String FM_YzMz              = "yyyy年MM月";
    public static final String FM_MzSzSz            = "mm分ss秒SSS毫秒";
    public static final String FM_YdMdD             = "yyyy-MM-dd";
    public static final String FM_YsMsD             = "yyyy/MM/dd";
    public static final String FM_YoMoD             = "yyyy.MM.dd";
    public static final String FM_MzDzHzMz          = "MM月dd日HH时mm分";
    public static final String FM_MzDzHoMz          = "MM月dd日HH点mm分";
    public static final String FM_MdDHcMcS          = "MM-dd HH:mm:ss";
    public static final String FM_MsDHcMcS          = "MM/dd HH:mm:ss";
    public static final String FM_MoDHcMcS          = "MM.dd HH:mm:ss";
    public static final String FM_YzMzDz            = "yyyy年MM月dd日";
    public static final String FM_HzMzSzSz          = "HH时mm分ss秒SSS毫秒";
    public static final String FM_HoMzSzSz          = "HH点mm分ss秒SSS毫秒";
    public static final String FM_YdMdDH            = "yyyy-MM-dd HH";
    public static final String FM_YsMsDH            = "yyyy/MM/dd HH";
    public static final String FM_YoMoDH            = "yyyy.MM.dd HH";
    public static final String FM_MzDzHzMzSz        = "MM月dd日HH时mm分ss秒";
    public static final String FM_MzDzHoMzSz        = "MM月dd日HH点mm分ss秒";
    public static final String FM_MdDHcMcSS         = "MM-dd HH:mm:ss.SSS";
    public static final String FM_MsDHcMcSS         = "MM/dd HH:mm:ss.SSS";
    public static final String FM_MoDHcMcSS         = "MM.dd HH:mm:ss.SSS";
    public static final String FM_YzMzDzHz          = "yyyy年MM月dd日HH时";
    public static final String FM_YzMzDzHo          = "yyyy年MM月dd日HH点";
    public static final String FM_YdMdDHcM          = "yyyy-MM-dd HH:mm";
    public static final String FM_YsMsDHcM          = "yyyy/MM/dd HH:mm";
    public static final String FM_YoMoDHcM          = "yyyy.MM.dd HH:mm";
    public static final String FM_MzDzHzMzSzSz      = "MM月dd日HH时mm分ss秒SSS毫秒";
    public static final String FM_MzDzHoMzSzSz      = "MM月dd日HH点mm分ss秒SSS毫秒";
    public static final String FM_YzMzDzHzMz        = "yyyy年MM月dd日HH时mm分";
    public static final String FM_YzMzDzHoMz        = "yyyy年MM月dd日HH点mm分";
    public static final String FM_YdMdDHcMcS        = "yyyy-MM-dd HH:mm:ss";
    public static final String FM_YsMsDHcMcS        = "yyyy/MM/dd HH:mm:ss";
    public static final String FM_YoMoDHcMcS        = "yyyy.MM.dd HH:mm:ss";
    public static final String FM_YzMzDzHzMzSz      = "yyyy年MM月dd日HH时mm分ss秒";
    public static final String FM_YzMzDzHoMzSz      = "yyyy年MM月dd日HH点mm分ss秒";
    public static final String FM_YdMdDHcMcSS       = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FM_YsMsDHcMcSS       = "yyyy/MM/dd HH:mm:ss.SSS";
    public static final String FM_YoMoDHcMcSS       = "yyyy.MM.dd HH:mm:ss.SSS";
    public static final String FM_YzMzDzHzMzSzSz    = "yyyy年MM月dd日HH时mm分ss秒SSS毫秒";
    public static final String FM_YzMzDzHoMzSzSz    = "yyyy年MM月dd日HH点mm分ss秒SSS毫秒";
    public static final String FM_YdMdDTHcMcS       = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FM_YsMsDTHcMcS       = "yyyy/MM/dd'T'HH:mm:ss";
    public static final String FM_YoMoDTHcMcS       = "yyyy.MM.dd'T'HH:mm:ss";
    public static final String FM_YdMdDTHcMcSS      = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String FM_YsMsDTHcMcSS      = "yyyy/MM/dd'T'HH:mm:ss.SSS";
    public static final String FM_YoMoDTHcMcSS      = "yyyy.MM.dd'T'HH:mm:ss.SSS";
    /*
     * 专用于 DateTimeFormatter 的时间格式化（FORMAT）字符串
     */
    public static final String FM_DTF_Y                 = "y";
    public static final String FM_DTF_Yz                = "y年";
    public static final String FM_DTF_YdM               = "y-M";
    public static final String FM_DTF_YsM               = "y/M";
    public static final String FM_DTF_YoM               = "y.M";
    public static final String FM_DTF_YzMz              = "y年M月";
    public static final String FM_DTF_YdMdD             = "y-M-d";
    public static final String FM_DTF_YsMsD             = "y/M/d";
    public static final String FM_DTF_YoMoD             = "y.M.d";
    public static final String FM_DTF_YzMzDz            = "y年M月d日";
    public static final String FM_DTF_YdMdDH            = "y-M-d H";
    public static final String FM_DTF_YsMsDH            = "y/M/d H";
    public static final String FM_DTF_YoMoDH            = "y.M.d H";
    public static final String FM_DTF_YzMzDzHz          = "y年M月d日H时";
    public static final String FM_DTF_YzMzDzHo          = "y年M月d日H点";
    public static final String FM_DTF_YdMdDHcM          = "y-M-d H:m";
    public static final String FM_DTF_YsMsDHcM          = "y/M/d H:m";
    public static final String FM_DTF_YoMoDHcM          = "y.M.d H:m";
    public static final String FM_DTF_YzMzDzHzMz        = "y年M月d日H时m分";
    public static final String FM_DTF_YzMzDzHoMz        = "y年M月d日H点m分";
    public static final String FM_DTF_YdMdDHcMcS        = "y-M-d H:m:s";
    public static final String FM_DTF_YsMsDHcMcS        = "y/M/d H:m:s";
    public static final String FM_DTF_YoMoDHcMcS        = "y.M.d H:m:s";
    public static final String FM_DTF_YzMzDzHzMzSz      = "y年M月d日H时m分s秒";
    public static final String FM_DTF_YzMzDzHoMzSz      = "y年M月d日H点m分s秒";
    public static final String FM_DTF_YdMdDHcMcSS       = "y-M-d H:m:s[.SSS][.SS][.S]";
    public static final String FM_DTF_YsMsDHcMcSS       = "y/M/d H:m:s[.SSS][.SS][.S]";
    public static final String FM_DTF_YoMoDHcMcSS       = "y.M.d H:m:s[.SSS][.SS][.S]";
    public static final String FM_DTF_YzMzDzHzMzSzSz    = "y年M月d日H时m分s秒[SSS毫秒]";  // 只能接收3位数的毫秒值，1位数的毫秒识别有问题：如 6毫秒，会被解析成 600毫秒；6毫秒应该写成 006 毫秒
    public static final String FM_DTF_YzMzDzHoMzSzSz    = "y年M月d日H点m分s秒[SSS毫秒]";  // 只能接收3位数的毫秒值，1位数的毫秒识别有问题：如 6毫秒，会被解析成 600毫秒；6毫秒应该写成 006 毫秒
    public static final String FM_DTF_YdMdDTHcMcS       = "y-M-d'T'H:m:s";
    public static final String FM_DTF_YsMsDTHcMcS       = "y/M/d'T'H:m:s";
    public static final String FM_DTF_YoMoDTHcMcS       = "y.M.d'T'H:m:s";
    public static final String FM_DTF_YdMdDTHcMcSS      = "y-M-d'T'H:m:s[.SSS][.SS][.S]";
    public static final String FM_DTF_YsMsDTHcMcSS      = "y/M/d'T'H:m:s[.SSS][.SS][.S]";
    public static final String FM_DTF_YoMoDTHcMcSS      = "y.M.d'T'H:m:s[.SSS][.SS][.S]";
    public static final String FM_DTF_YdMdDTHcMcSZ      = "y-M-d'T'H:m:sVV";
    public static final String FM_DTF_YsMsDTHcMcSZ      = "y/M/d'T'H:m:sVV";
    public static final String FM_DTF_YoMoDTHcMcSZ      = "y.M.d'T'H:m:sVV";
    public static final String FM_DTF_YdMdDTHcMcSSZ     = "y-M-d'T'H:m:s[.SSS][.SS][.S]VV";
    public static final String FM_DTF_YsMsDTHcMcSSZ     = "y/M/d'T'H:m:s[.SSS][.SS][.S]VV";
    public static final String FM_DTF_YoMoDTHcMcSSZ     = "y.M.d'T'H:m:s[.SSS][.SS][.S]VV";

    public static final String FM_DTF_YdMdDHcMcSZo      = "y-M-d H:m:s '['VV xxx']'";
    public static final String FM_DTF_YsMsDHcMcSZo      = "y/M/d H:m:s '['VV xxx']'";
    public static final String FM_DTF_YoMoDHcMcSZo      = "y.M.d H:m:s '['VV xxx']'";
    public static final String FM_DTF_YdMdDHcMcSSZo     = "y-M-d H:m:s[.SSS][.SS][.S] '['VV xxx']'";
    public static final String FM_DTF_YsMsDHcMcSSZo     = "y/M/d H:m:s[.SSS][.SS][.S] '['VV xxx']'";
    public static final String FM_DTF_YoMoDHcMcSSZo     = "y.M.d H:m:s[.SSS][.SS][.S] '['VV xxx']'";
    public static final String FM_DTF_YdMdDHcMcSOs      = "y-M-d H:m:s '['xxx']'";
    public static final String FM_DTF_YsMsDHcMcSOs      = "y/M/d H:m:s '['xxx']'";
    public static final String FM_DTF_YoMoDHcMcSOs      = "y.M.d H:m:s '['xxx']'";
    public static final String FM_DTF_YdMdDHcMcSSOs     = "y-M-d H:m:s[.SSS][.SS][.S] '['xxx']'";
    public static final String FM_DTF_YsMsDHcMcSSOs     = "y/M/d H:m:s[.SSS][.SS][.S] '['xxx']'";
    public static final String FM_DTF_YoMoDHcMcSSOs     = "y.M.d H:m:s[.SSS][.SS][.S] '['xxx']'";

    public static final String FM_DTF_HcM               = "H:m";
    public static final String FM_DTF_HzMz              = "H时m分";
    public static final String FM_DTF_HoMz              = "H点m分";
    public static final String FM_DTF_HcMcS             = "H:m:s";
    public static final String FM_DTF_HzMzSz            = "H时m分s秒";
    public static final String FM_DTF_HoMzSz            = "H点m分s秒";
    public static final String FM_DTF_HcMcSS            = "H:m:s[.SSS][.SS][.S]";
    public static final String FM_DTF_HzMzSzSz          = "H时m分s秒[SSS毫秒]";  // 只能接收3位数的毫秒值，1位数的毫秒识别有问题：如 6毫秒，会被解析成 600毫秒；6毫秒应该写成 006 毫秒
    public static final String FM_DTF_HoMzSzSz          = "H点m分s秒[SSS毫秒]";  // 只能接收3位数的毫秒值，1位数的毫秒识别有问题：如 6毫秒，会被解析成 600毫秒；6毫秒应该写成 006 毫秒
    public static final String FM_DTF_MzSz              = "m分s秒";
    public static final String FM_DTF_MzSzSz            = "m分s秒[SSS毫秒]";  // 只能接收3位数的毫秒值，1位数的毫秒识别有问题：如 6毫秒，会被解析成 600毫秒；6毫秒应该写成 006 毫秒

    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     ***********************        DateTimeFormatter 初始化        **********************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/
    /*
     * DateTimeFormatter for Year
     */
    public static final DateTimeFormatter DTF_Y = buildDTF(FM_DTF_Y);
    public static final DateTimeFormatter DTF_Y_ZH = buildDTF(FM_DTF_Yz);
    /*
     * DateTimeFormatter for Year, Month
     */
    public static final DateTimeFormatter DTF_YM = buildDTF(FM_YM);
    public static final DateTimeFormatter DTF_YM_DASH = buildDTF(FM_DTF_YdM);
    public static final DateTimeFormatter DTF_YM_SLASH = buildDTF(FM_DTF_YsM);
    public static final DateTimeFormatter DTF_YM_DOT = buildDTF(FM_DTF_YoM);
    public static final DateTimeFormatter DTF_YM_ZH = buildDTF(FM_DTF_YzMz);
    /*
     * DateTimeFormatter for Year, Month, Day
     */
    public static final DateTimeFormatter DTF_YMD = buildDTF(FM_YMD);
    public static final DateTimeFormatter DTF_YMD_DASH = buildDTF(FM_DTF_YdMdD);
    public static final DateTimeFormatter DTF_YMD_SLASH = buildDTF(FM_DTF_YsMsD);
    public static final DateTimeFormatter DTF_YMD_DOT = buildDTF(FM_DTF_YoMoD);
    public static final DateTimeFormatter DTF_YMD_ZH = buildDTF(FM_DTF_YzMzDz);
    /*
     * DateTimeFormatter for Year, Month, Day, Hour
     */
    public static final DateTimeFormatter DTF_YMDH = buildDTF(FM_YMDH);
    public static final DateTimeFormatter DTF_YMDH_DASH = buildDTF(FM_DTF_YdMdDH);
    public static final DateTimeFormatter DTF_YMDH_SLASH = buildDTF(FM_DTF_YsMsDH);
    public static final DateTimeFormatter DTF_YMDH_DOT = buildDTF(FM_DTF_YoMoDH);
    public static final DateTimeFormatter DTF_YMDH_ZHS = buildDTF(FM_DTF_YzMzDzHz);
    public static final DateTimeFormatter DTF_YMDH_ZHD = buildDTF(FM_DTF_YzMzDzHo);
    /*
     * DateTimeFormatter for Year, Month, Day, Hour, Minute
     */
    public static final DateTimeFormatter DTF_YMDHM = buildDTF(FM_YMDHM);
    public static final DateTimeFormatter DTF_YMDHM_DASH = buildDTF(FM_DTF_YdMdDHcM);
    public static final DateTimeFormatter DTF_YMDHM_SLASH = buildDTF(FM_DTF_YsMsDHcM);
    public static final DateTimeFormatter DTF_YMDHM_DOT = buildDTF(FM_DTF_YoMoDHcM);
    public static final DateTimeFormatter DTF_YMDHM_ZHS = buildDTF(FM_DTF_YzMzDzHzMz);
    public static final DateTimeFormatter DTF_YMDHM_ZHD = buildDTF(FM_DTF_YzMzDzHoMz);
    /*
     * DateTimeFormatter for Year, Month, Day, Hour, Minute, Second
     */
    public static final DateTimeFormatter DTF_YMDHMS = buildDTF(FM_YMDHMS);
    public static final DateTimeFormatter DTF_YMDHMS_DASH = buildDTF(FM_DTF_YdMdDHcMcS);
    public static final DateTimeFormatter DTF_YMDHMS_SLASH = buildDTF(FM_DTF_YsMsDHcMcS);
    public static final DateTimeFormatter DTF_YMDHMS_DOT = buildDTF(FM_DTF_YoMoDHcMcS);
    public static final DateTimeFormatter DTF_YMDHMS_ZHS = buildDTF(FM_DTF_YzMzDzHzMzSz);
    public static final DateTimeFormatter DTF_YMDHMS_ZHD = buildDTF(FM_DTF_YzMzDzHoMzSz);
    public static final DateTimeFormatter DTF_YMDHMS_T = buildDTF(FM_YMDTHMS);
    public static final DateTimeFormatter DTF_YMDHMS_DASH_T = buildDTF(FM_DTF_YdMdDTHcMcS);
    public static final DateTimeFormatter DTF_YMDHMS_SLASH_T = buildDTF(FM_DTF_YsMsDTHcMcS);
    public static final DateTimeFormatter DTF_YMDHMS_DOT_T = buildDTF(FM_DTF_YoMoDTHcMcS);
    public static final DateTimeFormatter DTF_YMDHMS_DASH_TZ = buildDTF(FM_DTF_YdMdDTHcMcSZ);
    public static final DateTimeFormatter DTF_YMDHMS_SLASH_TZ = buildDTF(FM_DTF_YsMsDTHcMcSZ);
    public static final DateTimeFormatter DTF_YMDHMS_DOT_TZ = buildDTF(FM_DTF_YoMoDTHcMcSZ);

    /*
     * DateTimeFormatter for Year, Month, Day, Hour, Minute, Second, Milli
     */
    public static final DateTimeFormatter DTF_YMDHMSS = buildDTF(FM_YMDHMSS);
    public static final DateTimeFormatter DTF_YMDHMSS_DASH = buildDTF(FM_DTF_YdMdDHcMcSS);
    public static final DateTimeFormatter DTF_YMDHMSS_SLASH = buildDTF(FM_DTF_YsMsDHcMcSS);
    public static final DateTimeFormatter DTF_YMDHMSS_DOT = buildDTF(FM_DTF_YoMoDHcMcSS);
    public static final DateTimeFormatter DTF_YMDHMSS_ZHS = buildDTF(FM_DTF_YzMzDzHzMzSzSz);
    public static final DateTimeFormatter DTF_YMDHMSS_ZHD = buildDTF(FM_DTF_YzMzDzHoMzSzSz);
    public static final DateTimeFormatter DTF_YMDHMSS_T = buildDTF(FM_YMDTHMSS);
    public static final DateTimeFormatter DTF_YMDHMSS_DASH_T = buildDTF(FM_DTF_YdMdDTHcMcSS);
    public static final DateTimeFormatter DTF_YMDHMSS_SLASH_T = buildDTF(FM_DTF_YsMsDTHcMcSS);
    public static final DateTimeFormatter DTF_YMDHMSS_DOT_T = buildDTF(FM_DTF_YoMoDTHcMcSS);
    public static final DateTimeFormatter DTF_YMDHMSS_DASH_TZ = buildDTF(FM_DTF_YdMdDTHcMcSSZ);
    public static final DateTimeFormatter DTF_YMDHMSS_SLASH_TZ = buildDTF(FM_DTF_YsMsDTHcMcSSZ);
    public static final DateTimeFormatter DTF_YMDHMSS_DOT_TZ = buildDTF(FM_DTF_YoMoDTHcMcSSZ);

    public static final DateTimeFormatter DTF_HM = buildDTF(FM_DTF_HcM);
    public static final DateTimeFormatter DTF_HM_ZHS = buildDTF(FM_DTF_HzMz);
    public static final DateTimeFormatter DTF_HM_ZHD = buildDTF(FM_DTF_HoMz);
    public static final DateTimeFormatter DTF_HMS = buildDTF(FM_DTF_HcMcS);
    public static final DateTimeFormatter DTF_HMS_ZHS = buildDTF(FM_DTF_HzMzSz);
    public static final DateTimeFormatter DTF_HMS_ZHD = buildDTF(FM_DTF_HoMzSz);
    public static final DateTimeFormatter DTF_HMSS = buildDTF(FM_DTF_HcMcSS);
    public static final DateTimeFormatter DTF_HMSS_ZHS = buildDTF(FM_DTF_HzMzSzSz);
    public static final DateTimeFormatter DTF_HMSS_ZHD = buildDTF(FM_DTF_HoMzSzSz);
    public static final DateTimeFormatter DTF_MS_ZHD = buildDTF(FM_DTF_MzSz);
    public static final DateTimeFormatter DTF_MSS_ZHD = buildDTF(FM_DTF_MzSzSz);

    /*
     * DateTimeFormatter with zone or zone offset
     */
    public static final DateTimeFormatter DTF_YMDHMS_DASH_ZO = buildDTF(FM_DTF_YdMdDHcMcSZo);
    public static final DateTimeFormatter DTF_YMDHMS_SLASH_ZO = buildDTF(FM_DTF_YsMsDHcMcSZo);
    public static final DateTimeFormatter DTF_YMDHMS_DOT_ZO = buildDTF(FM_DTF_YoMoDHcMcSZo);
    public static final DateTimeFormatter DTF_YMDHMSS_DASH_ZO = buildDTF(FM_DTF_YdMdDHcMcSSZo);
    public static final DateTimeFormatter DTF_YMDHMSS_SLASH_ZO = buildDTF(FM_DTF_YsMsDHcMcSSZo);
    public static final DateTimeFormatter DTF_YMDHMSS_DOT_ZO = buildDTF(FM_DTF_YoMoDHcMcSSZo);
    public static final DateTimeFormatter DTF_YMDHMS_DASH_OS = buildDTF(FM_DTF_YdMdDHcMcSOs);
    public static final DateTimeFormatter DTF_YMDHMS_SLASH_OS = buildDTF(FM_DTF_YsMsDHcMcSOs);
    public static final DateTimeFormatter DTF_YMDHMS_DOT_OS = buildDTF(FM_DTF_YoMoDHcMcSOs);
    public static final DateTimeFormatter DTF_YMDHMSS_DASH_OS = buildDTF(FM_DTF_YdMdDHcMcSSOs);
    public static final DateTimeFormatter DTF_YMDHMSS_SLASH_OS = buildDTF(FM_DTF_YsMsDHcMcSSOs);
    public static final DateTimeFormatter DTF_YMDHMSS_DOT_OS = buildDTF(FM_DTF_YoMoDHcMcSSOs);


    private static final Map<String, DateTimeFormatter> DTF_MAP = new HashMap<>();

    static {
        DTF_MAP.put(FM_DTF_Y, DTF_Y);
        DTF_MAP.put(FM_DTF_Yz, DTF_Y_ZH);
        DTF_MAP.put(FM_Y, DTF_Y);
        DTF_MAP.put(FM_Yz, DTF_Y_ZH);

        DTF_MAP.put(FM_YM, DTF_YM);
        DTF_MAP.put(FM_DTF_YdM, DTF_YM_DASH);
        DTF_MAP.put(FM_DTF_YsM, DTF_YM_SLASH);
        DTF_MAP.put(FM_DTF_YoM, DTF_YM_DOT);
        DTF_MAP.put(FM_DTF_YzMz, DTF_YM_ZH);
        DTF_MAP.put(FM_YdM, DTF_YM_DASH);
        DTF_MAP.put(FM_YsM, DTF_YM_SLASH);
        DTF_MAP.put(FM_YoM, DTF_YM_DOT);
        DTF_MAP.put(FM_YzMz, DTF_YM_ZH);

        DTF_MAP.put(FM_YMD, DTF_YMD);
        DTF_MAP.put(FM_DTF_YdMdD, DTF_YMD_DASH);
        DTF_MAP.put(FM_DTF_YsMsD, DTF_YMD_SLASH);
        DTF_MAP.put(FM_DTF_YoMoD, DTF_YMD_DOT);
        DTF_MAP.put(FM_DTF_YzMzDz, DTF_YMD_ZH);
        DTF_MAP.put(FM_YdMdD, DTF_YMD_DASH);
        DTF_MAP.put(FM_YsMsD, DTF_YMD_SLASH);
        DTF_MAP.put(FM_YoMoD, DTF_YMD_DOT);
        DTF_MAP.put(FM_YzMzDz, DTF_YMD_ZH);

        DTF_MAP.put(FM_YMDH, DTF_YMDH);
        DTF_MAP.put(FM_DTF_YdMdDH, DTF_YMDH_DASH);
        DTF_MAP.put(FM_DTF_YsMsDH, DTF_YMDH_SLASH);
        DTF_MAP.put(FM_DTF_YoMoDH, DTF_YMDH_DOT);
        DTF_MAP.put(FM_DTF_YzMzDzHz, DTF_YMDH_ZHS);
        DTF_MAP.put(FM_DTF_YzMzDzHo, DTF_YMDH_ZHD);
        DTF_MAP.put(FM_YdMdDH, DTF_YMDH_DASH);
        DTF_MAP.put(FM_YsMsDH, DTF_YMDH_SLASH);
        DTF_MAP.put(FM_YoMoDH, DTF_YMDH_DOT);
        DTF_MAP.put(FM_YzMzDzHz, DTF_YMDH_ZHS);
        DTF_MAP.put(FM_YzMzDzHo, DTF_YMDH_ZHD);

        DTF_MAP.put(FM_YMDHM, DTF_YMDHM);
        DTF_MAP.put(FM_DTF_YdMdDHcM, DTF_YMDHM_DASH);
        DTF_MAP.put(FM_DTF_YsMsDHcM, DTF_YMDHM_SLASH);
        DTF_MAP.put(FM_DTF_YoMoDHcM, DTF_YMDHM_DOT);
        DTF_MAP.put(FM_DTF_YzMzDzHzMz, DTF_YMDHM_ZHS);
        DTF_MAP.put(FM_DTF_YzMzDzHoMz, DTF_YMDHM_ZHD);
        DTF_MAP.put(FM_YdMdDHcM, DTF_YMDHM_DASH);
        DTF_MAP.put(FM_YsMsDHcM, DTF_YMDHM_SLASH);
        DTF_MAP.put(FM_YoMoDHcM, DTF_YMDHM_DOT);
        DTF_MAP.put(FM_YzMzDzHzMz, DTF_YMDHM_ZHS);
        DTF_MAP.put(FM_YzMzDzHoMz, DTF_YMDHM_ZHD);

        DTF_MAP.put(FM_YMDHMS, DTF_YMDHMS);
        DTF_MAP.put(FM_DTF_YdMdDHcMcS, DTF_YMDHMS_DASH);
        DTF_MAP.put(FM_DTF_YsMsDHcMcS, DTF_YMDHMS_SLASH);
        DTF_MAP.put(FM_DTF_YoMoDHcMcS, DTF_YMDHMS_DOT);
        DTF_MAP.put(FM_DTF_YzMzDzHzMzSz, DTF_YMDHMS_ZHS);
        DTF_MAP.put(FM_DTF_YzMzDzHoMzSz, DTF_YMDHMS_ZHD);
        DTF_MAP.put(FM_YdMdDHcMcS, DTF_YMDHMS_DASH);
        DTF_MAP.put(FM_YsMsDHcMcS, DTF_YMDHMS_SLASH);
        DTF_MAP.put(FM_YoMoDHcMcS, DTF_YMDHMS_DOT);
        DTF_MAP.put(FM_YzMzDzHzMzSz, DTF_YMDHMS_ZHS);
        DTF_MAP.put(FM_YzMzDzHoMzSz, DTF_YMDHMS_ZHD);
        DTF_MAP.put(FM_YMDTHMS, DTF_YMDHMS_T);
        DTF_MAP.put(FM_DTF_YdMdDTHcMcS, DTF_YMDHMS_DASH_T);
        DTF_MAP.put(FM_DTF_YsMsDTHcMcS, DTF_YMDHMS_SLASH_T);
        DTF_MAP.put(FM_DTF_YoMoDTHcMcS, DTF_YMDHMS_DOT_T);
        DTF_MAP.put(FM_YdMdDTHcMcS, DTF_YMDHMS_DASH_T);
        DTF_MAP.put(FM_YsMsDTHcMcS, DTF_YMDHMS_SLASH_T);
        DTF_MAP.put(FM_YoMoDTHcMcS, DTF_YMDHMS_DOT_T);
        DTF_MAP.put(FM_DTF_YdMdDTHcMcSZ, DTF_YMDHMS_DASH_TZ);
        DTF_MAP.put(FM_DTF_YsMsDTHcMcSZ, DTF_YMDHMS_SLASH_TZ);
        DTF_MAP.put(FM_DTF_YoMoDTHcMcSZ, DTF_YMDHMS_DOT_TZ);

        DTF_MAP.put(FM_YMDHMSS, DTF_YMDHMSS);
        DTF_MAP.put(FM_DTF_YdMdDHcMcSS, DTF_YMDHMSS_DASH);
        DTF_MAP.put(FM_DTF_YsMsDHcMcSS, DTF_YMDHMSS_SLASH);
        DTF_MAP.put(FM_DTF_YoMoDHcMcSS, DTF_YMDHMSS_DOT);
        DTF_MAP.put(FM_DTF_YzMzDzHzMzSzSz, DTF_YMDHMSS_ZHS);
        DTF_MAP.put(FM_DTF_YzMzDzHoMzSzSz, DTF_YMDHMSS_ZHD);
        DTF_MAP.put(FM_YdMdDHcMcSS, DTF_YMDHMSS_DASH);
        DTF_MAP.put(FM_YsMsDHcMcSS, DTF_YMDHMSS_SLASH);
        DTF_MAP.put(FM_YoMoDHcMcSS, DTF_YMDHMSS_DOT);
        DTF_MAP.put(FM_YzMzDzHzMzSzSz, DTF_YMDHMSS_ZHS);
        DTF_MAP.put(FM_YzMzDzHoMzSzSz, DTF_YMDHMSS_ZHD);
        DTF_MAP.put(FM_YMDTHMSS, DTF_YMDHMSS_T);
        DTF_MAP.put(FM_DTF_YdMdDTHcMcSS, DTF_YMDHMSS_DASH_T);
        DTF_MAP.put(FM_DTF_YsMsDTHcMcSS, DTF_YMDHMSS_SLASH_T);
        DTF_MAP.put(FM_DTF_YoMoDTHcMcSS, DTF_YMDHMSS_DOT_T);
        DTF_MAP.put(FM_YdMdDTHcMcSS, DTF_YMDHMSS_DASH_T);
        DTF_MAP.put(FM_YsMsDTHcMcSS, DTF_YMDHMSS_SLASH_T);
        DTF_MAP.put(FM_YoMoDTHcMcSS, DTF_YMDHMSS_DOT_T);
        DTF_MAP.put(FM_DTF_YdMdDTHcMcSSZ, DTF_YMDHMSS_DASH_TZ);
        DTF_MAP.put(FM_DTF_YsMsDTHcMcSSZ, DTF_YMDHMSS_SLASH_TZ);
        DTF_MAP.put(FM_DTF_YoMoDTHcMcSSZ, DTF_YMDHMSS_DOT_TZ);

        DTF_MAP.put(FM_DTF_HcM, DTF_HM);
        DTF_MAP.put(FM_DTF_HzMz, DTF_HM_ZHS);
        DTF_MAP.put(FM_DTF_HoMz, DTF_HM_ZHD);
        DTF_MAP.put(FM_DTF_HcMcS, DTF_HMS);
        DTF_MAP.put(FM_DTF_HzMzSz, DTF_HMS_ZHS);
        DTF_MAP.put(FM_DTF_HoMzSz, DTF_HMS_ZHD);
        DTF_MAP.put(FM_DTF_HcMcSS, DTF_HMSS);
        DTF_MAP.put(FM_DTF_HzMzSzSz, DTF_HMSS_ZHS);
        DTF_MAP.put(FM_DTF_HoMzSzSz, DTF_HMSS_ZHD);
        DTF_MAP.put(FM_HcM, DTF_HM);
        DTF_MAP.put(FM_HzMz, DTF_HM_ZHS);
        DTF_MAP.put(FM_HoMz, DTF_HM_ZHD);
        DTF_MAP.put(FM_HcMcS, DTF_HMS);
        DTF_MAP.put(FM_HzMzSz, DTF_HMS_ZHS);
        DTF_MAP.put(FM_HoMzSz, DTF_HMS_ZHD);
        DTF_MAP.put(FM_HcMcSS, DTF_HMSS);
        DTF_MAP.put(FM_HzMzSzSz, DTF_HMSS_ZHS);
        DTF_MAP.put(FM_HoMzSzSz, DTF_HMSS_ZHD);
        DTF_MAP.put(FM_DTF_MzSz, DTF_MS_ZHD);
        DTF_MAP.put(FM_DTF_MzSzSz, DTF_MSS_ZHD);

        DTF_MAP.put(FM_DTF_YdMdDHcMcSZo, DTF_YMDHMS_DASH_ZO);
        DTF_MAP.put(FM_DTF_YsMsDHcMcSZo, DTF_YMDHMS_SLASH_ZO);
        DTF_MAP.put(FM_DTF_YoMoDHcMcSZo, DTF_YMDHMS_DOT_ZO);
        DTF_MAP.put(FM_DTF_YdMdDHcMcSSZo, DTF_YMDHMSS_DASH_ZO);
        DTF_MAP.put(FM_DTF_YsMsDHcMcSSZo, DTF_YMDHMSS_SLASH_ZO);
        DTF_MAP.put(FM_DTF_YoMoDHcMcSSZo, DTF_YMDHMSS_DOT_ZO);
        DTF_MAP.put(FM_DTF_YdMdDHcMcSOs, DTF_YMDHMS_DASH_OS);
        DTF_MAP.put(FM_DTF_YsMsDHcMcSOs, DTF_YMDHMS_SLASH_OS);
        DTF_MAP.put(FM_DTF_YoMoDHcMcSOs, DTF_YMDHMS_DOT_OS);
        DTF_MAP.put(FM_DTF_YdMdDHcMcSSOs, DTF_YMDHMSS_DASH_OS);
        DTF_MAP.put(FM_DTF_YsMsDHcMcSSOs, DTF_YMDHMSS_SLASH_OS);
        DTF_MAP.put(FM_DTF_YoMoDHcMcSSOs, DTF_YMDHMSS_DOT_OS);

    }

    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     *********************************        方法        ********************************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/

    /**
     * 通过时间格式化字符串获取 DateTimeFormatter 对象
     *
     * @param pattern 时间格式化字符串
     * @return DateTimeFormatter 对象
     */
    public static DateTimeFormatter getDTF(String pattern) {
        DateTimeFormatter dtf = DTF_MAP.get(pattern);
        if (dtf == null) {
            /* "y/M/d H:m:s".length() == 11, 小于11，说明时间结构不是完整的"年月日时分秒" */
            return pattern.length() < 11 ? buildDTF(pattern) : DateTimeFormatter.ofPattern(pattern);
        } else {
            return dtf;
        }
    }

    /**
     * 创建带默认值的 {@code DateTimeFormatter}
     *
     * @param pattern 只精确到 年
     * @return DateTimeFormatter
     */
    public static DateTimeFormatter buildDTF(String pattern) {
        return new DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .parseDefaulting(ChronoField.YEAR_OF_ERA, 1970)
                // .parseDefaulting(ChronoField.YEAR, 1970)
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
                .toFormatter();
    }


    /**
     * Pattern for Java8 new date api {@link java.time.LocalDateTime})
     * using {@link java.time.format.DateTimeFormatter} <br>
     * 通过给定的时间串解析出匹配的时间格式串，适用于 {@link java.time.format.DateTimeFormatter}<br>
     * <b>注：</b><br>
     * 与 {@link #forSDF(String)} 相比，此函数<b>省略了一些不完整的时间格式解析</b>。
     * 比如：对于日期格式，必须要带<b>“年”</b>的部分；对于时间格式，则必须要带<b>“时”</b>的部分
     *
     *
     * @param dateTime 时间串
     * @return 时间格式串，如：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static String forDTF(final String dateTime) {
        if (S.isBlank(dateTime)) return null;
        int length = dateTime.length();
        if (length < 3) return null;

        DateTimeSeparator dts = null;
        String format = null;

        /*
         处理带时区偏移量或时区的时间串
         999-1-2 6:0:6.5 [Asia/Dubai +04:00]
         999-1-2 6:0:6.5 [+04:00]
         999-1-2 6:0:6 [Asia/Dubai +04:00]
         999-1-2 6:0:6 [+04:00]
         */
        if (length >= 16 && dateTime.endsWith("]")) {
            dts = checkSeparatorWithZone(dateTime);
            if (dts == null) return null;
            switch (dts) {
                case DASH_COLON:
                    format = matchPattern(YdMdDHcMcSZo, dateTime, FM_DTF_YdMdDHcMcSZo);
                    if (format != null) return format;
                    return matchPattern(YdMdDHcMcSOs, dateTime, FM_DTF_YdMdDHcMcSOs);
                case DASH_COLON_DOT:
                    format = matchPattern(YdMdDHcMcSSZo, dateTime, FM_DTF_YdMdDHcMcSSZo);
                    if (format != null) return format;
                    return matchPattern(YdMdDHcMcSSOs, dateTime, FM_DTF_YdMdDHcMcSSOs);
                case SLASH_COLON:
                    format = matchPattern(YsMsDHcMcSZo, dateTime, FM_DTF_YsMsDHcMcSZo);
                    if (format != null) return format;
                    return matchPattern(YsMsDHcMcSOs, dateTime, FM_DTF_YsMsDHcMcSOs);
                case SLASH_COLON_DOT:
                    format = matchPattern(YsMsDHcMcSSZo, dateTime, FM_DTF_YsMsDHcMcSSZo);
                    if (format != null) return format;
                    return matchPattern(YsMsDHcMcSSOs, dateTime, FM_DTF_YsMsDHcMcSSOs);
                case DOT_COLON:
                    format = matchPattern(YoMoDHcMcSZo, dateTime, FM_DTF_YoMoDHcMcSZo);
                    if (format != null) return format;
                    return matchPattern(YoMoDHcMcSOs, dateTime, FM_DTF_YoMoDHcMcSOs);
                case DOT_COLON_DOT:
                    format = matchPattern(YoMoDHcMcSSZo, dateTime, FM_DTF_YoMoDHcMcSSZo);
                    if (format != null) return format;
                    return matchPattern(YoMoDHcMcSSOs, dateTime, FM_DTF_YoMoDHcMcSSOs);
                default:
                    return null;
            }
        }

        /*
         长度判断
         */
        if (length > 25) return null;

        /*
         处理带T和结尾是Z的时间串
         14位：999-1-2T3:4:5Z
         24位：2024-01-02T00:00:00.000Z

         处理带T的时间串
         13位：999-1-2T3:4:5
         23位：2024-01-02T00:00:00.000
         */
        if (length >= 13 && length <= 24) {
            if (dateTime.contains("T")) {
                dts = checkSeparatorWithT(dateTime);
                if (dts == null) return null;
                if (dateTime.endsWith("Z")) {
                    switch (dts) {
                        case DASH_COLON:
                            return matchPattern(YdMdDTHcMcSZ, dateTime, FM_DTF_YdMdDTHcMcSZ);
                        case DASH_COLON_DOT:
                            return matchPattern(YdMdDTHcMcSSZ, dateTime, FM_DTF_YdMdDTHcMcSSZ);
                        case SLASH_COLON:
                            return matchPattern(YsMsDTHcMcSZ, dateTime, FM_DTF_YsMsDTHcMcSZ);
                        case SLASH_COLON_DOT:
                            return matchPattern(YsMsDTHcMcSSZ, dateTime, FM_DTF_YsMsDTHcMcSSZ);
                        case DOT_COLON:
                            return matchPattern(YoMoDTHcMcSZ, dateTime, FM_DTF_YoMoDTHcMcSZ);
                        case DOT_COLON_DOT:
                            return matchPattern(YoMoDTHcMcSSZ, dateTime, FM_DTF_YoMoDTHcMcSSZ);
                        default:
                            return null;
                    }
                } else {
                    switch (dts) {
                        case DASH_COLON:
                            return matchPattern(YdMdDTHcMcS, dateTime, FM_DTF_YdMdDTHcMcS);
                        case DASH_COLON_DOT:
                            return matchPattern(YdMdDTHcMcSS, dateTime, FM_DTF_YdMdDTHcMcSS);
                        case SLASH_COLON:
                            return matchPattern(YsMsDTHcMcS, dateTime, FM_DTF_YsMsDTHcMcS);
                        case SLASH_COLON_DOT:
                            return matchPattern(YsMsDTHcMcSS, dateTime, FM_DTF_YsMsDTHcMcSS);
                        case DOT_COLON:
                            return matchPattern(YoMoDTHcMcS, dateTime, FM_DTF_YoMoDTHcMcS);
                        case DOT_COLON_DOT:
                            return matchPattern(YoMoDTHcMcSS, dateTime, FM_DTF_YoMoDTHcMcSS);
                        default:    // NO_SEPARATOR
                            return matchPattern(cs(YMDTHMS, YMDTHMSS), dateTime, FM_YMDTHMS, FM_YMDTHMSS);
                    }
                }
            }
        }

        switch (length) {
            case 3:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case NO_SEPARATOR:
                        return matchPattern(Y, dateTime, FM_DTF_Y);
                    case COLON:
                        return matchPattern(HcM, dateTime, FM_DTF_HcM);
                }
                return null;

            case 4:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case NO_SEPARATOR:
                        return matchPattern(Y, dateTime, FM_DTF_Y);
                    case COLON:
                        return matchPattern(HcM, dateTime, FM_DTF_HcM);
                    case ZH_TIME:
                        return matchPattern(cs(HzMz, HoMz, MzSz), dateTime, FM_DTF_HzMz, FM_DTF_HoMz, FM_DTF_MzSz);
                    case ZH_DATE:
                        return matchPattern(Yz, dateTime, FM_DTF_Yz);
                }
                return null;
            case 5:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case SLASH:
                        return matchPattern(YsM, dateTime, FM_DTF_YsM);
                    case DASH:
                        return matchPattern(YdM, dateTime, FM_DTF_YdM);
                    case DOT:
                        return matchPattern(YoM, dateTime, FM_DTF_YoM);
                    case COLON:
                        return matchPattern(cs(HcMcS, HcM), dateTime, FM_DTF_HcMcS, FM_DTF_HcM);
                    case ZH_TIME:
                        return matchPattern(cs(HzMz, HoMz, MzSz), dateTime, FM_DTF_HzMz, FM_DTF_HoMz, FM_DTF_MzSz);
                    case ZH_DATE:
                        return matchPattern(Yz, dateTime, FM_DTF_Yz);
                }
                return null;
            case 6:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case NO_SEPARATOR:
                        return matchPattern(YM, dateTime, FM_YM);
                    case SLASH:
                        return matchPattern(YsM, dateTime, FM_DTF_YsM);
                    case DASH:
                        return matchPattern(YdM, dateTime, FM_DTF_YdM);
                    case DOT:
                        return matchPattern(YoM, dateTime, FM_DTF_YoM);
                    case COLON:
                        return matchPattern(HcMcS, dateTime, FM_DTF_HcMcS);
                    case ZH_TIME:
                        return matchPattern(cs(HzMzSz, HoMzSz, HzMz, HoMz, MzSz), dateTime, FM_DTF_HzMzSz, FM_DTF_HoMzSz, FM_DTF_HzMz, FM_DTF_HoMz, FM_DTF_MzSz);
                    case ZH_DATE:
                        return matchPattern(YzMz, dateTime, FM_DTF_YzMz);
                }
                return null;
            case 7:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case COLON_DOT:
                        return matchPattern(HcMcSS, dateTime, FM_DTF_HcMcSS);
                    case SLASH:
                        return matchPattern(cs(YsMsD, YsM), dateTime, FM_DTF_YsMsD, FM_DTF_YsM);
                    case DASH:
                        return matchPattern(cs(YdMdD, YdM), dateTime, FM_DTF_YdMdD, FM_DTF_YdM);
                    case DOT:
                        return matchPattern(cs(YoMoD, YoM), dateTime, FM_DTF_YoMoD, FM_DTF_YoM);
                    case COLON:
                        return matchPattern(HcMcS, dateTime, FM_DTF_HcMcS);
                    case ZH_TIME_MS:
                        return matchPattern(MzSzSz, dateTime, FM_DTF_MzSzSz);
                    case ZH_TIME:
                        return matchPattern(cs(HzMzSz, HoMzSz), dateTime, FM_DTF_HzMzSz, FM_DTF_HoMzSz);
                    case ZH_DATE:
                        return matchPattern(YzMz, dateTime, FM_DTF_YzMz);
                }
                return null;
            case 8:
                // 最常用，先检测
                format = matchPattern(HcMcS, dateTime, FM_HcMcS);
                if (null == format) {
                    dts = separatorForDate(dateTime);
                    if (null == dts) return null;
                    switch (dts) {
                        case NO_SEPARATOR:
                            return matchPattern(YMD, dateTime, FM_YMD);
                        case COLON_DOT:
                            return matchPattern(HcMcSS, dateTime, FM_DTF_HcMcSS);
                        case SLASH:
                            return matchPattern(YsMsD, dateTime, FM_DTF_YsMsD);
                        case DASH:
                            return matchPattern(YdMdD, dateTime, FM_DTF_YdMdD);
                        case DOT:
                            return matchPattern(YoMoD, dateTime, FM_DTF_YoMoD);
                        case ZH_TIME_MS:
                            return matchPattern(MzSzSz, dateTime, FM_DTF_MzSzSz);
                        case ZH_TIME:
                            return matchPattern(cs(HzMzSz, HoMzSz), dateTime, FM_DTF_HzMzSz, FM_DTF_HoMzSz);
                        case ZH_DATE:
                            return matchPattern(cs(YzMzDz, YzMz), dateTime, FM_DTF_YzMzDz, FM_DTF_YzMz);
                    }
                    return null;
                } else {
                    return format;
                }
            case 9:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case COLON_DOT:
                        return matchPattern(HcMcSS, dateTime, FM_DTF_HcMcSS);
                    case SLASH_COLON:
                        return matchPattern(YsMsDH, dateTime, FM_DTF_YsMsDH);
                    case DASH_COLON:
                        return matchPattern(YdMdDH, dateTime, FM_DTF_YdMdDH);
                    case DOT_COLON:
                        return matchPattern(YoMoDH, dateTime, FM_DTF_YoMoDH);
                    case SLASH:
                        return matchPattern(YsMsD, dateTime, FM_DTF_YsMsD);
                    case DASH:
                        return matchPattern(YdMdD, dateTime, FM_DTF_YdMdD);
                    case DOT:
                        return matchPattern(YoMoD, dateTime, FM_DTF_YoMoD);
                    case ZH_TIME_MS:
                        return matchPattern(cs(HzMzSzSSSz, HoMzSzSSSz, MzSzSz), dateTime, FM_DTF_HzMzSzSz, FM_DTF_HoMzSzSz, FM_DTF_MzSzSz);
                    case ZH_TIME:
                        return matchPattern(cs(HzMzSz, HoMzSz), dateTime, FM_DTF_HzMzSz, FM_DTF_HoMzSz);
                    case ZH_DATE:
                        return matchPattern(YzMzDz, dateTime, FM_DTF_YzMzDz);
                }
                return null;
            case 10:
                // 最常用，先检测
                format = matchPattern(YdMdD, dateTime, FM_YdMdD);
                if (null == format) {
                    dts = separatorForDate(dateTime);
                    if (null == dts) return null;
                    switch (dts) {
                        case NO_SEPARATOR:
                            return matchPattern(YMDH, dateTime, FM_YMDH);
                        case COLON_DOT:
                            return matchPattern(HcMcSS, dateTime, FM_DTF_HcMcSS);
                        case SLASH_COLON:
                            return matchPattern(YsMsDH, dateTime, FM_DTF_YsMsDH);
                        case DASH_COLON:
                            return matchPattern(YdMdDH, dateTime, FM_DTF_YdMdDH);
                        case DOT_COLON:
                            return matchPattern(YoMoDH, dateTime, FM_DTF_YoMoDH);
                        case SLASH:
                            return matchPattern(YsMsD, dateTime, FM_DTF_YsMsD);
                        case DOT:
                            return matchPattern(YoMoD, dateTime, FM_DTF_YoMoD);
                        case ZH_TIME_MS:
                            return matchPattern(cs(HzMzSzSSSz, HoMzSzSSSz, MzSzSz), dateTime, FM_DTF_HzMzSzSz, FM_DTF_HoMzSzSz, FM_DTF_MzSzSz);
                        case ZH_ZH:
                            return matchPattern(cs(YzMzDzHz, YzMzDzHo), dateTime, FM_DTF_YzMzDzHz, FM_DTF_YzMzDzHo);
                        case ZH_DATE:
                            return matchPattern(YzMzDz, dateTime, FM_DTF_YzMzDz);
                    }
                    return null;
                } else {
                    return format;
                }

            case 11:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case COLON_DOT:
                        return matchPattern(HcMcSS, dateTime, FM_DTF_HcMcSS);
                    case SLASH_COLON:
                        return matchPattern(cs(YsMsDHcM, YsMsDH), dateTime, FM_DTF_YsMsDHcM, FM_DTF_YsMsDH);
                    case DASH_COLON:
                        return matchPattern(cs(YdMdDHcM, YdMdDH), dateTime, FM_DTF_YdMdDHcM, FM_DTF_YdMdDH);
                    case DOT_COLON:
                        return matchPattern(cs(YoMoDHcM, YoMoDH), dateTime, FM_DTF_YoMoDHcM, FM_DTF_YoMoDH);
                    case ZH_TIME_MS:
                        return matchPattern(cs(HzMzSzSSSz, HoMzSzSSSz, MzSzSz), dateTime, FM_DTF_HzMzSzSz, FM_DTF_HoMzSzSz, FM_DTF_MzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHz, YzMzDzHo), dateTime, FM_DTF_YzMzDzHz, FM_DTF_YzMzDzHo);
                    case ZH_DATE:
                        return matchPattern(YzMzDz, dateTime, FM_DTF_YzMzDz);
                }
                return null;
            case 12:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case NO_SEPARATOR:
                        return matchPattern(YMDHM, dateTime, FM_YMDHM);
                    case COLON_DOT:
                        return matchPattern(HcMcSS, dateTime, FM_DTF_HcMcSS);
                    case SLASH_COLON:
                        return matchPattern(cs(YsMsDHcM, YsMsDH), dateTime, FM_DTF_YsMsDHcM, FM_DTF_YsMsDH);
                    case DASH_COLON:
                        return matchPattern(cs(YdMdDHcM, YdMdDH), dateTime, FM_DTF_YdMdDHcM, FM_DTF_YdMdDH);
                    case DOT_COLON:
                        return matchPattern(cs(YoMoDHcM, YoMoDH), dateTime, FM_DTF_YoMoDHcM, FM_DTF_YoMoDH);
                    case ZH_TIME_MS:
                        return matchPattern(cs(HzMzSzSSSz, HoMzSzSSSz), dateTime, FM_DTF_HzMzSzSz, FM_DTF_HoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMz, YzMzDzHoMz, YzMzDzHz, YzMzDzHo), dateTime,
                                FM_DTF_YzMzDzHzMz, FM_DTF_YzMzDzHoMz, FM_DTF_YzMzDzHz, FM_DTF_YzMzDzHo);
                }
                return null;
            case 13:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case SLASH_COLON:
                        return matchPattern(cs(YsMsDHcMcS, YsMsDHcM, YsMsDH), dateTime, FM_DTF_YsMsDHcMcS, FM_DTF_YsMsDHcM, FM_DTF_YsMsDH);
                    case DASH_COLON:
                        return matchPattern(cs(YdMdDHcMcS, YdMdDHcM, YdMdDH), dateTime, FM_DTF_YdMdDHcMcS, FM_DTF_YdMdDHcM, FM_DTF_YdMdDH);
                    case DOT_COLON:
                        return matchPattern(cs(YoMoDHcMcS, YoMoDHcM, YoMoDH), dateTime, FM_DTF_YoMoDHcMcS, FM_DTF_YoMoDHcM, FM_DTF_YoMoDH);
                    case ZH_TIME_MS:
                        return matchPattern(cs(HzMzSzSSSz, HoMzSzSSSz), dateTime, FM_DTF_HzMzSzSz, FM_DTF_HoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMz, YzMzDzHoMz, YzMzDzHz, YzMzDzHo), dateTime,
                                FM_DTF_YzMzDzHzMz, FM_DTF_YzMzDzHoMz, FM_DTF_YzMzDzHz, FM_DTF_YzMzDzHo);
                }
                return null;
            case 14:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case NO_SEPARATOR:
                        return matchPattern(YMDHMS, dateTime, FM_YMDHMS);
                    case SLASH_COLON:
                        return matchPattern(cs(YsMsDHcMcS, YsMsDHcM), dateTime, FM_DTF_YsMsDHcMcS, FM_DTF_YsMsDHcM);
                    case DASH_COLON:
                        return matchPattern(cs(YdMdDHcMcS, YdMdDHcM), dateTime, FM_DTF_YdMdDHcMcS, FM_DTF_YdMdDHcM);
                    case DOT_COLON:
                        return matchPattern(cs(YoMoDHcMcS, YoMoDHcM), dateTime, FM_DTF_YoMoDHcMcS, FM_DTF_YoMoDHcM);
                    case ZH_TIME_MS:
                        return matchPattern(cs(HzMzSzSSSz, HoMzSzSSSz), dateTime, FM_DTF_HzMzSzSz, FM_DTF_HoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz, YzMzDzHzMz, YzMzDzHoMz, YzMzDzHz, YzMzDzHo), dateTime,
                                FM_DTF_YzMzDzHzMzSz, FM_DTF_YzMzDzHoMzSz, FM_DTF_YzMzDzHzMz, FM_DTF_YzMzDzHoMz, FM_DTF_YzMzDzHz, FM_DTF_YzMzDzHo);
                }
                return null;
            case 15:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case DASH_COLON_DOT:
                        return matchPattern(YdMdDHcMcSS, dateTime, FM_DTF_YdMdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(YsMsDHcMcSS, dateTime, FM_DTF_YsMsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(YoMoDHcMcSS, dateTime, FM_DTF_YoMoDHcMcSS);
                    case SLASH_COLON:
                        return matchPattern(cs(YsMsDHcMcS, YsMsDHcM), dateTime, FM_DTF_YsMsDHcMcS, FM_DTF_YsMsDHcM);
                    case DASH_COLON:
                        return matchPattern(cs(YdMdDHcMcS, YdMdDHcM), dateTime, FM_DTF_YdMdDHcMcS, FM_DTF_YdMdDHcM);
                    case DOT_COLON:
                        return matchPattern(cs(YoMoDHcMcS, YoMoDHcM), dateTime, FM_DTF_YoMoDHcMcS, FM_DTF_YoMoDHcM);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz, YzMzDzHzMz, YzMzDzHoMz), dateTime,
                                FM_DTF_YzMzDzHzMzSz, FM_DTF_YzMzDzHoMzSz, FM_DTF_YzMzDzHzMz, FM_DTF_YzMzDzHoMz);
                }
                return null;
            case 16:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case DASH_COLON_DOT:
                        return matchPattern(YdMdDHcMcSS, dateTime, FM_DTF_YdMdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(YsMsDHcMcSS, dateTime, FM_DTF_YsMsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(YoMoDHcMcSS, dateTime, FM_DTF_YoMoDHcMcSS);
                    case SLASH_COLON:
                        return matchPattern(cs(YsMsDHcMcS, YsMsDHcM), dateTime, FM_DTF_YsMsDHcMcS, FM_DTF_YsMsDHcM);
                    case DASH_COLON:
                        return matchPattern(cs(YdMdDHcMcS, YdMdDHcM), dateTime, FM_DTF_YdMdDHcMcS, FM_DTF_YdMdDHcM);
                    case DOT_COLON:
                        return matchPattern(cs(YoMoDHcMcS, YoMoDHcM), dateTime, FM_DTF_YoMoDHcMcS, FM_DTF_YoMoDHcM);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz, YzMzDzHzMz, YzMzDzHoMz), dateTime,
                                FM_DTF_YzMzDzHzMzSz, FM_DTF_YzMzDzHoMzSz, FM_DTF_YzMzDzHzMz, FM_DTF_YzMzDzHoMz);
                }
                return null;
            case 17:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case NO_SEPARATOR:
                        return matchPattern(YMDHMSS, dateTime, FM_YMDHMSS);
                    case DASH_COLON_DOT:
                        return matchPattern(YdMdDHcMcSS, dateTime, FM_DTF_YdMdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(YsMsDHcMcSS, dateTime, FM_DTF_YsMsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(YoMoDHcMcSS, dateTime, FM_DTF_YoMoDHcMcSS);
                    case SLASH_COLON:
                        return matchPattern(YsMsDHcMcS, dateTime, FM_DTF_YsMsDHcMcS);
                    case DASH_COLON:
                        return matchPattern(YdMdDHcMcS, dateTime, FM_DTF_YdMdDHcMcS);
                    case DOT_COLON:
                        return matchPattern(YoMoDHcMcS, dateTime, FM_DTF_YoMoDHcMcS);
                    case ZH_ZH_MS:
                        return matchPattern(cs(YzMzDzHzMzSzSSSz, YzMzDzHoMzSzSSSz), dateTime, FM_DTF_YzMzDzHzMzSzSz, FM_DTF_YzMzDzHoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz, YzMzDzHzMz, YzMzDzHoMz), dateTime,
                                FM_DTF_YzMzDzHzMzSz, FM_DTF_YzMzDzHoMzSz, FM_DTF_YzMzDzHzMz, FM_DTF_YzMzDzHoMz);
                }
                return null;
            case 18:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case DASH_COLON_DOT:
                        return matchPattern(YdMdDHcMcSS, dateTime, FM_DTF_YdMdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(YsMsDHcMcSS, dateTime, FM_DTF_YsMsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(YoMoDHcMcSS, dateTime, FM_DTF_YoMoDHcMcSS);
                    case SLASH_COLON:
                        return matchPattern(YsMsDHcMcS, dateTime, FM_DTF_YsMsDHcMcS);
                    case DASH_COLON:
                        return matchPattern(YdMdDHcMcS, dateTime, FM_DTF_YdMdDHcMcS);
                    case DOT_COLON:
                        return matchPattern(YoMoDHcMcS, dateTime, FM_DTF_YoMoDHcMcS);
                    case ZH_ZH_MS:
                        return matchPattern(cs(YzMzDzHzMzSzSSSz, YzMzDzHoMzSzSSSz), dateTime, FM_DTF_YzMzDzHzMzSzSz, FM_DTF_YzMzDzHoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz), dateTime, FM_DTF_YzMzDzHzMzSz, FM_DTF_YzMzDzHoMzSz);
                }
                return null;
            case 19:
                // 最常用，先检测
                format = matchPattern(YdMdDHcMcS, dateTime, FM_DTF_YdMdDHcMcS);
                if (null == format) {
                    dts = separatorForDate(dateTime);
                    if (null == dts) return null;
                    switch (dts) {
                        case DASH_COLON_DOT:
                            return matchPattern(YdMdDHcMcSS, dateTime, FM_DTF_YdMdDHcMcSS);
                        case SLASH_COLON_DOT:
                            return matchPattern(YsMsDHcMcSS, dateTime, FM_DTF_YsMsDHcMcSS);
                        case DOT_COLON_DOT:
                            return matchPattern(YoMoDHcMcSS, dateTime, FM_DTF_YoMoDHcMcSS);
                        case SLASH_COLON:
                            return matchPattern(YsMsDHcMcS, dateTime, FM_DTF_YsMsDHcMcS);
                        case DOT_COLON:
                            return matchPattern(YoMoDHcMcS, dateTime, FM_DTF_YoMoDHcMcS);
                        case ZH_ZH_MS:
                            return matchPattern(cs(YzMzDzHzMzSzSSSz, YzMzDzHoMzSzSSSz), dateTime, FM_DTF_YzMzDzHzMzSzSz, FM_DTF_YzMzDzHoMzSzSz);
                        case ZH_ZH:
                            return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz), dateTime, FM_DTF_YzMzDzHzMzSz, FM_DTF_YzMzDzHoMzSz);
                    }
                    return null;
                } else {
                    return format;
                }
            case 20:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case DASH_COLON_DOT:
                        return matchPattern(YdMdDHcMcSS, dateTime, FM_DTF_YdMdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(YsMsDHcMcSS, dateTime, FM_DTF_YsMsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(YoMoDHcMcSS, dateTime, FM_DTF_YoMoDHcMcSS);
                    case ZH_ZH_MS:
                        return matchPattern(cs(YzMzDzHzMzSzSSSz, YzMzDzHoMzSzSSSz), dateTime, FM_DTF_YzMzDzHzMzSzSz, FM_DTF_YzMzDzHoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz), dateTime, FM_DTF_YzMzDzHzMzSz, FM_DTF_YzMzDzHoMzSz);
                }
                return null;
            case 21:
            case 22:
            case 23:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case DASH_COLON_DOT:
                        return matchPattern(YdMdDHcMcSS, dateTime, FM_DTF_YdMdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(YsMsDHcMcSS, dateTime, FM_DTF_YsMsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(YoMoDHcMcSS, dateTime, FM_DTF_YoMoDHcMcSS);
                    case ZH_ZH_MS:
                        return matchPattern(cs(YzMzDzHzMzSzSSSz, YzMzDzHoMzSzSSSz), dateTime, FM_DTF_YzMzDzHzMzSzSz, FM_DTF_YzMzDzHoMzSzSz);
                }
                return null;
            case 24:
            case 25:
                return matchPattern(cs(YzMzDzHzMzSzSSSz, YzMzDzHoMzSzSSSz), dateTime, FM_DTF_YzMzDzHzMzSzSz, FM_DTF_YzMzDzHoMzSzSz);
        }
        return null;
    }

    /**
     * Pattern for {@link java.util.Date} using {@link java.text.SimpleDateFormat} <br>
     * 通过给定的时间串解析出匹配的时间格式串，适用于 {@link java.text.SimpleDateFormat}
     *
     * @param dateTime 时间串
     * @return 时间格式串，如：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static String forSDF(final String dateTime) {
        if (S.isBlank(dateTime)) return null;
        int length = dateTime.length();
        if (length < 3 || length > 25) return null;

        DateTimeSeparator dts = null;
        String format = null;
        /*
         * 处理带T的时间串
         */
        if (length >= 13 && length <= 23 && dateTime.contains("T")) {
            dts = checkSeparatorWithT(dateTime);
            if (dts == null) return null;
            switch (dts) {
                case DASH_COLON:
                    return matchPattern(YdMdDTHcMcS, dateTime, FM_YdMdDTHcMcS);
                case DASH_COLON_DOT:
                    return matchPattern(YdMdDTHcMcSS, dateTime, FM_YdMdDTHcMcSS);
                case SLASH_COLON:
                    return matchPattern(YsMsDTHcMcS, dateTime, FM_YsMsDTHcMcS);
                case SLASH_COLON_DOT:
                    return matchPattern(YsMsDTHcMcSS, dateTime, FM_YsMsDTHcMcSS);
                case DOT_COLON:
                    return matchPattern(YoMoDTHcMcS, dateTime, FM_YoMoDTHcMcS);
                case DOT_COLON_DOT:
                    return matchPattern(YoMoDTHcMcSS, dateTime, FM_YoMoDTHcMcSS);
                default:    // NO_SEPARATOR
                    return matchPattern(cs(YMDTHMS, YMDTHMSS), dateTime, FM_YMDTHMS, FM_YMDTHMSS);
            }
        }

        switch (length) {
            case 3:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case NO_SEPARATOR:
                        return matchPattern(Y, dateTime, FM_Y);
                    case COLON:
                        return matchPattern(HcM, dateTime, FM_HcM);
                    case DASH:
                        return matchPattern(MdD, dateTime, FM_MdD);
                    case SLASH:
                        return matchPattern(MsD, dateTime, FM_MsD);
                    case DOT:
                        return matchPattern(MoD, dateTime, FM_MoD);
                }
                return null;

            case 4:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case NO_SEPARATOR:
                        return matchPattern(Y, dateTime, FM_Y);
                    case SLASH:
                        return matchPattern(MsD, dateTime, FM_MsD);
                    case DASH:
                        return matchPattern(MdD, dateTime, FM_MdD);
                    case DOT:
                        return matchPattern(MoD, dateTime, FM_MoD);
                    case COLON:
                        return matchPattern(HcM, dateTime, FM_HcM);
                    case ZH_TIME:
                        return matchPattern(cs(HzMz, HoMz, MzSz), dateTime, FM_HzMz, FM_HoMz, FM_MzSz);
                    case ZH_DATE:
                        return matchPattern(cs(Yz, MzDz), dateTime, FM_Yz, FM_MzDz);
                }
                return null;
            case 5:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case COLON_DOT:
                        return matchPattern(McSS, dateTime, FM_McSS);
                    case SLASH_COLON:
                        return matchPattern(MsDH, dateTime, FM_MsDH);
                    case DASH_COLON:
                        return matchPattern(MdDH, dateTime, FM_MdDH);
                    case DOT_COLON:
                        return matchPattern(MoDH, dateTime, FM_MoDH);
                    case SLASH:
                        return matchPattern(cs(YsM, MsD), dateTime, FM_YsM, FM_MsD);
                    case DASH:
                        return matchPattern(cs(YdM, MdD), dateTime, FM_YdM, FM_MdD);
                    case DOT:
                        return matchPattern(cs(YoM, MoD), dateTime, FM_YoM, FM_MoD);
                    case COLON:
                        return matchPattern(cs(HcMcS, HcM), dateTime, FM_HcMcS, FM_HcM);
                    case ZH_TIME:
                        return matchPattern(cs(HzMz, HoMz, MzSz), dateTime, FM_HzMz, FM_HoMz, FM_MzSz);
                    case ZH_DATE:
                        return matchPattern(cs(Yz, MzDz), dateTime, FM_Yz, FM_MzDz);
                }
                return null;
            case 6:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case NO_SEPARATOR:
                        return matchPattern(YM, dateTime, FM_YM);
                    case COLON_DOT:
                        return matchPattern(McSS, dateTime, FM_McSS);
                    case SLASH_COLON:
                        return matchPattern(MsDH, dateTime, FM_MsDH);
                    case DASH_COLON:
                        return matchPattern(MdDH, dateTime, FM_MdDH);
                    case DOT_COLON:
                        return matchPattern(MoDH, dateTime, FM_MoDH);
                    case SLASH:
                        return matchPattern(YsM, dateTime, FM_YsM);
                    case DASH:
                        return matchPattern(YdM, dateTime, FM_YdM);
                    case DOT:
                        return matchPattern(YoM, dateTime, FM_YoM);
                    case COLON:
                        return matchPattern(HcMcS, dateTime, FM_HcMcS);
                    case ZH_ZH:
                        return matchPattern(cs(MzDzHz, MzDzHo), dateTime, FM_MzDzHz, FM_MzDzHo);
                    case ZH_TIME:
                        return matchPattern(cs(HzMzSz, HoMzSz, HzMz, HoMz, MzSz), dateTime,
                                FM_HzMzSz, FM_HoMzSz, FM_HzMz, FM_HoMz, FM_MzSz);
                    case ZH_DATE:
                        return matchPattern(cs(YzMz, MzDz), dateTime, FM_YzMz, FM_MzDz);
                }
                return null;
            case 7:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case COLON_DOT:
                        return matchPattern(cs(HcMcSS, McSS), dateTime, FM_HcMcSS, FM_McSS);
                    case SLASH_COLON:
                        return matchPattern(cs(MsDHcM, MsDH), dateTime, FM_MsDHcM, FM_MsDH);
                    case DASH_COLON:
                        return matchPattern(cs(MdDHcM, MdDH), dateTime, FM_MdDHcM, FM_MdDH);
                    case DOT_COLON:
                        return matchPattern(cs(MoDHcM, MoDH), dateTime, FM_MoDHcM, FM_MoDH);
                    case SLASH:
                        return matchPattern(cs(YsMsD, YsM), dateTime, FM_YsMsD, FM_YsM);
                    case DASH:
                        return matchPattern(cs(YdMdD, YdM), dateTime, FM_YdMdD, FM_YdM);
                    case DOT:
                        return matchPattern(cs(YoMoD, YoM), dateTime, FM_YoMoD, FM_YoM);
                    case COLON:
                        return matchPattern(HcMcS, dateTime, FM_HcMcS);
                    case ZH_TIME_MS:
                        return matchPattern(MzSzSz, dateTime, FM_MzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(MzDzHz, MzDzHo), dateTime, FM_MzDzHz, FM_MzDzHo);
                    case ZH_TIME:
                        return matchPattern(cs(HzMzSz, HoMzSz), dateTime, FM_HzMzSz, FM_HoMzSz);
                    case ZH_DATE:
                        return matchPattern(YzMz, dateTime, FM_YzMz);
                }
                return null;
            case 8:
                // 最常用，先检测
                format = matchPattern(HcMcS, dateTime, FM_HcMcS);
                if (null == format) {
                    dts = separatorForDate(dateTime);
                    if (null == dts) return null;
                    switch (dts) {
                        case NO_SEPARATOR:
                            return matchPattern(YMD, dateTime, FM_YMD);
                        case COLON_DOT:
                            return matchPattern(cs(HcMcSS, McSS), dateTime, FM_HcMcSS, FM_McSS);
                        case SLASH_COLON:
                            return matchPattern(cs(MsDHcM, MsDH), dateTime, FM_MsDHcM, FM_MsDH);
                        case DASH_COLON:
                            return matchPattern(cs(MdDHcM, MdDH), dateTime, FM_MdDHcM, FM_MdDH);
                        case DOT_COLON:
                            return matchPattern(cs(MoDHcM, MoDH), dateTime, FM_MoDHcM, FM_MoDH);
                        case SLASH:
                            return matchPattern(YsMsD, dateTime, FM_YsMsD);
                        case DASH:
                            return matchPattern(YdMdD, dateTime, FM_YdMdD);
                        case DOT:
                            return matchPattern(YoMoD, dateTime, FM_YoMoD);
                        case ZH_TIME_MS:
                            return matchPattern(MzSzSz, dateTime, FM_MzSzSz);
                        case ZH_ZH:
                            return matchPattern(cs(MzDzHzMz, MzDzHoMz, MzDzHz, MzDzHo), dateTime,
                                    FM_MzDzHzMz, FM_MzDzHoMz, FM_MzDzHz, FM_MzDzHo);
                        case ZH_TIME:
                            return matchPattern(cs(HzMzSz, HoMzSz), dateTime, FM_HzMzSz, FM_HoMzSz);
                        case ZH_DATE:
                            return matchPattern(cs(YzMzDz, YzMz), dateTime, FM_YzMzDz, FM_YzMz);
                    }
                    return null;
                } else {
                    return format;
                }
            case 9:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case COLON_DOT:
                        return matchPattern(cs(HcMcSS, McSS), dateTime, FM_HcMcSS, FM_McSS);
                    case SLASH_COLON:
                        return matchPattern(cs(YsMsDH, MsDHcMcS, MsDHcM), dateTime, FM_YsMsDH, FM_MsDHcMcS, FM_MsDHcM);
                    case DASH_COLON:
                        return matchPattern(cs(YdMdDH, MdDHcMcS, MdDHcM), dateTime, FM_YdMdDH, FM_MdDHcMcS, FM_MdDHcM);
                    case DOT_COLON:
                        return matchPattern(cs(YoMoDH, MoDHcMcS, MoDHcM), dateTime, FM_YoMoDH, FM_MoDHcMcS, FM_MoDHcM);
                    case SLASH:
                        return matchPattern(YsMsD, dateTime, FM_YsMsD);
                    case DASH:
                        return matchPattern(YdMdD, dateTime, FM_YdMdD);
                    case DOT:
                        return matchPattern(YoMoD, dateTime, FM_YoMoD);
                    case ZH_TIME_MS:
                        return matchPattern(cs(HzMzSzSz, HoMzSzSz, MzSzSz), dateTime, FM_HzMzSzSz, FM_HoMzSzSz, FM_MzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(MzDzHzMz, MzDzHoMz, MzDzHz, MzDzHo), dateTime,
                                FM_MzDzHzMz, FM_MzDzHoMz, FM_MzDzHz, FM_MzDzHo);
                    case ZH_TIME:
                        return matchPattern(cs(HzMzSz, HoMzSz), dateTime, FM_HzMzSz, FM_HoMzSz);
                    case ZH_DATE:
                        return matchPattern(YzMzDz, dateTime, FM_YzMzDz);
                }
                return null;
            case 10:
                // 最常用，先检测
                format = matchPattern(YdMdD, dateTime, FM_YdMdD);
                if (null == format) {
                    dts = separatorForDate(dateTime);
                    if (null == dts) return null;
                    switch (dts) {
                        case NO_SEPARATOR:
                            return matchPattern(YMDH, dateTime, FM_YMDH);
                        case COLON_DOT:
                            return matchPattern(HcMcSS, dateTime, FM_HcMcSS);
                        case SLASH_COLON:
                            return matchPattern(cs(YsMsDH, MsDHcMcS, MsDHcM), dateTime, FM_YsMsDH, FM_MsDHcMcS, FM_MsDHcM);
                        case DASH_COLON:
                            return matchPattern(cs(YdMdDH, MdDHcMcS, MdDHcM), dateTime, FM_YdMdDH, FM_MdDHcMcS, FM_MdDHcM);
                        case DOT_COLON:
                            return matchPattern(cs(YoMoDH, MoDHcMcS, MoDHcM), dateTime, FM_YoMoDH, FM_MoDHcMcS, FM_MoDHcM);
                        case SLASH:
                            return matchPattern(YsMsD, dateTime, FM_YsMsD);
                        case DOT:
                            return matchPattern(YoMoD, dateTime, FM_YoMoD);
                        case ZH_TIME_MS:
                            return matchPattern(cs(HzMzSzSz, HoMzSzSz, MzSzSz), dateTime, FM_HzMzSzSz, FM_HoMzSzSz, FM_MzSzSz);
                        case ZH_ZH:
                            return matchPattern(cs(YzMzDzHz, YzMzDzHo, MzDzHzMzSz, MzDzHoMzSz, MzDzHzMz, MzDzHoMz), dateTime,
                                    FM_YzMzDzHz, FM_YzMzDzHo, FM_MzDzHzMzSz, FM_MzDzHoMzSz, FM_MzDzHzMz, FM_MzDzHoMz);
                        case ZH_DATE:
                            return matchPattern(YzMzDz, dateTime, FM_YzMzDz);
                    }
                    return null;
                } else {
                    return format;
                }

            case 11:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case DASH_COLON_DOT:
                        return matchPattern(MdDHcMcSS, dateTime, FM_MdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(MsDHcMcSS, dateTime, FM_MsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(MoDHcMcSS, dateTime, FM_MoDHcMcSS);
                    case COLON_DOT:
                        return matchPattern(HcMcSS, dateTime, FM_HcMcSS);
                    case SLASH_COLON:
                        return matchPattern(cs(YsMsDHcM, YsMsDH, MsDHcMcS, MsDHcM), dateTime,
                                FM_YsMsDHcM, FM_YsMsDH, FM_MsDHcMcS, FM_MsDHcM);
                    case DASH_COLON:
                        return matchPattern(cs(YdMdDHcM, YdMdDH, MdDHcMcS, MdDHcM), dateTime,
                                FM_YdMdDHcM, FM_YdMdDH, FM_MdDHcMcS, FM_MdDHcM);
                    case DOT_COLON:
                        return matchPattern(cs(YoMoDHcM, YoMoDH, MoDHcMcS, MoDHcM), dateTime,
                                FM_YoMoDHcM, FM_YoMoDH, FM_MoDHcMcS, FM_MoDHcM);
                    case ZH_TIME_MS:
                        return matchPattern(cs(HzMzSzSz, HoMzSzSz, MzSzSz), dateTime, FM_HzMzSzSz, FM_HoMzSzSz, FM_MzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHz, YzMzDzHo, MzDzHzMzSz, MzDzHoMzSz, MzDzHzMz, MzDzHoMz), dateTime,
                                FM_YzMzDzHz, FM_YzMzDzHo, FM_MzDzHzMzSz, FM_MzDzHoMzSz, FM_MzDzHzMz, FM_MzDzHoMz);
                    case ZH_DATE:
                        return matchPattern(YzMzDz, dateTime, FM_YzMzDz);
                }
                return null;
            case 12:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case NO_SEPARATOR:
                        return matchPattern(YMDHM, dateTime, FM_YMDHM);
                    case DASH_COLON_DOT:
                        return matchPattern(MdDHcMcSS, dateTime, FM_MdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(MsDHcMcSS, dateTime, FM_MsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(MoDHcMcSS, dateTime, FM_MoDHcMcSS);
                    case COLON_DOT:
                        return matchPattern(HcMcSS, dateTime, FM_HcMcSS);
                    case SLASH_COLON:
                        return matchPattern(cs(YsMsDHcM, YsMsDH, MsDHcMcS), dateTime, FM_YsMsDHcM, FM_YsMsDH, FM_MsDHcMcS);
                    case DASH_COLON:
                        return matchPattern(cs(YdMdDHcM, YdMdDH, MdDHcMcS), dateTime, FM_YdMdDHcM, FM_YdMdDH, FM_MdDHcMcS);
                    case DOT_COLON:
                        return matchPattern(cs(YoMoDHcM, YoMoDH, MoDHcMcS), dateTime, FM_YoMoDHcM, FM_YoMoDH, FM_MoDHcMcS);
                    case ZH_TIME_MS:
                        return matchPattern(cs(HzMzSzSz, HoMzSzSz), dateTime, FM_HzMzSzSz, FM_HoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMz, YzMzDzHoMz, YzMzDzHz, YzMzDzHo, MzDzHzMzSz, MzDzHoMzSz, MzDzHzMz, MzDzHoMz), dateTime,
                                FM_YzMzDzHzMz, FM_YzMzDzHoMz, FM_YzMzDzHz, FM_YzMzDzHo, FM_MzDzHzMzSz, FM_MzDzHoMzSz, FM_MzDzHzMz, FM_MzDzHoMz);
                }
                return null;
            case 13:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case DASH_COLON_DOT:
                        return matchPattern(MdDHcMcSS, dateTime, FM_MdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(MsDHcMcSS, dateTime, FM_MsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(MoDHcMcSS, dateTime, FM_MoDHcMcSS);
                    case SLASH_COLON:
                        return matchPattern(cs(YsMsDHcMcS, YsMsDHcM, YsMsDH, MsDHcMcS), dateTime,
                                FM_YsMsDHcMcS, FM_YsMsDHcM, FM_YsMsDH, FM_MsDHcMcS);
                    case DASH_COLON:
                        return matchPattern(cs(YdMdDHcMcS, YdMdDHcM, YdMdDH, MdDHcMcS), dateTime,
                                FM_YdMdDHcMcS, FM_YdMdDHcM, FM_YdMdDH, FM_MdDHcMcS);
                    case DOT_COLON:
                        return matchPattern(cs(YoMoDHcMcS, YoMoDHcM, YoMoDH, MoDHcMcS), dateTime,
                                FM_YoMoDHcMcS, FM_YoMoDHcM, FM_YoMoDH, FM_MoDHcMcS);
                    case ZH_ZH_MS:
                        return matchPattern(cs(MzDzHzMzSzSz, MzDzHoMzSzSz), dateTime, FM_MzDzHzMzSzSz, FM_MzDzHoMzSzSz);
                    case ZH_TIME_MS:
                        return matchPattern(cs(HzMzSzSz, HoMzSzSz), dateTime, FM_HzMzSzSz, FM_HoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMz, YzMzDzHoMz, YzMzDzHz, YzMzDzHo, MzDzHzMzSz, MzDzHoMzSz), dateTime,
                                FM_YzMzDzHzMz, FM_YzMzDzHoMz, FM_YzMzDzHz, FM_YzMzDzHo, FM_MzDzHzMzSz, FM_MzDzHoMzSz);
                }
                return null;
            case 14:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case NO_SEPARATOR:
                        return matchPattern(YMDHMS, dateTime, FM_YMDHMS);
                    case DASH_COLON_DOT:
                        return matchPattern(MdDHcMcSS, dateTime, FM_MdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(MsDHcMcSS, dateTime, FM_MsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(MoDHcMcSS, dateTime, FM_MoDHcMcSS);
                    case SLASH_COLON:
                        return matchPattern(cs(YsMsDHcMcS, YsMsDHcM, MsDHcMcS), dateTime, FM_YsMsDHcMcS, FM_YsMsDHcM, FM_MsDHcMcS);
                    case DASH_COLON:
                        return matchPattern(cs(YdMdDHcMcS, YdMdDHcM, MdDHcMcS), dateTime, FM_YdMdDHcMcS, FM_YdMdDHcM, FM_MdDHcMcS);
                    case DOT_COLON:
                        return matchPattern(cs(YoMoDHcMcS, YoMoDHcM, MoDHcMcS), dateTime, FM_YoMoDHcMcS, FM_YoMoDHcM, FM_MoDHcMcS);
                    case ZH_ZH_MS:
                        return matchPattern(cs(MzDzHzMzSzSz, MzDzHoMzSzSz), dateTime, FM_MzDzHzMzSzSz, FM_MzDzHoMzSzSz);
                    case ZH_TIME_MS:
                        return matchPattern(cs(HzMzSzSz, HoMzSzSz), dateTime, FM_HzMzSzSz, FM_HoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz, YzMzDzHzMz, YzMzDzHoMz, YzMzDzHz, YzMzDzHo, MzDzHzMzSz, MzDzHoMzSz), dateTime,
                                FM_YzMzDzHzMzSz, FM_YzMzDzHoMzSz, FM_YzMzDzHzMz, FM_YzMzDzHoMz, FM_YzMzDzHz, FM_YzMzDzHo, FM_MzDzHzMzSz, FM_MzDzHoMzSz);
                }
                return null;
            case 15:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case DASH_COLON_DOT:
                        return matchPattern(cs(YdMdDHcMcSS, MdDHcMcSS), dateTime, FM_YdMdDHcMcSS, FM_MdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(cs(YsMsDHcMcSS, MsDHcMcSS), dateTime, FM_YsMsDHcMcSS, FM_MsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(cs(YoMoDHcMcSS, MoDHcMcSS), dateTime, FM_YoMoDHcMcSS, FM_MoDHcMcSS);
                    case SLASH_COLON:
                        return matchPattern(cs(YsMsDHcMcS, YsMsDHcM), dateTime, FM_YsMsDHcMcS, FM_YsMsDHcM);
                    case DASH_COLON:
                        return matchPattern(cs(YdMdDHcMcS, YdMdDHcM), dateTime, FM_YdMdDHcMcS, FM_YdMdDHcM);
                    case DOT_COLON:
                        return matchPattern(cs(YoMoDHcMcS, YoMoDHcM), dateTime, FM_YoMoDHcMcS, FM_YoMoDHcM);
                    case ZH_ZH_MS:
                        return matchPattern(cs(MzDzHzMzSzSz, MzDzHoMzSzSz), dateTime, FM_MzDzHzMzSzSz, FM_MzDzHoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz, YzMzDzHzMz, YzMzDzHoMz, MzDzHzMzSz, MzDzHoMzSz), dateTime,
                                FM_YzMzDzHzMzSz, FM_YzMzDzHoMzSz, FM_YzMzDzHzMz, FM_YzMzDzHoMz, FM_MzDzHzMzSz, FM_MzDzHoMzSz);
                }
                return null;
            case 16:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case DASH_COLON_DOT:
                        return matchPattern(cs(YdMdDHcMcSS, MdDHcMcSS), dateTime, FM_YdMdDHcMcSS, FM_MdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(cs(YsMsDHcMcSS, MsDHcMcSS), dateTime, FM_YsMsDHcMcSS, FM_MsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(cs(YoMoDHcMcSS, MoDHcMcSS), dateTime, FM_YoMoDHcMcSS, FM_MoDHcMcSS);
                    case SLASH_COLON:
                        return matchPattern(cs(YsMsDHcMcS, YsMsDHcM), dateTime, FM_YsMsDHcMcS, FM_YsMsDHcM);
                    case DASH_COLON:
                        return matchPattern(cs(YdMdDHcMcS, YdMdDHcM), dateTime, FM_YdMdDHcMcS, FM_YdMdDHcM);
                    case DOT_COLON:
                        return matchPattern(cs(YoMoDHcMcS, YoMoDHcM), dateTime, FM_YoMoDHcMcS, FM_YoMoDHcM);
                    case ZH_ZH_MS:
                        return matchPattern(cs(MzDzHzMzSzSz, MzDzHoMzSzSz), dateTime, FM_MzDzHzMzSzSz, FM_MzDzHoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz, YzMzDzHzMz, YzMzDzHoMz), dateTime,
                                FM_YzMzDzHzMzSz, FM_YzMzDzHoMzSz, FM_YzMzDzHzMz, FM_YzMzDzHoMz);
                }
                return null;
            case 17:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case NO_SEPARATOR:
                        return matchPattern(YMDHMSS, dateTime, FM_YMDHMSS);
                    case DASH_COLON_DOT:
                        return matchPattern(cs(YdMdDHcMcSS, MdDHcMcSS), dateTime, FM_YdMdDHcMcSS, FM_MdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(cs(YsMsDHcMcSS, MsDHcMcSS), dateTime, FM_YsMsDHcMcSS, FM_MsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(cs(YoMoDHcMcSS, MoDHcMcSS), dateTime, FM_YoMoDHcMcSS, FM_MoDHcMcSS);
                    case SLASH_COLON:
                        return matchPattern(YsMsDHcMcS, dateTime, FM_YsMsDHcMcS);
                    case DASH_COLON:
                        return matchPattern(YdMdDHcMcS, dateTime, FM_YdMdDHcMcS);
                    case DOT_COLON:
                        return matchPattern(YoMoDHcMcS, dateTime, FM_YoMoDHcMcS);
                    case ZH_ZH_MS:
                        return matchPattern(cs(YzMzDzHzMzSzSz, YzMzDzHoMzSzSz, MzDzHzMzSzSz, MzDzHoMzSzSz), dateTime,
                                FM_YzMzDzHzMzSzSz, FM_YzMzDzHoMzSzSz, FM_MzDzHzMzSzSz, FM_MzDzHoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz, YzMzDzHzMz, YzMzDzHoMz), dateTime,
                                FM_YzMzDzHzMzSz, FM_YzMzDzHoMzSz, FM_YzMzDzHzMz, FM_YzMzDzHoMz);
                }
                return null;
            case 18:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case DASH_COLON_DOT:
                        return matchPattern(cs(YdMdDHcMcSS, MdDHcMcSS), dateTime, FM_YdMdDHcMcSS, FM_MdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(cs(YsMsDHcMcSS, MsDHcMcSS), dateTime, FM_YsMsDHcMcSS, FM_MsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(cs(YoMoDHcMcSS, MoDHcMcSS), dateTime, FM_YoMoDHcMcSS, FM_MoDHcMcSS);
                    case SLASH_COLON:
                        return matchPattern(YsMsDHcMcS, dateTime, FM_YsMsDHcMcS);
                    case DASH_COLON:
                        return matchPattern(YdMdDHcMcS, dateTime, FM_YdMdDHcMcS);
                    case DOT_COLON:
                        return matchPattern(YoMoDHcMcS, dateTime, FM_YoMoDHcMcS);
                    case ZH_ZH_MS:
                        return matchPattern(cs(YzMzDzHzMzSzSz, YzMzDzHoMzSzSz, MzDzHzMzSzSz, MzDzHoMzSzSz), dateTime,
                                FM_YzMzDzHzMzSzSz, FM_YzMzDzHoMzSzSz, FM_MzDzHzMzSzSz, FM_MzDzHoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz), dateTime, FM_YzMzDzHzMzSz, FM_YzMzDzHoMzSz);
                }
                return null;
            case 19:
                // 最常用，先检测
                format = matchPattern(YdMdDHcMcS, dateTime, FM_YdMdDHcMcS);
                if (null == format) {
                    dts = separatorForDate(dateTime);
                    if (null == dts) return null;
                    switch (dts) {
                        case DASH_COLON_DOT:
                            return matchPattern(YdMdDHcMcSS, dateTime, FM_YdMdDHcMcSS);
                        case SLASH_COLON_DOT:
                            return matchPattern(YsMsDHcMcSS, dateTime, FM_YsMsDHcMcSS);
                        case DOT_COLON_DOT:
                            return matchPattern(YoMoDHcMcSS, dateTime, FM_YoMoDHcMcSS);
                        case SLASH_COLON:
                            return matchPattern(YsMsDHcMcS, dateTime, FM_YsMsDHcMcS);
                        case DOT_COLON:
                            return matchPattern(YoMoDHcMcS, dateTime, FM_YoMoDHcMcS);
                        case ZH_ZH_MS:
                            return matchPattern(cs(YzMzDzHzMzSzSz, YzMzDzHoMzSzSz, MzDzHzMzSzSz, MzDzHoMzSzSz), dateTime,
                                    FM_YzMzDzHzMzSzSz, FM_YzMzDzHoMzSzSz, FM_MzDzHzMzSzSz, FM_MzDzHoMzSzSz);
                        case ZH_ZH:
                            return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz), dateTime, FM_YzMzDzHzMzSz, FM_YzMzDzHoMzSz);
                    }
                    return null;
                } else {
                    return format;
                }
            case 20:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case DASH_COLON_DOT:
                        return matchPattern(YdMdDHcMcSS, dateTime, FM_YdMdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(YsMsDHcMcSS, dateTime, FM_YsMsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(YoMoDHcMcSS, dateTime, FM_YoMoDHcMcSS);
                    case ZH_ZH_MS:
                        return matchPattern(cs(YzMzDzHzMzSzSz, YzMzDzHoMzSzSz, MzDzHzMzSzSz, MzDzHoMzSzSz), dateTime,
                                FM_YzMzDzHzMzSzSz, FM_YzMzDzHoMzSzSz, FM_MzDzHzMzSzSz, FM_MzDzHoMzSzSz);
                    case ZH_ZH:
                        return matchPattern(cs(YzMzDzHzMzSz, YzMzDzHoMzSz), dateTime, FM_YzMzDzHzMzSz, FM_YzMzDzHoMzSz);
                }
                return null;
            case 21:
            case 22:
            case 23:
                dts = separatorForDate(dateTime);
                if (null == dts) return null;
                switch (dts) {
                    case DASH_COLON_DOT:
                        return matchPattern(YdMdDHcMcSS, dateTime, FM_YdMdDHcMcSS);
                    case SLASH_COLON_DOT:
                        return matchPattern(YsMsDHcMcSS, dateTime, FM_YsMsDHcMcSS);
                    case DOT_COLON_DOT:
                        return matchPattern(YoMoDHcMcSS, dateTime, FM_YoMoDHcMcSS);
                    case ZH_ZH_MS:
                        return matchPattern(cs(YzMzDzHzMzSzSz, YzMzDzHoMzSzSz), dateTime, FM_YzMzDzHzMzSzSz, FM_YzMzDzHoMzSzSz);
                }
                return null;
            case 24:
            case 25:
                return matchPattern(cs(YzMzDzHzMzSzSz, YzMzDzHoMzSzSz), dateTime, FM_YzMzDzHzMzSzSz, FM_YzMzDzHoMzSzSz);
        }
        return null;
    }

    private static Pattern[] cs(Pattern... compiles) {
        return compiles;
    }

    private static String matchPattern(Pattern[] compiles, String dateTime, String... dateTimeFormats) {
        for (int i = 0; i < compiles.length; i++) {
            boolean isMatch = compiles[i].matcher(dateTime).matches();
            if (isMatch) {
                return dateTimeFormats[i];
            }
        }
        return null;
    }

    private static String matchPattern(Pattern compile, String dateTime, String dateTimeFormat) {
        boolean isMatch = compile.matcher(dateTime).matches();
        return isMatch ? dateTimeFormat : null;
    }

    /**
     * 采用最简单最快速的方法判断时间字符串使用哪一种分隔符。<br>
     * 注意：<br>
     * <b>此函数不做时间串的合法性判断</b>。
     *
     * @param dateTime 时间串
     * @return 时间格式类型
     */
    private static DateTimeSeparator separatorForDate(final String dateTime) {
        int length = dateTime.length();

        DateTimeSeparator dts = null;
        switch (length) {
            case 3:
            case 4:
                /*
                 * case 3：  ----------------------------
                 * [M.d, H:m]
                 * -----------------------------------
                 * yyy（三位数的年份）
                 * M-d，M.d，M/d
                 * H:m （省略 m:s，无法与H:m区分）
                 *
                 * case 4：  ----------------------------
                 * [M月d日, m分s秒, H时m分]
                 * -----------------------------------
                 * yyyy(忽略MMdd与HHmm，与yyyy冲突)
                 * yyy年 或 M月d日，MM-d，M.dd，M/dd等
                 * HH:m 等 或 H时m分 或 H点m分 或 m分s秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? NO_SEPARATOR : dts;
            case 5:
                /*
                 * [m:s.S, H:m:s, M.d H]
                 * -----------------------------------
                 * yyyy年 或 MM月d日 或 MM-dd，MM.dd，MM/dd 或 yyy-M,yyy/M,yyy.M
                 * HH:mm 或 HH时m分 或 H点mm分 或 mm分s秒等 或 H:m:s
                 * M.d H
                 * m:s.S
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? null : checkSeparatorMore(dateTime, dts);
            case 6:
                /*
                 * [yyyy.M, H时m分s秒, M月d日H时]
                 * -----------------------------------
                 * yyyyMM （HHmmss这种省略，与yyyyMM有冲突）
                 * MM月dd日 或 yyyy-M,yyyy/M,yyyy.M  或 yyy年M月
                 * HH时mm分 或 mm分ss秒等  或 HH:m:s 等 或 H时m分s秒
                 * MM.d H 或 M月d日H时
                 * mm:s.S
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? NO_SEPARATOR : checkSeparatorMore(dateTime, dts);
            case 7:
                /*
                 * [H:m:s.S, M.d H:m, yyyy年M月, m分s秒S毫秒]
                 * -----------------------------------
                 * yyyy-MM,yyyy/MM,yyyy.MM 或 yyyy年M月 或 yyy年MM月 或 yyy-M-d, yyy/M/d, yyy.M.d
                 * HH:mm:s 等 或 H时mm分s秒
                 * MM.dd H 或 M.d H:m 或 MM月d日H时
                 * mm:ss.S 或 H:m:s.S 或 m分s秒S毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? null : checkSeparatorMore(dateTime, dts);
            case 8:
                /*
                 * [yyyy.M.d, M月d日H时m分]
                 * -----------------------------------
                 * yyyyMMdd
                 * yyyy-M-d, yyyy/M/d, yyyy.M.d 或 yyy年M月d日 或 yyyy年MM月
                 * MM.dd HH 或 M.dd H:m 或 M月d日H时m分 或 MM月dd日H时
                 * HH:mm:ss  或 H时mm分ss秒
                 * mm:ss.SS 或 H:m:ss.S 或 m分ss秒S毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? NO_SEPARATOR : checkSeparatorMore(dateTime, dts);
            case 9:
                /*
                 * [M.d H:m:s, yyyy年M月d日, H时m分s秒S毫秒]
                 * -----------------------------------
                 * yyyy-M-dd, yyyy/M/dd, yyyy.M.dd 或 yyyy年M月d日
                 * HH时mm分ss秒
                 * M.d H:m:s 或 yyy.M.d H 或 MM.dd H:m 或 M月dd日H时m分 或 MM月dd日HH时
                 * mm:ss.SSS 或 H:mm:ss.S 或 mm分ss秒S毫秒 或 H时m分s秒S毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? null : checkSeparatorMore(dateTime, dts);
            case 10:
                /*
                 * [yyyy.M.d H, M月d日H时m分s秒]
                 * -----------------------------------
                 * yyyyMMddHH
                 * yyyy-MM-dd, yyyy/MM/dd, yyyy.MM.dd 或 yyyy年MM月d日
                 * MM.dd H:mm 或 MM.d H:m:s 或 yyyy.M.d H, M月d日H时m分s秒 或 yyy年M月d日H时 或  MM月dd日H时m分
                 * H:mm:ss.SS 或 mm分ss秒SS毫秒 或 H时m分s秒SS毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? NO_SEPARATOR : checkSeparatorMore(dateTime, dts);
            case 11:
                /*
                 * [M.d H:m:s.S, yyyy年M月d日H时]
                 * -----------------------------------
                 * yyyy年MM月dd日
                 * yyyy.M.dd H 或 yyy.M.d H:m 或 MM.dd HH:mm 或 MM.d HH:m:s 或 M月dd日H时m分s秒 或 yyyy年M月d日H时  或 MM月dd日HH时m分
                 * M.d H:m:s.S
                 * H:mm:ss.SSS 或 mm分ss秒SSS毫秒 或 H时m分s秒SSS毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? null : checkSeparatorMore(dateTime, dts);
            case 12:
                /*
                 * [yyyy.M.d H:m]
                 * -----------------------------------
                 * yyyyMMddHHmm
                 * yyyy.MM.dd H 或 yyyy.M.d H:m 或 MM.dd HH:m:s 或 MM月dd日HH时mm分  或 MM月dd日H时m分s秒 或 yyyy年MM月d日H时 或 yyy年M月d日H时m分
                 * MM.d H:m:s.S
                 * HH:mm:ss.SSS 或  HH时m分s秒SSS毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? NO_SEPARATOR : checkSeparatorMore(dateTime, dts);
            case 13:
                /*
                 * [M月d日H时m分s秒S毫秒, yyyy年M月d日H时m分]
                 * -----------------------------------
                 * MM.dd HH:mm:s 或 yyyy.M.d HH:m 或 yyyy.MM.dd HH 或 yyy.M.d H:m:s 或 MM月dd日HH时m分s秒 或 yyyy年MM月d日HH时 或 yyyy年M月d日H时m分
                 * MM.d H:m:s.SS 或 M月d日H时m分s秒S毫秒
                 * HH时mm分s秒SSS毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? null : checkSeparatorMore(dateTime, dts);
            case 14:
                /*
                 * [yyyy.M.d H:m:s]
                 * -----------------------------------
                 * yyyyMMddHHmmss
                 * MM.dd HH:mm:ss 或 yyyy.MM.d HH:m 或 yyyy.M.d H:m:s 或 MM月dd日HH时mm分s秒 或 yyyy年MM月dd日HH时 或 yyyy年MM月d日H时m分 或  yyy年M月d日H时m分s秒
                 * MM.d H:m:s.SSS 或 MM月d日H时m分s秒S毫秒
                 * HH时mm分ss秒SSS毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? NO_SEPARATOR : checkSeparatorMore(dateTime, dts);
            case 15:
                /*
                 * [yyyy年M月d日H时m分s秒]
                 * -----------------------------------
                 * yyyy.MM.d HH:mm 或 yyyy.M.d H:m:ss 或 MM月dd日HH时mm分ss秒  或  yyyy年MM月d日H时mm分  或 yyyy年M月d日H时m分s秒
                 * MM.d H:m:ss.SSS 或 yyy.M.d H:m:s.S 或 MM月d日H时m分s秒SS毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? null : checkSeparatorMore(dateTime, dts);
            case 16:
                /*
                 * [yyyy.M.d H:m:s.S]
                 * -----------------------------------
                 * yyyy.MM.dd HH:mm 或 yyyy.M.dd H:m:ss 或 yyyy年MM月dd日H时mm分  或 yyyy年M月d日H时m分ss秒
                 * MM.d H:mm:ss.SSS 或 yyyy.M.d H:m:s.S 或 MM月dd日H时m分s秒SS毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? null : checkSeparatorMore(dateTime, dts);
            case 17:
                /*
                 * yyyyMMddHHmmssSSS
                 * yyyy.M.dd HH:m:ss 或 yyyy年MM月dd日HH时mm分 或 yyyy年M月d日HH时m分ss秒
                 * MM.d HH:mm:ss.SSS 或 yyyy.M.d HH:m:s.S 或 MM月dd日H时m分s秒SSS毫秒  或  yyy年M月d日H时m分s秒S毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? NO_SEPARATOR : checkSeparatorMore(dateTime, dts);
            case 18:
                /*
                 * [yyyy年M月d日H时m分s秒S毫秒]
                 * -----------------------------------
                 * yyyy.MM.dd HH:m:ss 或 yyyy年MM月d日HH时m分ss秒
                 * MM.dd HH:mm:ss.SSS 或 yyyy.MM.d HH:m:s.S 或 MM月dd日HH时m分s秒SSS毫秒 或  yyyy年M月d日H时m分s秒S毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? null : checkSeparatorMore(dateTime, dts);
            case 19:
                /*
                 * yyyy.MM.dd HH:mm:ss 或 yyyy年MM月d日HH时mm分ss秒
                 * yyyy.MM.d HH:mm:s.S 或  MM月dd日HH时mm分s秒SSS毫秒 或 yyyy年M月d日H时mm分s秒S毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? null : checkSeparatorMore(dateTime, dts);
            case 20:
                /*
                 * yyyy年MM月dd日HH时mm分ss秒
                 * yyyy.MM.dd HH:mm:s.S 或  MM月dd日HH时mm分ss秒SSS毫秒 或 yyyy年M月dd日H时mm分s秒S毫秒
                 */
                dts = checkSeparator(dateTime);
                return null == dts ? null : checkSeparatorMore(dateTime, dts);
            case 21:
            case 22:
            case 23:
                /*
                 * case 21:  ------------------------
                 * yyyy.MM.dd HH:mm:ss.S 或  yyyy年M月dd日H时mm分ss秒S毫秒
                 * case 22:  ------------------------
                 * yyyy.MM.dd HH:mm:ss.SS 或  yyyy年M月dd日H时mm分ss秒SS毫秒
                 * case 23:  ------------------------
                 * yyyy.MM.dd HH:mm:ss.SSS 或  yyyy年M月dd日H时mm分ss秒SSS毫秒
                 */
                return checkSeparatorFast(dateTime);
            case 24:
            case 25:
                /*
                 * case 24:  ------------------------
                 * yyyy年MM月dd日H时mm分ss秒SSS毫秒
                 * case 25:  ------------------------
                 * yyyy年MM月dd日HH时mm分ss秒SSS毫秒
                 */
                return ZH_ZH_MS;
        }
        return null;
    }

    /**
     * 更进一步检测分隔符类型
     *
     * @param dateTime dateTime
     * @param dts      前面初步检测的分隔符
     * @return 时间串的分隔符类型
     */
    private static DateTimeSeparator checkSeparatorMore(final String dateTime, DateTimeSeparator dts) {
        DateTimeSeparator tmpDts = null;
        switch (dts) {
            case COLON:
                tmpDts = match(dateTime, CONTAIN)
                        .when("-", v -> DASH_COLON)
                        .when("/", v -> SLASH_COLON)
                        .when(".", v -> DOT)     // 先检测 - / 再检测 . ，因为 秒与毫秒可能用 . 分隔
                        .orElse(v -> COLON);
                switch (tmpDts) {
                    case DASH_COLON:
                        return dateTime.contains(".") ? DASH_COLON_DOT : DASH_COLON;
                    case SLASH_COLON:
                        return dateTime.contains(".") ? SLASH_COLON_DOT : SLASH_COLON;
                    case DOT:
                        return getDtsFromColonDotPosition(colonDotPosition(dateTime));
                    default:
                        return COLON;
                }
            case SPACE:
                return match(dateTime, CONTAIN)
                        .when("-", v -> DASH_COLON)
                        .when("/", v -> SLASH_COLON)
                        .when(".", v -> DOT)     // 先检测 - / 再检测 . ，因为 秒与毫秒可能用 . 分隔
                        .orElse(v -> null);
            case ZH_DATE:
                return match()
                        .when(zhTimeMs(dateTime),   v -> ZH_ZH_MS)
                        .when(zhTime(dateTime),     v -> ZH_ZH)
                        .orElse(v -> ZH_DATE);
        }

        return dts;
    }

    /**
     * 检测分隔符类型
     *
     * @param dateTime 时间串
     * @return 时间串的分隔符类型
     */
    private static DateTimeSeparator checkSeparator(String dateTime) {
        return match(dateTime, CONTAIN)
                .when(":",                  v -> COLON)
                .when(" ",                  v -> SPACE)      // 没有检测到冒号，却检测到空格，说明 MM-dd HH 或 MM/dd HH 或 yyyy-MM-dd HH 这种情况
                .when("-",                  v -> DASH)
                .when("/",                  v -> SLASH)
                .when(".",                  v -> DOT)        // 先检测 - / 再检测 . ，因为 秒与毫秒可能用 . 分隔
                .when(zhDate(dateTime),     v -> ZH_DATE)
                .when(zhTimeMs(dateTime),   v -> ZH_TIME_MS)
                .when(zhTime(dateTime),     v -> ZH_TIME)
                .orElse(v -> null);
    }

    /**
     * 为带T的时间串检测分隔符类型
     *
     * @param dateTimeWithT 带T的时间串
     * @return 时间串的分隔符类型
     */
    private static DateTimeSeparator checkSeparatorWithT(String dateTimeWithT) {
        if (dateTimeWithT.contains(":")) {
            DateTimeSeparator dts = match(dateTimeWithT, CONTAIN)
                    .when("-", v -> DASH_COLON)
                    .when("/", v -> SLASH_COLON)
                    .when(".", v -> DOT)     // 先检测 - / 再检测 . ，因为 秒与毫秒可能用 . 分隔
                    .orElse(v -> COLON);

            switch (dts) {
                case DASH_COLON:
                    return dateTimeWithT.contains(".") ? DASH_COLON_DOT : DASH_COLON;
                case SLASH_COLON:
                    return dateTimeWithT.contains(".") ? SLASH_COLON_DOT : SLASH_COLON;
                case DOT:
                    DateTimeSeparator dtsFromColon = getDtsFromColonDotPosition(colonDotPosition(dateTimeWithT));
                    if (dtsFromColon == null) return null;
                    switch (dtsFromColon) {
                        case DOT_COLON:
                        case DOT_COLON_DOT:
                            return dtsFromColon;
                        default:
                            return null;    // 不合法的时间串
                    }
                default:
                    return null;        // 不合法的时间串
            }
        }

        return NO_SEPARATOR;
    }


    /**
     * 为带时区的时间串检测分隔符类型
     *
     * @param dateTime 时间串
     * @return 时间串的分隔符类型
     */
    private static DateTimeSeparator checkSeparatorWithZone(String dateTime) {
        if (dateTime.contains(":")) {
            DateTimeSeparator dts = match(dateTime, CONTAIN)
                    .when("-", v -> DASH_COLON)
                    .when("/", v -> SLASH_COLON)
                    .when(".", v -> DOT)     // 先检测 - / 再检测 . ，因为 秒与毫秒可能用 . 分隔
                    .orElse(v -> COLON);

            switch (dts) {
                case DASH_COLON:
                    return dateTime.contains(".") ? DASH_COLON_DOT : DASH_COLON;
                case SLASH_COLON:
                    return dateTime.contains(".") ? SLASH_COLON_DOT : SLASH_COLON;
                case DOT:
                    String tmpDateTime = dateTime.substring(0, dateTime.indexOf("["));
                    DateTimeSeparator dtsFromColon = getDtsFromColonDotPosition(colonDotPosition(tmpDateTime));
                    if (dtsFromColon == null) return null;
                    switch (dtsFromColon) {
                        case DOT_COLON:
                        case DOT_COLON_DOT:
                            return dtsFromColon;
                        default:
                            return null;    // 不合法的时间串
                    }
                default:
                    return null;        // 不合法的时间串
            }
        }

        return null;
    }


    /**
     * 针对特定情况的快速检测分隔符类型
     *
     * @param dateTime 时间串
     * @return 时间串的分隔符类型
     */
    private static DateTimeSeparator checkSeparatorFast(String dateTime) {
        return match(dateTime, CONTAIN)
                .when("-",              v -> DASH_COLON_DOT)
                .when("/",              v -> SLASH_COLON_DOT)
                .when(".",              v -> DOT_COLON_DOT)        // 先检测 - / 再检测 . ，因为 秒与毫秒可能用 . 分隔
                .when(zhDate(dateTime), v -> ZH_ZH_MS)
                .orElse(v -> null);
    }

    /**
     * 是否包含中文日期串
     *
     * @param dateTime 时间串
     * @return true or false
     */
    private static boolean zhDate(String dateTime) {
        return dateTime.contains("年") || dateTime.contains("月");
    }

    /**
     * 是否包含中文时间串<br>
     * 包含以下几种情况：
     * H时m分、m分s秒、H时m分s秒
     *
     * @param dateTime 时间串
     * @return true or false
     */
    private static boolean zhTime(String dateTime) {
        return dateTime.contains("时") || dateTime.contains("点") || dateTime.contains("分");
    }

    /**
     * 是否包含毫秒的中文时间串<br>
     * 包含以下几种情况：
     * m分s秒S毫秒、H时m分s秒S毫秒
     *
     * @param dateTime 时间串
     * @return true or false
     */
    private static boolean zhTimeMs(String dateTime) {
        return dateTime.contains("分") && dateTime.contains("毫秒");
    }


    private static DateTimeSeparator getDtsFromColonDotPosition(int position) {
        switch (position) {
            case 0:
                return COLON;
            case 1:
                return DOT_COLON;
            case 2:
                return COLON_DOT;
            case 3:
                return DOT_COLON_DOT;
            default:
                return null;
        }
    }

    /**
     * 冒号（:）与点（.）的位置<br>
     * <ul>
     *     <li> 0：不包含点(.)
     *     <li> 1：点(.)在冒号(:)前
     *     <li> 2：点(.)在冒号(:)后
     *     <li> 3：冒号(:)前后都有点(.)
     *     <li> 4：点(.)在冒号之间（不合法的时间串）
     * </ul>
     *
     * @param dateTimeWithColon 带有冒号的时间串
     * @return 0~4
     */
    private static int colonDotPosition(String dateTimeWithColon) {
        int cIndex = dateTimeWithColon.indexOf(":");
        int cLastIndex = dateTimeWithColon.lastIndexOf(":");
        int dIndex = dateTimeWithColon.indexOf(".");
        int dLastIndex = dateTimeWithColon.lastIndexOf(".");

        if ((dIndex > cIndex && dIndex < cLastIndex) || (dLastIndex > cIndex && dLastIndex < cLastIndex)) return 4;
        if (dIndex > 0 && dIndex < cIndex && dLastIndex > cLastIndex) return 3;
        if (dLastIndex > cLastIndex) return 2;
        if (dIndex > 0 && dIndex < cIndex) return 1;
        return 0;
    }
}
