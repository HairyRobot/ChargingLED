package hk.ChargingLED.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import hk.ChargingLED.R;

public final class SeekBarPreference extends DialogPreference implements OnSeekBarChangeListener {

	// Namespaces to read attributes
	private static final String PREFERENCE_NS = "http://schemas.android.com/apk/res/hk.ChargingLED";
	private static final String ANDROID_NS = "http://schemas.android.com/apk/res/android";

	// Attribute names
	private static final String ATTR_DEFAULT_VALUE = "defaultValue";
	private static final String ATTR_MIN_VALUE = "minValue";
	private static final String ATTR_MAX_VALUE = "maxValue";

	// Default values for defaults
	private static final int DEFAULT_CURRENT_VALUE = 50;
	private static final int DEFAULT_MIN_VALUE = 0;
	private static final int DEFAULT_MAX_VALUE = 100;
	private static final int DEFAULT_STEP_SIZE = 1;

	// Real defaults
	private final int mDefaultValue;
	private final int mMaxValue;
	private final int mMinValue;
	private final int mStepSize;
	private final String mUnitsLeft;
	private final String mUnitsRight;

	// Current value
	private int mCurrentValue;

	// View elements
	private SeekBar mSeekBar;
	private TextView mValueText;
	private Context context;

	public SeekBarPreference(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.context = context;

		// Read parameters from attributes
		mDefaultValue = attrs.getAttributeIntValue(ANDROID_NS, ATTR_DEFAULT_VALUE, DEFAULT_CURRENT_VALUE);

		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.hk_ChargingLED_iu_SeekBarPreference);
		mMinValue = array.getInteger(R.styleable.hk_ChargingLED_iu_SeekBarPreference_minValue, DEFAULT_MIN_VALUE);
		mMaxValue = array.getInteger(R.styleable.hk_ChargingLED_iu_SeekBarPreference_maxValue, DEFAULT_MAX_VALUE);
		mStepSize = array.getInteger(R.styleable.hk_ChargingLED_iu_SeekBarPreference_stepSize, DEFAULT_STEP_SIZE);
		String units = array.getString(R.styleable.hk_ChargingLED_iu_SeekBarPreference_unitsLeft);
		mUnitsLeft = (units == null) ? "" : units;
		units = array.getString(R.styleable.hk_ChargingLED_iu_SeekBarPreference_unitsRight);
		mUnitsRight = (units == null) ? "" : units;
	}

	@Override
	protected View onCreateDialogView() {
		// Get current value from preferences
		mCurrentValue = getPersistedInt(mDefaultValue);

		// Inflate layout
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View view = inflater.inflate(R.layout.seek_bar_preference, null);

		// Setup minimum and maximum text labels
		CharSequence minValue = mUnitsLeft + Integer.toString(mMinValue) + mUnitsRight;
		((TextView) view.findViewById(R.id.min_value)).setText(minValue);
		CharSequence maxValue = mUnitsLeft + Integer.toString(mMaxValue) + mUnitsRight;
		((TextView) view.findViewById(R.id.max_value)).setText(maxValue);

		// Setup SeekBar
		mSeekBar = view.findViewById(R.id.seek_bar);
		mSeekBar.setMax(mMaxValue - mMinValue);
		mSeekBar.setProgress(mCurrentValue - mMinValue);
//		mSeekBar.incrementProgressBy(mStepSize);
		mSeekBar.setOnSeekBarChangeListener(this);

		// Setup text label for current value
		mValueText = view.findViewById(R.id.current_value);
		CharSequence currentValue = mUnitsLeft + Integer.toString(mCurrentValue) + mUnitsRight;
		mValueText.setText(currentValue);

		return view;
	}

	@Override
	protected void onDialogClosed(boolean positiveResult) {
		super.onDialogClosed(positiveResult);

		// Return if change was cancelled
		if (!positiveResult) {
			return;
		}

		// Persist current value if needed
		if (shouldPersist()) {
			persistInt(mCurrentValue);
		}

		// Notify activity about changes (to update preference summary line)
		notifyChanged();
	}

	@Override
	public CharSequence getSummary() {
		// Format summary string with current value
		String summary = super.getSummary().toString();
		int value = getPersistedInt(mDefaultValue);
		return mUnitsLeft + value + mUnitsRight;
//		return String.format(summary, value);
	}

	public void onProgressChanged(SeekBar seek, int value, boolean fromTouch) {
		// Update current value
		mCurrentValue = value + mMinValue;
		// Update label with current value
		CharSequence currentValue = mUnitsLeft + Integer.toString(mCurrentValue) + mUnitsRight;
		mValueText.setText(currentValue);
	}

	public void onStartTrackingTouch(SeekBar seek) {
		// Not used
	}

	public void onStopTrackingTouch(SeekBar seek) {
		// Not used
	}
}
