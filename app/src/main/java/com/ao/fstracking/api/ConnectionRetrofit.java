package com.ao.fstracking.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ConnectionRetrofit {
	private static final String BASE_URL = "http://blinkappservices.com/";
	private static Retrofit retrofit;

	public ConnectionRetrofit() {
		//Singleton pattern
	}

	public static Retrofit getInstance() {
		if (retrofit == null) {

			retrofit = new Retrofit.Builder()

					//.client(okHttpClient)
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();

		}
		return retrofit;
	}

	public static ApiALL gtApiALL() {
		return getInstance().create(ApiALL.class);
	}

}
