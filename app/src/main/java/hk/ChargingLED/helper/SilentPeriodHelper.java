package hk.ChargingLED.helper;

import android.content.Context;
import android.util.Log;

import java.util.Calendar;

public final class SilentPeriodHelper {
	private static final String TAG = SilentPeriodHelper.class.getSimpleName();

	public static String formatLeadingZero(int value) {
		return (value < 10 ? "0" : "") + value;
	}

	public static boolean isInSilentPeriod(Context context, long time) {
		if (!PreferenceHelper.getIsEnabledSilentPeriod(context)) {
			return false;
		}
		int startHour = PreferenceHelper.getSilentPeriodStartHour(context);
		int startMinute = PreferenceHelper.getSilentPeriodStartMinute(context);
		int endHour = PreferenceHelper.getSilentPeriodEndHour(context);
		int endMinute = PreferenceHelper.getSilentPeriodEndMinute(context);
		Log.d(TAG, "start=" + startHour + ":" + startMinute);
		Log.d(TAG, "end=" + endHour + ":" + endMinute);
		Calendar startCal = convertToCalendar(startHour, startMinute);
		Calendar endCal = convertToCalendar(endHour, endMinute);
		if (endCal.getTimeInMillis() < startCal.getTimeInMillis()) {
			endCal.add(Calendar.DAY_OF_MONTH, 1);
		}
		boolean silent = startCal.getTimeInMillis() <= time && time <= endCal.getTimeInMillis();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss", Locale.ENGLISH);
//		String startDate = formatter.format(startCal.getTimeInMillis());
//		String timeDate = formatter.format(time);
//		String endDate = formatter.format(endCal.getTimeInMillis());
//		Log.d(TAG, " start=" + startCal.getTimeInMillis() + "   " + startDate);
//		Log.d(TAG, "  time=" + time + "   " + timeDate);
//		Log.d(TAG, "   end=" + endCal.getTimeInMillis() + "   " + endDate);
		Log.d(TAG, "silent=" + silent);
		return silent;
	}

	/**
	 * @param hour   (00-23)
	 * @param minute (00-59)
	 */
	private static Calendar convertToCalendar(int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
}
