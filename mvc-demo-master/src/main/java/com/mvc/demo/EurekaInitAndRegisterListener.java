package com.mvc.demo;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class EurekaInitAndRegisterListener implements ServletContextListener {

    private static final DynamicPropertyFactory configInstance = DynamicPropertyFactory
            .getInstance();

    public void contextInitialized(ServletContextEvent sce) {
        /*设置被读取配置文件名称  默认config.properties*/
        System.setProperty("archaius.configurationSource.defaultFileName", "config.properties");
        /*注册*/
        registerWithEureka();
    }

    public void registerWithEureka() {
        /*加载本地配置文件 根据配置初始化这台 Eureka Application Service 并且注册到 Eureka Server*/
        DiscoveryManager.getInstance().initComponent(
                new MyInstanceConfig(),
                new DefaultEurekaClientConfig());

        /**本台 Application Service 已启动，准备好侍服网络请求*/
        ApplicationInfoManager.getInstance().setInstanceStatus(
                InstanceInfo.InstanceStatus.UP);

    }

    public void contextDestroyed(ServletContextEvent sce) {
        DiscoveryManager.getInstance().shutdownComponent();
    }
}