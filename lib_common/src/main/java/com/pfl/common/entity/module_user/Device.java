package com.pfl.common.entity.module_user;

import com.pfl.common.entity.base.BaseEntyty;

import java.util.List;

/**
 * Created by Administrator on 2018\6\24 0024.
 */

public class Device {

    private List<DeviceBean> list;

    public List<DeviceBean> getList() {
        return list;
    }

    public void setList(List<DeviceBean> list) {
        this.list = list;
    }

    public static class DeviceBean extends BaseEntyty {
        private String id;
        private String name;
        private String imei;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }
    }
}
