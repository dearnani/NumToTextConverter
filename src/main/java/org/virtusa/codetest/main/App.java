package org.virtusa.codetest.main;

import org.virtusa.codetest.service.NumToTextConversionService;
import org.virtusa.codetest.service.NumToTextConversionServiceImpl;
import org.virtusa.codetest.util.AdoptSettings;
import org.virtusa.codetest.util.PropertyServiceLocator;

/**
 * @author Narasimha
 *
 */
public class App 
{
	public static void main(String[] args) {
		
		AdoptSettings.applyConfiguarions();
		
		String[] testdata = PropertyServiceLocator.getInstance().getValuesByKey("app.testdata");

		NumToTextConversionService numToTextConversionService = new NumToTextConversionServiceImpl();
		for(int i=0;i<testdata.length;i++) {

			try {
				int testdataItem = Integer.parseInt(testdata[i]);
				System.out.printf("Number: %s -> Text: %s \n", testdata[i], numToTextConversionService.NumToTextConverter(testdataItem));
			} catch(NumberFormatException exception) {
				System.out.printf("The given test data %s is Not valid",testdata[i]);
			}
		}
	}
}
