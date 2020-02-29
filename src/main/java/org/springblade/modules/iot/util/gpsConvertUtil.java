package org.springblade.modules.iot.util;

import org.springblade.modules.iot.entity.Device;

import java.util.ArrayList;
import java.util.List;

public class gpsConvertUtil {

//	/**
//	 *
//	 * @param dotPos
//	 * @param locationNum
//	 * @return
//	 */
//	public static long getSecondStr(int dotPos,String locationNum){
//
//		StringBuilder str = new StringBuilder();
//		str.append(locationNum.substring(dotPos,dotPos+2)).append(locationNum.substring(dotPos+3));
//		long lng2Double = Long.parseLong(String.valueOf(str))*10/60;
//		System.out.println(lng2Double);
//		return lng2Double;
//	}
//
//	/**
//	 * 经度-->弧度转化为十进制数
//	 * 12007.008540 -->
//	 * 01   5
//	 * @param lng
//	 * @return
//	 */
//	public static Double convert2NormalLng(String lng){
//		Double lngInDouble = null;
//		StringBuilder lngStr = new StringBuilder();
//
//		int lng1 = Integer.parseInt(lng.substring(0,3));
//
//
//		System.out.println("\':"+getSecondStr(3,lng));
//
//		//浮点处理
//		StringBuilder lng2Str = new StringBuilder();
//		lng2Str.append(lng.substring(3,5)).append(lng.substring(6));
//		double lng2Double = Long.parseLong(String.valueOf(lng2Str))/60.0;
//
//		lngStr.append(lng1).append(".").append(lng2Double);
//		System.out.println("lng: "+lngStr);
//		lngInDouble = Double.parseDouble(String.valueOf(lng2Double));
//
//		ArrayList<String> a = new ArrayList<>();
//		a.toString();
//		return lngInDouble;
//	}
//
//	/**
//	 * 纬度-->弧度转化为十进制数
//	 * 3015.873551 --->
//	 * 01  4
//	 * @param lat
//	 * @return
//	 */
//	public static Double convert2Normallat(String lat){
//		Double latInDouble = null;
//		StringBuilder latStr = new StringBuilder();
//
//		int lat1 = Integer.parseInt(lat.substring(0,2));
//
//		//浮点处理
//		StringBuilder lat2Str = new StringBuilder();
//		lat2Str.append(lat.substring(2,4)).append(lat.substring(5));
//		double lat2Double = Long.parseLong(String.valueOf(lat2Str))/60.0;
//
//		latStr.append(lat1).append(".").append(lat2Double);
//		System.out.println(latStr);
//		latInDouble = Double.parseDouble(String.valueOf(lat2Double));
//
//		ArrayList<String> a = new ArrayList<>();
//		a.toString();
//		return latInDouble;
//	}

	public static void getDevicesWithLocation(List<Device> devices){
		for (Device device : devices) {
			if(device.getLat() != null){
				//修改gps格式
				String latNum = getDoubleNum(device.getLat());
				device.setLat(latNum);
				String lngNum = getDoubleNum(device.getLng());
				device.setLng(lngNum);
			}
		}
	}

	/**
	 * GPS数据GPRMC的转换
	 *
	 * @param num
	 * @return
	 */
	public static String getDoubleNum(String num){
		double resultNum = 0;
		if(num != null){
			double gpsNum = Double.parseDouble(num);
			resultNum = (int)(gpsNum/100) + (gpsNum/100.0 - (int)(gpsNum/100)) *100.0 / 60.0;
		}
		return String.valueOf(resultNum);
	}

	public static void main(String[] args) {
//		System.out.println(a);
//		System.out.println(b);
		double gpslng = 12007.008540;
		double gpslat = 3015.873551;
		double resultlng = 0;
		double resultlat = 0;
		resultlng = (int)(gpslng/100) + (gpslng/100.0 - (int)(gpslng/100)) *100.0 / 60.0;
		resultlat = (int)(gpslat/100) + (gpslat/100.0 - (int)(gpslat/100)) *100.0 / 60.0;

		System.out.println(resultlng);
		System.out.println(resultlat);
	}
}
