package com.ao.fstracking.repo.chartHome;

import com.google.gson.annotations.SerializedName;

public class InfoItem {

	@SerializedName("date")
	private String date;

	@SerializedName("OverAllResult")
	private int overAllResult;

	@SerializedName("id")
	private int id;

	@SerializedName("NumberofOrders")
	private int numberofOrders;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getOverAllResult() {
		return overAllResult;
	}

	public void setOverAllResult(int overAllResult) {
		this.overAllResult = overAllResult;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberofOrders() {
		return numberofOrders;
	}

	public void setNumberofOrders(int numberofOrders) {
		this.numberofOrders = numberofOrders;
	}

	@Override
	public String toString() {
		return
				"InfoItem{" +
						"date = '" + date + '\'' +
						",overAllResult = '" + overAllResult + '\'' +
						",id = '" + id + '\'' +
						",numberofOrders = '" + numberofOrders + '\'' +
						"}";
	}
}