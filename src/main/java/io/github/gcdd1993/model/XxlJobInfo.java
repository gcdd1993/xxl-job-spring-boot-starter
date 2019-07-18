package io.github.gcdd1993.model;

import lombok.Data;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/7/18.
 */
@Data
public class XxlJobInfo {

    /**
     * 执行器主键ID
     */
    private int jobGroup;

    /**
     * 任务执行CRON表达式
     */
    private String jobCron;

    private String jobDesc;

    private Date addTime;
    private Date updateTime;

    /**
     * 负责人
     */
    private String author;

    /**
     * 报警邮件
     */
    private String alarmEmail;

    /**
     * 执行器路由策略
     */
    private String executorRouteStrategy;

    /**
     * 执行器，任务Handler名称
     */
    private String executorHandler;

    /**
     * 执行器，任务参数
     */
    private String executorParam;

    /**
     * 阻塞处理策略
     */
    private String executorBlockStrategy;

    /**
     * 任务执行超时时间，单位秒
     */
    private int executorTimeout;

    /**
     * 失败重试次数
     */
    private int executorFailRetryCount;

    /**
     * GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
     */
    private String glueType;

    public MultiValueMap<String, Object> toMultiValueMap() {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(20);
        Field[] fields = XxlJobInfo.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                Object value = field.get(this);
                if (value != null) {
                    map.set(fieldName, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return map;
    }

}
