# 项目名称

图书馆管理系统

（tips:其实个人觉得这个东西做的很垃圾，都不咋敢放出来给各位大佬看）

## 上手指南

前往[Release](https://github.com/YYTtheBest/SpringbootBookManager/releases)
,下载最新的jar包，然后`java -server -Xms2m -Xmx256m -jar sbm.jar`就可以跑起来啦
端口号请查看日志文件或命令行

## 技术架构：

### 开发环境

* 语言：Java 11+ (小于等于17)

* IDE(JAVA)： IDEA(必须安装lombok插件 )、Vscode

* IDE(前端)： Vscode、IDEA

* 依赖管理：Maven

* 数据库脚本：MySQL8.0.27

### 后端

* 基础框架：Spring Boot 2.3.7

* 持久层框架：MybatisPlus 3.4

* 日志打印：log4j

* 测试：junit

* 其他：hutool(狂吹这个小工具)、jackson、thymeleaf、lombok（简化代码）等。

### 前端

* 框架：layui、layuiminiv2