package io.github.gcdd1993.client;

import com.xxl.job.core.biz.model.ReturnT;
import io.github.gcdd1993.model.XxlJobInfo;
import io.github.gcdd1993.xxljob.autoconfigure.XxlJobProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * impl of {@link XxlJobClient}
 *
 * @author gaochen
 * Created on 2019/7/18.
 */
@SuppressWarnings("unchecked")
public class XxlJobClientImpl implements XxlJobClient {

    private final RestTemplate restTemplate;

    private final String baseUrl;

    private static final String XXL_JOB_URL_PREFIX = "/jobinfo";
    private static final String ADD_URL = "/add";
    private static final String UPDATE_URL = "/update";
    private static final String REMOVE_URL = "/remove";
    private static final String STOP_URL = "/stop";
    private static final String START_URL = "/start";

    public XxlJobClientImpl(RestTemplate restTemplate, XxlJobProperties xxlJobProperties) {
        this.restTemplate = restTemplate;
        this.baseUrl = xxlJobProperties.getAdminAddresses();
    }

    @Override
    public ReturnT<String> add(XxlJobInfo jobInfo) {
        String url = baseUrl + XXL_JOB_URL_PREFIX + ADD_URL;

        MultiValueMap<String, Object> valueMap = jobInfo.toMultiValueMap();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(valueMap, new HttpHeaders());

        return restTemplate.postForObject(url, requestEntity, ReturnT.class);
    }

    @Override
    public ReturnT<String> update(int id, XxlJobInfo jobInfo) {
        String url = baseUrl + XXL_JOB_URL_PREFIX + UPDATE_URL;

        MultiValueMap<String, Object> valueMap = jobInfo.toMultiValueMap();
        valueMap.set("id", id);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(valueMap, new HttpHeaders());

        return restTemplate.postForObject(url, requestEntity, ReturnT.class);
    }

    @Override
    public ReturnT<String> remove(int id) {
        String url = baseUrl + XXL_JOB_URL_PREFIX + REMOVE_URL;
        MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<>(3);
        valueMap.set("id", id);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(valueMap, new HttpHeaders());

        return restTemplate.postForObject(url, requestEntity, ReturnT.class);
    }

    @Override
    public ReturnT<String> stop(int id) {
        String url = baseUrl + XXL_JOB_URL_PREFIX + STOP_URL;
        MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<>(3);
        valueMap.set("id", id);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(valueMap, new HttpHeaders());

        return restTemplate.postForObject(url, requestEntity, ReturnT.class);
    }

    @Override
    public ReturnT<String> start(int id) {
        String url = baseUrl + XXL_JOB_URL_PREFIX + START_URL;
        MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<>(3);
        valueMap.set("id", id);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(valueMap, new HttpHeaders());

        return restTemplate.postForObject(url, requestEntity, ReturnT.class);
    }
}
