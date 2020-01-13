package it.world.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取yum配置数据
 */
@Component
@ConfigurationProperties(prefix="properties")
public class Properties {
    //忽略认证的Url
    String[] ignoreUrls;

    public String[] getIgnoreUrls() {
        return ignoreUrls;
    }

    public void setIgnoreUrls(String[] ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }
}
