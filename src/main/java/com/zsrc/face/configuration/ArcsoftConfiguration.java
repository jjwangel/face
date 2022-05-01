package com.zsrc.face.configuration;

import com.arcsoft.face.ActiveFileInfo;
import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FunctionConfiguration;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
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
    public FaceEngine faceEngine() throws Exception {
        int errorCode;
        //激活引擎
        FaceEngine faceEngine = new FaceEngine(this.arcsoftProperties.getGlobal().getSdkLibPath());
        errorCode = faceEngine.activeOnline(this.arcsoftProperties.getGlobal().getAppId(), this.arcsoftProperties.getGlobal().getSdkKey());
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            log.error("人脸识别-引擎激活失败！");
            throw new Exception("人脸识别-引擎激活失败！");
        }

        ActiveFileInfo activeFileInfo = new ActiveFileInfo();
        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            log.error("人脸识别-获取激活文件信息失败！");
            throw new Exception("人脸识别-获取激活文件信息失败！");
        } else {
            log.info("人脸识别-激活文件信息：" + activeFileInfo);
        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        switch (this.arcsoftProperties.getEngineConfig().getDetectMode()) {
            case 0: {
                engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_VIDEO);
                break;
            }
            case 1: {
                engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
                break;
            }
            default: {
                log.error("人脸识别-无效的初始化的模式！");
                throw new Exception("人脸识别-无效的初始化的模式！");
            }
        }
        switch (this.arcsoftProperties.getEngineConfig().getDetectFaceOrientPriority()) {
            case 1: {
                engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);
                break;
            }
            case 2: {
                engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_90_ONLY);
                break;
            }
            case 3: {
                engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_180_ONLY);
                break;
            }
            case 4: {
                engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_270_ONLY);
                break;
            }
            case 5: {
                engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
                break;
            }
            default: {
                log.error("人脸识别-无效的人脸检测角度！");
                throw new Exception("人脸识别-无效的人脸检测角度！");
            }
        }
        engineConfiguration.setDetectFaceMaxNum(this.arcsoftProperties.getEngineConfig().getDetectFaceMaxNum());
        engineConfiguration.setDetectFaceScaleVal(this.arcsoftProperties.getEngineConfig().getDetectFaceScaleVal());

        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(this.arcsoftProperties.getFunctionConfig().getSupportAge());
        functionConfiguration.setSupportFace3dAngle(this.arcsoftProperties.getFunctionConfig().getSupportFace3dAngle());
        functionConfiguration.setSupportFaceDetect(this.arcsoftProperties.getFunctionConfig().getSupportFaceDetect());
        functionConfiguration.setSupportFaceRecognition(this.arcsoftProperties.getFunctionConfig().getSupportFaceRecognition());
        functionConfiguration.setSupportGender(this.arcsoftProperties.getFunctionConfig().getSupportGender());
        functionConfiguration.setSupportLiveness(this.arcsoftProperties.getFunctionConfig().getSupportLiveness());
        functionConfiguration.setSupportIRLiveness(this.arcsoftProperties.getFunctionConfig().getSupportIrLiveness());
        engineConfiguration.setFunctionConfiguration(functionConfiguration);

        //初始化引擎
        errorCode = faceEngine.init(engineConfiguration);
        if (errorCode != ErrorInfo.MOK.getValue()) {
            log.error("人脸识别-初始化引擎失败！");
            throw new Exception("人脸识别-初始化引擎失败！");
        }

        return faceEngine;
    }

}
