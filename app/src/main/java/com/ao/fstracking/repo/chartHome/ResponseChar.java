package com.ao.fstracking.repo.chartHome;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResponseChar {

	@SerializedName("Result")
	private int result;

	@SerializedName("info")
	private List<InfoItem> info;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public List<InfoItem> getInfo() {
		return info;
	}

	public void setInfo(List<InfoItem> info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return
				"ResponseChar{" +
						"result = '" + result + '\'' +
						",info = '" + info + '\'' +
						"}";
	}
}