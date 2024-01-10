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
package com.iofairy.si;

import com.iofairy.except.CircularReferencesException;
import com.iofairy.except.UndefinedVariableException;
import com.iofairy.except.UnexpectedParameterException;
import com.iofairy.except.UnexpectedTypeException;
import com.iofairy.top.G;
import com.iofairy.top.O;
import com.iofairy.top.S;
import com.iofairy.tuple.Tuple;

import java.util.*;

import static com.iofairy.si.SIBase.*;

/**
 * String Interpolator. <b>It's not thread-safe</b>.<br>
 * 字符串插值器<b>（非线程安全）</b>
 *
 * @since 0.0.1
 */
public class SI {

    private final static int CACHE_SIZE = 1000;
    private final static int NESTED_CACHE_SIZE = 500;
    private final static int KEY_CACHE_SIZE = 2000;
    private final static Map<String, List<StringToken>> TEMPLATE_CACHE = Collections.synchronizedMap(new LRUCache<>(CACHE_SIZE));
    private final static Map<String, List<Object>> NESTED_TEMPLATE_CACHE = Collections.synchronizedMap(new LRUCache<>(NESTED_CACHE_SIZE));
    private final static Map<String, String> KEY_CACHE = Collections.synchronizedMap(new LRUCache<>(KEY_CACHE_SIZE));

    private final Map<String, Object> valueMap = new HashMap<>();   // 读多写少，未加同步机制

    /**
     * 是否开启嵌套插值
     */
    private boolean enableSIInVariables = false;
    /**
     * 是否在 {@link #valueMap} 的值中开启嵌套插值（{@link #enableSIInVariables} 为 {@code true} 时才有效）
     */
    private boolean enableSIInValues = false;
    /**
     * 是否抛出异常，当 {@link #valueMap} 中不存在指定的变量
     */
    private boolean enableUndefinedVariableException = false;


    private final static String MSG_UNEXPECTED_PARAM = "This parameter is a key, the key must be end with \" ->\" or \" >>>\" or \" >>\". ";

    public SI() {

    }

    public SI(final Tuple... tuples) {
        tuplesPutToMap(tuples);
    }

    public SI(final Map<String, ?> valueMap) {
        if (valueMap != null) this.valueMap.putAll(valueMap);
    }

    public static SI of(final Tuple... tuples) {
        return new SI(tuples);
    }

    public static SI of(final Map<String, ?> map) {
        return new SI(map);
    }

    /**
     * Instantiate an SI object by key-value pairs.
     *
     * @param kvs key-value pairs
     * @return SI object
     * @throws RuntimeException             if the <b>kvs</b> length not be even.
     * @throws NullPointerException         if the <b>key</b> is null.
     * @throws UnexpectedTypeException      if the <b>key</b> is not String.
     * @throws UnexpectedParameterException if the <b>key</b> is not end with " -&gt;" or " &gt;&gt;&gt;" or " &gt;&gt;".
     * @since 0.0.1
     */
    public static SI of(Object... kvs) {
        Map<String, Object> kvMap = toMap(false, false, kvs);
        return of(kvMap);
    }

    /**
     * Instantiate an SI object by key-value pairs, and key must be end with " -&gt;" or " &gt;&gt;&gt;" or " &gt;&gt;",
     * and key will be removed leading and trailing whitespace. <br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * String infoTemplate = "ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}";
     *
     * SI si = SI.init("         ip ->", "127.0.0.1",
     *                 "         db ->", "testdb",
     *                 "       port ->", 3306,
     *                 "     dbType ->", "mysql",
     *                 " other_info ->", Tuple.of("isCluster", true),
     *                 "description ->", new Object());
     *
     * String dbInfo = si.$(infoTemplate);
     * }</pre></blockquote>
     *
     * @param kvs key-value pairs
     * @return SI object
     * @throws RuntimeException             if the <b>kvs</b> length not be even.
     * @throws NullPointerException         if the <b>key</b> is null.
     * @throws UnexpectedTypeException      if the <b>key</b> is not String.
     * @throws UnexpectedParameterException if the <b>key</b> is not end with " -&gt;" or " &gt;&gt;&gt;" or " &gt;&gt;".
     * @since 0.0.1
     */
    public static SI init(Object... kvs) {
        Map<String, Object> kvMap = toMap(true, true, kvs);
        return of(kvMap);
    }

    /**
     * Instantiate an SI object by key-value pairs, and key must be end with " -&gt;" or " &gt;&gt;&gt;" or " &gt;&gt;". <br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * SI si = SI.load("ip ->", "127.0.0.1",
     *                 "port ->", 3306,
     *                 "db ->", "testdb",
     *                 "dbType ->", "mysql",
     *                 "other_info ->", Tuple.of("isCluster", true),
     *                 "description ->", new Object());
     *
     *  String dbInfo = si.$("ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}");
     * }</pre></blockquote>
     *
     * @param kvs key-value pairs
     * @return SI object
     * @throws RuntimeException             if the <b>kvs</b> length not be even.
     * @throws NullPointerException         if the <b>key</b> is null.
     * @throws UnexpectedTypeException      if the <b>key</b> is not String.
     * @throws UnexpectedParameterException if the <b>key</b> is not end with " -&gt;" or " &gt;&gt;&gt;" or " &gt;&gt;".
     * @since 0.0.1
     */
    public static SI load(Object... kvs) {
        Map<String, Object> kvMap = toMap(true, false, kvs);
        return of(kvMap);
    }

    public SI add(Tuple... tuples) {
        tuplesPutToMap(tuples);
        return this;
    }

    public SI add(Map<String, ?> valueMap) {
        if (valueMap != null) this.valueMap.putAll(valueMap);
        return this;
    }

    /**
     * Add key-value pairs to this SI object.
     *
     * @param kvs key-value pairs
     * @return this SI object
     * @throws RuntimeException        if the <b>kvs</b> length not be even.
     * @throws NullPointerException    if the <b>key</b> is null.
     * @throws UnexpectedTypeException if the <b>key</b> is not String.
     * @since 0.0.1
     */
    public SI add(Object... kvs) {
        Map<String, Object> kvMap = toMap(false, false, kvs);
        return this.add(kvMap);
    }

    /**
     * Fill key-value pairs to this SI object. And key must be end with " -&gt;" or " &gt;&gt;&gt;" or " &gt;&gt;",
     * and key will be removed leading and trailing whitespace. <br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * String infoTemplate = "ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}";
     * SI si = SI.of();
     * si.fill("         ip ->", "127.0.0.1",
     *         "         db ->", "testdb",
     *         "       port ->", 3306,
     *         "     dbType ->", "mysql",
     *         " other_info ->", Tuple.of("isCluster", true),
     *         "description ->", new Object());
     *
     * String dbInfo = si.$(infoTemplate);
     * }</pre></blockquote>
     *
     * @param kvs key-value pairs
     * @return this SI object
     * @throws RuntimeException             if the <b>kvs</b> length not be even.
     * @throws NullPointerException         if the <b>key</b> is null.
     * @throws UnexpectedTypeException      if the <b>key</b> is not String.
     * @throws UnexpectedParameterException if the <b>key</b> is not end with " -&gt;" or " &gt;&gt;&gt;" or " &gt;&gt;".
     * @since 0.0.1
     */
    public SI fill(Object... kvs) {
        Map<String, Object> kvMap = toMap(true, true, kvs);
        return this.add(kvMap);
    }

    public SI set(Tuple... tuples) {
        valueMap.clear();
        return this.add(tuples);
    }

    public SI set(Map<String, ?> valueMap) {
        this.valueMap.clear();
        return this.add(valueMap);
    }

    /**
     * Reset this SI object with key-value pairs.
     *
     * @param kvs key-value pairs
     * @return this SI object
     * @throws RuntimeException        if the <b>kvs</b> length not be even.
     * @throws NullPointerException    if the <b>key</b> is null.
     * @throws UnexpectedTypeException if the <b>key</b> is not String.
     * @since 0.0.1
     */
    public SI set(Object... kvs) {
        valueMap.clear();
        Map<String, Object> kvMap = toMap(false, false, kvs);
        return this.add(kvMap);
    }

    public SI del(String... keys) {
        if (keys != null) {
            Arrays.stream(keys).forEach(valueMap::remove);
        }
        return this;
    }

    private void tuplesPutToMap(Tuple... tuples) {
        if (tuples != null) {
            Arrays.stream(tuples)
                    .filter(e -> e != null && e.arity() != 0)
                    .forEach(t -> valueMap.putAll(t.toMap()));
        }
    }

    /**
     * Interpolating for strings.<br>
     * 执行插值程序，解析字符串
     *
     * @param source source string
     * @return string that has been processed
     * @throws CircularReferencesException when the circular reference occurs
     * @throws UndefinedVariableException  No variable was found during string interpolation when {@link #enableUndefinedVariableException} is {@code true}.
     * @since 0.0.1
     */
    public String $(CharSequence source) {
        if (source == null) return null;
        if (S.isBlank(source)) return source.toString();

        String sourceString = source.toString();

        StringBuilder interpolated = new StringBuilder();

        if (enableSIInVariables) {
            List<Object> nestedTokens = getNestedTokens(sourceString);

            for (Object token : nestedTokens) {
                if (token instanceof NestedStringToken) {
                    List<String> variablesStack = new ArrayList<>();
                    Object afterInterpolated = interpolate(sourceString, (NestedStringToken) token, variablesStack);
                    interpolated.append(afterInterpolated);
                } else {
                    interpolated.append(token);
                }
            }
        } else {
            List<StringToken> tokens = getTokens(sourceString);

            for (StringToken token : tokens) {
                String value = token.value;
                if (enableUndefinedVariableException && token.type == StringType.VARIABLE && !valueMap.containsKey(value)) {
                    throw new UndefinedVariableException("Cannot resolve variable `" + value + "` in \"" + sourceString + "\". ");
                }
                interpolated.append(token.type == StringType.STRING ? value : valueMap.getOrDefault(value, token.originValue));
            }
        }

        return interpolated.toString();
    }

    /**
     * 字符串插值处理
     *
     * @param source         原始字符串
     * @param nestedToken    nestedToken
     * @param variablesStack 变量栈
     * @return 插值后的字符串
     * @since 0.4.0
     */
    private Object interpolate(String source, NestedStringToken nestedToken, List<String> variablesStack) {
        String key = traverseInterpolation(source, variablesStack, nestedToken.key);

        int lengthOfCacheThreshold = 30;        // valueMap中的值需要缓存的临界长度

        if (valueMap.containsKey(key)) {
            Object obj = valueMap.get(key);
            if (!enableSIInValues || obj == null) return obj;

            String value = obj.toString();
            if (!value.contains(PREFIX)) {          // valueMap的值中不包含 ${
                return value;
            } else {                                // valueMap的值中包含 ${，需要解析
                checkCyclic(key, variablesStack, source);
                variablesStack.add(key);

                List<Object> tokens = value.length() <= lengthOfCacheThreshold ? StringExtractor.nestedParse(value) : getNestedTokens(value);
                value = traverseInterpolation(source, variablesStack, tokens);

                variablesStack.remove(variablesStack.size() - 1);
                return value;
            }
        } else {
            if (enableUndefinedVariableException) {
                throw new UndefinedVariableException("Cannot resolve variable `" + key + "` in \"" + source + "\". ");
            }

            return nestedToken.defaultValue.isEmpty() ? PREFIX + key + SUFFIX : traverseInterpolation(source, variablesStack, nestedToken.defaultValue);
        }
    }

    /**
     * 遍历token进行字符串插值
     *
     * @param source         原始字符串
     * @param variablesStack 变量栈
     * @param tokens         tokens
     * @return 插值后的字符串
     * @since 0.4.0
     */
    private String traverseInterpolation(String source, List<String> variablesStack, List<Object> tokens) {
        StringBuilder sb = new StringBuilder();
        for (Object token : tokens) {
            if (token instanceof NestedStringToken) {
                sb.append(interpolate(source, (NestedStringToken) token, variablesStack));
            } else {
                sb.append(token);
            }
        }
        return sb.toString();
    }


    /**
     * Check for circular references when inspecting string interpolation. <br>
     * 检查字符串插值时是否有存在循环引用
     *
     * @param variable       当前检查的变量
     * @param variablesStack 变量栈
     * @param source         原始字符串
     * @since 0.4.0
     */
    private void checkCyclic(final String variable, final List<String> variablesStack, final String source) {
        if (!variablesStack.contains(variable)) {
            return;
        }
        variablesStack.add(variable);
        throw new CircularReferencesException("Circular references in string interpolation of " + G.toString(source) + ": " + String.join(" -> ", variablesStack));
    }

    private static List<Object> getNestedTokens(String source) {
        List<Object> tokens;
        if (NESTED_TEMPLATE_CACHE.containsKey(source)) {
            tokens = NESTED_TEMPLATE_CACHE.get(source);
        } else {
            tokens = StringExtractor.nestedParse(source);
            NESTED_TEMPLATE_CACHE.put(source, tokens);
        }
        return tokens;
    }

    /**
     * Interpolating for strings.<br>
     * 字符串插值
     *
     * @param source    source string
     * @param arguments arguments
     * @return string that has been processed
     * @since 0.3.3
     */
    public static String $(CharSequence source, Object... arguments) {
        if (source == null) return null;
        if (S.isBlank(source)) return source.toString();
        int length = G.isEmpty(arguments) ? 0 : arguments.length;

        int placeholderCount = 0;
        List<StringToken> tokens = getTokens(source.toString());
        StringBuilder parsed = new StringBuilder();
        for (StringToken token : tokens) {
            String value = token.value;
            if (token.type == StringType.STRING) {
                parsed.append(value);
            } else {
                if (placeholderCount < length) {
                    parsed.append(arguments[placeholderCount]);
                    placeholderCount++;
                } else {
                    parsed.append(token.originValue);
                }
            }
        }

        return parsed.toString();
    }

    private static List<StringToken> getTokens(String source) {
        List<StringToken> tokens;
        if (TEMPLATE_CACHE.containsKey(source)) {
            tokens = TEMPLATE_CACHE.get(source);
        } else {
            tokens = StringExtractor.split(source);
            TEMPLATE_CACHE.put(source, tokens);
        }
        return tokens;
    }

    public Map<String, Object> getValueMap() {
        return Collections.unmodifiableMap(valueMap);
    }

    private static Map<String, Object> toMap(boolean withSuffix, boolean needTrim, Object... kvs) {
        Map<String, Object> kvMap = new HashMap<>();
        if (kvs == null || kvs.length == 0) return kvMap;
        O.verifyMapKV(false, true, false, kvs);

        for (int i = 0; i < kvs.length; i++) {
            if (i % 2 == 0) {
                String k = (String) kvs[i];
                if (withSuffix) {
                    String cacheKey = (needTrim ? "init -> " : "load -> ") + k;

                    if (KEY_CACHE.containsKey(cacheKey)) {
                        kvMap.put(KEY_CACHE.get(cacheKey), kvs[i + 1]);
                    } else {
                        String tempKey = k;
                        if (needTrim) tempKey = k.replaceAll("[\\s　]+$", "");  // 删除尾部的空白字符，包括中文空格
                        boolean isEndThreeGT = tempKey.endsWith(" >>>");
                        if (tempKey.endsWith(" ->") || isEndThreeGT || tempKey.endsWith(" >>")) {
                            // 删除后缀符
                            String realKey = tempKey.substring(0, tempKey.length() - (isEndThreeGT ? 4 : 3));
                            realKey = needTrim ? realKey.trim() : realKey;

                            KEY_CACHE.put(cacheKey, realKey);
                            kvMap.put(realKey, kvs[i + 1]);
                        } else {
                            throw new UnexpectedParameterException("Index: " + i + ". " + MSG_UNEXPECTED_PARAM);
                        }
                    }
                } else {
                    kvMap.put(k, kvs[i + 1]);
                }

            }
        }
        return kvMap;
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    /**
     * Simple LRU Cache, <b>this implementation is not synchronized and not thread-safe.</b>
     *
     * @param <K> key type
     * @param <V> value type
     */
    static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int maxEntries;

        public LRUCache(final int maxEntries) {
            super(maxEntries + 1, 1.0f, true);
            this.maxEntries = maxEntries;
        }

        public LRUCache(final int maxEntries, float loadFactor) {
            super(maxEntries + 1, loadFactor, true);
            this.maxEntries = maxEntries;
        }

        @Override
        protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
            return size() > maxEntries;
        }
    }


    public boolean isEnableSIInVariables() {
        return enableSIInVariables;
    }

    public SI setEnableSIInVariables(boolean enableSIInVariables) {
        this.enableSIInVariables = enableSIInVariables;
        return this;
    }

    public boolean isEnableSIInValues() {
        return enableSIInValues;
    }

    public SI setEnableSIInValues(boolean enableSIInValues) {
        this.enableSIInValues = enableSIInValues;
        return this;
    }

    public SI setEnableNestedSI(boolean enableNestedSI) {
        this.enableSIInVariables = enableNestedSI;
        this.enableSIInValues = enableNestedSI;
        return this;
    }

    public boolean isEnableUndefinedVariableException() {
        return enableUndefinedVariableException;
    }

    public SI setEnableUndefinedVariableException(boolean enableUndefinedVariableException) {
        this.enableUndefinedVariableException = enableUndefinedVariableException;
        return this;
    }

}
