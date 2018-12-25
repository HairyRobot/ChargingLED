package hk.ChargingLED.preferences;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import hk.ChargingLED.R;

public class ChangelogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.webview);

		WebView wv = findViewById(R.id.webView);
		wv.loadUrl("file:///android_asset/changelog.html");
	}
}
