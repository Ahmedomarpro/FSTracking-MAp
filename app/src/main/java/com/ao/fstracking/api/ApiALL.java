package com.ao.fstracking.api;

import com.ao.fstracking.repo.business.ResponseBusiness;
import com.ao.fstracking.repo.chartHome.ResponseChar;
import com.ao.fstracking.repo.city.RepoCity;
import com.ao.fstracking.repo.country.RepoCountry;
import com.ao.fstracking.repo.gender.ResponseGender;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiALL {

	//⦁	http://blinkappservices.com/DriverService.asmx/GetCountry
	@GET("DriverService.asmx/GetCountry")
	Call<RepoCountry> RESPONSE_COUNTRY_CALL();


	// http://blinkappservices.com/DriverService.asmx/GetCity?CountryID=100
	@GET("DriverService.asmx/GetCity")
	Call<RepoCity> RESPONSE_CiTY_CALL(@Query("CountryID") String countryID);


	//http://blinkappservices.com/DriverService.asmx/GetGender
	@GET("DriverService.asmx/GetGender")
	Call<List<ResponseGender>> RESPONSE_Gender_CALL();

	//********************------URL---------**********************************
	// http://blinkappservices.com/MerchantService.asmx/GetShopType?BusinessTypeID=1

	@GET("MerchantService.asmx/GetShopType")
	Call<List<ResponseBusiness>> RESPONSE_Business_CALL(@Query("BusinessTypeID") String BusinessTypeID);

	// http://blinkappservices.com/MerchantService.asmx/Merchant_ChartHome_Test?VerificationCode=hc6yy233few3eqaa&MerchantsID=60007
	@GET("MerchantService.asmx/Merchant_ChartHome_Test")
	Call<List<ResponseChar>> RESPONSE_Char_CALL(
			@Query("VerificationCode") String VerificationCode, @Query("MerchantsID") String MerchantsID);


}
