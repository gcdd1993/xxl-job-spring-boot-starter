package io.github.gcdd1993.client;

import com.xxl.job.core.biz.model.ReturnT;
import io.github.gcdd1993.model.XxlJobInfo;

/**
 * xxl job 客户端任务管理
 *
 * @author gaochen
 * Created on 2019/7/18.
 */
public interface XxlJobClient {

    /**
     * 添加一个任务
     *
     * @param jobInfo {@link XxlJobInfo}
     * @return 任务ID
     */
    ReturnT add(XxlJobInfo jobInfo);

    /**
     * 修改任务
     *
     * @param id      任务ID
     * @param jobInfo {@link XxlJobInfo}
     * @return if update success
     */
    ReturnT<String> update(int id, XxlJobInfo jobInfo);

    /**
     * 删除任务
     *
     * @param id 任务ID
     * @return if remove success
     */
    ReturnT<String> remove(int id);

    /**
     * 暂停任务
     *
     * @param id 任务ID
     * @return if pause success
     */
    ReturnT<String> stop(int id);

    /**
     * 启动任务
     *
     * @param id 任务ID
     * @return if start success
     */
    ReturnT<String> start(int id);

}
