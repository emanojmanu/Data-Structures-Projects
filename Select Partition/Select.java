package optionalproject4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Select<T extends Comparable<? super T>> {

	public static<T extends Comparable<? super T>> int partition(T[] x,int p,int r)
	{
		int q=0;
		
			q=(int)((Math.random()*(r-p))+p);
			swap(x,q,r);
			T pivot =x[r];
			int j=p-1;
			for(int k=p;k<r;k++)
			{
				if(x[k].compareTo(pivot)<0)
				{
					j++;
				swap(x,j,k);
				}
			}
			j++;
			swap(x,j,r);
		return j;
	}
	private static<T extends Comparable<? super T>> void swap(T[] x, int q, int r) {
		// TODO Auto-generated method stub
		T temp=x[q];
		x[q]=x[r];
		x[r]=temp;
	}

	public static<T extends Comparable<? super T>> T select(T[] arr, int p, int r, int k)
	// Find the k largest elements of arr[p..r].  Returns index q.
	   // The k largest elements are found in arr[q..r].
	{
		int pivot=partition(arr, p, r);
		
		T pos = null;
		if(r-pivot>=k)
		{
			pos=select(arr,pivot+1,r,k);// 4 0 5 3 6 7 8 9 8 * 9
		}
		else if(r-pivot+1==k)
		{
			return arr[pivot];
			
		}
		else{
			pos=select(arr,p,pivot-1,k-(r-pivot+1));
		}

	return pos;	
	}
	public static<T extends Comparable<? super T>> T selectPriority(List<T> arr, int k)
	{
		List<T> y=arr;
		Iterator i=y.iterator();
		Queue<Integer> q=new PriorityQueue<Integer>();
		int count=0;
		while(i.hasNext()&&count<k)
		{		
			q.add((Integer) i.next());
			count++;
		}
		while(i.hasNext())
		{
			int temp=(Integer) i.next();
		if(q.peek().compareTo(temp)<0)
		{
			q.poll();
			q.add(temp);
		}
		}		
		return (T) q.poll();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] x={2,12,3,5,89,21,-9};
		int select=5;
		//int k=select(x, 0, x.length-1,select);
	//	System.out.println(k);
		
		List<Integer> y=new ArrayList<>();
		Integer[] x2=new Integer[524288];
		for(int i=0;i<524288;i++)
		{
			
			int dummy=0;
			dummy=(int)((Math.random()+1)*524288);
			y.add(dummy);
			x2[i]=dummy;
		}
		Timer t1=new Timer();
		Timer t2=new Timer();
		t1.start();
		System.out.println("result "+select(x2, 0, x2.length-1,102220));
		t1.end();
		System.out.println("first time "+t1);
		t2.start();
		System.out.println("result2 "+selectPriority(y, 102220));
		t2.end();
		System.out.println("second time "+t2);
		
		/*selecting 102 largest result 1048483
		first time Time: 159 msec.
		Memory: 25 MB / 123 MB.
		result2 1048483
		second time Time: 61 msec.
		Memory: 27 MB / 123 MB.*/
     /* searching 102220th largest element		result 946951
		first time Time: 177 msec.
		Memory: 25 MB / 123 MB.
		result2 946951
		second time Time: 468 msec.
		Memory: 30 MB / 123 MB.
*/

		}

}
