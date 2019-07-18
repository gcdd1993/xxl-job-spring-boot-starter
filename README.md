# 简介

[XXL-JOB](http://www.xuxueli.com/xxl-job/#/) Spring Boot 开箱即用。

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

compile 'io.github.gcdd1993:xxl-job-spring-boot-starter:v0.1.0'
```

## 2. 将设置添加到`application.yml`文件中：

可供配置项如下：

```yaml
xxl:
  job:
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

{% note info %} 详细的配置项说明可以在[这里](http://www.xuxueli.com/xxl-job/#/?id=%e6%ad%a5%e9%aa%a4%e4%ba%8c%ef%bc%9a%e6%89%a7%e8%a1%8c%e5%99%a8%e9%85%8d%e7%bd%ae)找到 {% endnote %}

