package hk.ChargingLED.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import hk.ChargingLED.service.BootCompletedService;

public class BootCompletedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
			Intent serviceIntent = new Intent(context, BootCompletedService.class);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
				context.startForegroundService(serviceIntent);
			} else {
				context.startService(serviceIntent);
			}
		}
	}
}
