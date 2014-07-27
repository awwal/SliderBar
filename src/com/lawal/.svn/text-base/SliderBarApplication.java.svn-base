package com.lawal;

import java.text.DateFormat;
import java.util.Date;

import com.lawal.SliderBar.SliderValueListener;
import com.lawal.SliderBar.ValueOutOfBoundsException;
import com.lawal.SliderUtil.DateLabelFormat;
import com.vaadin.Application;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class SliderBarApplication extends Application {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5484943553531596125L;

	@Override
	public void init() {
		Window mainWindow = new Window("SliderBar Application");
		
		VerticalLayout vlay = setup(); 
		
	
		
		mainWindow.addComponent(vlay);

		setMainWindow(mainWindow);
	}

	private VerticalLayout setup() {
		
		final SliderBar<Long> bar = new SliderBar<Long>(Long.class);
		bar.setCaption("When I will rate this plugin"); 
		final TextField tf = new TextField();
		
		Button btn = new Button("Increase");
		
		btn.addListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					bar.setSliderValue( SliderUtil.MINUTE + (Long) tf.getData());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		VerticalLayout vlay = new VerticalLayout();
		vlay.setWidth(500,Sizeable.UNITS_PIXELS);
		vlay.addStyleName(Reindeer.LAYOUT_BLUE);
		
		bar.addRangeValueListener(new SliderValueListener<Long>() {

			@Override
			public void sliderValuesChanged(Long value) {
				Date n = new Date( value);
				tf.setValue( DateFormat.getDateTimeInstance().format(n));
				tf.setData(n.getTime());
				
			}
			
			
			
		});
		
		bar.setNumberLabels(3);
		bar.setNumberTicks(10);
		Date now = new Date();

		bar.setMin(now.getTime() - (10*SliderUtil.MINUTE));
		bar.setMax(now.getTime() + (10*SliderUtil.MINUTE));
		bar.setStepSize(SliderUtil.SECOND); 
		bar.setSuperImmediateMode(true);
		try {
			bar.setValue(10L);
		} catch (ValueOutOfBoundsException e) {
			e.printStackTrace();
		}
		bar.setLabelAsDate(DateLabelFormat.HOUR_MINUTE_SECOND);
		bar.setImmediate(true);
		bar.setSizeFull();
		
		
		HorizontalLayout hlay = new HorizontalLayout();
		hlay.addComponent(tf);
		hlay.addComponent(btn);
		vlay.addComponent(hlay);
		vlay.addComponent(bar);
		return vlay;
	}

}
