package org.virtusa.codetest.main;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.virtusa.codetest.service.NumToTextConversionService;
import org.virtusa.codetest.service.NumToTextConversionServiceImpl;
import org.virtusa.codetest.util.AdoptSettings;
import org.virtusa.codetest.util.PropertyServiceLocator;
import org.virtusa.codetest.util.ResourceBundleServiceLocator;

/**
 * @author Narasimha
 *
 */
public class App 
{
	private static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		AdoptSettings.applyConfiguarions();

		String[] testdata = PropertyServiceLocator.getInstance().getValuesByKey("app.testdata");
		NumToTextConversionService numToTextConversionService = new NumToTextConversionServiceImpl();

		for(int i=0;i<testdata.length;i++) {
			try {
				int testdataItem = Integer.parseInt(testdata[i]);
				logger.debug(String.format("Number: %s -> Text: %s \n", testdata[i], numToTextConversionService.NumToTextConverter(testdataItem)));
			} catch(NumberFormatException numException) {
				
				Optional<String> errorMsg = ResourceBundleServiceLocator.INSTANCE.getProperty("error.1001");
				logger.error(String.format("Exception Occured at App: Main -ErrorCode->1001 : ErrorMessage:-> %s ExceptionMessage->%s",errorMsg.get(),numException.getMessage()));
				logger.error(String.format("The given test data %s is Not valid",testdata[i]));
			}
		}
	}
}
