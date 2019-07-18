package io.github.gcdd1993.client;

import com.xxl.job.core.biz.model.ReturnT;
import io.github.gcdd1993.model.XxlJobInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/7/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
public class XxlJobClientTest {

    @Autowired
    private XxlJobClient xxlJobClient;

    @Test
    public void add() {
        XxlJobInfo xxlJobInfo = new XxlJobInfo();

        xxlJobInfo.setJobGroup(1);
        xxlJobInfo.setJobDesc("测试任务1");
        xxlJobInfo.setJobCron("0 0 0 * * ? *");

        xxlJobInfo.setExecutorRouteStrategy("FIRST");
        xxlJobInfo.setExecutorHandler("xxl-job-executor-sample");
        xxlJobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
        xxlJobInfo.setExecutorFailRetryCount(0);
        xxlJobInfo.setExecutorTimeout(0);
        xxlJobInfo.setAuthor("xxl-job-spring-boot-starter");

        xxlJobInfo.setGlueType("BEAN");

        ReturnT returnT = xxlJobClient.add(xxlJobInfo);

        assert returnT.getCode() == 200;
    }

    @Test
    public void update() {
        XxlJobInfo xxlJobInfo = new XxlJobInfo();

        xxlJobInfo.setJobGroup(1);
        xxlJobInfo.setJobDesc("测试任务修改");
        xxlJobInfo.setJobCron("0 0 0 * * ? *");

        xxlJobInfo.setExecutorRouteStrategy("FIRST");
        xxlJobInfo.setExecutorHandler("xxl-job-executor-sample");
        xxlJobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
        xxlJobInfo.setExecutorFailRetryCount(0);
        xxlJobInfo.setExecutorTimeout(0);
        xxlJobInfo.setAuthor("xxl-job-spring-boot-starter");

        xxlJobInfo.setGlueType("BEAN");

        ReturnT returnT = xxlJobClient.update(5, xxlJobInfo);

        assert returnT.getCode() == 200;
    }

    @Test
    public void remove() {
        ReturnT<String> returnT = xxlJobClient.remove(5);

        assert returnT.getCode() == 200;
    }

    @Test
    public void stop() {
        ReturnT<String> returnT = xxlJobClient.stop(1);

        assert returnT.getCode() == 200;
    }

    @Test
    public void start() {
        ReturnT<String> returnT = xxlJobClient.start(1);

        assert returnT.getCode() == 200;
    }
}