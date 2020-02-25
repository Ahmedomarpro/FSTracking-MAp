package com.ao.fstracking.repo.gender;

public class InfoItem {
	private int overAllResult;
	private int id;
	private String gender;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return
				"InfoItem{" +
						"overAllResult = '" + overAllResult + '\'' +
						",id = '" + id + '\'' +
						",gender = '" + gender + '\'' +
						"}";
	}
}
