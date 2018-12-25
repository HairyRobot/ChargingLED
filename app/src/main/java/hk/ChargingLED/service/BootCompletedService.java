package hk.ChargingLED.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.IBinder;

import hk.ChargingLED.Constant;
import hk.ChargingLED.R;
import hk.ChargingLED.helper.ForegroundServiceNotificationHelper;

public class BootCompletedService extends Service {
	private static final String TAG = BootCompletedService.class.getSimpleName();

	@Override
	public void onCreate() {
		super.onCreate();

		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(batteryBroadcastReceiver, filter);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
			startForeground(Constant.NOTIFICATION_ID.FS_BOOT_COMPLETED_SERVICE_ID,
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
		unregisterReceiver(batteryBroadcastReceiver);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
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
			if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {

				boolean isPlugged = false;
				switch (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0)) {
					case 0:    // On battery
						break;
					case BatteryManager.BATTERY_PLUGGED_AC:
					case BatteryManager.BATTERY_PLUGGED_USB:
					case BatteryManager.BATTERY_PLUGGED_WIRELESS:
					default:
						isPlugged = true;
						break;
				}

				boolean isCharging = false;
				switch (intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN)) {
					case BatteryManager.BATTERY_STATUS_CHARGING:
					case BatteryManager.BATTERY_STATUS_FULL:
						isCharging = true;
						break;
					case BatteryManager.BATTERY_STATUS_DISCHARGING:
					case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
						break;
					default:    // BatteryManager.BATTERY_STATUS_UNKNOWN
						break;
				}

//				switch (intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN)) {
//					case BatteryManager.BATTERY_HEALTH_UNKNOWN:
//						break;
//					case BatteryManager.BATTERY_HEALTH_GOOD:
//						break;
//					case BatteryManager.BATTERY_HEALTH_OVERHEAT:
//						break;
//					case BatteryManager.BATTERY_HEALTH_DEAD:
//						break;
//					case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
//						break;
//					case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
//						break;
//					case BatteryManager.BATTERY_HEALTH_COLD:
//						break;
//				}

				if (isPlugged && isCharging) {
					if (!ChargingLedService.sIsRunning) {
						Intent serviceIntent = new Intent(getApplicationContext(), ChargingLedService.class);
						getApplicationContext().startService(serviceIntent);
					}
				}

				stopSelf();
			}
		}
	};
}
