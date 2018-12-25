需要的修改的是注册中心的地址：<br/>

里面包含了控制台dubbo-admin、和监控中心dubbo-monitor-simple<br/>
incubator-dubbo-ops-master\dubbo-admin\src\main\resources的application.properties文件<br/>




dubbo-monitor-simple监控中心需要修改的地方：<br/>
修改路径为 dubbo-monitor-simple-2.0.0\conf的dubbo.properties文件<br/>
dubbo.registry.address=zookeeper://127.0.0.1:2181<br/>
dubbo.protocol.port=7070<br/>
dubbo.jetty.port=8080<br/>
启动方式为:双击start.bat文件