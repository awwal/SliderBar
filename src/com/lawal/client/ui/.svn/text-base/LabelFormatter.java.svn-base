package com.lawal.client.ui;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;

/**
 * A formatter used to format the labels displayed in the widget.
 */
public abstract class LabelFormatter {
	/**
	 * Generate the text to display in each label based on the label's value.
	 * Override this method to change the text displayed within the SliderBar.
	 * 
	 * @param slider
	 *            the Slider bar
	 * @param value
	 *            the value the label displays
	 * @return the text to display for the label
	 */
	public abstract String formatLabel(VSliderBar slider, double value);

	public static LabelFormatter getDateLabelFormatter(final String dateformat) {
		return new LabelFormatter() {
			@Override
			public String formatLabel(VSliderBar slider, double value) {
				long lv = new Double(value).longValue();
				Date d = new Date(lv);
				PredefinedFormat format = PredefinedFormat.valueOf(dateformat); 
				return DateTimeFormat.getFormat(format).format(d);
			}
		};
	}

	public static LabelFormatter getDefault() {
		return new LabelFormatter() {
			@Override
			public String formatLabel(VSliderBar slider, double value) {
				return String.valueOf(value);
			}
		};
	}
}