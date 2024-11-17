package io.games.swings.base.model.form;

/**
 * ProjectName: MyUtilityBase
 * @author amit_kshirsagar
 * @date Feb 27, 2014
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class NameValuePair {
	static Logger log = Logger.getLogger(NameValuePair.class.getName());

	private String name = null;
	private String value = null;
	private List<String> valueStringList = null;

	/**
	 * @param name
	 * @param value
	 */
	public NameValuePair(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public NameValuePair(String name, List<String> valueStringList) {
		super();
		this.name = name;
		this.valueStringList = valueStringList;
		setMultiValued(true);
	}

	public NameValuePair(String name, int value) {
		super();
		this.name = name;
		this.value = value + "";
	}

	public NameValuePair(String name, float value) {
		super();
		this.name = name;
		this.value = value + "";
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public int getValueInt() {
		int valueInt = -1;
		try {
			valueInt = Integer.parseInt(value);
		} catch (NumberFormatException nfe) {
			log.error("Exception Converting " + value + " to Integer.", nfe);
			throw nfe;
		}
		return valueInt;
	}

	public float getValueFloat() {
		float valueInt = -1;
		try {
			valueInt = Float.parseFloat(value);
		} catch (NumberFormatException nfe) {
			log.error("Exception Converting " + value + " to Float.", nfe);
			throw nfe;
		}
		return valueInt;
	}

	public double getValueDouble() {
		double valueInt = -1;
		try {
			valueInt = Double.parseDouble(value);
		} catch (NumberFormatException nfe) {
			log.error("Exception Converting " + value + " to Double.", nfe);
			throw nfe;
		}
		return valueInt;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setValue(int value) {
		this.value = value + "";
	}

	public void setValue(float value) {
		this.value = value + "";
	}

	public void setValue(double value) {
		this.value = value + "";
	}

	public void addValue(String value) {
		if (valueStringList == null) {
			valueStringList = new ArrayList<String>();
			valueStringList.add(this.value);
			setMultiValued(true);
		}
		valueStringList.add(value);
	}

	public List<String> getValues() {
		return valueStringList;
	}

	private boolean multiValued = false;

	/**
	 * @return the multiValued
	 */
	public boolean isMultiValued() {
		return multiValued;
	}

	/**
	 * @param multiValued
	 *            the multiValued to set
	 */
	public void setMultiValued(boolean multiValued) {
		this.multiValued = multiValued;
	}
}
