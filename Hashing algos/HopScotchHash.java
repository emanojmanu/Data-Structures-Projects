/** @author rbk
 *  Skeleton of code for hash table using open addressing (aka closed hashing)
 */
//mxe150630
//dxv151430
//sxv154030
//axj155830
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class HopScotchHash<T> {
    static int initialSize = 16;
    class HashEntry<E> {
	E element;
	boolean occupied;
	HashEntry(E x) {
	    element = x;
	    occupied = true;
	}
    }
    HashEntry<T>[] table;
    int size, maxSize;
    int maxProbeSequence;
    int hop;
    Set<T> overflow;
    HopScotchHash() {
	table = new HashEntry[initialSize];
	size = 0;  maxSize = initialSize;
	overflow = new HashSet<>();
	maxProbeSequence=0;
	hop=4;
    }
    HopScotchHash(int n) {
	table = new HashEntry[n];
	size = 0;  maxSize = n;
	overflow = new HashSet<>();
    }

    
    int find(T x) {
	// find x in hash table and return index of the element if found,
	// otherwise return the index where it stopped
    	int s0=hashCode(x)%maxSize;
    	int i=s0; 	
    	while(i<=s0+hop)
    	{ 		
    		if(table[i]!=null&&table[i].element.equals(x))
    		{
    			return i;
    		}
    		i++;
    	}
    	
    return i-1;
    }

    public boolean add(T x) {
	int s0=hashCode(x)%maxSize;
	int i=s0;
	ArrayList<T> helpArra=new ArrayList<>();
	while(i<maxSize)
	{
		if(table[i]!=null&&table[i].element!=null&&table[i].element.equals(x))
		{
			return false;
		}
		else if(table[i]==null||table[i].element==null)//when an element is deleted table[i].element alone becomesnull|| not entire table[i]
		{
			if(i<s0+hop)
			{
			table[i]=new HashEntry<T>(x);
			}
			break;
		}
		if(table[i]!=null&&table[i].element!=null)
		{
			
			T y=table[i].element;
					
			if(hashCode(y)%maxSize!=s0) //the exact items which cannot be dis positioned
			{
			//int diff=((hashCode(x)%maxSize));
			helpArra.add(y);
			}
		}		
		i++;
	}
	
	if(i<maxSize)//if found an element before the max size or a vacancy is found
	{
		int inde=helpArra.size();
	   while(inde>0)
	   {
		   T dummy=helpArra.remove(inde-1);
		   if(dummy!=null&&(i-(hashCode(dummy)%maxSize)<hop))
		   {
			   table[i]=new HashEntry<T>(dummy);
			   i=(hashCode(dummy)%maxSize);
			   inde=helpArra.size();//to continue the loop once a dummy is ready to relocate 
			   ///choose another dummy to relocate to prev dummy position
		   }
		   if(i-s0<hop)//once desired vacancy is found
		   {
			   table[i]=new HashEntry<T>(x);
			   return true;//added element
		   }
		   inde--;
	   }
	}
	return false;//either no vacancy is found before the max size or the elements or not willing to relocate
	
	
	/*
	while(i<s0+hop)
	{
		if(table[i]!=null&&table[i].element.equals(x))
		{
			return false;
		}
		else if(table[i]==null)
		{
			table[i]=new HashEntry<T>(x);
			break;
		}
		if(table[i]!=null)
		{
			if(hashCode(x)%maxSize!=s0)
			{
			int diff=(i-(hashCode(x)%maxSize));
		//	helpArra[i-s0]=diff;
			}
		}
	i++;
	}*/
		//return false;
    }
    private int hashCode(T x) {
		// TODO Auto-generated method stub
    	int h=x.hashCode()%maxSize;
		return h;
	}

    private int hashcodeSecondary(T x,int i)
    {
    	int s0=hashCode(x);
    	int result=(s0+i*(hashcode2(x)))%maxSize;
    	return result;
    }
    private int hashcode2(T x)
    {
    	//H2(K) = 1 + ( (K/M) mod (M-1) )
    	int m=13;//randomhsahfunction
    	int i=1 + ( ((Integer)x/m) % (m-1) );
    	return i;
    }
    
	public T get(T x) {
		int s0=hashCode(x)%maxSize;
    	int s[]=new int[maxSize];
    	s[0]=s0;
    	int i=s0;
    	while(i<s0+hop)
    	{
    	if(table[i]==null)
    	{
    		return null;
    	}
    	else if(table[i].element!=null&&table[i].element.equals(x)){
    		//table[s[i]].element=null;
    		//System.out.println("inside remove");
    		return x;
    	}
    	i++;
    	//s[i]=hashcodeSecondary(x,i);
    	}//if element is not found within hop range
	return null;
    }

    public T remove(T x) {
	// If x exists, remove it from the hash table and return it.
	// Otherwise return null;
    	int s0=hashCode(x)%maxSize;
    	int s[]=new int[maxSize];
    	s[0]=s0;
    	int i=s0;
    	while(i<s0+hop)
    	{
    	if(table[i]==null)
    	{
    		return null;
    	}
    	else if(table[i].element!=null&&table[i].element.equals(x)){
    		table[i].element=null;
    		//System.out.println("inside remove");
    		return x;
    	}
    	i++;
    	//s[i]=hashcodeSecondary(x,i);
    	}
    	
	return null;
    }

    public boolean contains(T x) {
    	int s0=hashCode(x)%maxSize;
    	int i=s0;
    	while(i<s0+hop)
    	{
    	if(table[i]==null)
    	{
    	//	System.out.println("inside contains");
    		return false;
    	}
    	else if(table[i].element!=null&&table[i].element.equals(x)){
    		return true;
    	}
    	i++;
    	//s[i]=hashcodeSecondary(x,i);
    	}
    	return false;
    }

    void resize() {
  }
    public static void main(String args[]) throws FileNotFoundException
    {
    	HopScotchHash<Integer> dh=new HopScotchHash<>();
    	Scanner in;//enter the size follwed by the elements in the array
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
      int size=in.nextInt();
      Integer[] ii=new Integer[size];
      /*for(int i=0;i<size;i++)
      {
    	  ii[i]=in.nextInt();
          dh.add(ii[i]);
      }
      */
      /*for(int i=0;i<ii.length;i++)
      {
    	  System.out.println(dh.contains(ii[i]));
      }
     //  System.out.println("ading done");
      for(int i=0;i<size;i++)
      {
          System.out.println(dh.get(ii[i]));
      }*/
     // System.out.println("mn");
    }
}