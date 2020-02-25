package com.ao.fstracking.ui.charTest;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;
import com.ao.fstracking.R;
import com.ao.fstracking.api.ConnectionRetrofit;
import com.ao.fstracking.repo.chartHome.ResponseChar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharHomeTest extends AppCompatActivity {
	public static final String VerificationCode = "hc6yy233few3eqaa";
	public static final String MerchantsID = "60007";
	protected TextView textChar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_char_home_test);
		initView();
		ConnectionRetrofit.gtApiALL().RESPONSE_Char_CALL(VerificationCode, MerchantsID)
				.enqueue(new Callback<List<ResponseChar>>() {
					@Override
					public void onResponse(Call<List<ResponseChar>> call, Response<List<ResponseChar>> response) {
						Toast.makeText(CharHomeTest.this,
								"dd" + response.body().get(0).getInfo(), Toast.LENGTH_SHORT).show();
						Log.e("u", "" + response.body());
					}

					@Override
					public void onFailure(Call<List<ResponseChar>> call, Throwable t) {
						textChar.setText(t.getLocalizedMessage());

					}
				});

		Pie pie = AnyChart.pie();

		List<DataEntry> data = new ArrayList<>();
		data.add(new ValueDataEntry("Jordan3", 10000));
		data.add(new ValueDataEntry("Jordan2", 12000));
		data.add(new ValueDataEntry("Jordan4", 18000));
		data.add(new ValueDataEntry("Jordan3", 10000));
		data.add(new ValueDataEntry("Jordan2", 12000));
		data.add(new ValueDataEntry("Jordan4", 18000));
		data.add(new ValueDataEntry("Jordan3", 10000));
		data.add(new ValueDataEntry("Jordan2", 12000));
		data.add(new ValueDataEntry("Jordan4", 18000));

		AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
		anyChartView.setChart(pie);
	}

	private void initView() {
		textChar = findViewById(R.id.textChar);
	}
}
