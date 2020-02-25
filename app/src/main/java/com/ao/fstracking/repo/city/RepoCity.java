package com.ao.fstracking.repo.city;

import java.util.List;

public class RepoCity {


	/**
	 * Result : 1
	 * info : [{"Id":100000,"CityName":"Amman","OverAllResult":1}]
	 */

	private int Result;
	private List<InfoBean> info;

	public int getResult() {
		return Result;
	}

	public void setResult(int Result) {
		this.Result = Result;
	}

	public List<InfoBean> getInfo() {
		return info;
	}

	public void setInfo(List<InfoBean> info) {
		this.info = info;
	}

	public static class InfoBean {
		/**
		 * Id : 100000
		 * CityName : Amman
		 * OverAllResult : 1
		 */

		private int Id;
		private String CityName;
		private int OverAllResult;

		public int getId() {
			return Id;
		}

		public void setId(int Id) {
			this.Id = Id;
		}

		public String getCityName() {
			return CityName;
		}

		public void setCityName(String CityName) {
			this.CityName = CityName;
		}

		public int getOverAllResult() {
			return OverAllResult;
		}

		public void setOverAllResult(int OverAllResult) {
			this.OverAllResult = OverAllResult;
		}
	}
}
