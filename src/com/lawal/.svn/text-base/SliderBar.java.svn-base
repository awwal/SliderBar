/*
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * 
 * This is a direct port of the Google gwt SliderBar into Vaadin obtained from
 * http://google-web-toolkit-incubator.googlecode.com/svn/trunk/demo/SliderBar/index.html
 * 
 * @author
 * talktolawal@gmail.com
 * 
 */
package com.lawal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lawal.SliderUtil.DateLabelFormat;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractField;

@SuppressWarnings("serial")
@com.vaadin.ui.ClientWidget(com.lawal.client.ui.VSliderBar.class)
public class SliderBar<T extends Number> extends AbstractField {

	public interface SliderValueListener<T> {
		public void sliderValuesChanged(T value);
	}

	/**
	 * ValueOutOfBoundsException
	 * 
	 * @author IT Mill Ltd.
	 */
	public static class ValueOutOfBoundsException extends Exception {
		private final Double _value;

		/**
		 * Constructs an <code>ValueOutOfBoundsException</code> with the
		 * specified detail message.
		 * 
		 * @param valueOutOfBounds
		 */
		public ValueOutOfBoundsException(Double valueOutOfBounds) {
			_value = valueOutOfBounds;
		}

		public Double getValue() {
			return _value;
		}
	}

	private DateLabelFormat _dateLabelFormat = null;
	private Class<T> _numberClass;
	private List<SliderValueListener<T>> listeners = new ArrayList<SliderValueListener<T>>();
	private T max;
	private T min;
	private int numLabels = 10;
	private int numTicks = 10;
	private T stepSize;
	private boolean superImmediateMode = false;

	/**
	 * Default Slider constructor. Sets all values to defaults and the slide
	 * handle at minimum value.
	 */
	@SuppressWarnings("unchecked")
	public SliderBar(Class<T> numberClass) {
		min = (T) SliderUtil.initValue(numberClass, 0);
		max = (T) SliderUtil.initValue(numberClass, 100);
		stepSize = (T) SliderUtil.initValue(numberClass, 1);

		_numberClass = numberClass;
		setListener();
		setInternalVal(new Double(min.doubleValue()));
	}

	private void setListener() {
		super.addListener(new ValueChangeListener() {

			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				fireValueChangeListeners();
				
			}
		});
		
	}

	/**
	 * @param min
	 * @param max
	 * @param stepSize
	 */
	public SliderBar(Class<T> numberClass, T min, T max, T stepsize) {
		this(numberClass);
		setMin(min);
		setMax(max);
		setStepSize(stepsize);
	}

	/*
	 * @deprecated use {@link #addRangeValueListener(SliderValueListener)}
	 * instead
	 */
	@Override
	@Deprecated
	public void addListener(ValueChangeListener listener) {

		super.addListener(listener);
	}

	public void addRangeValueListener(SliderValueListener<T> listener) {
		listeners.add(listener);
	}

	/**
	 * Invoked when the value of a variable has changed. SliderBar listeners are
	 * notified if the slider value has changed.
	 * 
	 * @param source
	 * @param variables
	 */
	@Override
	public void changeVariables(Object source, Map<String, Object> variables) {
		super.changeVariables(source, variables);
		if (variables.containsKey("value")) {
			final Object value = variables.get("value");
			final Double newValue = (Double) value;
			if (newValue != null && newValue != getValue() && !newValue.equals(getValue())) {
				try {
					setInternalVal(newValue, true);
				} catch (final ValueOutOfBoundsException e) {
					// Convert to nearest bound
					double out = e.getValue().doubleValue();
					if (out < min.doubleValue()) {
						out = min.doubleValue();
					}
					if (out > max.doubleValue()) {
						out = max.doubleValue();
					}
					super.setValue(new Double(out), false);
				}
			}
		}
	}


	@SuppressWarnings("unchecked")
	protected void fireValueChangeListeners() {
		for (SliderValueListener<T> list : listeners) {
			Object value = super.getValue();
			list.sliderValuesChanged((T) SliderUtil.getValue(_numberClass, (Number) value));
		}
	}

	/**
	 * Gets the biggest possible value in Sliders range.
	 * 
	 * @return the biggest value slider can have
	 */
	public T getMax() {
		return max;
	}

	/**
	 * Gets the minimum value in Sliders range.
	 * 
	 * @return the smalles value slider can have
	 */
	public T getMin() {
		return min;
	}

	public int getNumberLabels() {
		return numLabels;
	}

	public int getNumberTicks() {
		return numTicks;
	}

	/**
	 * Get the current stepSize of the Slider.
	 * 
	 * @return stepSize
	 */
	public T getStepSize() {
		return stepSize;
	}

	@Override
	public Class<?> getType() {
		return _numberClass;
	}

	public boolean isSuperImmediateMode() {
		return superImmediateMode;
	}

	@Override
	public void paintContent(PaintTarget target) throws PaintException {
		super.paintContent(target);
		target.addAttribute("min", min.doubleValue());
		if (max.doubleValue() > min.doubleValue()) {
			target.addAttribute("max", max.doubleValue());
		} else {
			target.addAttribute("max", min.doubleValue());
		}
		target.addAttribute("stepsize", stepSize.doubleValue());
		target.addAttribute("numticks", numTicks);
		target.addAttribute("numlabels", numLabels);
		target.addAttribute("superimmediate", superImmediateMode);
		if (_dateLabelFormat != null) {
			target.addAttribute("dateformat", _dateLabelFormat.name());
		}
		if (stepSize.doubleValue() > 0) {
			target.addVariable(this, "value", ((Number) getValue()).doubleValue());
		} else {
			target.addVariable(this, "value", ((Number) getValue()).intValue());
		}
	}

	public void removeRangeValueListener(SliderValueListener<T> listener) {
		if (listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}

	/**
	 * Set the value of this Slider.
	 * 
	 * @param value
	 *            New value of Slider. Must be within Sliders range (min - max),
	 *            otherwise throws an exception.
	 * @param repaintIsNotNeeded
	 *            If true, client-side is not requested to repaint itself.
	 * @throws ValueOutOfBoundsException
	 */
	private void setInternalVal(Double value, boolean repaintIsNotNeeded) throws ValueOutOfBoundsException {
		double newValue = value.doubleValue();

		super.setValue(new Double(newValue), repaintIsNotNeeded);
		if (repaintIsNotNeeded) {
			fireValueChangeListeners();

		}
	}

	private void setInternalVal(Number value) {
		try {
			setInternalVal(value.doubleValue(), false);
		} catch (ValueOutOfBoundsException todo) {
			todo.printStackTrace();
		}

	}

	/**
	 * Set the maximum value of the Slider. If the current value of the Slider
	 * is out of new bounds, the value is set to new minimum.
	 * 
	 * @param max
	 *            New maximum value of the Slider.
	 */
	public void setMax(T max) {
		this.max = max;
		try {
			if ((new Double(getValue().toString())).doubleValue() > max.doubleValue()) {
				setInternalVal(new Double(max.doubleValue()));
			}
		} catch (final ClassCastException e) {
			// FIXME: Handle exception
			/*
			 * Where does ClassCastException come from? Can't see any casts
			 * above
			 */
			setInternalVal(new Double(max.doubleValue()));
		}
		requestRepaint();
	}

	/**
	 * Set the minimum value of the Slider. If the current value of the Slider
	 * is out of new bounds, the value is set to new minimum.
	 * 
	 * @param min
	 *            New minimum value of the Slider.
	 */
	public void setMin(T min) {
		this.min = min;
		try {
			if ((new Double(getValue().toString())).doubleValue() < min.doubleValue()) {
				super.setValue(new Double(min.doubleValue()));
			}
		} catch (final ClassCastException e) {
			// FIXME: Handle exception
			/*
			 * Where does ClassCastException come from? Can't see any casts
			 * above
			 */
			super.setValue(new Double(min.doubleValue()));
		}
		requestRepaint();
	}

	/**
	 * The number of labels to show.
	 */
	public void setNumberLabels(int numLabels) {
		this.numLabels = numLabels;
	}

	/**
	 * The number of tick marks to show.
	 */
	public void setNumberTicks(int numTicks) {
		this.numTicks = numTicks;
	}

	/**
	 * This method is used instead of {@link #setValue(T)} without throwing
	 * exception. The val is set to max or min if it exceeds the min or max
	 * value accordingly
	 * 
	 * @param val
	 *            .
	 */
	public void setSliderValue(T val) {
		if (val.doubleValue() > max.doubleValue()) {
			setInternalVal(max);
		} else if (val.doubleValue() < min.doubleValue()) {
			setInternalVal(min);
			return;
		} else
			setInternalVal(val);
	}

	/**
	 * Set a new stepSize for the Slider.
	 * 
	 * @param stepSize
	 */
	public void setStepSize(T stepSize) {
		if (stepSize.doubleValue() < 0) {
			return;
		}
		this.stepSize = stepSize;
		requestRepaint();
	}

	/**
	 * If super immediate mode is true, values are immediately received from
	 * client when dragging with the mouse and when the Arrow keys are pressed
	 * down
	 * 
	 * @param superImmediateMode
	 */
	
	
	
	public void setSuperImmediateMode(boolean superImmediateMode) {
		if (superImmediateMode)
			this.setImmediate(true);
		this.superImmediateMode = superImmediateMode;
		requestRepaint();
	}

	/**
	 * Set the value of this Slider.
	 * 
	 * @param value
	 *            New value of Slider. Must be within Sliders range (min - max),
	 *            otherwise throws an exception.
	 * @throws ValueOutOfBoundsException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setValue(Object value) {
		if(value.getClass()!= _numberClass) {
			throw new IllegalArgumentException(String.format("Class mismatch. Value is of class %s while value of class %s was expected",value.getClass().getName(), _numberClass.getName()));
			
		}
		try {
			this.setValue((T)value);
		} catch (ValueOutOfBoundsException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	public void setValue(T value) throws ValueOutOfBoundsException {

        setValue(new Double(value.doubleValue()), false);


	}

	public DateLabelFormat getDateLabelFormat() {
		return _dateLabelFormat;
	}

	public void setLabelAsDate(DateLabelFormat _dateLabelFormat) {
		this._dateLabelFormat = _dateLabelFormat;
	}

}
