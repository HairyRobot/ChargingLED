package hk.ChargingLED.helper;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import hk.ChargingLED.service.JobAlarmService;
import hk.ChargingLED.service.JobPowerConnectedService;

public class ScheduleJobHelper {
	private static final String TAG = ScheduleJobHelper.class.getSimpleName();

	private static final int JOB_ID_POWER_CONNECTED = 101;
	private static final int JOB_ID_ALARM = 102;

	/**
	 * @param context The current context.
	 * @param duration The minimumLatency (milliseconds)
	 */
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public static void scheduleJobPowerConnected(Context context, long duration) {
		Log.i(TAG, "scheduleJobPowerConnected");
		ComponentName componentName = new ComponentName(context, JobPowerConnectedService.class);
		JobInfo.Builder builder = new JobInfo.Builder(JOB_ID_POWER_CONNECTED, componentName);
		if (duration >= 0) {
			builder.setMinimumLatency(duration);
		}
		builder.setRequiresCharging(true);
		builder.setRequiresDeviceIdle(false);
		builder.setPersisted(true);
		JobInfo jobInfo = builder.build();
		JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
		jobScheduler.schedule(jobInfo);
	}

	/**
	 * @param context The current context.
	 * @param duration The minimumLatency/overrideDeadline (milliseconds)
	 */
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public static void scheduleJobAlarm(Context context, long duration) {
		Log.i(TAG, "scheduleJobAlarm");
		ComponentName componentName = new ComponentName(context, JobAlarmService.class);
		JobInfo.Builder builder = new JobInfo.Builder(JOB_ID_ALARM, componentName);
		if (duration < 0) {
			duration = 1000 * 60 * 5;    // 5 minutes
		}
		builder.setMinimumLatency(duration);
		builder.setOverrideDeadline(duration);
		builder.setRequiresDeviceIdle(false);
		builder.setPersisted(false);
		JobInfo jobInfo = builder.build();
		JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
		jobScheduler.schedule(jobInfo);
	}
}
