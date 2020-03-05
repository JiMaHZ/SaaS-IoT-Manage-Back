package org.springblade.modules.iot.util;

import java.text.DecimalFormat;

public class DataUtil {
	public static String dataConvert(double value,String type){
		//TODO 完成类型转换
		return "";
	}

	public static void main(String[] args) {
		int originLen = 2;
		int min = 3;
		int max = 6;
		int originValue = 4;
		double result = originValue*(originLen*1.0/(max-min))+min;
		System.out.println(result);
		DecimalFormat df = new DecimalFormat( "0");
		double d1 = 00000012211.000000000000000000;
		double d2 = 4.34534;
		System.out.println(df.format(d1));
		System.out.println(df.format(d2));
		String r1 = "";
		System.out.println(r1.length());
		System.out.println(d2%1);

		float step = (float) 0.001;

		System.out.println((int)(d2%1/step+0.5));
		System.out.println(step);
		float m = (((int)(d2%1/step+0.5))*step);
		System.out.println((int)d2);
		System.out.println(m+(int)d2);
	}
}
