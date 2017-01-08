/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/** 
 *  Ver 1.0:  Priority Sort: 2016/09/08.
 */

public class PrioritySort {

    private static <T extends Comparable<? super T>> void priorityqSort(T[] A) 
    {
        PriorityQueue<T> pq1 = new PriorityQueue<T>();
        //adding an element into priority queue
        for (T x : A) {
            pq1.add(x);
	}
        //output an element from priority queue
        while (!pq1.isEmpty()) {
            Integer i = (Integer) pq1.poll();
           // System.out.println(i);
        }
        System.out.println("pq1: " + pq1);
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
        priorityqSort(a);
        timer.end();
        System.out.println(timer);
    }
    
}

