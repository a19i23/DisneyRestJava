package org.alanvilla.projects.disneyRestJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

public class BlackoutDates {
	
	private static Calendar calendar = Calendar.getInstance();
	private static int date = calendar.get(Calendar.DATE);
	private static int month = calendar.get(Calendar.MONTH) + 1;
	
	private static HashMap<Integer, ArrayList<Integer>> disneyBlackoutDates = new HashMap<Integer, ArrayList<Integer>>() {
		{
			put(1, new ArrayList<Integer>(Arrays.asList(1,2,3,4,5)));		//January
		    put(2, new ArrayList<Integer>(Arrays.asList(16, 17)));			//February
		    put(3, new ArrayList<Integer>(Arrays.asList(16, 23, 30))); 		//March

		}
	};
	
	public static boolean isDisneyBlackoutDate() {
		ArrayList<Integer> datesForMonth = disneyBlackoutDates.get(month); 
		
		if(datesForMonth.contains(date)) {
			return true;
		}		
		return false;
	}

}
