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

public class DoubleHashing<T> {
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
    int permissibleLimit;
    Set<T> overflow;
    DoubleHashing() {
	table = new HashEntry[initialSize];
	size = 0;  maxSize = initialSize;
	overflow = new HashSet<>();
	maxProbeSequence=0;
	permissibleLimit=4;
    }

    DoubleHashing(int n) {
	table = new HashEntry[n];
	size = 0;  maxSize = n;
	overflow = new HashSet<>();
	maxProbeSequence=0;
	permissibleLimit=4;
    }
   int find(T x) {
	// find x in hash table and return index of the element if found,
	// otherwise return the index where it stopped
    	int s0=hashCode(x)%maxSize;
    	int s[]=new int[maxSize];
    	s[0]=s0;
    	int i=0;
    	while(i<maxSize)
    	{
    	if(table[s[i]]==null)
    	{
    		return i;
    	}
    	else if(table[s[i]].element.equals(x))
    	{
    		return i;
    	}
    	i++;
    	s[i]=hashcodeSecondary(x,i);
    	}
   
	return i;
    }

    public boolean add(T x) {
	int s0=hashCode(x)%maxSize;
	int s[]=new int[maxSize];
	s[0]=s0;
	int i=0;
	while(i<maxSize)
	{
	if(table[s[i]]==null)
	{
		table[s[i]]=new HashEntry<T>(x);
		if(i>maxProbeSequence)  //for resize operation max probe sequence length
		{
			maxProbeSequence=i;
		}
		this.size++;
		return true;
	}// bit confused whether to include duplicate elements inside the hashtable or not 
	//if not then the below else if has to be commented to verify for the existence of the duplicate elments
	
	else if(table[s[i]].element==null)//slot vacted due to some deletion
	{
		table[s[i]].element=x;
		if(i>maxProbeSequence)  //for resize operation max probe sequence length
		{
			maxProbeSequence=i;
		}
		this.size++;
		return true;
	}
	else if(table[s[i]].element!=null&&table[s[i]].element.equals(x)){
		if(i>maxProbeSequence)
		{
			maxProbeSequence=i;
		}
		return false;
	}
	i++;
	if(i>permissibleLimit)
	{
		overflow.add(x);
		return false;
	}
	s[i]=hashcodeSecondary(x,i);
	}	
	return false;
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
    	int m=13;
    	int i=1 + ( ((Integer)x/m) % (m-1) );
    	return i;
    }
    
	public T get(T x) {
		int s0=hashCode(x)%maxSize;
    	int s[]=new int[maxSize];
    	s[0]=s0;
    	int i=0;
    	while(i<maxSize)
    	{
    	if(table[s[i]]==null)
    	{
    		return null;
    	}
    	else if(table[s[i]].element!=null&&table[s[i]].element.equals(x)){
    		//table[s[i]].element=null;
    		//System.out.println("inside remove");
    		return x;
    	}
    	i++;
    	s[i]=hashcodeSecondary(x,i);
    	}
	return null;
    }

    public T remove(T x) {
	// If x exists, remove it from the hash table and return it.
	// Otherwise return null;
    	int s0=hashCode(x)%maxSize;
    	int s[]=new int[maxSize];
    	s[0]=s0;
    	int i=0;
    	while(i<maxSize)
    	{
    	if(table[s[i]]==null)
    	{
    		return null;
    	}
    	else if(table[s[i]].element!=null&&table[s[i]].element.equals(x)){
    		table[s[i]].element=null;
    		//System.out.println("inside remove");
    		this.size--;
    		return x;
    	}
    	i++;
    	s[i]=hashcodeSecondary(x,i);
    	}
    	
	return null;
    }

    public boolean contains(T x) {
    	int s0=hashCode(x)%maxSize;
    	int s[]=new int[maxSize];
    	s[0]=s0;
    	int i=0;
    	while(i<maxSize)
    	{
    	if(table[s[i]]==null)
    	{////when an element is deleted table[s[i]].element alone becomesnull|| not entire table[i]
    	//	System.out.println("inside contains");
    		return false;
    	}
    	else if(table[s[i]].element!=null&&table[s[i]].element.equals(x)){
    		return true;
    	}
    	i++;
    	if(i!=maxSize)
    	{
    	s[i]=hashcodeSecondary(x,i);
    	}
    	}
    	return false;
    }

    void resize() {
    	int i=this.size;
    	int overflowsize=this.overflow.size();
    	int newSize=(i+overflowsize)*4;
    	int prevmax=this.maxProbeSequence+3;//prev maximum misses  
    	DoubleHashing newDummy=new DoubleHashing(newSize);
    	newDummy.permissibleLimit=prevmax+3;    	
    	int count=0;
    	while(count<this.initialSize)
    	{
    		
    		if(this.table[count]!=null) //to see for an entry iniside the table
    		{
    			if(this.table[count].element!=null) //to see for an undeleted element in the table
    			{
    				newDummy.add(table[count].element);
    			}
    			
    		}
    	//	System.out.println(count);
    		count++;
    	}
    	//int i=this.overflow.size();
    	for(T h:this.overflow)
    	{
    		newDummy.add((T)h);
    	}
    	this.table=newDummy.table;
    	this.maxSize=newDummy.maxSize;
    	this.maxProbeSequence=newDummy.maxProbeSequence;
    	this.overflow=newDummy.overflow;
    	this.permissibleLimit=newDummy.permissibleLimit;
    	this.size=newDummy.size;		
    	System.out.println("resixe done");
    }
    public static void main(String args[]) throws FileNotFoundException
    {
    	DoubleHashing<Integer> dh=new DoubleHashing<>();
    	Scanner in;//enter the size follwed by the elements in the array
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
      int size=in.nextInt();
      Integer[] ii=new Integer[size];
      for(int i=0;i<size;i++)
      {
    	  ii[i]=in.nextInt();
          dh.add(ii[i]);
          if(i<8)
          {
        	  dh.remove(ii[i]);
          }
      }
      for(int i=0;i<ii.length;i++)
      {
    	  //System.out.println(dh.contains(ii[i]));'
    	  //18 22 34 28 46 24 52 53 78 48 42 36 423 28 26 64 68 88 98
      }
      System.out.println("adding done");
   /*   for(int i=0;i<size;i++)
      {
          System.out.println(dh.get(ii[i]));
      }*/
		System.out.println("resizeing starts");
		dh.resize();
		System.out.println("resizeing ends");
	      /*for(int i=0;i<ii.length;i++)
      {
    	  if(i==4||i==6||i==9)
    	  {
    	  dh.remove(ii[i]);
    	  }
    	  boolean h=dh.contains(ii[i]);
    	  System.out.println(h);
    	  if(!h)
    	  {
    		  dh.overflow.contains(ii[i]);
    		  System.out.println("inside set");
    	  }
      }*/
     // System.out.println("mn");
    }
}