package io.games.swings.builder;

/**
 * ProjectName: SwingsParseX2J
 * @author amit_kshirsagar
 * @date Jan 20, 2014
 */

import static io.games.swings.builder.JComponantBuilder.application;

import java.util.List;

import org.apache.log4j.Logger;

import io.games.swings.componants.FormComponent;

public class FormBuilder extends JComponantBuilder {
	static Logger log = Logger.getLogger(FormBuilder.class.getName());

	public static List<FormComponent> getFormComponentsForPanel(String panel) {
		if (application == null) {
			parseDom2Componants();
		}
		List<FormComponent> formComponentList = null;
		for (int i = 0; i < application.getFrame().getPanel().size(); i++) {
			if (application.getFrame().getPanel().get(i).getPanelName()
					.equalsIgnoreCase(panel)
					&& application.getFrame().getPanel().get(i)
							.getSubPanelName().equalsIgnoreCase("FormPanel")) {
				formComponentList = application.getFrame().getPanel().get(i)
						.getFormComponent();
			}

		}
		return formComponentList;
	}

	public static void main(String[] args) {
		FormBuilder.parseDom2Componants();
	}

}
