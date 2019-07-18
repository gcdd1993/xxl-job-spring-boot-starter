# 简介

[XXL-JOB](http://www.xuxueli.com/xxl-job/#/) Spring Boot 开箱即用。

如果你还没有尝试过XXL-JOB，可以去[XXL-JOB](http://www.xuxueli.com/xxl-job/#/) 官网看下，或者看我写的[分布式任务调度XXL-JOB初体验](https://gcdd1993.github.io/p/29085/)。

# 功能

- 与SpringBoot无缝集成，**实现接近零配置**
- 在代码层面实现任务的增删改查和简单管理，方便动态的修改任务

# 使用

## 1. 将`xxl-job-spring-boot-starter`依赖项添加到项目中：

### Maven

```xml
...
<repositories>
	<repository>
	  <snapshots>
		<enabled>
		  false
		</enabled>
	  </snapshots>
	  <id>
		bintray-gcdd1993-maven
	  </id>
	  <name>
		bintray
	  </name>
	  <url>
		https://dl.bintray.com/gcdd1993/maven
	  </url>
	</repository>
</repositories>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
	<version>${springBootVersion}</version>
</dependency>
<dependency>
	<groupId>com.xuxueli</groupId>
	<artifactId>xxl-job-core</artifactId>
	<version>${xxlJobVersion}</version>
</dependency>
<dependency>
	<groupId>io.github.gcdd1993</groupId>
	<artifactId>xxl-job-spring-boot-starter</artifactId>
	<version>v0.1.0</version>
</dependency>
```

### Gradle

```groovy
repositories {
	maven {
		url  "https://dl.bintray.com/gcdd1993/maven"
	}
}

// 为了引入 RestTemplate
compile 'org.springframework.boot:spring-boot-starter-web'
compile "com.xuxueli:xxl-job-core:${xxlJobVersion}"
	
compile 'io.github.gcdd1993:xxl-job-spring-boot-starter:v0.1.0'
```

## 2. 将设置添加到`application.yml`文件中：

可供配置项如下：

```yaml
xxl:
  job:
	enabled: true # 控制是否自动装配
    admin-addresses: "http://127.0.0.1:8080/xxl-job-admin"
    access-token:
    executor:
      app-name: "xxl-job-spring-boot-starter-test"
      ip:
      port: 8989
      log-path: "/data/xxl-job/log/job-handler"
      log-retention-days: 7
```

最简配置可以如下：

```yaml
xxl:
  job:
    admin-addresses: "http://127.0.0.1:8080/xxl-job-admin"
```

详细的配置项说明可以在[这里](http://www.xuxueli.com/xxl-job/#/?id=%e6%ad%a5%e9%aa%a4%e4%ba%8c%ef%bc%9a%e6%89%a7%e8%a1%8c%e5%99%a8%e9%85%8d%e7%bd%ae)找到

## 3. 取消Admin端的登录校验

如果你不需要在代码层面进行任务的管理，请跳过。

由于使用`RestTemplate`模拟Http请求来操作Job，但是没有模拟登录获取Cookie（懒惰），所以需要把相关接口的登录校验去除。

修改`com.xxl.job.admin.controller.JobInfoController`，在相关接口上添加注解`@PermissionLimit(limit = false)`

```java
@RequestMapping("/add")
@ResponseBody
+ @PermissionLimit(limit = false)
public ReturnT<String> add(XxlJobInfo jobInfo) {
	return xxlJobService.add(jobInfo);
}

@RequestMapping("/update")
@ResponseBody
+ @PermissionLimit(limit = false)
public ReturnT<String> update(XxlJobInfo jobInfo) {
	return xxlJobService.update(jobInfo);
}

@RequestMapping("/remove")
@ResponseBody
+ @PermissionLimit(limit = false)
public ReturnT<String> remove(int id) {
	return xxlJobService.remove(id);
}

@RequestMapping("/stop")
@ResponseBody
+ @PermissionLimit(limit = false)
public ReturnT<String> pause(int id) {
	return xxlJobService.stop(id);
}

@RequestMapping("/start")
@ResponseBody
+ @PermissionLimit(limit = false)
public ReturnT<String> start(int id) {
	return xxlJobService.start(id);
}
```

登录鉴权相关代码位于`com.xxl.job.admin.controller.interceptor`包下，可以说，包结构非常清晰了，一目了然。

做完这一切后打包Admin，搭建Admin端

# 测试

测试代码可以在这里找到 https://github.com/gcdd1993/xxl-job-spring-boot-starter/blob/master/src/test/
