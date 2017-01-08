//mxe150630
//dxv151430
//sxv154030
//axj155830

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
//import java.util.Set;

/*
public  Implement separate chaining and 2-choice hashing algorithms.
Compare the maximum list length between the two algorithms.
Try different values of N > 1M, and insert 10N random elements. 
Compare the maximum size of the lists and the number of empty lists
between the two approaches.  Suggested code organization:

	class HashTable<T>  // separate chaining
	class TwoChoice<T extends HashCodes<? super T>>  // 2-choice hashing
	public interface HashCodes<T> {
	    int h1(T x);  // Hash function 1
	    int h2(T x);  // Hash function 2
	}
*/

public class TwoChoice2<T extends Comparable<? super T>> implements HashCodes<T> // 2-choice hashing
{
	    static int initialSize = 16;
	    class HashEntry<T> {
		//E element;
		List<T> chain;
		boolean occupied;
		HashEntry(T x) {
		   // element = x;
		    occupied = true;
		    chain=new ArrayList<>();
		    chain.add(x);
		}
	    }

	    HashEntry<T>[] table;
	    int size, maxSize;
	   // Set<T> overflow;
	    TwoChoice2() {
		table = new HashEntry[initialSize];
		size = 0;  maxSize = initialSize;
		//overflow = new HashSet<>();
	    }

	    TwoChoice2(int n) {
		table = new HashEntry[n];
		size = 0;  maxSize = n;
	//	overflow = new HashSet<>();
	    }

		public int h1(T x) {
			// TODO Auto-generated method stub
			return x.hashCode()%11;
		}

		public int h2(T x) {
			// TODO Auto-generated method stub
			return x.hashCode()%13;
		}

	    int find(T x) {
		// find x in hash table and return index of the element if found,
		// otherwise return the index where it stopped
		
	    	int hash1=h1(x);
	    	int hash2=h2(x);
	    	if(this.table[hash2]!=null)
			{
				if(this.table[hash2].chain.contains(x))
				{
					return hash2;
				}
			}
	    	
	    	if(this.table[hash1]!=null)
			{
				if(this.table[hash1].chain.contains(x))
				{
					return hash1;
				}
			}
		//return null;
	    	return hash1;//default
	    }

	    public boolean add(T x) {
	    	
	    	int hash1=h1(x);
	    	int hash2=h2(x);
	    	// to look for duplicates
	    	if(this.table[hash2]!=null)
			{
				if(this.table[hash2].chain.contains(x))  //first case is to handle the deleted in which obj is not null element alone is null
				{
					return false;
				}
			}
	    	
	    	if(this.table[hash1]!=null)
			{
				if(this.table[hash1].chain.contains(x))
				{
					return false;
				}
			} 
			//to add elements to add at the starting of the block
	     		if(this.table[hash1]==null)
	    		{
	    			
	    		    this.table[hash1]=new HashEntry<T>(x);
	    		    return true;
	    		}
	    		else if(this.table[hash2]==null)
	    		{
	    			this.table[hash2]=new HashEntry<T>(x);
	    			return true;
	    		}
	    	
	    	if(this.table[hash1].chain.size()<=this.table[hash2].chain.size())
	    	{
	    		
	    		this.table[hash1].chain.add(x);
	    	}
	    	else{
	    		this.table[hash2].chain.add(x);
	    	}
	    	return true;
	    }

	    public T get(T x) {
	    	int hash1=h1(x);
	    	int hash2=h2(x);
	    	if(this.table[hash2]!=null)
			{
				if(this.table[hash2].chain.contains(x))
				{
					return x;
				}
			}
	    	
	    	 if(this.table[hash1]!=null)
			{
				if(this.table[hash1].chain.contains(x))
				{
					return x;
				}
			}
		return null;
	    }

	    public T remove(T x) {
		// If x exists, remove it from the hash table and return it.
		// Otherwise return null;
	    	int hash1=h1(x);
	    	int hash2=h2(x);
	    	if(this.table[hash2]!=null)
			{
	    		int possibleindex=this.table[hash2].chain.indexOf(x);
				//to check wheteth element is deleted in which case the table.element is null
				if(possibleindex>-1)
				{
					this.table[hash2].chain.remove(possibleindex);
					return x;
				}
			
			}
	    	
	    	if(this.table[hash1]!=null)
			{
	    		int possibleindex=this.table[hash1].chain.indexOf(x);
				//to check wheteth element is deleted in which case the table.element is null
				if(possibleindex>-1)
				{
					this.table[hash1].chain.remove(possibleindex);
					return x;
				}
	
			}
		return null;
	    }

	    public boolean contains(T x) {
		
	    	int hash1=h1(x);
	    	int hash2=h2(x);
	    	if(this.table[hash2]!=null)
			{
				if(this.table[hash2].chain.contains(x))
				{
					return true;
				}
			}
	    	
	    	if(this.table[hash1]!=null)
			{
				if(this.table[hash1].chain.contains(x))
				{
					return true;
				}
			}
		//return null;
	    	return false;
	    }
	    
	    public static void main(String args[]) throws FileNotFoundException
	    {
	    	
	    	TwoChoice2<Integer> dh=new TwoChoice2();
	    	Scanner in;//enter the size follwed by the elements in the array
	        if (args.length > 0) {
	            File inputFile = new File(args[0]);
	            in = new Scanner(inputFile);
	        } else {
	            in = new Scanner(System.in);
	        }
	      int size=in.nextInt();
	      Integer[] ii=new Integer[size];
	      for(int i=0;i<size;i++)
	      {
	    	  ii[i]=in.nextInt();
	          dh.add(ii[i]);
	      }
	      
	      for(int i=0;i<ii.length;i++)
	      {
	    	  if(i==6|i==9||i==14)
	    	  {
	    		  dh.remove(ii[i]);
	    	  }
	    	  System.out.println(dh.contains(ii[i]));
	      }
	     // System.out.println(dh.contains(85475)+"extra qualififcation");
	    }

}