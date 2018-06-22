package com.pfl.module_user.entity;

/**
 * Created by Administrator on 2018\6\22 0022.
 */

public class Score {

    private String score;//我的积分
    private String score_add_yesterday;//昨日增加积分

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore_add_yesterday() {
        return score_add_yesterday;
    }

    public void setScore_add_yesterday(String score_add_yesterday) {
        this.score_add_yesterday = score_add_yesterday;
    }
}
