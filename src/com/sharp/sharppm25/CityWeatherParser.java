package com.sharp.sharppm25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import jodd.jerry.Jerry;
import android.util.Log;

import com.sharp.sharppm25.bean.Result;

public class CityWeatherParser {
	private String cityName;
	private static URL url;
	private static URLConnection conn;

	public CityWeatherParser(String cityName) {
		this.cityName = cityName;
	}
	public Result parseByJerry(){
		Result result = new Result();
		try {
			Log.i(this.getClass().getName(), "Start Connecting......");
			long t1 = System.currentTimeMillis();
			url = new URL(GlobalConstant.URL_PATTERN_START + this.cityName
					+ GlobalConstant.URL_PATTERN_END);
			conn = url.openConnection();
			conn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)");
			conn.setDoOutput(true);

			InputStream inputStream = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(inputStream, "utf8");
			
			StringBuffer sb = new StringBuffer();
			BufferedReader in = new BufferedReader(isr, 8 * 1024);
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
				sb.append("\n");
			}
			String resultString = sb.toString();
			Log.i(this.getClass().getName(), "End Connecting......");
			long t2 = System.currentTimeMillis();
			Log.i(this.getClass().getName(), "Execute Time is " + (t2 - t1));
			
			Log.i(this.getClass().getName(), "Start Parsing......");
			Jerry doc = Jerry.jerry(resultString);
			Log.i(this.getClass().getName(), "End Parsing......");
			long t3 = System.currentTimeMillis();
			Log.i(this.getClass().getName(), "Parse Time is " + (t3 - t2));
			
			result.getIndex().setAqi(Integer.parseInt(doc.$("div."+GlobalConstant.AQI).text()));
			Log.i(this.getClass().getName(), "End AQI......");
			
			result.getIndex().setPm25(Integer.parseInt(doc.$("td#"+GlobalConstant.PM25).first().text()));
			Log.i(this.getClass().getName(), "End PM25......");
			
			result.getIndex().setPm10(Integer.parseInt(doc.$("td#"+GlobalConstant.PM10).first().text()));
			Log.i(this.getClass().getName(), "End PM10......");
			
			result.getIndex().setNo2(Integer.parseInt(doc.$("td#"+GlobalConstant.NO2).first().text()));
			Log.i(this.getClass().getName(), "End NO2......");
			
			result.getIndex().setSo2(Integer.parseInt(doc.$("td#"+GlobalConstant.SO2).first().text()));
			Log.i(this.getClass().getName(), "End SO2......");
			
			result.getIndex().setCo(Integer.parseInt(doc.$("td#"+GlobalConstant.CO).first().text()));
			Log.i(this.getClass().getName(), "End CO......");
			
			result.getIndex().setO3(Integer.parseInt(doc.$("td#"+GlobalConstant.O3).first().text()));
			Log.i(this.getClass().getName(), "End O3......");
			
			result.setStatus(true);

		} catch (MalformedURLException e) {
			result.setStatus(false);
			result.setMsg("Please Check Your City's Code!");
			result.setDetail(e.toString());
		} catch (IOException e) {
			result.setStatus(false);
			result.setMsg("Please Check Your Network!");
			result.setDetail(e.toString());
		} catch (NumberFormatException e) {
			result.setStatus(true);
			result.setMsg("Sorry That I Loved Your!");
			result.setDetail(e.toString());
		} catch (Exception e) {
			result.setStatus(false);
			result.setMsg("Sorry That I Loved Your!");
			result.setDetail(e.toString());
		}

		return result;
	}
}
