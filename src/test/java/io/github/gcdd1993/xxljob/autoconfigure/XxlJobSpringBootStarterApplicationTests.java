package io.github.gcdd1993.xxljob.autoconfigure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
public class XxlJobSpringBootStarterApplicationTests {

    @Autowired
    private XxlJobAutoConfiguration xxlJobAutoConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(XxlJobSpringBootStarterApplicationTests.class, args);
    }

    @Test
    public void contextLoads() {
    }

}
