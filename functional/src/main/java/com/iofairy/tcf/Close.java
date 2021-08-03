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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Close resources gracefully<br>
 * 优雅的关闭资源<br><br>
 * <b>Examples:</b><br>
 * non-use of <b>{@code Close}</b>: <br>
 * <pre>
 *      Connection conn = null;
 *      PreparedStatement st = null;
 *      ResultSet rs = null;
 *      try {
 *           ...
 *      } catch (Exception e) {
 *           ...
 *      } finally {
 *          if (rs != null) {
 *              try {
 *                  rs.close();
 *              } catch (Exception e) {
 *                  e.printStackTrace();
 *              }
 *          }
 *          if (st != null) {
 *              try {
 *                  st.close();
 *              } catch (Exception e) {
 *                  e.printStackTrace();
 *              }
 *          }
 *          if (conn != null) {
 *              try {
 *                  conn.close();
 *              } catch (Exception e) {
 *                  e.printStackTrace();
 *              }
 *          }
 *      }
 * </pre>
 * use <b>{@code Close}</b>: <br>
 * <pre>
 *      Connection conn = null;
 *      PreparedStatement st = null;
 *      ResultSet rs = null;
 *      try {
 *           ...
 *      } catch (Exception e) {
 *           ...
 *      } finally {
 *          Close.close(rs);
 *          Close.close(st);
 *          Close.close(conn);
 *      }
 * </pre>
 * @param <C> object type
 * @since 0.0.3
 */
public class Close<C> {

    private final static Logger log = Logger.getLogger(Close.class.getName());

    private C c;

    private Close() {
    }

    public static <T> Close<T> of(T t) {
        Close<T> close = new Close<>();
        close.c = t;
        return close;
    }

    public static <T extends AutoCloseable> void close(final T autoCloseable) {
        close(autoCloseable, true);
    }

    public static <T extends AutoCloseable> void close(final T autoCloseable, boolean isPrintTrace) {
        close(autoCloseable, isPrintTrace, 0);
    }

    public static <T extends AutoCloseable> void close(final T autoCloseable, boolean isPrintTrace, long delaySeconds) {
        close(autoCloseable, isPrintTrace, TimeUnit.SECONDS, delaySeconds);
    }

    public static <T extends AutoCloseable> void close(final T autoCloseable, boolean isPrintTrace, TimeUnit timeUnit, long delay) {
        if (autoCloseable != null) {
            try {
                timeUnit.sleep(delay);
                autoCloseable.close();
            } catch (Throwable e) {
                if (isPrintTrace) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw, true);
                    e.printStackTrace(pw);
                    log.severe("Exception in `close()` method:\n\n" + sw.getBuffer().toString());
                }
            }
        }
    }

    public static <T extends AutoCloseable> void close(final T autoCloseable, V2<T, Throwable> catchAction) {
        close(autoCloseable, catchAction, null);
    }

    public static <T extends AutoCloseable> void close(final T autoCloseable, V2<T, Throwable> catchAction, V1<T> finallyAction) {
        close(autoCloseable, catchAction, finallyAction, 0);
    }

    public static <T extends AutoCloseable> void close(final T autoCloseable, V2<T, Throwable> catchAction, V1<T> finallyAction, long delaySeconds) {
        close(autoCloseable, catchAction, finallyAction, TimeUnit.SECONDS, delaySeconds);
    }

    public static <T extends AutoCloseable> void close(final T autoCloseable, V2<T, Throwable> catchAction, V1<T> finallyAction, TimeUnit timeUnit, long delay) {
        if (autoCloseable != null) {
            try {
                timeUnit.sleep(delay);
                autoCloseable.close();
            } catch (Throwable e) {
                if (catchAction != null) catchAction.$(autoCloseable, e);
            } finally {
                if (finallyAction != null) finallyAction.$(autoCloseable);
            }
        }
    }

    public void tcf(VT1<C, Throwable> closeAction) {
        tcf(closeAction, true);
    }

    public void tcf(VT1<C, Throwable> closeAction, boolean isPrintTrace) {
        tcf(closeAction, isPrintTrace, 0);
    }

    public void tcf(VT1<C, Throwable> closeAction, boolean isPrintTrace, long delaySeconds) {
        tcf(closeAction, isPrintTrace, TimeUnit.SECONDS, delaySeconds);
    }

    public void tcf(VT1<C, Throwable> closeAction, boolean isPrintTrace, TimeUnit timeUnit, long delay) {
        if (c != null) {
            try {
                timeUnit.sleep(delay);
                closeAction.$(c);
            } catch (Throwable e) {
                if (isPrintTrace) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw, true);
                    e.printStackTrace(pw);
                    log.severe("Exception in `tcf()` method:\n\n" + sw.getBuffer().toString());
                }
            }
        }
    }

    public void tcf(VT1<C, Throwable> closeAction, V2<C, Throwable> catchAction) {
        tcf(closeAction, catchAction, null);
    }

    public void tcf(VT1<C, Throwable> closeAction, V2<C, Throwable> catchAction, V1<C> finallyAction) {
        tcf(closeAction, catchAction, finallyAction, 0);
    }

    public void tcf(VT1<C, Throwable> closeAction, V2<C, Throwable> catchAction, V1<C> finallyAction, long delaySeconds) {
        tcf(closeAction, catchAction, finallyAction, TimeUnit.SECONDS, delaySeconds);
    }

    public void tcf(VT1<C, Throwable> closeAction, V2<C, Throwable> catchAction, V1<C> finallyAction, TimeUnit timeUnit, long delay) {
        if (c != null) {
            try {
                timeUnit.sleep(delay);
                closeAction.$(c);
            } catch (Throwable e) {
                if (catchAction != null) catchAction.$(c, e);
            } finally {
                if (finallyAction != null) finallyAction.$(c);
            }
        }
    }

}
