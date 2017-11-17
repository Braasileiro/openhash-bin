/*
 * TimeMeasure.java
 * Author: Lucas Cota
 * Description: Common time measures.
 * Date: 2017-11-17
 * Modified: 2017-11-17
 */

package com.apa.openhashbin.Helpers;

public class TimeMeasure
{
	public static long NanoTime()
	{
		return System.nanoTime();
	}
	
	public static float MeasureNanoToMilis(long nanoTime)
	{
		return ((System.nanoTime() - nanoTime) / 1000000f);
	}
}
