package hk.ChargingLED.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import hk.ChargingLED.R;

public final class SeekBarDialog {

	public interface OnChangedListener {
		void onChanged(int value);
	}

	private OnChangedListener mListener;
	private int mDefaultValue;
	private int mMinValue;
	private int mMaxValue;
//	private int mStepSize;

	public void setListener(OnChangedListener listener) {
		mListener = listener;
	}

	public void showDialog(Context context, String title,
						   int defaultValue, int minValue, int maxValue, int stepSize) {

		if (minValue > maxValue) {
			mMinValue = maxValue;
			mMaxValue = minValue;
		} else {
			mMinValue = minValue;
			mMaxValue = maxValue;
		}

		if (mMinValue <= defaultValue && defaultValue <= mMaxValue) {
			mDefaultValue = defaultValue;
		} else {
			mDefaultValue = mMinValue;
		}

//		if (stepSize > 0) {
//			mStepSize = stepSize;
//		} if (stepSize == 0) {
//			mStepSize = 1;
//		} else {
//			mStepSize = -stepSize;
//		}

		showAlertDialog(context, title);
	}

	private void showAlertDialog(final Context context, String title) {
		LayoutInflater inflater = LayoutInflater.from(context);
		final View dialogView = inflater.inflate(R.layout.dialog_seek_bar, null);
		final TextView minValue = dialogView.findViewById(R.id.min_value);
		final TextView maxValue = dialogView.findViewById(R.id.max_value);
		final TextView currentValue = dialogView.findViewById(R.id.current_value);
		final SeekBar seekBar = dialogView.findViewById(R.id.seek_bar);

		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				String text = "" + (mMinValue + progress + "%");
				currentValue.setText(text);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
		seekBar.setMax(mMaxValue - mMinValue);
		seekBar.setProgress(mDefaultValue - mMinValue);
//		seekBar.incrementProgressBy(mStepSize);

		CharSequence minText = mMinValue + "%";
		minValue.setText(minText);
		CharSequence maxText = mMaxValue + "%";
		maxValue.setText(maxText);
		CharSequence currentText = mDefaultValue + "%";
		currentValue.setText(currentText);

		final AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setView(dialogView);
		builder.setTitle(title);
		builder.setPositiveButton(R.string.btn_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						String newValue = currentValue.getText().toString();
						if (newValue.length() > 0) {
							if (mListener != null) {
								try {
									String numValue = newValue.replace(newValue.substring(newValue.length() - 1), "");
									mListener.onChanged(Integer.parseInt(numValue));
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				});
		builder.setNegativeButton(R.string.btn_cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
					}
				});
		// Capture the BACK key as cancel.
		builder.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				dialog.dismiss();
			}
		});

		builder.create();
		builder.show();
	}
}
