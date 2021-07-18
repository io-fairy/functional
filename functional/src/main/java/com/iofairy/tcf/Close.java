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
 *          Close.of(rs).run(ResultSet::close);
 *          Close.of(st).run(Statement::close);
 *          Close.of(conn).run(Connection::close);
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

    public void tcf(VT1<C, Throwable> closeAction) {
        if (c != null) {
            try {
                closeAction.$(c);
            } catch (Throwable e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw, true);
                e.printStackTrace(pw);
                log.severe("Exception in `run` method:\n\n" + sw.getBuffer().toString());
            }
        }
    }

    public void tcf(VT1<C, Throwable> closeAction, V2<C, Throwable> catchAction) {
        if (c != null) {
            try {
                closeAction.$(c);
            } catch (Throwable e) {
                if (catchAction != null) catchAction.$(c, e);
            }
        }
    }

    public void tcf(VT1<C, Throwable> closeAction, V2<C,Throwable> catchAction, V1<C> finallyAction) {
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
