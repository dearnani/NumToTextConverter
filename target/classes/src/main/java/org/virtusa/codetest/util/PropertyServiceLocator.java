package org.virtusa.codetest.util;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

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
public class PropertyServiceLocator {
	
	private static Logger logger = LoggerFactory.getLogger(PropertyServiceLocator.class);
	
	private final Properties configProp = new Properties();

	private PropertyServiceLocator() {
		 logger.debug("Read all properties from properties file: application.properties");
		try (InputStream configInputStream = this.getClass().getClassLoader().getResourceAsStream("application-settings.properties");){
			configProp.load(configInputStream);
		} catch (Exception propException) {
			
			 Optional<String> errorMsg = ResourceBundleServiceLocator.INSTANCE.getProperty("error.1003");
			 logger.warn(String.format("Exception Occured at PropertyServiceLocator: NumToTextConverter-ErrorCode->1004 : ErrorMessage:-> %s ExceptionMessage->%s",errorMsg.get(),propException.getMessage()));
			 throw new NumToTextException("1003", errorMsg.get());
		}
	}

	// singleton pattern
	private static class LazyHolder {
		private static final PropertyServiceLocator INSTANCE = new PropertyServiceLocator();
	}

	public static PropertyServiceLocator getInstance() {
		return LazyHolder.INSTANCE;
	}

	public String getProperty(String key) {
		return configProp.getProperty(key);
	}

	public Set<String> getAllPropertyNames() {
		return configProp.stringPropertyNames();
	}

	public boolean containsKey(String key) {
		return configProp.containsKey(key);
	}
	
	public Properties getProperties() {
		return configProp;
	}
	
	public void setProperties(Properties settingProps) {
		 configProp.putAll(settingProps);
	}
	
	public String[] getValuesByKey(String key) {
		return configProp.getProperty(key).split(" ");
	}
}