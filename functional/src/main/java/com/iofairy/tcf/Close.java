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

import java.util.logging.Logger;

/**
 * Close resources gracefully<br>
 * 优雅的关闭资源<br><br>
 * <b>Examples:</b><br>
 * non-use of <b>{@code Close}</b>: <br>
 * <blockquote><pre>{@code
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
 * }</pre></blockquote>
 * use <b>{@code Close}</b>: <br>
 * <blockquote><pre>{@code
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
 * }</pre></blockquote>
 *
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

    /**
     * Closing resources. <br>
     * 用于简化关闭资源的操作
     *
     * @param autoCloseable resources that {@code implements} {@link AutoCloseable}
     * @param <T>           type of resources
     * @see #close(AutoCloseable, boolean)
     */
    public static <T extends AutoCloseable> void close(final T autoCloseable) {
        close(autoCloseable, true);
    }

    /**
     * Closing resources. <br>
     * 用于简化关闭资源的操作
     *
     * @param autoCloseable resources that {@code implements} {@link AutoCloseable}
     * @param isPrintTrace  isPrintTrace
     * @param <T>           type of resources
     */
    public static <T extends AutoCloseable> void close(final T autoCloseable, boolean isPrintTrace) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Throwable e) {
                if (isPrintTrace) {
                    log.severe("Exception in `close()` method:\n" + G.stackTrace(e));
                }
            }
        }
    }

    /**
     * Simplify {@code try-catch-finally} block when closing resources. <br>
     * 用于简化关闭资源的操作
     *
     * @param autoCloseable resources that {@code implements} {@link AutoCloseable}
     * @param catchAction   catchAction
     * @param <T>           type of resources
     * @see #close(AutoCloseable, V2, V1)
     */
    public static <T extends AutoCloseable> void close(final T autoCloseable, V2<T, Throwable> catchAction) {
        close(autoCloseable, catchAction, null);
    }

    /**
     * Simplify {@code try-catch-finally} block when closing resources. <br>
     * 用于简化关闭资源的操作
     *
     * @param autoCloseable resources that {@code implements} {@link AutoCloseable}
     * @param catchAction   catchAction
     * @param finallyAction finallyAction
     * @param <T>           type of resources
     */
    public static <T extends AutoCloseable> void close(final T autoCloseable, V2<T, Throwable> catchAction, V1<T> finallyAction) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Throwable e) {
                if (catchAction != null) catchAction.$(autoCloseable, e);
            } finally {
                if (finallyAction != null) finallyAction.$(autoCloseable);
            }
        }
    }

    /**
     * Closing multi-resources that {@code implements} {@link AutoCloseable}. <br>
     * 关闭多个实现了 {@link AutoCloseable} 资源
     *
     * @param autoCloseables autoCloseables
     * @since 0.1.1
     */
    public static void closeAll(final AutoCloseable... autoCloseables) {
        closeAll(true, autoCloseables);
    }

    /**
     * Closing multi-resources that {@code implements} {@link AutoCloseable}. <br>
     * 关闭多个实现了 {@link AutoCloseable} 资源
     *
     * @param isPrintTrace   isPrintTrace
     * @param autoCloseables autoCloseables
     * @since 0.1.1
     */
    public static void closeAll(boolean isPrintTrace, final AutoCloseable... autoCloseables) {
        if (!G.isEmpty(autoCloseables)) {
            for (AutoCloseable autoCloseable : autoCloseables) {
                if (autoCloseable != null) {
                    try {
                        autoCloseable.close();
                    } catch (Throwable e) {
                        if (isPrintTrace) {
                            log.severe("Exception in `closeAll()` method:\n" + G.stackTrace(e));
                        }
                    }
                }
            }
        }
    }

    /**
     * Closing resources that not {@code implements} {@link AutoCloseable}. <br>
     * 用于简化关闭资源的操作，且这些资源未实现 {@link AutoCloseable} 接口
     *
     * @param closeAction closeAction
     * @see #tcf(VT1, boolean)
     */
    public void tcf(VT1<C, Throwable> closeAction) {
        tcf(closeAction, true);
    }

    /**
     * Closing resources that not {@code implements} {@link AutoCloseable}. <br>
     * 用于简化关闭资源的操作，且这些资源未实现 {@link AutoCloseable} 接口
     *
     * @param closeAction  closeAction
     * @param isPrintTrace isPrintTrace
     */
    public void tcf(VT1<C, Throwable> closeAction, boolean isPrintTrace) {
        if (c != null) {
            try {
                closeAction.$(c);
            } catch (Throwable e) {
                if (isPrintTrace) {
                    log.severe("Exception in `tcf()` method:\n" + G.stackTrace(e));
                }
            }
        }
    }

    /**
     * Simplify {@code try-catch-finally} block when closing resources that not {@code implements} {@link AutoCloseable}. <br>
     * 用于简化关闭资源的操作，且这些资源未实现 {@link AutoCloseable} 接口
     *
     * @param closeAction closeAction
     * @param catchAction catchAction
     * @see #tcf(VT1, V2, V1)
     */
    public void tcf(VT1<C, Throwable> closeAction, V2<C, Throwable> catchAction) {
        tcf(closeAction, catchAction, null);
    }

    /**
     * Simplify {@code try-catch-finally} block when closing resources that not {@code implements} {@link AutoCloseable}. <br>
     * 用于简化关闭资源的操作，且这些资源未实现 {@link AutoCloseable} 接口
     *
     * @param closeAction   closeAction
     * @param catchAction   catchAction
     * @param finallyAction finallyAction
     */
    public void tcf(VT1<C, Throwable> closeAction, V2<C, Throwable> catchAction, V1<C> finallyAction) {
        if (c != null) {
            try {
                closeAction.$(c);
            } catch (Throwable e) {
                if (catchAction != null) catchAction.$(c, e);
            } finally {
                if (finallyAction != null) finallyAction.$(c);
            }
        }
    }

}
