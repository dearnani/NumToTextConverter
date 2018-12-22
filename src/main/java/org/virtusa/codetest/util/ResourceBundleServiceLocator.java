package org.virtusa.codetest.util;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * 
 * Utility Class to read the properties from configuration Files.
 * We can write the properties, reads all properties, individual property and caches  
 * 
 * @author Narasimha
 *
 */
public class ResourceBundleServiceLocator {

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

		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.printf("Excpetion thrown while parsing %s", exception.getMessage() );
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
		return "  ".split(" ");
	}



}