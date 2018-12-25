package hk.ChargingLED.preferences;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import hk.ChargingLED.R;
import hk.ChargingLED.helper.PreferenceHelper;

import net.margaritov.preference.colorpicker.ColorPickerPreference;

public class CustomColorFragment extends PreferenceFragment {

	private ColorPickerPreference mBatteryLevelRange1View;
	private ColorPickerPreference mBatteryLevelRange2View;
	private ColorPickerPreference mBatteryLevelRange3View;
	private ColorPickerPreference mBatteryLevelRange4View;
	private ColorPickerPreference mBatteryLevelRange5View;
	private ColorPickerPreference mBatteryLevelRange6View;
	private ColorPickerPreference mBatteryLevelRange7View;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.custom_color);

		mBatteryLevelRange1View = (ColorPickerPreference) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_1);
		mBatteryLevelRange1View.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				preference.setSummary(ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));
				return true;
			}
		});
		mBatteryLevelRange1View.setAlphaSliderEnabled(false);

		mBatteryLevelRange2View = (ColorPickerPreference) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_2);
		mBatteryLevelRange2View.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				preference.setSummary(ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));
				return true;
			}
		});
		mBatteryLevelRange2View.setAlphaSliderEnabled(false);

		mBatteryLevelRange3View = (ColorPickerPreference) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_3);
		mBatteryLevelRange3View.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				preference.setSummary(ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));
				return true;
			}
		});
		mBatteryLevelRange3View.setAlphaSliderEnabled(false);

		mBatteryLevelRange4View = (ColorPickerPreference) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_4);
		mBatteryLevelRange4View.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				preference.setSummary(ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));
				return true;
			}
		});
		mBatteryLevelRange4View.setAlphaSliderEnabled(false);

		mBatteryLevelRange5View = (ColorPickerPreference) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_5);
		mBatteryLevelRange5View.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				preference.setSummary(ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));
				return true;
			}
		});
		mBatteryLevelRange5View.setAlphaSliderEnabled(false);

		mBatteryLevelRange6View = (ColorPickerPreference) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_6);
		mBatteryLevelRange6View.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				preference.setSummary(ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));
				return true;
			}
		});
		mBatteryLevelRange6View.setAlphaSliderEnabled(false);

		mBatteryLevelRange7View = (ColorPickerPreference) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_7);
		mBatteryLevelRange7View.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				preference.setSummary(ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));
				return true;
			}
		});
		mBatteryLevelRange7View.setAlphaSliderEnabled(false);
	}

	@Override
	public void onResume() {
		super.onResume();

		setBatteryLevelTitle(getActivity());
		setBatteryLevelSummary();
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
		if (preference.getTitle().equals(getString(R.string.battery_level_t_default_battery_level_color))) {
			PreferenceHelper.setBatteryLevelRange1Color(getActivity(), PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_1_DEFAULT);
			PreferenceHelper.setBatteryLevelRange2Color(getActivity(), PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_2_DEFAULT);
			PreferenceHelper.setBatteryLevelRange3Color(getActivity(), PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_3_DEFAULT);
			PreferenceHelper.setBatteryLevelRange4Color(getActivity(), PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_4_DEFAULT);
			PreferenceHelper.setBatteryLevelRange5Color(getActivity(), PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_5_DEFAULT);
			PreferenceHelper.setBatteryLevelRange6Color(getActivity(), PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_6_DEFAULT);
			PreferenceHelper.setBatteryLevelRange7Color(getActivity(), PreferenceHelper.PREF_BATTERY_LEVEL_RANGE_7_DEFAULT);
			setBatteryLevelSummary();
			return true;
		}

		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}

	private void setBatteryLevelTitle(Context context) {
		int lowerLevel;
		int upperLevel;

		lowerLevel = PreferenceHelper.getBatteryLevelInterval1(context);
		upperLevel = 100;
		if (lowerLevel == upperLevel) {
			mBatteryLevelRange1View.setTitle("" + lowerLevel + "%");
		} else {
			mBatteryLevelRange1View.setTitle("" + lowerLevel + "% - " + upperLevel + "%");
		}

		lowerLevel = PreferenceHelper.getBatteryLevelInterval2(context);
		upperLevel = PreferenceHelper.getBatteryLevelInterval1(context) - 1;
		if (lowerLevel == upperLevel) {
			mBatteryLevelRange2View.setTitle("" + lowerLevel + "%");
		} else {
			mBatteryLevelRange2View.setTitle("" + lowerLevel + "% - " + upperLevel + "%");
		}

		lowerLevel = PreferenceHelper.getBatteryLevelInterval3(context);
		upperLevel = PreferenceHelper.getBatteryLevelInterval2(context) - 1;
		if (lowerLevel == upperLevel) {
			mBatteryLevelRange3View.setTitle("" + lowerLevel + "%");
		} else {
			mBatteryLevelRange3View.setTitle("" + lowerLevel + "% - " + upperLevel + "%");
		}

		lowerLevel = PreferenceHelper.getBatteryLevelInterval4(context);
		upperLevel = PreferenceHelper.getBatteryLevelInterval3(context) - 1;
		if (lowerLevel == upperLevel) {
			mBatteryLevelRange4View.setTitle("" + lowerLevel + "%");
		} else {
			mBatteryLevelRange4View.setTitle("" + lowerLevel + "% - " + upperLevel + "%");
		}

		lowerLevel = PreferenceHelper.getBatteryLevelInterval5(context);
		upperLevel = PreferenceHelper.getBatteryLevelInterval4(context) - 1;
		if (lowerLevel == upperLevel) {
			mBatteryLevelRange5View.setTitle("" + lowerLevel + "%");
		} else {
			mBatteryLevelRange5View.setTitle("" + lowerLevel + "% - " + upperLevel + "%");
		}

		lowerLevel = PreferenceHelper.getBatteryLevelInterval6(context);
		upperLevel = PreferenceHelper.getBatteryLevelInterval5(context) - 1;
		if (lowerLevel == upperLevel) {
			mBatteryLevelRange6View.setTitle("" + lowerLevel + "%");
		} else {
			mBatteryLevelRange6View.setTitle("" + lowerLevel + "% - " + upperLevel + "%");
		}

		upperLevel = PreferenceHelper.getBatteryLevelInterval6(context);
		mBatteryLevelRange7View.setTitle(getString(R.string.battery_level_t_range_below, "" + upperLevel + "%"));
	}

	private void setBatteryLevelSummary() {
		mBatteryLevelRange1View.setSummary(ColorPickerPreference.convertToARGB(
				Integer.valueOf(String.valueOf(PreferenceHelper.getBatteryLevelRange1Color(getActivity())))
		));

		mBatteryLevelRange2View.setSummary(ColorPickerPreference.convertToARGB(
				Integer.valueOf(String.valueOf(PreferenceHelper.getBatteryLevelRange2Color(getActivity())))
		));

		mBatteryLevelRange3View.setSummary(ColorPickerPreference.convertToARGB(
				Integer.valueOf(String.valueOf(PreferenceHelper.getBatteryLevelRange3Color(getActivity())))
		));

		mBatteryLevelRange4View.setSummary(ColorPickerPreference.convertToARGB(
				Integer.valueOf(String.valueOf(PreferenceHelper.getBatteryLevelRange4Color(getActivity())))
		));

		mBatteryLevelRange5View.setSummary(ColorPickerPreference.convertToARGB(
				Integer.valueOf(String.valueOf(PreferenceHelper.getBatteryLevelRange5Color(getActivity())))
		));

		mBatteryLevelRange6View.setSummary(ColorPickerPreference.convertToARGB(
				Integer.valueOf(String.valueOf(PreferenceHelper.getBatteryLevelRange6Color(getActivity())))
		));

		mBatteryLevelRange7View.setSummary(ColorPickerPreference.convertToARGB(
				Integer.valueOf(String.valueOf(PreferenceHelper.getBatteryLevelRange7Color(getActivity())))
		));
	}
}
