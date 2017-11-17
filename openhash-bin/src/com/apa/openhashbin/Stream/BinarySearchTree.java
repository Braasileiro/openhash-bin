/*
 * BinarySearchTree.java
 * Author: Lucas Cota
 * Description: Binary Search Tree common operations.
 * Reference: <http://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/>
 * Date: 2017-11-17
 * Modified: 2017-11-17
 */

package com.apa.openhashbin.Stream;

import com.apa.openhashbin.Helpers.TimeMeasure;

class Node
{
	int nodeValue;
	Node nodeLeft;
	Node nodeRight;
	
	public Node(int nodeValue)
	{
		this.nodeLeft = null;
		this.nodeRight = null;
		this.nodeValue = nodeValue;
	}
}

public class BinarySearchTree
{
	// Root
	private Node rootNode;
	
	// Counters
	private float searchTime;
	private int searchComparisons;




	// Default Constructor
	public BinarySearchTree()
	{
		this.rootNode = null;
	}




	/*
	 * Methods
	 */

	public void Insert(int nodeValue)
	{
		Node insertNode = new Node(nodeValue);
		
		// Verifica se já possui um nó raiz.
		if (rootNode == null)
		{
			rootNode = insertNode;
			
			return;
		}
		
		Node nextNode = null;
		Node currentNode = rootNode;
		
		/*
		 * Verifica se o nó que está sendo inserido é menor ou maior que a raiz.
		 * Se menor, insere na esquerda.
		 * Se maior, insere na direita.
		*/
		while (true)
		{
			nextNode = currentNode;

			if (nodeValue < currentNode.nodeValue)
			{				
				currentNode = currentNode.nodeLeft;

				if (currentNode == null)
				{
					nextNode.nodeLeft = insertNode;

					return;
				}
			}
			else
			{
				currentNode = currentNode.nodeRight;
				
				if (currentNode == null)
				{
					nextNode.nodeRight = insertNode;

					return;
				}
			}
		}
	}
	
	public Boolean Delete(int nodeToDelete)
	{
		Node nextNode = rootNode;
		Node currentNode = rootNode;
		
		boolean isLeftNode = false;
		
		// Verifica se o "nodeToDelete" existe na árvore e marca se possui nós a esquerda ou direita.
		while (currentNode.nodeValue != nodeToDelete)
		{
			nextNode = currentNode;
			
			if (currentNode.nodeValue > nodeToDelete)
			{
				isLeftNode = true;
				currentNode = currentNode.nodeLeft;
			}
			else
			{
				isLeftNode = false;
				currentNode = currentNode.nodeRight;
			}
			
			if (currentNode == null)
			{
				return false;
			}
		}
			
		// Verifica se o "nodeToDelete" não possui filhos.
		if (currentNode.nodeLeft == null && currentNode.nodeRight == null)
		{
			// Se o nó for a raiz, retorna nulo.
			if (currentNode == rootNode)
			{
				return null;
			}
			
			// Se possuir filhos que são NÓS FOLHA a esquerda ou direita, marca-os como nulos também.
			if (isLeftNode == true)
			{
				nextNode.nodeLeft = null;
			}
			else
			{
				nextNode.nodeRight = null;
			}
		}
		
		// Verifica se a direita do "nodeToDelete" possui filhos.
		else if (currentNode.nodeRight == null)
		{
			// Se o nó for raiz, seu filho a esquerda vira o novo nó raiz.
			if (currentNode == rootNode)
			{
				rootNode = currentNode.nodeLeft;
			}
			
			// Se possuir filhos a esquerda, "sobe" os nós adjacentes para cima.
			else if (isLeftNode)
			{
				nextNode.nodeLeft = currentNode.nodeLeft;
			}
			else
			{
				nextNode.nodeRight = currentNode.nodeRight;
			}
		}
		
		// Verifica se a esquerda do "nodeToDelete" possui filhos.
		else if (currentNode.nodeLeft == null)
		{
			// Se o nó for raiz, seu filho a direita vira o novo nó raiz.
			if (currentNode == rootNode)
			{
				rootNode = currentNode.nodeRight;
			}
			
			// Se possuir filhos a esquerda, "sobe" os nós adjacentes para cima.
			else if (isLeftNode)
			{
				nextNode.nodeLeft = currentNode.nodeRight;
			}
			else
			{
				nextNode.nodeRight = currentNode.nodeRight;
			}
		}
		
		// Verifica se possui no mínimo um elemente na árvore a direita.
		else if (currentNode.nodeLeft != null && currentNode.nodeRight != null)
		{
			// Procura os nós que serão substituídos após a exclusão do pai.
			Node replaceNode = FindReplaceNode(currentNode);
			
			// Se o nó for raiz, seu sucessor vira o novo nó raiz.
			if (currentNode == rootNode)
			{
				rootNode = replaceNode;
			}
			
			// Se possuir filhos a esquerda, "sobe" os nós adjacentes para cima.
			else if (isLeftNode)
			{
				nextNode.nodeLeft = replaceNode;
			}
			else
			{
				nextNode.nodeRight = replaceNode;
			}
			
			replaceNode.nodeLeft = currentNode.nodeLeft;
		}
		
		return true;
	}
	
	public Boolean Find(int nodeValue)
	{
		// Time measure start time
		long startTime = TimeMeasure.NanoTime();

		Node currentNode = rootNode;
		
		// Verifica pela raiz se o nó está a esquerda ou direita.
		while (currentNode != null)
		{
			if (currentNode.nodeValue == nodeValue)
			{
				searchComparisons++;

				// Time measure final time
				searchTime = TimeMeasure.MeasureNanoToMilis(startTime);

				return true;
			}
			else if (currentNode.nodeValue > nodeValue)
			{
				searchComparisons++;

				currentNode = currentNode.nodeLeft;
			}
			else
			{
				searchComparisons++;

				currentNode = currentNode.nodeRight;
			}
		}
		
		// Time measure final time
		searchTime = TimeMeasure.MeasureNanoToMilis(startTime);

		// Se não encontrá-lo, retorna false.
		return false;
	}
	
	private Node FindReplaceNode(Node nodeToDelete)
	{
		Node replaceNode = null;
		Node nextReplaceNode = null;
		Node currentNode = nodeToDelete.nodeRight;
		
		while (currentNode != null)
		{
			nextReplaceNode = replaceNode;
			replaceNode = currentNode;
			currentNode = currentNode.nodeLeft;
		}
		
		// Verifica se o novo nó possui filhos a direita, se não houver, coloca-o no "nextReplaceNode".
		if (replaceNode != nodeToDelete.nodeRight)
		{
			nextReplaceNode.nodeLeft = replaceNode.nodeRight;
			replaceNode.nodeRight = nodeToDelete.nodeRight;
		}
		
		return replaceNode;
	}
	
	public void ShowResultHeader(int VALUE_RANGE, int ARRAY_SIZE_TEST)
	{
		System.out.println
		(
			String.format
			(
				"%sBinarySearchTree(ARRAY_SIZE_TEST: %d, VALUE_RANGE: %d) : Boolean%s",
				System.lineSeparator(),
				VALUE_RANGE,
				ARRAY_SIZE_TEST,
				System.lineSeparator()
			)
		);
	}
	
	public void ShowResult(int currentValue, boolean searchResult)
	{
		System.out.println
		(
			String.format
			(
				"Value: %4d | Time: %f ms | Comparisons: %4d | Found: %5s",
				currentValue,
				getSearchTime(),
				getSearchComparisons(),
				searchResult
			)
		);
	}





	/*
	 * Getters/Setters
	 */

	public float getSearchTime()
	{
		return searchTime;
	}

	public int getSearchComparisons()
	{
		return searchComparisons;
	}
}
