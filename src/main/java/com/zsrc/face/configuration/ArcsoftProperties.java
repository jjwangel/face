package com.zsrc.face.configuration;


import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jianjiawen
 * @date 2022-04-30 11:00:00
 */
@Data
@ConfigurationProperties(prefix = "arcsoft.global")
public class ArcsoftProperties {

    /**
     * appId
     */
    private String appId;

    /**
     * sdkKey
     */
    private String sdkKey;

    /**
     * sdkLibPath
     */
    private String sdkLibPath;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
