package hk.ChargingLED.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import hk.ChargingLED.Constant;
import hk.ChargingLED.R;
import hk.ChargingLED.helper.ForegroundServiceNotificationHelper;
import hk.ChargingLED.helper.NotifyChargingHelper;
import hk.ChargingLED.helper.NotifyLedHelper;
import hk.ChargingLED.helper.NotifySoundHelper;
import hk.ChargingLED.helper.PreferenceHelper;
import hk.ChargingLED.helper.ScheduleJobHelper;
import hk.ChargingLED.helper.SoundHelper;

public class ChargingLedService extends Service {
	private static final String TAG = ChargingLedService.class.getSimpleName();

	public static boolean sIsRunning = false;
	public static boolean sIsStopping = false;

	public static int sPrevChargeLevel = -1;
	public static int sChargeLevel = 0;
	public static boolean sIsCharging = true;

	@Override
	public void onCreate() {
		super.onCreate();

		sIsRunning = true;
		sIsStopping = false;
		sChargeLevel = 0;
		sPrevChargeLevel = -1;

		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
		registerReceiver(batteryBroadcastReceiver, filter);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
			startForeground(Constant.NOTIFICATION_ID.FS_CHARGING_LED_SERVICE_ID,
					ForegroundServiceNotificationHelper.buildForegroundNotification(this,
							Constant.NOTIFICATION_CHANNEL_ID.FOREGROUND_SERVICE_STATUS,
							R.drawable.ic_launcher,
							TAG,
							R.string.foreground_service_see_faq)
			);
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		sIsRunning = false;

		unregisterReceiver(batteryBroadcastReceiver);
		NotifyChargingHelper.clearStatusNotification(this);
		NotifySoundHelper.clearSoundNotification(this);
		NotifyLedHelper.clearLed(this);
		AlarmService.setAlarmOff();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
			ScheduleJobHelper.scheduleJobPowerConnected(this, 0);
			stopForeground(true);
		}
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private BroadcastReceiver batteryBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			// No needs to check Intent.ACTION_SCREEN_OFF and Intent.ACTION_SCREEN_ON 
			if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())) {
				if (PreferenceHelper.getIsDebugMode(context)) {
					SoundHelper.onPowerDisconnectedDebug(context);
				}
				if (PreferenceHelper.getIsChargingSound(context)) {
					SoundHelper.onPowerDisconnectedSound(context);
				}
				sIsStopping = true;
				sPrevChargeLevel = -1;
				stopSelf();
				return;
			} else if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
				if (sPrevChargeLevel != sChargeLevel) {
					sPrevChargeLevel = sChargeLevel;
				}
				int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
				int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
				if (scale <= 0) {
					scale = 100;
				}
				sChargeLevel = (level * 100) / scale;
				Log.d(TAG, "level=" + level + "   scale=" + scale + "   chargeLevel=" + sChargeLevel
						+ "   prevChargeLevel=" + sPrevChargeLevel);
				sIsCharging = false;
				switch (intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN)) {
					case BatteryManager.BATTERY_STATUS_CHARGING:
					case BatteryManager.BATTERY_STATUS_FULL:
						sIsCharging = true;
						break;
					case BatteryManager.BATTERY_STATUS_DISCHARGING:
					case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
						break;
					default:    // BatteryManager.BATTERY_STATUS_UNKNOWN
						break;
				}
			}

			if (!sIsStopping) {
				ProcessChargeLevel(sIsCharging, sChargeLevel);
			}
		}
	};

	private void ProcessChargeLevel(boolean isCharging, int chargeLevel) {
		if (!isCharging) {
			NotifyChargingHelper.clearStatusNotification(this);
			NotifySoundHelper.clearSoundNotification(this);
			NotifyLedHelper.clearLed(this);
			AlarmService.setAlarmOff();
			return;
		}

		if (chargeLevel == ChargingLedService.sPrevChargeLevel) {
			return;
		}

		if (!PreferenceHelper.getIsChargingNotification(this)) {
			NotifyChargingHelper.clearStatusNotification(this);
		}
		if (!PreferenceHelper.getIsChargingSound(this)) {
			NotifySoundHelper.clearSoundNotification(this);
		}
		if (!PreferenceHelper.getIsChargingLed(this)) {
			NotifyLedHelper.clearLed(this);
		}

		// charging status is enabled
		if (PreferenceHelper.getIsChargingNotification(this)) {
			if (!PreferenceHelper.getIsChargingSound(this)) {    // charging sound is disabled
				NotifyChargingHelper.NotifyChargingStatus(this, chargeLevel);
			} else {    // charging sound enabled
				if (!PreferenceHelper.getIsMonitorBatteryLevel(this)) {    // is not monitor battery level
					NotifyChargingHelper.NotifyChargingStatus(this, chargeLevel);
				} else {    // is monitor battery level
					if (chargeLevel < PreferenceHelper.getBatteryLevel(this)) {
						NotifyChargingHelper.NotifyChargingStatus(this, chargeLevel);
					}
				}
			}
		}
		// charging sound is enabled
		if (PreferenceHelper.getIsChargingSound(this)) {
			if (PreferenceHelper.getIsMonitorBatteryLevel(this)) {
				if (chargeLevel >= PreferenceHelper.getBatteryLevel(this)) {
					NotifySoundHelper.NotifySoundChargingStatus(this, chargeLevel);
				}
			}
		}
		// charging LED is enabled
		if (PreferenceHelper.getIsChargingLed(this)) {
			flashLED(chargeLevel);
		}
	}

	private void flashLED(int chargeLevel) {
		int ledColor;    // Constant.ARGB_WHITE
		int ledOnMs;
		int ledOffMs;

		if (PreferenceHelper.getEnabledBlink2(this)) {
			ledOnMs = PreferenceHelper.getBlinkOn2Interval(this);
			ledOffMs = PreferenceHelper.getBlinkOff2Interval(this);
		} else {
			ledOnMs = 1;
			ledOffMs = 0;
		}

		if (chargeLevel >= PreferenceHelper.getBatteryLevelInterval1(this)) {            // 100
			if (PreferenceHelper.getEnabledBlink1(this)) {
				ledOnMs = PreferenceHelper.getBlinkOn1Interval(this);
				ledOffMs = PreferenceHelper.getBlinkOff1Interval(this);
			} else {
				ledOnMs = 1;
				ledOffMs = 0;
			}
			ledColor = PreferenceHelper.getBatteryLevelRange1Color(this);
		} else if (chargeLevel >= PreferenceHelper.getBatteryLevelInterval2(this)) {    // 95-99
			ledColor = PreferenceHelper.getBatteryLevelRange2Color(this);
		} else if (chargeLevel >= PreferenceHelper.getBatteryLevelInterval3(this)) {    // 90-94
			ledColor = PreferenceHelper.getBatteryLevelRange3Color(this);
		} else if (chargeLevel >= PreferenceHelper.getBatteryLevelInterval4(this)) {    // 75-89
			ledColor = PreferenceHelper.getBatteryLevelRange4Color(this);
		} else if (chargeLevel >= PreferenceHelper.getBatteryLevelInterval5(this)) {    // 50-74
			ledColor = PreferenceHelper.getBatteryLevelRange5Color(this);
		} else if (chargeLevel >= PreferenceHelper.getBatteryLevelInterval6(this)) {    // 25-49
			ledColor = PreferenceHelper.getBatteryLevelRange6Color(this);
		} else {                                                                        // below 25
			ledColor = PreferenceHelper.getBatteryLevelRange7Color(this);
		}

		NotifyLedHelper.clearLed(this);
		NotifyLedHelper.showLed(this, chargeLevel, ledColor, ledOnMs, ledOffMs);
	}
}
