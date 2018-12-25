package hk.ChargingLED.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;

import hk.ChargingLED.helper.SoundHelper;

public final class NotificationUtil {

	/**
	 * Show notification for specified PendingIntent.
	 *
	 * @param context The current context.
	 * @param pi The PendingIntent.
	 * @param id The notification id.
	 * @param icon The notification icon.
	 * @param largeIcon The notification large icon.
	 * @param when The time (when) the notification.
	 * @param showWhen Flag to signal show when the notification is send.
	 * @param ticker The notification ticker. Can be empty.
	 * @param title The notification title.
	 * @param text The notification text.
	 * @param onGoing The notification is on going flag.
	 * @param autoCancel The notification can be auto cancel flag.
	 * @param notificationFlags The additional notification flags. (eg. Notification.FLAG_ONLY_ALERT_ONCE)
	 * @param withVibrate Notification with default vibrate flag.
	 * @param withRingtone Notification with default notification ringtone flag.
	 * @param ringtone The notification ringtone.
	 * @param withPriority Notification priority flag.
	 * @param priority The notification priority. (eg. Notification.PRIORITY_DEFAULT)
	 * @param channelId The notification channel id (API-26).
	 */
	public static void showNotification(Context context, PendingIntent pi, int id,
										int icon, Bitmap largeIcon,
										long when, boolean showWhen,
										String ticker,
										String title, String text,
										boolean onGoing,
										boolean autoCancel, int notificationFlags,
										boolean withVibrate,
										boolean withRingtone, String ringtone,
										boolean withPriority, int priority,
										String channelId) {
		showNotificationWithLed(context, pi, id,
				icon, largeIcon,
				when, showWhen,
				ticker,
				title, text,
				onGoing,
				autoCancel, notificationFlags,
				withVibrate,
				withRingtone, ringtone,
				withPriority, priority,
				channelId,
				false, 0, 0, 0);
	}

	/**
	 * Show notification with LED for specified PendingIntent.
	 *
	 * @param context The current context.
	 * @param pi The PendingIntent.
	 * @param id The notification id.
	 * @param icon The notification icon.
	 * @param largeIcon The notification large icon.
	 * @param when The time (when) the notification.
	 * @param showWhen Flag to signal show when the notification is send.
	 * @param ticker The notification ticker. Can be empty.
	 * @param title The notification title.
	 * @param text The notification text.
	 * @param onGoing The notification is on going flag.
	 * @param autoCancel The notification can be auto cancel flag.
	 * @param notificationFlags The additional notification flags. (eg. Notification.FLAG_NO_CLEAR)
	 * @param withVibrate Notification with default vibrate flag.
	 * @param withRingtone Notification with default notification ringtone flag.
	 * @param ringtone The notification ringtone.
	 * @param withPriority Notification priority flag.
	 * @param priority The notification priority. (eg. Notification.PRIORITY_DEFAULT)
	 * @param withLed Notification with LED flag.
	 * @param channelId The notification channel id (API-26).
	 * @param ledColor The LED colour.
	 * @param ledOnMs The LED on cycle in milliseconds.
	 * @param ledOffMs The LED off cycle in milliseconds.
	 */
	public static void showNotificationWithLed(Context context, PendingIntent pi, int id,
											   int icon, Bitmap largeIcon,
											   long when, boolean showWhen,
											   String ticker,
											   String title, String text,
											   boolean onGoing,
											   boolean autoCancel, int notificationFlags,
											   boolean withVibrate,
											   boolean withRingtone, String ringtone,
											   boolean withPriority, int priority,
											   String channelId,
											   boolean withLed, int ledColor, int ledOnMs, int ledOffMs) {
		PendingIntent pendingIntent = pi;
		if (pi == null) {
			pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent("DoNothing"), 0);
		}

		int defaults = 0;
		Notification notification;
		NotificationManagerCompat nm = NotificationManagerCompat.from(context);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);

		builder.setContentIntent(pendingIntent);
		builder.setSmallIcon(icon);
		if (largeIcon != null) {
			builder.setLargeIcon(largeIcon);
		}
		if (when >= 0) {
			if (when > 0) {
				builder.setWhen(when);
			} else {
				builder.setWhen(System.currentTimeMillis());
			}
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {    // API-17
			builder.setShowWhen(showWhen);
		}
		builder.setOngoing(onGoing);
		builder.setAutoCancel(autoCancel);
		if (!TextUtils.isEmpty(title)) {
			builder.setContentTitle(title);
		}
		if (!TextUtils.isEmpty(text)) {
			builder.setContentText(text);
		}
		if (!TextUtils.isEmpty(ticker)) {
			builder.setTicker(ticker);
		}
//		if (withRingtone) {
//			// empty is silence.
//			if (!TextUtils.isEmpty(ringtone)) {
//				try {
//					Uri ringtoneUri = Uri.parse(ringtone);
//					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {    // API-21
//						builder.setSound(ringtoneUri, new AudioAttributes.Builder()
//								.setLegacyStreamType(AudioManager.STREAM_NOTIFICATION)
//								.build()
//						);
//					} else {
//						//noinspection deprecation
//						builder.setSound(ringtoneUri, AudioManager.STREAM_NOTIFICATION);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
		builder.setSound(null);
		if (withVibrate) {
			defaults |= NotificationCompat.DEFAULT_VIBRATE;
//			builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
		} else {
			builder.setVibrate(null);
		}
		if (withPriority) {
			switch (priority) {
				case NotificationCompat.PRIORITY_HIGH:
					builder.setPriority(NotificationCompat.PRIORITY_HIGH);
					break;
				case NotificationCompat.PRIORITY_LOW:
					builder.setPriority(NotificationCompat.PRIORITY_LOW);
					break;
				case NotificationCompat.PRIORITY_MAX:
					builder.setPriority(NotificationCompat.PRIORITY_MAX);
					break;
				case NotificationCompat.PRIORITY_MIN:
					builder.setPriority(NotificationCompat.PRIORITY_MIN);
					break;
				default:
					builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
					break;
			}
		} else {
			builder.setPriority(NotificationCompat.PRIORITY_LOW);
		}
		if (withLed) {
			builder.setLights(ledColor, ledOnMs, ledOffMs);
		}
		builder.setDefaults(defaults);
		notification = builder.build();
		if (notificationFlags != 0) {
			notification.flags |= notificationFlags;
		}
		nm.notify(id, notification);
		if (withRingtone) {
			if (!TextUtils.isEmpty(ringtone)) {
				SoundHelper.playNotificationRingtone(context, ringtone);
			}
		}
	}

	/**
	 * Cancel the notification with specified id.
	 *
	 * @param context The current context.
	 * @param id The notification id.
	 */
	public static void cancelNotification(Context context, int id) {
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.cancel(id);
	}
}
