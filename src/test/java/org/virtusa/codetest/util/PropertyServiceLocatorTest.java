package org.virtusa.codetest.util;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class PropertyServiceLocatorTest {

	public Properties loadedConfigs = null;
	
	@Before
	public void setUp() throws Exception {
		loadedConfigs = PropertyServiceLocator.getInstance().getProperties();
	}

	@Test
	public void testGetProperty() {
		assertTrue("en".equals(loadedConfigs.getProperty("config.app.locale")));
	}

	@Test
	public void testGetAllPropertyNames() {
		assertTrue(PropertyServiceLocator.getInstance().getAllPropertyNames().contains("app.testdata"));
	}

	@Test
	public void testContainsKey() {
		assertTrue(loadedConfigs.containsKey("config.app.locale"));
	}
	
	@Test
	public void testContainsKeyNegativeScenario() {
		assertFalse(loadedConfigs.containsKey("config.app"));
	}

	@Test
	public void testGetProperties() {
		assertTrue(loadedConfigs.equals(loadedConfigs));
		
	}

	@Test
	public void testGetValuesByKey() {
		
		String data[] = PropertyServiceLocator.getInstance().getValuesByKey("app.testdata");
		assertNotNull(data);
		assertTrue("100".equals(data[0]));
	}

}
