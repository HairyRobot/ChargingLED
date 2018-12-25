package hk.ChargingLED.preferences.ie;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;

import hk.ChargingLED.Constant;
import hk.ChargingLED.helper.PopToast;
import hk.ChargingLED.helper.PreferenceHelper;

import java.io.File;
import java.io.FileWriter;

public class ExportActivity extends Activity {

	@SuppressLint("StaticFieldLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				return writeConfig();
			}

			@Override
			protected void onPostExecute(String result) {
				PopToast.toast(ExportActivity.this, result);
				finish();
			}
		}.execute();
	}

	private String writeConfig() {
		String configFullFilename = "!!!";

		try {
			File root = Environment.getExternalStorageDirectory();

			if (!root.exists()) {
				root.mkdirs();
			}

			File configFilename = new File(root, Constant.CONFIG_FILENAME);
			configFullFilename = configFilename.toString();
			FileWriter writer = new FileWriter(configFilename);

			writer.append("# ChargingLED config file\n");
			writer.append("# Version 4\n");
			writer.append("# Colour is coded as ARGB, where Alpha is ignored, and is always set to 0xFF. As required by the API.\n");

			writer.append("# Charge level 100%  <- this is the default interval\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_RANGE_1_COLOR
					+"0x"+Integer.toHexString(PreferenceHelper.getBatteryLevelRange1Color(this))+"\n");
			writer.append("# Charge level 95% - 99%  <- this is the default interval\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_RANGE_2_COLOR
					+"0x"+Integer.toHexString(PreferenceHelper.getBatteryLevelRange2Color(this))+"\n");
			writer.append("# Charge level 90% - 94%  <- this is the default interval\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_RANGE_3_COLOR
					+"0x"+Integer.toHexString(PreferenceHelper.getBatteryLevelRange3Color(this))+"\n");
			writer.append("# Charge level 75% - 89%  <- this is the default interval\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_RANGE_4_COLOR
					+"0x"+Integer.toHexString(PreferenceHelper.getBatteryLevelRange4Color(this))+"\n");
			writer.append("# Charge level 50% - 74%  <- this is the default interval\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_RANGE_5_COLOR
					+"0x"+Integer.toHexString(PreferenceHelper.getBatteryLevelRange5Color(this))+"\n");
			writer.append("# Charge level 25% - 49%  <- this is the default interval\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_RANGE_6_COLOR
					+"0x"+Integer.toHexString(PreferenceHelper.getBatteryLevelRange6Color(this))+"\n");
			writer.append("# Charge level below 25%  <- this is the default interval\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_RANGE_7_COLOR
					+"0x"+Integer.toHexString(PreferenceHelper.getBatteryLevelRange7Color(this))+"\n");

			writer.append("# Battery level interval 1\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_1
					+PreferenceHelper.getBatteryLevelInterval1(this)+"\n");
			writer.append("# Battery level interval 2\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_2
					+PreferenceHelper.getBatteryLevelInterval2(this)+"\n");
			writer.append("# Battery level interval 3\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_3
					+PreferenceHelper.getBatteryLevelInterval3(this)+"\n");
			writer.append("# Battery level interval 4\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_4
					+PreferenceHelper.getBatteryLevelInterval4(this)+"\n");
			writer.append("# Battery level interval 5\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_5
					+PreferenceHelper.getBatteryLevelInterval5(this)+"\n");
			writer.append("# Battery level interval 6\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_INTERVAL_6
					+PreferenceHelper.getBatteryLevelInterval6(this)+"\n");

			writer.append("# Charge level 100% (on/off interval in ms.  1000 ms = 1 second)\n");
			writer.append(Constant.CONFIG_ENABLED_BLINK_1
					+PreferenceHelper.getEnabledBlink1(this)+"\n");
			writer.append(Constant.CONFIG_BLINK_ON_1_INTERVAL
					+PreferenceHelper.getBlinkOn1Interval(this)+"\n");
			writer.append(Constant.CONFIG_BLINK_OFF_1_INTERVAL
					+PreferenceHelper.getBlinkOff1Interval(this)+"\n");

			writer.append("# Charge level below 100% (on/off interval in ms.  1000 ms = 1 second)\n");
			writer.append(Constant.CONFIG_ENABLED_BLINK_2
					+PreferenceHelper.getEnabledBlink2(this)+"\n");
			writer.append(Constant.CONFIG_BLINK_ON_2_INTERVAL
					+PreferenceHelper.getBlinkOn2Interval(this)+"\n");
			writer.append(Constant.CONFIG_BLINK_OFF_2_INTERVAL
					+PreferenceHelper.getBlinkOff2Interval(this)+"\n");

			writer.append("# Charging LED (true/false)\n");
			writer.append(Constant.CONFIG_CHARGING_LED
					+PreferenceHelper.getIsChargingLed(this)+"\n");

			writer.append("# Charging Notification (true/false)\n");
			writer.append(Constant.CONFIG_CHARGING_NOTIFICATION
					+PreferenceHelper.getIsChargingNotification(this)+"\n");

			writer.append("# Charging Sound (true/false)\n");
			writer.append(Constant.CONFIG_CHARGING_SOUND
					+PreferenceHelper.getIsChargingSound(this)+"\n");

			writer.append("# Plugin sound\n");
			writer.append(Constant.CONFIG_PLUGIN_SOUND
					+PreferenceHelper.getPluginSound(this)+"\n");
			writer.append("# Unplug sound\n");
			writer.append(Constant.CONFIG_UNPLUG_SOUND
					+PreferenceHelper.getUnplugSound(this)+"\n");
			writer.append("# Monitor battery level (true/false)\n");
			writer.append(Constant.CONFIG_MONITOR_BATTERY_LEVEL
					+PreferenceHelper.getIsMonitorBatteryLevel(this)+"\n");
			writer.append("# Battery level (1-100)\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL
					+PreferenceHelper.getBatteryLevel(this)+"\n");
			writer.append("# Battery level reached sound\n");
			writer.append(Constant.CONFIG_BATTERY_LEVEL_REACHED_SOUND
					+PreferenceHelper.getBatteryLevelReachedSound(this)+"\n");
			writer.append("# Enable silent period (true/false)\n");
			writer.append(Constant.CONFIG_ENABLE_SILENT_PERIOD
					+PreferenceHelper.getIsEnabledSilentPeriod(this)+"\n");
			writer.append("# Silent period start hour (00-23)\n");
			writer.append(Constant.CONFIG_SILENT_PERIOD_START_HOUR
					+PreferenceHelper.getSilentPeriodStartHour(this)+"\n");
			writer.append("# Silent period start minute (00-59)\n");
			writer.append(Constant.CONFIG_SILENT_PERIOD_START_MINUTE
					+PreferenceHelper.getSilentPeriodStartMinute(this)+"\n");
			writer.append("# Silent period end hour (00-23)\n");
			writer.append(Constant.CONFIG_SILENT_PERIOD_END_HOUR
					+PreferenceHelper.getSilentPeriodEndHour(this)+"\n");
			writer.append("# Silent period end minute (00-59)\n");
			writer.append(Constant.CONFIG_SILENT_PERIOD_END_MINUTE
					+PreferenceHelper.getSilentPeriodEndMinute(this)+"\n");

			writer.append("#\n");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return configFullFilename;
	}
}
