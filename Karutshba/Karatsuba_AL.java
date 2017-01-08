/** 
 *  Karatsuba Algorithm : Multiplication
 *  Ver 1.0: added multiplication: 2016/09/19.
 */

import java.util.*;

public class Karatsuba_AL  {
	
	
	public static LinkedList<Integer> karat(LinkedList<Integer> num1, LinkedList<Integer> num2) 
	{
		//If numbers of size less than 2 return product
		 if ((num1.size() < 2) || (num2.size() < 2))
		    return mul(num1,num2);
		
		LinkedList<Integer> low1,low2,high1,high2,z0,z1,z2;
		 int m=Math.max(num1.size(),num2.size());
		 m=(m/2);
		 
		 //splitting numbers num1 and num2
		 low1 = splitlow(num1,m);
		 low2 = splitlow(num2,m);
		 high1 = splithigh(num1,m);
		 high2 = splithigh(num2,m);
		 z0=karat(low1,low2);
		 z1=karat(add(low1,high1),add(low2,high2));
		 z2=karat(high1,high2);
		 int k= (int) Math.pow(10,(2*m));
		 int l= (int) Math.pow(10, m);
		 return add(add((mulp(z2,k)),mulp((subtract(z1,z2,z0)),l)),z0);
		  
	}
	
	public static LinkedList<Integer> splitlow(LinkedList<Integer> num, int m) 
	{
		LinkedList<Integer> newList = new LinkedList<>(num.subList(0,m));
		return newList; //return first m digits of array list
	}

	public static LinkedList<Integer> splithigh(LinkedList<Integer> num, int m) {
		LinkedList<Integer> newList = new LinkedList<>(num.subList(m,num.size()));
		return newList; //return last m digits of array list
	}
	
	public static LinkedList<Integer> mul(LinkedList<Integer> num1,LinkedList<Integer> num2)
	{
		LinkedList<Integer> num = new LinkedList<Integer>();
		LinkedList<Integer> tmp1 = new LinkedList<Integer>();
		LinkedList<Integer> tmp2= new LinkedList<Integer>();
		//tmp1 with size 1 and tmp2 with other
		if(num1.size()==1)  
		{ tmp1=num1;tmp2=num2; }
		else { tmp1=num2; tmp2=num1;}
		int k = tmp1.get(0);
		int carry=0;
		// Invariant: i point to array list with size greater than 1 
     	// multiplies each digit of tmp2 with tmp1 
		// adds result digits to array list num
		for(int i=0;i<Math.max(num1.size(),num2.size());i++)
		{
			int a = tmp2.get(i);
			int b = (k*a) + carry;
			carry=0;
			int blength = (int)(Math.log10(b)+1);
			if(blength>1){num.add(b%10);
			carry = Integer.parseInt(Integer.toString(b).substring(0, 1));
			}
			else {num.add(b);}
			
		}	
		if(carry>0) num.add(carry);
		return num;	
	}
	
	public static LinkedList<Integer> mulp(LinkedList<Integer> tmp2,int p)
	{
		LinkedList<Integer> num = new LinkedList<Integer>();
		LinkedList<Integer> tmp1= new LinkedList<Integer>();
		tmp1.add(p);
		int k = tmp1.get(0);
		int carry=0;
		// Invariant: i point to array list
     	// multiplies each digit of tmp2 with p 
		// adds result digits to array list num
		for(int i=0;i<tmp2.size();i++)
		{
			int a = tmp2.get(i);
			int b = (k*a) + carry;
			carry=0;
			int blength = (int)(Math.log10(b)+1);
			if(blength>1){num.add(b%10);
			carry = b/10;
			}
			else {num.add(b);}
			
		}	
		// Add remaining carry digits to array list num
		
		if(carry>0) {
			while(carry>0){
			num.add(carry%10);
			carry=carry/10;} }
		return num;	
	}
	public static LinkedList<Integer> subtract(LinkedList<Integer> tmp1,LinkedList<Integer> tmp2,LinkedList<Integer> tmp3)
	{
		LinkedList<Integer> num = new LinkedList<Integer>();
		int m,i=0,p=0,q=0,r=0;
		// get the number from array list tmp1 with base as 10
		while(i<tmp1.size()){
			m=tmp1.get(i);
			p=(int) (p+(Math.pow(10,i))*m);
			i++;
		}
		 m=0;i=0;
		// get the number from array list tmp2 with base as 10
		 while(i<tmp2.size()){
			m=tmp2.get(i);
			q=(int) (q+(Math.pow(10,i))*m);
			i++;
			}
		 m=0;i=0;
		// get the number from array list tmp3 with base as 10
		 while(i<tmp3.size()){
			m=tmp3.get(i);
			r=(int) (r+(Math.pow(10,i))*m);
			i++;
			}
		 //subtract 3 numbers
		 int j =p-q-r;
		 //add the digits of number j to array list num
		 while(j>0){
			 num.add(j%10);
			 j=j/10;
		 }
		return num;
	}

	public static LinkedList<Integer> add(LinkedList<Integer> num1,LinkedList<Integer> num2)
	{
		LinkedList<Integer> num = new LinkedList<Integer>();
		LinkedList<Integer> tmp1 = new LinkedList<Integer>();
		LinkedList<Integer> tmp2= new LinkedList<Integer>();
		//tmp1 with size minimum and tmp2 with other
		if(num1.size()<=num2.size())  
		{ tmp1=num1;tmp2=num2; }
		else { tmp1=num2; tmp2=num1;}
		int carry=0,n=0;
		// Invariant: i points to array list
     	// add each digit of tmp2 with p 
		// adds result digits to array list num
		for(int i=0;i<Math.min(num1.size(),num2.size());i++)
		{
			int a = tmp2.get(i);
			int k= tmp1.get(i);
			int b = (k+a) + carry;
			carry=0;
			int blength = (int)(Math.log10(b)+1);
			if(blength>1){num.add(b%10);
			carry = Integer.parseInt(Integer.toString(b).substring(0, 1));
			}
			else {num.add(b);}
			n++;
		}
		// Invariant: j points to remaining digits of array list of maximum size
     	// add each digit of array list with carry 
		// adds result digits to array list num
		for(int j=n;j < Math.max(num1.size(),num2.size());j++)
		{
			int s=tmp2.get(j);
			s=s+carry;
			carry=0;
			int blength = (int)(Math.log10(s)+1);
			if(blength>1){num.add(s%10);
			carry = Integer.parseInt(Integer.toString(s).substring(0, 1));
			}
			else {num.add(s);}
		}
		
		if(carry>0) num.add(carry);
		return num;	
	}


	public static void main(String args[])
	{
		 LinkedList<Integer> num1 = new LinkedList<Integer>();
		 LinkedList<Integer> num2 = new LinkedList<Integer>();
		 LinkedList<Integer> num = new LinkedList<Integer>();
		 num1.add(5);
		 num1.add(4);
		 num1.add(3);
		 num1.add(2);
		 num1.add(1);
		 num2.add(9);
		 num2.add(8);
		 num2.add(7);
		 num2.add(6); 
		 System.out.println("num1 : "+num1);
		 System.out.println("num2 : "+num2);
		 //multiply numbers num1 and num2
		 num=karat(num1,num2);
		 System.out.println("Product of 2 numbers : "+num);
	}




}
