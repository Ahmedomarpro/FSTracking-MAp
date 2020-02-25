package com.ao.fstracking.repo.country;

import java.util.List;

public class RepoCountry {

	/**
	 * Result : 1
	 * info : [{"Id":100,"CountryName":"Jordan","OverAllResult":1}]
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
		 * Id : 100
		 * CountryName : Jordan
		 * OverAllResult : 1
		 */

		private int Id;
		private String CountryName;
		private int OverAllResult;

		public int getId() {
			return Id;
		}

		public void setId(int Id) {
			this.Id = Id;
		}

		public String getCountryName() {
			return CountryName;
		}

		public void setCountryName(String CountryName) {
			this.CountryName = CountryName;
		}

		public int getOverAllResult() {
			return OverAllResult;
		}

		public void setOverAllResult(int OverAllResult) {
			this.OverAllResult = OverAllResult;
		}
	}
}
