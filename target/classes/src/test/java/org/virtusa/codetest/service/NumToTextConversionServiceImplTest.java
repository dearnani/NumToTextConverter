//package org.virtusa.codetest.service;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.virtusa.codetest.util.ResourceBundleServiceLocator;
//
//public class NumToTextConversionServiceImplTest {
//	
//	public static  String[] units=null;// = ResourceBundleServiceLocator.getInstance().getValuesByKey("num.units");
//	public static  String[] tens=null;// = ResourceBundleServiceLocator.getInstance().getValuesByKey("num.tens");
//	NumToTextConversionService numToTextConversionService = null;
//
//	@Before
//	public void setUp() throws Exception {
//		 units = ResourceBundleServiceLocator.getInstance().getValuesByKey("num.units");
//		 tens = ResourceBundleServiceLocator.getInstance().getValuesByKey("num.tens");
//		 numToTextConversionService = new NumToTextConversionServiceImpl();
//	}
//
//	@Test
//	public void testNumToTextConverterOutofRangeTest() {
//		try {
//		String result = numToTextConversionService.NumToTextConverter(new Integer("21474836479"));
//		//assertNotNull(result);
//		assertTrue(false);
//		} catch(NumberFormatException exception) {
//			assertTrue(true);
//			
//		}
//	}
//	
//	@Test
//	public void testNumToTextConverterInRangeTest() {
//		try {
//		String result = numToTextConversionService.NumToTextConverter(new Integer("2147483647"));
//		//assertNotNull(result);
//		assertTrue(true);
//		} catch(NumberFormatException exception) {
//			assertTrue(false);
//			
//		}
//	}
//	
//	@Test
//	public void testNumToTextConvertervalidateTest() {
//		try {
//		String result = numToTextConversionService.NumToTextConverter(new Integer("asdg"));
//		//assertNotNull(result);
//		assertTrue(false);
//		} catch(NumberFormatException exception) {
//			assertTrue(true);
//			
//		}
//	}
//	
//
//}
