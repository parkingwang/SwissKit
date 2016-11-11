# Swiss：A Lightweight Java Lang toolkit library

> 瑞士军刀：小而有用的工具包

### Stream

为Java集合数据增加`filter`和`map`等在Java8的函数体验。演示如下：

```java
final List<String> output = Stream.from(1,2,3,4,5,6,7,8,9)
        .filter(new Filter<Integer>() {
            public boolean filter(Integer arg) {
                return arg % 2 == 0;
            }
        })
        .map(new Transformer<Integer, String>() {
            public String transform(Integer in) {
                return "S" + in;
            }
        })
        .toList();
final String content = StringKit.join(output, ",");
Assert.assertEquals("S2,S4,S6,S8", content);
```

### ByteKit

字节工具类，提供字节数组转换功能；

### CastKit

类型转换工具类，提供Java基础类型转换的接口；

### HashKit

简便的Hash接口，MD5，SHA1和SHA256。

### StreamKit

Input/Output流复制；

