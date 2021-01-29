# monitorparent

一次来此毕设的尝试：设计一个 C S 架构的 JVM 信息监控系统，包含有定时任务查询其所属工程的 JVM 信息，推送到配置的 MQ 中，利用 S 端进行对数据的消费、落库、与展示。

Client 演示只需配置rabbitmq地址，启动 Main 即可。
