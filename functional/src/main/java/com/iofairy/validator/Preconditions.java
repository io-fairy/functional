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
package com.iofairy.validator;

import com.iofairy.except.*;
import com.iofairy.except.ai.*;
import com.iofairy.si.SI;
import com.iofairy.top.*;

import java.io.FileNotFoundException;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.*;

import static com.iofairy.validator.ErrorMsgType.*;

/**
 * Pre-check whether the parameters meet the conditions. <br><br>
 *
 * <b>NOTE: </b>You'd better import this class first using <b>static import</b>:
 * <blockquote><pre>{@code
 * import static com.iofairy.validator.Preconditions.*;
 * }</pre></blockquote>
 *
 * @since 0.6.0
 */
public final class Preconditions {

    private final static String[] NULL_STR_ARRAY = null;

    private Preconditions() {
    }

    public static Object[] args(Object... objs) {
        return O.args(objs);
    }

    public static CharSequence[] args(CharSequence... cs) {
        return O.args(cs);
    }

    public static String[] args(String... ss) {
        return O.args(ss);
    }

    /*==============================================================================
     *************    throw ConditionsNotMetException with 400 code    *************
     ==============================================================================*/
    public static void checkBlank(CharSequence cs) {
        if (S.isBlank(cs)) {
            throwConditionsNotMetException400(getErrorMsg(BLANK, NULL_STR_ARRAY));
        }
    }

    public static void checkBlank(CharSequence cs, String msgTemplate, Object... objs) {
        if (S.isBlank(cs)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkBlank(CharSequence cs, String[] paramNames) {
        if (S.isBlank(cs)) {
            throwConditionsNotMetException400(getErrorMsg(BLANK, paramNames));
        }
    }

    public static void checkEmpty(Object o) {
        if (G.isEmpty(o)) {
            throwConditionsNotMetException400(getErrorMsg(EMPTY, NULL_STR_ARRAY));
        }
    }

    public static void checkEmpty(Object o, String msgTemplate, Object... objs) {
        if (G.isEmpty(o)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkEmpty(Object o, String[] paramNames) {
        if (G.isEmpty(o)) {
            throwConditionsNotMetException400(getErrorMsg(EMPTY, paramNames));
        }
    }

    public static void checkNull(Object o) {
        if (o == null) {
            throwConditionsNotMetException400(getErrorMsg(NULL, NULL_STR_ARRAY));
        }
    }

    public static void checkNull(Object o, String msgTemplate, Object... objs) {
        if (o == null) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkNull(Object o, String[] paramNames) {
        if (o == null) {
            throwConditionsNotMetException400(getErrorMsg(NULL, paramNames));
        }
    }

    public static void checkNotBlank(CharSequence cs) {
        if (!S.isBlank(cs)) {
            throwConditionsNotMetException400(getErrorMsg(NOT_BLANK, NULL_STR_ARRAY));
        }
    }

    public static void checkNotBlank(CharSequence cs, String msgTemplate, Object... objs) {
        if (!S.isBlank(cs)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkNotBlank(CharSequence cs, String[] paramNames) {
        if (!S.isBlank(cs)) {
            throwConditionsNotMetException400(getErrorMsg(NOT_BLANK, paramNames));
        }
    }

    public static void checkNotEmpty(Object o) {
        if (!G.isEmpty(o)) {
            throwConditionsNotMetException400(getErrorMsg(NOT_EMPTY, NULL_STR_ARRAY));
        }
    }

    public static void checkNotEmpty(Object o, String msgTemplate, Object... objs) {
        if (!G.isEmpty(o)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkNotEmpty(Object o, String[] paramNames) {
        if (!G.isEmpty(o)) {
            throwConditionsNotMetException400(getErrorMsg(NOT_EMPTY, paramNames));
        }
    }

    public static void checkNotNull(Object o) {
        if (o != null) {
            throwConditionsNotMetException400(getErrorMsg(NOT_NULL, NULL_STR_ARRAY));
        }
    }

    public static void checkNotNull(Object o, String msgTemplate, Object... objs) {
        if (o != null) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkNotNull(Object o, String[] paramNames) {
        if (o != null) {
            throwConditionsNotMetException400(getErrorMsg(NOT_NULL, paramNames));
        }
    }

    public static void checkHasBlank(CharSequence[] cs) {
        if (S.hasBlank(cs)) {
            String errorMsg = getErrorMsg(HAS_BLANK, NULL_STR_ARRAY) + getErrorParamsString(HAS_BLANK, cs, NULL_STR_ARRAY);
            throwConditionsNotMetException400(errorMsg);
        }
    }

    public static void checkHasBlank(CharSequence[] cs, String msgTemplate, Object... objs) {
        if (S.hasBlank(cs)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkHasBlank(CharSequence[] cs, String[] paramNames) {
        if (S.hasBlank(cs)) {
            String errorMsg = getErrorMsg(HAS_BLANK, paramNames) + getErrorParamsString(HAS_BLANK, cs, paramNames);
            throwConditionsNotMetException400(errorMsg);
        }
    }

    public static void checkHasEmpty(Object[] os) {
        if (G.hasEmpty(os)) {
            String errorMsg = getErrorMsg(HAS_EMPTY, NULL_STR_ARRAY) + getErrorParamsString(HAS_EMPTY, os, NULL_STR_ARRAY);
            throwConditionsNotMetException400(errorMsg);
        }
    }

    public static void checkHasEmpty(Object[] os, String msgTemplate, Object... objs) {
        if (G.hasEmpty(os)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkHasEmpty(Object[] os, String[] paramNames) {
        if (G.hasEmpty(os)) {
            String errorMsg = getErrorMsg(HAS_EMPTY, paramNames) + getErrorParamsString(HAS_EMPTY, os, paramNames);
            throwConditionsNotMetException400(errorMsg);
        }
    }

    public static void checkHasNull(Object[] os) {
        if (G.hasNull(os)) {
            String errorMsg = getErrorMsg(HAS_NULL, NULL_STR_ARRAY) + getErrorParamsString(HAS_NULL, os, NULL_STR_ARRAY);
            throwConditionsNotMetException400(errorMsg);
        }
    }

    public static void checkHasNull(Object[] os, String msgTemplate, Object... objs) {
        if (G.hasNull(os)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkHasNull(Object[] os, String[] paramNames) {
        if (G.hasNull(os)) {
            String errorMsg = getErrorMsg(HAS_NULL, paramNames) + getErrorParamsString(HAS_NULL, os, paramNames);
            throwConditionsNotMetException400(errorMsg);
        }
    }

    public static void checkHasBlank(Collection<? extends CharSequence> cs) {
        if (S.hasBlank(cs)) {
            String errorMsg = getErrorMsg(HAS_BLANK, NULL_STR_ARRAY) + getErrorParamsString(HAS_BLANK, cs, NULL_STR_ARRAY);
            throwConditionsNotMetException400(errorMsg);
        }
    }

    public static void checkHasBlank(Collection<? extends CharSequence> cs, String msgTemplate, Object... objs) {
        if (S.hasBlank(cs)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkHasBlank(Collection<? extends CharSequence> cs, String[] paramNames) {
        if (S.hasBlank(cs)) {
            String errorMsg = getErrorMsg(HAS_BLANK, paramNames) + getErrorParamsString(HAS_BLANK, cs, paramNames);
            throwConditionsNotMetException400(errorMsg);
        }
    }

    public static void checkHasEmpty(Collection<?> os) {
        if (G.hasEmpty(os)) {
            String errorMsg = getErrorMsg(HAS_EMPTY, NULL_STR_ARRAY) + getErrorParamsString(HAS_EMPTY, os, NULL_STR_ARRAY);
            throwConditionsNotMetException400(errorMsg);
        }
    }

    public static void checkHasEmpty(Collection<?> os, String msgTemplate, Object... objs) {
        if (G.hasEmpty(os)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkHasEmpty(Collection<?> os, String[] paramNames) {
        if (G.hasEmpty(os)) {
            String errorMsg = getErrorMsg(HAS_EMPTY, paramNames) + getErrorParamsString(HAS_EMPTY, os, paramNames);
            throwConditionsNotMetException400(errorMsg);
        }
    }

    public static void checkHasNull(Collection<?> os) {
        if (G.hasNull(os)) {
            String errorMsg = getErrorMsg(HAS_NULL, NULL_STR_ARRAY) + getErrorParamsString(HAS_NULL, os, NULL_STR_ARRAY);
            throwConditionsNotMetException400(errorMsg);
        }
    }

    public static void checkHasNull(Collection<?> os, String msgTemplate, Object... objs) {
        if (G.hasNull(os)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkHasNull(Collection<?> os, String[] paramNames) {
        if (G.hasNull(os)) {
            String errorMsg = getErrorMsg(HAS_NULL, paramNames) + getErrorParamsString(HAS_NULL, os, paramNames);
            throwConditionsNotMetException400(errorMsg);
        }
    }


    public static void checkAllBlank(CharSequence[] cs) {
        if (S.allBlank(cs)) {
            throwConditionsNotMetException400(getErrorMsg(ALL_BLANK, NULL_STR_ARRAY));
        }
    }

    public static void checkAllBlank(CharSequence[] cs, String msgTemplate, Object... objs) {
        if (S.allBlank(cs)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkAllBlank(CharSequence[] cs, String[] paramNames) {
        if (S.allBlank(cs)) {
            throwConditionsNotMetException400(getErrorMsg(ALL_BLANK, paramNames));
        }
    }

    public static void checkAllEmpty(Object[] os) {
        if (G.allEmpty(os)) {
            throwConditionsNotMetException400(getErrorMsg(ALL_EMPTY, NULL_STR_ARRAY));
        }
    }

    public static void checkAllEmpty(Object[] os, String msgTemplate, Object... objs) {
        if (G.allEmpty(os)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkAllEmpty(Object[] os, String[] paramNames) {
        if (G.allEmpty(os)) {
            throwConditionsNotMetException400(getErrorMsg(ALL_EMPTY, paramNames));
        }
    }

    public static void checkAllNull(Object[] os) {
        if (G.allNull(os)) {
            throwConditionsNotMetException400(getErrorMsg(ALL_NULL, NULL_STR_ARRAY));
        }
    }

    public static void checkAllNull(Object[] os, String msgTemplate, Object... objs) {
        if (G.allNull(os)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkAllNull(Object[] os, String[] paramNames) {
        if (G.allNull(os)) {
            throwConditionsNotMetException400(getErrorMsg(ALL_NULL, paramNames));
        }
    }


    public static void checkAllBlank(Collection<? extends CharSequence> cs) {
        if (S.allBlank(cs)) {
            throwConditionsNotMetException400(getErrorMsg(ALL_BLANK, NULL_STR_ARRAY));
        }
    }

    public static void checkAllBlank(Collection<? extends CharSequence> cs, String msgTemplate, Object... objs) {
        if (S.allBlank(cs)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkAllBlank(Collection<? extends CharSequence> cs, String[] paramNames) {
        if (S.allBlank(cs)) {
            throwConditionsNotMetException400(getErrorMsg(ALL_BLANK, paramNames));
        }
    }

    public static void checkAllEmpty(Collection<?> os) {
        if (G.allEmpty(os)) {
            throwConditionsNotMetException400(getErrorMsg(ALL_EMPTY, NULL_STR_ARRAY));
        }
    }

    public static void checkAllEmpty(Collection<?> os, String msgTemplate, Object... objs) {
        if (G.allEmpty(os)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkAllEmpty(Collection<?> os, String[] paramNames) {
        if (G.allEmpty(os)) {
            throwConditionsNotMetException400(getErrorMsg(ALL_EMPTY, paramNames));
        }
    }

    public static void checkAllNull(Collection<?> os) {
        if (G.allNull(os)) {
            throwConditionsNotMetException400(getErrorMsg(ALL_NULL, NULL_STR_ARRAY));
        }
    }

    public static void checkAllNull(Collection<?> os, String msgTemplate, Object... objs) {
        if (G.allNull(os)) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    public static void checkAllNull(Collection<?> os, String[] paramNames) {
        if (G.allNull(os)) {
            throwConditionsNotMetException400(getErrorMsg(ALL_NULL, paramNames));
        }
    }


    /**
     * expression 为 {@code true} 则抛出异常
     *
     * @param expression 条件
     */
    public static void checkCondition(boolean expression) {
        if (expression) {
            throwConditionsNotMetException400("The condition is not met! ");
        }
    }

    /**
     * expression 为 {@code true} 则抛出异常
     *
     * @param expression  条件
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     */
    public static void checkCondition(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throwConditionsNotMetException400(msgTemplate, objs);
        }
    }

    /**
     * expression 为 {@code true} 则抛出异常
     *
     * @param code        状态码
     * @param expression  条件
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     */
    public static void checkCondition(String code, boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new ConditionsNotMetException(msgTemplate, objs).setCode(code);
        }
    }


    private static void throwConditionsNotMetException400(String msgTemplate, Object... objs) {
        throw new ConditionsNotMetException(msgTemplate, objs).setCode("400");
    }

    /*==============================================================================
     ****************    throw UnsupportedTemporalTypeException     ****************
     ==============================================================================*/

    /**
     * 专门用于校验时间相关的表达式，expression 为 {@code true} 则抛出 {@link UnsupportedTemporalTypeException} 异常
     *
     * @param expression  时间相关的条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     */
    public static void checkTemporal(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new UnsupportedTemporalTypeException(SI.$(msgTemplate, objs));
        }
    }


    /*==============================================================================
     ********************    throw IllegalArgumentException     ********************
     ==============================================================================*/

    /**
     * 专门用于校验非法参数的表达式，expression 为 {@code true} 则抛出 {@link IllegalArgumentException} 异常
     *
     * @param expression  参数条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     */
    public static void checkArgument(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new IllegalArgumentException(SI.$(msgTemplate, objs));
        }
    }

    /*==============================================================================
     **********************    throw OutOfBoundsException     **********************
     ==============================================================================*/

    /**
     * 专门用于校验参数是否超出范围的表达式，expression 为 {@code true} 则抛出 {@link OutOfBoundsException} 异常
     *
     * @param expression  条件表达式
     * @param number      当前值
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     */
    public static void checkOutOfBounds(boolean expression, Number number, String msgTemplate, Object... objs) {
        if (expression) {
            throw new OutOfBoundsException(number, SI.$(msgTemplate, objs));
        }
    }

    /*==============================================================================
     **********************    throw FileNotFoundException    **********************
     ==============================================================================*/

    /**
     * 专门用于校验文件是否不存在的表达式，expression 为 {@code true} 则抛出 {@link FileNotFoundException} 异常
     *
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws FileNotFoundException FileNotFoundException
     */
    public static void checkFileNotFound(boolean expression, String msgTemplate, Object... objs) throws FileNotFoundException {
        if (expression) {
            throw new FileNotFoundException(SI.$(msgTemplate, objs));
        }
    }

    /*==============================================================================
     *******************          throw GeneralException         *******************
     ==============================================================================*/

    /**
     * 通用异常。expression 为 {@code true} 则抛出 {@link GeneralException} 异常
     *
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws GeneralException GeneralException
     */
    public static void checkGeneralException(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new GeneralException(msgTemplate, objs);
        }
    }

    /**
     * 检测非法的查询结果异常。expression 为 {@code true} 则抛出 {@link GeneralException} 异常
     *
     * @param code        状态码
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws GeneralException GeneralException
     */
    public static void checkGeneralException(String code, boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new GeneralException(msgTemplate, objs).setCode(code);
        }
    }

    /*==============================================================================
     *******************    throw IllegalQueryResultException    *******************
     ==============================================================================*/

    /**
     * 检测非法的查询结果异常。expression 为 {@code true} 则抛出 {@link IllegalQueryResultException} 异常
     *
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws IllegalQueryResultException IllegalQueryResultException
     */
    public static void checkQueryResult(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new IllegalQueryResultException(msgTemplate, objs);
        }
    }

    /**
     * 检测非法的查询结果异常。expression 为 {@code true} 则抛出 {@link IllegalQueryResultException} 异常
     *
     * @param code        状态码
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws IllegalQueryResultException IllegalQueryResultException
     */
    public static void checkQueryResult(String code, boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new IllegalQueryResultException(msgTemplate, objs).setCode(code);
        }
    }


    /*==============================================================================
     *******************    throw CircularReferencesException    *******************
     ==============================================================================*/

    /**
     * 检测是否存在循环引用。expression 为 {@code true} 则抛出 {@link CircularReferencesException} 异常
     *
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws CircularReferencesException CircularReferencesException
     */
    public static void checkCircularReferences(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new CircularReferencesException(msgTemplate, objs);
        }
    }

    /**
     * 检测是否存在循环引用。expression 为 {@code true} 则抛出 {@link CircularReferencesException} 异常
     *
     * @param code        状态码
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws CircularReferencesException CircularReferencesException
     */
    public static void checkCircularReferences(String code, boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new CircularReferencesException(msgTemplate, objs).setCode(code);
        }
    }


    /*==============================================================================
     ********************     throw RequestFailureException     ********************
     ==============================================================================*/

    /**
     * 检测请求失败的异常。expression 为 {@code true} 则抛出 {@link RequestFailureException} 异常
     *
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws RequestFailureException RequestFailureException
     */
    public static void checkRequest(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new RequestFailureException(msgTemplate, objs);
        }
    }

    /**
     * 检测请求失败的异常。expression 为 {@code true} 则抛出 {@link RequestFailureException} 异常
     *
     * @param code        状态码
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws RequestFailureException RequestFailureException
     */
    public static void checkRequest(String code, boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new RequestFailureException(msgTemplate, objs).setCode(code);
        }
    }

    /*==============================================================================
     ********************    throw UnexpectedResultException    ********************
     ==============================================================================*/

    /**
     * 检测预期外的结果异常。expression 为 {@code true} 则抛出 {@link UnexpectedResultException} 异常
     *
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws UnexpectedResultException UnexpectedResultException
     */
    public static void checkResult(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new UnexpectedResultException(msgTemplate, objs);
        }
    }

    /**
     * 检测预期外的结果异常。expression 为 {@code true} 则抛出 {@link UnexpectedResultException} 异常
     *
     * @param code        状态码
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws UnexpectedResultException UnexpectedResultException
     */
    public static void checkResult(String code, boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new UnexpectedResultException(msgTemplate, objs).setCode(code);
        }
    }

    /*==============================================================================
     ********************           throw AIException           ********************
     ==============================================================================*/

    /**
     * 检测AI相关错误。expression 为 {@code true} 则抛出 {@link AIException} 异常
     *
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws AIException AIException
     */
    public static void checkAIError(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new AIException(msgTemplate, objs);
        }
    }

    /**
     * 检测AI相关错误。expression 为 {@code true} 则抛出 {@link AIException} 异常
     *
     * @param code        状态码
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws AIException AIException
     */
    public static void checkAIError(String code, boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new AIException(msgTemplate, objs).setCode(code);
        }
    }

    /*==============================================================================
     ********************         throw AICallException         ********************
     ==============================================================================*/

    /**
     * 检测调用AI服务错误。expression 为 {@code true} 则抛出 {@link AICallException} 异常
     *
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws AICallException AICallException
     */
    public static void checkAICall(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new AICallException(msgTemplate, objs);
        }
    }

    /**
     * 检测调用AI服务错误。expression 为 {@code true} 则抛出 {@link AICallException} 异常
     *
     * @param code        状态码
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws AICallException AICallException
     */
    public static void checkAICall(String code, boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new AICallException(msgTemplate, objs).setCode(code);
        }
    }

    /*==============================================================================
     ********************       throw AIRequestException        ********************
     ==============================================================================*/

    /**
     * 检测请求AI服务错误。expression 为 {@code true} 则抛出 {@link AIRequestException} 异常
     *
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws AIRequestException AIRequestException
     */
    public static void checkAIRequest(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new AIRequestException(msgTemplate, objs);
        }
    }

    /**
     * 检测请求AI服务错误。expression 为 {@code true} 则抛出 {@link AIRequestException} 异常
     *
     * @param code        状态码
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws AIRequestException AIRequestException
     */
    public static void checkAIRequest(String code, boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new AIRequestException(msgTemplate, objs).setCode(code);
        }
    }

    /*==============================================================================
     ********************      throw AITrainingException        ********************
     ==============================================================================*/

    /**
     * 检测AI训练错误。expression 为 {@code true} 则抛出 {@link AITrainingException} 异常
     *
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws AITrainingException AITrainingException
     */
    public static void checkAITraining(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new AITrainingException(msgTemplate, objs);
        }
    }

    /**
     * 检测AI训练错误。expression 为 {@code true} 则抛出 {@link AITrainingException} 异常
     *
     * @param code        状态码
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws AITrainingException AITrainingException
     */
    public static void checkAITraining(String code, boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new AITrainingException(msgTemplate, objs).setCode(code);
        }
    }

    /*==============================================================================
     ********************      throw AIInferenceException       ********************
     ==============================================================================*/

    /**
     * 检测请求AI服务错误。expression 为 {@code true} 则抛出 {@link AIInferenceException} 异常
     *
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws AIInferenceException AIInferenceException
     */
    public static void checkAIInference(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new AIInferenceException(msgTemplate, objs);
        }
    }

    /**
     * 检测请求AI服务错误。expression 为 {@code true} 则抛出 {@link AIInferenceException} 异常
     *
     * @param code        状态码
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws AIInferenceException AIInferenceException
     */
    public static void checkAIInference(String code, boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new AIInferenceException(msgTemplate, objs).setCode(code);
        }
    }

    /*==============================================================================
     **********************    throw RuntimeException    **********************
     ==============================================================================*/

    /**
     * 校验运行时异常，expression 为 {@code true} 则抛出 {@link RuntimeException} 异常
     *
     * @param expression  条件表达式
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     * @throws RuntimeException RuntimeException
     */
    public static void checkRuntime(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new RuntimeException(SI.$(msgTemplate, objs));
        }
    }

    /*==============================================================================
     ****************          throw IllegalStateException          ****************
     ==============================================================================*/

    /**
     * expression 为 {@code true} 则抛出异常
     *
     * @param expression 条件
     */
    public static void checkState(boolean expression) {
        if (expression) {
            throw new IllegalStateException();
        }
    }

    /**
     * expression 为 {@code true} 则抛出异常
     *
     * @param expression  条件
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     */
    public static void checkState(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new IllegalStateException(SI.$(msgTemplate, objs));
        }
    }

    /*==============================================================================
     ****************          throw NullPointerException          ****************
     ==============================================================================*/

    /**
     * expression 为 {@code true} ，则抛出 {@link NullPointerException} 异常
     *
     * @param expression  条件
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     */
    public static void checkConditionNPE(boolean expression, String msgTemplate, Object... objs) {
        if (expression) {
            throw new NullPointerException(SI.$(msgTemplate, objs));
        }
    }

    /**
     * 待校验的对象为 {@code null}，则抛出 {@link NullPointerException} 异常
     *
     * @param o 待校验的对象
     */
    public static void checkNullNPE(Object o) {
        if (o == null) {
            throw new NullPointerException(getErrorMsg(NULL, NULL_STR_ARRAY));
        }
    }

    /**
     * 待校验的对象为 {@code null}，则抛出 {@link NullPointerException} 异常
     *
     * @param o           待校验的对象
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     */
    public static void checkNullNPE(Object o, String msgTemplate, Object... objs) {
        if (o == null) {
            throw new NullPointerException(SI.$(msgTemplate, objs));
        }
    }

    /**
     * 待校验的对象为 {@code null}，则抛出 {@link NullPointerException} 异常
     *
     * @param o          待校验的对象
     * @param paramNames 校验的参数名称数组
     */
    public static void checkNullNPE(Object o, String[] paramNames) {
        if (o == null) {
            throw new NullPointerException(getErrorMsg(NULL, paramNames));
        }
    }

    /**
     * 待校验的数组或集合中存在为 {@code null} 的元素，则抛出 {@link NullPointerException} 异常
     *
     * @param os 待校验的数组或集合
     */
    public static void checkHasNullNPE(Object[] os) {
        if (G.hasNull(os)) {
            String errorMsg = getErrorMsg(HAS_NULL, NULL_STR_ARRAY) + getErrorParamsString(HAS_NULL, os, NULL_STR_ARRAY);
            throw new NullPointerException(errorMsg);
        }
    }

    /**
     * 待校验的数组或集合中存在为 {@code null} 的元素，则抛出 {@link NullPointerException} 异常
     *
     * @param os          待校验的数组或集合
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     */
    public static void checkHasNullNPE(Object[] os, String msgTemplate, Object... objs) {
        if (G.hasNull(os)) {
            throw new NullPointerException(SI.$(msgTemplate, objs));
        }
    }

    /**
     * 待校验的数组或集合中存在为 {@code null} 的元素，则抛出 {@link NullPointerException} 异常
     *
     * @param os         待校验的数组或集合
     * @param paramNames 校验的参数名称数组
     */
    public static void checkHasNullNPE(Object[] os, String[] paramNames) {
        if (G.hasNull(os)) {
            String errorMsg = getErrorMsg(HAS_NULL, paramNames) + getErrorParamsString(HAS_NULL, os, paramNames);
            throw new NullPointerException(errorMsg);
        }
    }

    /**
     * 待校验的数组或集合中存在为 {@code null} 的元素，则抛出 {@link NullPointerException} 异常
     *
     * @param os 待校验的数组或集合
     */
    public static void checkHasNullNPE(Collection<?> os) {
        if (G.hasNull(os)) {
            String errorMsg = getErrorMsg(HAS_NULL, NULL_STR_ARRAY) + getErrorParamsString(HAS_NULL, os, NULL_STR_ARRAY);
            throw new NullPointerException(errorMsg);
        }
    }

    /**
     * 待校验的数组或集合中存在为 {@code null} 的元素，则抛出 {@link NullPointerException} 异常
     *
     * @param os          待校验的数组或集合
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     */
    public static void checkHasNullNPE(Collection<?> os, String msgTemplate, Object... objs) {
        if (G.hasNull(os)) {
            throw new NullPointerException(SI.$(msgTemplate, objs));
        }
    }

    /**
     * 待校验的数组或集合中存在为 {@code null} 的元素，则抛出 {@link NullPointerException} 异常
     *
     * @param os         待校验的数组或集合
     * @param paramNames 校验的参数名称数组
     */
    public static void checkHasNullNPE(Collection<?> os, String[] paramNames) {
        if (G.hasNull(os)) {
            String errorMsg = getErrorMsg(HAS_NULL, paramNames) + getErrorParamsString(HAS_NULL, os, paramNames);
            throw new NullPointerException(errorMsg);
        }
    }

    /**
     * 待校验的数组或集合中的元素全部为 {@code null}，则抛出 {@link NullPointerException} 异常
     *
     * @param os 待校验的数组或集合
     */
    public static void checkAllNullNPE(Object[] os) {
        if (G.allNull(os)) {
            throw new NullPointerException(getErrorMsg(ALL_NULL, NULL_STR_ARRAY));
        }
    }

    /**
     * 待校验的数组或集合中的元素全部为 {@code null}，则抛出 {@link NullPointerException} 异常
     *
     * @param os          待校验的数组或集合
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     */
    public static void checkAllNullNPE(Object[] os, String msgTemplate, Object... objs) {
        if (G.allNull(os)) {
            throw new NullPointerException(SI.$(msgTemplate, objs));
        }
    }

    /**
     * 待校验的数组或集合中的元素全部为 {@code null}，则抛出 {@link NullPointerException} 异常
     *
     * @param os         待校验的数组或集合
     * @param paramNames 校验的参数名称数组
     */
    public static void checkAllNullNPE(Object[] os, String[] paramNames) {
        if (G.allNull(os)) {
            throw new NullPointerException(getErrorMsg(ALL_NULL, paramNames));
        }
    }

    /**
     * 待校验的数组或集合中的元素全部为 {@code null}，则抛出 {@link NullPointerException} 异常
     *
     * @param os 待校验的数组或集合
     */
    public static void checkAllNullNPE(Collection<?> os) {
        if (G.allNull(os)) {
            throw new NullPointerException(getErrorMsg(ALL_NULL, NULL_STR_ARRAY));
        }
    }

    /**
     * 待校验的数组或集合中的元素全部为 {@code null}，则抛出 {@link NullPointerException} 异常
     *
     * @param os          待校验的数组或集合
     * @param msgTemplate 信息模板
     * @param objs        信息参数
     */
    public static void checkAllNullNPE(Collection<?> os, String msgTemplate, Object... objs) {
        if (G.allNull(os)) {
            throw new NullPointerException(SI.$(msgTemplate, objs));
        }
    }

    /**
     * 待校验的数组或集合中的元素全部为 {@code null}，则抛出 {@link NullPointerException} 异常
     *
     * @param os         待校验的数组或集合
     * @param paramNames 校验的参数名称数组
     */
    public static void checkAllNullNPE(Collection<?> os, String[] paramNames) {
        if (G.allNull(os)) {
            throw new NullPointerException(getErrorMsg(ALL_NULL, paramNames));
        }
    }


    /**
     * 根据 错误信息类型 和 参数名，动态生成错误信息。<br>
     * 注：<br>
     * <b>参数与所提供的参数名可能不是一一对应的关系，所以无法准确判断出具体是哪个参数有问题，只能笼统的抛出错误信息</b>
     *
     * @param type       错误信息类型。如果 type 为 null，则默认为 {@link ErrorMsgType#NULL}
     * @param paramNames 参数名
     * @return 错误信息
     */
    public static String getErrorMsg(ErrorMsgType type, String... paramNames) {
        if (type == null) type = ErrorMsgType.NULL;

        int length = G.isEmpty(paramNames) ? 0 : paramNames.length;

        // 没传参数名，或只有一个参数，但是参数名为空
        boolean paramNameEmpty = length == 0 || (length == 1 && S.isBlank(paramNames[0]));
        final char SPACE = ' ';
        String paramNamesStr = paramNameEmpty ? "" : (length == 1 ? "`" + paramNames[0] + "`" : Arrays.asList(paramNames).toString()) + SPACE;

        String mustBe = type.mustBe;
        String errorReason = type.errorReason;

        String errorMsg = (paramNameEmpty ? "This parameter " : (length == 1 ? "The parameter " : "All parameters ")) + paramNamesStr + "must be " + mustBe + "! ";

        String length1Msg = "The parameter " + paramNamesStr + "must be " + mustBe + "! ";

        switch (type) {
            case NULL:      // OR has not null
            case NOT_NULL:  // OR has not null
            case EMPTY:     // OR has empty
            case NOT_EMPTY: // OR has not empty
            case BLANK:     // OR has blank
            case NOT_BLANK: // OR has not blank
                // default errorMsg
                break;
            case HAS_BLANK:
            case HAS_EMPTY:
            case HAS_NULL:
                errorMsg = paramNameEmpty ? "None of the parameters " + paramNamesStr + "can be " + errorReason + "! " :
                        (length == 1 ? length1Msg : "None of these parameters " + paramNamesStr + "can be " + errorReason + "! ");
                break;
            case ALL_BLANK:
            case ALL_EMPTY:
            case ALL_NULL:
                errorMsg = paramNameEmpty ? "At least one parameter " + paramNamesStr + "can't be " + errorReason + "! " :
                        (length == 1 ? length1Msg : "At least one of these parameters " + paramNamesStr + "can't be " + errorReason + "! ");
                break;
        }
        return errorMsg;
    }

    /**
     * 返回导致错误的【参数序号】或【参数名】
     *
     * @param type       错误信息类型。如果 type 为 null，则默认为 {@link ErrorMsgType#HAS_NULL}。<br>
     *                   <b>注：{@link ErrorMsgType#HAS_BLANK} 只能用于校验 CharSequence 类型的集合</b>
     * @param os         待校验的集合
     * @param paramNames 校验的参数名称数组
     * @return 导致错误的【参数序号】或【参数名】
     */
    public static List<Object> listErrorParams(ErrorMsgType type, Collection<?> os, String... paramNames) {
        if (type == null) type = HAS_NULL;
        List<Object> params = new ArrayList<>();
        if (G.isEmpty(os)) return params;

        int i = 0;
        if (G.isEmpty(paramNames) || os.size() != paramNames.length) {   // 返回导致错误的【参数的序号】
            for (Object o : os) {
                switch (type) {
                    case HAS_NULL:
                        if (o == null) params.add(i);
                        break;
                    case HAS_EMPTY:
                        if (G.isEmpty(o)) params.add(i);
                        break;
                    case HAS_BLANK:     // ErrorMsgType#HAS_BLANK 只能用于校验 CharSequence 类型的集合
                        CharSequence s = null;
                        try {
                            s = (CharSequence) o;
                        } catch (Exception e) {
                            throw new ClassCastException("The `ErrorMsgType.HAS_BLANK` can only be used to validate a `Collection<? extends CharSequence>`. ");
                        }
                        if (S.isBlank(s)) params.add(i);
                        break;
                }
                i++;
            }
        } else {        // 返回导致错误的【参数的名称】
            for (Object o : os) {
                String paramName = paramNames[i];
                switch (type) {
                    case HAS_NULL:
                        if (o == null) params.add(paramName);
                        break;
                    case HAS_EMPTY:
                        if (G.isEmpty(o)) params.add(paramName);
                        break;
                    case HAS_BLANK:     // ErrorMsgType#HAS_BLANK 只能用于校验 CharSequence 类型的集合
                        CharSequence s = null;
                        try {
                            s = (CharSequence) o;
                        } catch (Exception e) {
                            throw new ClassCastException("The `ErrorMsgType.HAS_BLANK` can only be used to validate a `Collection<? extends CharSequence>`. ");
                        }
                        if (S.isBlank(s)) params.add(paramName);
                        break;
                }
                i++;
            }
        }

        return params;
    }

    /**
     * 根据错误参数列表构建错误提示信息
     *
     * @param type       错误信息类型。如果 type 为 null，则默认为 {@link ErrorMsgType#HAS_NULL}。<br>
     * @param os         待校验的集合
     * @param paramNames 校验的参数名称数组
     * @return 错误提示信息
     */
    public static String getErrorParamsString(ErrorMsgType type, Collection<?> os, String... paramNames) {
        if (os == null) return "";
        List<Object> objs = listErrorParams(type, os, paramNames);
        if (G.isEmpty(objs)) return "";
        Object o = objs.get(0);
        if (o instanceof Integer) {
            if (objs.size() == 1) {
                return SI.$("But parameter with index ${?} is ${?}! ", objs, type.errorReason);
            } else {
                return SI.$("But parameters with index ${?} are ${?}! ", objs, type.errorReason);
            }
        } else {
            if (objs.size() == 1) {
                return SI.$("But parameter ${?} is ${?}! ", objs, type.errorReason);
            } else {
                return SI.$("But parameters ${?} are ${?}! ", objs, type.errorReason);
            }
        }
    }

    /**
     * 根据错误参数列表构建错误提示信息
     *
     * @param type       错误信息类型。如果 type 为 null，则默认为 {@link ErrorMsgType#HAS_NULL}。<br>
     * @param os         待校验的数组
     * @param paramNames 校验的参数名称数组
     * @return 错误提示信息
     */
    public static String getErrorParamsString(ErrorMsgType type, Object[] os, String... paramNames) {
        if (os == null) return "";
        return getErrorParamsString(type, Arrays.asList(os), paramNames);
    }

}
