/*
 * Randoman.java
 * Author: Lucas Cota
 * Description: openhash-bin Main.
 * Date: 2017-11-17
 * Modified: 2017-11-17
 */

package com.apa.openhashbin;

import java.util.ArrayList;

import com.apa.openhashbin.Helpers.Randoman;
import com.apa.openhashbin.Stream.BinarySearchTree;

public class Main
{
	private static final int VALUE_RANGE = 1000;
	private static final int ARRAY_SIZE_TEST = 100;

	public static void main(String[] args)
	{
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		
		ArrayList<Integer> rangedArray = Randoman.RangedArray(VALUE_RANGE);
		ArrayList<Integer> rangedTestArray = Randoman.RandomicArray(ARRAY_SIZE_TEST, VALUE_RANGE);
		
		for (int currentValue : rangedArray)
		{
			binarySearchTree.Insert(currentValue);
		}
		
		for (int currentValue : rangedTestArray)
		{
			boolean searchResult = binarySearchTree.Find(currentValue);
			
			System.out.println
			(
				String.format
				(
					"Value: %4d | Time: %f ms | Comparations: %4d | Find: %5s",
					currentValue,
					binarySearchTree.getSearchTime(),
					binarySearchTree.getSearchComparations(),
					searchResult
				)
			);
		}
	}
}
