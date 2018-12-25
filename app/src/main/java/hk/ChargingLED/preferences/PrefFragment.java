package hk.ChargingLED.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.RingtonePreference;

import hk.ChargingLED.Constant;
import hk.ChargingLED.R;
import hk.ChargingLED.dialog.TimeRangePickerDialog;
import hk.ChargingLED.helper.PlayStoreHelper;
import hk.ChargingLED.helper.PreferenceHelper;
import hk.ChargingLED.helper.SilentPeriodHelper;
import hk.ChargingLED.ui.SeekBarPreference;

public class PrefFragment extends PreferenceFragment implements
		SharedPreferences.OnSharedPreferenceChangeListener {
	private static final String TAG = PrefFragment.class.getSimpleName();

	private CheckBoxPreference mMonitorChargingView;
	private PreferenceScreen mModeView;
	private CheckBoxPreference mChargingLedView;
	private CheckBoxPreference mChargingNotificationView;
	private CheckBoxPreference mChargingSoundView;
	private RingtonePreference mPluginSoundView;
	private RingtonePreference mUnplugSoundView;
	private CheckBoxPreference mMonitorBatteryLevelView;
	private SeekBarPreference mBatteryLevelView;
	private RingtonePreference mBatteryLevelReachedSoundView;
	private PreferenceScreen mSilentPeriodView;
	private CheckBoxPreference mDebugModeView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);

		mMonitorChargingView = (CheckBoxPreference) findPreference(PreferenceHelper.PREF_ENABLED_MONITOR_CHARGING);
		mModeView = (PreferenceScreen) findPreference(PreferenceHelper.PREF_MODE);

		mChargingLedView = (CheckBoxPreference) findPreference(PreferenceHelper.PREF_ENABLED_CHARGING_LED);
		mChargingNotificationView = (CheckBoxPreference) findPreference(PreferenceHelper.PREF_ENABLED_CHARGING_NOTIFICATION);
		mChargingSoundView = (CheckBoxPreference) findPreference(PreferenceHelper.PREF_ENABLED_CHARGING_SOUND);
		mPluginSoundView = (RingtonePreference) findPreference(PreferenceHelper.PREF_PLUGIN_SOUND);
		mUnplugSoundView = (RingtonePreference) findPreference(PreferenceHelper.PREF_UNPLUG_SOUND);
		mMonitorBatteryLevelView = (CheckBoxPreference) findPreference(PreferenceHelper.PREF_ENABLED_MONITOR_BATTERY_LEVEL);
		mBatteryLevelView = (SeekBarPreference) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL);
		mBatteryLevelReachedSoundView = (RingtonePreference) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_REACHED_SOUND);
		mSilentPeriodView = (PreferenceScreen) findPreference(PreferenceHelper.PREF_SILENT_PERIOD);
		mSilentPeriodView.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				final TimeRangePickerDialog tpd = TimeRangePickerDialog.newInstance(
						new TimeRangePickerDialog.OnTimeRangeSelectedListener() {
							@Override
							public void onTimeRangeSelected(int startHour, int startMinute,
															int endHour, int endMinute) {
								setSilentPeriodSummary(startHour, startMinute, endHour, endMinute);
								PreferenceHelper.setSilentPeriodStartHour(getActivity(), startHour);
								PreferenceHelper.setSilentPeriodStartMinute(getActivity(), startMinute);
								PreferenceHelper.setSilentPeriodEndHour(getActivity(), endHour);
								PreferenceHelper.setSilentPeriodEndMinute(getActivity(), endMinute);
							}
						}, true,
						PreferenceHelper.getSilentPeriodStartHour(getActivity()),
						PreferenceHelper.getSilentPeriodStartMinute(getActivity()),
						PreferenceHelper.getSilentPeriodEndHour(getActivity()),
						PreferenceHelper.getSilentPeriodEndMinute(getActivity()));
				tpd.show(getActivity().getFragmentManager(), TAG);
				return true;
			}
		});
		mDebugModeView = (CheckBoxPreference) findPreference(PreferenceHelper.PREF_ENABLED_DEBUG_MODE);

		PreferenceScreen versionView = (PreferenceScreen) findPreference(PreferenceHelper.PREF_VERSION);
		versionView.setTitle(getString(R.string.about_t_version, getAppVersionName(getActivity())));
		versionView.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				PlayStoreHelper.viewInPlayStore(getActivity(), Constant.THIS_PACKAGE);
				return true;
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();

		setMonitorChargingSummary(PreferenceHelper.getIsMonitorCharging(getActivity()));
		setModeSummary();

		setChargingLedSummary(PreferenceHelper.getIsChargingLed(getActivity()));
		setChargingNotificationSummary(PreferenceHelper.getIsChargingNotification(getActivity()));
		setChargingSoundSummary(PreferenceHelper.getIsChargingSound(getActivity()));

		setPluginSoundSummary(PreferenceHelper.getPluginSound(getActivity()));
		setUnplugSoundSummary(PreferenceHelper.getUnplugSound(getActivity()));
		setMonitorBatteryLevelSummary(PreferenceHelper.getIsMonitorBatteryLevel(getActivity()));
		setBatteryLevelSummary(PreferenceHelper.getBatteryLevel(getActivity()));
		setBatteryLevelReachedSoundSummary(PreferenceHelper.getBatteryLevelReachedSound(getActivity()));
		setSilentPeriodSummary(PreferenceHelper.getSilentPeriodStartHour(getActivity()),
				PreferenceHelper.getSilentPeriodStartMinute(getActivity()),
				PreferenceHelper.getSilentPeriodEndHour(getActivity()),
				PreferenceHelper.getSilentPeriodEndMinute(getActivity()));
		setDebugModeSummary(PreferenceHelper.getIsDebugMode(getActivity()));

		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onPause() {
		super.onPause();

		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(PreferenceHelper.PREF_ENABLED_MONITOR_CHARGING)) {
			setMonitorChargingSummary(PreferenceHelper.getIsMonitorCharging(getActivity()));
		} else if (key.equals(PreferenceHelper.PREF_ENABLED_CHARGING_LED)) {
			setChargingLedSummary(PreferenceHelper.getIsChargingLed(getActivity()));
			setModeSummary();
		} else if (key.equals(PreferenceHelper.PREF_ENABLED_CHARGING_NOTIFICATION)) {
			setChargingNotificationSummary(PreferenceHelper.getIsChargingNotification(getActivity()));
			setModeSummary();
		} else if (key.equals(PreferenceHelper.PREF_ENABLED_CHARGING_SOUND)) {
			setChargingSoundSummary(PreferenceHelper.getIsChargingSound(getActivity()));
			setModeSummary();
		} else if (key.equals(PreferenceHelper.PREF_ENABLED_MONITOR_BATTERY_LEVEL)) {
			setMonitorBatteryLevelSummary(PreferenceHelper.getIsMonitorBatteryLevel(getActivity()));
		} else if (key.equals(PreferenceHelper.PREF_ENABLED_DEBUG_MODE)) {
			setDebugModeSummary(PreferenceHelper.getIsDebugMode(getActivity()));
		}
	}

	private void setMonitorChargingSummary(boolean flag) {
		if (flag) {
			mMonitorChargingView.setSummary(getString(R.string.settings_s_enabled));
		} else {
			mMonitorChargingView.setSummary(getString(R.string.settings_s_disabled));
		}
		mMonitorChargingView.setChecked(flag);
	}

	private void setModeSummary() {
		String modeSummary = "";

		if (PreferenceHelper.getIsChargingLed(getActivity())) {
			if (modeSummary.length() == 0) {
				modeSummary = "" + getString(R.string.settings_s_led);
			} else {
				modeSummary += " + " + getString(R.string.settings_s_led);
			}
		}

		if (PreferenceHelper.getIsChargingNotification(getActivity())) {
			if (modeSummary.length() == 0) {
				modeSummary = "" + getString(R.string.settings_s_notification);
			} else {
				modeSummary += " + " + getString(R.string.settings_s_notification);
			}
		}

		if (PreferenceHelper.getIsChargingSound(getActivity())) {
			if (modeSummary.length() == 0) {
				modeSummary = "" + getString(R.string.settings_s_sound);
			} else {
				modeSummary += " + " + getString(R.string.settings_s_sound);
			}
		}

		if (modeSummary.length() > 0) {
			mModeView.setSummary(modeSummary);
		} else {
			PreferenceHelper.setIsMonitorCharging(getActivity(), false);
			setMonitorChargingSummary(PreferenceHelper.getIsMonitorCharging(getActivity()));
//			PreferenceHelper.setIsChargingLed(getActivity(), true);
//			setChargingLedSummary(PreferenceHelper.getIsChargingLed(getActivity()));
			PreferenceHelper.setIsChargingNotification(getActivity(), true);
			setChargingNotificationSummary(PreferenceHelper.getIsChargingNotification(getActivity()));
			PreferenceHelper.setIsChargingSound(getActivity(), true);
			setChargingSoundSummary(PreferenceHelper.getIsChargingSound(getActivity()));
			setModeSummary();
		}
	}

	private void setChargingLedSummary(boolean flag) {
		if (flag) {
			mChargingLedView.setSummary(getString(R.string.settings_s_enabled));
		} else {
			mChargingLedView.setSummary(getString(R.string.settings_s_disabled));
		}
		mChargingLedView.setChecked(flag);
	}

	private void setChargingNotificationSummary(boolean flag) {
		if (flag) {
			mChargingNotificationView.setSummary(getString(R.string.settings_s_enabled));
		} else {
			mChargingNotificationView.setSummary(getString(R.string.settings_s_disabled));
		}
		mChargingNotificationView.setChecked(flag);
	}

	private void setChargingSoundSummary(boolean flag) {
		if (flag) {
			mChargingSoundView.setSummary(getString(R.string.settings_s_enabled));
		} else {
			mChargingSoundView.setSummary(getString(R.string.settings_s_disabled));
		}
		mChargingSoundView.setChecked(flag);
	}

	private void setPluginSoundSummary(String sound) {
		if (sound.length() == 0) {
			mPluginSoundView.setSummary(getString(R.string.settings_s_ringtone_silent));
		} else {
			try {
				Ringtone ringtone = RingtoneManager.getRingtone(getActivity(), Uri.parse(sound));
				if (ringtone == null) {
					mPluginSoundView.setSummary(getString(R.string.settings_s_ringtone_silent));
				} else {
					mPluginSoundView.setSummary(ringtone.getTitle(getActivity()));
				}
			} catch (Exception e) {
				e.printStackTrace();
				mPluginSoundView.setSummary("???");
				PreferenceHelper.setPluginSound(getActivity(), "");
			}
		}
	}

	private void setUnplugSoundSummary(String sound) {
		if (sound.length() == 0) {
			mUnplugSoundView.setSummary(getString(R.string.settings_s_ringtone_silent));
		} else {
			try {
				Ringtone ringtone = RingtoneManager.getRingtone(getActivity(), Uri.parse(sound));
				if (ringtone == null) {
					mUnplugSoundView.setSummary(getString(R.string.settings_s_ringtone_silent));
				} else {
					mUnplugSoundView.setSummary(ringtone.getTitle(getActivity()));
				}
			} catch (Exception e) {
				e.printStackTrace();
				mUnplugSoundView.setSummary("???");
				PreferenceHelper.setUnplugSound(getActivity(), "");
			}
		}
	}

	private void setMonitorBatteryLevelSummary(boolean flag) {
		if (flag) {
			mMonitorBatteryLevelView.setSummary(getString(R.string.settings_s_enabled));
		} else {
			mMonitorBatteryLevelView.setSummary(getString(R.string.settings_s_disabled));
		}
		mMonitorBatteryLevelView.setChecked(flag);
	}

	private void setBatteryLevelSummary(int value) {
		mBatteryLevelView.setSummary("" + value + "%");
	}

	private void setBatteryLevelReachedSoundSummary(String sound) {
		if (sound.length() == 0) {
			mBatteryLevelReachedSoundView.setSummary(getString(R.string.settings_s_ringtone_silent));
		} else {
			try {
				Ringtone ringtone = RingtoneManager.getRingtone(getActivity(), Uri.parse(sound));
				if (ringtone == null) {
					mBatteryLevelReachedSoundView.setSummary(getString(R.string.settings_s_ringtone_silent));
				} else {
					// ringtone.getTitle causes SecurityException, if ringtone from external storage
					// https://github.com/consp1racy/android-support-preference/issues/38
					mBatteryLevelReachedSoundView.setSummary(ringtone.getTitle(getActivity()));
				}
			} catch (Exception e) {
				e.printStackTrace();
				mBatteryLevelReachedSoundView.setSummary("???");
				PreferenceHelper.setBatteryLevelReachedSound(getActivity(), "");
			}
		}
	}

	private void setSilentPeriodSummary(int startHour, int startMinute, int endHour, int endMinute) {
		mSilentPeriodView.setSummary(SilentPeriodHelper.formatLeadingZero(startHour)
				+ ":" + SilentPeriodHelper.formatLeadingZero(startMinute)
				+ " - "
				+ SilentPeriodHelper.formatLeadingZero(endHour)
				+ ":" + SilentPeriodHelper.formatLeadingZero(endMinute));
	}

	private void setDebugModeSummary(boolean flag) {
		if (flag) {
			mDebugModeView.setSummary(getString(R.string.settings_s_enabled));
		} else {
			mDebugModeView.setSummary(getString(R.string.settings_s_disabled));
		}
		mDebugModeView.setChecked(flag);
	}

	private static String getAppVersionName(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}
}
