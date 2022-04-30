package com.zsrc.face.configuration;

import com.arcsoft.face.FaceEngine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jianjiawen
 * @date 2022-04-30 11:00:00
 */
@Slf4j
@AllArgsConstructor
@Configuration
@EnableConfigurationProperties(ArcsoftProperties.class)
public class ArcsoftConfiguration {

    private final ArcsoftProperties arcsoftProperties;

    @Bean
    public FaceEngine faceEngine(){
        log.info(this.arcsoftProperties.toString());
        return new FaceEngine(this.arcsoftProperties.getSdkLibPath());
    }

}
