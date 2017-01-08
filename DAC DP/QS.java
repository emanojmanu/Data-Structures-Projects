
public class QS {
	public  <T extends Comparable<? super T>> int partition(T[] x, int start,int end)
	{
		int k=0,p=0;	
			k=(int)((Math.random()*(end-start)+1)+start);
			T temp=x[k];
			x[k]=x[end];
 			x[end]=temp;
			p=start-1;
			for(int i=start;i<end;i++)
			{
				if(x[i].compareTo(temp)<=0)
				{
					p++;
					T tem=x[p];
					x[p]=x[i];
					x[i]=tem;
				}
			}
			p++;
			T tem=x[p];
			x[p]=x[end];
			x[end]=tem;	
		return p;
	}
	public <T extends Comparable<? super T>>void swap(T[] x,int pos1,int pos2)
	{
		T temp=x[pos1];
		x[pos1]=x[pos2];
		x[pos2]=temp;
	}
	public <T extends Comparable<? super T>> int[] dualPartition(T[] x,int start,int end)
	{
		int[] ret=new int[2];
		int q=0,t=0,k2=0,k1=0,p=0;
		if(end-start>=2)
		{
		k1=(int)((Math.random()*(end-start)+1)+start);
		k2=(int)((Math.random()*(end-start)+1)+start);
		if(k1==k2)
		{
			if(k1==end)              //if only k1==k2 elements to prevent k2=end+||start-1 
			{
				k2=end;
				k1=end-1;
			}
			else if(k1==start){
				if(k1!=end)
				{
				k2=start+1;
				}
			}
		}
		else if(k1>k2)
		{
			int temp=k1;  //k1<k2
			k1=k2;
			k2=temp;
		}
		//to makex[k1]<x[k2]
		if(x[k1].compareTo(x[k2])>0)
		{
		swap(x,k1,k2);
		}
	//swaping k1 with index0 and k2 with last index
		swap(x,k1,start);
		swap(x,k2,end);
		p=start;t=start;
		q=start;
		T pivot1=x[start];
		T pivot2=x[end];
		for(int i=start+1;i<end;i++)
		{
			if(x[i].compareTo(pivot1)<0)
			{
			p++;t++;q++;
			
			T temp1=x[i];
			//swap(x,i,q);
			x[i]=x[t]; ////t++>pivot2
			x[t]=x[p];//>p1&&<p2//p++
			x[p]=temp1;//<p1
			}
			else if(x[i].compareTo(pivot1)>=0&&x[i].compareTo(pivot2)<=0)
			{
			t++;q++;
			T temp2=x[i];
			x[i]=x[t];
			x[t]=temp2;
			}
		}
    	swap(x,start,p);
		q++;
		if(q>=p)
		{
		swap(x,end,q);
		}
		ret[0]=p;         //ret contains p,q
		ret[1]=q;
		}
		else{             //if only two elements or 1 elements
			if(x[start].compareTo(x[end])>0)
			{
				swap(x, start, end);
			}
			ret[0]=start;
			ret[1]=end;
		}
		return ret;
	}
	public <T extends Comparable<? super T>> void quicksor(T[] x,int start,int end)
	{
		if(end>start)
		{
			int pivot=partition(x, start, end);
			quicksor(x, start, pivot-1);
			quicksor(x, pivot+1, end);
		}
	}
	public <T extends Comparable<? super T>> void DualPartitionQuickSort(T[] x,int start,int end)
	{
		if(end>start)
		{
			int a[]=new int[2];
			a=dualPartition(x, start, end);
			DualPartitionQuickSort(x, start, a[0]-1);
			DualPartitionQuickSort(x, a[1]+1,end);
			if(x[a[0]]!=x[a[1]])
			{
				DualPartitionQuickSort(x, a[0]+1, a[1]-1);
			}	
		}
	}
	public static void main(String[] args) {

		Timer t=new Timer();
		Integer[] x=new Integer[9999];
		for(int i=0;i<9999;i++)
		{
			
			x[i]=(int)(Math.random()*9999);
		}
		Integer[] y=new Integer[9999];
		for(int i=0;i<9999;i++)
		{
			
			y[i]=(int)(Math.random()*9999);
		}	
		QS q=new QS();
		System.out.println("normal sorting");
		t.start();
		q.quicksor(y, 0, y.length-1);
		t.end();
		
		for(int h=0;h<y.length;h++)
		{
			System.out.println(y[h]);
		}
		System.out.println("normal sorting time" +t);
		
		System.out.println("dual pivot sort");
		Timer t2=new Timer();
		t2.start();
		q.DualPartitionQuickSort(x, 0, x.length-1);
		t2.end();
		
		for(int k=0;k<x.length;k++)
		{
			System.out.println(x[k]);
		}
		System.out.println("Dual pivot sorting time" +t2);
	}
}
