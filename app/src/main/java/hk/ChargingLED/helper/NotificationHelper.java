package hk.ChargingLED.helper;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import net.margaritov.preference.colorpicker.ColorPickerPreference;

public final class NotificationHelper {
//	private static final String TAG = NotificationHelper.class.getSimpleName();

	@TargetApi(Build.VERSION_CODES.O)
	public static void createFsNotificationChannel(final Context context,
												   String channelId,
												   CharSequence channelName, String channelDesc,
												   int importance) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
			// https://developer.android.com/training/notify-user/channels#java
			// Create the NotificationChannel, but only on API 26+ because
			// the NotificationChannel class is new and not in the support library
			NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
			channel.setDescription(channelDesc);
			channel.setSound(null, null);
			channel.enableLights(false);
			channel.enableVibration(false);
			// Register the channel with the system; you can't change the importance
			// or other notification behaviors after this
			NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);
		}
	}

	@TargetApi(Build.VERSION_CODES.O)
	public static void createNotificationChannel(final Context context,
												 String channelId,
												 CharSequence channelName, String channelDesc,
												 int importance) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
			NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
			channel.setDescription(channelDesc);
			channel.setSound(null, null);
			channel.enableLights(false);
			channel.enableVibration(false);
			NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);
		}
	}

	@TargetApi(Build.VERSION_CODES.O)
	public static void createLedNotificationChannel(final Context context,
													int argb,
													int importance) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
			String channelId = getChannelId(argb);
			NotificationChannel channel = new NotificationChannel(channelId, channelId, importance);
			channel.setDescription(channelId);
			channel.setSound(null, null);
			channel.enableLights(true);
			channel.setLightColor(argb);
			channel.enableVibration(false);
			NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);
		}
	}

	public static String getChannelId(int argb) {
		return ColorPickerPreference.convertToARGB(argb);
	}
}
