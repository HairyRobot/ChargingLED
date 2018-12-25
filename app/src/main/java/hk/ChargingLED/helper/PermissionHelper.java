package hk.ChargingLED.helper;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import hk.ChargingLED.R;

public class PermissionHelper {
	private static final String TAG = PermissionHelper.class.getSimpleName();

	public interface PermissionAffirmativeCallback {
		void onPermissionConfirmed();
	}

	private static final String SHARED_PREF_NAME = "permissionRequestedPrefs";
	private static final int SHARED_PREF_MODE = Context.MODE_PRIVATE;

	public enum PermissionType {
		STORAGE
	}

	private static final int PERMISSIONS_REQUEST_STORAGE = 200;

	private PermissionAffirmativeCallback mAffirmativeCallback;
	private Activity mActivity;
	//parameters
	private String mManifestPermission;
	private int mRequestCode;
	private String mDeniedMsg;
	private String mDeniedNeverAskTitle;
	private String mDeniedNeverAskMsg;

	public static PermissionHelper permissionHelper(PermissionType type,
													Activity activity,
													PermissionAffirmativeCallback callback) {
		return new PermissionHelper(type, activity, callback);
	}

	private PermissionHelper(PermissionType type,
							 Activity activity,
							 PermissionAffirmativeCallback callback) {
		if (PermissionType.STORAGE == type) {
			mManifestPermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
			mRequestCode = PERMISSIONS_REQUEST_STORAGE;
			mDeniedMsg = activity.getString(R.string.alert_msg_storage_denied);
			mDeniedNeverAskTitle = activity.getString(R.string.alert_title_storage_denied_never_ask);
			mDeniedNeverAskMsg = activity.getString(R.string.alert_msg_storage_denied_never_ask);
		}
		mActivity = activity;
		mAffirmativeCallback = callback;

		checkPermission(activity, type);
	}

	private void checkPermission(Context context, PermissionType permissionType) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			int permissionStatus = context.checkSelfPermission(mManifestPermission);
			Log.d(TAG, mManifestPermission);
			Log.d(TAG, "permissionStatus (0=GRANTED): " + permissionStatus);
			if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
				boolean shouldShowRationale = ((Activity) context).shouldShowRequestPermissionRationale(mManifestPermission);
				Log.d(TAG, "shouldShowRequestPermissionRationale: " + String.valueOf(shouldShowRationale));
				if (shouldShowRationale) {
					// No explanation needed, we can request the permission.
					Log.d(TAG, "should show rationale, so here now, prompt ask again");
					//request window
					requestPermission(context);
				} else {
					//TWO CASE:
					//1. first time - system up - //request window
					if (!hasPermissionBeenRequested(mActivity, permissionType.toString())) {
						setPermissionBeenRequested(mActivity, permissionType.toString(), true);
						Log.d(TAG, "rationale false, but is first time, so show request window");
						requestPermission(context);
					} else {
						//2. second time - user denied with never ask - goto settings
						Log.d(TAG, "user denied with never ask again, need show settings chooser");
						promptSettings();
					}
				}
				return;
			}
		}

		if (mAffirmativeCallback != null) {
			mAffirmativeCallback.onPermissionConfirmed();
		}
	}

	@TargetApi(Build.VERSION_CODES.M)
	private void requestPermission(Context context) {
		((Activity) context).requestPermissions(new String[]{mManifestPermission}, mRequestCode);
	}

	@TargetApi(Build.VERSION_CODES.M)
	public void onRequestPermissionsResult(final Context context, int requestCode,
										   String permissions[], int[] grantResults) {
		Log.i(TAG, "onRequestPermissionsResult");
		if (requestCode == mRequestCode) {
			Log.d(TAG, mManifestPermission);
			boolean hasResult = grantResults.length > 0;
			if (hasResult) {
				if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					//user accepted, permission granted
					Log.d(TAG, "Permission granted");
					if (mAffirmativeCallback != null) {
						mAffirmativeCallback.onPermissionConfirmed();
					}
				} else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
					//http://stackoverflow.com/questions/30719047/android-m-check-runtime-permission-how-to-determine-if-the-user-checked-nev
					boolean shouldShowRationale = ((Activity) context).shouldShowRequestPermissionRationale(mManifestPermission);
					Log.d(TAG, "onRequestPermissionsResult - shouldShowRequestPermissionRationale: " + String.valueOf(shouldShowRationale));
					if (shouldShowRationale) {
						//true,
						AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
						builder.setTitle(R.string.alert_title_permission_denied);
						builder.setMessage(mDeniedMsg);
						builder.setPositiveButton(R.string.btn_confirm, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						});
						builder.setNegativeButton(R.string.btn_retry, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
								requestPermission(context);
							}
						});
						builder.show();
					} else {
						promptSettings();
					}
				}
			}
		}
	}

	private void promptSettings() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
		builder.setTitle(mDeniedNeverAskTitle);
		builder.setMessage(mDeniedNeverAskMsg);
		builder.setPositiveButton(R.string.btn_settings, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				gotoSettings();
			}
		});
		builder.setNegativeButton(R.string.btn_cancel, null);
		builder.show();
	}

	private void gotoSettings() {
		Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
				Uri.parse("package:" + mActivity.getPackageName()));
		myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
		myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mActivity.startActivity(myAppSettings);
	}

	private boolean hasPermissionBeenRequested(Context context, String key) {
		SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, SHARED_PREF_MODE);
		boolean value;
		try {
			value = sharedPref.getBoolean(key, false);
		} catch (Exception e) {
			value = false;
		}
		return value;
	}

	private void setPermissionBeenRequested(Context context, String key, boolean flag) {
		SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, SHARED_PREF_MODE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putBoolean(key, flag);
		editor.apply();
	}
}
