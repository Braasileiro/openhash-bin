/*
 * Randoman.java
 * Author: Lucas Cota
 * Description: O cara que randomiza os trem. :)
 * Date: 2017-11-17
 * Modified: 2017-11-17
 */

package com.apa.openhashbin.Helpers;

import java.util.Random;
import java.util.ArrayList;

public class Randoman
{
	private static Random randomValue = new Random();

	// Random values
	public static Integer Generate()
	{
		return randomValue.ints().limit(1).findFirst().getAsInt();
	}

	// Random values in a range
	public static Integer Generate(int valueRange)
	{
		return randomValue.ints(1, (valueRange + 1)).limit(1).findFirst().getAsInt();
	}
	
	// Randomic sized array with random values 
	public static ArrayList<Integer> RandomicArray(int arraySize)
	{
		ArrayList<Integer> currentArray = new ArrayList<Integer>();
		
		for (int i = 1; i <= arraySize; i++)
		{
			currentArray.add(Generate());
		}
		
		return currentArray;
	}
	
	// Randomic sized array with ranged random values
	public static ArrayList<Integer> RandomicArray(int arraySize, int valueRange)
	{
		ArrayList<Integer> currentArray = new ArrayList<Integer>();
		
		for (int i = 1; i <= arraySize; i++)
		{
			currentArray.add(Generate(valueRange));
		}
		
		return currentArray;
	}
	
	// Randomic array with size of ranged values
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
