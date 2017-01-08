import java.util.Random;
/** 
 *  Ver 1.0:  Merge Sort: 2016/09/25.
 */

public class InsertionSort<T> {
	
	 public static<T extends Comparable<? super T>> void InsertionSort(T[] A,int p, int q) {
		 
		 int i,j;
		 for(i=p;i<q;i++)
		 {
			j=i;
			while(j>0 && (A[j-1].compareTo(A[j]) > 0) )
			{
				T temp;
				temp=A[j];
				A[j]=A[j-1];
				A[j-1]=temp;
				j--;
			}			
		 }
		 
	 }
	   public static void main(String[] args) 
	    {
	    	//timer class object creation to begin the timer
	        Timer timer = new Timer();
	        int size=(int) Math.pow(2, 17);
	        Integer[] a = new Integer[size];
	        for(int x = 0; x < size; x++){
	            a[x] = x;
	        }
	        Shuffle s=new Shuffle();
	        s.shuffle(a);
	        timer.start();
	        InsertionSort(a,0,a.length-1);
	        timer.end();
	        System.out.println(timer);
	        
	    }

}
