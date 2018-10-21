package utils;

import graph.Site;

public class Measure {

    public static double normDistance(Site a, Site b) {
        // 获取 a 的坐标.
        double aCoordinateX = a.getCoordinateX();
        double aCoordinateY = a.getCoordinateY();

        // 获取 b 的坐标.
        double bCoordinateX = b.getCoordinateX();
        double bCoordinateY = b.getCoordinateY();

        // 计算 2 范数距离.
        double deltaX = aCoordinateX - bCoordinateX;
        double deltaY = aCoordinateY - bCoordinateY;
        return Math.sqrt(Math.pow(deltaX, 2.0) + Math.pow(deltaY, 2));
    }
}
