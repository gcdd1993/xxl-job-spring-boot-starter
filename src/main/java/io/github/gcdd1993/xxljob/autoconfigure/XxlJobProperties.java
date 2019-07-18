package io.github.gcdd1993.xxljob.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/7/18.
 */
@Data
@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobProperties {

    private static final String DEFAULT_EXECUTOR_APP_NAME = "xxl-job-executor-sample";
    private static final int DEFAULT_EXECUTOR_PORT = 9999;
    private static final String DEFAULT_EXECUTOR_LOG_PATH = "/data/xxl-job/log/job-handler";
    private static final int DEFAULT_EXECUTOR_LOG_RETENTION_DAYS = 7;

    private Executor executor = new Executor();

    private String adminAddresses;

    private String accessToken;

    @Data
    public static class Executor {
        private String appName = DEFAULT_EXECUTOR_APP_NAME;
        private String ip;
        private int port = DEFAULT_EXECUTOR_PORT;
        private String logPath = DEFAULT_EXECUTOR_LOG_PATH;
        private int logRetentionDays = DEFAULT_EXECUTOR_LOG_RETENTION_DAYS;
    }
}
