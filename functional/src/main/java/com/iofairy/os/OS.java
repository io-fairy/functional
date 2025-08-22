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
package com.iofairy.os;

import com.iofairy.tcf.Try;
import com.iofairy.top.S;

import java.io.File;
import java.util.Locale;

/**
 * OS for getting operating system info. <br>
 * 获取操作系统信息类
 *
 * @since 0.6.0
 */
public final class OS {
    /**
     * Operating System name. <br>
     * 操作系统名称
     */
    public static final String OS_NAME = getProperty("os.name");
    /**
     * Operating System name (Lower Case). <br>
     * 操作系统名称（小写）
     */
    private static final String os = OS_NAME == null ? "" : OS_NAME.toLowerCase();

    /**
     * Operating System version. <br>
     * 操作系统版本号（即数字类型版本号）
     */
    public static final String OS_VERSION = getProperty("os.version");
    /**
     * Operating System version. <br>
     * 操作系统版本号（即数字类型版本号）
     */
    private static final String osVersion = OS_VERSION == null ? "" : OS_VERSION;
    /**
     * Default Locale
     */
    public static final Locale DEFAULT_LOCALE = Locale.getDefault();
    /**
     * Default Language
     */
    public static final String DEFAULT_LANG = DEFAULT_LOCALE == null ? "" : DEFAULT_LOCALE.getLanguage();
    /**
     * Default Country
     */
    public static final String DEFAULT_COUNTRY = DEFAULT_LOCALE == null ? "" : DEFAULT_LOCALE.getCountry();
    /**
     * Is the default language Chinese? <br>
     * 默认语言是否是中文？
     */
    public static final boolean IS_ZH_LANG = DEFAULT_LANG.equals(new Locale("zh").getLanguage());

    /**
     * Operating System architecture. But it is NOT the bitness of the OS, it is actually the bitness of the JVM. <br>
     * 操作系统架构。但是这个实际上返回的是 JVM 的架构，当64位操作系统上安装32位JVM时，会返回32位，而不是64位。
     */
    public static final String OS_ARCH = getProperty("os.arch");

    /**
     * User Home folder. <br>
     * 用户home目录
     */
    public static final String USER_HOME = getProperty("user.home");

    /**
     * User dir folder. Current project(module) root folder). <br>
     * 用户dir目录。当前项目（模块）根目录
     */
    public static final String USER_DIR = getProperty("user.dir");

    /**
     * User Language. <br>
     * 当前用户使用的语言
     */
    public static final String USER_LANGUAGE = getProperty("user.language");
    /**
     * User Timezone. <br>
     * 当前用户使用的时区
     */
    public static final String USER_TIMEZONE = getProperty("user.timezone");
    /**
     * The system-dependent Awt Toolkit class name. <br>
     * 当前操作系统Awt Toolkit类名
     */
    public static final String AWT_TOOLKIT_CLASSNAME = getProperty("awt.toolkit");

    /**
     * The system-dependent line separator string. <br>
     *
     * <p>On UNIX systems, it returns {@code "\n"};  <br>
     * on Microsoft Windows systems it returns {@code "\r\n"}.  <br> <br>
     * 当前系统行分隔符。在UNIX系统是<code>"\n"</code>；在Windows系统中是<code>"\r\n"</code>
     */
    public static final String LINE_SEPARATOR = System.lineSeparator();
    /**
     * The system-dependent default name-separator character.  <br>
     * <p>On UNIX systems the value of this field is <code>'/'</code>; <br>
     * on Microsoft Windows systems it is <code>'\'</code>.  <br><br>
     * 当前系统路径中文件夹层级分隔符。在UNIX系统中是<code>'/'</code>；在Windows系统中是<code>'\'</code>
     */
    public static final String FILE_SEPARATOR = File.separator;
    /**
     * The system-dependent path-separator character.  <br>
     * <p>On UNIX systems, this character is <code>':'</code>;   <br>
     * on Microsoft Windows systems it is <code>';'</code>.  <br><br>
     * 当前系统路径列表（路径与路径之间）的分隔符。在UNIX系统中是<code>':'</code>；在Windows系统中是<code>';'</code>
     */
    public static final String PATH_SEPARATOR = File.pathSeparator;


    /*
     **********************************************************************************
     ##################################################################################
     #######                      Java相关信息（Java Info）                       #######
     ##################################################################################
     **********************************************************************************
     */

    public static final String JAVA_HOME                        = getProperty("java.home");
    public static final String JAVA_CLASS_PATH                  = getProperty("java.class.path");
    public static final String JAVA_COMPILER                    = getProperty("java.compiler");
    public static final String JAVA_LIBRARY_PATH                = getProperty("java.library.path");

    public static final String JAVA_EXT_DIRS                    = getProperty("java.ext.dirs");
    public static final String JAVA_VM_INFO                     = getProperty("java.vm.info");
    public static final String JAVA_AWT_PRINTERJOB              = getProperty("java.awt.printerjob");
    public static final String JAVA_IO_TMPDIR                   = getProperty("java.io.tmpdir");
    public static final String JAVA_VENDOR_URL_BUG              = getProperty("java.vendor.url.bug");
    public static final String JAVA_ENDORSED_DIRS               = getProperty("java.endorsed.dirs");
    public static final String JAVA_AWT_GRAPHICSENV             = getProperty("java.awt.graphicsenv");
    public static final String JAVA_VENDOR_URL                  = getProperty("java.vendor.url");
    /*
     * version
     */
    public static final String JAVA_VERSION                     = getProperty("java.version");
    public static final String JAVA_SPECIFICATION_VERSION       = getProperty("java.specification.version");
    public static final JavaVersion J_VERSION                   = JavaVersion.of(JAVA_SPECIFICATION_VERSION);
    public static final float JAVA_VERSION_NUMBER               = Try.tcf(() -> Float.valueOf(JAVA_SPECIFICATION_VERSION), 0.0f, false);
    public static final String JAVA_CLASS_VERSION               = getProperty("java.class.version");
    public static final String JAVA_RUNTIME_VERSION             = getProperty("java.runtime.version");
    public static final String JAVA_VM_VERSION                  = getProperty("java.vm.version");
    public static final String JAVA_VM_SPECIFICATION_VERSION    = getProperty("java.vm.specification.version");
    /*
     * name
     */
    public static final String JAVA_SPECIFICATION_NAME          = getProperty("java.specification.name");
    public static final String JAVA_RUNTIME_NAME                = getProperty("java.runtime.name");
    public static final String JAVA_VM_NAME                     = getProperty("java.vm.name");
    public static final String JAVA_VM_SPECIFICATION_NAME       = getProperty("java.vm.specification.name");
    /*
     * vendor
     */
    public static final String JAVA_VENDOR                      = getProperty("java.vendor");
    public static final String JAVA_SPECIFICATION_VENDOR        = getProperty("java.specification.vendor");
    public static final String JAVA_VM_VENDOR                   = getProperty("java.vm.vendor");
    public static final String JAVA_VM_SPECIFICATION_VENDOR     = getProperty("java.vm.specification.vendor");



    /*
     **********************************************************************************
     ##################################################################################
     ##   Mac操作系统版本参考以下网址 （Mac OS version refer to the following website）：  ##
     ##            https://en.wikipedia.org/wiki/MacOS#Release_history               ##
     ##################################################################################
     **********************************************************************************
     */
    private static final boolean IS_PRE_MACOSX = os.startsWith("mac os x");

    public static final boolean IS_MACOS_SEQUOIA_6          = macOSCompare("15.6");
    public static final boolean IS_MACOS_SEQUOIA_5          = macOSCompare("15.5");
    public static final boolean IS_MACOS_SEQUOIA_4          = macOSCompare("15.4");
    public static final boolean IS_MACOS_SEQUOIA_3          = macOSCompare("15.3");
    public static final boolean IS_MACOS_SEQUOIA_2          = macOSCompare("15.2");
    public static final boolean IS_MACOS_SEQUOIA_1          = macOSCompare("15.1");
    public static final boolean IS_MACOS_SEQUOIA_0          = macOSCompare("15.0");
    public static final boolean IS_MACOS_SONOMA_7           = macOSCompare("14.7");
    public static final boolean IS_MACOS_SONOMA_6           = macOSCompare("14.6");
    public static final boolean IS_MACOS_SONOMA_5           = macOSCompare("14.5");
    public static final boolean IS_MACOS_SONOMA_4           = macOSCompare("14.4");
    public static final boolean IS_MACOS_SONOMA_3           = macOSCompare("14.3");
    public static final boolean IS_MACOS_SONOMA_2           = macOSCompare("14.2");
    public static final boolean IS_MACOS_SONOMA_1           = macOSCompare("14.1");
    public static final boolean IS_MACOS_SONOMA_0           = macOSCompare("14.0");
    public static final boolean IS_MACOS_VENTURA_7          = macOSCompare("13.7");
    public static final boolean IS_MACOS_VENTURA_6          = macOSCompare("13.6");
    public static final boolean IS_MACOS_VENTURA_5          = macOSCompare("13.5");
    public static final boolean IS_MACOS_VENTURA_4          = macOSCompare("13.4");
    public static final boolean IS_MACOS_VENTURA_3          = macOSCompare("13.3");
    public static final boolean IS_MACOS_VENTURA_2          = macOSCompare("13.2");
    public static final boolean IS_MACOS_VENTURA_1          = macOSCompare("13.1");
    public static final boolean IS_MACOS_VENTURA_0          = macOSCompare("13.0");
    public static final boolean IS_MACOS_MONTEREY_6         = macOSCompare("12.6");
    public static final boolean IS_MACOS_MONTEREY_5         = macOSCompare("12.5");
    public static final boolean IS_MACOS_MONTEREY_4         = macOSCompare("12.4");
    public static final boolean IS_MACOS_MONTEREY_3         = macOSCompare("12.3");
    public static final boolean IS_MACOS_MONTEREY_2         = macOSCompare("12.2");
    public static final boolean IS_MACOS_MONTEREY_1         = macOSCompare("12.1");
    public static final boolean IS_MACOS_MONTEREY_0         = macOSCompare("12.0");
    public static final boolean IS_MACOS_BIG_SUR_7          = macOSCompare("11.7");
    public static final boolean IS_MACOS_BIG_SUR_6          = macOSCompare("11.6");
    public static final boolean IS_MACOS_BIG_SUR_5          = macOSCompare("11.5");
    public static final boolean IS_MACOS_BIG_SUR_4          = macOSCompare("11.4");
    public static final boolean IS_MACOS_BIG_SUR_3          = macOSCompare("11.3");
    public static final boolean IS_MACOS_BIG_SUR_2          = macOSCompare("11.2");
    public static final boolean IS_MACOS_BIG_SUR_1          = macOSCompare("11.1");
    public static final boolean IS_MACOS_BIG_SUR_0          = macOSCompare("11.0");
    public static final boolean IS_MACOS_CATALINA           = macOSCompare("10.15");
    public static final boolean IS_MACOS_MOJAVE             = macOSCompare("10.14");
    public static final boolean IS_MACOS_HIGH_SIERRA        = macOSCompare("10.13");
    public static final boolean IS_MACOS_SIERRA             = macOSCompare("10.12");
    public static final boolean IS_MAC_OS_X_EL_CAPITAN      = macOSCompare("10.11");
    public static final boolean IS_MAC_OS_X_YOSEMITE        = macOSCompare("10.10");
    public static final boolean IS_MAC_OS_X_MAVERICKS       = macOSCompare("10.9");
    public static final boolean IS_MAC_OS_X_MOUNTAIN_LION   = macOSCompare("10.8");
    public static final boolean IS_MAC_OS_X_LION            = macOSCompare("10.7");
    public static final boolean IS_MAC_OS_X_SNOW_LEOPARD    = macOSCompare("10.6");
    public static final boolean IS_MAC_OS_X_LEOPARD         = macOSCompare("10.5");
    public static final boolean IS_MAC_OS_X_TIGER           = macOSCompare("10.4");
    public static final boolean IS_MAC_OS_X_PANTHER         = macOSCompare("10.3");
    public static final boolean IS_MAC_OS_X_JAGUAR          = macOSCompare("10.2");
    public static final boolean IS_MAC_OS_X_PUMA            = macOSCompare("10.1");
    public static final boolean IS_MAC_OS_X_CHEETAH         = macOSCompare("10.0");

    public static final boolean IS_MAC_15_6     = IS_MACOS_SEQUOIA_6;
    public static final boolean IS_MAC_15_5     = IS_MACOS_SEQUOIA_5;
    public static final boolean IS_MAC_15_4     = IS_MACOS_SEQUOIA_4;
    public static final boolean IS_MAC_15_3     = IS_MACOS_SEQUOIA_3;
    public static final boolean IS_MAC_15_2     = IS_MACOS_SEQUOIA_2;
    public static final boolean IS_MAC_15_1     = IS_MACOS_SEQUOIA_1;
    public static final boolean IS_MAC_15_0     = IS_MACOS_SEQUOIA_0;
    public static final boolean IS_MAC_14_7     = IS_MACOS_SONOMA_7;
    public static final boolean IS_MAC_14_6     = IS_MACOS_SONOMA_6;
    public static final boolean IS_MAC_14_5     = IS_MACOS_SONOMA_5;
    public static final boolean IS_MAC_14_4     = IS_MACOS_SONOMA_4;
    public static final boolean IS_MAC_14_3     = IS_MACOS_SONOMA_3;
    public static final boolean IS_MAC_14_2     = IS_MACOS_SONOMA_2;
    public static final boolean IS_MAC_14_1     = IS_MACOS_SONOMA_1;
    public static final boolean IS_MAC_14_0     = IS_MACOS_SONOMA_0;
    public static final boolean IS_MAC_13_7     = IS_MACOS_VENTURA_7;
    public static final boolean IS_MAC_13_6     = IS_MACOS_VENTURA_6;
    public static final boolean IS_MAC_13_5     = IS_MACOS_VENTURA_5;
    public static final boolean IS_MAC_13_4     = IS_MACOS_VENTURA_4;
    public static final boolean IS_MAC_13_3     = IS_MACOS_VENTURA_3;
    public static final boolean IS_MAC_13_2     = IS_MACOS_VENTURA_2;
    public static final boolean IS_MAC_13_1     = IS_MACOS_VENTURA_1;
    public static final boolean IS_MAC_13_0     = IS_MACOS_VENTURA_0;
    public static final boolean IS_MAC_12_6     = IS_MACOS_MONTEREY_6;
    public static final boolean IS_MAC_12_5     = IS_MACOS_MONTEREY_5;
    public static final boolean IS_MAC_12_4     = IS_MACOS_MONTEREY_4;
    public static final boolean IS_MAC_12_3     = IS_MACOS_MONTEREY_3;
    public static final boolean IS_MAC_12_2     = IS_MACOS_MONTEREY_2;
    public static final boolean IS_MAC_12_1     = IS_MACOS_MONTEREY_1;
    public static final boolean IS_MAC_12_0     = IS_MACOS_MONTEREY_0;
    public static final boolean IS_MAC_11_7     = IS_MACOS_BIG_SUR_7;
    public static final boolean IS_MAC_11_6     = IS_MACOS_BIG_SUR_6;
    public static final boolean IS_MAC_11_5     = IS_MACOS_BIG_SUR_5;
    public static final boolean IS_MAC_11_4     = IS_MACOS_BIG_SUR_4;
    public static final boolean IS_MAC_11_3     = IS_MACOS_BIG_SUR_3;
    public static final boolean IS_MAC_11_2     = IS_MACOS_BIG_SUR_2;
    public static final boolean IS_MAC_11_1     = IS_MACOS_BIG_SUR_1;
    public static final boolean IS_MAC_11_0     = IS_MACOS_BIG_SUR_0;
    public static final boolean IS_MAC_10_15    = IS_MACOS_CATALINA;
    public static final boolean IS_MAC_10_14    = IS_MACOS_MOJAVE;
    public static final boolean IS_MAC_10_13    = IS_MACOS_HIGH_SIERRA;
    public static final boolean IS_MAC_10_12    = IS_MACOS_SIERRA;
    public static final boolean IS_MAC_10_11    = IS_MAC_OS_X_EL_CAPITAN;
    public static final boolean IS_MAC_10_10    = IS_MAC_OS_X_YOSEMITE;
    public static final boolean IS_MAC_10_9     = IS_MAC_OS_X_MAVERICKS;
    public static final boolean IS_MAC_10_8     = IS_MAC_OS_X_MOUNTAIN_LION;
    public static final boolean IS_MAC_10_7     = IS_MAC_OS_X_LION;
    public static final boolean IS_MAC_10_6     = IS_MAC_OS_X_SNOW_LEOPARD;
    public static final boolean IS_MAC_10_5     = IS_MAC_OS_X_LEOPARD;
    public static final boolean IS_MAC_10_4     = IS_MAC_OS_X_TIGER;
    public static final boolean IS_MAC_10_3     = IS_MAC_OS_X_PANTHER;
    public static final boolean IS_MAC_10_2     = IS_MAC_OS_X_JAGUAR;
    public static final boolean IS_MAC_10_1     = IS_MAC_OS_X_PUMA;
    public static final boolean IS_MAC_10_0     = IS_MAC_OS_X_CHEETAH;
    /**
     * The value is {@code true} if current Operating System is Mac. <br>
     * 如果当前操作系统是Mac系统，那么值为true。
     */
    public static final boolean IS_MAC = os.startsWith("mac os");
    /**
     * The value is {@code true} if current Operating System is "macOS". <br>
     * 如果当前操作系统是macOS系统（2016年，"OS X"更名为"macOS"），那么值为true。
     */
    public static final boolean IS_MACOS = isMacOS();
    /**
     * The value is {@code true} if current Operating System is "Mac OS X" or "OS X". <br>
     * 如果当前操作系统是Mac OS X或OS X系统，那么值为true。
     */
    public static final boolean IS_MAC_OS_X = isMacOSX();



    /*
     **********************************************************************************
     ##################################################################################
     ##    windows版本参考以下网址（windows version refer to the following website）：   ##
     ##       https://en.wikipedia.org/wiki/List_of_Microsoft_Windows_versions       ##
     ##################################################################################
     **********************************************************************************
     */
    /**
     * The value is {@code true} if current Operating System is Windows. <br>
     * 如果当前操作系统是Windows系统，那么值为true。
     */
    public static final boolean IS_WINDOWS              = os.startsWith("windows");
    public static final boolean IS_WINDOWS_11           = os.startsWith("windows 11");
    public static final boolean IS_WINDOWS_10           = os.startsWith("windows 10");
    public static final boolean IS_WINDOWS_8            = os.startsWith("windows 8");
    public static final boolean IS_WINDOWS_7            = os.startsWith("windows 7");
    public static final boolean IS_WINDOWS_VISTA        = os.startsWith("windows vista");
    public static final boolean IS_WINDOWS_XP           = os.startsWith("windows xp");
    public static final boolean IS_WINDOWS_ME           = os.startsWith("windows me");
    public static final boolean IS_WINDOWS_2000         = os.startsWith("windows 2000");
    public static final boolean IS_WINDOWS_98           = os.startsWith("windows 98");
    public static final boolean IS_WINDOWS_95           = os.startsWith("windows 95");
    public static final boolean IS_WINDOWS_NT           = os.startsWith("windows nt");
    public static final boolean IS_WINDOWS_SERVER_2022  = os.startsWith("windows server 2022");
    public static final boolean IS_WINDOWS_SERVER_2019  = os.startsWith("windows server 2019");
    public static final boolean IS_WINDOWS_SERVER_2016  = os.startsWith("windows server 2016");
    public static final boolean IS_WINDOWS_SERVER_2012  = os.startsWith("windows server 2012");
    public static final boolean IS_WINDOWS_SERVER_2008  = os.startsWith("windows server 2008");
    public static final boolean IS_WINDOWS_SERVER_2003  = os.startsWith("windows server 2003");


    /*
     **********************************************************************************
     ##################################################################################
     ##             其他常用操作系统（other commonly used Operating System）             ##
     ##################################################################################
     **********************************************************************************
     */
    /**
     * The value is {@code true} if current Operating System is Linux. <br>
     * 如果当前操作系统是Linux系统，那么值为true。
     */
    public static final boolean IS_LINUX    = os.startsWith("linux");
    public static final boolean IS_FREEBSD  = os.startsWith("freebsd");
    public static final boolean IS_NETBSD   = os.startsWith("netbsd");
    public static final boolean IS_SOLARIS  = os.startsWith("solaris");
    public static final boolean IS_SUNOS    = os.startsWith("sunos");
    public static final boolean IS_HP_UX    = os.startsWith("hp-ux");
    public static final boolean IS_FUCHSIA  = os.startsWith("fuchsia");
    public static final boolean IS_OS_400   = os.startsWith("os/400");
    public static final boolean IS_OS_2     = os.startsWith("os/2");
    public static final boolean IS_Z_OS     = os.startsWith("z/os");

    /**
     * The value is {@code true} if current Operating System is Unix. <br>
     * 如果当前操作系统是 Unix 系统，那么值为true。
     */
    public static final boolean IS_UNIX = isUnix();

    private static boolean macOSCompare(String version) {
        return IS_PRE_MACOSX && macOSVerCompare(version);
    }

    private static boolean macOSVerCompare(String version) {
        if (osVersion.equals(version)) {
            return true;
        } else {
            int secondIndex = S.indexOf(osVersion, ".", 2);
            if (secondIndex != -1) {
                return osVersion.substring(0, secondIndex).equals(version);
            }
            return false;
        }
    }

    private static boolean isMacOS() {
        return IS_MACOS_SEQUOIA_6
                || IS_MACOS_SEQUOIA_5
                || IS_MACOS_SEQUOIA_4
                || IS_MACOS_SEQUOIA_3
                || IS_MACOS_SEQUOIA_2
                || IS_MACOS_SEQUOIA_1
                || IS_MACOS_SEQUOIA_0
                || IS_MACOS_SONOMA_7
                || IS_MACOS_SONOMA_6
                || IS_MACOS_SONOMA_5
                || IS_MACOS_SONOMA_4
                || IS_MACOS_SONOMA_3
                || IS_MACOS_SONOMA_2
                || IS_MACOS_SONOMA_1
                || IS_MACOS_SONOMA_0
                || IS_MACOS_VENTURA_7
                || IS_MACOS_VENTURA_6
                || IS_MACOS_VENTURA_5
                || IS_MACOS_VENTURA_4
                || IS_MACOS_VENTURA_3
                || IS_MACOS_VENTURA_2
                || IS_MACOS_VENTURA_1
                || IS_MACOS_VENTURA_0
                || IS_MACOS_MONTEREY_6
                || IS_MACOS_MONTEREY_5
                || IS_MACOS_MONTEREY_4
                || IS_MACOS_MONTEREY_3
                || IS_MACOS_MONTEREY_2
                || IS_MACOS_MONTEREY_1
                || IS_MACOS_MONTEREY_0
                || IS_MACOS_BIG_SUR_7
                || IS_MACOS_BIG_SUR_6
                || IS_MACOS_BIG_SUR_5
                || IS_MACOS_BIG_SUR_4
                || IS_MACOS_BIG_SUR_3
                || IS_MACOS_BIG_SUR_2
                || IS_MACOS_BIG_SUR_1
                || IS_MACOS_BIG_SUR_0
                || IS_MACOS_SIERRA
                || IS_MACOS_HIGH_SIERRA
                || IS_MACOS_MOJAVE
                || IS_MACOS_CATALINA;
    }

    private static boolean isMacOSX() {
        return IS_MAC_OS_X_CHEETAH
                || IS_MAC_OS_X_PUMA
                || IS_MAC_OS_X_JAGUAR
                || IS_MAC_OS_X_PANTHER
                || IS_MAC_OS_X_TIGER
                || IS_MAC_OS_X_LEOPARD
                || IS_MAC_OS_X_SNOW_LEOPARD
                || IS_MAC_OS_X_LION
                || IS_MAC_OS_X_MOUNTAIN_LION
                || IS_MAC_OS_X_MAVERICKS
                || IS_MAC_OS_X_YOSEMITE
                || IS_MAC_OS_X_EL_CAPITAN;
    }

    private static boolean isUnix() {
        return IS_MAC
                || IS_LINUX
                || IS_FREEBSD
                || IS_NETBSD
                || IS_SOLARIS
                || IS_SUNOS
                || IS_HP_UX;
    }

    private static String getProperty(String propertyKey) {
        return Try.tcf(() -> System.getProperty(propertyKey), false);
    }

}
