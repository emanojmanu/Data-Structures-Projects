import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/** Skip List Implementation
 *  @author Divya Vudem
 */

public class SkipListImpl<T extends Comparable<? super T>> implements
		SkipList<T> {

	   //structure of each node in skiplist. It contains element and Entryarr node
	   public class Entry<T> {
	        T element;
	        Entryarr[] ar;

	        Entry(T x, int i) 
	        {
	            element = x;
	            ar= new Entryarr[i];
	            for(int j=0;j<i;j++)
	            	ar[j]=new Entryarr();
	        }
	   
	    }
	   
	   //Structure of each level of height of a node. It has width to next node and reference to other entry
       public class Entryarr<T>{
       	Entry<T> node;
       	int width;
       	
      //constructor to declare node and width
       	Entryarr()
       	{
       		Entry<T> node=null;
       		width=0;
       	}
       	
       }
       
       
	
	   Entry<T> header, tail,dummy,tail1;
	    int size,maxlevel=10;
	    T last;
	  
	  //constructor to declare header and tail
	    SkipListImpl()   
	    {
	    	header = new Entry<>((T) new Long(Long.MIN_VALUE),10);
	    	tail = new Entry<>((T) new Long(Long.MAX_VALUE),10);
	    	for(int i=0;i<10;i++)
	    	{	    	
	    		header.ar[i].node=tail;
	    		header.ar[i].width=0;
	    		tail.ar[i].node=tail;
	    		tail.ar[i].width=0;
	    	
	    	}	    	
	    	
	    }
	    
	  //find all the blocking nodes
	    public Entry[] find(T x) 
	    {
	    	Entry<T> p= this.header;
	    	Entry<T>[] prev = new Entry[maxlevel] ;
	    	int i;
	    	/*
	    	 * Invariant : Go from maxlevel to down until 0
	    	 * check if the element is greater than it and if it is obstructing add it to prev
	    	 * update p value accordingly 
	    	 */
	    	for(i=maxlevel-1;i>=0;i--)
	    	{
	    		while(x.compareTo((T) p.ar[i].node.element) > 0)
	    		{
	    			p=p.ar[i].node;
	    		}
	    	
	    		prev[i]=p;
	    	}
			return prev;
	    }   
	

	//add an element to skiplist
    @Override
    public boolean add(T x) 
    {
    	Entry<T>[] prev =  (SkipListImpl<T>.Entry<T>[]) find(x);
    	int i;
    	if(prev[0].ar[0].node.element.equals(x)) //duplicate element added
    	{
    		prev[0].ar[0].node.element=x;
    		prev=null;
    		return false;
    	}
    	else
    	{
    		int level= choice(maxlevel);
    		if(level==maxlevel)level=maxlevel-1;
    		int j=maxlevel-1;
    		/* 
    		 * update width nodes above the given level
    		  */
    		while(j>level){
    			prev[j].ar[j].width++;
    			j--;
    			}    		
    		Entry<T> n= new Entry<T>(x,level+1);
    		
    		/*Invariant
    		 * update widths of elements of given level
    		 * if a blocking element q has height greater than the newly added element
    		 * l is the distance from q to next element
    		 * k is the distance from q to newly added element
    		 */
    		for(i=level;i>=0;i--)
    		{
    			int l=-1;
    			if(prev[i].ar[i].node!=tail)
    			{ 
    			Entry<T> q= prev[i];
    			T r=  (T) prev[i].ar[i].node.element;
    			while(r.compareTo((T) q.element) > 0){q=q.ar[0].node; l++;}
    			}
    			else  l=prev[i].ar[i].width;    			
    			Entry<T> p= prev[i];
    			//k is distance between prev and new adding element(x)
    			int k=-1;
    			while(x.compareTo((T) p.element) > 0){p=p.ar[0].node; k++;}    			
    			n.ar[i].node=prev[i].ar[i].node;
    			n.ar[i].width=l-k;
    			prev[i].ar[i].node=n;   
    			prev[i].ar[i].width=k;
    			
    		} 		
    		
    		//update last element
    		if(size==0){last=x;}
    		else if(x.compareTo(last)>0){last=x;} 
    		size++; //increment size
    		prev=null;
			return true;
    	}
    }
    
    //find element at a specified index n
	@Override
	public T findIndex(int n) {		
		if(n>size)return null;
		else{
		Entry<T> p= this.header;
		Entry<T> k= this.header;
		Entry<T> q=null;n=n+1;
    	int i=maxlevel-1,sum = 0;
    	/*Invariant
    	 * find the specified index based on width
    	 * increment width in binary search format
    	 */
    	while(i>=0)
    	{
    		if(sum+p.ar[i].width+1==n) {
    		q=p.ar[i].node; break;}
    		else if(sum+p.ar[i].width+1<n)
    		{
    		sum=sum+p.ar[i].width+1;	
    		p=p.ar[i].node;
    		}
    		else
    			i--;	
    	}
    	return (T) q.element ;
	}
		}
    
	//find level for the new node
    private int choice(int maxlevel) 
    {
    	int l=0;
    	Boolean b;
    	Random randomno = new Random();
    	/*
    	 * Invariant: find the random boolean value 
    	 * repeat it until we get 1
    	 */
    	while(l<maxlevel)
    	{
    	b=randomno.nextBoolean();
    	if(b) break;
    	else l++;
    	}
		return l;
	}

 // Greatest element that is <= x, or null if no such element
    @Override
    public T floor(T x) 
    {
    	//If x is less than the first element, return null
    	if(x.compareTo((T) this.header.ar[0].node.element)<0){return null;}
    	else{
    	Entry<T> p= this.header;
    	Entry<T> prev = null;
    	int i;
    	/*Invariant
    	 * Start the loop with maxlevel and compare with the element
    	 * if the element is greater update p accordingly
    	 * Repeat this until we find the element
    	 */
    	for(i=maxlevel-1;i>=0;i--)
    	{
    		while(x.compareTo((T) p.ar[i].node.element) >= 0)//x>p.next[i] 
    		{
    			p=p.ar[i].node;
    		}
    	}
		prev=p;
	return prev.element;}
    }

    // Is the list empty?
    @Override
    public boolean isEmpty()
    {
    	if(this.header.ar[0].node==tail)
    		return true;
    	else return false;
    }


    // Return the last element of the list
    @Override
    public T last()
    {
    	if(this.header.ar[0].node!=tail)
      		 return last;
      	    	else return null;
	
    }



    // Greatest element that is >= x, or null if no such element
	@Override
	public T ceiling(T x) {
		//if x is greater than last element, return null
		if(x.compareTo(last)>0){return null;}
		else{
	 	Entry<T> p= this.header;
    	Entry<T> prev = null;
    	int i;
    	/*Invariant
    	 * Start the loop with maxlevel and compare with the element
    	 * if the element is greater update p accordingly
    	 * Repeat this until we find the element
    	 */
    	for(i=maxlevel-1;i>=0;i--)
    	{
    		while(x.compareTo((T) p.ar[i].node.element) >= 0)//x>p.next[i] 
    		{
    			p=p.ar[i].node;
    		}
    	}
		prev=p;
		//return if we find an exact match
		if(prev.element.equals(x))
			return prev.element;
		//return next element if we don't find an exact match
		else
			return (T) prev.ar[0].node.element;}
	}


	//IF x is in skiplist
	@Override
	public boolean contains(T x) {
		Entry<T>[] p =  (SkipListImpl<T>.Entry<T>[]) find(x);
    	//check if the element we found is equal to x
    	if(x.compareTo((T) p[0].ar[0].node.element) == 0)
    		return true;
    	else
    		return false;
	}
	
	
	//First element of skiplist
	@Override
	public T first() {
		if(this.header.ar[0].node!=tail)
    		return (T) this.header.ar[0].node.element;
    	else return null;
	}



	//Iterator for skiplist
    @Override
    public Iterator<T> iterator() 
    {
    	return new SLIterator<>(this.header);
    }
    
    private class SLIterator<T> implements Iterator<T> 
    {
    	Entry<T> cursor, prev;
    	SLIterator(Entry<T> head) 
    	{
    	    cursor = head; //to save position of skiplist
    	    prev = null;
    	}
    	//Check if the skiplist has further elements
		@Override
		public boolean hasNext() {
		    return cursor.ar[0].node != tail;
		}
		//return next element in the skiplist
		@Override
		public T next() {
			 prev = cursor;
			    cursor = cursor.ar[0].node;
			    return cursor.element;
		}
	}


    //remove element x from the skiplist
	@Override
	public T remove(T x) {
	  	Entry<T>[] prev =  (SkipListImpl<T>.Entry<T>[]) find(x);
    	Entry<T> n= null;
    	/*Invariant
    	 * Check if prev[i] is equal to x
    	 * repeat this until we find the element 
    	 * Remove the links associated with it and updates widths
    	 */    	
    	if(prev[0].ar[0].node.element.equals(x))
    	{
    		int level=prev[0].ar.length+1;
    		
    		n=prev[0].ar[0].node;
    		if(last.equals(x)) last=prev[0].element;
    		for(int i=0;i<maxlevel;i++)
    		{
    			
    			if(prev[i].ar[i].node==n)
    				{
    				prev[i].ar[i].node=n.ar[i].node;
    				prev[i].ar[i].width=prev[i].ar[i].width+n.ar[i].width;
    				}
    			else 
    				prev[i].ar[i].width--;
    		}
    		
    		size--; //decrement size
    		
    		return n.element;
    	}
    	else    	
    		return null;	
	}


	//return size of the list
	@Override
	public int size() {
		return size; 
	}
	
	//Build the skiplist into ideal skiplist
    @Override
    public void rebuild() 
    {
    	Entry<T>[] arr =  new Entry[size] ;
    	int k =(int) (Math.log(size)/Math.log(2))+1;
    	reBuild(arr,0,size-1,k);
    	/*Invariant
    	 * Dummy node acts as temporary header for ideal skiplist 
    	 */
    	dummy = new Entry<>((T) new Long(Long.MIN_VALUE),k);
    	for(int i=0;i<k;i++)
    	{	    	
    		dummy.ar[i].node=dummy;
    		dummy.ar[i].width=0;
    	
    	}
    	
    	buildlinks(arr);
    	header=dummy;    	
    }

    
    //Build ideal skiplist 
	private void reBuild(Entry[] arr, int p, int r, int k) 
    {
		/*Invariant
		 * Max height is k
		 * build array with ideal heights
		 * build array of height k-1 at every call and make it to half for next call until it reaches 1
		 */
		if(p<=r)
		{
			if(k==0)
			{
				for(int i=p;i<r;i++){
					arr[i]=new Entry<T>	(null,1);						
				}
			}
			else{
				int q=(p+r)/2;//reduce height to half
				arr[q]=new Entry<T>(null,k);
				reBuild(arr,p,q-1,k-1);
				reBuild(arr,q+1,r,k-1);
			}

    	}
    }
	
	
	
    private void buildlinks(SkipListImpl<T>.Entry<T>[] arr) 
    {
    int i,k,l=0;
    i=0;
    if(i==0){
    	arr[i].element=findIndex(i);
    	k=arr[i].ar.length;	
    	l=k;
		for(int j=0;j<k;j++)
		{
			dummy.ar[j].node= arr[i];
		}
		i++;
    }

	for(i=1;i<size;i++)
	{
		arr[i].element=findIndex(i);
		k=arr[i].ar.length;
		if(l>k)l=k;
		for(int j=0;j<l;j++)
		{
			(dummy.ar[j].node).ar[j].node=arr[i];
		}
		k=arr[i].ar.length;	
		for(int j=0;j<k;j++)
		{
			dummy.ar[j].node= arr[j];
		}
	
	}
		
	}
}