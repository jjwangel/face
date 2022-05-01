package com.zsrc.face.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jianjiawen
 * @date 2022-04-30 11:00:00
 */
@Data
@ConfigurationProperties(prefix = "arcsoft")
public class ArcsoftProperties {

    private Global global;
    private EngineConfig engineConfig;
    private FunctionConfig functionConfig;

    @Data
    public static class Global {
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
    }

    @Data
    public static class EngineConfig {

        /**
         * 0：VIDEO模式; 1：IMAGE模式
         */
        private Integer detectMode = 1;

        /**
         * 人脸检测角度
         * 1:逆时针0度；2：逆时针90度；3：逆时针180度；4：逆时针270度；5：全角度检测
         */
        private Integer detectFaceOrientPriority = 5;

        /**
         * 最大需要检测的人脸个数，取值范围[1,50]
         */
        private Integer detectFaceMaxNum = 10;

        /**
         * 识别的最小人脸比例（图片长边与人脸框长边的比值）;
         * VIDEO模式取值范围[2,32]，推荐值为16;
         * IMAGE模式取值范围[2,32]，推荐值为32
         */
        private Integer detectFaceScaleVal = 32;
    }

    @Data
    public static class FunctionConfig {
        /**
         * 是否支持年龄检测功能
         */
        private Boolean supportAge = true;

        /**
         * 是否支持3D检测功能
         */
        private Boolean supportFace3dAngle = true;

        /**
         * 是否支持人脸检测功能
         */
        private Boolean supportFaceDetect = true;

        /**
         * 是否支持人脸识别功能
         */
        private Boolean supportFaceRecognition = true;

        /**
         * 是否支持性别检测功能
         */
        private Boolean supportGender = true;

        /**
         * 是否支持RGB活体检测功能
         */
        private Boolean supportLiveness = true;

        /**
         * 是否支持IR活体检测功能
         */
        private Boolean supportIrLiveness = true;
    }
}
