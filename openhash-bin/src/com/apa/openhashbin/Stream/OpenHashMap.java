/*
 * OpenHashMap.java
 * Author: Lucas Cota
 * Description: Open HashMap common operations.
 * Reference: <http://www.algolist.net/Data_structures/Hash_table/Open_addressing>
 * Date: 2017-11-19
 * Modified: 2017-11-19
 */

package com.apa.openhashbin.Stream;

import com.apa.openhashbin.Helpers.TimeMeasure;

public class OpenHashMap
{
	// HashTable
	private int tableSize;
	private HashEntry[] hashTable;
	
	// Counters
	private float searchTime;
	private int searchComparisons;




	// Default Constructor
	public OpenHashMap(int tableSize)
	{
		this.tableSize = tableSize;

		// Cria uma nova tabela hash de acordo com o tamanho passado pelo construtor.
		hashTable = new HashEntry[this.tableSize];
		
		// Seta todas as posições inicialmente como nulo.
		for (int i = 0; i < this.tableSize; i++)
		{
			hashTable[i] = null;
		}
	}




	/*
	 * Methods
	 */

	public void Insert(int key, int value)
	{
		// Fórmula padrão do cálculo da hash de acordo com a chave.
		int hashFormula = (key % tableSize);
		
		// Cria uma nova entrada na tablea hash de acordo com a fórmula padrão.
		hashTable[hashFormula] = new HashEntry(key, value);
	}
	
	public Boolean Delete(int key)
	{
		// Fórmula padrão usada para encontrar o valor da hash de acordo com a chave.
		int hashFormula = (key % tableSize);
		
		// Se encontrar, seta a entrada como removida, onde a chave e o valor são setados como -1.
		if (hashTable[hashFormula] != null)
		{
			hashTable[hashFormula] = HashRemovedEntry.getUniqueRemovedEntry();
			
			return true;
		}
		
		return false;
	}
		
	public int Find(int key)
	{
		// Time measure start time
		long startTime = TimeMeasure.NanoTime();

		// Fórmula padrão usada para encontrar o valor da hash de acordo com a chave.
		int hashFormula = (key % tableSize);

		// Se não encontrar, retorna -1.
		if (hashTable[hashFormula] == null)
		{
			searchComparisons++;

			// Time measure final time
			searchTime = TimeMeasure.MeasureNanoToMilis(startTime);

			return -1;
		}

		// Se encontrar, retorna o valor da hash de acordo com a key usada na fórmula.
		else
		{
			searchComparisons++;

			// Time measure final time
			searchTime = TimeMeasure.MeasureNanoToMilis(startTime);

			return hashTable[hashFormula].getValue();
		}
	}
	
	public void DisplayHeader(int VALUE_RANGE, int ARRAY_SIZE_TEST)
	{
		System.out.println
		(
			String.format
			(
				"%sOpenHashMap(ARRAY_SIZE_TEST: %d, VALUE_RANGE: %d)%s",
				System.lineSeparator(),
				ARRAY_SIZE_TEST,
				VALUE_RANGE,
				System.lineSeparator()
			)
		);
	}
	
	public void Display(int currentValue, int searchResult)
	{
		String searchValue;

		if (searchResult == -1)
		{
			searchValue = "Not Found";
		}
		else
		{
			searchValue = String.valueOf(searchResult);
		}

		System.out.println
		(
			String.format
			(
				"FindKey: %4d | FindTime: %f ms | FindComparisons: %4d | ValueOfKey: %s",
				currentValue,
				getSearchTime(),
				getSearchComparisons(),
				searchValue
			)
		);
	}




	/*
	 * Getters/Setters
	 */

	public float getSearchTime()
	{
		float currentSearchTime = searchTime;
		
		searchTime = 0;
		
		return currentSearchTime;
	}

	public int getSearchComparisons()
	{
		int currentSearchComparisons = searchComparisons;
		
		searchComparisons = 0;

		return currentSearchComparisons;
	}
}




// HashEntry Structure
class HashEntry
{
	private int key;
	private int value;
	
	public HashEntry(int key, int value)
	{
		this.key = key;
		this.value = value;
	}

	public int getKey()
	{
		return key;
	}

	public void setKey(int key)
	{
		this.key = key;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}
}




/*
 * HashTable Removed Entries
 * A entrada removida da HashTable é setada para -1 em sua chave e valor.
 */
class HashRemovedEntry extends HashEntry
{
	private static HashRemovedEntry hashEntry = null;

	private HashRemovedEntry()
	{
		super(-1, -1);
	}
	
	public static HashRemovedEntry getUniqueRemovedEntry()
	{
		if (hashEntry == null)
		{
			hashEntry = new HashRemovedEntry();
		}

		return hashEntry;
	}
}
