package org.alanvilla.projects.disneyRestJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

public class BlackoutDates {

	private static Calendar calendar = Calendar.getInstance();
	private static int todaysDate = calendar.get(Calendar.DATE);
	private static int month = calendar.get(Calendar.MONTH) + 1;
	
	private static ArrayList<Integer> numDays31= new ArrayList<Integer>(){
	
		{
			for (int i=1; i<32; i++) {
				add(new Integer(i));
			}
		}
	};
	
	//days 21 -30
	private static ArrayList<Integer> secondHalfJuneDays = new ArrayList<Integer>() {
		{
			for(int i=20; i<30; i++) {
				add(new Integer(numDays31.get(i)));
			}
		}
		
	};
	
	//first days of August 
	private static ArrayList<Integer> daysInAugust = new ArrayList<Integer>() {
		{
			for (int i=0;  i<18; i++) {
				add(new Integer(numDays31.get(i)));
			}
		}
	};
	


	private static HashMap<Integer, ArrayList<Integer>> disneyDeluxeBlackoutDates = new HashMap<Integer, ArrayList<Integer>>() {
		{
			put(1, new ArrayList<Integer>(Arrays.asList(1,2,3,4,5)));				//January
			put(2, new ArrayList<Integer>(Arrays.asList(16, 17)));					//February
			put(3, new ArrayList<Integer>(Arrays.asList(16, 23, 30))); 				//March
			put(4, new ArrayList<Integer>(Arrays.asList(6, 13, 19, 20, 27))); 		//April
			put(5, new ArrayList<Integer>(Arrays.asList(4, 11, 18, 25, 26)));		//May
			
			ArrayList<Integer> blackoutDaysInJune = new ArrayList<Integer>(Arrays.asList(1, 7, 8, 9, 14, 15, 16));
			blackoutDaysInJune.addAll(secondHalfJuneDays); //21-30
			put(6, blackoutDaysInJune); 	//June
		
			put(7, numDays31);				//July

			daysInAugust.addAll(Arrays.asList(24,31));
			put(8, daysInAugust);			//August
			
			put(9, new ArrayList<Integer>(Arrays.asList(1)));	//September
			put(10, new ArrayList<Integer>(Arrays.asList(5,12,19,26)));	//October
			put(11, new ArrayList<Integer>(Arrays.asList(28,29,30)));	//November
			
			ArrayList<Integer> blackoutDaysInDecember = new ArrayList<Integer>(Arrays.asList(7, 14, 15));
			blackoutDaysInDecember.add(20);
			blackoutDaysInDecember.addAll(secondHalfJuneDays); //21-30
			blackoutDaysInDecember.add(31);
			
			put(12, blackoutDaysInDecember);	//December
		}
	};
	
	private static HashMap<Integer, ArrayList<Integer>> californiaAdventureDeluxeBlackoutDates = new HashMap<Integer, ArrayList<Integer>>() {
		{
			put(1, new ArrayList<Integer>(Arrays.asList(1,2,3,4,5)));				//January
			put(2, new ArrayList<Integer>(Arrays.asList(16, 17)));					//February
			put(3, new ArrayList<Integer>(Arrays.asList(16, 23, 30))); 				//March
			put(4, new ArrayList<Integer>(Arrays.asList(6, 13, 19, 20, 27))); 		//April
			put(5, new ArrayList<Integer>(Arrays.asList(4, 11, 18, 25, 26)));		//May
			
			put(6, null); 	//June
			put(7, null);	//July

			put(8, new ArrayList<Integer>(Arrays.asList(31)));			//August		
			put(9, new ArrayList<Integer>(Arrays.asList(1)));			//September
			put(10, null);												//October
			put(11, new ArrayList<Integer>(Arrays.asList(28,29,30)));	//November
			
			ArrayList<Integer> blackoutDaysInDecember = new ArrayList<Integer>(Arrays.asList(7, 14, 15));
			blackoutDaysInDecember.add(20);
			blackoutDaysInDecember.addAll(secondHalfJuneDays); //21-30
			blackoutDaysInDecember.add(31);
			
			put(12, blackoutDaysInDecember);	//December
		}
	};

	public static boolean isDisneyBlackoutDate() {
		ArrayList<Integer> datesForMonth = disneyDeluxeBlackoutDates.get(month); 

		if(datesForMonth.contains(todaysDate)) {
			return true;
		}		
		return false;
	}
	
	public static boolean isCaliforniaAdventureBlackoutDate() {
		ArrayList<Integer> datesForMonth = californiaAdventureDeluxeBlackoutDates.get(month); 
		
		if(datesForMonth.contains(todaysDate)) {
			return true;
		}		
		return false;

	}

}
