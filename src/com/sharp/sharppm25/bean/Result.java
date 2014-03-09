package com.sharp.sharppm25.bean;

public class Result {
	private AirQualityIndex index = new AirQualityIndex();
	private boolean status;
	private String msg;
	private String detail;
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public AirQualityIndex getIndex() {
		return index;
	}
	public void setIndex(AirQualityIndex index) {
		this.index = index;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
