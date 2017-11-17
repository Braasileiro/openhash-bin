/*
 * Randoman.java
 * Author: Lucas Cota
 * Description: Common random generators.
 * Date: 2017-11-17
 * Modified: 2017-11-17
 */

package com.apa.openhashbin;

import java.util.Random;
import java.util.ArrayList;

public class Randoman
{
	private static Random randomValue = new Random();

	public static Integer Generate()
	{
		return randomValue.ints().limit(1).findFirst().getAsInt();
	}

	public static Integer Generate(int valueRange)
	{
		return randomValue.ints(1, (valueRange + 1)).limit(1).findFirst().getAsInt();
	}
	
	public static ArrayList<Integer> RangedArray(int valueRange)
	{
		ArrayList<Integer> currentArray = new ArrayList<Integer>();
		
		for (int i = 1; i <= valueRange; i++)
		{
			currentArray.add(Generate(valueRange));
		}
		
		return currentArray;
	}
}
