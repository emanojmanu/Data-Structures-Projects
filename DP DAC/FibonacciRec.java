/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectOpt3;

import static java.lang.Float.sum;
import static java.lang.Integer.sum;
import java.math.BigInteger;
import java.util.Scanner;


public class FibonacciRec {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        //int num = Integer.parseInt(args[0]);
        System.out.println("Fibonacci number: ");
        //for (int i = 1; i <= num; i++)
        System.out.println(fibonacci(num));
    }

    private static BigInteger fibonacci(int num) {
        BigInteger sum;
        if (num == 0)
            return BigInteger.valueOf(0);
        else if (num == 1)
            return BigInteger.valueOf(1);
        else 
            //return BigInteger.valueOf(fibonacci(num - 1)).add(BigInteger.valueOf(fibonacci(num - 2)));
             sum = fibonacci(num-1);
             return sum.add(fibonacci(num-2));
            //return fibonacci(num -1) + fibonacci(num-2);
        
        
    }
    
}
