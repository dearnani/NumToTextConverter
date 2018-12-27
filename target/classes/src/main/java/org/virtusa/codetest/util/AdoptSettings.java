package org.virtusa.codetest.util;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.virtusa.codetest.exception.NumToTextException;
/**
 * 
 * @author Narasimha
 *
 */
public class AdoptSettings {
	private static Logger logger = LoggerFactory.getLogger(AdoptSettings.class);
	public static void applyConfiguarions() {

		Optional<String> localeconfigParam = null;
		
		try {
			if ( PropertyServiceLocator.getInstance().containsKey("config.app.locale") ) {
				localeconfigParam = Optional.of(PropertyServiceLocator.getInstance().getProperty("config.app.locale"));
				System.setProperty("config.app.locale", localeconfigParam.get());
			}

			if(localeconfigParam.get() == null) {
				Optional<String> defaultlocaleParam = ResourceBundleServiceLocator.getInstance().getProperty("config.app.default.locale");

				if(System.getProperty("config.app.locale")== null) {
					System.setProperty("config.app.locale",defaultlocaleParam.get());
				}
			}
		} catch(Exception configException) {
			
			logger.error("Error Occured at AdoptSettings:applyConfiguarions ");
			Optional<String> errorMsg = ResourceBundleServiceLocator.getInstance().getProperty("error.1005");
			logger.error(String.format("Exception Occured at AdoptSettings: applyConfiguarions  -ErrorCode->1005 : ErrorMessage:-> %s ExceptionMessage->%s",errorMsg.get(),configException.getMessage()));
			throw new NumToTextException("1005", errorMsg.get());
			
			}
	}
}
