package org.virtusa.codetest.util;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.virtusa.codetest.exception.NumToTextException;


/**
 * 
 * Utility Class to read the properties from configuration Files.
 * We can write the properties, reads all properties, individual property and caches  
 * 
 * @author Narasimha
 *
 */
public class ResourceBundleServiceLocator {

	private static Logger logger = LoggerFactory.getLogger(ResourceBundleServiceLocator.class); 
	
	private final static String RESOURCE_BUNDLE = "config/application";
	ResourceBundle bundle = null;
	Locale locale = Locale.ENGLISH;


	private ResourceBundleServiceLocator() {

		try {

			if(System.getProperty("config.app.locale")!= null)
			{
				locale = new Locale(System.getProperty("config.app.locale"));
			}
			else
				if (ResourceBundleServiceLocator.getInstance().getProperty("config.app.default.locale") != null )  {
					locale = new Locale(ResourceBundleServiceLocator.getInstance().getProperty("config.app.default.locale").get());
				}

			Locale.setDefault(locale);
			bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE,
					locale);

		} catch (Exception rsrcParserexception) {
			Optional<String> errorMsg = ResourceBundleServiceLocator.getInstance().getProperty("error.1002");
			logger.error(String.format("Excpetion thrown while parsing the application.properties At ResourceBundleServiceLocator -ErrorCode->1002 : ErrorMessage:-> %s Exception Message:%s",errorMsg.get(),rsrcParserexception.getMessage()));
			throw new NumToTextException("1002", errorMsg.get());
		}
	}

	// singleton pattern
	private static class LazyHolder {
		private static final ResourceBundleServiceLocator INSTANCE = new ResourceBundleServiceLocator();
	}

	public static ResourceBundleServiceLocator getInstance() {
		return LazyHolder.INSTANCE;
	}

	public Optional<String> getProperty(String key) {
		return Optional.ofNullable(bundle.getString(key));
	}

	public boolean containsKey(String key) {
		return bundle.containsKey(key);
	}

	public String[] getValuesByKey(String key) {
		if(bundle.containsKey(key))
			return bundle.getString(key).split(" ");
		return " ".split(" ");
	}



}