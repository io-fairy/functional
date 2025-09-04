<h1 align="center">📚Functional</h1>

简体中文 | [English](./README.md)

---

Functional是一个强大的Java工具类库，提供了多种便捷的函数式编程工具类。如下：  
+ 🔥提供更简单更好用的Java函数式编程接口 (Java Functional Interface that more simpler and easier to use)  
+ 🔥增强版switch（简单的模式匹配）(Enhanced switch or simple pattern matching supported)  
+ 🔥字符串插值(String Interpolation)  
+ 🔥提供元组（tuple）类型支持  
+ 🕒<u>**不可变且线程安全**</u>的**时间处理类**`DateTime`，提供**统一**的时间API
    - 🔺强大的时间处理API
        - **自动识别**解析多种时间格式串
        - 指定时间单位的**取整操作**（四舍五入、向上/下取整）
        - 时间偏移（指定时间单位的增减）
        - 计算两个时间间隔
        - 时间格式化
        - 获取并格式化**周信息**
        - 各种时间类的互相转换
    - 🔺支持多种时间类型：`Date`, `Calendar`, `LocalDateTime`, `ZonedDateTime`, `OffsetDateTime`, `Instant`
+ 🕒便捷的**秒表**`Stopwatch`工具类，方便测试各个代码片段或任意多个连续代码片段的执行时间
+ 💡**兼容Java 8及Java 9+模块化系统**  
+ **……**  
💡💡💡  
**旧项目地址：**[https://github.com/GG-A/JFunctional](https://github.com/GG-A/JFunctional)   


## 🛠️Environment（开发环境）  
+ JDK 9.0.4 **（兼容Java 8及Java 9+模块化系统）**
+ Apache maven 3.6.1


## 💿集成方式（兼容Java 8及Java 9+模块化系统）
### Maven
```xml
<dependency>
  <groupId>com.iofairy</groupId>
  <artifactId>functional</artifactId>
  <version>0.6.0</version>
</dependency>
```

### Gradle
```
implementation 'com.iofairy:functional:0.6.0'
```


## 🗺️使用指南（User Guide）
- [📘增强版switch（简单的模式匹配）](#增强版switch简单的模式匹配)
  - [匹配对象的值](#匹配对象的值)
  - [null值匹配](#null值匹配)
  - [按类型匹配（替代instanceof）](#按类型匹配替代instanceof)
  - [String匹配](#string匹配)
  - [按条件匹配（替代if语句）](#按条件匹配替代if语句)
- [🔥String Interpolator（字符串插值器）](#string-interpolator字符串插值器)
  - [能做什么](#能做什么)
  - [字符串插值（参数少时）](#字符串插值参数少时)
  - [字符串插值（参数多时）](#字符串插值参数多时)
  - [default-value（设置默认值）](#default-value设置默认值)
  - [`${}` metachar（元字符）](#-metachar元字符)
  - [add-del-set](#add-del-set)
- [📘Functional与函数式接口](#functional与函数式接口)
  - [Java函数式接口说明](#java函数式接口说明)
  - [Functional函数式接口使用](#functional函数式接口使用)
- [📘Tuple（元组）](#tuple元组)
  - [Tuple（元组）使用](#tuple元组使用)
  - [EasyTuple 使用](#easytuple-使用)
- [🕒强大的时间处理API](#强大的时间处理API)
    - [自动识别时间字符串](#自动识别时间字符串)
    - [时间取整操作](#时间取整操作)
    - [时间偏移(时间加减)](#时间偏移时间加减)
    - [计算两个时间间隔](#计算两个时间间隔)
- [🕒秒表`Stopwatch`](#秒表Stopwatch)
- [📝`DateTime`与`Range`类的Swagger配置](#DateTime与Range类的Swagger配置)


## 📘增强版switch（简单的模式匹配）
**增强版switch**不仅支持[传统switch语句匹配的类型](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html)（`byte`, `short`, `char`,  `int`, `enum` and `String`），还支持：
+ 任意类型的匹配
+ 对象类型匹配（替代 instanceof）
+ 条件匹配（替代if语句）


### 匹配对象的值
用于匹配两个值或对象是否相等，注意静态导入（`import static com.iofairy.pattern.Pattern.*;`）
- 带返回值的匹配
```java
import static com.iofairy.pattern.Pattern.*;

String s = "5";
// 带返回值
String result = match(s)
        .when("1", v -> v + v)
        .when("2", v -> v + "a")
        .when(in("3", "4", "5", "6"), v -> v + " - abcd")    // in方法用于一次匹配多个值
        .orElse(v -> "no match");

/*
 * it is equivalent to the code below.
 * 上面代码等同于如下switch代码
 */
String switchResult;
switch (s) {
    case "1":
        switchResult = s + s;
        break;
    case "2":
        switchResult = s + "a";
        break;
    case "3":
    case "4":
    case "5":
    case "6":
        switchResult = s + " - abcd";
        break;
    default:
        switchResult = "no match";
}
```
- 不带返回值的匹配
```java
import static com.iofairy.pattern.Pattern.*;

int i = 10;
// 返回值为null
Void nullValue = match(i)
        .when(1,
                /*
                 * if you want to match `when(V matchValue, V1<V> action)` not `when(V matchValue, R1<V, R> action)`,
                 * you need add `{ }`, see: void-compatible and value-compatible
                 */
                v -> { System.out.println("match value：" + v); })  // add {} to void-compatible. 添加 {} 表示lambda无返回值，解决方法调用歧义（Ambiguous）问题
        .whenNext(10,
                v -> System.out.println("match value：" + v + " whenNext continue..."))
        .when(20,
                v -> System.out.println("match value：" + v))
        .orElse(
                v -> System.out.println("--orElse--"));
/*
 * output:
 * match value：10 whenNext continue...
 * --orElse--
 */
```
🔔️注意：Lambda表达式的[void兼容块与值兼容块](https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.27.2)。


### null值匹配
可以匹配`null`值，可以不用使用 `if(xxx == null){...} else {...}`来进行`null`值的判断。   
🔔️建议将 `null` 值**优先匹配**， 这样如果变量为`null`，则不会再执行后续的分支语句，很大程度避免 `NullPointerException`的异常。
```java
import static com.iofairy.pattern.Pattern.*;

String s = null;
String strResult1 = match(s)
        // Avoid "Ambiguous method call", if you want to match `null` value, you need use `(String) null` or in(null)
        .when((String) null, v -> "string null value")
        .when("abcd", v -> "is not null")
        .orElse(v -> "other value");

assertEquals("string null value", strResult1);

String strResult2 = match(s)
        .when(in(null), v -> "contains null value")
        .when("abcd", v -> "is not null")
        .orElse(v -> "other value");

assertEquals("contains null value", strResult2);

String strResult3 = match(s)
        .when(in("a", "b", null, "c"), v -> "contains null value")
        .when("abcd", v -> "is not null")
        .orElse(v -> "other value");

assertEquals("contains null value", strResult3);


Tuple2<String, Integer> t2 = null;
String classMatch = match(t2, TYPE)
        .when(Integer.class, v -> "integer class")
        .when(null, v -> "value is null: " + v)
        .when(Tuple2.class, v -> "tuple2 class")
        .orElse(v -> "other class");

assertEquals("value is null: " + null, classMatch);

String res = match(null, VALUE)
        .when(null, v -> "null value")
        .orElse(v -> "other value");
assertEquals("null value", res);
```


### 按类型匹配（替代instanceof）
```java
import static com.iofairy.pattern.Pattern.*;

Object o = Tuple.of("zs", 20);
// add `TYPE` to match Class<?>. 这里需要加个TYPE，表示按类型匹配。
Integer result = match(o, TYPE)  
        .when(Integer.class, v -> v + 10)
        .when(Tuple2.class,  v -> v.arity())
        .when(String.class,  v -> v.contains("abc") ? 20 : 30)
        .orElse(v -> 40);
        

/*
 * it is equivalent to the code below.
 * 上面代码等同于如下instanceof代码
 */
Integer ifResult;
if (o instanceof Integer) {
    ifResult = (Integer) o + 10;
} else if (o instanceof Tuple2) {
    ifResult = ((Tuple2) o).arity();
} else if (o instanceof String) {
    ifResult = ((String) o).contains("abc") ? 20 : 30;
} else {
    ifResult = 40;
}
```

### String匹配
```java
import static com.iofairy.pattern.Pattern.*;

String str = "aBcdE123.$fGHIj";
// ignore case match
String matchRes1 = match(str, IGNORECASE)
        .when((String) null, v -> "match null")
        .when("abcd", v -> "match abcd")
        .when("abcde123.$fGHIj", v -> "ignore case match")
        .orElse(v -> "no match");
assertEquals("ignore case match", matchRes1);
// CONTAIN match
String matchRes2 = match(str, CONTAIN)
        .when("abcd", v -> "abcd")
        .when("E123", v -> "E123")
        .orElse(v -> "no match");
assertEquals("E123", matchRes2);
// ignore case for contain
String matchRes3 = match(str, ICCONTAIN)
        .when("abcd1", v -> "abcd1")
        .when(in(null, "aaa", ".$fghi", "123"), v -> ".$fghi")
        .orElse(v -> "no match");
assertEquals(".$fghi", matchRes3);
// PREFIX
String matchRes4 = match(str, PREFIX)
        .when("abcd", v -> "abcd")
        .when("aBcd", v -> "aBcd")
        .orElse(v -> "no match");
assertEquals("aBcd", matchRes4);
// ignore case for suffix
String matchRes5 = match(str, ICSUFFIX)
        .when("fghij", v -> "fGHIj")
        .when("aBcd", v -> "aBcd")
        .orElse(v -> "no match");
assertEquals("fGHIj", matchRes5);
```

### 按条件匹配（替代if语句）
```java
import static com.iofairy.pattern.Pattern.*;

int i = 10;
String result = match()
        .when(i == 0,
                v -> "i is zero")
        .when(i < 5 && i > 0,
                v -> "i is between 0~5")
        .when(i > 5,
                v -> "i is greater than 5")
        .orElse(v -> "i is equals to: " + v);
        
System.out.println("match result：" + result);


/*
 * it is equivalent to the code below
 * 上面代码等同于如下if代码
 */
String ifResult;
if (i == 0) {
    ifResult = "i is zero";
} else if (i < 5 && i > 0) {
    ifResult = "i is between 0~5";
} else if (i > 5) {
    ifResult = "i is greater than 5";
} else {
    ifResult = "i is equals to: " + i;
}
```

## 🔥String Interpolator（字符串插值器）  
### 能做什么 
取代不够优雅、可读性差的`+`号拼接字符串的方式以及Java内置字符串插值器`MessageFormat.format()`和`String.format()`  
- **使用Java内置**  
```java
int id = 12345;
String name = "zhangsan";
float height = 180.5f; 

// 使用 + 号拼接
String res1 = "id: " + id + "  名字：" + name + "  身高(cm): " + height;
System.out.println(res1);

// 使用 MessageFormat.format
String res2 = MessageFormat.format("id: {0}  名字：{1}  身高(cm): {2}", id, name, height);
System.out.println(res2);

// 使用 String.format
String res3 = String.format("id: %d  名字：%s  身高(cm): %.1f", id, name, height);
System.out.println(res3);
```
- **使用string interpolator（可读性强）**
```java
SI si = Tuple.of(id, name, height).alias("id", "name", "height").toSI();
String s = si.$("id: ${id}  名字：${name}  身高(cm): ${height}");
System.out.println(s);
```

### 字符串插值（参数少时）  
```java
SI si = Tuple.of("zs", 20, "tom", 190.5, 123456).alias("name", "age", "nickName", "height", "id").toSI();
String parse = si.$("${name}--${age}--${nickName}--${id}--${height}");  // result: zs--20--tom--123456--190.5
```

### 字符串插值（参数多时）  
```java
SI si = SI.init("         ip -> ", "127.0.0.1",
                "         db -> ", "testdb",
                "       port -> ", 3306,
                "     dbType -> ", "mysql",
                " other_info -> ", Tuple.of("isCluster", true),
                "description -> ", new Object());

String dbInfo = si.$("ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}");
```

### default-value（设置默认值）  
```java
// use ": " (: + space) set default value
String source = "${NAME}--${NAME: tom}--${age: 20}--${ID1:}--${ ID1 }--${ID1: }--${id1}--" +
                "${age::20}--${ID}--${ ID1:  }--${ID: 123456}";
Tuple t1 = Tuple.of("zs", null).alias("NAME", "ID");
String parse = SI.of(t1).$(source);
System.out.println(parse);   // output: zs--zs--20--${ID1:}--${ ID1 }----${id1}--${age::20}--null-- --null
```

### `${}` metachar（元字符）  
```java
SI si = Tuple.of("zs", 123456).alias("NAME", "ID").toSI();
// ${} will be parsed $
String parse = si.$("${NAME}--$${ID}--$$$${ID}--${}{ID}--${}");   // output: zs--$123456--$$$123456--${ID}--$
```


### add-del-set  
```java
String source = "${NAME}--${age: 18}--${nickName}--${ID}--${height}--${_1}--${_2}";

SI si = Tuple.of().toSI();
parse = si.$(source);
assertEquals("${NAME}--18--${nickName}--${ID}--${height}--${_1}--${_2}", parse);

Tuple t2 = Tuple.of(20, "tom").alias("age", "nickName");
si.add(t2);         // add
parse = si.$(source);
assertEquals("${NAME}--20--tom--${ID}--${height}--${_1}--${_2}", parse);

HashMap<String, Object> hashMap = new HashMap<>();
hashMap.put("height", 175);
si.add(hashMap);    // add
parse = si.$(source);
assertEquals("${NAME}--20--tom--${ID}--175--${_1}--${_2}", parse);

si.del("age", "nickName");  // delete
parse = si.$(source);
assertEquals("${NAME}--18--${nickName}--${ID}--175--${_1}--${_2}", parse);

Tuple t3 = Tuple.of(20, "tom").alias("age", "nickName");
si.set(t3);             // set
parse = si.$(source);
assertEquals("${NAME}--20--tom--${ID}--${height}--${_1}--${_2}", parse);
```




## 📘Functional与函数式接口

### Java函数式接口说明
关于**函数式接口**，Java 8标准中也有提供，在`java.util.function`下，总共包含43个接口，这些接口是为了让**Lamdba函数表达式**使用的更加简便。总共包含以下几类接口：

| 接口类型 | 表示 |
| :-----| :----- |
| **Consumer** | 有输入参数，但无返回结果的函数 | 
| **Function** | 有输入参数，并返回结果的函数 | 
| **Predicate** | 有输入参数，并返回boolean类型的结果的函数 | 
| **Supplier** | 不提供参数，但返回结果的函数 | 
| **Operator** | 有输入参数，并返回与参数类型一致的结果 | 

可以看到Java 8标准中提供的函数式接口还是挺丰富的，但是个人感觉使用起来有三个不便的地方：
1. 类别太多，且每个类别下又有几个或者十几个接口，命名不一，不方便记忆
2. 如果有仔细观察过这些函数式接口，会发现这些接口所表示的函数最多只有两个参数，如果要使用3个及以上的函数，就要自己构造
3. 对于抛出异常的函数或Lambda表达式无法很好的支持，必须使用try catch才能正常使用，而不能继续向外抛出异常

#### 🔥而Functional提供了6种类别、60个基础的函数式接口，涵盖了以上5种类别所提供的所有函数，且将原本仅支持 2个参数 扩展到多达 9个参数 的函数，并扩展了支持抛出异常的函数式接口。

**6种类别：**

| 🔥新接口类型         | 含义                       |
|:--------------|:-------------------------|
| **V** (**V**oid)系 | 表示无返回值的函数  | 
| **R** (**R**eturn)系 | 表示有返回值的函数  | 
| **P** (**P**redicate)系 | 表示返回值为boolean的函数 | 
| **VT** (**V**oid and **T**hrow exception)系 | 表示无返回值且可抛出异常的函数 | 
| **RT** (**R**eturn and **T**hrow exception)系 | 表示有返回值且可抛出异常的函数  | 
| **PT** (**P**redicate and **T**hrow exception)系 | 表示返回值为boolean且可抛出异常的函数 | 


采用数字结尾，数字表示的是函数的参数个数，分别提供 **0 ~ 9** 个参数的函数，方便记忆。
以下是**6种类别**的接口说明：

| Vn | 含义 |
| :----:| :----: |
| **V0** | 无参数，无返回值 | 
| **V1** | 1个参数，无返回值 | 
| **...** | ...... | 
| **V9** | 9个参数，无返回值 | 

| Rn | 含义 |
| :----:| :----: |
| **R0** | 无参数，但有返回值 | 
| **R1** | 1个参数，且有返回值 | 
| **...** | ...... | 
| **R9** | 9个参数，且有返回值 | 

|   Pn    | 含义 |
|:-------:| :----: |
| **P0**  | 无参数，返回值为boolean | 
| **P1**  | 1个参数，返回值为boolean | 
| **...** | ...... | 
|  **P9**  | 9个参数，返回值为boolean | 

| VTn | 含义 |
| :----:| :----: |
| **VT0** | 无参数，无返回值且抛出异常 | 
| **VT1** | 1个参数，无返回值且抛出异常 | 
| **...** | ...... | 
| **VT9** | 9个参数，无返回值且抛出异常 | 

| RTn | 含义 |
| :----:| :----: |
| **RT0** | 无参数，但有返回值且抛出异常 | 
| **RT1** | 1个参数，有返回值且抛出异常 | 
| **...** | ...... | 
| **RT9** | 9个参数，有返回值且抛出异常 | 

|   PTn   | 含义 |
|:-------:| :----: |
| **PT0** | 无参数，返回值为boolean且抛出异常 | 
| **PT1** | 1个参数，返回值为boolean且抛出异常 | 
| **...** | ...... | 
| **PT9**  | 9个参数，返回值为boolean且抛出异常 | 


### Functional函数式接口使用  
- V2接口示例  
```java
public void testV2(){
    /*
     Java 8之前：使用匿名内部类，调用v2AsParams
     */
    v2AsParams(new V2<String, String>() {
        @Override
        public void $(String s1, String s2) {
            System.out.println(s1 + " -- " + s2);
        }
    });


    /*
     Java 8 及以后：使用 Lambda 表达式，调用v2AsParams
     */
    v2AsParams((s1, s2) -> System.out.println(s1 + " -- " + s2));

}

// 当一个函数需要接收一个 `两个参数无返回值的函数接口` 时，可以使用现有的 V2<T1, T2>，而不用重新构造一个接口
private void v2AsParams(V2<String, String> v2) {
    v2.$("abcd", "1234");
}
```

- R1接口示例  
```java
public void testR1() {
    List<String> ls = Arrays.asList("1", "2", "3", "4");
    /*
     Java 8之前：使用匿名内部类，调用 map
     */
    List<Integer> intList = map(ls, new R1<String, Integer>() {
        @Override
        public Integer $(String s) {
            return Integer.valueOf(s) + 10;
        }
    });
    System.out.println(intList);      // 输出：[11, 12, 13, 14]

    /*
    Java 8 及以后：使用 Lambda 表达式，调用 map
     */
    List<Integer> map = map(ls, s -> Integer.valueOf(s) + 20);
    System.out.println(map);         // 输出：[21, 22, 23, 24]

}

// 当一个函数需要接收一个 `接收一个参数，并返回值的函数接口` 时，可以使用 R1<T, R>，不用重新构造一个接口，
// 如：java.util.stream.Stream 中的 map 函数
private <T, R> List<R> map(List<T> ls, R1<T, R> r1) {
    ArrayList<R> rs = new ArrayList<>();
    for (T l : ls)
        rs.add(r1.$(l));

    return rs;
}
```

- R2接口（不支持抛出异常） 处理异常示例  
```java
public void testR2Exception(){
    // 必须在 lambda 表达式中使用 try-catch 块处理，无法将异常继续向外抛出
    R2<String, Integer, String> r2 = (s, i) -> {
        if (i == 5) {
            try {
                // 必须使用 try-catch 处理，否则报错
                throw new IOException("抛出异常");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return s + i;
    };

    // 由于R2不支持抛出异常，所以调用 $函数没有异常
    String s = r2.$("abcd", 1);
}
```

- RT2接口（支持抛出异常） 处理异常示例  
```java
public void testRT2Exception() throws IOException { 
    RT2<String, Integer, String, IOException> rt2 = (s, i) -> {
        // 使用 RT2 在lambda 表达式中，不用处理异常，等到调用 $ 函数时再处理
        if (i == 5) throw new IOException("抛出异常");
        return s + i;
    };
    /*
    第一种方式：使用 try-catch 处理异常
     */
    try {
        String s = rt2.$("abcd", 1);
    } catch (IOException e) {
        e.printStackTrace();
    }

    /*
     第二种方式：继续向外抛出异常，在函数上申明异常：  public void rt2_exception() throws IOException
     */
    // String s1 = rt2.$("1234", 5);
    String s2 = rt2.$("1234", 56);
}
```



## 📘Tuple（元组）
元组（Tuple）是用来表示一组数据的集合。与列表（List）类似，但与列表有着本质的区别：
1. 元组可以存放不同类型的数据，而列表只能存放相同类型的数据
2. 元组的值一经初始化，无法修改，只能查看

Java中一直没有提供元组（Tuple）类型的支持，导致有些时候，简单的问题复杂化，特别是当**一个方法需要返回多个值，且这些值的类型不一致**时，采用元组（Tuple）可以提供极大的便利。为此，Functional 提供 **Tuple0 ~ Tuple9** 这 10 种 Tuple 类型。

### Tuple（元组）使用
- 创建元组与取出元组中的元素
```java
Tuple3<String, Integer, Tuple2<String, String>> t3 = new Tuple3<>("zs", 20, new Tuple2<String, String>("123", "abc"));
System.out.println(t3._1);    // 输出: zs
System.out.println(t3._2);    // 输出: 20
System.out.println(t3._3);    // 输出: ("123", "abc")
```

- 为元组中的元素起别名以及通过别名取元素
```java
// 方式一（推荐）
// MyTupleAlias.java
package mypackage;
import com.iofairy.tuple.TupleAlias;

public enum MyTupleAlias implements TupleAlias {
     $USER_ALIAS$,
          ID, NAME, TEL, AGE, BIRTHDAY, PROVINCE, CITY, REGISTERTIME,
     $ORDER_ALIAS$,
          ORDERID, GOODSID, USERID, PRICE, QUANTITY, ORDERTIME, PAYTIME
}

// Test.java
package test.xxx;
import static mypackage.MyTupleAlias.*;

Tuple3<Integer, String, Integer> userInfo = new Tuple3<>(1, "Tom", 20);
userInfo.alias(ID, NAME, AGE);
Integer id  = userInfo.__(ID);     // （推荐）使用枚举值取tuple中的元素
String name = userInfo.__("NAME");   // （不推荐）使用枚举值对应的字符串取tuple中的元素
System.out.println("ID: " + id + "  name: " + name);    // output：ID: 1  name: Tom

// 方式二
Tuple2<String, Integer> t2 = new Tuple2<>("abc", 20).alias("name", "age");
String name = (String)t2.__("name");    // 不使用泛型参数
Integer age = t2.<Integer>__("age");    // 使用泛型参数
System.out.println(name);               // 输出: abc
System.out.println(age);                // 输出: 20

```

- 遍历元组中的元素
```java
Tuple2<String, Integer> t2 = new Tuple2<>("zs", 20).alias("name", "age");
for (int i = 0; i < t2.arity(); i++) {
    Object element = t2.element(i);                                     // 不带别名
    System.out.println(element);                                        // 输出：zs  和   20
    Tuple2<String, Object> elementWithAlias = t2.elementWithAlias(i);   // 带别名
    System.out.println(elementWithAlias);                               // 输出：("name", "zs")   和  ("age", 20)
}
```

- 方法中返回多个值
```java
public Tuple2<String, Integer> returnMultipleValue(){
    String name = "zs";
    Integer age = 20;
    
    return new Tuple2<>(name, age);   // 把 String 和 Integer 的数据一起返回
}
```

- 配合 Java10 的局部变量自动类型推断(Auto Type Inferring)会更好哦
```java
// Java 8 语法
Tuple9<String, Integer, Tuple1<String>, String, Integer, String, Integer, Tuple2<String, String>, String> tuple91 = new Tuple9<>("abcdefg", 20, new Tuple1<>("10000").alias("id"), (String) null, 29, "tupel6", 666, new Tuple2<>("123", "abc"), "tuple9");

// Java 10及以上语法(var)
var tuple9 = new Tuple9<>("abcdefg", 20, new Tuple1<>("10000").alias("id"), (String)null, 29, "tupel6", 666, new Tuple2<>("123", "abc"), "tuple9");
```

### EasyTuple 使用
EasyTuple是简单版的Tuple，在**所有元素都是相同类型**的情境下使用，和列表（List）很像，但是使用起来比列表（List）更方便一些  

```java
EasyTuple8<String> et8 = new EasyTuple8<>("abcdefg", "abc", "bcd", null, "29", "tupel6", "666", "tuple8");
System.out.println(et8);  // 输出: ("abcdefg", "abc", "bcd", null, "29", "tupel6", "666", "tuple8")
EasyTuple8<String> alias = et8.alias(null, "", "testTuple", "abc", "5", "第6个", "7", "8");
System.out.println(alias);  // 输出: (null: "abcdefg", : "abc", testTuple: "bcd", abc: null, 5: "29", 第6个: "tupel6", 7: "666", 8: "tuple8")
String s = et8.__((String) null);
System.out.println(s);
String s1 = et8.__("");
System.out.println(s1);
System.out.println(et8.__("第6个"));
System.out.println(et8._5);
for (int i = 0; i < et8.arity(); i++) {
    Tuple2<String, String> t2 = et8.elementWithAlias(i);
    System.out.println("t2(" + i + "): " + t2);
}
```

## 🕒强大的时间处理API
### 自动识别时间字符串
```java
DateTime dt00 = DateTime.parse("2022-8-01 10:5:15", TZ.UTC);
DateTime dt01 = DateTime.parse("2022/8/01T10:5:15.987");
DateTime dt02 = DateTime.parse("2022");
DateTime dt03 = DateTime.parse("999");
DateTime dt04 = DateTime.parse("2点5分", TZ.NEW_YORK);
DateTime dt05 = DateTime.parse("20220810T170650666");
DateTime dt06 = DateTime.parse("2022-8-01 10:5:15");
DateTime dt07 = DateTime.parse("2022-8-01T10:5:15.987");
DateTime dt08 = DateTime.parse("2022");
DateTime dt09 = DateTime.parse("999");
DateTime dt10 = DateTime.parse("10点5分");
DateTime dt11 = DateTime.parse("2022-8-01T10:5:15.98");
DateTime dt12 = DateTime.parse("202208");
DateTime dt13 = DateTime.parse("20220810");
DateTime dt14 = DateTime.parse("20220810170650");
DateTime dt15 = DateTime.parse("20220810170650666");

System.out.println(dt00.dtDetail());    // 2022-08-01 10:05:15.000000000 [UTC +00:00 GMT 周一]
System.out.println(dt01.dtDetail());    // 2022-08-01 10:05:15.987000000 [Asia/Shanghai +08:00 GMT+8 周一]
System.out.println(dt02.dtDetail());    // 2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
System.out.println(dt03.dtDetail());    // 999-01-01 00:00:00.000000000 [Asia/Shanghai +08:05 GMT+8:05:43 周二]
System.out.println(dt04.dtDetail());    // 1970-01-01 02:05:00.000000000 [America/New_York -05:00 GMT-5 周四]
System.out.println(dt05.dtDetail());    // 2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]
System.out.println(dt06.dtDetail());    // 2022-08-01 10:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
System.out.println(dt07.dtDetail());    // 2022-08-01 10:05:15.987000000 [Asia/Shanghai +08:00 GMT+8 周一]
System.out.println(dt08.dtDetail());    // 2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
System.out.println(dt09.dtDetail());    // 999-01-01 00:00:00.000000000 [Asia/Shanghai +08:05 GMT+8:05:43 周二]
System.out.println(dt10.dtDetail());    // 1970-01-01 10:05:00.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
System.out.println(dt11.dtDetail());    // 2022-08-01 10:05:15.980000000 [Asia/Shanghai +08:00 GMT+8 周一]
System.out.println(dt12.dtDetail());    // 2022-08-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
System.out.println(dt13.dtDetail());    // 2022-08-10 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周三]
System.out.println(dt14.dtDetail());    // 2022-08-10 17:06:50.000000000 [Asia/Shanghai +08:00 GMT+8 周三]
System.out.println(dt15.dtDetail());    // 2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]
```

### 时间取整操作
```java
/*
 各种原始时间类型
 */
LocalDateTime ldt = LocalDateTime.of(2022, 2, 27, 8, 0, 10, 100);
ZonedDateTime zdt = ldt.atZone(TZ.DEFAULT_ZONE);
Instant instant = zdt.toInstant();
OffsetDateTime odt = zdt.toOffsetDateTime();
Calendar calendar = Calendar.getInstance();
calendar.setTime(Date.from(zdt.toInstant()));
Date date = calendar.getTime();
/*
 将各种原始时间转换为 DateTime 类型
 */
DateTime dt1 = DateTime.of(ldt);        // 2022-02-27 08:00:10.000
DateTime dt2 = DateTime.of(zdt);        // 2022-02-27 08:00:10.000
DateTime dt3 = DateTime.of(instant);    // 2022-02-27 08:00:10.000
DateTime dt4 = DateTime.of(odt);        // 2022-02-27 08:00:10.000 [+08:00 +08:00]
DateTime dt5 = DateTime.of(calendar);   // 2022-02-27 08:00:10.000
DateTime dt6 = DateTime.of(date);       // 2022-02-27 08:00:10.000
/*
 各种时间类型取整操作
 */
System.out.println(dt1.round(ChronoUnit.DAYS, RoundingDT.FLOOR));       // 2022-02-27 00:00:00.000
System.out.println(dt2.round(ChronoUnit.DAYS, RoundingDT.CEILING));     // 2022-02-28 00:00:00.000
System.out.println(dt3.round(ChronoUnit.MONTHS, RoundingDT.HALF_UP));   // 2022-03-01 00:00:00.000
System.out.println(dt4.round(ChronoUnit.MINUTES, RoundingDT.CEILING));  // 2022-02-27 08:01:00.000 [+08:00 +08:00]
System.out.println(dt5.round(ChronoUnit.HOURS, RoundingDT.CEILING));    // 2022-02-27 09:00:00.000
System.out.println(dt6.round(ChronoUnit.HOURS, RoundingDT.HALF_UP));    // 2022-02-27 08:00:00.000
```

### 时间偏移(时间加减)
```java
/*
 各种原始时间类型
 */
LocalDateTime ldt = LocalDateTime.of(2022, 2, 27, 8, 0, 10, 100);
ZonedDateTime zdt = ldt.atZone(TZ.DEFAULT_ZONE);
Instant instant = zdt.toInstant();
OffsetDateTime odt = zdt.toOffsetDateTime();
Calendar calendar = Calendar.getInstance();
calendar.setTime(Date.from(zdt.toInstant()));
Date date = calendar.getTime();
/*
 将各种时间转换为 DateTime 类型
 */
DateTime dt1 = DateTime.of(ldt);        // 2022-02-27 08:00:10.000
DateTime dt2 = DateTime.of(zdt);        // 2022-02-27 08:00:10.000
DateTime dt3 = DateTime.of(instant);    // 2022-02-27 08:00:10.000
DateTime dt4 = DateTime.of(odt);        // 2022-02-27 08:00:10.000 [+08:00 +08:00]
DateTime dt5 = DateTime.of(calendar);   // 2022-02-27 08:00:10.000
DateTime dt6 = DateTime.of(date);       // 2022-02-27 08:00:10.000
/*
 各种时间类型偏移（加减）操作
 */
System.out.println(dt1.minusHours(5).plusDays(2));  // 2022-03-01 03:00:10.000
System.out.println(dt2.minusMillis(-10000));        // 2022-02-27 08:00:20.000
System.out.println(dt3.plusDays(2));                // 2022-03-01 08:00:10.000
System.out.println(dt4.plusMonths(1));              // 2022-03-27 08:00:10.000 [+08:00 +08:00]
System.out.println(dt5.plusDays(-5));               // 2022-02-22 08:00:10.000
System.out.println(dt6.minusMinutes(100));          // 2022-02-27 06:20:10.000
```
### 计算两个时间间隔
```java
LocalDateTime fromLDT = LocalDateTime.of(2020, 8, 15, 10, 56, 43, 10000000);
DateTime toDT = DateTime.parse("2022/06/20 20:10:56.200");
SignedInterval interval1 = SignedInterval.between(toDT, fromLDT);
Interval interval2 = Interval.between(fromLDT, toDT);
System.out.println(interval1);      // -1年-10月-5天-9时-14分-13秒-190毫秒
System.out.println(interval2);      // 1年10月5天9时14分13秒190毫秒
```


## 🕒秒表`Stopwatch`
**秒表**`Stopwatch`：方便<u>测试各个代码片段或任意多个连续代码片段的执行时间</u>。一个独立的秒表从运行开始到结束之前，便具有**分段记录**、**持续记录**与**实时记录**的功能  
秒表中的**标记**`Stopwatch.mark()`：可在每个代码片段（业务）开始或结束时打个标记。 一方面是<u>代码片段（业务）间的分界点</u>；同时，也类似于savepoint（保存点），保存当时的时间
```java
/*
 elapsedLastStringAndMark 方法：返回秒表距离上次标记处耗时，并打上新的标记
 等同于：
 stopwatch.elapsedLastString();
 stopwatch.mark();
 */
Stopwatch stopwatch = Stopwatch.run();

// >> 开始业务1
Try.sleep(1000);  // ... 执行一些操作
// << 结束业务1
 System.out.println("sleep(1000)。秒表总耗时：" + stopwatch + "---" + "秒表距离上次标记处耗时1：" + stopwatch.elapsedLastStringAndMark()); // 输出：sleep(1000)。秒表总耗时：1.003(秒)---秒表距离上次标记处耗时1：1.01(秒)

// >> 开始业务2
Try.sleep(500);  // ... 执行一些操作
// << 结束业务2
System.out.println("sleep(500)。秒表总耗时：" + stopwatch + "---" + "秒表距离上次标记处耗时2：" + stopwatch.elapsedLastString()); // 输出：sleep(500)。秒表总耗时：1.518(秒)---秒表距离上次标记处耗时2：507.527(毫秒)

stopwatch.mark();  // 业务开始时打个标记
// >> 开始业务3
Try.sleep(1500);  // ... 执行一些操作
// << 结束业务3
System.out.println("sleep(1500)。秒表总耗时：" + stopwatch + "---" + "秒表距离上次标记处耗时3：" + stopwatch.elapsedLastStringAndMark()); // 输出：sleep(1500)。秒表总耗时：3.034(秒)---秒表距离上次标记处耗时3：1.516(秒)

// 业务1开始到业务3完成所耗时间
Stopwatch.Elapsed elapsed = stopwatch.elapsed(0, 3);
System.out.println("业务1开始到业务3完成所耗时间：" + elapsed + "---" + elapsed.toFullString()); // 输出：业务1开始到业务3完成所耗时间：3.034(秒)---3.034(秒) (index: 0 -> 3, mark: START -> MARK3)

```


## 📝DateTime与Range类的Swagger配置
为了更友好的查看包含`DateTime`与`Range`类的Swagger文档，建议在你的项目中添加如下配置：
```java
import com.iofairy.range.Range;
import com.iofairy.time.DateTime;
import com.fasterxml.classmate.TypeResolver;
import springfox.documentation.spring.web.plugins.Docket;

@Bean
public Docket api() {
  TypeResolver typeResolver = new TypeResolver();

  return new Docket(DocumentationType.OAS_30)
          .apiInfo(apiInfo())
          // ... 
          .select()
          .build()
          .directModelSubstitute(DateTime.class, String.class)      // DateTime 类替换为 String
          .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Range.class, WildcardType.class), String.class, 0));  // Range 类替换为 String
}

```




## 💡IntelliJ IDEA 智能提示
由于接口名过于简单，导致 IntelliJ IDEA 智能提示不是很友好，对于**只有一个字母的接口名**，可能无法智能提示，解决办法：  
1. 使用智能补全快捷键（设置方法，进入IDEA快捷键设置Keymap：**Main menu > Code > Completion > Basic**），我设置的快捷键是：**alt + /** ，以 **V1** 为例：  
a. 输入 v1，会发现没有 **V1 接口**的提示  
![IDEA 智能提示](./images/IDEA%20Smart%20tips%201.png)  
b. 此时，按下 **alt + /**，就会有 **V1 接口**的提示  
![IDEA 智能提示](./images/IDEA%20Smart%20tips%202.png)  

2. 手动导入`lambda`下的所有接口（但是对于**只有一个字母的接口名**依然无法智能提示，只是手动输入的时候，可以避免出现 由于没有导包导致的错误）  
`import com.iofairy.lambda.*;`



## ⭐点个赞哟
如果你喜欢 Functional，感觉 Functional 帮助到了你，可以点右上角 **Star** 支持一下哦，感谢感谢！

## 📜Copyright

   **Copyright (C) 2021 iofairy**, < https://github.com/io-fairy/functional >
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.



