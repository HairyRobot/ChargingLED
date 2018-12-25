package hk.ChargingLED.preferences;

import android.app.Activity;
import android.os.Bundle;

public class CustomIntervalActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new CustomIntervalFragment()).commit();
	}
}
