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
package com.iofairy.tcf;

import com.iofairy.except.TryException;
import com.iofairy.lambda.*;
import com.iofairy.si.SI;
import com.iofairy.top.G;
import com.iofairy.top.S;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Support some simple try-catch block<br>
 * 仅支持一些简单的try-catch块<br><br>
 * <b>Examples:</b><br>
 * non-use of <b>{@code Try}</b>: <br>
 * <blockquote><pre>{@code
 *      try {
 *          Thread.sleep(2000);
 *      } catch (InterruptedException e) {
 *          e.printStackTrace();
 *      }
 * }</pre></blockquote>
 * use <b>{@code Try}</b>: <br>
 * <blockquote><pre>{@code
 * Try.sleep(2000);
 * // or
 * Try.sleep(TimeUnit.SECONDS, 2);
 * // or
 * Try.tcf(() -> Thread.sleep(2000));
 * }</pre></blockquote>
 *
 * @since 0.0.4
 */
public final class Try {
    /**
     * {@code JUL(java.util.logging)} Logger<br><br>
     *
     * <b>NOTE: </b> If you use {@code logback}, but want to output the following {@code JUL} logs, you need to make some settings: <br>
     * <blockquote><pre>{@code
     * // ==================================
     * // First, reference bridge dependency
     * // ==================================
     * <dependency>
     *     <groupId>org.slf4j</groupId>
     *     <artifactId>jul-to-slf4j</artifactId>
     *     <version>${slf4j.version}</version>
     * </dependency>
     * // =====================================
     * // Second, install 'jul-to-slf4j' bridge
     * // =====================================
     * SLF4JBridgeHandler.removeHandlersForRootLogger();
     * SLF4JBridgeHandler.install();
     *
     * }</pre></blockquote>
     */
    private final static Logger log = Logger.getLogger(Try.class.getName());

    private final static String ERROR_MSG = "Exception in `tcf()` method:";

    private Try() {
    }

    /**
     * Simplify {@code try-catch} block. <br>
     * 简化冗长的 {@code try-catch} 块
     *
     * @param tryAction action that maybe throw exception
     * @see #tcf(VT0, boolean)
     */
    public static void tcf(VT0<Throwable> tryAction) {
        tcf(tryAction, true);
    }

    /**
     * Simplify {@code try-catch} block. <br>
     * 简化冗长的 {@code try-catch} 块
     *
     * @param tryAction    action that maybe throw exception
     * @param isPrintTrace isPrintTrace
     */
    public static void tcf(VT0<Throwable> tryAction, boolean isPrintTrace) {
        try {
            tryAction.$();
        } catch (Throwable e) {
            if (isPrintTrace) {
                log.severe(ERROR_MSG + "\n" + G.stackTrace(e));
            }
        }
    }

    /**
     * Simplify {@code try-catch} block. <br>
     * 简化冗长的 {@code try-catch} 块
     *
     * @param tryAction action that maybe throw exception
     * @param <R>       return value type
     * @return R object
     * @see #tcf(RT0, boolean)
     */
    public static <R> R tcf(RT0<R, Throwable> tryAction) {
        return tcf(tryAction, true);
    }

    /**
     * Simplify {@code try-catch} block. <br>
     * 简化冗长的 {@code try-catch} 块
     *
     * @param tryAction    action that maybe throw exception
     * @param isPrintTrace isPrintTrace
     * @param <R>          return value type
     * @return R object
     * @see #tcf(RT0, Object, boolean)
     */
    public static <R> R tcf(RT0<R, Throwable> tryAction, boolean isPrintTrace) {
        return tcf(tryAction, null, isPrintTrace);
    }

    /**
     * Simplify {@code try-catch} block. <br>
     * 简化冗长的 {@code try-catch} 块
     *
     * @param tryAction     action that maybe throw exception
     * @param defaultReturn default return value when occur exception
     * @param isPrintTrace  isPrintTrace
     * @param <R>           return value type
     * @return R object
     */
    public static <R> R tcf(RT0<R, Throwable> tryAction, R defaultReturn, boolean isPrintTrace) {
        try {
            return tryAction.$();
        } catch (Throwable e) {
            if (isPrintTrace) {
                log.severe(ERROR_MSG + "\n" + G.stackTrace(e));
            }
        }
        return defaultReturn;
    }

    /**
     * Simplify {@code try-catch} block. <br>
     * 简化冗长的 {@code try-catch} 块
     *
     * @param tryAction   action that maybe throw exception
     * @param catchAction catchAction
     */
    public static void tcf(VT0<Throwable> tryAction, V1<Throwable> catchAction) {
        try {
            tryAction.$();
        } catch (Throwable e) {
            if (catchAction != null) catchAction.$(e);
        }
    }

    /**
     * Simplify {@code try-catch} block. <br>
     * 简化冗长的 {@code try-catch} 块
     *
     * @param tryAction   action that maybe throw exception
     * @param catchAction catchAction
     * @param <R>         return value type
     * @return R object
     * @see #tcf(RT0, Object, V1)
     */
    public static <R> R tcf(RT0<R, Throwable> tryAction, V1<Throwable> catchAction) {
        return tcf(tryAction, null, catchAction);
    }

    /**
     * Simplify {@code try-catch} block. <br>
     * 简化冗长的 {@code try-catch} 块
     *
     * @param tryAction     action that maybe throw exception
     * @param defaultReturn default return value when occur exception
     * @param catchAction   catchAction
     * @param <R>           return value type
     * @return R object
     */
    public static <R> R tcf(RT0<R, Throwable> tryAction, R defaultReturn, V1<Throwable> catchAction) {
        try {
            return tryAction.$();
        } catch (Throwable e) {
            if (catchAction != null) catchAction.$(e);
        }
        return defaultReturn;
    }

    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     *********************           New `tcf()` methods            *********************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/

    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    /*==============================================================================
     ****************    `tcft()` methods for `TryType.TRACE_LOG`    ***************
     ==============================================================================*/

    /**
     * Simplify {@code try-catch} block ({@link TryType#TRACE_LOG}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#TRACE_LOG})
     *
     * @param tryAction action that maybe throw exception
     */
    public static void tcft(VT0<Throwable> tryAction) {
        tcf(tryAction, TryType.TRACE_LOG, null, null);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#TRACE_LOG}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#TRACE_LOG})
     *
     * @param tryAction   action that maybe throw exception
     * @param msgTemplate message template
     * @param args        dynamic arguments
     */
    public static void tcft(VT0<Throwable> tryAction, String msgTemplate, Object... args) {
        tcf(tryAction, TryType.TRACE_LOG, null, msgTemplate, args);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#TRACE_LOG}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#TRACE_LOG})
     *
     * @param tryAction     action that maybe throw exception
     * @param defaultReturn default return value when occur exception
     * @param msgTemplate   message template
     * @param args          dynamic arguments
     * @param <R>           return value type
     * @return R object
     */
    public static <R> R tcft(RT0<R, Throwable> tryAction, R defaultReturn, String msgTemplate, Object... args) {
        return tcf(tryAction, defaultReturn, TryType.TRACE_LOG, null, msgTemplate, args);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#TRACE_LOG}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#TRACE_LOG})
     *
     * @param tryAction     action that maybe throw exception
     * @param defaultReturn default return value when occur exception
     * @param <R>           return value type
     * @return R object
     */
    public static <R> R tcft(RT0<R, Throwable> tryAction, R defaultReturn) {
        return tcf(tryAction, defaultReturn, TryType.TRACE_LOG, null, null);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#TRACE_LOG}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#TRACE_LOG})
     *
     * @param tryAction action that maybe throw exception
     * @param <R>       return value type
     * @return R object
     */
    public static <R> R tcft(RT0<R, Throwable> tryAction) {
        return tcf(tryAction, null, TryType.TRACE_LOG, null, null);
    }


    /*==============================================================================
     ****************    `tcfl()` methods for `TryType.LOGGING`    ***************
     ==============================================================================*/

    /**
     * Simplify {@code try-catch} block ({@link TryType#LOGGING}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#LOGGING})
     *
     * @param tryAction action that maybe throw exception
     */
    public static void tcfl(VT0<Throwable> tryAction) {
        tcf(tryAction, TryType.LOGGING, null, null);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#LOGGING}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#LOGGING})
     *
     * @param tryAction   action that maybe throw exception
     * @param msgTemplate message template
     * @param args        dynamic arguments
     */
    public static void tcfl(VT0<Throwable> tryAction, String msgTemplate, Object... args) {
        tcf(tryAction, TryType.LOGGING, null, msgTemplate, args);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#LOGGING}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#LOGGING})
     *
     * @param tryAction     action that maybe throw exception
     * @param defaultReturn default return value when occur exception
     * @param msgTemplate   message template
     * @param args          dynamic arguments
     * @param <R>           return value type
     * @return R object
     */
    public static <R> R tcfl(RT0<R, Throwable> tryAction, R defaultReturn, String msgTemplate, Object... args) {
        return tcf(tryAction, defaultReturn, TryType.LOGGING, null, msgTemplate, args);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#LOGGING}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#LOGGING})
     *
     * @param tryAction     action that maybe throw exception
     * @param defaultReturn default return value when occur exception
     * @param <R>           return value type
     * @return R object
     */
    public static <R> R tcfl(RT0<R, Throwable> tryAction, R defaultReturn) {
        return tcf(tryAction, defaultReturn, TryType.LOGGING, null, null);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#LOGGING}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#LOGGING})
     *
     * @param tryAction action that maybe throw exception
     * @param <R>       return value type
     * @return R object
     */
    public static <R> R tcfl(RT0<R, Throwable> tryAction) {
        return tcf(tryAction, null, TryType.LOGGING, null, null);
    }


    /*==============================================================================
     ****************    `tcfr()` methods for `TryType.RETHROW`    ***************
     ==============================================================================*/

    /**
     * Simplify {@code try-catch} block ({@link TryType#RETHROW}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#RETHROW})
     *
     * @param tryAction action that maybe throw exception
     * @throws TryException when {@code tryAction} throw exception
     */
    public static void tcfr(VT0<Throwable> tryAction) {
        tcf(tryAction, TryType.RETHROW, null, null);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#RETHROW}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#RETHROW})
     *
     * @param tryAction   action that maybe throw exception
     * @param msgTemplate message template
     * @param args        dynamic arguments
     * @throws TryException when {@code tryAction} throw exception
     */
    public static void tcfr(VT0<Throwable> tryAction, String msgTemplate, Object... args) {
        tcf(tryAction, TryType.RETHROW, null, msgTemplate, args);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#RETHROW}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#RETHROW})
     *
     * @param tryAction     action that maybe throw exception
     * @param defaultReturn default return value when occur exception
     * @param msgTemplate   message template
     * @param args          dynamic arguments
     * @param <R>           return value type
     * @return R object
     * @throws TryException when {@code tryAction} throw exception
     */
    public static <R> R tcfr(RT0<R, Throwable> tryAction, R defaultReturn, String msgTemplate, Object... args) {
        return tcf(tryAction, defaultReturn, TryType.RETHROW, null, msgTemplate, args);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#RETHROW}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#RETHROW})
     *
     * @param tryAction     action that maybe throw exception
     * @param defaultReturn default return value when occur exception
     * @param <R>           return value type
     * @return R object
     * @throws TryException when {@code tryAction} throw exception
     */
    public static <R> R tcfr(RT0<R, Throwable> tryAction, R defaultReturn) {
        return tcf(tryAction, defaultReturn, TryType.RETHROW, null, null);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#RETHROW}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#RETHROW})
     *
     * @param tryAction action that maybe throw exception
     * @param <R>       return value type
     * @return R object
     * @throws TryException when {@code tryAction} throw exception
     */
    public static <R> R tcfr(RT0<R, Throwable> tryAction) {
        return tcf(tryAction, null, TryType.RETHROW, null, null);
    }

    /*==============================================================================
     ****************    `tcfs()` methods for `TryType.SILENT`    ***************
     ==============================================================================*/

    /**
     * Simplify {@code try-catch} block ({@link TryType#SILENT}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#SILENT})
     *
     * @param tryAction action that maybe throw exception
     */
    public static void tcfs(VT0<Throwable> tryAction) {
        tcf(tryAction, TryType.SILENT, null, null);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#SILENT}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#SILENT})
     *
     * @param tryAction     action that maybe throw exception
     * @param defaultReturn default return value when occur exception
     * @param <R>           return value type
     * @return R object
     */
    public static <R> R tcfs(RT0<R, Throwable> tryAction, R defaultReturn) {
        return tcf(tryAction, defaultReturn, TryType.SILENT, null, null);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#SILENT}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#SILENT})
     *
     * @param tryAction action that maybe throw exception
     * @param <R>       return value type
     * @return R object
     */
    public static <R> R tcfs(RT0<R, Throwable> tryAction) {
        return tcf(tryAction, null, TryType.SILENT, null, null);
    }

    /*==============================================================================
     ****************    `tcfc()` methods for `TryType.CATCH_DO`    ***************
     ==============================================================================*/

    /**
     * Simplify {@code try-catch} block ({@link TryType#CATCH_DO}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#CATCH_DO})
     *
     * @param tryAction   action that maybe throw exception
     * @param catchAction do something when an exception is caught
     */
    public static void tcfc(VT0<Throwable> tryAction, V1<Throwable> catchAction) {
        tcf(tryAction, TryType.CATCH_DO, catchAction, null);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#CATCH_DO}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#CATCH_DO})
     *
     * @param tryAction action that maybe throw exception
     */
    public static void tcfc(VT0<Throwable> tryAction) {
        tcf(tryAction, TryType.CATCH_DO, null, null);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#CATCH_DO}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#CATCH_DO})
     *
     * @param tryAction     action that maybe throw exception
     * @param defaultReturn default return value when occur exception
     * @param catchAction   do something when an exception is caught
     * @param <R>           return value type
     * @return R object
     */
    public static <R> R tcfc(RT0<R, Throwable> tryAction, R defaultReturn, V1<Throwable> catchAction) {
        return tcf(tryAction, defaultReturn, TryType.CATCH_DO, catchAction, null);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#CATCH_DO}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#CATCH_DO})
     *
     * @param tryAction   action that maybe throw exception
     * @param catchAction do something when an exception is caught
     * @param <R>         return value type
     * @return R object
     */
    public static <R> R tcfc(RT0<R, Throwable> tryAction, V1<Throwable> catchAction) {
        return tcf(tryAction, null, TryType.CATCH_DO, catchAction, null);
    }

    /**
     * Simplify {@code try-catch} block ({@link TryType#CATCH_DO}) <br>
     * 简化冗长的 {@code try-catch} 块 ({@link TryType#CATCH_DO})
     *
     * @param tryAction action that maybe throw exception
     * @param <R>       return value type
     * @return R object
     */
    public static <R> R tcfc(RT0<R, Throwable> tryAction) {
        return tcf(tryAction, null, TryType.CATCH_DO, null, null);
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    /*##############################################################################
     ===============================================================================
     ##############################################################################*/

    /**
     * Simplify {@code try-catch} block. <br>
     * 简化冗长的 {@code try-catch} 块
     *
     * @param tryAction   action that maybe throw exception
     * @param tryType     {@link TryType}
     * @param catchAction do something when an exception is caught
     * @param msgTemplate message template
     * @param args        dynamic arguments
     * @throws TryException when {@code tryAction} throw exception and {@code tryType} is {@link TryType#RETHROW}
     */
    public static void tcf(VT0<Throwable> tryAction, TryType tryType, V1<Throwable> catchAction, String msgTemplate, Object... args) {
        try {
            tryAction.$();
        } catch (Throwable e) {
            catchSwitch(e, tryType, catchAction, msgTemplate, args);
        }
    }

    /**
     * Simplify {@code try-catch} block. <br>
     * 简化冗长的 {@code try-catch} 块
     *
     * @param tryAction     action that maybe throw exception
     * @param defaultReturn default return value when occur exception
     * @param tryType       {@link TryType}
     * @param catchAction   do something when an exception is caught
     * @param msgTemplate   message template
     * @param args          dynamic arguments
     * @param <R>           return value type
     * @return R object
     * @throws TryException when {@code tryAction} throw exception and {@code tryType} is {@link TryType#RETHROW}
     */
    public static <R> R tcf(RT0<R, Throwable> tryAction, R defaultReturn, TryType tryType, V1<Throwable> catchAction, String msgTemplate, Object... args) {
        try {
            return tryAction.$();
        } catch (Throwable e) {
            catchSwitch(e, tryType, catchAction, msgTemplate, args);
        }
        return defaultReturn;
    }

    private static void catchSwitch(Throwable e, TryType tryType, V1<Throwable> catchAction, String msgTemplate, Object[] args) {
        if (tryType == null) tryType = TryType.TRACE_LOG;
        String logMsg;

        switch (tryType) {
            case TRACE_LOG:
                logMsg = (S.isEmpty(msgTemplate) ? ERROR_MSG : SI.$(msgTemplate, args)) + "\n" + G.stackTrace(e);
                log.severe(logMsg);
                break;
            case CATCH_DO:
                if (catchAction != null) catchAction.$(e);
                break;
            case LOGGING:
                logMsg = S.isEmpty(msgTemplate) ? ERROR_MSG + "\n" + G.stackTrace(e) : SI.$(msgTemplate, args);
                log.severe(logMsg);
                break;
            case RETHROW:
                throw new TryException(e, msgTemplate, args);
                // default: SILENT
        }
    }


    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     *********************            `sleep()` methods             *********************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/

    /**
     * Performs a {@link #sleep(long, boolean)}
     *
     * @param milliSeconds the length of time to sleep in millisecond
     * @since 0.1.0
     */
    public static void sleep(long milliSeconds) {
        sleep(milliSeconds, true);
    }

    /**
     * Performs a {@link #sleep(TimeUnit, long, boolean)}
     *
     * @param milliSeconds the length of time to sleep in millisecond
     * @param isPrintTrace isPrintTrace
     * @since 0.1.0
     */
    public static void sleep(long milliSeconds, boolean isPrintTrace) {
        sleep(TimeUnit.MILLISECONDS, milliSeconds, isPrintTrace);
    }

    /**
     * Performs a {@link #sleep(TimeUnit, long, boolean)}
     *
     * @param timeUnit timeUnit
     * @param timeout  the minimum time to sleep. If less than or equal to zero, do not sleep at all.
     * @since 0.1.0
     */
    public static void sleep(TimeUnit timeUnit, long timeout) {
        sleep(timeUnit, timeout, true);
    }

    /**
     * Performs a {@link TimeUnit#sleep(long)} using this time unit
     *
     * @param timeUnit     timeUnit
     * @param timeout      the minimum time to sleep. If less than or equal to zero, do not sleep at all.
     * @param isPrintTrace isPrintTrace
     * @since 0.1.0
     */
    public static void sleep(TimeUnit timeUnit, long timeout, boolean isPrintTrace) {
        try {
            timeUnit.sleep(timeout);
        } catch (Throwable e) {
            if (isPrintTrace) {
                log.severe("Exception in `sleep()` method:\n" + G.stackTrace(e));
            }
        }
    }

}
