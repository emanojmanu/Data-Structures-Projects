/**
 * Class to find number of pairs
 *  @author Divya Vudem, Manoj Kumar, Srabonti Chakraborty, Alwin Johns
 *
 */

import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Q1 
{
	private static<T extends Comparable<? super T>> int getpairscount(T[] arr, int sum) 
	{
		//Tree Map 
		TreeMap<T,Integer> treemap=new TreeMap();
		int k=0, count=0;
		treemap.put(arr[0], 1);
		/*Invariant
		 * Insert each unique element from array into tree map
		 * Increment the count of element availability in the array
		 */
		for(int i=1;i<arr.length;i++)
		{
			if(treemap.containsKey(arr[i]))
			{
				k= treemap.get(arr[i]);
				treemap.put(arr[i],++k);
			}	
			else treemap.put(arr[i],1);
		}	
		/*
		 * 	Traverse through the tree map
		 * Check if the difference between sum to be found and the element exists in hash map
		 * If exists increment the count	
		 */
		for(Entry<T, Integer> entry : treemap.entrySet()) 
		{
		T key = entry.getKey();
		Integer diff=sum-(Integer)key;
		if(treemap.containsKey(diff))
		{
			if(diff==key)
				count=count+entry.getValue()-1; //increment the value
			else
				count=count+entry.getValue();}
		}
			
		return count;
	}
	
	public static void main(String[] args)
	{
	//Integer[] arr={1,2,2,4,3,2,3};
	 System.out.print("Enter the count of number of elements: ");
     Scanner in;
     in = new Scanner(System.in);
     int numEle = in.nextInt();
     Integer[] arr = new Integer[numEle];
     System.out.println("Enter Elements : ");
     for (int c=0; c<numEle;c++)
     {    	 
    	 arr[c] = in.nextInt(); 
     }
     
     System.out.println("Enter sum of elements : ");
     in = new Scanner(System.in);
     int sum = in.nextInt();
	int count=getpairscount(arr,sum);
	System.out.println("Number of pairs : "+count);
	}
}
