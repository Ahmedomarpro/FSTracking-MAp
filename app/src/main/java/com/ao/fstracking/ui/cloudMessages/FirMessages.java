package com.ao.fstracking.ui.cloudMessages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.ao.fstracking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class FirMessages extends AppCompatActivity {
	private static final String TAG = "FirMessages";

	private TextView mTextField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fir_messages);

		mTextField = findViewById(R.id.textTime);


		new CountDownTimer(30000, 1000) {

			public void onTick(long millisUntilFinished) {
				mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
				//here you can have your logic to set text to edittext
				notifications();
			}

			public void onFinish() {
				mTextField.setText("done!!");
				//MyFirebaseMessagingService messagingService = new MyFirebaseMessagingService() ;
			}

		}.start();
	}

	private void notifications() {
		FirebaseInstanceId.getInstance().getInstanceId()
				.addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
					@Override
					public void onComplete(@NonNull Task<InstanceIdResult> task) {
						if (!task.isSuccessful()) {
//To do//
							return;
						}/*
						 * Really depends on device you were trying it on.
						 * ive been pulling my hair out for weeks trying to figure it out.. thought it was my code.. so i sent the firebase team a email to ask. They confirmed that there was no fix for this at the present time.. hope this helps*/

// Get the Instance ID token//
						String token = task.getResult().getToken();
						String msg = getString(R.string.fcm_token, token);
						Log.d(TAG, msg);

					}
				});

	}

}
