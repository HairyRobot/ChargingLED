package hk.ChargingLED.preferences;

import android.app.Activity;
import android.os.Bundle;

public class ImExActivity extends Activity {

	private ImExFragment mImExFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mImExFragment = new ImExFragment();
		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, mImExFragment).commit();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		mImExFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
}
