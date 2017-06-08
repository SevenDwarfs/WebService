# 订你所想 在线电影订票软件
![logo](./src/main/resources/images/logo1.png)

[![Build Status](https://travis-ci.org/SevenDwarfs/WebService.svg?branch=dev)](https://travis-ci.org/SevenDwarfs/WebService)

## 构建环境
Eclipse + Maven + Spring-boot

## 运行
方法一：使用maven在命令行
```
mvn spring-boot:run
```
方法二：Eclipse环境下
Run as -> Maven Build -> 在 Goals 写
```
spring-boot:run
```

## 访问
```
localhost:8080
```
## 配置
- application.properties 是主配置文件，负责切换配置文件
  - application-dev.properties 是开发时候配置文件，连接本地数据库
  - application-prod.properties 是生产时候配置文件，连接了云端数据库
- 运行时，将application.properties设置为
```
spring.profiles.active=prod
```
可以直接运行。

## 更新
- v0.8.3
  - 添加订单功能
- v0.8.2
  - 添加了电影图集
- v0.8.1
  - 添加了管理员增删改影院功能
- v0.8.0
  - 添加了 影院 排片 功能
- v0.0.9
  - 添加了 logback 日志功能
- v0.0.8
  - 添加了 演员/导演 的查询
- v0.0.7
  - 查询电影时返回的是电影的简要信息：名字，id，海报URL
  - 修复了查询最近上映电影的BUG
  - 将数据库部署到云端，现在可以直接运行本程序无需修改配置
- v0.0.6 加入管理员模块，需要以管理员身份修改数据库
- v0.0.5 完成用户功能模块(登录，注册，修改)
- v0.0.4 集成 c3p0 和 Hibernate
- v0.0.3 项目引入代码质量管理插件PMD
- v0.0.2 项目引入checkstyle，修改包名和代码使得符合Google风格
- v0.0.1 项目启动，HelloWorld
