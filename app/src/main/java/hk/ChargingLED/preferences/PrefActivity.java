package hk.ChargingLED.preferences;

import android.app.Activity;
import android.os.Bundle;

import hk.ChargingLED.helper.IgnoreBatteryOptimizationHelper;

public class PrefActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new PrefFragment()).commit();

		new IgnoreBatteryOptimizationHelper().showDialog(this);
	}
}
