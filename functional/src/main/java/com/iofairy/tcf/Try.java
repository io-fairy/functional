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

import com.iofairy.lambda.*;
import com.iofairy.top.G;

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
public class Try {

    private final static Logger log = Logger.getLogger(Try.class.getName());

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
                log.severe("Exception in `tcf()` method:\n" + G.stackTrace(e));
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
                log.severe("Exception in `tcf()` method:\n" + G.stackTrace(e));
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
