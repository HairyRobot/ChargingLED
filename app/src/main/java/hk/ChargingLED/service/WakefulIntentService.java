package hk.ChargingLED.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import hk.ChargingLED.Constant;

abstract public class WakefulIntentService extends IntentService {
	abstract void doWakefulWork(Intent intent);

	public static final String LOCK_NAME_STATIC = Constant.THIS_PACKAGE + ":Service.Static";
	private static PowerManager.WakeLock sLockStatic = null;

	public WakefulIntentService(String name) {
		super(name);
	}

	public static void acquireStaticLock(Context context) {
		getLock(context).acquire();
	}

	synchronized private static PowerManager.WakeLock getLock(Context context) {
		if (sLockStatic == null) {
			PowerManager mgr = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

			sLockStatic = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, LOCK_NAME_STATIC);
			sLockStatic.setReferenceCounted(true);
		}

		return (sLockStatic);
	}

	@Override
	final protected void onHandleIntent(Intent intent) {
		try {
			doWakefulWork(intent);
		} finally {
			try {
				getLock(this).release();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
