/**
 * Class to represent find longest streak of elements
 *  @author Divya Vudem, Manoj Kumar, Srabonti Chakraborty, Alwin Johns
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class LongestStreak {	
	 static int longestStreak(int[] A) { //using treeSet
		 // RT = O(nlogn).
	     // Ex: A = {1,7,9,4,1,7,4,8,7,1}.  longestStreak(A) return 3,
	     //    corresponding to the streak {7,8,9} of consecutive integers
	     //    that occur somewhere in A.
	 TreeSet<Integer> treeSet=new TreeSet<>();
		for (int k : A) //adding all the elements in the treeset
		{
			treeSet.add(k);
		}
		int k=0,dummy=0;
		int max=-1,intit,count = 1;
		boolean flag=true;
		Iterator<Integer> iter=treeSet.iterator(); //iteraator for treeset
		dummy=iter.next();
		while(iter.hasNext())
		{	
			int kk=iter.next();
			if(kk-dummy==1)
			{
				dummy=kk;
				count++;
			}
			else{
				dummy=kk;
				if(count>1&&count>max)
				{
					max=count;
				}
				count=1;
			}	
		}	
		if(count>max)//to cover loop termination without updating max value case
		{
			max=count;
		}
		 return max;
	   }
static int longestStreaknew(int[] A) { //using priority queue
	
	    PriorityQueue<Integer> q=new PriorityQueue<>();
		for(int k:A)
		{
			q.add(k);
		}
		int max=-1,intit,count = 1;
		while(!q.isEmpty())
		{
			intit=q.remove();
			if(!q.isEmpty()&&q.peek()-intit==1)
			{
				count++;
			}
			else{
				if(count>max)
				{
					max=count;
				}
				count=1;
			}
		}
	//	System.out.println(max);
		return max;
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);  //enter the data with first element representing size
            in = new Scanner(inputFile);
        } else {
        	System.out.println("enter the data starting with size");
            in = new Scanner(System.in);
        }
        int[] B=readArray(in);
		//int[] A = {10,7,9,11,4,3,1,7,4,8,7,1,0,-2,-1,2};
		int result=longestStreak(B);
	    System.out.println(result);	
	}
	private static int[] readArray(Scanner in) {
		// TODO Auto-generated method stub
		
		int size=in.nextInt(),count=0;         //size of the array
		int C[] =new int[size];
		while(count<size)
		{
			C[count]=in.nextInt();
			count++;
		}
		return C;
	}
}
