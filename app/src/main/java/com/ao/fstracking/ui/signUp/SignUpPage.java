package com.ao.fstracking.ui.signUp;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.ao.fstracking.R;
import com.ao.fstracking.api.ConnectionRetrofit;
import com.ao.fstracking.repo.business.ResponseBusiness;
import com.ao.fstracking.repo.city.RepoCity;
import com.ao.fstracking.repo.country.RepoCountry;
import com.ao.fstracking.repo.gender.ResponseGender;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPage extends AppCompatActivity {

	protected TextView textGender;
	protected TextView textBusiness;
	private TextView textID;
	private TextView textCity;
	//List<InfoItem> arrayList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_sign_up_page);
		initView();
		signUpAPI();

	}


	private void signUpAPI() {
		/*ConnectionRetrofit.gtApiALL().RESPONSE_COUNTRY_CALL().enqueue(new Callback<InfoItem>() {
			@SuppressLint("SetTextI18n")
			@Override
			public void onResponse(Call<InfoItem> call, Response<InfoItem> response) {
				if (response.body() != null && response.isSuccessful()) {
					Toast.makeText(SignUpPage.this,
							"new" + response.body(), Toast.LENGTH_SHORT).show();


  					textID.setText(response.body().toString());

				} else {
					Toast.makeText(SignUpPage.this, "Error" + response, Toast.LENGTH_SHORT).show();
					Log.e("ee", "" + response);
				}


			}

			@Override
			public void onFailure(Call<InfoItem> call, Throwable t) {
				Toast.makeText(SignUpPage.this, "Error" + t, Toast.LENGTH_SHORT).show();
				Log.e("rrr", String.valueOf(t));

			}
		});*/          //test code a
		ConnectionRetrofit.gtApiALL().RESPONSE_COUNTRY_CALL().enqueue(new Callback<RepoCountry>() {
			@Override
			public void onResponse(Call<RepoCountry> call, Response<RepoCountry> response) {
				textID.setText(response.body().getInfo().iterator().next().getCountryName());
			}

			@Override
			public void onFailure(Call<RepoCountry> call, Throwable t) {
				textID.setText(t.getLocalizedMessage());

			}
		});

		ConnectionRetrofit.gtApiALL().RESPONSE_CiTY_CALL("100").enqueue(new Callback<RepoCity>() {
			@Override
			public void onResponse(Call<RepoCity> call, Response<RepoCity> response) {

				//textCity.setText(response.body().getInfo().get(1).getCityName());
				textCity.setText((CharSequence) response.body());
				//	Log.e("getCity","" +response.body().getInfo().get(0).getCityName());

			}

			@Override
			public void onFailure(Call<RepoCity> call, Throwable t) {
				textCity.setText(t.getLocalizedMessage());

			}
		});


		ConnectionRetrofit.gtApiALL().RESPONSE_Gender_CALL().enqueue(new Callback<ResponseGender>() {
			@RequiresApi(api = Build.VERSION_CODES.N)
			@Override
			public void onResponse(Call<ResponseGender> call, Response<ResponseGender> response) {
				Toast.makeText(SignUpPage.this, "" + response.body(), Toast.LENGTH_SHORT).show();
				//textGender.setText(response.body().getInfo().iterator().next().getGender());
				textGender.setText(response.body().getInfo().get(0).getGender());

			}

			@Override
			public void onFailure(Call<ResponseGender> call, Throwable t) {
				textGender.setText(t.getLocalizedMessage());

			}
		});

		ConnectionRetrofit.gtApiALL().RESPONSE_Business_CALL("1").enqueue(new Callback<List<ResponseBusiness>>() {
			@Override
			public void onResponse(Call<List<ResponseBusiness>> call, Response<List<ResponseBusiness>> response) {
				textBusiness.setText("" + response.body().iterator().next().getInfo());
				textBusiness.setText((CharSequence) response.body().get(0).getInfo());

			}

			@Override
			public void onFailure(Call<List<ResponseBusiness>> call, Throwable t) {
				textBusiness.setText(t.getLocalizedMessage());

			}
		});
	}


	private void initView() {
		textID = findViewById(R.id.textID);
		textCity = findViewById(R.id.textCity);
		textGender = (TextView) findViewById(R.id.textGender);
		textBusiness = (TextView) findViewById(R.id.textBusiness);
	}


	/*
	public void showResponse(String response) {
    if(mResponseTv.getVisibility() == View.GONE) {
        mResponseTv.setVisibility(View.VISIBLE);
    }
    mResponseTv.setText(response);
}
	* */
}
