package hk.ChargingLED.preferences.ie;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import hk.ChargingLED.Constant;
import hk.ChargingLED.R;
import hk.ChargingLED.helper.BatteryLevelIntervalHelper;
import hk.ChargingLED.helper.PopToast;
import hk.ChargingLED.helper.PreferenceHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ImportActivity extends Activity {
	private static final String TAG = "ImportActivity";

	private static final int MASK_RED = 0x00FF0000;
	private static final int MASK_GREEN = 0x0000FF00;
	private static final int MASK_BLUE = 0x000000FF;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		readConfig();
		PopToast.toast(this, getString(R.string.settings_imported));
		finish();
	}

	private void readConfig() {
		try {
			File root = Environment.getExternalStorageDirectory();
			File configFilename = new File(root, Constant.CONFIG_FILENAME);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(configFilename));
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				processConfigLine(line);
			}

			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			PopToast.toast(this, getString(R.string.settings_not_found));
			finish();
			return;
		} catch (Exception ignored) {
		}

		BatteryLevelIntervalHelper.checkValidInterval(this);
	}

	private void processConfigLine(String line) {
		int alpha = 0xFF;
		int minInterval = 0;
		String minIntervalStr = "0";

		try {
			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_RANGE_1_COLOR)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_RANGE_1_COLOR.length() + 2);
				Log.i(TAG, "-- value=" + str);
				Long longValue = Long.parseLong(str, 16);
				int red = (int) (longValue & MASK_RED) >> 16;
				int green = (int) (longValue & MASK_GREEN) >> 8;
				int blue = (int) (longValue & MASK_BLUE);
				int color = Color.argb(alpha, red, green, blue);
				PreferenceHelper.setBatteryLevelRange1Color(this, color);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_RANGE_2_COLOR)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_RANGE_2_COLOR.length() + 2);
				Log.i(TAG, "-- value=" + str);
				Long longValue = Long.parseLong(str, 16);
				int red = (int) (longValue & MASK_RED) >> 16;
				int green = (int) (longValue & MASK_GREEN) >> 8;
				int blue = (int) (longValue & MASK_BLUE);
				int color = Color.argb(alpha, red, green, blue);
				PreferenceHelper.setBatteryLevelRange2Color(this, color);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_RANGE_3_COLOR)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_RANGE_3_COLOR.length() + 2);
				Log.i(TAG, "-- value=" + str);
				Long longValue = Long.parseLong(str, 16);
				int red = (int) (longValue & MASK_RED) >> 16;
				int green = (int) (longValue & MASK_GREEN) >> 8;
				int blue = (int) (longValue & MASK_BLUE);
				int color = Color.argb(alpha, red, green, blue);
				PreferenceHelper.setBatteryLevelRange3Color(this, color);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_RANGE_4_COLOR)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_RANGE_4_COLOR.length() + 2);
				Log.i(TAG, "-- value=" + str);
				Long longValue = Long.parseLong(str, 16);
				int red = (int) (longValue & MASK_RED) >> 16;
				int green = (int) (longValue & MASK_GREEN) >> 8;
				int blue = (int) (longValue & MASK_BLUE);
				int color = Color.argb(alpha, red, green, blue);
				PreferenceHelper.setBatteryLevelRange4Color(this, color);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_RANGE_5_COLOR)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_RANGE_5_COLOR.length() + 2);
				Log.i(TAG, "-- value=" + str);
				Long longValue = Long.parseLong(str, 16);
				int red = (int) (longValue & MASK_RED) >> 16;
				int green = (int) (longValue & MASK_GREEN) >> 8;
				int blue = (int) (longValue & MASK_BLUE);
				int color = Color.argb(alpha, red, green, blue);
				PreferenceHelper.setBatteryLevelRange5Color(this, color);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_RANGE_6_COLOR)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_RANGE_6_COLOR.length() + 2);
				Log.i(TAG, "-- value=" + str);
				Long longValue = Long.parseLong(str, 16);
				int red = (int) (longValue & MASK_RED) >> 16;
				int green = (int) (longValue & MASK_GREEN) >> 8;
				int blue = (int) (longValue & MASK_BLUE);
				int color = Color.argb(alpha, red, green, blue);
				PreferenceHelper.setBatteryLevelRange6Color(this, color);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_RANGE_7_COLOR)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_RANGE_7_COLOR.length() + 2);
				Log.i(TAG, "-- value=" + str);
				Long longValue = Long.parseLong(str, 16);
				int red = (int) (longValue & MASK_RED) >> 16;
				int green = (int) (longValue & MASK_GREEN) >> 8;
				int blue = (int) (longValue & MASK_BLUE);
				int color = Color.argb(alpha, red, green, blue);
				PreferenceHelper.setBatteryLevelRange7Color(this, color);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_1)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_1.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str, 10);
				PreferenceHelper.setBatteryLevelInterval1(this, intValue);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_2)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_2.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str, 10);
				PreferenceHelper.setBatteryLevelInterval2(this, intValue);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_3)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_3.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str, 10);
				PreferenceHelper.setBatteryLevelInterval3(this, intValue);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_4)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_4.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str, 10);
				PreferenceHelper.setBatteryLevelInterval4(this, intValue);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_5)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_5.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str, 10);
				PreferenceHelper.setBatteryLevelInterval5(this, intValue);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_6)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_6.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str, 10);
				PreferenceHelper.setBatteryLevelInterval6(this, intValue);
				return;
			}

			if (line.startsWith(Constant.CONFIG_ENABLED_BLINK_1)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_ENABLED_BLINK_1.length());
				Log.i(TAG, "-- value=" + str);
				boolean flag = Boolean.parseBoolean(str);
				PreferenceHelper.setEnabledBlink1(this, flag);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BLINK_ON_1_INTERVAL)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BLINK_ON_1_INTERVAL.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str);
				if (intValue < minInterval) {
					str = minIntervalStr;
//					intValue = minInterval;
				}
				PreferenceHelper.setBlinkOn1Interval(this, str);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BLINK_OFF_1_INTERVAL)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BLINK_OFF_1_INTERVAL.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str);
				if (intValue < minInterval) {
					str = minIntervalStr;
//					intValue = minInterval;
				}
				PreferenceHelper.setBlinkOff1Interval(this, str);
				return;
			}

			if (line.startsWith(Constant.CONFIG_ENABLED_BLINK_2)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_ENABLED_BLINK_2.length());
				Log.i(TAG, "-- value=" + str);
				boolean flag = Boolean.parseBoolean(str);
				PreferenceHelper.setEnabledBlink2(this, flag);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BLINK_ON_2_INTERVAL)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BLINK_ON_2_INTERVAL.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str);
				if (intValue < minInterval) {
					str = minIntervalStr;
//					intValue = minInterval;
				}
				PreferenceHelper.setBlinkOn2Interval(this, str);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BLINK_OFF_2_INTERVAL)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BLINK_OFF_2_INTERVAL.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str);
				if (intValue < minInterval) {
					str = minIntervalStr;
//					intValue = minInterval;
				}
				PreferenceHelper.setBlinkOff2Interval(this, str);
				return;
			}

			if (line.startsWith(Constant.CONFIG_CHARGING_LED)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_CHARGING_LED.length());
				Log.i(TAG, "-- value=" + str);
				PreferenceHelper.setIsChargingLed(this, Boolean.parseBoolean(str));
				return;
			}

			if (line.startsWith(Constant.CONFIG_CHARGING_NOTIFICATION)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_CHARGING_NOTIFICATION.length());
				Log.i(TAG, "-- value=" + str);
				PreferenceHelper.setIsChargingNotification(this, Boolean.parseBoolean(str));
				return;
			}

			if (line.startsWith(Constant.CONFIG_CHARGING_SOUND)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_CHARGING_SOUND.length());
				Log.i(TAG, "-- value=" + str);
				PreferenceHelper.setIsChargingSound(this, Boolean.parseBoolean(str));
				return;
			}

			if (line.startsWith(Constant.CONFIG_PLUGIN_SOUND)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_PLUGIN_SOUND.length());
				Log.i(TAG, "-- value=" + str);
				PreferenceHelper.setPluginSound(this, str);
				return;
			}

			if (line.startsWith(Constant.CONFIG_UNPLUG_SOUND)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_UNPLUG_SOUND.length());
				Log.i(TAG, "-- value=" + str);
				PreferenceHelper.setUnplugSound(this, str);
				return;
			}

			if (line.startsWith(Constant.CONFIG_MONITOR_BATTERY_LEVEL)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_MONITOR_BATTERY_LEVEL.length());
				Log.i(TAG, "-- value=" + str);
				PreferenceHelper.setIsMonitorBatteryLevel(this, Boolean.parseBoolean(str));
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str);
				if (intValue < 1 || intValue > 100) {
					intValue = 100;
				}
				PreferenceHelper.setBatteryLevel(this, intValue);
				return;
			}

			if (line.startsWith(Constant.CONFIG_BATTERY_LEVEL_REACHED_SOUND)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_BATTERY_LEVEL_REACHED_SOUND.length());
				Log.i(TAG, "-- value=" + str);
				PreferenceHelper.setBatteryLevelReachedSound(this, str);
				return;
			}

			if (line.startsWith(Constant.CONFIG_ENABLE_SILENT_PERIOD)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_ENABLE_SILENT_PERIOD.length());
				Log.i(TAG, "-- value=" + str);
				PreferenceHelper.setIsEnabledSilentPeriod(this, Boolean.parseBoolean(str));
				return;
			}

			if (line.startsWith(Constant.CONFIG_SILENT_PERIOD_START_HOUR)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_SILENT_PERIOD_START_HOUR.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str);
				if (intValue < 0 || intValue > 23) {
					intValue = 0;
				}
				PreferenceHelper.setSilentPeriodStartHour(this, intValue);
				return;
			}

			if (line.startsWith(Constant.CONFIG_SILENT_PERIOD_START_MINUTE)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_SILENT_PERIOD_START_MINUTE.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str);
				if (intValue < 0 || intValue > 59) {
					intValue = 0;
				}
				PreferenceHelper.setSilentPeriodStartMinute(this, intValue);
				return;
			}

			if (line.startsWith(Constant.CONFIG_SILENT_PERIOD_END_HOUR)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_SILENT_PERIOD_END_HOUR.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str);
				if (intValue < 0 || intValue > 23) {
					intValue = 0;
				}
				PreferenceHelper.setSilentPeriodEndHour(this, intValue);
				return;
			}

			if (line.startsWith(Constant.CONFIG_SILENT_PERIOD_END_MINUTE)) {
				Log.d(TAG, "-- " + line);
				String str = line.substring(Constant.CONFIG_SILENT_PERIOD_END_MINUTE.length());
				Log.i(TAG, "-- value=" + str);
				int intValue = Integer.parseInt(str);
				if (intValue < 0 || intValue > 59) {
					intValue = 0;
				}
				PreferenceHelper.setSilentPeriodEndMinute(this, intValue);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
