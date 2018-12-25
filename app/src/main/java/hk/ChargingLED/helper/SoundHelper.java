package hk.ChargingLED.helper;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.TextUtils;

import hk.ChargingLED.R;

public final class SoundHelper {

	public static void onPowerConnectedDebug(Context context) {
		PopToast.toast(context, context.getString(R.string.power_connected));
	}

	public static void onPowerDisconnectedDebug(Context context) {
		PopToast.toast(context, context.getString(R.string.power_disconnected));
	}

	public static void onPowerConnectedSound(Context context) {
		String pluginRingtone = PreferenceHelper.getPluginSound(context);
		if (!TextUtils.isEmpty(pluginRingtone)) {
			try {
				Uri ringtoneUri = Uri.parse(pluginRingtone);
				Ringtone ringtone = RingtoneManager.getRingtone(context, ringtoneUri);
				if (ringtone != null) {
					if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
						ringtone.setAudioAttributes(new AudioAttributes.Builder()
								.setLegacyStreamType(AudioManager.STREAM_NOTIFICATION)
								.build()
						);
					} else {
						//noinspection deprecation
						ringtone.setStreamType(AudioManager.STREAM_NOTIFICATION);
					}
					ringtone.play();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void onPowerDisconnectedSound(Context context) {
		String unplugRingtone = PreferenceHelper.getUnplugSound(context);
		if (!TextUtils.isEmpty(unplugRingtone)) {
			try {
				Uri ringtoneUri = Uri.parse(unplugRingtone);
				Ringtone ringtone = RingtoneManager.getRingtone(context, ringtoneUri);
				if (ringtone != null) {
					if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
						ringtone.setAudioAttributes(new AudioAttributes.Builder()
								.setLegacyStreamType(AudioManager.STREAM_NOTIFICATION)
								.build()
						);
					} else {
						//noinspection deprecation
						ringtone.setStreamType(AudioManager.STREAM_NOTIFICATION);
					}
					ringtone.play();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void playNotificationRingtone(Context context, String notificationRingtone) {
		if (!TextUtils.isEmpty(notificationRingtone)) {
			try {
				Uri ringtoneUri = Uri.parse(notificationRingtone);
				Ringtone ringtone = RingtoneManager.getRingtone(context, ringtoneUri);
				if (ringtone != null) {
					if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
						ringtone.setAudioAttributes(new AudioAttributes.Builder()
								.setLegacyStreamType(AudioManager.STREAM_NOTIFICATION)
								.build()
						);
					} else {
						//noinspection deprecation
						ringtone.setStreamType(AudioManager.STREAM_NOTIFICATION);
					}
					ringtone.play();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
