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

### 配置覆盖关系 ###

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
    <div>
        <b><span color="red">精确优先</span></b>：<span>方法>接口>全局</span><br>
        <b><span color="red">消费者设置优先</span></b>：如果级别一样,方法消费方>提供方
    </div>

[其官网介绍如下](http://dubbo.apache.org/zh-cn/docs/user/configuration/xml.html)
<br>
### 重试次数 ###
 retries="2"  <br>
 失败自动切换，当出现失败，重试其它服务器 [1]。通常用于读操作，但重试会带来更长延迟。可通过 retries="2" 来设置重试次数(不含第一次)。<br>
 重试次数配置如下：
```xml
 <dubbo:service retries="2" />
 ```
 或者
 ```xml
  <dubbo:reference retries="2" />
  ```
  或者  
  ```xml
   <dubbo:reference>
       <dubbo:method name="findFoo" retries="2" />
   </dubbo:reference>
   ```
 ## 多版本 ##
 
 当一个接口实现，出现不兼容升级时，可以用版本号过渡，版本号不同的服务相互间不引用。
 
 可以按照以下的步骤进行版本迁移：
 
 - 在低压力时间段，先升级一半提供者为新版本
 - 再将所有消费者升级为新版本
 - 然后将剩下的一半提供者升级为新版本
 
 老版本服务 **提供者** 配置：
 ```java
 @com.alibaba.dubbo.config.annotation.Service(version = "1.0.0") //暴露服务
 @Component
 public class UserServiceImpl1 implements UserService {
 }
 ```
 新版本**提供者** 配置：
 ```java
 @com.alibaba.dubbo.config.annotation.Service(version = "2.0.0") //暴露服务
 @Component
 public class UserServiceImpl2 implements UserService {
    }
  ```
  服务**消费者**选择版本调用
  
  调用指定版本
 ```java  
  @Service
  public class UserServiceImpl implements OrderService {
      @Reference(version="1.0.0") 
      private UserService userService;
```
  或者任意匹配
```java  
    @Service
    public class UserServiceImpl implements OrderService {
        @Reference(version="*")
        private UserService userService;
```

注意:本人在测试调用多版本的过程中使用以下配置(消费者端的配置)启动服务报错,没有试出来
```yml
dubbo:
  application:
    name: order-service-consumer #服务名称
  consumer:
    version: *  #匹配任意的版本进行调用
```

# springBoot与Dubbo的三种连接方式 #

## 第1种方式
##### 1、导入dubbo-starter
##### 2、在application.properties中配置dubbo的属性
######  2.1、使用@Service 暴露服务 是dubbo的jar包注解
######  2.2、使用@Reference 引用服务 是dubbo的jar包注解
##### 3、在主启动类添加@EnableDubbo注解
    作用：开启基于dubbo的注解功能(2中实现方式)
    
    3.1、直接在主启动类上添加注解
    ```java
     @SpringBootApplication
     @EnableDubbo //开启基于dubbo的注解功能
     public class BootOrderServiceConsumerApplication {
         public static void main(String[] args) {
             SpringApplication.run(BootOrderServiceConsumerApplication.class, args);
         }
     }
     ```
     3.2、在application.properties中使用包扫描
        dubbo.scan.base-packages=com.atguigu.gmail.service.impl #包扫描(和主类的@EnableDubbo的作用相同)  
## 第2种方式
##### 1、保留dubbo.xml配置文件的方式(里面配置dubbo的基本信息)
##### 2、导入dubbo-starter
##### 3、在主启动类引入配置文件@ImportResource
    提供者
    ```java
    @SpringBootApplication
    @ImportResource(locations = "classpath:provider.xml")
    public class BootUserServiceProviderApplication {
        public static void main(String[] args) {
            SpringApplication.run(BootUserServiceProviderApplication.class, args);
        }
    }
    ```
    消费者
    ```java
    @SpringBootApplication
    @ImportResource(locations = "classpath:consumer.xml")
    public class BootOrderServiceConsumerApplication {
        public static void main(String[] args) {
            SpringApplication.run(BootOrderServiceConsumerApplication.class, args);
        }
    }
    ```

## 第3种方式


