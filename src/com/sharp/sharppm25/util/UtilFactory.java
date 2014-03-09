package com.sharp.sharppm25.util;

public class UtilFactory {
	
	private static String[] enTips = {"Good","Moderate","Unhealthy for Sensitive Groups",
			"Unhealthy","Very Unhealthy","Hazardous"};
	private static String[] zhTips = {"优","良","轻度污染","中度污染","重度污染","严重污染"};
	
	
	public static String getEnTip(int aqi){
		return enTips[checkLevel(aqi)];
	}
	
	public static String getZhTip(int aqi){
		return zhTips[checkLevel(aqi)];
	}
	
	public static int checkLevel(int aqi){
		if(aqi <= 50){
			return 0;
		}else if(aqi <= 100){
			return 1;
		}else if(aqi <= 150){
			return 2;
		}else if(aqi <= 200){
			return 3;
		}else if(aqi <= 300){
			return 4;
		}else{
			return 5;
		}
	}
	
}
