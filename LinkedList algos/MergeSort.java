/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.*;
import java.util.Random;

/** 
 *  Ver 1.0:  Merge Sort: 2016/09/08.
 */
public class MergeSort<T>{
    
    
    public static<T extends Comparable<? super T>> void MergeSort(T[] A, int p, int r) {
        int q =0;
        // Invariant: p and r are used to find the mid element.
     	// p is the left most and element and r is the right most element
        //this is done in loop until the last individual set of elements are reached
        if (p< r)
        {
            q = (p+r)/2;
            MergeSort(A, p, q);
            MergeSort(A, q+1, r);
            merge(A, p, q, r);
        }         
    }
    
    private static <T extends Comparable<? super T>> void merge(T[] A, int p, int q, int r) {
        int n1 = q - p +1;
        int n2 = r - q;
        T[] L = (T[])new Comparable[n1];
        T[] R = (T[])new Comparable[n2];
    
        System.arraycopy(A, p, L, 0, n1);
        System.arraycopy(A, q+1, R, 0, n2);
        int i=0;
        int j=0;
        int k = p;
        // Invariant: i and j point to left array and right array 
     	// compares the left array element with right array element and inserts accordingly 
        // into array A in sorted order
        while(i< n1 && j< n2){
            if (L[i].compareTo(R[j]) <= 0){
                A[k] = L[i++];
            }else{
                A[k] = R[j++];
            }
            k++;
        }
        //copy the remaining elements of left array into array A
        while(i<n1){
                A[k] = L[i];
                i++;
                k++;
        }
        //copy the remaining elements of right array into array A
        while(j<n2){
            A[k] = R[j++];
            k++;
        }          
    }
    
    public static void main(String[] args) 
    {
    	//timer class object creation to begin the timer
        Timer timer = new Timer();
        timer.start();
        Integer[] a = new Integer[1000000];
        Random rand = new Random();
        double d;
        int in;
     // Invariant: insert random 1000000 elements into array
        for(int x = 0; x < 1000000; x++){
            d =   Math.random()*50;
            in = (int) d;
            a[x] = in;
        }
        MergeSort(a, 0, a.length-1);        
        timer.end();
        System.out.println(timer);
        
    }    
}
