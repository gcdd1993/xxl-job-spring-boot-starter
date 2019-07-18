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

    private Executor executor;

    private String adminAddresses;

    private String accessToken;

    @Data
    public static class Executor {
        private String appName;
        private String ip;
        private int port;
        private String logPath;
        private int logRetentionDays;
    }
}
