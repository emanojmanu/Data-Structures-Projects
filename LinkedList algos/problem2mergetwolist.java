package optionalproj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class problem2mergetwolist {
//code to print a list
	 public static <T extends Comparable<? super T>>
     void printl(List<T> out) {
	if(out.size()==0)
	{
		System.out.println("List to be printed is empty");
	}
	else
	{
	 for(T item:out)
		{
			System.out.println(item);
		}
	}
	 }
	//returns a list without duplicates
	 public static <T extends Comparable<? super T>>
     List<T> removeDuplicates(List<T> outList) { 
		 int total=outList.size();
		for(int i=0;i<total-1;i++)
		{
			
			if(((outList.get(i)).compareTo(outList.get(i+1)))==0)
			{
				outList.remove(i+1);i--;
				total--;
			}
		}
		return outList;
	 }
	 //removes duplicates and returns a sored list
	 public static<T extends Comparable<? super T>> List<T> sorting(List<T> l)
	 {
	 	int siz=l.size();
	 	List<T> sorted=l;
	 			 T min;
	 			 //bubble sort
	 	for(int i=0;i<siz-1;i++)
	 	{
	 		for(int j=1;j<siz-i;j++)
	 		{
	 			if(sorted.get(j-1).compareTo(sorted.get(j))>0)
	 			{
	 				//swapping 
	 			   min=sorted.get(j);
	 			   sorted.remove(j);
	 			   sorted.add(j, sorted.get(j-1));
	 			   sorted.remove(j-1);
	 			   sorted.add(j-1, min);
	 			}
	 			else if(sorted.get(j-1).compareTo(sorted.get(j))==0)
	 			{
	 				sorted.remove(j);
	 				siz--;
	 			}
	 		}
	 	}
	 	return sorted;
	 }
	 public static<T extends Comparable<? super T>>
     void intersect(List<T> l1, List<T> l2, List<T> outList) {
	   // Return elements common to l1 and l2, in sorted order.
	   // outList is an empty list created by the calling
        // program and passed as a parameter.
	   // Function should be efficient whether the List is
	   // implemented using ArrayList or LinkedList.
	   // Do not use HashSet/Map or TreeSet/Map or other complex
        // data structures.
		List<T> input1=l1;
		List<T> input2=l2;
		
		int x=0;
		while(x<input1.size())
		{
			int y=0,dup=0;
			while(y<input2.size())
			{
			if(input1.get(x).compareTo(input2.get(y))==0&&dup==0)
			{
				dup++;
				outList.add(input1.get(x));
				
			}
			y++;
			}
			x++;
		}
		outList=sorting(outList); // to removeduplicate  from the intersected list
	
	}
	
	 //implemented kind of merge sort thing sorting two lists and merging them
 public static<T extends Comparable<? super T>>
     void union(List<T> l1, List<T> l2, List<T> outList) {
	   // Return the union of l1 and l2, in sorted order.
	   // Output is a set, so it should have no duplicates.
		List<T> input1=(l1);
		List<T> input2=(l2);
		sorting(input1);
		sorting(input2);
		int total=input1.size()+input2.size();
		int input1flag=0;
		int input2flag=0;
		
		for(int i=0;i<total;i++)
		{
			
		if(input2flag+input1.size()>=total||
				((input1flag<input1.size())&&input1.get(input1flag).compareTo(input2.get(input2flag))<=0))
		{
			outList.add(input1.get(input1flag));
			input1flag++;		
		}
		else{
			outList.add(input2.get(input2flag));
			input2flag++;
		}
		} 
	 
	outList=removeDuplicates(outList);              
	}

 public static<T extends Comparable<? super T>>
     void difference(List<T> l1, List<T> l2, List<T> outList) {
	   // Return l1 - l2 (i.e, items in l1 that are not in l2), in sorted order.
	   // Output is a set, so it should have no duplicates.
	 List<T> sortedl1=sorting(l1);
	 List<T> intersectl1l2=new ArrayList<>();
			 intersect(l1, l2, intersectl1l2);
			 int intsize=intersectl1l2.size();
			 int j=0;
			for(int i=0;i<sortedl1.size();i++)
			{
				if(j<intsize&&sortedl1.get(i).compareTo(intersectl1l2.get(j))==0)
				{
					
					j++;
				}
				else{
					outList.add(sortedl1.get(i));
				}
			}
	 
	}
	
    public static void main(String[] args) {
       List<String> l1=new LinkedList<String>();
        for(int i=0;i<10;i++)
        {
        l1.add("b"+i);
        
        }l1.add("b");l1.add("b"+1);
        
        l1.add("mano");

        List<String> l2=new ArrayList<String>();
        for(int i=9;i<15;i++)
        {
        	l2.add("a"+i);
        }
        l2.add("a"+2);
        l2.add("a"+5);
        l2.add("b"+1);
        l2.add("b"+1);
        l2.add("b"+9);
        
        List<String> unionString=new LinkedList<>();
        List<String> intersectString=new LinkedList<>();
        List<String> differenceString=new LinkedList<>();
        System.out.println("implementing String types");

        System.out.println("the result of the intersect");
        intersect(l1,l2,intersectString);
        printl(intersectString);
        
   System.out.println("the result of the union ");
        union(l1,l2,unionString);
        printl(unionString);
   
        System.out.println("the result of difference");
        difference(l1,l2,differenceString);
        printl(differenceString);
        
      
       System.out.println("implementing Double types");
       List<Double> o = new LinkedList<>();
       List<Double> p = new LinkedList<>();
        List<Double> unionDouble=new LinkedList<>();
        List<Double> intersectDouble=new LinkedList<>();
        List<Double> differenceDouble=new LinkedList<>();
       for(int i=0;i<10;i++)
       {
    	   o.add(i+0.1);
       }
       o.add(1.1);
       o.add(1.1);
       o.add(1.1);
       
       o.add(1.1);
       
      for(int i=5;i<15;i++)
       {
    	   p.add(i+0.5);
    	   p.add(i+0.1);
    	   
       }
       p.add(1.1);
       p.add(1.1);
         System.out.println("the result of the intersect");
       intersect(o,p,intersectDouble);
       printl(intersectDouble);

       System.out.println("the result of the union ");
       union(o,p,unionDouble);
       printl(unionDouble);
     
       System.out.println("the result of difference");
       difference(o,p, differenceDouble);
       printl(differenceDouble);
 }
}
