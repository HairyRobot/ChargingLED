package hk.ChargingLED.helper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import hk.ChargingLED.Constant;
import hk.ChargingLED.MainActivity;
import hk.ChargingLED.R;
import hk.ChargingLED.util.NotificationUtil;

public final class NotifyLedHelper {
	private static final String TAG = NotifyLedHelper.class.getSimpleName();

	private NotifyLedHelper() {
	}

	public static void showLed(final Context context, int chargeLevel,
							   int ledColor, int ledOnMs, int ledOffMs) {
		String ticker;
		String title;
		String text;
		String channelId = Constant.NOTIFICATION_CHANNEL_ID.UNCATEGORIZED;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
			channelId = NotificationHelper.getChannelId(ledColor);
			NotificationHelper.createLedNotificationChannel(context,
					ledColor,
					NotificationManager.IMPORTANCE_LOW);
		}

		Intent intent = new Intent(context, MainActivity.class);
		PendingIntent pi;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_NO_CREATE);
		} else {
			// Below ICS, a PendingIntent is required
			// http://stackoverflow.com/questions/20032249/is-setcontentintentpendingintent-required-in-notificationcompat-builder
			pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		}

		if (PreferenceHelper.getIsChargingNotification(context)) {
			ticker = "" + chargeLevel + "%";
		} else {
			ticker = "";
		}
		if (PreferenceHelper.getIsChargingSound(context)) {
			if (chargeLevel >= PreferenceHelper.getBatteryLevel(context)) {
				ticker = "" + chargeLevel + "%";
			}
		}

		if (chargeLevel >= 100) {
			title = context.getString(R.string.charging_completed);
			text = "100%";
		} else {
			title = context.getString(R.string.charging);
			text = "" + chargeLevel + "%";
		}

		NotifyChargingHelper.clearStatusNotification(context);
		NotifySoundHelper.clearSoundNotification(context);
		NotifyLedHelper.clearLed(context);
		Log.d(TAG, "LED notification: " + text);
		NotificationUtil.showNotificationWithLed(context, pi,
				Constant.NOTIFICATION_ID.LED_NOTIFICATION_ID,
				R.drawable.ic_launcher, null,
				0L, true,
				ticker,
				title, text,
				true,
				false, 0,
				false,
				false, null,
				false, 0,
				channelId,
				true, ledColor, ledOnMs, ledOffMs);
	}

	public static void clearLed(final Context context) {
		NotificationUtil.cancelNotification(context, Constant.NOTIFICATION_ID.LED_NOTIFICATION_ID);
	}
}
