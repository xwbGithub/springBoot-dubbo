#dubbo-project
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
### 属性的覆盖策略 ### 
[官网描述如下](http://dubbo.apache.org/zh-cn/docs/user/configuration/properties.html)<br>
共3个文件.
>dubbo.properties、application.yml、-D(虚拟机参数设定)<br>
    
    > ==>优先级为-D >dubbo.xml>dubbo.properties(测试请看dubbo.properties描述)


### 启动时检查 ###
[官网介绍](http://dubbo.apache.org/zh-cn/docs/user/demos/preflight-check.html)<br>
通过 spring 配置文件
关闭某个服务的启动时检查 (没有提供者时报错)：
```xml
<dubbo:reference interface="com.foo.BarService" check="false" />
```
关闭所有服务的启动时检查 (没有提供者时报错)：
```xml
<dubbo:consumer check="false" />
```
关闭注册中心启动时检查 (注册订阅失败时报错)：
```xml
<dubbo:registry check="false" />
```
通过 dubbo.properties
```
dubbo.reference.com.foo.BarService.check=false
dubbo.reference.check=false
dubbo.consumer.check=false
dubbo.registry.check=false
```
通过 -D 参数
```yml
java -Ddubbo.reference.com.foo.BarService.check=false
java -Ddubbo.reference.check=false
java -Ddubbo.consumer.check=false 
java -Ddubbo.registry.check=false
```

###【配置覆盖关系】###

以 timeout 为例，显示了配置的查找顺序，其它 retries, loadbalance, actives 等类似：

**方法级优先，接口级次之，全局配置再次之。**<br>
**如果级别一样，则消费方优先，提供方次之。**<br>
其中，服务提供方配置，通过 URL 经由注册中心传递给消费方。<br>
<div>
    <p align="center">
        <img src="http://dubbo.apache.org/docs/zh-cn/user/sources/images/dubbo-config-override.jpg" width="600" height="620"/>
        <br>
</div><br>

综上所述：
```html
    <div>
        <span style="color:red">精确优先</span>：<span>方法>接口>全局</span><br>
        <span style="color:red">消费者设置优先</span>：如果级别一样,方法消费方>提供方
    </div>
```

[其官网介绍如下](http://dubbo.apache.org/zh-cn/docs/user/configuration/xml.html)
<br>
