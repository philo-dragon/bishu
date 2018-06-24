package com.pfl.common.entity.module_user;

import java.util.List;

/**
 * Created by Administrator on 2018\6\24 0024.
 */

public class FindBean {

    private Weather weather;
    private Location location;
    private Traffic_restrict traffic_restrict;
    private Car car;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Traffic_restrict getTraffic_restrict() {
        return traffic_restrict;
    }

    public void setTraffic_restrict(Traffic_restrict traffic_restrict) {
        this.traffic_restrict = traffic_restrict;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public static class Weather {
        private String temperature;
        private String desc;

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static class Location{
        private String city;
        private String district;
        private String detail;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }

    public static class Traffic_restrict{

       private List<String> restrict_number;

        public List<String> getRestrict_number() {
            return restrict_number;
        }

        public void setRestrict_number(List<String> restrict_number) {
            this.restrict_number = restrict_number;
        }
    }

    public static class Car{
       private String plate_number;
       private Violation violation;

        public String getPlate_number() {
            return plate_number;
        }

        public void setPlate_number(String plate_number) {
            this.plate_number = plate_number;
        }

        public Violation getViolation() {
            return violation;
        }

        public void setViolation(Violation violation) {
            this.violation = violation;
        }
    }

    public static class Violation{
        private String unsolved_num;
        private String score_cost;
        private String money_cost;

        public String getUnsolved_num() {
            return unsolved_num;
        }

        public void setUnsolved_num(String unsolved_num) {
            this.unsolved_num = unsolved_num;
        }

        public String getScore_cost() {
            return score_cost;
        }

        public void setScore_cost(String score_cost) {
            this.score_cost = score_cost;
        }

        public String getMoney_cost() {
            return money_cost;
        }

        public void setMoney_cost(String money_cost) {
            this.money_cost = money_cost;
        }
    }
}
