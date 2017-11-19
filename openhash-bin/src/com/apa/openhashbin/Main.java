/*
 * Main.java
 * Author: Lucas Cota
 * Description: openhash-bin Main.
 * Date: 2017-11-17
 * Modified: 2017-11-19
 */

package com.apa.openhashbin;

import java.util.ArrayList;

import com.apa.openhashbin.Helpers.Randoman;
import com.apa.openhashbin.Stream.OpenHashMap;
import com.apa.openhashbin.Stream.BinarySearchTree;

public class Main
{
	private static final int VALUE_RANGE = 1000;
	private static final int ARRAY_SIZE_TEST = 100;

	public static void main(String[] args)
	{
		OpenHashMap openHashMap = new OpenHashMap(VALUE_RANGE);
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		
		ArrayList<Integer> rangedArray = Randoman.RangedArray(VALUE_RANGE);
		ArrayList<Integer> rangedTestArray = Randoman.RandomicArray(ARRAY_SIZE_TEST, VALUE_RANGE);
		
		for (int i = 0; i < rangedArray.size(); i++)
		{
			int currentValue = rangedArray.get(i);
			
			binarySearchTree.Insert(currentValue);
			
			// Randomic key "name" of hash in a preset VALUE_RANGE.
			openHashMap.Insert(Randoman.Generate(VALUE_RANGE), currentValue);
		}
		
		/* Possible BinarySearchTree commands
		 * binarySearchTree.Insert(int nodeValue): Inserts a new node on tree.
		 * binarySearchTree.Delete(int nodeToDelete): Delete an exist node on tree.
		 * binarySearchTree.Find(int nodeValue): Find an exist node on tree.
		 * binarySearchTree.DisplayHeader(int VALUE_RANGE, int ARRAY_SIZE_TEST): Show a simple output header for results.
		 * binarySearchTree.Display(int currentValue, boolean searchResult): Show the detailed results of the test.
		 */
		
		binarySearchTree.DisplayHeader(VALUE_RANGE, ARRAY_SIZE_TEST);

		for (int currentValue : rangedTestArray)
		{
			boolean searchResult = binarySearchTree.Find(currentValue);
			
			binarySearchTree.Display(currentValue, searchResult);
		}
		
		/* Possible BinarySearchTree commands
		 * openHashMap.Insert(int key, int value): Inserts a new value on hash table.
		 * openHashMap.Delete(int key): Delete an exist value on hash table.
		 * openHashMap.Find(int key): Find an exist value on hash table.
		 * openHashMap.DisplayHeader(int VALUE_RANGE, int ARRAY_SIZE_TEST): Show a simple output header for results.
		 * openHashMap.Display(int currentValue, boolean searchResult): Show the detailed results of the test.
		 */
		
		openHashMap.DisplayHeader(VALUE_RANGE, ARRAY_SIZE_TEST);

		for (int currentValue : rangedTestArray)
		{
			int searchResult = openHashMap.Find(currentValue);
			
			openHashMap.Display(currentValue, searchResult);
		}
	}
}
