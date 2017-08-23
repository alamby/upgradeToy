/**
 * Copyright (c) 2008, ConnectInfinity Info-Tech Inc.
 * All rights reserved.
 *
 * Created on 2009-3-4 下午12:21:48
 */
package com.carlos.luke.math;

/**
 * 地理方面的工具类
 * 
 */

public final class GeoUtils {
	public static final double EARTH_RADIUS = 6378137; // 地球半径

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 * 
	 * @param lng1	经度1
	 * @param lat1	纬度1
	 * @param lng2	经度2
	 * @param lat2	纬度2
	 * @return	
	 */
	public static double distance(double lng1, double lat1, double lng2, double lat2) {
		if (lat1 == lat2 && lng1 == lng2)
			return 0;
		
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		return s;
	}
	
	/**
	 * 判断一个点是否在多边形范围内
	 * @param x 要测试的指定的 X 坐标
	 * @param y 要测试的指定的 Y 坐标
	 * @param xpoints X 坐标的数组
	 * @param ypoints Y 坐标的数组
	 * @return
	 */
	public static boolean inPolygon(double x, double y, double[] xpoints, double[] ypoints) {
		int npoints = xpoints.length;
		if (npoints <= 2) {
			return false;
		}
		int hits = 0;

		double lastx = xpoints[npoints - 1];
		double lasty = ypoints[npoints - 1];
		double curx, cury;

		// Walk the edges of the polygon
		for (int i = 0; i < npoints; lastx = curx, lasty = cury, i++) {
			curx = xpoints[i];
			cury = ypoints[i];

			if (cury == lasty) {
				continue;
			}

			double leftx;
			if (curx < lastx) {
				if (x >= lastx) {
					continue;
				}
				leftx = curx;
			} else {
				if (x >= curx) {
					continue;
				}
				leftx = lastx;
			}

			double test1, test2;
			if (cury < lasty) {
				if (y < cury || y >= lasty) {
					continue;
				}
				if (x < leftx) {
					hits++;
					continue;
				}
				test1 = x - curx;
				test2 = y - cury;
			} else {
				if (y < lasty || y >= cury) {
					continue;
				}
				if (x < leftx) {
					hits++;
					continue;
				}
				test1 = x - lastx;
				test2 = y - lasty;
			}

			if (test1 < (test2 / (lasty - cury) * (lastx - curx))) {
				hits++;
			}
		}

		return ((hits & 1) != 0);
	}
    
}
