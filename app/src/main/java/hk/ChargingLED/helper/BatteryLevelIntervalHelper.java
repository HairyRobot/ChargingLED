package hk.ChargingLED.helper;

import android.content.Context;

public final class BatteryLevelIntervalHelper {

	public static void checkValidInterval(Context context) {
		if (PreferenceHelper.getBatteryLevelInterval1(context) < PreferenceHelper.getBatteryLevelInterval2(context)) {
			resetDefaultInterval(context);
			return;
		}
		if (PreferenceHelper.getBatteryLevelInterval2(context) < PreferenceHelper.getBatteryLevelInterval3(context)) {
			resetDefaultInterval(context);
			return;
		}
		if (PreferenceHelper.getBatteryLevelInterval3(context) < PreferenceHelper.getBatteryLevelInterval4(context)) {
			resetDefaultInterval(context);
			return;
		}
		if (PreferenceHelper.getBatteryLevelInterval4(context) < PreferenceHelper.getBatteryLevelInterval5(context)) {
			resetDefaultInterval(context);
			return;
		}
		if (PreferenceHelper.getBatteryLevelInterval5(context) < PreferenceHelper.getBatteryLevelInterval6(context)) {
			resetDefaultInterval(context);
			return;
		}
		if (PreferenceHelper.getBatteryLevelInterval6(context) < PreferenceHelper.getBatteryLevelInterval7(context)) {
			resetDefaultInterval(context);
			return;
		}
	}

	public static void resetDefaultInterval(Context context) {
		PreferenceHelper.setBatteryLevelInterval1(context, PreferenceHelper.PREF_BATTERY_LEVEL_INTERVAL_1_DEFAULT);
		PreferenceHelper.setBatteryLevelInterval2(context, PreferenceHelper.PREF_BATTERY_LEVEL_INTERVAL_2_DEFAULT);
		PreferenceHelper.setBatteryLevelInterval3(context, PreferenceHelper.PREF_BATTERY_LEVEL_INTERVAL_3_DEFAULT);
		PreferenceHelper.setBatteryLevelInterval4(context, PreferenceHelper.PREF_BATTERY_LEVEL_INTERVAL_4_DEFAULT);
		PreferenceHelper.setBatteryLevelInterval5(context, PreferenceHelper.PREF_BATTERY_LEVEL_INTERVAL_5_DEFAULT);
		PreferenceHelper.setBatteryLevelInterval6(context, PreferenceHelper.PREF_BATTERY_LEVEL_INTERVAL_6_DEFAULT);
	}
}
