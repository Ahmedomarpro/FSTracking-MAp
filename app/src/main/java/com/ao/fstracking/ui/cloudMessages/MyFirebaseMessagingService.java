package com.ao.fstracking.ui.cloudMessages;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ao.fstracking.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
	//Test code
/*
	public static Integer  Notifications_ID = 0;
	String TAG ="MALIK";

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {

		generateNotifcation(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());



		if (remoteMessage.getNotification() != null) {
			Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
			generateNotifcation(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
		}


	}

	private void generateNotifcation(String body, String title) {
		Intent intent = new Intent(this, FirMessages.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		//Context context;
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 */
	/* Request code *//*
, intent,
				PendingIntent.FLAG_ONE_SHOT);
		Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
				.setSmallIcon(R.mipmap.ic_launcher)
				.setContentTitle(title)
				.setContentText(body)
				.setStyle(new NotificationCompat.BigTextStyle())

				.setAutoCancel(true)
				.setSound(sound)
				.setContentIntent(pendingIntent);
		//Context context;
		NotificationManager notificationManager = (NotificationManager) getSystemService
				(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(Notifications_ID,builder.build());

		if (Notifications_ID > 555555) {
		Notifications_ID = 0;
		}
		// Since android Oreo notification channel is needed.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel channel = new NotificationChannel(Notifications_ID.toString(),
					"Channel human readable title",
					NotificationManager.IMPORTANCE_DEFAULT);
			notificationManager.createNotificationChannel(channel);
		}



		}

	@Override
	public void onNewToken(String s) {
		super.onNewToken(s);
 		String DeviceToken = FirebaseInstanceId.getInstance().getToken();
		Log.e(TAG,DeviceToken);
		//sendRegistrationToServer(token);
	}
*/
	private NotificationManagerCompat notificationManager;

	@Override
	public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
		super.onMessageReceived(remoteMessage);
		notificationManager = NotificationManagerCompat.from(this);
		sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());

	}

	private void sendNotification(String title, String msg) {
		Intent resultIntent = new Intent(this, FirMessages.class);

		String channelId = getString(R.string.fcm_token);
		String channelName = "Message Notification";

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addNextIntentWithParentStack(resultIntent);

		PendingIntent pendingIntent = stackBuilder.getPendingIntent(99, PendingIntent.FLAG_UPDATE_CURRENT);

		Notification notification = new NotificationCompat.Builder(this, channelId)
				.setSmallIcon(R.mipmap.ic_launcher_round)
				.setContentTitle(title)
				.setContentText(msg)
				.setPriority(NotificationCompat.PRIORITY_HIGH)
				.setCategory(NotificationCompat.CATEGORY_MESSAGE)
				.setContentIntent(pendingIntent)
				.build();

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
			NotificationManager manager = getSystemService(NotificationManager.class);
			NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
			manager.createNotificationChannel(channel);
		}

		notificationManager.notify(0, notification);
	}

}


