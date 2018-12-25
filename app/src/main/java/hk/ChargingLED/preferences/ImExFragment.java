package hk.ChargingLED.preferences;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import java.util.ArrayList;
import java.util.List;

import hk.ChargingLED.R;
import hk.ChargingLED.helper.PermissionHelper;
import hk.ChargingLED.preferences.ie.ExportActivity;
import hk.ChargingLED.preferences.ie.ImportActivity;

public class ImExFragment extends PreferenceFragment {

	protected List<PermissionHelper> mPermissionHelpers = new ArrayList<>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.import_export);

		PreferenceScreen mImportSettingsView = (PreferenceScreen) findPreference("importSettings");
		mImportSettingsView.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				PermissionHelper permissionHelper = PermissionHelper.permissionHelper(
						PermissionHelper.PermissionType.STORAGE,
						getActivity(),
						new PermissionHelper.PermissionAffirmativeCallback() {
							@Override
							public void onPermissionConfirmed() {
								Intent intent = new Intent(getActivity(), ImportActivity.class);
								intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								startActivity(intent);
							}
						});
				mPermissionHelpers.clear();
				mPermissionHelpers.add(permissionHelper);
				return true;
			}
		});

		PreferenceScreen mExportSettingsView = (PreferenceScreen) findPreference("exportSettings");
		mExportSettingsView.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				PermissionHelper permissionHelper = PermissionHelper.permissionHelper(
						PermissionHelper.PermissionType.STORAGE,
						getActivity(),
						new PermissionHelper.PermissionAffirmativeCallback() {
							@Override
							public void onPermissionConfirmed() {
								Intent intent = new Intent(getActivity(), ExportActivity.class);
								intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								startActivity(intent);
							}
						});
				mPermissionHelpers.clear();
				mPermissionHelpers.add(permissionHelper);
				return true;
			}
		});
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		for(PermissionHelper helper : mPermissionHelpers){
			helper.onRequestPermissionsResult(getActivity(), requestCode, permissions, grantResults);
		}
	}
}
