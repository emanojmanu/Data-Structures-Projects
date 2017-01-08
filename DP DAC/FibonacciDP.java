/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectOpt3;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author srabo
 */
public class FibonacciDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        System.out.println("Fibonacci number: ");
        fibonacciDP(num);  
    }

    private static void fibonacciDP(int num) {
        BigInteger f[] = new BigInteger[num+1];
        f[0] =BigInteger.ZERO;
        f[1] =BigInteger.ONE;
        
        for (int i=2 ; i<= num; i++){
            f[i] = f[i-1].add(f[i-2]);  
            //System.out.println(f[i]);
        }
        System.out.println(f[num]);
        //return f;
    }
    
}
