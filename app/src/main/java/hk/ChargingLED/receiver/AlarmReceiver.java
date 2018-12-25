package hk.ChargingLED.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import hk.ChargingLED.service.AlarmService;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		AlarmService.acquireStaticLock(context);
		Intent i = new Intent(context, AlarmService.class);
		context.startService(i);
	}
}
