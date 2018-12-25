package hk.ChargingLED.preferences;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import hk.ChargingLED.R;
import hk.ChargingLED.dialog.SeekBarDialog;
import hk.ChargingLED.dialog.SeekBarDialog.OnChangedListener;
import hk.ChargingLED.helper.BatteryLevelIntervalHelper;
import hk.ChargingLED.helper.PreferenceHelper;

public class CustomIntervalFragment extends PreferenceFragment {

	private PreferenceScreen mBatteryLevelInterval1View;
	private PreferenceScreen mBatteryLevelInterval2View;
	private PreferenceScreen mBatteryLevelInterval3View;
	private PreferenceScreen mBatteryLevelInterval4View;
	private PreferenceScreen mBatteryLevelInterval5View;
	private PreferenceScreen mBatteryLevelInterval6View;
	private PreferenceScreen mBatteryLevelInterval7View;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.custom_interval);

		mBatteryLevelInterval1View = (PreferenceScreen) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_INTERVAL_1);
		mBatteryLevelInterval1View.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				SeekBarDialog seekBarDialog = new SeekBarDialog();
				seekBarDialog.setListener(new OnChangedListener() {
					@Override
					public void onChanged(int value) {
						PreferenceHelper.setBatteryLevelInterval1(getActivity(), value);
						setBatteryLevelIntervalSummary(getActivity());
					}
				});
				int upperInterval = 100;
				int defaultInterval = PreferenceHelper.getBatteryLevelInterval1(getActivity());
				int lowerInterval = PreferenceHelper.getBatteryLevelInterval2(getActivity()) + 1;
				seekBarDialog.showDialog(getActivity(), "Title 1", defaultInterval, lowerInterval, upperInterval, 1);
				return true;
			}
		});

		mBatteryLevelInterval2View = (PreferenceScreen) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_INTERVAL_2);
		mBatteryLevelInterval2View.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				SeekBarDialog seekBarDialog = new SeekBarDialog();
				seekBarDialog.setListener(new OnChangedListener() {
					@Override
					public void onChanged(int value) {
						PreferenceHelper.setBatteryLevelInterval2(getActivity(), value);
						setBatteryLevelIntervalSummary(getActivity());
					}
				});
				int upperInterval = PreferenceHelper.getBatteryLevelInterval1(getActivity()) - 1;
				int defaultInterval = PreferenceHelper.getBatteryLevelInterval2(getActivity());
				int lowerInterval = PreferenceHelper.getBatteryLevelInterval3(getActivity()) + 1;
				seekBarDialog.showDialog(getActivity(), "Title 2", defaultInterval, lowerInterval, upperInterval, 1);
				return true;
			}
		});

		mBatteryLevelInterval3View = (PreferenceScreen) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_INTERVAL_3);
		mBatteryLevelInterval3View.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				SeekBarDialog seekBarDialog = new SeekBarDialog();
				seekBarDialog.setListener(new OnChangedListener() {
					@Override
					public void onChanged(int value) {
						PreferenceHelper.setBatteryLevelInterval3(getActivity(), value);
						setBatteryLevelIntervalSummary(getActivity());
					}
				});
				int upperInterval = PreferenceHelper.getBatteryLevelInterval2(getActivity()) - 1;
				int defaultInterval = PreferenceHelper.getBatteryLevelInterval3(getActivity());
				int lowerInterval = PreferenceHelper.getBatteryLevelInterval4(getActivity()) + 1;
				seekBarDialog.showDialog(getActivity(), "Title 3", defaultInterval, lowerInterval, upperInterval, 1);
				return true;
			}
		});

		mBatteryLevelInterval4View = (PreferenceScreen) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_INTERVAL_4);
		mBatteryLevelInterval4View.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				SeekBarDialog seekBarDialog = new SeekBarDialog();
				seekBarDialog.setListener(new OnChangedListener() {
					@Override
					public void onChanged(int value) {
						PreferenceHelper.setBatteryLevelInterval4(getActivity(), value);
						setBatteryLevelIntervalSummary(getActivity());
					}
				});
				int upperInterval = PreferenceHelper.getBatteryLevelInterval3(getActivity()) - 1;
				int defaultInterval = PreferenceHelper.getBatteryLevelInterval4(getActivity());
				int lowerInterval = PreferenceHelper.getBatteryLevelInterval5(getActivity()) + 1;
				seekBarDialog.showDialog(getActivity(), "Title 4", defaultInterval, lowerInterval, upperInterval, 1);
				return true;
			}
		});

		mBatteryLevelInterval5View = (PreferenceScreen) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_INTERVAL_5);
		mBatteryLevelInterval5View.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				SeekBarDialog seekBarDialog = new SeekBarDialog();
				seekBarDialog.setListener(new OnChangedListener() {
					@Override
					public void onChanged(int value) {
						PreferenceHelper.setBatteryLevelInterval5(getActivity(), value);
						setBatteryLevelIntervalSummary(getActivity());
					}
				});
				int upperInterval = PreferenceHelper.getBatteryLevelInterval4(getActivity()) - 1;
				int defaultInterval = PreferenceHelper.getBatteryLevelInterval5(getActivity());
				int lowerInterval = PreferenceHelper.getBatteryLevelInterval6(getActivity()) + 1;
				seekBarDialog.showDialog(getActivity(), "Title 5", defaultInterval, lowerInterval, upperInterval, 1);
				return true;
			}
		});

		mBatteryLevelInterval6View = (PreferenceScreen) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_INTERVAL_6);
		mBatteryLevelInterval6View.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				SeekBarDialog seekBarDialog = new SeekBarDialog();
				seekBarDialog.setListener(new OnChangedListener() {
					@Override
					public void onChanged(int value) {
						PreferenceHelper.setBatteryLevelInterval6(getActivity(), value);
						setBatteryLevelIntervalSummary(getActivity());
					}
				});
				int upperInterval = PreferenceHelper.getBatteryLevelInterval5(getActivity()) - 1;
				int defaultInterval = PreferenceHelper.getBatteryLevelInterval6(getActivity());
				int lowerInterval = PreferenceHelper.getBatteryLevelInterval7(getActivity()) + 1;
				seekBarDialog.showDialog(getActivity(), "Title 6", defaultInterval, lowerInterval, upperInterval, 1);
				return true;
			}
		});

		mBatteryLevelInterval7View = (PreferenceScreen) findPreference(PreferenceHelper.PREF_BATTERY_LEVEL_INTERVAL_7);
	}

	@Override
	public void onResume() {
		super.onResume();

		setBatteryLevelIntervalSummary(getActivity());
	}

	@Override
	public void onPause() {
		super.onPause();

		BatteryLevelIntervalHelper.checkValidInterval(getActivity());
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
		if (preference.getTitle().equals(getString(R.string.battery_level_t_default_battery_level_interval))) {
			BatteryLevelIntervalHelper.resetDefaultInterval(getActivity());
			setBatteryLevelIntervalSummary(getActivity());
			return true;
		}

		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}

	private void setBatteryLevelIntervalSummary(Context context) {
		setBatteryLevelInterval1Summary(context);
		setBatteryLevelInterval2Summary(context);
		setBatteryLevelInterval3Summary(context);
		setBatteryLevelInterval4Summary(context);
		setBatteryLevelInterval5Summary(context);
		setBatteryLevelInterval6Summary(context);
		setBatteryLevelInterval7Summary(context);
	}

	private void setBatteryLevelInterval1Summary(Context context) {
		int lowerLevel = PreferenceHelper.getBatteryLevelInterval1(context);
		int upperLevel = 100;
		if (lowerLevel == upperLevel) {
			mBatteryLevelInterval1View.setTitle("(" + lowerLevel + "%)");
		} else {
			mBatteryLevelInterval1View.setTitle("(" + lowerLevel + "%) - " + upperLevel + "%");
		}
	}

	private void setBatteryLevelInterval2Summary(Context context) {
		int lowerLevel = PreferenceHelper.getBatteryLevelInterval2(context);
		int upperLevel = PreferenceHelper.getBatteryLevelInterval1(context) - 1;
		if (lowerLevel == upperLevel) {
			mBatteryLevelInterval2View.setTitle("(" + lowerLevel + "%)");
		} else {
			mBatteryLevelInterval2View.setTitle("(" + lowerLevel + "%) - " + upperLevel + "%");
		}
	}

	private void setBatteryLevelInterval3Summary(Context context) {
		int lowerLevel = PreferenceHelper.getBatteryLevelInterval3(context);
		int upperLevel = PreferenceHelper.getBatteryLevelInterval2(context) - 1;
		if (lowerLevel == upperLevel) {
			mBatteryLevelInterval3View.setTitle("(" + lowerLevel + "%)");
		} else {
			mBatteryLevelInterval3View.setTitle("(" + lowerLevel + "%) - " + upperLevel + "%");
		}
	}

	private void setBatteryLevelInterval4Summary(Context context) {
		int lowerLevel = PreferenceHelper.getBatteryLevelInterval4(context);
		int upperLevel = PreferenceHelper.getBatteryLevelInterval3(context) - 1;
		if (lowerLevel == upperLevel) {
			mBatteryLevelInterval4View.setTitle("(" + lowerLevel + "%)");
		} else {
			mBatteryLevelInterval4View.setTitle("(" + lowerLevel + "%) - " + upperLevel + "%");
		}
	}

	private void setBatteryLevelInterval5Summary(Context context) {
		int lowerLevel = PreferenceHelper.getBatteryLevelInterval5(context);
		int upperLevel = PreferenceHelper.getBatteryLevelInterval4(context) - 1;
		if (lowerLevel == upperLevel) {
			mBatteryLevelInterval5View.setTitle("(" + lowerLevel + "%)");
		} else {
			mBatteryLevelInterval5View.setTitle("(" + lowerLevel + "%) - " + upperLevel + "%");
		}
	}

	private void setBatteryLevelInterval6Summary(Context context) {
		int lowerLevel = PreferenceHelper.getBatteryLevelInterval6(context);
		int upperLevel = PreferenceHelper.getBatteryLevelInterval5(context) - 1;
		if (lowerLevel == upperLevel) {
			mBatteryLevelInterval6View.setTitle("(" + lowerLevel + "%)");
		} else {
			mBatteryLevelInterval6View.setTitle("(" + lowerLevel + "%) - " + upperLevel + "%");
		}
	}

	private void setBatteryLevelInterval7Summary(Context context) {
		int upperLevel = PreferenceHelper.getBatteryLevelInterval6(context);
		mBatteryLevelInterval7View.setTitle(getString(R.string.battery_level_t_range_below, "" + upperLevel + "%"));
	}
}
