package hk.ChargingLED;

/*
 * To test Power Connected
 * (1) vi ~/.emulator_console_auth_token
 * (2) QrarYfmvxFBXM2bd	<-- copy the token from the file
 * (3) telnet localhost 5554
 * (4) auth QrarYfmvxFBXM2bd
 * (5) power display
 * (6) power ac on/off
 * (7) power capacity 0..100
 *
 * runs app in background
 * adb shell am make-uid-idle <package>
 *
 * force background limitations
 * adb shell cmd appops set <package> RUN_IN_BACKGROUND deny
 *
 * returns app to normal behavior (based on target SDK)
 * adb shell cmd appops set <package> RUN_IN_BACKGROUND allow
 */
public abstract class Constant {
	public static final String THIS_PACKAGE = "hk.ChargingLED";

	public static final String CONFIG_FILENAME = "ChargingLED.ini";
	public static final String CONFIG_BATTERY_LEVEL_RANGE_1_COLOR = "BatteryLevelRange1Color=";
	public static final String CONFIG_BATTERY_LEVEL_RANGE_2_COLOR = "BatteryLevelRange2Color=";
	public static final String CONFIG_BATTERY_LEVEL_RANGE_3_COLOR = "BatteryLevelRange3Color=";
	public static final String CONFIG_BATTERY_LEVEL_RANGE_4_COLOR = "BatteryLevelRange4Color=";
	public static final String CONFIG_BATTERY_LEVEL_RANGE_5_COLOR = "BatteryLevelRange5Color=";
	public static final String CONFIG_BATTERY_LEVEL_RANGE_6_COLOR = "BatteryLevelRange6Color=";
	public static final String CONFIG_BATTERY_LEVEL_RANGE_7_COLOR = "BatteryLevelRange7Color=";
	public static final String CONFIG_BATTERY_LEVEL_INTERVAL_1 = "BatteryLevelInterval1=";
	public static final String CONFIG_BATTERY_LEVEL_INTERVAL_2 = "BatteryLevelInterval2=";
	public static final String CONFIG_BATTERY_LEVEL_INTERVAL_3 = "BatteryLevelInterval3=";
	public static final String CONFIG_BATTERY_LEVEL_INTERVAL_4 = "BatteryLevelInterval4=";
	public static final String CONFIG_BATTERY_LEVEL_INTERVAL_5 = "BatteryLevelInterval5=";
	public static final String CONFIG_BATTERY_LEVEL_INTERVAL_6 = "BatteryLevelInterval6=";
	public static final String CONFIG_ENABLED_BLINK_1 = "EnabledBlink1=";
	public static final String CONFIG_BLINK_ON_1_INTERVAL = "BlinkOn1Interval=";
	public static final String CONFIG_BLINK_OFF_1_INTERVAL = "BlinkOff1Interval=";
	public static final String CONFIG_ENABLED_BLINK_2 = "EnabledBlink2=";
	public static final String CONFIG_BLINK_ON_2_INTERVAL = "BlinkOn2Interval=";
	public static final String CONFIG_BLINK_OFF_2_INTERVAL = "BlinkOff2Interval=";
	public static final String CONFIG_CHARGING_LED = "ChargingLED=";
	public static final String CONFIG_CHARGING_NOTIFICATION = "ChargingNotification=";
	public static final String CONFIG_CHARGING_SOUND = "ChargingSound=";
	public static final String CONFIG_PLUGIN_SOUND = "PluginSound=";
	public static final String CONFIG_UNPLUG_SOUND = "UnplugSound=";
	public static final String CONFIG_MONITOR_BATTERY_LEVEL = "MonitorBatteryLevel=";
	public static final String CONFIG_BATTERY_LEVEL = "BatteryLevel=";
	public static final String CONFIG_BATTERY_LEVEL_REACHED_SOUND = "BatteryLevelReachedSound=";
	public static final String CONFIG_ENABLE_SILENT_PERIOD = "EnableSilentPeriod=";
	public static final String CONFIG_SILENT_PERIOD_START_HOUR = "SilentPeriodStartHour=";
	public static final String CONFIG_SILENT_PERIOD_START_MINUTE = "SilentPeriodStartMinute=";
	public static final String CONFIG_SILENT_PERIOD_END_HOUR = "SilentPeriodEndHour=";
	public static final String CONFIG_SILENT_PERIOD_END_MINUTE = "SilentPeriodEndMinute=";

	public static final int ARGB_RED = 0xFFFF0000;
	public static final int ARGB_GREEN = 0xFF00FF00;
	public static final int ARGB_BLUE = 0xFF0000FF;
	public static final int ARGB_YELLOW = 0xFFFFFF00;
	public static final int ARGB_PURPLE = 0xFF9D00FF;
	public static final int ARGB_WHITE = 0xFFFFFFFF;

	public interface NOTIFICATION_ID {
		int FS_ALARM_SERVICE_ID = 101;
		int FS_BOOT_COMPLETED_SERVICE_ID = 102;
		int FS_CHARGING_LED_SERVICE_ID = 103;
		int LED_NOTIFICATION_ID = 32101;
		int STATUS_NOTIFICATION_ID = 32102;
		int SOUND_NOTIFICATION_ID = 32103;
	}

	public interface NOTIFICATION_CHANNEL_ID {
		String FOREGROUND_SERVICE_STATUS = "FOREGROUND_SERVICE_STATUS";
		String UNCATEGORIZED = "UNCATEGORIZED";
	}

	public interface NOTIFICATION_CHANNEL_NAME {
		int FOREGROUND_SERVICE_STATUS = R.string.channel_name_foreground_service_status;
		int UNCATEGORIZED = R.string.channel_name_uncategorized;
	}

	public interface NOTIFICATION_CHANNEL_DESC {
		int FOREGROUND_SERVICE_STATUS = R.string.channel_name_foreground_service_status;
		int UNCATEGORIZED = R.string.channel_name_uncategorized;
	}
}
