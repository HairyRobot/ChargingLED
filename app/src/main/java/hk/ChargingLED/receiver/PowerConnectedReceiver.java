package hk.ChargingLED.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import hk.ChargingLED.helper.PreferenceHelper;
import hk.ChargingLED.helper.SoundHelper;
import hk.ChargingLED.service.ChargingLedService;

public class PowerConnectedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (!PreferenceHelper.getIsDisclaimerAccepted(context)) {
			return;
		}

		if (!PreferenceHelper.getIsMonitorCharging(context)) {
			return;
		}

		if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())) {
			if (ChargingLedService.sIsRunning) {
				return;
			}
			if (PreferenceHelper.getIsDebugMode(context)) {
				SoundHelper.onPowerConnectedDebug(context);
			}
			if (PreferenceHelper.getIsChargingSound(context)) {
				SoundHelper.onPowerConnectedSound(context);
			}
			try {
				Intent serviceIntent = new Intent(context, ChargingLedService.class);
				context.startService(serviceIntent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
