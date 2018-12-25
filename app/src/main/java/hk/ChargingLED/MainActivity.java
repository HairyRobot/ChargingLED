package hk.ChargingLED;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;

import hk.ChargingLED.helper.PreferenceHelper;
import hk.ChargingLED.preferences.PrefActivity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (PreferenceHelper.getIsDisclaimerAccepted(this)) {
			startChargingLED();
		} else {
			showDisclaimerDialog();
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		finish();
	}

	private void startChargingLED() {
		Intent intent = new Intent(MainActivity.this, PrefActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		MainActivity.this.startActivity(intent);
	}

	private void acceptDisclaimer() {
		PreferenceHelper.setIsDisclaimerAccepted(this, true);
	}

	private void showDisclaimerDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setTitle(R.string.disclaimer_title);
		builder.setMessage(R.string.disclaimer_text);
		builder.setPositiveButton(R.string.btn_accept,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						acceptDisclaimer();
						startChargingLED();
					}
				}
		);
		builder.setNegativeButton(R.string.btn_reject,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						finish();
					}
				}
		);
		// Capture the BACK key as cancel.
		builder.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				finish();
			}
		});
		builder.create();
		builder.show();
	}
}
