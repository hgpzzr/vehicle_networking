package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.User;
import com.alibaba.fastjson.JSONObject;
import com.example.vehicle_networking.vo.readData.DataInfoDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sun.rmi.runtime.Log;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Collections;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/8 8:15
 */
@Service
@Slf4j
public class RestTemplateTo {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 以get方式请求第三方http接口 getForEntity
     * @param url
     * @return
     */
    public JSONObject doGetWith(String url, String cookie){

        // 需要转义
        URI uri = UriComponentsBuilder.fromUriString(url).build(true).toUri();

        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Cookie", Collections.singletonList(cookie));
        HttpEntity request = new HttpEntity(headers);
        // 构造execute()执行所需要的参数。
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, JSONObject.class);
        ResponseExtractor<ResponseEntity<JSONObject>> responseExtractor = restTemplate.responseEntityExtractor(JSONObject.class);
        // 执行execute()，发送请求
        ResponseEntity<JSONObject> response = restTemplate.execute(uri, HttpMethod.GET, requestCallback, responseExtractor);
        log.info(" 返回数据 = {}",response.getBody());
        return response.getBody();
    }

    /**
     * 以get方式请求第三方http接口 getForObject
     * 返回值返回的是响应体，省去了我们再去getBody()
     * @param url
     * @return
     */
    public User doGetWith2(String url){
        User user  = restTemplate.getForObject(url, User.class);
        return user;
    }

    /**
     * 以post方式请求第三方http接口 postForEntity
     * @param url
     * @return
     */
    public String doPostWith1(String url){
        User user = new User();
        user.setUserName("yangjing");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, user, String.class);
        String body = responseEntity.getBody();
        return body;
    }

    /**
     * 以post方式请求第三方http接口 postForEntity
     * @param url
     * @return
     */
    public String doPostWith2(String url){
        User user = new User();
        user.setUserName("Ric");
        String body = restTemplate.postForObject(url, user, String.class);
        return body;
    }

    /**
     * exchange
     * @return
     */
    public String doExchange(String url, Integer age, String name){
        //header参数
        HttpHeaders headers = new HttpHeaders();
        String token = "asdfaf2322";
        headers.add("authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        //放入body中的json参数
        JSONObject obj = new JSONObject();
        obj.put("age", age);
        obj.put("name", name);

        //组装
        HttpEntity<JSONObject> request = new HttpEntity<>(obj, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        String body = responseEntity.getBody();
        return body;
    }
}
