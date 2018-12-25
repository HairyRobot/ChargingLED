package hk.ChargingLED.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TimePicker;

import hk.ChargingLED.R;

public class TimeRangePickerDialog extends DialogFragment {
//	private static final String TAG = TimeRangePickerDialog.class.getSimpleName();

	TabHost mTabHost;
	TimePicker mStartTimePicker, mEndTimePicker;
	OnTimeRangeSelectedListener mOnTimeRangeSelectedListener;
	boolean mIs24HourMode;
	int mStartHour, mStartMinute;
	int mEndHour, mEndMinute;

	public static TimeRangePickerDialog newInstance(OnTimeRangeSelectedListener callback,
													boolean is24HourMode,
													int startHour, int startMinute,
													int endHour, int endMinute) {
		TimeRangePickerDialog dialog = new TimeRangePickerDialog();
		dialog.initialize(callback, is24HourMode, startHour, startMinute, endHour, endMinute);
		return dialog;
	}

	public void initialize(OnTimeRangeSelectedListener callback, boolean is24HourMode,
						   int startHour, int startMinute, int endHour, int endMinute) {
		mOnTimeRangeSelectedListener = callback;
		mIs24HourMode = is24HourMode;
		mStartHour = startHour;
		mStartMinute = startMinute;
		mEndHour = endHour;
		mEndMinute = endMinute;
	}

	public interface OnTimeRangeSelectedListener {
		void onTimeRangeSelected(int startHour, int startMinute, int endHour, int endMinute);
	}

	public void setOnTimeRangeSetListener(OnTimeRangeSelectedListener callback) {
		mOnTimeRangeSelectedListener = callback;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		setCancelable(true);
		setRetainInstance(true);

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View rootView = inflater.inflate(R.layout.timerange_picker_dialog, null);

		mStartTimePicker = rootView.findViewById(R.id.startTimePicker);
		mStartTimePicker.setIs24HourView(mIs24HourMode);
		mStartTimePicker.setCurrentHour(mStartHour);
		mStartTimePicker.setCurrentMinute(mStartMinute);

		mEndTimePicker = rootView.findViewById(R.id.endTimePicker);
		mEndTimePicker.setIs24HourView(mIs24HourMode);
		mEndTimePicker.setCurrentHour(mEndHour);
		mEndTimePicker.setCurrentMinute(mEndMinute);

		mTabHost = rootView.findViewById(R.id.tabHost);
		mTabHost.setup();
		TabHost.TabSpec tabPage1 = mTabHost.newTabSpec("one");
		tabPage1.setContent(R.id.startTimeGroup);
		tabPage1.setIndicator(getResources().getString(R.string.start_time));

		TabHost.TabSpec tabPage2 = mTabHost.newTabSpec("two");
		tabPage2.setContent(R.id.endTimeGroup);
		tabPage2.setIndicator(getResources().getString(R.string.end_time));

		mTabHost.addTab(tabPage1);
		mTabHost.addTab(tabPage2);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(rootView);
		builder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				int startHour = mStartTimePicker.getCurrentHour();
				int startMinute = mStartTimePicker.getCurrentMinute();
				int endHour = mEndTimePicker.getCurrentHour();
				int endMinute = mEndTimePicker.getCurrentMinute();
				mOnTimeRangeSelectedListener.onTimeRangeSelected(startHour, startMinute, endHour, endMinute);
			}
		});
		builder.setNegativeButton(R.string.btn_cancel, null);
		return builder.create();
	}

	@Override
	public void onStart() {
		super.onStart();

		// safety check
		if (getDialog() == null) {
			return;
		}
		getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.WRAP_CONTENT);
	}
}
