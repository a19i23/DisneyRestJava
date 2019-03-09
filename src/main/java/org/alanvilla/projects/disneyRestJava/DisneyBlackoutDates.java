package org.alanvilla.projects.disneyRestJava;

import java.util.HashMap;

public class DisneyBlackoutDates {
	
	private HashMap<Integer, int[]> disneyBlackoutDates = new HashMap<Integer, int[]>() {
		{
			put(1, new int[]{1,2,3,4,5});		//January
		    put(2, new int[] {16, 17});			//February

		}
	};

}
