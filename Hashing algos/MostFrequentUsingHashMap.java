//mxe150630
//dxv151430
//sxv154030
//axj155830

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

class Count{
	int count;
	int index;
	public Count(int ind)
	{
		index=ind;
		count=1;
	}
	public void increment()
	{
		count++;
	}
}
public class MostFrequentUsingHashMap<T extends Comparable<? super T>> {
	public static<T> T mostFrequent(T[] arr)
	{
		Count max=new Count(-1);
		T maxat=null;
		HashMap<T, Count> hm=new HashMap<>();//count object is created to make note of the position and count of an element
		int counter=0;// count is created for distinct keys with keys as array elements 
		for(T k:arr)
		{	
			Count value=hm.get(k);
			if(value!=null)   // if elemetns is there previously
			{
				value.increment();// incremented and the look for the maxcount is updated if needed.
				if(value.count>max.count)
				{
					maxat=k;
					max=value;
				}
				else if(value.count==max.count)  // maxatt is updated based on the index of the cont
				{
					if(value.index<max.index)
					{
					maxat=k;
					max=value;
					}
				}
			}
			else{
				value=new Count(counter);
				
				hm.put(k, value);
			}
			counter++;
		}
		
		return maxat;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//Integer[] ii={2,3,2,4,2,52,3,3,4,4,42,423,4,2};2 3 2 4 2 52 3 3 4 4 42 423 4 2
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
	      }
	     int k=mostFrequent(ii);
	  //   System.out.println("frequent element "+k);
	}
}
