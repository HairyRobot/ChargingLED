package hk.ChargingLED.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public final class PlayStoreHelper {

	/**
	 * Trigger intent for view app in Play Store.
	 */
	public static void viewInPlayStore(Context context, String packageName) {
		try {
			Uri uri = Uri.parse("market://details?id=" + packageName);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		} catch (Exception e) {
			viewInPlayStoreWeb(context, packageName);
		}
	}

	/**
	 * Trigger intent for view app in Play Store website.
	 */
	public static void viewInPlayStoreWeb(Context context, String packageName) {
		try {
			Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + packageName);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		} catch (Exception ignored) {
		}
	}
}
