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

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobAlarmService extends JobService {
	private static final String TAG = JobAlarmService.class.getSimpleName();

	private BgJobExecutor mBgJobExecutor;

	@SuppressLint("StaticFieldLeak")
	@Override
	public boolean onStartJob(final JobParameters params) {
		Log.i(TAG, "onStartJob");
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
			AlarmService.acquireStaticLock(context);
			Intent i = new Intent(context, AlarmService.class);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    // API-26
				context.startForegroundService(i);
			} else {
				context.startService(i);
			}
			return null;
		}
	}
}
