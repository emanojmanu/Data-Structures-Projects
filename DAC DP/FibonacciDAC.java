/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectOpt3;

import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciDAC {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        System.out.println("Fibonacci number: "+ fibonacciDAC(num));
        //fibonacciDAC(num);
    }

    private static BigInteger fibonacciDAC(int num) {
        BigInteger f[][] = new BigInteger[][]{{BigInteger.valueOf(1),BigInteger.valueOf(1)},{BigInteger.valueOf(1),BigInteger.valueOf(0)}};
        BigInteger a[][] = new BigInteger[][]{{BigInteger.valueOf(1),BigInteger.valueOf(1)},{BigInteger.valueOf(1),BigInteger.valueOf(0)}};
        if (num ==0)
            return BigInteger.valueOf(0);
        else
            a=calcPower(f, num-1);
              
        return a[0][0];   
    }

    private static BigInteger[][] calcPower(BigInteger[][] f, int n) {
        //BigInteger prod[][] = new BigInteger[][]{{BigInteger.valueOf(1),BigInteger.valueOf(1)},{BigInteger.valueOf(1),BigInteger.valueOf(0)}};
        BigInteger prod[][] = new BigInteger[][]{{BigInteger.valueOf(1),BigInteger.valueOf(0)},{BigInteger.valueOf(0),BigInteger.valueOf(1)}};
        
        //BigInteger prod[][] = new BigInteger[][]{{BigInteger.valueOf(1),BigInteger.valueOf(1)},{BigInteger.valueOf(1),BigInteger.valueOf(0)}};
        while(n !=0){
            if (n%2 != 0)
                prod = matrxMulti(prod, f);
            n = n/2;
            f = matrxMulti(f, f);  
        }    
        return prod;
    }

    private static BigInteger[][] matrxMulti(BigInteger[][] prod, BigInteger[][] f) {
        BigInteger temp1, temp2, temp3, temp4;
        BigInteger temp[][] = new BigInteger[][]{{BigInteger.valueOf(1),BigInteger.valueOf(1)},{BigInteger.valueOf(1),BigInteger.valueOf(0)}};
        temp1 = f[0][0].multiply(prod[0][0]).add(f[0][0].multiply(prod[0][0]));
        temp2 = f[0][0].multiply(prod[0][1]).add(f[0][1].multiply(prod[1][1]));
        temp3 = f[1][0].multiply(prod[0][0]).add(f[1][1].multiply(prod[1][0]));
        temp4 = f[1][0].multiply(prod[0][1]).add(f[1][1].multiply(prod[1][1]));
        prod[0][0] = temp1;
        prod[0][1] = temp2;
        prod[1][0] = temp3;
        prod[1][1] = temp4;
        return prod;
    }    
}
