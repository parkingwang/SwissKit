# Swiss Java：A Lightweight Java Lang toolkit library

> 为 Java 提供的小工具库。包括一些常用的工具类。

### ByteKit - 字节工具

字节工具类，提供常用字节操作功能；

### CastKit - 类型转换工具

类型转换工具类，提供Java基础类型转换的接口；

### HashKit - Hash工具

简便的Hash接口，MD5，SHA1和SHA256。

### MapReader - Map提供Key链读取方式

提供一个通过点链字符串来读取多层Map的方式。支持两种方式：

1. 层级。通过 `.` 来表示；
2. 数组。通过 `[x]` 来表示；

例如如下JSON结构：

```json
{
    "message": "YES",
    "errors": [
        { "code": 1999 },
        { "code": 2016 }
    ],
    "profile": {
        "avatar": {
            "url": "http://abc.com/avatar.png",
            "short_url": "http://t.cn/abc.png",
            "hash": "123456789"
        },
        "username": "yoojia chen"
    }
}
```
- 层级读取规则：`profile.avatar.short_url` 返回JSON的 short_url 字段文本；
- 数组读取规则：`errors[1].code`，返回值为 `2016`。

更多见测试例子：[MapReaderTest](./src/test/java/com/parkingwang/lang/MapReaderTest.java)

```java
@Test
public void testObject(){
    JSONObject map = JSON.parseObject("{'message': 'YES', 'errors':[{'code': 1999}, {'code': 2016}]}");

    MapReader reader = MapReader.of(map);
    assertEquals("YES", reader.getCasted("message", "-FAILED-"));
    long code = reader.getCasted("errors[0].code", 0);
    assertEquals(1999, code );
    assertEquals(JSON.parseObject("{'code': 1999}"), reader.getCasted("errors[0]", new JSONObject(0)));
    assertEquals(JSON.parseArray("[{'code': 1999}, {'code': 2016}]"), reader.getCasted("errors", new JSONArray(0)));
}
```

### Lazy / ArgLazy 提供一个懒加载实现

```java
// 定义懒加载实现接口。这里示例的NextProgress将在第一次调用getChecked()时，通过Supplier来创建，并被缓存以供后续使用。
private final Lazy<NextProgress> mProgressView = new Lazy<>(new Supplier<NextProgress>() {
    @Override
    public NextProgress call() {
        return new NextProgress(getContext());
    }
});

// 调用
final NextProgress progress = mProgressView.getChecked();
progress.setMessage(message);
progress.show();

```

### Latched - 提供一个等待设值才继续执行的实现类

通常的使用场景是：

> 在异步操作中，某些执行代码，需要等待异步操作执行完成，并设置了某个值才继续执行，否则一直等待。

### Required - 提供一个检测必备数值的实现

类似 Optional ，并提供一个类似 Latched 的等待检测方法：getAwaitChecked() / getAwaitUnchecked();

### KeyMap - 内置数值类型转换的Map实现

通过 get<TYPE>Value 方法来读取数值类型转换后的Map数值。

### ImmutableList - 不可变List。

----

## 依赖

```gradle

compile 'com.parkingwang:swiss:2.8.1'

```