package hk.ChargingLED.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import hk.ChargingLED.Constant;

public final class PreferenceHelper {

	private static final String PREF_ACCEPTED_DISCLAIMER = "acceptedDisclaimer";
	private static final boolean AD_DEFAULT = false;

	public static final String PREF_MODE = "mode";
	public static final String PREF_VERSION = "version";

	public static final String PREF_ENABLED_MONITOR_CHARGING = "enabledMonitorCharging";
	private static final boolean PREF_ENABLED_MONITOR_CHARGING_DEFAULT = false;

	public static final String PREF_ENABLED_CHARGING_LED = "enabledChargingLed";
	private static final boolean PREF_ENABLED_CHARGING_LED_DEFAULT = false;

	public static final String PREF_BATTERY_LEVEL_INTERVAL_1 = "batteryLevelInterval1";
	static final int PREF_BATTERY_LEVEL_INTERVAL_1_DEFAULT = 100;
	public static final String PREF_BATTERY_LEVEL_INTERVAL_2 = "batteryLevelInterval2";
	static final int PREF_BATTERY_LEVEL_INTERVAL_2_DEFAULT = 95;
	public static final String PREF_BATTERY_LEVEL_INTERVAL_3 = "batteryLevelInterval3";
	static final int PREF_BATTERY_LEVEL_INTERVAL_3_DEFAULT = 90;
	public static final String PREF_BATTERY_LEVEL_INTERVAL_4 = "batteryLevelInterval4";
	static final int PREF_BATTERY_LEVEL_INTERVAL_4_DEFAULT = 75;
	public static final String PREF_BATTERY_LEVEL_INTERVAL_5 = "batteryLevelInterval5";
	static final int PREF_BATTERY_LEVEL_INTERVAL_5_DEFAULT = 50;
	public static final String PREF_BATTERY_LEVEL_INTERVAL_6 = "batteryLevelInterval6";
	static final int PREF_BATTERY_LEVEL_INTERVAL_6_DEFAULT = 25;
	public static final String PREF_BATTERY_LEVEL_INTERVAL_7 = "batteryLevelInterval7";
	private static final int PREF_BATTERY_LEVEL_INTERVAL_7_DEFAULT = 0;

	public static final String PREF_BATTERY_LEVEL_RANGE_1 = "batteryLevelRange1";
	public static final int PREF_BATTERY_LEVEL_RANGE_1_DEFAULT = Constant.ARGB_GREEN;
	public static final String PREF_BATTERY_LEVEL_RANGE_2 = "batteryLevelRange2";
	public static final int PREF_BATTERY_LEVEL_RANGE_2_DEFAULT = Constant.ARGB_GREEN;
	public static final String PREF_BATTERY_LEVEL_RANGE_3 = "batteryLevelRange3";
	public static final int PREF_BATTERY_LEVEL_RANGE_3_DEFAULT = Constant.ARGB_BLUE;
	public static final String PREF_BATTERY_LEVEL_RANGE_4 = "batteryLevelRange4";
	public static final int PREF_BATTERY_LEVEL_RANGE_4_DEFAULT = Constant.ARGB_WHITE;
	public static final String PREF_BATTERY_LEVEL_RANGE_5 = "batteryLevelRange5";
	public static final int PREF_BATTERY_LEVEL_RANGE_5_DEFAULT = Constant.ARGB_YELLOW;
	public static final String PREF_BATTERY_LEVEL_RANGE_6 = "batteryLevelRange6";
	public static final int PREF_BATTERY_LEVEL_RANGE_6_DEFAULT = Constant.ARGB_PURPLE;
	public static final String PREF_BATTERY_LEVEL_RANGE_7 = "batteryLevelRange7";
	public static final int PREF_BATTERY_LEVEL_RANGE_7_DEFAULT = Constant.ARGB_RED;

	public static final String PREF_ENABLED_CHARGING_NOTIFICATION = "enabledChargingNotification";
	private static final boolean PREF_ENABLED_CHARGING_NOTIFICATION_DEFAULT = false;

	public static final String PREF_ENABLED_CHARGING_SOUND = "enabledChargingSound";
	private static final boolean PREF_ENABLED_CHARGING_SOUND_DEFAULT = false;

	public static final String PREF_PLUGIN_SOUND = "pluginSound";
	private static final String PREF_PLUGIN_SOUND_DEFAULT = "";
	public static final String PREF_UNPLUG_SOUND = "unplugSound";
	private static final String PREF_UNPLUG_SOUND_DEFAULT = "";
	public static final String PREF_ENABLED_MONITOR_BATTERY_LEVEL = "enabledMonitorBatteryLevel";
	private static final boolean PREF_ENABLED_MONITOR_BATTERY_LEVEL_DEFAULT = true;
	public static final String PREF_BATTERY_LEVEL = "batteryLevel";
	private static final int PREF_BATTERY_LEVEL_DEFAULT = 100;
	public static final String PREF_BATTERY_LEVEL_REACHED_SOUND = "batteryLevelReachedSound";
	private static final String PREF_BATTERY_LEVEL_REACHED_SOUND_DEFAULT = "content://settings/system/notification_sound";
	private static final String PREF_ENABLE_SILENT_PERIOD = "enableSilentPeriod";
	private static final boolean PREF_ENABLE_SILENT_PERIOD_DEFAULT = false;
	public static final String PREF_SILENT_PERIOD = "silentPeriod";
	private static final String PREF_SP_START_HOUR = "silentPeriodStartHour";
	private static final int PREF_SP_START_HOUR_DEFAULT = 0;
	private static final String PREF_SP_START_MINUTE = "silentPeriodStartMinute";
	private static final int PREF_SP_START_MINUTE_DEFAULT = 0;
	private static final String PREF_SP_END_HOUR = "silentPeriodEndHour";
	private static final int PREF_SP_END_HOUR_DEFAULT = 8;
	private static final String PREF_SP_END_MINUTE = "silentPeriodEndMinute";
	private static final int PREF_SP_END_MINUTE_DEFAULT = 0;

	public static final String PREF_ENABLED_DEBUG_MODE = "enabledDebugMode";
	private static final boolean PREF_ENABLED_DEBUG_MODE_DEFAULT = false;

	public static final String PREF_ENABLED_BLINKING_1 = "enabledBlinking1";
	public static final boolean PREF_ENABLED_BLINKING_1_DEFAULT = false;

	public static final String PREF_BLINK_ON_1 = "blinkOn1";
	private static final String PREF_BLINK_ON_1_500 = "500";
	public static final String PREF_BLINK_ON_1_DEFAULT = PREF_BLINK_ON_1_500;

	public static final String PREF_BLINK_OFF_1 = "blinkOff1";
	private static final String PREF_BLINK_OFF_1_1000 = "1000";
	public static final String PREF_BLINK_OFF_1_DEFAULT = PREF_BLINK_OFF_1_1000;

	public static final String PREF_ENABLED_BLINKING_2 = "enabledBlinking2";
	public static final boolean PREF_ENABLED_BLINKING_2_DEFAULT = true;

	public static final String PREF_BLINK_ON_2 = "blinkOn2";
	private static final String PREF_BLINK_ON_2_500 = "500";
	public static final String PREF_BLINK_ON_2_DEFAULT = PREF_BLINK_ON_2_500;

	public static final String PREF_BLINK_OFF_2 = "blinkOff2";
	private static final String PREF_BLINK_OFF_2_1000 = "1000";
	public static final String PREF_BLINK_OFF_2_DEFAULT = PREF_BLINK_OFF_2_1000;

	private static final String PREF_IGNORE_BATTERY_OPTIMIZATION_DONT_SHOW_AGAIN = "ignoreBatteryOptimizationDontShowAgain";

	/**
	 * Get is monitor charging enabled flag
	 */
	public static boolean getIsMonitorCharging(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(PREF_ENABLED_MONITOR_CHARGING, PREF_ENABLED_MONITOR_CHARGING_DEFAULT);
	}

	/**
	 * Set is monitor charging enabled flag
	 */
	public static void setIsMonitorCharging(Context context, boolean flag) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean(PREF_ENABLED_MONITOR_CHARGING, flag);
		editor.apply();
	}

	/**
	 * Get is charging LED enabled flag
	 */
	public static boolean getIsChargingLed(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(PREF_ENABLED_CHARGING_LED, PREF_ENABLED_CHARGING_LED_DEFAULT);
	}

	/**
	 * Set is charging LED enabled flag
	 */
	public static void setIsChargingLed(Context context, boolean flag) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean(PREF_ENABLED_CHARGING_LED, flag);
		editor.apply();
	}

	/**
	 * Get is charging notification enabled flag
	 */
	public static boolean getIsChargingNotification(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(PREF_ENABLED_CHARGING_NOTIFICATION, PREF_ENABLED_CHARGING_NOTIFICATION_DEFAULT);
	}

	/**
	 * Set is charging notification enabled flag
	 */
	public static void setIsChargingNotification(Context context, boolean flag) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean(PREF_ENABLED_CHARGING_NOTIFICATION, flag);
		editor.apply();
	}

	/**
	 * Get is charging sound enabled flag
	 */
	public static boolean getIsChargingSound(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(PREF_ENABLED_CHARGING_SOUND, PREF_ENABLED_CHARGING_SOUND_DEFAULT);
	}

	/**
	 * Set is charging sound enabled flag
	 */
	public static void setIsChargingSound(Context context, boolean flag) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean(PREF_ENABLED_CHARGING_SOUND, flag);
		editor.apply();
	}

	/**
	 * Get is debug mode enabled flag
	 */
	public static boolean getIsDebugMode(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(PREF_ENABLED_DEBUG_MODE, PREF_ENABLED_DEBUG_MODE_DEFAULT);
	}

	/**
	 * Get battery level range 1
	 */
	public static int getBatteryLevelRange1Color(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL_RANGE_1, PREF_BATTERY_LEVEL_RANGE_1_DEFAULT);
	}

	/**
	 * set battery level range 1
	 */
	public static void setBatteryLevelRange1Color(Context context, int color) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL_RANGE_1, color);
		editor.apply();
	}

	/**
	 * Get battery level range 2
	 */
	public static int getBatteryLevelRange2Color(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL_RANGE_2, PREF_BATTERY_LEVEL_RANGE_2_DEFAULT);
	}

	/**
	 * set battery level range 2
	 */
	public static void setBatteryLevelRange2Color(Context context, int color) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL_RANGE_2, color);
		editor.apply();
	}

	/**
	 * Get battery level range 3
	 */
	public static int getBatteryLevelRange3Color(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL_RANGE_3, PREF_BATTERY_LEVEL_RANGE_3_DEFAULT);
	}

	/**
	 * set battery level range 3
	 */
	public static void setBatteryLevelRange3Color(Context context, int color) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL_RANGE_3, color);
		editor.apply();
	}

	/**
	 * Get battery level range 4
	 */
	public static int getBatteryLevelRange4Color(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL_RANGE_4, PREF_BATTERY_LEVEL_RANGE_4_DEFAULT);
	}

	/**
	 * set battery level range 4
	 */
	public static void setBatteryLevelRange4Color(Context context, int color) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL_RANGE_4, color);
		editor.apply();
	}

	/**
	 * Get battery level range 5
	 */
	public static int getBatteryLevelRange5Color(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL_RANGE_5, PREF_BATTERY_LEVEL_RANGE_5_DEFAULT);
	}

	/**
	 * set battery level range 5
	 */
	public static void setBatteryLevelRange5Color(Context context, int color) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL_RANGE_5, color);
		editor.apply();
	}

	/**
	 * Get battery level range 6
	 */
	public static int getBatteryLevelRange6Color(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL_RANGE_6, PREF_BATTERY_LEVEL_RANGE_6_DEFAULT);
	}

	/**
	 * set battery level range 6
	 */
	public static void setBatteryLevelRange6Color(Context context, int color) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL_RANGE_6, color);
		editor.apply();
	}

	/**
	 * Get battery level range 7
	 */
	public static int getBatteryLevelRange7Color(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL_RANGE_7, PREF_BATTERY_LEVEL_RANGE_7_DEFAULT);
	}

	/**
	 * set battery level range 7
	 */
	public static void setBatteryLevelRange7Color(Context context, int color) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL_RANGE_7, color);
		editor.apply();
	}

	/**
	 * Get battery level interval 1
	 */
	public static int getBatteryLevelInterval1(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL_INTERVAL_1, PREF_BATTERY_LEVEL_INTERVAL_1_DEFAULT);
	}

	/**
	 * set battery level interval 1
	 */
	public static void setBatteryLevelInterval1(Context context, int value) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL_INTERVAL_1, value);
		editor.apply();
	}

	/**
	 * Get battery level interval 2
	 */
	public static int getBatteryLevelInterval2(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL_INTERVAL_2, PREF_BATTERY_LEVEL_INTERVAL_2_DEFAULT);
	}

	/**
	 * set battery level interval 2
	 */
	public static void setBatteryLevelInterval2(Context context, int value) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL_INTERVAL_2, value);
		editor.apply();
	}

	/**
	 * Get battery level interval 3
	 */
	public static int getBatteryLevelInterval3(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL_INTERVAL_3, PREF_BATTERY_LEVEL_INTERVAL_3_DEFAULT);
	}

	/**
	 * set battery level interval 3
	 */
	public static void setBatteryLevelInterval3(Context context, int value) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL_INTERVAL_3, value);
		editor.apply();
	}

	/**
	 * Get battery level interval 4
	 */
	public static int getBatteryLevelInterval4(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL_INTERVAL_4, PREF_BATTERY_LEVEL_INTERVAL_4_DEFAULT);
	}

	/**
	 * set battery level interval 4
	 */
	public static void setBatteryLevelInterval4(Context context, int value) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL_INTERVAL_4, value);
		editor.apply();
	}

	/**
	 * Get battery level interval 5
	 */
	public static int getBatteryLevelInterval5(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL_INTERVAL_5, PREF_BATTERY_LEVEL_INTERVAL_5_DEFAULT);
	}

	/**
	 * set battery level interval 5
	 */
	public static void setBatteryLevelInterval5(Context context, int value) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL_INTERVAL_5, value);
		editor.apply();
	}

	/**
	 * Get battery level interval 6
	 */
	public static int getBatteryLevelInterval6(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL_INTERVAL_6, PREF_BATTERY_LEVEL_INTERVAL_6_DEFAULT);
	}

	/**
	 * set battery level interval 6
	 */
	public static void setBatteryLevelInterval6(Context context, int value) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL_INTERVAL_6, value);
		editor.apply();
	}

	/**
	 * Get battery level interval 7
	 */
	public static int getBatteryLevelInterval7(Context context) {
//		return PreferenceManager.getDefaultSharedPreferences(context)
//			.getInt(PREF_BATTERY_LEVEL_INTERVAL_7, PREF_BATTERY_LEVEL_INTERVAL_7_DEFAULT);
		return PREF_BATTERY_LEVEL_INTERVAL_7_DEFAULT;
	}

//	/** set battery level interval 7 */
//	public static void setBatteryLevelInterval7(Context context, int value) {
//		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
//		SharedPreferences.Editor editor = pref.edit();
//		editor.putInt(PREF_BATTERY_LEVEL_INTERVAL_7, value);
//		editor.apply();
//	}

	/**
	 * Get enabled blinking 1 flag
	 */
	public static boolean getEnabledBlink1(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(PREF_ENABLED_BLINKING_1, PREF_ENABLED_BLINKING_1_DEFAULT);
	}

	/**
	 * Set enabled blinking 1 flag
	 */
	public static void setEnabledBlink1(Context context, boolean flag) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean(PREF_ENABLED_BLINKING_1, flag);
		editor.apply();
	}

	/**
	 * Get blink on 1 interval
	 */
	public static int getBlinkOn1Interval(Context context) {
		String interval = PreferenceManager.getDefaultSharedPreferences(context)
				.getString(PREF_BLINK_ON_1, PREF_BLINK_ON_1_DEFAULT);
		return Integer.parseInt(interval);
	}

	/**
	 * Set blink on 1 interval
	 */
	public static void setBlinkOn1Interval(Context context, String interval) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(PREF_BLINK_ON_1, interval);
		editor.apply();
	}

	/**
	 * Get blink off 1 interval
	 */
	public static int getBlinkOff1Interval(Context context) {
		String interval = PreferenceManager.getDefaultSharedPreferences(context)
				.getString(PREF_BLINK_OFF_1, PREF_BLINK_OFF_1_DEFAULT);
		return Integer.parseInt(interval);
	}

	/**
	 * Set blink off 1 interval
	 */
	public static void setBlinkOff1Interval(Context context, String interval) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(PREF_BLINK_OFF_1, interval);
		editor.apply();
	}

	/**
	 * Get enabled blinking 2 flag
	 */
	public static boolean getEnabledBlink2(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(PREF_ENABLED_BLINKING_2, PREF_ENABLED_BLINKING_2_DEFAULT);
	}

	/**
	 * Set enabled blinking 2 flag
	 */
	public static void setEnabledBlink2(Context context, boolean flag) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean(PREF_ENABLED_BLINKING_2, flag);
		editor.apply();
	}

	/**
	 * Get blink on 2 interval
	 */
	public static int getBlinkOn2Interval(Context context) {
		String interval = PreferenceManager.getDefaultSharedPreferences(context)
				.getString(PREF_BLINK_ON_2, PREF_BLINK_ON_2_DEFAULT);
		return Integer.parseInt(interval);
	}

	/**
	 * Set blink on 2 interval
	 */
	public static void setBlinkOn2Interval(Context context, String interval) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(PREF_BLINK_ON_2, interval);
		editor.apply();
	}

	/**
	 * Get blink off 2 interval
	 */
	public static int getBlinkOff2Interval(Context context) {
		String interval = PreferenceManager.getDefaultSharedPreferences(context)
				.getString(PREF_BLINK_OFF_2, PREF_BLINK_OFF_2_DEFAULT);
		return Integer.parseInt(interval);
	}

	/**
	 * Set blink off 2 interval
	 */
	public static void setBlinkOff2Interval(Context context, String interval) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(PREF_BLINK_OFF_2, interval);
		editor.apply();
	}

	/**
	 * Get plugin sound
	 */
	public static String getPluginSound(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getString(PREF_PLUGIN_SOUND, PREF_PLUGIN_SOUND_DEFAULT);
	}

	/**
	 * Set plugin sound
	 */
	public static void setPluginSound(Context context, String sound) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(PREF_PLUGIN_SOUND, sound);
		editor.apply();
	}

	/**
	 * Get unplug sound
	 */
	public static String getUnplugSound(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getString(PREF_UNPLUG_SOUND, PREF_UNPLUG_SOUND_DEFAULT);
	}

	/**
	 * Set unplug sound
	 */
	public static void setUnplugSound(Context context, String sound) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(PREF_UNPLUG_SOUND, sound);
		editor.apply();
	}

	/**
	 * Get is monitor battery level enabled flag
	 */
	public static boolean getIsMonitorBatteryLevel(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(PREF_ENABLED_MONITOR_BATTERY_LEVEL, PREF_ENABLED_MONITOR_BATTERY_LEVEL_DEFAULT);
	}

	/**
	 * Set is monitor battery level enabled flag
	 */
	public static void setIsMonitorBatteryLevel(Context context, boolean flag) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean(PREF_ENABLED_MONITOR_BATTERY_LEVEL, flag);
		editor.apply();
	}

	/**
	 * Get battery level
	 */
	public static int getBatteryLevel(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_BATTERY_LEVEL, PREF_BATTERY_LEVEL_DEFAULT);
	}

	/**
	 * Set battery level
	 */
	public static void setBatteryLevel(Context context, int batteryLevel) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_BATTERY_LEVEL, batteryLevel);
		editor.apply();
	}

	/**
	 * Get battery level reached sound
	 */
	public static String getBatteryLevelReachedSound(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getString(PREF_BATTERY_LEVEL_REACHED_SOUND, PREF_BATTERY_LEVEL_REACHED_SOUND_DEFAULT);
	}

	/**
	 * Set battery level reached sound
	 */
	public static void setBatteryLevelReachedSound(Context context, String sound) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(PREF_BATTERY_LEVEL_REACHED_SOUND, sound);
		editor.apply();
	}

	/**
	 * Get is silent period enabled flag
	 */
	public static boolean getIsEnabledSilentPeriod(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(PREF_ENABLE_SILENT_PERIOD, PREF_ENABLE_SILENT_PERIOD_DEFAULT);
	}

	/**
	 * Set is silent period enabled flag
	 */
	public static void setIsEnabledSilentPeriod(Context context, boolean flag) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean(PREF_ENABLE_SILENT_PERIOD, flag);
		editor.apply();
	}

	/**
	 * Get silent period start hour
	 */
	public static int getSilentPeriodStartHour(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_SP_START_HOUR, PREF_SP_START_HOUR_DEFAULT);
	}

	/**
	 * Set silent period start hour
	 */
	public static void setSilentPeriodStartHour(Context context, int value) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_SP_START_HOUR, value);
		editor.apply();
	}

	/**
	 * Get silent period start minute
	 */
	public static int getSilentPeriodStartMinute(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_SP_START_MINUTE, PREF_SP_START_MINUTE_DEFAULT);
	}

	/**
	 * Set silent period start minute
	 */
	public static void setSilentPeriodStartMinute(Context context, int value) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_SP_START_MINUTE, value);
		editor.apply();
	}

	/**
	 * Get silent period end hour
	 */
	public static int getSilentPeriodEndHour(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_SP_END_HOUR, PREF_SP_END_HOUR_DEFAULT);
	}

	/**
	 * Set silent period end hour
	 */
	public static void setSilentPeriodEndHour(Context context, int value) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_SP_END_HOUR, value);
		editor.apply();
	}

	/**
	 * Get silent period end minute
	 */
	public static int getSilentPeriodEndMinute(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getInt(PREF_SP_END_MINUTE, PREF_SP_END_MINUTE_DEFAULT);
	}

	/**
	 * Set silent period end minute
	 */
	public static void setSilentPeriodEndMinute(Context context, int value) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(PREF_SP_END_MINUTE, value);
		editor.apply();
	}

	/**
	 * Get is disclaimer accepted flag
	 */
	public static boolean getIsDisclaimerAccepted(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(PREF_ACCEPTED_DISCLAIMER, AD_DEFAULT);
	}

	/**
	 * Set is disclaimer accepted flag
	 */
	public static void setIsDisclaimerAccepted(Context context, boolean flag) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean(PREF_ACCEPTED_DISCLAIMER, flag);
		editor.apply();
	}

	/**
	 * Get IGNORE_BATTERY_OPTIMIZATION don't show again flag
	 */
	public static boolean getIgnoreBatteryOptimizationDontShowAgain(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(PREF_IGNORE_BATTERY_OPTIMIZATION_DONT_SHOW_AGAIN, false);
	}

	/**
	 * Set IGNORE_BATTERY_OPTIMIZATION don't show again flag
	 */
	public static void setIgnoreBatteryOptimizationDontShowAgain(Context context, boolean flag) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean(PREF_IGNORE_BATTERY_OPTIMIZATION_DONT_SHOW_AGAIN, flag);
		editor.apply();
	}
}
