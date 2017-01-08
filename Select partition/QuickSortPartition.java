package optionalproject4;

public class QuickSortPartition<T extends Comparable<? super T>> {

	public <T extends Comparable<? super T>> int partition(T[] x,int p,int r)
	{
		int q=0;
		
			q=(int)((Math.random()*(r-p)+1)+p);
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
	
	public <T extends Comparable<? super T>> int[]  partitionDual(T[] x, int p,int r)
	{
		int q=0,q1=0;
		int pivotArr[]=new int[2];
		
		if(r>p&&r-p==1)
		{
			if(x[p].compareTo(x[r])>0)
			{
				swap(x,p,r);
			}
			pivotArr[0]=p;
			pivotArr[1]=r;		
		}

		else{
		while(q==q1)
		{
		q=(int)((Math.random()*(r-p)+1)+p);
		q1=(int)((Math.random()*(r-p)+1)+p);
		}
		swap(x,q,p);
		swap(x,q1,r);
		T pivot1 =x[p];
		T pivot2 =x[r];
		
		if(pivot1.compareTo(pivot2)>0)
		{
			swap(x,p,r);
			T temp=pivot1;
			pivot1=pivot2;
			pivot2=temp;
		}
		int j=p,t=p,f=p;
		for(int k=p+1;k<r;k++)
		{
			if(pivot1.compareTo(x[k])>0)
			{
				j++;t++;f++;
				swap(x,t,k);
			    swap(x,j,t);
			}
			else if(pivot2.compareTo(x[k])>0)
			{
			t++;
			f++;
			swap(x,t,k);
			}
		}
		/*j++;
		t++;*/
		swap(x,j,p);
		t++;
		swap(x,t,r);
		pivotArr[0]=j;
		pivotArr[1]=t;	
		}
	return pivotArr;
	}
	
	public void quickSortOrdinary(T[] x, int p, int r) {
		if (r > p) {
			int m = partition(x, p, r);
			quickSortOrdinary(x, p, m - 1);
			quickSortOrdinary(x, m + 1, r);
		}

	}

	public void quickSortDual(T[] x, int p, int r) {
		if(r>p)
		{
			int[] arr=partitionDual(x, p, r);
			int q1=arr[0];
			int q2=arr[1];
			quickSortDual(x, p, q1-1);
			quickSortDual(x, q2+1, r);
			if(x[q1].compareTo(x[q2])!=0)
			{
				quickSortDual(x, q1+1, q2-1);
			}
			
		}

	}
	
	private <T extends Comparable<? super T>>void swap(T[] x, int q, int r) {
		// TODO Auto-generated method stub
		T temp=x[q];
		x[q]=x[r];
		x[r]=temp;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] x2=new Integer[1048576];
		Integer[] x=new Integer[1048576];
		
		for(int i=0;i<1048576;i++)
		{
			
			int dummy=0;
			dummy=(int)((Math.random()+1)*1048576);
			x[i]=dummy;
			x2[i]=dummy;
		}
		QuickSortPartition q=new QuickSortPartition();
		Timer t1=new Timer();
		Timer t2=new Timer();
	    t1.start();
		
		q.quickSortOrdinary(x, 0, x.length-1);
		t1.end();
		System.out.println("normal partition "+t1);
		//	q.partition(x,4,x.length);
		t2.start();
		q.quickSortDual(x2, 0, x2.length-1);
		t2.end();
		System.out.println("dual partition "+t2);
/*		normal partition Time: 1791 msec.
		Memory: 40 MB / 123 MB.
		dual partition Time: 1470 msec.
		Memory: 45 MB / 123 MB.
normal partition Time: 1715 msec.
Memory: 40 MB / 123 MB.
dual partition Time: 1807 msec.
Memory: 46 MB / 123 MB.

*/
		}
}
