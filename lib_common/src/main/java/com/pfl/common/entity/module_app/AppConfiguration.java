package com.pfl.common.entity.module_app;

import java.util.List;

/**
 * Created by Administrator on 2018\6\22 0022.
 */

public class AppConfiguration {

    private int force_update;
    private Version top_version;
    private Oss oss;

    public int getForce_update() {
        return force_update;
    }

    public void setForce_update(int force_update) {
        this.force_update = force_update;
    }

    public Version getTop_version() {
        return top_version;
    }

    public void setTop_version(Version top_version) {
        this.top_version = top_version;
    }

    public Oss getOss() {
        return oss;
    }

    public void setOss(Oss oss) {
        this.oss = oss;
    }

    public static class Version {
        private String ver_code;
        private List<String> content;

        public String getVer_code() {
            return ver_code;
        }

        public void setVer_code(String ver_code) {
            this.ver_code = ver_code;
        }

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }
    }


    public static class Oss {

        private String endPoint;
        private String bucketName;
        private String callbackUrl;

        public String getEndPoint() {
            return endPoint;
        }

        public void setEndPoint(String endPoint) {
            this.endPoint = endPoint;
        }

        public String getBucketName() {
            return bucketName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }

        public String getCallbackUrl() {
            return callbackUrl;
        }

        public void setCallbackUrl(String callbackUrl) {
            this.callbackUrl = callbackUrl;
        }
    }

}
