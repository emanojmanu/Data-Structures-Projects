import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class MinComparatoro2<T extends Comparable<? super T>> implements Comparator<T>{
	 
	@Override
	public int compare(T o1, T o2) {
		// TODO Auto-generated method stub
		return o1.compareTo(o2);
	}
}

class MaxComparatoro2<T extends Comparable<? super T>> implements Comparator<T>{
	@Override
	public int compare(T o1, T o2) {
		// TODO Auto-generated method stub
		return o2.compareTo(o1);
	}
}

public class HeapMedian1<T extends Comparable<? super T>> {

	PriorityQueue<T> minq;
	PriorityQueue<T> maxq;
	boolean removeFlag=true;
	public HeapMedian1() {
		// TODO Auto-generated constructor stub
	minq=new PriorityQueue<>(new MinComparatoro2());
	maxq=new PriorityQueue<>(new MaxComparatoro2());
	
	}
	public <T extends Comparable<? super T>> void findMedian(T[] a)
	{
int position=0;
	T med=null;		
//maxq.add((T) b[2]);
		/*if(a[0].compareTo(a[1])<0)
		{
		maxq.add( a[0]);
		minq.add(a[1]);
		}
		else{
			this.maxq.add(a[1]);
			this.minq.add(a[0]);
		}
	//int med=0;
		//int med=(a[1]<a[2]?a[1]:a[2]);
		if(a[1]<a[2])
		{
			this.minq.remove();
			this.minq.add(a[2]);
			med=a[1];
		}
		else{
			med=a[2];
		}
		for(int i=3;i<a.length;i++)
		{	
			//System.out.println(a[i]);
			if(a[i]>med)
			{
				if(position!=2)
				{
					this.minq.add(a[i]);
					position=2;
				}
				else
				{
					this.maxq.add(med);
					med=this.minq.remove();
					this.minq.add(a[i]);
					position=0;
				}
				
			}
			else
			{
				if(position!=1)
				{
					this.maxq.add(a[i]);
					position=1;
				}
				else{
					this.minq.add(med);
					med=this.maxq.remove();
					this.maxq.add(a[i]);
					position=0;
				}
				
			}
			
		}*/

	}
	
	public T remoceMedian()
	{
		int l=this.maxq.size();
		int r=this.minq.size();
		
		if(l>=r)
		{
			return this.maxq.remove();
		}
		else
		{
			return this.minq.remove();
		}
		//return null;
	}
	
	public T removeMedian(T a[],boolean f)
	{
	
		if(this.removeFlag)
		{
		
			if (f) {
				
				return this.maxq.remove();
			}
			else{
			this.removeFlag=false;
			//System.out.println("first");
			return this.maxq.remove();
			}
		}
		else{
			this.removeFlag=true;
			return this.minq.remove();
		
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub -1 0 1 2 3 4 5 6 7 9 10 11 11 21 23 46 56 110
		Integer[] a={4,10,3,-1,11,23,-8,7,1,6,56,21,46,0,3,2,11,110,9};
		Integer[] b=new Integer[20];
		for(int i=0;i<20;i++)
		{
			b[i]=(int) ((1+Math.random())*100);
		}
		HeapMedian1<Integer> obj=new HeapMedian1<>();
		obj.findMedian(a);
	
		int position=0;
		int len=a.length;
		if(a[0]<a[1])
		{
		obj.maxq.add(a[0]);
		obj.minq.add(a[1]);
		}
		else{
			obj.maxq.add(a[1]);
			obj.minq.add(a[0]);
		}
	int med=0;
		
		if(obj.minq.peek()<a[2])
		{
			Integer k=obj.minq.remove();
			obj.minq.add(a[2]);
			med=k;
		}
		else if(obj.maxq.peek()>a[2]){
			Integer k=obj.maxq.remove();
			obj.maxq.add(a[2]);
			med=k;
		}
		else{
			med=a[2];
		}
		for(int i=3;i<a.length;i++)
		{	
			
			if(a[i]==7)
			{
				System.out.println("c");
			}
			if(a[i]>med)
			{
				if(position!=2)
				{
					obj.minq.add(a[i]);
					position=2;
				}
				else
				{
					obj.maxq.add(med);
					if(a[i]<obj.minq.peek())
					{
						med=a[i];
					}
					else{
					med=obj.minq.remove();
					obj.minq.add(a[i]);
					}
					position=2;
				}
				
			}
			else
			{
				if(position!=1)
				{
					obj.maxq.add(a[i]);
					position=1;
				}
				else{
					obj.minq.add(med);
					if(a[i]>obj.maxq.peek())
					{
						med=a[i];
					}
					else{

						med=obj.maxq.remove();//not comoparing maxqueue and a[i]
						obj.maxq.add(a[i]);
							
					}position=1;
				}
				
			}
			//median is added to smaller heap(Maxheap)
		}
		
		
int diff=obj.maxq.size()-obj.minq.size();
boolean maxat=(obj.maxq.size()>obj.minq.size()); //true if maxq is big
while(diff>1||diff<-1)
{
	if(maxat)
	{
		obj.minq.add(med);
		med=obj.maxq.remove();
		diff--;
	}
	else{
		obj.maxq.add(med);
		med=obj.minq.remove();
		diff++;
	}
}
	
		if(obj.maxq.size()>obj.minq.size())
		{
			obj.minq.add(med);
		}
		else if(obj.maxq.size()==obj.minq.size())
		{
			//Integer dummy=obj.minq.remove();
			obj.maxq.add(med);
			//obj.minq.add(med);
		}
		else{
			obj.maxq.add(med);
		}
		med=(obj.minq.size()>obj.maxq.size()?obj.minq.peek():obj.maxq.peek());
		Arrays.sort(a);
		for(int i=0;i<a.length;i++)
		{
		System.out.println(a[i]);
		}
		boolean f=(a.length%2==0);
		System.out.println(med);
		for(int i=0;i<a.length;i++)
		{
		System.out.println("median remove"+obj.remoceMedian());
		}
	}
}
