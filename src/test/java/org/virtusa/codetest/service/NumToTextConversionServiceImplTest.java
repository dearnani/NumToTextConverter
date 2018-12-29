package org.virtusa.codetest.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class NumToTextConversionServiceImplTest {
	
	NumToTextConversionService numToTextConversionService = null;

	@Before
	public void setUp() throws Exception {
		 numToTextConversionService = new NumToTextConversionServiceImpl();
	}

	@Test
	public void testNumToTextConverterOutofRangeTest() {
		try {
		String result = numToTextConversionService.NumToTextConverter(new Integer("21474836479"));
		assertNotNull(result);
		} catch(NumberFormatException exception) {
			assertTrue(true);
			
		}
	}
	
	@Test
	public void testNumToTextConverterInRangeTest() {
		try {
		String result = numToTextConversionService.NumToTextConverter(new Integer("123456"));
		assertNotNull(result);
		} catch(NumberFormatException exception) {
			assertTrue(false);
			
		}
	}
	
	@Test
	public void testNumToTextConvertervalidateTest() {
		try {
		String result = numToTextConversionService.NumToTextConverter(new Integer("asdg"));
		assertNotNull(result);
		} catch(NumberFormatException exception) {
			assertTrue(true);
			
		}
	}
	

}
