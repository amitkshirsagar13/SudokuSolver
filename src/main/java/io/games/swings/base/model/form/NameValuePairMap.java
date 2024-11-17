package io.games.swings.base.model.form;

/**
 * ProjectName: MyUtilityBase
 * @author amit_kshirsagar
 * @date Feb 27, 2014
 */

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

public class NameValuePairMap extends HashMap<String, NameValuePair> {
	static Logger log = Logger.getLogger(NameValuePairMap.class.getName());

	public void addNameValuePair(String name, String value) {
		NameValuePair nameValue = new NameValuePair(name, value);
		if (this.containsKey(nameValue.getName())) {
			this.remove(nameValue);
		}
		addNameValuePair(nameValue);
	}

	public void setNameValuePair(String name, String value) {
		if (this.containsKey(name)) {
			this.get(name).setValue(value);
		} else {
			NameValuePair nameValue = new NameValuePair(name, value);
			addNameValuePair(nameValue);
		}
	}

	public void addNameValuePair(NameValuePair nameValue) {
		this.put(nameValue.getName(), nameValue);
	}

	public void addNameValuePairValues(String name, String value) {
		if (this.containsKey(name)) {
			this.get(name).addValue(value);
		} else {
			NameValuePair nameValue = new NameValuePair(name, value);
			addNameValuePair(nameValue);
		}
	}

	public String getValueForName(String name) {
		if (this.containsKey(name)) {
			return this.get(name).getValue();
		}
		return null;
	}

	public int getValueIntForName(String name) {
		if (this.containsKey(name)) {
			return this.get(name).getValueInt();
		}
		return -1;
	}

	public float getValueFloatForName(String name) {
		if (this.containsKey(name)) {
			return this.get(name).getValueFloat();
		}
		return -1;
	}

	public double getValueDoubleForName(String name) {
		if (this.containsKey(name)) {
			return this.get(name).getValueDouble();
		}
		return -1;
	}

	public List<String> getValueListForName(String name) {
		if (this.containsKey(name)) {
			return this.get(name).getValues();
		}
		return null;
	}
}
