package hk.ChargingLED;

import android.app.Application;
import android.app.NotificationManager;
import android.os.Build;

import hk.ChargingLED.helper.NotificationHelper;
import hk.ChargingLED.helper.ScheduleJobHelper;

public class MainApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
			ScheduleJobHelper.scheduleJobPowerConnected(this, 0);

			NotificationHelper.createFsNotificationChannel(this,
					Constant.NOTIFICATION_CHANNEL_ID.FOREGROUND_SERVICE_STATUS,
					getString(Constant.NOTIFICATION_CHANNEL_NAME.FOREGROUND_SERVICE_STATUS),
					getString(Constant.NOTIFICATION_CHANNEL_DESC.FOREGROUND_SERVICE_STATUS),
					NotificationManager.IMPORTANCE_LOW);

			NotificationHelper.createNotificationChannel(this,
					Constant.NOTIFICATION_CHANNEL_ID.UNCATEGORIZED,
					getString(Constant.NOTIFICATION_CHANNEL_NAME.UNCATEGORIZED),
					getString(Constant.NOTIFICATION_CHANNEL_DESC.UNCATEGORIZED),
					NotificationManager.IMPORTANCE_LOW);
		}
	}
}
