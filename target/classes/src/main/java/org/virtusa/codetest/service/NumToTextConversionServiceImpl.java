package org.virtusa.codetest.service;

import java.util.Optional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.virtusa.codetest.exception.NumToTextException;
import org.virtusa.codetest.util.ResourceBundleServiceLocator;

public class NumToTextConversionServiceImpl implements NumToTextConversionService {

	private static Logger logger = LoggerFactory.getLogger(NumToTextConversionServiceImpl.class);

	@Override
	public String NumToTextConverter(@NotNull(message="Number should not be NULL") @Max(2147483647) @Min(-2147483647) int number) {

		try {
			
			ResourceBundleServiceLocator resourceBundle = ResourceBundleServiceLocator.getInstance();
			final String[] units = resourceBundle.getValuesByKey("num.units");
			final String[] tens = resourceBundle.getValuesByKey("num.tens");
			final String[] patterns = resourceBundle.getValuesByKey("num.pattern");
			
			if (number < 0) {
				return String.format("Minus %s ", NumToTextConverter(-number));
			}

			if (number < 20) {
				return units[number];
			}

			if (number < 100) {
				return String.format("%s %s %s", tens[number / 10], ((number % 10 != 0) ? " " : ""), units[number % 10]);
			}

			if (number < 1000) {
				return String.format("%s %s %s %s", units[number / 100], patterns[0],((number % 100 != 0) ? " " : ""),
						NumToTextConverter(number % 100));
			}

			if (number < 100000) {
				return String.format("%s %s %s %s", NumToTextConverter(number / 1000),patterns[1],
						((number % 10000 != 0) ? " " : ""), NumToTextConverter(number % 1000));
			}

			if (number < 10000000) {
				return String.format("%s %s %s %s", NumToTextConverter(number / 100000),patterns[2],
						((number % 100000 != 0) ? " " : ""), NumToTextConverter(number % 100000));
			}

			return String.format("%s %s %s %s", NumToTextConverter(number / 10000000),patterns[3],
					((number % 10000000 != 0) ? " " : ""), NumToTextConverter(number % 10000000));
			
		} catch(Exception serviceException) {
			Optional<String> errorMsg = ResourceBundleServiceLocator.getInstance().getProperty("error.1004");
			logger.error(String.format("Exception Occured at NumToTextConversionServiceImpl: NumToTextConverter-ErrorCode->1004 : ErrorMessage:-> %s ExceptionMessage->%s",errorMsg.get(),serviceException.getMessage()));
			throw new NumToTextException("1004", errorMsg.get());
		}
	}

}