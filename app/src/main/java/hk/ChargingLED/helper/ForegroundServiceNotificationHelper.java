package hk.ChargingLED.helper;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.os.Build;

public final class ForegroundServiceNotificationHelper {

	/**
	 * @param context The current context.
	 * @param channelId The channel ID.
	 * @param icon The notification icon.
	 * @param contentTitle The notification content title string.
	 * @param contentTextResId The notification content text resId.
	 * @return the Notification object.
	 */
	@TargetApi(Build.VERSION_CODES.O)
	public static Notification buildForegroundNotification(
			Context context,
			String channelId,
			int icon,
			String contentTitle,
			int contentTextResId) {

		return buildForegroundNotification(context, channelId,
				icon,
				contentTitle,
				context.getString(contentTextResId));
	}

	/**
	 * @param context The current context.
	 * @param channelId The channel ID.
	 * @param icon The notification icon.
	 * @param contentTitle The notification content title string.
	 * @param contentText The notification content text string.
	 * @return the Notification object.
	 */
	@TargetApi(Build.VERSION_CODES.O)
	public static Notification buildForegroundNotification(
			Context context,
			String channelId,
			int icon,
			String contentTitle,
			String contentText) {

		Notification.Builder builder = new Notification.Builder(context, channelId);
		builder.setContentTitle(contentTitle);
		builder.setContentText(contentText);
		builder.setSmallIcon(icon);
		builder.setOngoing(true);
		builder.setAutoCancel(true);
		builder.setShowWhen(false);
		builder.setCategory(Notification.CATEGORY_STATUS);
		builder.setVisibility(Notification.VISIBILITY_SECRET);

		return (builder.build());
	}
}
