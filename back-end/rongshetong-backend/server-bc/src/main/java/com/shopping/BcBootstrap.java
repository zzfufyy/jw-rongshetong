package com.shopping;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.MultipartConfigElement;
import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * EnableAspectJAutoProxy 表示开启AOP代理自动配置
 */
@SpringBootApplication(scanBasePackages = "com.shopping")
@EnableConfigurationProperties
@MapperScan({"com.shopping.wx.mapper", "com.shopping.wx.managed_mapper"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@EnableScheduling
public class BcBootstrap {

    public static void main(String[] args) throws Exception {
        // MQ日志配置
        //System.setProperty("rocketmq.client.log.loadconfig","false");
        System.setProperty("ons.client.logFileMaxIndex", "3");
        System.setProperty("ons.client.logLevel", "Info");
        // MQ异常之springboot环境下相同类进行转换出现ClassCastException异常 https://www.jianshu.com/p/e6d5a3969343
        System.setProperty("spring.devtools.restart.enabled", "false");
        // 禁用nacos默认的日志配置,防止覆盖log4j配置
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(BcBootstrap.class, args);
    }

    /**
     * 上传文件新增临时存放路径
     * 报错信息： java.io.IOException: java.io.FileNotFoundException:
     * /tmp/tomcat.273391201583741210.8080/work/Tomcat/localhost/ROOT/tmp/source/IMG_20160129_132623.jpg (No such file or directory)
     *
     * @return
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = System.getProperty("user.dir") + "/data/tmp";
        File file = new File(location);
        if (!file.exists()) {
            file.mkdirs();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }

    /**
     * 让
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("wx.yml"));
        configurer.setProperties(yaml.getObject());
        return configurer;
    }

    // 在某配置类中添加如下内容
    // 监听的http请求的端口,需要在application配置中添加http.port=端口号，如80
    @Value("${http.port}")
    Integer httpPort;

    //正常启用的https端口 如443
    @Value("${server.port}")
    Integer httpsPort;

    // springboot2 写法
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(httpPort);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(httpsPort);
        return connector;
    }

}
