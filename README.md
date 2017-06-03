# 订你所想 在线电影订票软件
![logo](./src/main/resources/images/logo1.png)

[![Build Status](https://travis-ci.org/zhzdeng/movie-booking.svg?branch=dev)](https://travis-ci.org/zhzdeng/movie-booking)

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

## 测试
测试前需要在 application.properties 文件中填写自己数据库的用户名和密码，并且需要本地Mysql有 movieBooking 库
运行 BussinessTest类 的两个测试方法，如果测试通过说明配置成功

## 更新
- v0.0.6 加入管理员模块，需要以管理员身份修改数据库
- v0.0.5 完成用户功能模块(登录，注册，修改)
- v0.0.4 集成 c3p0 和 Hibernate
- v0.0.3 项目引入代码质量管理插件PMD
- v0.0.2 项目引入checkstyle，修改包名和代码使得符合Google风格
- v0.0.1 项目启动，HelloWorld
