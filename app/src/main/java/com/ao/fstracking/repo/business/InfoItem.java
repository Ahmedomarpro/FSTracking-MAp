package com.ao.fstracking.repo.business;

public class InfoItem {
	private String shopTyep;
	private int overAllResult;
	private int id;

	public String getShopTyep() {
		return shopTyep;
	}

	public void setShopTyep(String shopTyep) {
		this.shopTyep = shopTyep;
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

	@Override
	public String toString() {
		return
				"InfoItem{" +
						"shopTyep = '" + shopTyep + '\'' +
						",overAllResult = '" + overAllResult + '\'' +
						",id = '" + id + '\'' +
						"}";
	}
}
