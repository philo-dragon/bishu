package com.pfl.common.entity.module_app;

import java.util.List;

/**
 * Created by Administrator on 2018\6\22 0022.
 */

public class AppConfiguration {

    private int force_update;
    private Version top_version;

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


}
