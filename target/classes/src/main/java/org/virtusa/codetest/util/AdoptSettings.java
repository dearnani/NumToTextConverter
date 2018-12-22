package org.virtusa.codetest.util;

import java.util.Optional;

public class AdoptSettings {
	
	public static void applyConfiguarions() {
		Optional<String> localeconfigParam = null;
		if ( PropertyServiceLocator.getInstance().containsKey("config.app.locale") ) {
			localeconfigParam = Optional.of(PropertyServiceLocator.getInstance().getProperty("config.app.locale"));
			System.setProperty("config.app.locale", localeconfigParam.get());
		}

		if(localeconfigParam.get() == null) {
			Optional<String> defaultlocaleParam = ResourceBundleServiceLocator.getInstance().getProperty("config.app.default.locale");

			if(System.getProperty("config.app.locale")== null)
			{
				System.setProperty("config.app.locale",defaultlocaleParam.get());
			}

		}
	}

}
