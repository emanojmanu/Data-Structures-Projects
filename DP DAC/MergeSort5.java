/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//from 2^24

import java.lang.*;
import java.util.Random;

/** 
 *  Ver 1.0:  Merge Sort with insertion sort, using an array B and avoiding unnecessary copying: 2016/09/25.
 */
public class MergeSort5<T>{
    
    static int h1,h2;
    public static<T extends Comparable<? super T>> int MergeSort(T[] A,T[] B, int p, int r) {
        int q =0;
        // Invariant: p and r are used to find the mid element.
      	// p is the left most and element and r is the right most element
         //this is done in loop until the number of elements reached a threshold 20
         //If number of elements is greater than 20 then Insertion sort is used
         //h is used to increment for each pass
       if ((r-p+1) >12)
        {
        q = (p+r)/2;
        h1=MergeSort(A,B,p,q);
        h2=MergeSort(A,B,q+1, r);
        if(h1%2==0)
        merge(A, B, p, q, r);
        else merge(B, A, p, q, r);
        return h1++;
        }
         else
        {
        	InsertionSort a =new InsertionSort();
        	a.InsertionSort(A,p,r+1);
        	return 0;
        }
        
    }
    
    private static <T extends Comparable<? super T>> void merge(T[] A,T[] b, int p, int q, int r) 
    {
        int i=p;
        int j=q+1;
        int k;
        // Invariant: k is used to increment the counter for each element in array
     	// If loop in it determines which element is greater
        //this is done in loop until the last individual set of elements are reached
        for(k=p;k<=r;k++)
        {
        	if(j>r || ((i<=q)&&b[i].compareTo(b[j])<=0))
        	{
        		b[k]=A[i++];
        	}
        	else
        	{
        		b[k]=A[j++];
        	}
        }               
    }
    
    public static void main(String[] args) 
    {        
        Timer timer = new Timer();
        int size=(int) Math.pow(2, 4);
        Integer[] a = new Integer[size];
        for(int x = 0; x < size; x++){
            a[x] = x;
        }
        Shuffle s=new Shuffle();
        s.shuffle(a);
        Integer[]  b=a.clone();
        timer.start();
         int h= MergeSort(a, b,0, a.length-1); 
      /*  if(h%2!=0)
        for(int i=0;i<a.length;i++){System.out.print(" "+b[i]);}
        else
        for(int i=0;i<a.length;i++){System.out.print(" "+a[i]);}*/
        timer.end();
        System.out.println(timer);     
    }    
}
