package io.github.gcdd1993.xxljob.autoconfigure;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import io.github.gcdd1993.client.XxlJobClient;
import io.github.gcdd1993.client.XxlJobClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/7/18.
 */
@Slf4j
@EnableConfigurationProperties(XxlJobProperties.class)
@ConditionalOnProperty(name = "xxl.job.enabled", havingValue = "true", matchIfMissing = true)
public class XxlJobAutoConfiguration {

    @Bean(initMethod = "start", destroyMethod = "destroy")
    @ConditionalOnMissingBean
    public XxlJobSpringExecutor xxlJobExecutor(XxlJobProperties xxlJobProperties) {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();

        xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdminAddresses());
        xxlJobSpringExecutor.setAppName(xxlJobProperties.getExecutor().getAppName());
        xxlJobSpringExecutor.setIp(xxlJobProperties.getExecutor().getIp());
        xxlJobSpringExecutor.setPort(xxlJobProperties.getExecutor().getPort());
        xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getAccessToken());
        xxlJobSpringExecutor.setLogPath(xxlJobProperties.getExecutor().getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlJobProperties.getExecutor().getLogRetentionDays());

        return xxlJobSpringExecutor;
    }

    @Bean
    @ConditionalOnMissingBean
    public XxlJobClient xxlJobClient(RestTemplate restTemplate, XxlJobProperties xxlJobProperties) {
        return new XxlJobClientImpl(restTemplate, xxlJobProperties);
    }

}
