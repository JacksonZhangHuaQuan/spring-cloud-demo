package com.mvc.demo.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryManager;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


@RestController
@RequestMapping(value = "/info")
public class InfoController {

    @RequestMapping(value = "/consumer",produces = "application/json;charset=UTF-8")
    public String dc(HttpServletRequest request){
        RestTemplate restTemplate = new RestTemplate();
        //获取服务的真实地址
        String myServer = this.getRealServerHost("eureka-client");
        String url = "http://" + myServer + "/dc";
        //请求头
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            headers.add(key, value);
        }
        HttpEntity entity = new HttpEntity(headers);
        //使用restTemplate发起请求并返回结果
        ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return (String) responseEntity.getBody();
    }

    public String getRealServerHost(String serviceId){
        InstanceInfo serverInfo = DiscoveryManager.getInstance()
                        .getDiscoveryClient()
                        .getNextServerFromEureka(serviceId, false);
        String realServerName = serverInfo.getIPAddr() + ":" + serverInfo.getPort();
        return realServerName;
    }

}
