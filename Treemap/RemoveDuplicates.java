/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsoptionls;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Srabonti Chakraborty, Divya Vudem, Manoj Kumar, Alwin Johns
 */
public class RemoveDuplicates {

    /**
     * @param args the command line arguments
     */
        static<T extends Comparable<? super T>> T[] exactlyOnce(T[] A) { // RT = O(nlogn).
     // Ex: A = {6,3,4,5,3,5}.  exactlyOnce(A) returns {6,4}
    
        TreeMap<T, Integer> tmap = new TreeMap<T, Integer>();
        for (int i=0; i< A.length; i++){
            if (tmap.containsKey(A[i])){
                int element = tmap.get(A[i]);
                tmap.put(A[i], element+1);
            }
            else{
                tmap.put(A[i],1);
            }
        }
        List<T> a = new ArrayList<T>();
        for (int i=0; i< A.length; i++){
            if (tmap.get(A[i]).compareTo(1) == 0){
                a.add(A[i]);
            }
        }
     
        T[] B = (T[]) Array.newInstance(a.get(0).getClass(), a.size());
        for (int i = 0; i < a.size(); i++) {
            B[i] = a.get(i);
        }                                
        return B;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Enter the number of elements: ");
        Scanner in;
        in = new Scanner(System.in);
        int numEle = in.nextInt();
        Integer[] A = new Integer[numEle];
        
        for (int c=0; c<numEle;c++){
           A[c] = in.nextInt(); 
        }
        
        //Integer[] A = {6,3,4,5,3,5};
        Integer[] B = exactlyOnce(A);
        for (int i=0; i< B.length; i++){
                System.out.print(B[i]+ "  ");
        }
        
    }
    
}
