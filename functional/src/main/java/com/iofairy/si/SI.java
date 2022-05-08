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

import com.iofairy.except.UnexpectedParameterException;
import com.iofairy.top.G;
import com.iofairy.top.S;
import com.iofairy.tuple.Tuple;

import java.util.*;

/**
 * String Interpolator. <br>
 * 字符串插值器
 *
 * @since 0.0.1
 */
public class SI {

    private final static int CACHE_SIZE = 1000;
    private final static int KEY_CACHE_SIZE = 2000;
    private final static Map<String, List<StringToken>> TEMPLATE_CACHE = Collections.synchronizedMap(new LRUCache<>(CACHE_SIZE));
    private final static Map<String, String> KEY_CACHE = Collections.synchronizedMap(new LRUCache<>(KEY_CACHE_SIZE));

    private final Map<String, Object> valueMap = new HashMap<>();   // 读多写少，未加同步机制

    private final static String MSG_UNEXPECTED_PARAM = "This parameter is a key, the key must be end with \" ->\" or \" >>>\" or \" >>\". ";

    public SI() {

    }

    public SI(final Tuple... tuples) {
        tuplesPutToMap(tuples);
    }

    public SI(final Map<String, Object> valueMap) {
        if (valueMap != null) this.valueMap.putAll(valueMap);
    }

    public static SI of(final Tuple... tuples) {
        return new SI(tuples);
    }

    public static SI of(final Map<String, Object> map) {
        return new SI(map);
    }

    /**
     * Instantiate an SI object by key-value pairs.
     *
     * @param kvs key-value pairs
     * @return SI object
     * @throws RuntimeException             if the <b>kvs</b> length not be even.
     * @throws NullPointerException         if the <b>key</b> is null.
     * @throws ClassCastException           if the <b>key</b> is not String.
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
     * <pre>
     * String infoTemplate = "ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}";
     *
     * SI si = SI.init("         ip -&gt;", "127.0.0.1",
     *                 "         db -&gt;", "testdb",
     *                 "       port -&gt;", 3306,
     *                 "     dbType -&gt;", "mysql",
     *                 " other_info -&gt;", Tuple.of("isCluster", true),
     *                 "description -&gt;", new Object());
     *
     * String dbInfo = si.$(infoTemplate);
     * </pre>
     *
     * @param kvs key-value pairs
     * @return SI object
     * @throws RuntimeException             if the <b>kvs</b> length not be even.
     * @throws NullPointerException         if the <b>key</b> is null.
     * @throws ClassCastException           if the <b>key</b> is not String.
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
     * <pre>
     * SI si = SI.load("ip -&gt;", "127.0.0.1",
     *                 "port -&gt;", 3306,
     *                 "db -&gt;", "testdb",
     *                 "dbType -&gt;", "mysql",
     *                 "other_info -&gt;", Tuple.of("isCluster", true),
     *                 "description -&gt;", new Object());
     *
     *  String dbInfo = si.$("ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}");
     * </pre>
     *
     * @param kvs key-value pairs
     * @return SI object
     * @throws RuntimeException             if the <b>kvs</b> length not be even.
     * @throws NullPointerException         if the <b>key</b> is null.
     * @throws ClassCastException           if the <b>key</b> is not String.
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

    public SI add(Map<String, Object> valueMap) {
        if (valueMap != null) this.valueMap.putAll(valueMap);
        return this;
    }

    /**
     * Add key-value pairs to this SI object.
     *
     * @param kvs key-value pairs
     * @return this SI object
     * @throws RuntimeException     if the <b>kvs</b> length not be even.
     * @throws NullPointerException if the <b>key</b> is null.
     * @throws ClassCastException   if the <b>key</b> is not String.
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
     * <pre>
     * String infoTemplate = "ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}";
     * SI si = SI.of();
     * si.fill("         ip -&gt;", "127.0.0.1",
     *         "         db -&gt;", "testdb",
     *         "       port -&gt;", 3306,
     *         "     dbType -&gt;", "mysql",
     *         " other_info -&gt;", Tuple.of("isCluster", true),
     *         "description -&gt;", new Object());
     *
     * String dbInfo = si.$(infoTemplate);
     * </pre>
     *
     * @param kvs key-value pairs
     * @return this SI object
     * @throws RuntimeException             if the <b>kvs</b> length not be even.
     * @throws NullPointerException         if the <b>key</b> is null.
     * @throws ClassCastException           if the <b>key</b> is not String.
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

    public SI set(Map<String, Object> valueMap) {
        this.valueMap.clear();
        return this.add(valueMap);
    }

    /**
     * Reset this SI object with key-value pairs.
     *
     * @param kvs key-value pairs
     * @return this SI object
     * @throws RuntimeException     if the <b>kvs</b> length not be even.
     * @throws NullPointerException if the <b>key</b> is null.
     * @throws ClassCastException   if the <b>key</b> is not String.
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
     * @since 0.0.1
     */
    public String $(CharSequence source) {
        if (source == null) return null;
        if (S.isBlank(source)) return source.toString();

        List<StringToken> tokens = getTokens(source.toString());
        StringBuffer parsed = new StringBuffer();
        tokens.forEach(token -> {
            String value = token.value;
            parsed.append(token.type == StringType.STRING ? value : valueMap.getOrDefault(value, token.originValue));
        });

        return parsed.toString();
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
        verifyPairWithStringKey(kvs);
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

    private static void verifyPairWithStringKey(Object... kvs) {
        if (kvs != null) {
            if (kvs.length % 2 != 0)
                throw new RuntimeException("The parameters length must be even. 参数个数必须为偶数。");
            for (int i = 0; i < kvs.length; i++) {
                if (i % 2 == 0) {
                    if (kvs[i] == null)
                        throw new NullPointerException("Index: " + i + ". This parameter is a key, the key must be not null. ");
                    try {
                        String k = (String) kvs[i];
                    } catch (ClassCastException castException) {
                        throw new ClassCastException("Index: " + i + ". This parameter is a key, the key must be `String` type. ");
                    }
                }
            }
        }
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
}
