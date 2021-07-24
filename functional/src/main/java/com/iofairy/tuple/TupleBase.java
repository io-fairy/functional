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
package com.iofairy.tuple;

import com.iofairy.except.*;
import com.iofairy.si.SI;

import java.util.*;

/**
 * Base tuple
 *
 * @since 0.0.1
 */
public abstract class TupleBase implements Tuple {

    private static final long serialVersionUID = 10065917090L;

    /**
     * List of aliases.　普通别名列表。
     */
    private final List<String> aliasList = new ArrayList<>();
    /**
     * List of tuple aliases.　TupleAlias别名列表。
     */
    private final List<TupleAlias> tupleAliasList = new ArrayList<>();
    /**
     * Map of aliases.　别名与序号键值对
     */
    private final Map<String, Integer> alias_index = new HashMap<>();

    private final static String MSG_FOR_TUPLE0_UNSUPPORTED =
                    "`alias` method is unsupported in Tuple0. Because Tuple0 is empty tuple. " +
                    "Tuple0不支持调用alias方法，因为Tuple0是一个空元组。";

    private final static String MSG_FOR_STRING_ALIASES =
                    "The aliases not set. Please call `alias(String...)` or `alias(TupleAlias...)` method first. " +
                    "别名未设置，请先调用 alias(String...) 或 alias(TupleAlias...) 方法设置别名。";

    private final static String MSG_FOR_TUPLE_ALIASES =
                    "The aliases not set. Please call `alias(TupleAlias...)` method first. " +
                    "别名未设置，请先调用 alias(TupleAlias...) 方法设置别名。";

    @Override
    public Tuple alias(TupleAlias... aliases) {
        if (arity() == 0) throw new UnsupportedOperationException(MSG_FOR_TUPLE0_UNSUPPORTED);

        clearAlias();
        if (aliases == null) {
            if (arity() == 1) {
                alias((String[]) null);
                tupleAliasList.add(null);
                return this;
            }
            throw new NumberOfAliasesException("aliases' length is not equals " + arity() + ". 参数aliases的长度不等于" + arity() + "。");
        }

        List<TupleAlias> localAliases = Arrays.asList(aliases);
        Set<TupleAlias> aliasSet = new HashSet<>(localAliases);
        if (localAliases.size() != aliasSet.size()) throw new AliasDuplicateException("The aliases can't repeat. 别名不能重复！");

        String[] localStrAliases = localAliases.stream().map( e -> e == null ? null : e.toString()).toArray(String[]::new);
        alias(localStrAliases);
        tupleAliasList.addAll(localAliases);

        return this;
    }

    @Override
    public Tuple alias(String... aliases) {
        if (arity() == 0) throw new UnsupportedOperationException(MSG_FOR_TUPLE0_UNSUPPORTED);

        clearAlias();
        if (aliases == null) {
            if (arity() == 1) {
                putToMapAndList(null, 0);
                return this;
            }
            throw new NumberOfAliasesException("aliases' length is not equals " + arity() + ". 参数aliases的长度不等于" + arity() + "。");
        }

        List<String> localAliases = Arrays.asList(aliases);
        Set<String> aliasSet = new HashSet<>(localAliases);
        if (localAliases.size() != aliasSet.size()) throw new AliasDuplicateException("The aliases can't repeat. 别名不能重复！");

        if (arity() != aliases.length)
            throw new NumberOfAliasesException("aliases' length is not equals " + arity() + ". 参数aliases的长度不等于" + arity() + "。");
        for (int i = 0; i < aliases.length; i++) putToMapAndList(aliases[i], i);

        return this;
    }

    private void putToMapAndList(String alias, int index) {
        alias_index.put(alias, index);
        aliasList.add(alias);
    }

    @Override
    public String aliasType() {
        return !tupleAliasList.isEmpty() ? "tuple" : !aliasList.isEmpty() ? "string" : "null";
    }

    @Override
    public Tuple copyAliases(Tuple tuple) {
        if (tuple.arity() == arity()){
            List<TupleAlias> tempTupleAliases = tuple.getTupleAliases();
            List<String> tempAliases = tuple.getAliases();
            clearAlias();
            if (tempTupleAliases.isEmpty()) {
                if (!tempAliases.isEmpty()) {
                    String[] strAliases = tempAliases.toArray(new String[0]);
                    alias(strAliases);
                }
            }else {
                TupleAlias[] tupleAliases = tempTupleAliases.toArray(new TupleAlias[0]);
                alias(tupleAliases);
            }
            return this;
        }

        throw new NumberOfAliasesException("`tuple.arity()` is not equals " + arity() + ". 参数tuple的元素数量不等于" + arity() + "。");
    }

    @Override
    public void clearAlias() {
        aliasList.clear();
        tupleAliasList.clear();
        alias_index.clear();
    }

    @Override
    public List<TupleAlias> getTupleAliases() {
        return Collections.unmodifiableList(tupleAliasList);
    }

    @Override
    public List<String> getAliases() {
        return Collections.unmodifiableList(aliasList);
    }

    @Override
    public boolean aliasesEquals(Tuple tuple) {
        if (tuple == null || tuple.arity() != arity() || !tuple.aliasType().equals(aliasType())) return false;
        if (arity() == 0) return true;

        List<String> aliases1 = getAliases();
        List<String> aliases2 = tuple.getAliases();
        // tupleAliasList 不用判断，因为 tupleAliasList 会转成 string 存储在 aliasList 中。
        if (aliases1.size() != aliases2.size()) return false;
        if (aliases1.isEmpty()) return true;
        for (int i = 0; i < aliases1.size(); i++) if (!Objects.equals(aliases1.get(i), aliases2.get(i))) return false;

        return true;
    }

    @Override
    public <R> R __(TupleAlias alias) {
        // 这里TupleAlias没有设置不用抛异常，需要再检查字符串别名是否有设置
        // if (tupleAliasList.isEmpty()) throw new AliasNotSetException(MSG_FOR_TUPLE_ALIASES);
        return __(alias == null ? null : alias.toString());
    }

    @Override
    public <R> R __(String alias) {
        if (aliasList.isEmpty()) {
            throw new AliasNotSetException(MSG_FOR_STRING_ALIASES);
        }
        if (alias_index.containsKey(alias)) {
            return element(alias_index.get(alias));
        }else {
            throw new AliasNotFoundException("the alias `" + alias + "` not found. "+" 别名`" + alias + "`没有找到。");
        }
    }

    @Override
    public boolean containsAlias(TupleAlias alias) {
        return !tupleAliasList.isEmpty() && containsAlias(alias.toString());
    }

    @Override
    public boolean containsAlias(String alias) {
        return arity() != 0 && aliasList.contains(alias);
    }

    @Override
    public <R> Map<String, R> toMap() {
        return map();
    }

    private <R> Map<String, R> map() {
        Map<String, R> tupleMap = new HashMap<>();
        if (arity() == 0) return tupleMap;
        for (int i = 0; i < arity(); i++) {
            if (aliasList.isEmpty()) {
                tupleMap.put("_" + (i + 1), element(i));
            }else {
                tupleMap.put(aliasList.get(i), element(i));
            }
        }
        return tupleMap;
    }

    @Override
    public SI toSI() {
        return SI.of(this.toMap());
    }

    @Override
    public <R> Tuple2<TupleAlias, R> elementWithTupleAlias(int n) {
        if (n >= arity()) throw new IndexOutOfBoundsException("Index out of range: " + n + ", Size: " + arity());

        if (tupleAliasList.isEmpty()) {
            throw new AliasNotSetException(MSG_FOR_TUPLE_ALIASES);
        }
        TupleAlias alias = tupleAliasList.get(n);
        R element = this.<R>element(n);

        return new Tuple2<>(alias, element);
    }

    @Override
    public <R> Tuple2<String, R> elementWithAlias(int n) {
        if (n >= arity()) throw new IndexOutOfBoundsException("Index out of range: " + n + ", Size: " + arity());

        if (aliasList.isEmpty()) {
            throw new AliasNotSetException(MSG_FOR_STRING_ALIASES);
        }
        String alias = aliasList.get(n);
        R element = this.<R>element(n);

        return new Tuple2<>(alias, element);
    }

    @Override
    public String toString() {
        if (arity() == 0) {
            return "()";
        }else {
            List<String> strList = new ArrayList<>();
            for (int i = 0; i < arity(); i++) {
                strList.add(_nStr(element(i)));
            }
            return alias_index.isEmpty() ? "(" + String.join(", ", strList) + ")" : "(" + concatElement(strList) + ")";
        }
    }

    private String concatElement(List<String> strList) {
        ArrayList<String> tempStrList = new ArrayList<>();
        for (int i = 0; i < strList.size(); i++) {
            String alias = aliasList.get(i);
            alias = (alias == null ? "`null`" : alias);
            tempStrList.add(alias + ": " + strList.get(i));
        }
        return String.join(", ", tempStrList);
    }

    private <R> String _nStr(R _n) {
        String nstr = _n == null ? "null" : _n.toString();
        // 如果 _n == null，那么无论 R 是什么类型，`_n instanceof Object` 都为 false
        if (_n instanceof String){
            nstr = "\"" + nstr + "\"";
        }
        return nstr;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tuple){
            Tuple tuple = (Tuple) obj;
            if (aliasesEquals(tuple)) {
                for (int i = 0; i < arity(); i++)  if (!Objects.equals(element(i), tuple.element(i))) return false;
                return true;
            }
        }
        return false;
    }

}
