package optionalproj;

import java.util.ArrayList;
import java.util.List;

public class probliem2AddSub {
	public static void add(List<Integer> x, List<Integer>  y,List<Integer> z, int b) {
	   	  // Return z = x + y.  Numbers are stored using base b.
		  // The "digits" are stored in the list with the least
	   	  // significant digit first.  For example, if b = 10, then
		  // the number 709 will be stored as 9 -> 0 -> 7.
		  // Assume that b is small enough that you will not get any
		  // overflow of numbers during the operation.
		List<Integer> list1=x;
		List<Integer> list2=y;
		int size1=list1.size();
		int size2=list2.size();
		int offset=0,carry=0;   //carry will be 1 or 0 depending upon ?total>base,offset=total%base
		int total=0,i=0,temp=0;   //temp=total/base
		int list1var,list2var;   //list1var , list2var are corresponing vars of list1 and list2
		if(size1>=size2)
		{
			for(i=0;i<size2;i++)
			{
				list1var=list1.get(i);
				list2var=list2.get(i);
				total=list1var+list2var+carry;
				 temp=total/b;
				 offset=total%b;		
				if(temp>0)                       //to carry to next step
				{
					carry=1;
				}
				else{
					carry=0;
             		}
			z.add(offset);                    //adding element in the new list
			}
			if(temp>0&&size1==size2)         //if sizes are same and u are left with a carry to add
			{
				z.add(carry);
			}                                 // if sizes are size1>size2
			                                     //u need to add the leftover carry to rest of large list
			else if(size1>size2)
			{
				
			while(i<size1)
			{
    			 list1var=list1.get(i);
				total=list1var+carry;
				temp=total/b;
				 offset=total%b;	
				if(temp>0)
				{
					carry=1;
				}
				else{
					carry=0;
            		}
				z.add(offset);
				i++;
			}
			if(carry>0)
			{
				z.add(carry);
			}	
			}	
		}
		else{                               //if size1<size2  we will swap arguments in the 
			                                     //add(y,x,z,b)
			add(y, x,z,b);                   
		}
	   }
	   public static void subtract(List<Integer> x, List<Integer>  y,List<Integer> z, int b) {
	   	  // Return z = x - y.  Numbers are stored using base b.
		  // Assume that x >= y.
		   List<Integer> list1=x;
			List<Integer> list2=y;
			int size1=list1.size();
			int list2var=0,list1var=0;
			int i=0;
			int size2=list2.size();             //Assuming list1size>=list2 size since x>=y
			for(i=0;i<size2;i++)
			{
				{
				list1var=list1.get(i);
				list2var=list2.get(i);
				int diff=list1var-list2var;				
				if(diff>=0)                  //diffe of numbers at ith poistion>0 then appended to Z list
				{
					appending(z,diff);
				}
				else{
					int j=i+1;
					while(list1.get(j)==0)        //to find the non zero number oposition in the list1
					{
						j++;
					}
				
					int firstflag=0;			//flag to identify and on operate the non zero number correctly
						while(j>i)
						{
							if(firstflag==0)
							{
								int dummy=list1.get(j);  //List is modified to support proper subtraction
								list1.remove(j);
								list1.add(j,dummy-1);    //the identified nonzero number is decremented by 1
								firstflag++;
							}
							else{
							list1.remove(j);          //remaining numbers(i.e., 0's) till ith are added with b-1 by swapping
							list1.add(j, b-1);
							}
							j--;
						}	
						int vartobesub=list1.get(i)+b*1;  //and the number in ith is added with by swapping 
						list1.remove(i);
						list1.add(i,vartobesub);
						list1var=list1.get(i);
						list2var=list2.get(i);
						diff=list1var-list2var;
					appending(z,diff);	
				}
			}

			}                               // if size1>size2 u will be left out 
			while(i<size1)                      //copying the i+1th elements from modified list1 step110 
			{                                      //to Z
				appending(z,list1.get(i));
				i++;
			}			
	   }
	private static void appending(List<Integer> z,int diff) {
		// TODO Auto-generated method stub
		z.add(diff);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> l1=new ArrayList<>();
		List<Integer> l2=new ArrayList<>();
		List<Integer> addL=new ArrayList<>();
		List<Integer> subL=new ArrayList<>();	
		l1.add(0);
		l1.add(0);
		l1.add(0);
		l1.add(3);	
		l2.add(5);
subtract(l1, l2, subL, 10);   //10 is the base, base can be changed 
System.out.println("Result after Subtraction");
for(Integer i:subL)
{
	System.out.println(i);
}
		
        int base=10;
        add(l1,l2,addL,base);
		System.out.println("result after addition");
        for(Integer i:addL)
		{
			System.out.println(i);
		}
	}

}
