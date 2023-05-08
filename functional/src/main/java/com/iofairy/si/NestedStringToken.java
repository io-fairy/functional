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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.iofairy.si.SIBase.*;

/**
 * Nested String Token
 *
 * @since 0.4.0
 */
public class NestedStringToken {
    /**
     * 父节点
     */
    transient NestedStringToken parentNode;
    /**
     * <code>${}</code> 中的字符串
     */
    final LinkedList<Object> tokens = new LinkedList<>();
    /**
     * 变量名 variable name
     */
    transient final LinkedList<Object> key = new LinkedList<>();
    /**
     * 默认值
     */
    transient final LinkedList<Object> defaultValue = new LinkedList<>();
    /**
     * 在原始字符串中的开始位置
     */
    int startIndex;
    /**
     * 在原始字符串中的结束位置
     */
    int endIndex;
    /**
     * 是否 <code>${}</code> 封闭
     */
    boolean isClosed;

    public NestedStringToken() {
    }

    public NestedStringToken(int startIndex) {
        this.startIndex = startIndex;
    }

    public NestedStringToken getParentNode() {
        return parentNode;
    }

    public void setParentNode(NestedStringToken parentNode) {
        this.parentNode = parentNode;
    }

    public LinkedList<Object> getTokens() {
        return tokens;
    }

    public LinkedList<Object> getKey() {
        return key;
    }

    public LinkedList<Object> getDefaultValue() {
        return defaultValue;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    /**
     * 添加token
     *
     * @param token token
     */
    public void addToken(String token) {
        tokens.add(token);
    }

    /**
     * 添加token
     *
     * @param token token
     */
    public void addToken(NestedStringToken token) {
        token.parentNode = this;
        this.tokens.add(token);
    }

    /**
     * 批量添加token
     *
     * @param tokens tokens
     */
    public void addTokens(LinkedList<Object> tokens) {
        for (Object token : tokens) {
            if (token instanceof NestedStringToken) {
                ((NestedStringToken) token).parentNode = this;
            }
            this.tokens.add(token);
        }
    }

    /**
     * 移除token
     *
     * @param token token
     */
    public void removeToken(NestedStringToken token) {
        token.parentNode = null;
        this.tokens.remove(token);
    }

    /**
     * 合并连续的字符串token，并将token分别存储在 {@link #key} 与 {@link #defaultValue} 中
     */
    public void mergeAndSplitTokens() {
        if (isClosed) {
            LinkedList<Object> newTokens = new LinkedList<>();
            int indexOfDefaultValue = -1;
            Iterator<Object> iter = this.tokens.iterator();
            StringBuilder sb = new StringBuilder();
            int count = 0;
            while (iter.hasNext()) {
                Object next = iter.next();
                if (next instanceof String) {
                    sb.append(next);
                } else {
                    if (count != 0) indexOfDefaultValue = getIndexOfDefaultValue(newTokens, indexOfDefaultValue, sb);

                    newTokens.add(next);
                    sb = new StringBuilder();
                }
                count++;
            }

            if (sb.length() > 0) indexOfDefaultValue = getIndexOfDefaultValue(newTokens, indexOfDefaultValue, sb);

            this.tokens.clear();
            this.tokens.addAll(newTokens);
            if (indexOfDefaultValue == -1) {
                this.key.addAll(newTokens);
            } else {
                this.key.addAll(newTokens.subList(0, indexOfDefaultValue));
                this.defaultValue.addAll(newTokens.subList(indexOfDefaultValue + 1, newTokens.size()));
            }
        }
    }

    private static int getIndexOfDefaultValue(LinkedList<Object> newTokens, int indexOfDefaultValue, StringBuilder sb) {
        String word = sb.toString();
        int i = word.indexOf(DEFAULT_VALUE_DELIMITER);
        if (indexOfDefaultValue == -1 && i > -1) {  // 之前没有找到默认值，且当前找到默认值
            newTokens.add(word.substring(0, i));
            newTokens.add(DEFAULT_VALUE_DELIMITER);
            indexOfDefaultValue = newTokens.size() - 1;
            newTokens.add(word.substring(i + 2));
        } else {
            newTokens.add(word);
        }
        return indexOfDefaultValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = tokensToString(this.tokens);
        sb.insert(0, PREFIX);
        sb.append(isClosed ? SUFFIX : "");

        return sb.toString();
    }

    public static StringBuilder tokensToString(List<Object> tokens) {
        StringBuilder sb = new StringBuilder();
        tokens.forEach(sb::append);
        return sb;
    }

}
