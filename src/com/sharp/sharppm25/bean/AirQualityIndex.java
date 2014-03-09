package com.sharp.sharppm25.bean;
/**
 *  空气质量指数
 * @author CJF
 *
 */
public class AirQualityIndex {
	// aqi
	private int aqi;
	// 细颗粒物
	private int pm25;
	// 可吸入颗粒物
	private int pm10;
	// 臭氧
	private int o3;
	// 二氧化氮
	private int no2;
	// 二氧化硫
	private int so2;
	// 一氧化碳
	private int co;
	// 温度
	private int temperature;
	// 露点温度
	private int dew;
	// 压强
	private int pressure;
	// 湿度
	private int humidity;
	// 风力
	private int wind;
	
	public int getPm25() {
		return pm25;
	}
	public void setPm25(int pm25) {
		this.pm25 = pm25;
	}
	public int getPm10() {
		return pm10;
	}
	public void setPm10(int pm10) {
		this.pm10 = pm10;
	}
	public int getO3() {
		return o3;
	}
	public void setO3(int o3) {
		this.o3 = o3;
	}
	public int getNo2() {
		return no2;
	}
	public void setNo2(int no2) {
		this.no2 = no2;
	}
	public int getSo2() {
		return so2;
	}
	public void setSo2(int so2) {
		this.so2 = so2;
	}
	public int getCo() {
		return co;
	}
	public void setCo(int co) {
		this.co = co;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public int getDew() {
		return dew;
	}
	public void setDew(int dew) {
		this.dew = dew;
	}
	public int getPressure() {
		return pressure;
	}
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public int getWind() {
		return wind;
	}
	public void setWind(int wind) {
		this.wind = wind;
	}
	public void setAqi(int aqi) {
		this.aqi = aqi;
	}
	public int getAqi() {
		return aqi;
	}
	
	
	
}
