package org.virtusa.codetest.util;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;


public class ResourceBundleServiceLocatorTest {

	@Before
	public void setUp() throws Exception {
		System.setProperty("config.app.locale", "en");
	}

	@Test
	public void testGetProperty() {
		ResourceBundleServiceLocator resourceBundle = ResourceBundleServiceLocator.getInstance();
		if 	(resourceBundle!=null) {
			Optional<String> testLocale = resourceBundle.getProperty("config.app.default.locale");
			assertTrue("en".equals(testLocale.get()));
		}
			
		
	}

	@Test
	public void testContainsKey() {
		assertTrue(ResourceBundleServiceLocator.getInstance().containsKey("num.units"));
	}

	@Test
	public void testGetValuesByKey() {
		String[] units = ResourceBundleServiceLocator.getInstance().getValuesByKey("num.units");
		assertTrue("One".equals(units[1]));
	}
}
