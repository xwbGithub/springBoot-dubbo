# dubbo-project
dubbo 项目开发.

spring普通方式集成dubbo:<br>
order-service-consumer(服务消费者):<br>
user-service-provider(服务提供者)<br>


<span  style="font-size:20px;color:red;"><b>集成SpringBoot开发dubbo框架</b></span><br>
boot-order-service-consumer(服务消费者)<br>
boot-user-service-provider(服务提供者)<br>

版本之间的对应关系
Dependencies<br>
[对应网址](https://github.com/apache/incubator-dubbo-spring-boot-project)<br>
### Dependencies

| versions | Java  | Spring Boot | Dubbo      |
| -------- | ----- | ----------- | ---------- |
| `0.2.0`  | 1.8+ | `2.0.x` | `2.6.2` + |
| `0.1.1`  | 1.7+ | `1.5.x` | `2.6.2` + |

dubbo和zookeeper的版本匹配关系

```xml
 <!-- dubbo依赖 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
             <version>2.6.5</version>
        </dependency>
        <!-- 引入注册中心 -->
        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-framework</artifactId>
            <version>2.12.0</version>
        </dependency>
        <!--  因为导入的dubbo是2.6.5,所以此处需要导入对应版本的zk -->
        <!--dubbo-2.6.2之前使用以下zk -->
        <!--
        <dependency>
            <groupId>com.101tec</groupId>
            <artifactId>zkclient</artifactId>
            <version>0.11</version>
        </dependency>
        -->
```
