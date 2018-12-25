package hk.ChargingLED.helper;

import android.content.Context;
import android.widget.Toast;

public final class PopToast {

	public static void toast(final Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
