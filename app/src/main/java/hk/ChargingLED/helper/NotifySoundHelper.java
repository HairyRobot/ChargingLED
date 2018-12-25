package hk.ChargingLED.helper;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import hk.ChargingLED.Constant;
import hk.ChargingLED.MainActivity;
import hk.ChargingLED.R;
import hk.ChargingLED.service.AlarmService;
import hk.ChargingLED.util.NotificationUtil;

public final class NotifySoundHelper {
	private static final String TAG = NotifySoundHelper.class.getSimpleName();

	private NotifySoundHelper() {
	}

	public static void NotifySoundChargingStatus(final Context context, int chargeLevel) {
		String ticker;
		String title;
		String text;
		String ringtone;
		boolean withRingtone = true;

		Intent intent = new Intent(context, MainActivity.class);
		PendingIntent pi;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_NO_CREATE);
		} else {
			// Below ICS, a PendingIntent is required
			// http://stackoverflow.com/questions/20032249/is-setcontentintentpendingintent-required-in-notificationcompat-builder
			pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		}

		if (chargeLevel >= PreferenceHelper.getBatteryLevel(context)) {
			ticker = "" + chargeLevel + "%";
		} else {
			ticker = "";
		}

		if (chargeLevel >= 100) {
			title = context.getString(R.string.charging_completed);
			text = "100%";
		} else {
			title = context.getString(R.string.charging);
			text = "" + chargeLevel + "%";
		}

		if (chargeLevel >= PreferenceHelper.getBatteryLevel(context)) {
			ringtone = getSoundUri(context).toString();
			if (SilentPeriodHelper.isInSilentPeriod(context, System.currentTimeMillis())) {
				withRingtone = false;
				ringtone = "";
			}
		} else {
			withRingtone = false;
			ringtone = "";
		}

		if (AlarmService.isAlarmOn()) {
			NotifyChargingHelper.NotifyChargingStatus(context, chargeLevel);
			return;
		}

		NotifyChargingHelper.clearStatusNotification(context);
		NotifySoundHelper.clearSoundNotification(context);
		NotifyLedHelper.clearLed(context);
		Log.d(TAG, "Sound notification: " + text);
		NotificationUtil.showNotification(context, pi,
				Constant.NOTIFICATION_ID.SOUND_NOTIFICATION_ID,
				R.drawable.ic_launcher, null,
				0L, true,
				ticker,
				title, text,
				true,
				false, 0,
				false,
				withRingtone, ringtone,
				false, 0,
				Constant.NOTIFICATION_CHANNEL_ID.UNCATEGORIZED);

		AlarmService.scheduleAlarm(context);
	}

	public static void clearSoundNotification(final Context context) {
		NotificationUtil.cancelNotification(context, Constant.NOTIFICATION_ID.SOUND_NOTIFICATION_ID);
	}

	private static Uri getSoundUri(final Context context) {
		Uri ringtoneUri = Uri.parse(PreferenceHelper.getBatteryLevelReachedSound(context));
		Ringtone ringtone = RingtoneManager.getRingtone(context, ringtoneUri);
		if (ringtone != null) {
			return ringtoneUri;
		}
		return Settings.System.DEFAULT_NOTIFICATION_URI;
	}
}
