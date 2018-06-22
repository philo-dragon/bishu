package com.pfl.module_user.entity;

import com.pfl.common.base.MultiTypeAdapter;
import com.pfl.module_user.BR;
import com.pfl.module_user.R;

public class MineTripBean implements MultiTypeAdapter.IItem {
        private String name;
        private String startTime;
        private String endTime;
        private String money;
        private int type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public int getLayout() {
            return R.layout.module_user_item_my_trip;
        }

        @Override
        public int getVariableId() {
            return BR.item;
        }
    }