package hk.ChargingLED.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.lang.ref.WeakReference;

import hk.ChargingLED.helper.PreferenceHelper;
import hk.ChargingLED.helper.ScheduleJobHelper;
import hk.ChargingLED.helper.SoundHelper;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobPowerConnectedService extends JobService {
	private static final String TAG = JobPowerConnectedService.class.getSimpleName();

	private static final long DELAY = 1000 * 60;    // 1 minutes

	private BgJobExecutor mBgJobExecutor;

	@SuppressLint("StaticFieldLeak")
	@Override
	public boolean onStartJob(final JobParameters params) {
		Log.i(TAG, "onStartJob");
		if (!PreferenceHelper.getIsDisclaimerAccepted(this)) {
			ScheduleJobHelper.scheduleJobPowerConnected(this, DELAY);
			return false;
		}
		if (!PreferenceHelper.getIsMonitorCharging(this)) {
			ScheduleJobHelper.scheduleJobPowerConnected(this, DELAY);
			return false;
		}
		if (PreferenceHelper.getIsDebugMode(this)) {
			SoundHelper.onPowerConnectedDebug(this);
		}
		if (PreferenceHelper.getIsChargingSound(this)) {
			SoundHelper.onPowerConnectedSound(this);
		}
		mBgJobExecutor = new BgJobExecutor(this) {
			@Override
			protected void onPostExecute(Void aVoid) {
				Log.i(TAG, "onPostExecute");
				super.onPostExecute(aVoid);
				jobFinished(params, false);
			}
		};
		mBgJobExecutor.execute();
		return true;
	}

	@Override
	public boolean onStopJob(JobParameters params) {
		mBgJobExecutor.cancel(true);
		return false;
	}

	@SuppressLint("StaticFieldLeak")
	private class BgJobExecutor extends AsyncTask<Void, Void, Void> {

		private WeakReference<Context> mContextRef;

		BgJobExecutor(Context context) {
			mContextRef = new WeakReference<>(context);
		}

		@Override
		protected Void doInBackground(Void... voids) {
			Context context = mContextRef.get();
			if (context == null) return null;
			Intent serviceIntent = new Intent(context, ChargingLedService.class);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
				context.startForegroundService(serviceIntent);
			} else {
				context.startService(serviceIntent);
			}
			return null;
		}
	}
}
