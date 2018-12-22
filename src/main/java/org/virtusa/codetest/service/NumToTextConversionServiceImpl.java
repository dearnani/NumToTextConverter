package org.virtusa.codetest.service;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.virtusa.codetest.util.ResourceBundleServiceLocator;

public class NumToTextConversionServiceImpl implements NumToTextConversionService {

	public static final String[] units = ResourceBundleServiceLocator.getInstance().getValuesByKey("num.units");
	public static final String[] tens = ResourceBundleServiceLocator.getInstance().getValuesByKey("num.tens");
	public static final String[] patterns = ResourceBundleServiceLocator.getInstance().getValuesByKey("num.pattern");
	@Override
	public String NumToTextConverter(@NotNull(message="Number should not be NULL") @Max(2147483647) @Min(-2147483647) int number) {

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
	}
}