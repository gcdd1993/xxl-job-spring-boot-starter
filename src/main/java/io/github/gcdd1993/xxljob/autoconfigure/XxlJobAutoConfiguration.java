package io.github.gcdd1993.xxljob.autoconfigure;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import io.github.gcdd1993.client.XxlJobClient;
import io.github.gcdd1993.client.XxlJobClientImpl;
import io.github.gcdd1993.model.XxlJobInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/7/18.
 */
@Slf4j
@ConditionalOnClass({
        XxlJobExecutor.class,
        XxlJobInfo.class,
        RestTemplate.class
})
@EnableConfigurationProperties(XxlJobProperties.class)
@ConditionalOnProperty(name = "xxl.job.enabled", havingValue = "true", matchIfMissing = true)
public class XxlJobAutoConfiguration {

    @Bean(initMethod = "start", destroyMethod = "destroy")
    @ConditionalOnMissingBean
    public XxlJobSpringExecutor xxlJobExecutor(XxlJobProperties xxlJobProperties) {
        // check properties
        Assert.notNull(xxlJobProperties, "xxl job properties can not be null.");

        Assert.notNull(xxlJobProperties.getAdminAddresses(), "xxl job admin address can not be null.");

        log.info("xxl-job config {} init.", xxlJobProperties);

        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();

        xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdminAddresses());
        xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getAccessToken());

        xxlJobSpringExecutor.setAppName(xxlJobProperties.getExecutor().getAppName());
        xxlJobSpringExecutor.setIp(xxlJobProperties.getExecutor().getIp());
        xxlJobSpringExecutor.setPort(xxlJobProperties.getExecutor().getPort());
        xxlJobSpringExecutor.setLogPath(xxlJobProperties.getExecutor().getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlJobProperties.getExecutor().getLogRetentionDays());


        return xxlJobSpringExecutor;
    }

    @Bean
    @ConditionalOnMissingBean
    public XxlJobClient xxlJobClient(RestTemplate restTemplate, XxlJobProperties xxlJobProperties) {
        return new XxlJobClientImpl(restTemplate, xxlJobProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
