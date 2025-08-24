<h1 align="center">📚Functional</h1>

English | [简体中文](./README.zh-CN.md)

---

**Functional** is a powerful Java utility library providing various functional programming tools:
+ 🔥 Provides simpler and better Java functional programming interfaces
+ 🔥 Enhanced switch statement (simple pattern matching support)
+ 🔥 String interpolation (`SI`)
+ 🔥 `Tuple` type support
+ 🕒 **Immutable and thread-safe** `DateTime` datetime utility class with unified temporal API
    - 🔺 Powerful datetime API
        - **Automatic parsing** of multiple datetime formats
        - Time unit-based **rounding** operations (round, ceil, floor)
        - Temporal offset calculations
        - Temporal Interval calculations
        - Custom datetime formatting
        - Week information retrieval and formatting
        - Conversion between temporal types
    - 🔺 Supports multiple temporal types: `Date`, `Calendar`, `LocalDateTime`, `ZonedDateTime`, `OffsetDateTime`, `Instant`, `LocalDate`
+ 🕒 Convenient `Stopwatch` utility for benchmarking code execution times
+ 💡 **Compatibility with Java 8+ and Java 9+ module systems**
+ **...**



# 🗺️ Overview
- [📘Enhanced Switch (Pattern Matching)](#enhanced-switch-pattern-matching)
  - [Object Value Matching](#object-value-matching)
  - [Null Value Handling](#null-value-handling)
  - [Type-based Matching (alternative to instanceof)](#type-based-matching)
  - [String Pattern Matching](#string-pattern-matching)
  - [Conditional Matching (alternative to if-else)](#conditional-matching-alternative-to-if-else)
- [🔥String Interpolator](#string-interpolator)
  - [What can do?](#what-can-do)
  - [Basic Interpolation](#basic-interpolation)
  - [Multi-parameter Interpolation](#multi-parameter-interpolation)
  - [Default Values](#default-values)
  - [`${}` metachar](#-metachar)
  - [Add-Del-Set Operations](#add-del-set-operations)
- [📘Functional Interfaces](#functional-interfaces)
  - [Java Functional Interface Specification](#java-functional-interface-specification)
  - [Functional Interface Implementation](#functional-interface-implementation)
- [📘Tuple](#tuple)
  - [Tuple Usage](#tuple-usage)
  - [EasyTuple Usage](#easytuple-usage)
- [🕒Powerful Temporal API](#powerful-temporal-api)
    - [Auto-parsing Time Strings](#auto-parsing-time-strings)
    - [Time Rounding Operations](#time-rounding-operations)
    - [Time Offset Calculations](#time-offset-calculations)
    - [Time Interval Calculation](#time-interval-calculation)
- [🕒Stopwatch](#stopwatch)


# 🚀Quick Start

## 🛠️Environment
+ JDK 9.0.4 **(Compatibility with Java 8+ and Java 9+ module systems)**
+ Apache maven 3.6.1


## 💿 Integration

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


## 📘Enhanced Switch (Pattern Matching)
**Enhanced switch** supports:
+ Traditional switch-compatible types (`byte`, `short`, `char`, `int`, `enum`, `String`)
+ **Arbitrary type matching**
+ **Object type checks** (replaces `instanceof`)
+ **Conditional branching** (replaces `if-else`)


### Object Value Matching
For value equality checks (requires static import: `import static com.iofairy.pattern.Pattern.*;`)
- Value-returning pattern matching
```java
import static com.iofairy.pattern.Pattern.*;

String s = "5";
// with return value
String result = match(s)
        .when("1", v -> v + v)
        .when("2", v -> v + "a")
        .when(in("3", "4", "5", "6"), v -> v + " - abcd")    // The in() is used to match multiple values at once.
        .orElse(v -> "no match");

/*
 * it is equivalent to the code below.
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

- Void-returning pattern matching
```java
import static com.iofairy.pattern.Pattern.*;

int i = 10;
// returns null
Void nullValue = match(i)
        .when(1,
                /*
                 * if you want to match `when(V matchValue, V1<V> action)` not `when(V matchValue, R1<V, R> action)`,
                 * you need add `{ }`, see: void-compatible and value-compatible
                 */
                v -> { System.out.println("match value：" + v); })  // add {} to void-compatible. 
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
🔔️Note: Lambda expressions [void-compatible or value-compatible](https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.27.2)。


### Null Value Handling
Can match `null` values, eliminating the need for verbose `null` checks like:`if(xxx == null){...} else {...}`   
🔔️ **Recommendation**: **Prioritize null matching** to prevent `NullPointerException` - if a variable is `null`, subsequent branches are skipped automatically.  
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


### Type-based Matching
```java
import static com.iofairy.pattern.Pattern.*;

Object o = Tuple.of("zs", 20);
// add `TYPE` to match Class<?>. 
Integer result = match(o, TYPE)  
        .when(Integer.class, v -> v + 10)
        .when(Tuple2.class,  v -> v.arity())
        .when(String.class,  v -> v.contains("abc") ? 20 : 30)
        .orElse(v -> 40);
        

/*
 * it is equivalent to the code below.
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

### String Pattern Matching
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

### Conditional Matching (alternative to if-else)
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

## 🔥String Interpolator
### What can do?
Replaces cumbersome `+` concatenation and **Java's built-in** interpolators (`MessageFormat.format()` or `String.format()`)  

- **Built-in Java**  
```java
int id = 12345;
String name = "zhangsan";
float height = 180.5f; 

// use `+` concatenation
String res1 = "id: " + id + "  名字：" + name + "  身高(cm): " + height;
System.out.println(res1);

// use MessageFormat.format
String res2 = MessageFormat.format("id: {0}  名字：{1}  身高(cm): {2}", id, name, height);
System.out.println(res2);

// use String.format
String res3 = String.format("id: %d  名字：%s  身高(cm): %.1f", id, name, height);
System.out.println(res3);
```
- **use string interpolator**
```java
SI si = Tuple.of(id, name, height).alias("id", "name", "height").toSI();
String s = si.$("id: ${id}  名字：${name}  身高(cm): ${height}");
System.out.println(s);
```

### Basic Interpolation
```java
SI si = Tuple.of("zs", 20, "tom", 190.5, 123456).alias("name", "age", "nickName", "height", "id").toSI();
String parse = si.$("${name}--${age}--${nickName}--${id}--${height}");  // result: zs--20--tom--123456--190.5
```

### Multi-parameter Interpolation
```java
SI si = SI.init("         ip -> ", "127.0.0.1",
                "         db -> ", "testdb",
                "       port -> ", 3306,
                "     dbType -> ", "mysql",
                " other_info -> ", Tuple.of("isCluster", true),
                "description -> ", new Object());

String dbInfo = si.$("ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}");
```

### Default Values
```java
// use ": " (: + space) set default value
String source = "${NAME}--${NAME: tom}--${age: 20}--${ID1:}--${ ID1 }--${ID1: }--${id1}--" +
                "${age::20}--${ID}--${ ID1:  }--${ID: 123456}";
Tuple t1 = Tuple.of("zs", null).alias("NAME", "ID");
String parse = SI.of(t1).$(source);
System.out.println(parse);   // output: zs--zs--20--${ID1:}--${ ID1 }----${id1}--${age::20}--null-- --null
```

### `${}` metachar
```java
SI si = Tuple.of("zs", 123456).alias("NAME", "ID").toSI();
// ${} will be parsed $
String parse = si.$("${NAME}--$${ID}--$$$${ID}--${}{ID}--${}");   // output: zs--$123456--$$$123456--${ID}--$
```


### Add-Del-Set Operations
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




## 📘Functional Interfaces

### Java Functional Interface Specification
Java 8 introduced **functional interfaces** in the `java.util.function` package to streamline lambda expression usage. These interfaces are categorized based on their operational characteristics:   

| Interface Type | Description                                                |
|:---------------|:-----------------------------------------------------------|
| **Consumer**         | Accepts input, returns void                                |
| **Function**         | Accepts input, returns result                              |
| **Predicate**        | Accepts input, returns boolean                             |
| **Supplier**         | Returns result without input                               |
| **Operator**         | Accepts input, returns same type |

While Java 8's 43 **functional interfaces** in `java.util.function` improve lambda usability, they have limitations:
1. **Overly fragmented**: spread across multiple categories (`Consumer`, `Function`, `Predicate`, etc.), with inconsistent naming conventions
2. **Limited arity**: Most support only 1-2 parameters
3. **No exception handling**: Checked exceptions must be caught within lambdas or wrapped in unchecked exceptions, can't be propagated outside of lambda expressions


#### This Functional provides 4 categories and 40 basic functional interfaces, covering all functions from the aforementioned 5 categories. It extends support from 2-parameter functions to up to 9-parameter functions and expanded handle exceptions.  
**4 Interface Categories**

+ **V** (**V**oid): a function without return value
+ **R** (**R**eturn): a function with return value
+ **VT** (**V**oid and **T**hrow exception): a function without return value + exception
+ **RT** (**R**eturn and **T**hrow exception): a function with return value + exception  

They use **numeric suffixes**, where the number indicates the number of function parameters, providing functions with **0 ~ 9** parameters respectively for easier memorization.  
Below are the interface specifications for the 4 categories:  


| Vn |                          Meaning                          |
| :----:|:---------------------------------------------------------:|
| **V0** |  a function that accepts 0 argument and returns no result | 
| **V1** |  a function that accepts 1 argument and returns no result | 
| **...** |                           ......                          | 
| **V9** | a function that accepts 9 arguments and returns no result | 

| Rn |                               Meaning                               |
| :----:|:-------------------------------------------------------------------:|
| **R0** |      a function that accepts 0 argument and produces a result       | 
| **R1** |      a function that accepts 1 argument and produces a result       | 
| **...** |                               ......                                | 
| **R9** |      a function that accepts 9 arguments and produces a result      | 

| VTn |                               Meaning                               |
| :----:|:-------------------------------------------------------------------:|
| **VT0** |  accepts 0 argument and returns no result, and will throw exception | 
| **VT1** |  accepts 1 argument and returns no result, and will throw exception | 
| **...** |                                ......                               | 
| **VT9** | accepts 9 arguments and returns no result, and will throw exception | 

| RTn |                                      Meaning                                      |
| :----:|:---------------------------------------------------------------------------------:|
| **RT0** |        accepts 0 argument and produces a result, and will throw exception         | 
| **RT1** |        accepts 1 argument and produces a result, and will throw exception         | 
| **...** |                                      ......                                       | 
| **RT9** |        accepts 9 arguments and produces a result, and will throw exception        | 


### Functional Interface Implementation
- `V2` interface example   
```java
public void testV2(){
    /*
     Using Anonymous Inner Classes to Call "v2AsParams" in Java 8 and Earlier
     */
    v2AsParams(new V2<String, String>() {
        @Override
        public void $(String s1, String s2) {
            System.out.println(s1 + " -- " + s2);
        }
    });


    /*
     Using Lambda Expressions to Call "v2AsParams" in Java 8 and Later
     */
    v2AsParams((s1, s2) -> System.out.println(s1 + " -- " + s2));

}

// When a function needs to accept a "2 parameters and void-returning functional interface", the existing V2<T1, T2> can be used instead of reconstructing a new interface.
private void v2AsParams(V2<String, String> v2) {
    v2.$("abcd", "1234");
}
```

- `R1` interface example
```java
public void testR1() {
    List<String> ls = Arrays.asList("1", "2", "3", "4");
    /*
     Using Anonymous Inner Classes to Call "map" in Java 8 and Earlier
     */
    List<Integer> intList = map(ls, new R1<String, Integer>() {
        @Override
        public Integer $(String s) {
            return Integer.valueOf(s) + 10;
        }
    });
    System.out.println(intList);      // output: [11, 12, 13, 14]

    /*
    Using Lambda Expressions to Call "map" in Java 8 and Later
     */
    List<Integer> map = map(ls, s -> Integer.valueOf(s) + 20);
    System.out.println(map);         // output: [21, 22, 23, 24]

}

// When a function needs to accept a "one-parameter value-returning functional interface", R1<T, R> can be used without needing to redefine a new interface
// For example: the map function in java.util.stream.Stream
private <T, R> List<R> map(List<T> ls, R1<T, R> r1) {
    ArrayList<R> rs = new ArrayList<>();
    for (T l : ls)
        rs.add(r1.$(l));

    return rs;
}
```

- `R2` Interface (**without exception throwing support**) Exception Handling Example
```java
public void testR2Exception(){
    // Exceptions must be handled with try-catch blocks in lambda expressions, and cannot be propagated outside
    R2<String, Integer, String> r2 = (s, i) -> {
        if (i == 5) {
            try {
                // they must be handled with try-catch, otherwise an error will occur
                throw new IOException("throw a io exception");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return s + i;
    };

    // since R2 does not support throwing exceptions, calling the $ function does not throw exceptions
    String s = r2.$("abcd", 1);
}
```

- `RT2` Interface (**with exception throwing support**) Exception Handling Example  
```java
public void testRT2Exception() throws IOException { 
    RT2<String, Integer, String, IOException> rt2 = (s, i) -> {
        // Using RT2 in lambda expressions, there's no need to handle exceptions, they can be handled when calling the $ function
        if (i == 5) throw new IOException("throw a io exception");
        return s + i;
    };
    /*
    First approach: handle exceptions using try-catch
     */
    try {
        String s = rt2.$("abcd", 1);
    } catch (IOException e) {
        e.printStackTrace();
    }

    /*
     Second approach: propagate exceptions outward and declare them on the function:
       public void rt2_exception() throws IOException
     */
    // String s1 = rt2.$("1234", 5);
    String s2 = rt2.$("1234", 56);
}
```



## 📘Tuple
Tuple is a data structure used to represent a collection of elements. Similar to List, but with fundamental differences:  
1. Tuples can store elements of different types, while Lists are restricted to homogeneous types  
2. Tuple values are immutable once initialized and cannot be modified  

Java's lack of native tuple support often complicates simple scenarios, especially when **methods need to return multiple values of differing types**. Functional provides **Tuple0 to Tuple9** implementations to address this need.  

### Tuple Usage
- Creating and accessing tuple elements  
```java
Tuple3<String, Integer, Tuple2<String, String>> t3 = new Tuple3<>("zs", 20, new Tuple2<String, String>("123", "abc"));
System.out.println(t3._1);    // output: zs
System.out.println(t3._2);    // output: 20
System.out.println(t3._3);    // output: ("123", "abc")
```

- Aliasing tuple elements and accessing via aliases  
```java
/*
 First approach
 */
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
Integer id  = userInfo.__(ID);     // (Recommended) Access elements of a tuple using enum values
String name = userInfo.__("NAME");   // (Not Recommended) Access elements of a tuple using the string corresponding to the enum value
System.out.println("ID: " + id + "  name: " + name);    // output：ID: 1  name: Tom

/*
 Second approach
 */
Tuple2<String, Integer> t2 = new Tuple2<>("abc", 20).alias("name", "age");
String name = (String)t2.__("name");    // Without using generic parameters
Integer age = t2.<Integer>__("age");    // Using generic parameters
System.out.println(name);               // output: abc
System.out.println(age);                // output: 20

```

- Iterating through tuple elements  
```java
Tuple2<String, Integer> t2 = new Tuple2<>("zs", 20).alias("name", "age");
for (int i = 0; i < t2.arity(); i++) {
    Object element = t2.element(i);                                     // without alias
    System.out.println(element);                                        // output: zs  and   20
    Tuple2<String, Object> elementWithAlias = t2.elementWithAlias(i);   // with alias
    System.out.println(elementWithAlias);                               // output: ("name", "zs")   and  ("age", 20)
}
```

- Returning multiple values from methods  
```java
public Tuple2<String, Integer> returnMultipleValue(){
    String name = "zs";
    Integer age = 20;
    
    return new Tuple2<>(name, age);   // return String and Integer data together
}
```

- Use **Auto Type Inferring** in Java 10 for `Tuple` type  
```java
// Java 8
Tuple9<String, Integer, Tuple1<String>, String, Integer, String, Integer, Tuple2<String, String>, String> tuple91 = new Tuple9<>("abcdefg", 20, new Tuple1<>("10000").alias("id"), (String) null, 29, "tupel6", 666, new Tuple2<>("123", "abc"), "tuple9");

// Java 10+(var)
var tuple9 = new Tuple9<>("abcdefg", 20, new Tuple1<>("10000").alias("id"), (String)null, 29, "tupel6", 666, new Tuple2<>("123", "abc"), "tuple9");
```

### EasyTuple Usage
`EasyTuple` is a simplified version of `Tuple`, used when **all elements are of the same type**, 
similar to List but more convenient for specific scenarios 


```java
EasyTuple8<String> et8 = new EasyTuple8<>("abcdefg", "abc", "bcd", null, "29", "tupel6", "666", "tuple8");
System.out.println(et8);  // output: ("abcdefg", "abc", "bcd", null, "29", "tupel6", "666", "tuple8")
EasyTuple8<String> alias = et8.alias(null, "", "testTuple", "abc", "5", "第6个", "7", "8");
System.out.println(alias);  // output: (null: "abcdefg", : "abc", testTuple: "bcd", abc: null, 5: "29", 第6个: "tupel6", 7: "666", 8: "tuple8")
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

## 🕒Powerful Temporal API
### Auto-parsing Time Strings
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

### Time Rounding Operations
```java
/*
 Various Temporal types 
 */
LocalDateTime ldt = LocalDateTime.of(2022, 2, 27, 8, 0, 10, 100);
ZonedDateTime zdt = ldt.atZone(TZ.DEFAULT_ZONE);
Instant instant = zdt.toInstant();
OffsetDateTime odt = zdt.toOffsetDateTime();
Calendar calendar = Calendar.getInstance();
calendar.setTime(Date.from(zdt.toInstant()));
Date date = calendar.getTime();
/*
 Converting Various Temporal types to `DateTime` type
 */
DateTime dt1 = DateTime.of(ldt);        // 2022-02-27 08:00:10.000
DateTime dt2 = DateTime.of(zdt);        // 2022-02-27 08:00:10.000
DateTime dt3 = DateTime.of(instant);    // 2022-02-27 08:00:10.000
DateTime dt4 = DateTime.of(odt);        // 2022-02-27 08:00:10.000 [+08:00 +08:00]
DateTime dt5 = DateTime.of(calendar);   // 2022-02-27 08:00:10.000
DateTime dt6 = DateTime.of(date);       // 2022-02-27 08:00:10.000
/*
 Rounding operations for Temporal types
 */
System.out.println(dt1.round(ChronoUnit.DAYS, RoundingDT.FLOOR));       // 2022-02-27 00:00:00.000
System.out.println(dt2.round(ChronoUnit.DAYS, RoundingDT.CEILING));     // 2022-02-28 00:00:00.000
System.out.println(dt3.round(ChronoUnit.MONTHS, RoundingDT.HALF_UP));   // 2022-03-01 00:00:00.000
System.out.println(dt4.round(ChronoUnit.MINUTES, RoundingDT.CEILING));  // 2022-02-27 08:01:00.000 [+08:00 +08:00]
System.out.println(dt5.round(ChronoUnit.HOURS, RoundingDT.CEILING));    // 2022-02-27 09:00:00.000
System.out.println(dt6.round(ChronoUnit.HOURS, RoundingDT.HALF_UP));    // 2022-02-27 08:00:00.000
```

### Time Offset Calculations
```java
/*
 Various Temporal types 
 */
LocalDateTime ldt = LocalDateTime.of(2022, 2, 27, 8, 0, 10, 100);
ZonedDateTime zdt = ldt.atZone(TZ.DEFAULT_ZONE);
Instant instant = zdt.toInstant();
OffsetDateTime odt = zdt.toOffsetDateTime();
Calendar calendar = Calendar.getInstance();
calendar.setTime(Date.from(zdt.toInstant()));
Date date = calendar.getTime();
/*
 Converting Various Temporal types to `DateTime` type
 */
DateTime dt1 = DateTime.of(ldt);        // 2022-02-27 08:00:10.000
DateTime dt2 = DateTime.of(zdt);        // 2022-02-27 08:00:10.000
DateTime dt3 = DateTime.of(instant);    // 2022-02-27 08:00:10.000
DateTime dt4 = DateTime.of(odt);        // 2022-02-27 08:00:10.000 [+08:00 +08:00]
DateTime dt5 = DateTime.of(calendar);   // 2022-02-27 08:00:10.000
DateTime dt6 = DateTime.of(date);       // 2022-02-27 08:00:10.000
/*
 Offset (addition/subtraction) operations for Temporal types
 */
System.out.println(dt1.minusHours(5).plusDays(2));  // 2022-03-01 03:00:10.000
System.out.println(dt2.minusMillis(-10000));        // 2022-02-27 08:00:20.000
System.out.println(dt3.plusDays(2));                // 2022-03-01 08:00:10.000
System.out.println(dt4.plusMonths(1));              // 2022-03-27 08:00:10.000 [+08:00 +08:00]
System.out.println(dt5.plusDays(-5));               // 2022-02-22 08:00:10.000
System.out.println(dt6.minusMinutes(100));          // 2022-02-27 06:20:10.000
```
### Time Interval Calculation
```java
LocalDateTime fromLDT = LocalDateTime.of(2020, 8, 15, 10, 56, 43, 10000000);
DateTime toDT = DateTime.parse("2022/06/20 20:10:56.200");
SignedInterval interval1 = SignedInterval.between(toDT, fromLDT);
Interval interval2 = Interval.between(fromLDT, toDT);
System.out.println(interval1);      // -1年-10月-5天-9时-14分-13秒-190毫秒
System.out.println(interval2);      // 1年10月5天9时14分13秒190毫秒
```


## 🕒`Stopwatch`
**Stopwatch**: Facilitates testing the execution time of **code segments** or **multiple consecutive code blocks**. A standalone stopwatch provides **segmented recording**, **continuous recording**, and **real-time recording** capabilities from start to end.  
**Markers** in the stopwatch (`Stopwatch.mark()`): Allows marking points at the beginning or end of code segments (business logic). These serve as **boundaries between code segments** while also functioning like *savepoints* to capture timestamps at specific moments.
```java
/*
 elapsedLastStringAndMark()      - Return Stopwatch elapsed time since last mark and create new mark
 Equivalent to:  
 stopwatch.elapsedLastString();
 stopwatch.mark();
 */
Stopwatch stopwatch = Stopwatch.run();

// >> start business1
Try.sleep(1000);  // ... execute some operations
// << stop business1
 System.out.println("sleep(1000)。Total elapsed time: " + stopwatch + "---" + "Elapsed time since last mark1：" + stopwatch.elapsedLastStringAndMark()); // output: sleep(1000)。Total elapsed time: 1.003(秒)---Elapsed time since last mark1：1.01(秒)

// >> start business2
Try.sleep(500);  // ... execute some operations
// << stop business2
System.out.println("sleep(500)。Total elapsed time: " + stopwatch + "---" + "Elapsed time since last mark2：" + stopwatch.elapsedLastString()); // output: sleep(500)。Total elapsed time: 1.518(秒)---Elapsed time since last mark2：507.527(毫秒)

stopwatch.mark();  // create new mark
// >> start business3
Try.sleep(1500);  // ... execute some operations
// << stop business3
System.out.println("sleep(1500)。Total elapsed time: " + stopwatch + "---" + "Elapsed time since last mark3：" + stopwatch.elapsedLastStringAndMark()); // output: sleep(1500)。Total elapsed time: 3.034(秒)---Elapsed time since last mark3：1.516(秒)

// Time elapsed from Business 1 start to Business 3 completion
Stopwatch.Elapsed elapsed = stopwatch.elapsed(0, 3);
System.out.println("Time elapsed from Business 1 start to Business 3 completion: " + elapsed + "---" + elapsed.toFullString()); // output: Time elapsed from Business 1 start to Business 3 completion: 3.034(秒)---3.034(秒) (index: 0 -> 3, mark: START -> MARK3)

```



## 💡IntelliJ IDEA Smart Prompt
Due to overly simplistic interface names, IntelliJ IDEA's smart prompt is less effective for **single-letter interface names** 
and may fail to trigger suggestions. Solutions:
1. Use the smart completion shortcut key (Configuration steps: Navigate to IDEA's Keymap settings: **Main menu > Code > Completion > Basic**). 
My configured shortcut is: **alt + /**. For example, with V1:  
a. Typing **"v1"** will show no suggestions for the `V1` interface  
![IDEA Smart Prompt](./images/IDEA%20Smart%20tips%201.png)  
b. pressing **alt + /** will display suggestions for the `V1` interface  
![IDEA Smart Prompt](./images/IDEA%20Smart%20tips%202.png)  

2. Manually import all interfaces under lambda(though smart completion still won't work for **single-letter names**, it prevents package import errors during manual entry):  
`import com.iofairy.lambda.*;`



## ⭐Star
If you find **Functional** useful! Please give a **Star**⭐ to support. Thank you!    

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



