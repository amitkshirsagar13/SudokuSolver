package io.games.swings.builder;

/**
 * ProjectName: MyUtilityBase
 * @author amit_kshirsagar
 * @date Feb 27, 2014
 */

import java.io.File;

import org.apache.log4j.Logger;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import io.games.swings.componants.Application;

public class JComponantBuilder {
	static Logger log = Logger.getLogger(JComponantBuilder.class.getName());

	public static Application application = null;

	public static void parseDom2Componants() {
		try {
			File file = new File("./conf/SudokuComponants.xml");
			JAXBContext jaxbContext = JAXBContext
					.newInstance(Application.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			application = (Application) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
