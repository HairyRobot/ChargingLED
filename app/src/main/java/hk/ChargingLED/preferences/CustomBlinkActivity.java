package hk.ChargingLED.preferences;

import android.app.Activity;
import android.os.Bundle;

public class CustomBlinkActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new CustomBlinkFragment()).commit();
	}
}
