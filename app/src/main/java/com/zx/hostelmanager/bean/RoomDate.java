package com.zx.hostelmanager.bean;

/**
 * Created by zhangxin on 2017/4/24 0024.
 * <p>
 * Description :
 */

public class RoomDate {
    public String weekDay;
    public String date;
    public boolean flag; //默认是false;

    public RoomDate(int weekDay, String date) {
        this.weekDay = getWeekDay(weekDay);
        this.date = date;
    }

    private String getWeekDay(int weekDay) {
        switch (weekDay) {
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            case 6:
                return "周六";
            case 7:
                return "周日";
            default:
                return "";
        }
    }
}
