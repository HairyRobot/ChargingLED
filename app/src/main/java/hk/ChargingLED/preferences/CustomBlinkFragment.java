package hk.ChargingLED.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import hk.ChargingLED.R;
import hk.ChargingLED.helper.PreferenceHelper;

public class CustomBlinkFragment extends PreferenceFragment implements
		SharedPreferences.OnSharedPreferenceChangeListener {

	private CheckBoxPreference mEnabledBlink1View;
	private ListPreference mBlinkOn1View;
	private ListPreference mBlinkOff1View;
	private CheckBoxPreference mEnabledBlink2View;
	private ListPreference mBlinkOn2View;
	private ListPreference mBlinkOff2View;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.custom_blink);

		mEnabledBlink1View = (CheckBoxPreference) findPreference(PreferenceHelper.PREF_ENABLED_BLINKING_1);
		mBlinkOn1View = (ListPreference) findPreference(PreferenceHelper.PREF_BLINK_ON_1);
		mBlinkOff1View = (ListPreference) findPreference(PreferenceHelper.PREF_BLINK_OFF_1);
		mEnabledBlink2View = (CheckBoxPreference) findPreference(PreferenceHelper.PREF_ENABLED_BLINKING_2);
		mBlinkOn2View = (ListPreference) findPreference(PreferenceHelper.PREF_BLINK_ON_2);
		mBlinkOff2View = (ListPreference) findPreference(PreferenceHelper.PREF_BLINK_OFF_2);
	}

	@Override
	public void onResume() {
		super.onResume();

		setEnabledBlinkTitle();
		setBlinkIntervalSummary();

		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onPause() {
		super.onPause();

		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(PreferenceHelper.PREF_ENABLED_BLINKING_1)) {
			if (PreferenceHelper.getEnabledBlink1(getActivity())) {
				mEnabledBlink1View.setSummary(getString(R.string.blink_rate_s_range_enabled));
			} else {
				mEnabledBlink1View.setSummary(getString(R.string.blink_rate_s_range_disabled));
			}
		} else if (key.equals(PreferenceHelper.PREF_BLINK_ON_1)) {
			mBlinkOn1View.setSummary("" + PreferenceHelper.getBlinkOn1Interval(getActivity()) + " ms");
		} else if (key.equals(PreferenceHelper.PREF_BLINK_OFF_1)) {
			mBlinkOff1View.setSummary("" + PreferenceHelper.getBlinkOff1Interval(getActivity()) + " ms");
		} else if (key.equals(PreferenceHelper.PREF_ENABLED_BLINKING_2)) {
			if (PreferenceHelper.getEnabledBlink2(getActivity())) {
				mEnabledBlink2View.setSummary(getString(R.string.blink_rate_s_range_enabled));
			} else {
				mEnabledBlink2View.setSummary(getString(R.string.blink_rate_s_range_disabled));
			}
		} else if (key.equals(PreferenceHelper.PREF_BLINK_ON_2)) {
			mBlinkOn2View.setSummary("" + PreferenceHelper.getBlinkOn2Interval(getActivity()) + " ms");
		} else if (key.equals(PreferenceHelper.PREF_BLINK_OFF_2)) {
			mBlinkOff2View.setSummary("" + PreferenceHelper.getBlinkOff2Interval(getActivity()) + " ms");
		}
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
		if (preference.getTitle().equals(getString(R.string.blink_rate_t_default_blink_rate))) {
			PreferenceHelper.setEnabledBlink1(getActivity(), PreferenceHelper.PREF_ENABLED_BLINKING_1_DEFAULT);
			PreferenceHelper.setBlinkOn1Interval(getActivity(), PreferenceHelper.PREF_BLINK_ON_1_DEFAULT);
			PreferenceHelper.setBlinkOff1Interval(getActivity(), PreferenceHelper.PREF_BLINK_OFF_1_DEFAULT);
			PreferenceHelper.setEnabledBlink2(getActivity(), PreferenceHelper.PREF_ENABLED_BLINKING_2_DEFAULT);
			PreferenceHelper.setBlinkOn2Interval(getActivity(), PreferenceHelper.PREF_BLINK_ON_2_DEFAULT);
			PreferenceHelper.setBlinkOff2Interval(getActivity(), PreferenceHelper.PREF_BLINK_OFF_2_DEFAULT);

			mEnabledBlink1View.setChecked(PreferenceHelper.PREF_ENABLED_BLINKING_1_DEFAULT);
			mEnabledBlink2View.setChecked(PreferenceHelper.PREF_ENABLED_BLINKING_2_DEFAULT);
			setBlinkIntervalSummary();
			return true;
		}

		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}

	private void setEnabledBlinkTitle() {
		int lowerLevel = PreferenceHelper.getBatteryLevelInterval1(getActivity());
		int upperLevel = 100;
		if (lowerLevel == upperLevel) {
			mEnabledBlink1View.setTitle("" + lowerLevel + "%");
		} else {
			mEnabledBlink1View.setTitle("" + lowerLevel + "% - " + upperLevel + "%");
		}

		upperLevel = PreferenceHelper.getBatteryLevelInterval1(getActivity());
		mEnabledBlink2View.setTitle(getString(R.string.battery_level_t_range_below, "" + upperLevel + "%"));
	}

	private void setBlinkIntervalSummary() {
		if (PreferenceHelper.getEnabledBlink1(getActivity())) {
			mEnabledBlink1View.setSummary(getString(R.string.blink_rate_s_range_enabled));
		} else {
			mEnabledBlink1View.setSummary(getString(R.string.blink_rate_s_range_disabled));
		}

		mBlinkOn1View.setSummary("" + PreferenceHelper.getBlinkOn1Interval(getActivity()) + " ms");
		mBlinkOff1View.setSummary("" + PreferenceHelper.getBlinkOff1Interval(getActivity()) + " ms");

		if (PreferenceHelper.getEnabledBlink2(getActivity())) {
			mEnabledBlink2View.setSummary(getString(R.string.blink_rate_s_range_enabled));
		} else {
			mEnabledBlink2View.setSummary(getString(R.string.blink_rate_s_range_disabled));
		}

		mBlinkOn2View.setSummary("" + PreferenceHelper.getBlinkOn2Interval(getActivity()) + " ms");
		mBlinkOff2View.setSummary("" + PreferenceHelper.getBlinkOff2Interval(getActivity()) + " ms");
	}
}
