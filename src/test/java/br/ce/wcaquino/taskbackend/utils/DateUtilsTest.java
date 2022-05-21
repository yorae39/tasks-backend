package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;


public class DateUtilsTest {
	
	@Test
	public void returnTrueForFutureDates() {
		
		LocalDate date = LocalDate.of(2030, 01, 01);
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
		
		Boolean validDate = DateUtils.isEqualOrFutureDate(date);		
		Assert.assertEquals(validDate, true);
	}
	
	@Test
	public void returnFalseForPastDates() {
		
		LocalDate date = LocalDate.of(2010, 01, 01);
		Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
		
		Boolean validDate = DateUtils.isEqualOrFutureDate(date);		
		Assert.assertEquals(validDate, false);
	}
	
	
	@Test
	public void returnTrueForActualDates() {
		
		LocalDate date = LocalDate.now();
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
		
		Boolean validDate = DateUtils.isEqualOrFutureDate(date);		
		Assert.assertEquals(validDate, true);
	}
	

}
