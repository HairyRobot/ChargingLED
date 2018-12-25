package hk.ChargingLED.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import hk.ChargingLED.Constant;
import hk.ChargingLED.R;
import hk.ChargingLED.helper.ForegroundServiceNotificationHelper;
import hk.ChargingLED.helper.NotifySoundHelper;
import hk.ChargingLED.helper.PreferenceHelper;
import hk.ChargingLED.helper.ScheduleJobHelper;
import hk.ChargingLED.receiver.AlarmReceiver;

public class AlarmService extends WakefulIntentService {
	private static final String TAG = AlarmService.class.getSimpleName();

	private static final int BATTERY_ALARM_ID = 123;
	private static final long DURATION = 1000 * 60 * 5;    // 5 minutes

	private static boolean sAlarmOn = false;

	public AlarmService() {
		super("AlarmService");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
			startForeground(Constant.NOTIFICATION_ID.FS_ALARM_SERVICE_ID,
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
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
			stopForeground(true);
		}
		super.onDestroy();
	}

	@Override
	void doWakefulWork(Intent intent) {
		if (!ChargingLedService.sIsCharging) {
			return;
		}

		Log.d(TAG, "Alarm fired");
		setAlarmOff();

		if (PreferenceHelper.getIsChargingSound(this)) {
			if (ChargingLedService.sChargeLevel >= PreferenceHelper.getBatteryLevel(this)) {
				NotifySoundHelper.NotifySoundChargingStatus(this, ChargingLedService.sChargeLevel);
			}
		}
	}

	public static void scheduleAlarm(Context context) {
		Intent intent = new Intent(context, AlarmReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, BATTERY_ALARM_ID, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
			ScheduleJobHelper.scheduleJobAlarm(context, DURATION);
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    // API-19
			am.setExact(AlarmManager.RTC_WAKEUP, (System.currentTimeMillis() + DURATION), pi);
		} else {
			am.set(AlarmManager.RTC_WAKEUP, (System.currentTimeMillis() + DURATION), pi);
		}
		Log.d(TAG, "Scheduled alarm");
		sAlarmOn = true;
	}

	public static void setAlarmOff() {
		sAlarmOn = false;
	}

	public static boolean isAlarmOn() {
		return sAlarmOn;
	}
}
