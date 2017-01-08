import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;


class Comparatorobj1<T extends Comparable<? super T>> implements Comparator<inputg<T>>{
	 
//	@Override
//	public int compare(T o1, T o2) {
//		return o1.compareTo(o2);
//	}

	@Override
	public int compare(inputg<T> o1, inputg<T> o2) {
		// TODO Auto-generated method stub
		return o1.element.compareTo(o2.element);
	}
}


class inputg<T extends Comparable<? super T>>{
	T element;
	int index;
	
	inputg(T x,int i)
	{
		element=x;
		index=i;
	}
}
public class KwaySorti<T extends Comparable<? super T>> {


	public static<T extends Comparable<? super T>> void kMergeSort(inputg<T>[] A, int k)
	{
		int p=0;int r=A.length-1;
		kmerge(A,p,r,k);		
	}
	private static<T extends Comparable<? super T>> void kmerge(inputg<T>[] a, int p, int r,int k) {
		// TODO Auto-generated method stub
		//boolean h=false;
		Comparable[] b=new Comparable[a.length];
		PriorityQueue<inputg> q=new PriorityQueue<>(new Comparatorobj1());
			
		if(r>=a.length)
		{
			r=a.length-1;
			Arrays.sort(a, p, r+1, new Comparatorobj1<T>());
			return;
		}
		if(r>p&&r-p<=k)
		{
			if(r==a.length-1)
			{
			Arrays.sort(a, p, r+1, new Comparatorobj1<T>());
			}
			else{
				Arrays.sort(a, p, r, new Comparatorobj1<T>());
				
			}
			int l=p;
			while(l<r+1)
			{
				
				a[l].index=l;
				l++;
			}
		} 
 		else if(r>p&&r<a.length)
		{
 			int factor=(r-p)/k;
 			int init=p;
 			System.out.println("p  "+p+" r"+r);
 			for(int index=0;index<factor-1;index++)
			{
				init=p+(index*factor);
				System.out.println("intin "+init);
				kmerge(a, init,init+factor, k);
				//System.out.println("intin "+init);
				q.add(a[init]);
				//a[init].index=init;
			}
 			init=p+((factor-1)*factor);
 			kmerge(a, init,a.length-1, k);
			System.out.println("intin "+init);
			q.add(a[init]);
			
 			/*
			PriorityQueue<T> q=new PriorityQueue<>();
			HashMap<T, Integer> hmap=new HashMap<>();
			int z=(r-p)/k;
			int init=0,index;
			System.out.println("Z is "+z);//dividing sub problems using for loop
			for(index=0;index<z-1;index++)
			{
				init=p+(index*z);
				kmerge(a,B, init,init+z, k);
				System.out.println("intin "+init);
				q.add(a[init]);
				hmap.put(a[init],init);
			}
			init=index*z;
			kmerge(a,B, init, a.length, k);
			q.add(a[init]);
			hmap.put(a[init],init);
			//Integer[] B=new Integer[a.length];
			int ind=p;
			//if(r-p<=a.length-1)
			{
				while(!q.isEmpty()){
					T item=q.remove();
					//System.out.println(item);
					B[p]= item;
					int index1=hmap.remove(item); //removed number position in the array
					index1=index1+1;
					if(index1%z!=0)
					{
					//	System.out.println("r is "+r +" p "+p);
						q.add(a[index1]);
						hmap.put(a[index1],index1);
					}
					ind++;
				}	
			}*/
 			while(!q.isEmpty())
 			{
 				//System.out.println(q.remove().element+" q is");
 				inputg<T> x=q.remove();
 				//a[p]=x;
 				b[p]=(Comparable)x.element;
 				int ind=x.index+1;
 				if((ind%(k))!=0)
 				{
 					q.add(a[ind]);
 					/*ind++;
 					*/
 				}
 				//
 				System.out.println(b[p]);
 				p++;
 			}
 			
		}
		//adding the offset elements	
		
	}
	/*public void mergingPhase()
	{
		
	}*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] a={5,4,1,6,8,56,2,7,78,9,11,14,16};
		inputg<Integer>[] k=new inputg[a.length];
		for(int x=0;x<a.length;x++)
		{
			k[x]=new inputg(a[x], x);
		}
		Integer[] b=new Integer[a.length];
		kMergeSort(k,3);
		
		for(int i=0;i<a.length;i++)
		{
			//System.out.println(k[i].element+" sorted ");
		}
	}
}