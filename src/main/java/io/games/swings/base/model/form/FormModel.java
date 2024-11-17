package io.games.swings.base.model.form;

/**
 * ProjectName: MyUtilityBase
 * @author amit_kshirsagar
 * @date Feb 27, 2014
 */

import org.apache.log4j.Logger;

public class FormModel {
	static Logger log = Logger.getLogger(FormModel.class.getName());

	private String formName = null;

	public FormModel(String formName) {
		this.formName = formName;
		formElements = new NameValuePairMap();
	}

	private NameValuePairMap formElements = null;

	public void setElement(String name, String value) {
		formElements.addNameValuePair(name, value);
	}

	public void addElementValues(String name, String value) {
		formElements.addNameValuePairValues(name, value);
	}

	public void addElement(NameValuePair nameValue) {
		formElements.addNameValuePair(nameValue);
	}
}
