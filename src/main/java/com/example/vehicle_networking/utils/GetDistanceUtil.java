package com.example.vehicle_networking.utils;

/**
 * @author GO FOR IT
 */
public class GetDistanceUtil {
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离 不同的计算方式存在误差
     *
     * @param lat1  第一个点的纬度
     * @param lng1  第一个点的经度
     * @param lat2  第二个点的纬度
     * @param lng2  第二个点的经度
     * @return 距离 (单位：米)
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }
}