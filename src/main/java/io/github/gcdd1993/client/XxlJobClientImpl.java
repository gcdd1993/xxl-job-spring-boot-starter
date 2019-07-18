package io.github.gcdd1993.client;

import com.xxl.job.core.biz.model.ReturnT;
import io.github.gcdd1993.model.XxlJobInfo;
import io.github.gcdd1993.xxljob.autoconfigure.XxlJobProperties;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/7/18.
 */
public class XxlJobClientImpl implements XxlJobClient {

    private final RestTemplate restTemplate;

    private final String baseUrl;

    public XxlJobClientImpl(RestTemplate restTemplate, XxlJobProperties xxlJobProperties) {
        this.restTemplate = restTemplate;
        this.baseUrl = xxlJobProperties.getAdminAddresses();
    }

    @Override
    public ReturnT<String> add(XxlJobInfo jobInfo) {
        String url = baseUrl + "/jobinfo/add";
        return restTemplate.postForObject(url, jobInfo, ReturnT.class);
    }

    @Override
    public ReturnT<String> update(int id, XxlJobInfo jobInfo) {
        jobInfo.setId(id);
        String url = baseUrl + "/jobinfo/update";
        return restTemplate.postForObject(url, jobInfo, ReturnT.class);
    }

    @Override
    public ReturnT<String> remove(int id) {
        String url = baseUrl + "/jobinfo/remove";
        Map<String, Object> request = new HashMap<>(3);
        request.put("id", id);

        return restTemplate.postForObject(url, request, ReturnT.class);
    }

    @Override
    public ReturnT<String> pause(int id) {
        String url = baseUrl + "/jobinfo/pause";
        Map<String, Object> request = new HashMap<>(3);
        request.put("id", id);

        return restTemplate.postForObject(url, request, ReturnT.class);
    }

    @Override
    public ReturnT<String> start(int id) {
        String url = baseUrl + "/jobinfo/start";
        Map<String, Object> request = new HashMap<>(3);
        request.put("id", id);

        return restTemplate.postForObject(url, request, ReturnT.class);
    }
}
