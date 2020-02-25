package com.ao.fstracking.repo.business;

import java.util.List;

public class ResponseBusiness {
	private int result;
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
				"ResponseBusiness{" +
						"result = '" + result + '\'' +
						",info = '" + info + '\'' +
						"}";
	}
}