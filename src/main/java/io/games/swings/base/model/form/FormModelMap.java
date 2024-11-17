package io.games.swings.base.model.form;

/**
 * ProjectName: MyUtilityBase
 * @author amit_kshirsagar
 * @date Feb 27, 2014
 */

import java.util.HashMap;

import org.apache.log4j.Logger;

public class FormModelMap extends HashMap<String, FormModel> {
	static Logger log = Logger.getLogger(FormModelMap.class.getName());
	private String formModelMapName = null;
	private static FormModelMap formModelMap = null;

	private FormModelMap(String name) {
		formModelMapName = name;
	}

	public static FormModelMap getFormModelMap(String name) {
		if (formModelMap == null) {
			formModelMap = new FormModelMap(name);
		}
		return formModelMap;
	}

	public FormModel getFormModel(String formName) {
		if (formModelMap != null) {
			return formModelMap.get(formName);
		}
		return null;
	}

	public void createFormModel(String formName) {
		if (!formModelMap.containsKey(formName)) {
			formModelMap.put(formName, new FormModel(formName));
		}
	}
}
