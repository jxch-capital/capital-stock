# capital-stock4j-spring-boot-starter
股票数据通用接口

---

示例
```java
    @Autowired
    private StockApiLoadBalance stockApi;

    @Test
    void query() {
        // 查询个股
        StockRes stockRes = stockApi.query(StockParam.builder().code("QQQ")
                .start(Date.from(LocalDate.now().plusDays(-20).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .build());
        
        // 批量查询
        StockBatchRes stockRes = stockApi.query(StockBatchParam.builder().codes(List.of("QQQ", "SPY"))
                .start(Date.from(LocalDate.now().plusDays(-20).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .build());
    }
```

开启代理
```properties
capital.stock4j.proxy.enable=false
capital.stock4j.proxy.host=localhost
capital.stock4j.proxy.port=10809
capital.stock4j.proxy.type=HTTP
```

Maven 依赖
```xml
<dependency>
  <groupId>org.jxch.capital</groupId>
  <artifactId>capital-stock4j-spring-boot-starter</artifactId>
  <version>3.2.5-SNAPSHOT</version>
</dependency>
```

---
开发进度：3.2.5-SNAPSHOT
* &#x2705; 支持 Yahoo URL






