/** 
 *  Singly linked list: Multiunzip
 *  Ver 1.0: initial description of linked list.
 *  Ver 1.1: added multiunzip: 2016/09/08.
 */

import java.util.*;

public class MultiUnzip<T> implements Iterable<T> {

    /** Class Entry holds a single node of the list */
    public class Entry<T> {
        T element;
        Entry<T> next;

        Entry(T x, Entry<T> nxt) {
            element = x;
            next = nxt;
        }
    }

    // Dummy header is used.  tail stores reference of tail element of list
    Entry<T> header, tail;
    int size;

    MultiUnzip() {
        header = new Entry<>(null, null);
        tail = header;
        size = 0;
    }

    public Iterator<T> iterator() { return new SLLIterator<>(header); }

    private class SLLIterator<E> implements Iterator<E> {
	Entry<E> cursor, prev;
	SLLIterator(Entry<E> head) {
	    cursor = head;
	    prev = null;
	}

	public boolean hasNext() {
	    return cursor.next != null;
	}
	
	public E next() {
	    prev = cursor;
	    cursor = cursor.next;
	    return cursor.element;
	}

	// To do: error checking
	// What should cursor be set to after a remove?
	public void remove() {
	    prev.next = cursor.next;
	    prev = null;
	}
    }

    // Add new elements to the end of the list
    void add(T x) {
	tail.next = new Entry<>(x, null);
	tail = tail.next;
	size++;
    }
    //print elements of list
    void printList() {
	for(T item: this) {
	    System.out.print(item + " ");
	}

	System.out.println();
    }

    // Rearrange the elements of the list by linking the elements at kth index
    // followed by the elements at next index. Implemented by rearranging pointers
    // of existing elements without allocating any new elements.  
    void unzip(int k)
    {
     	if(size < k+1) 
     	{  // Too few elements.  No change.
        return;
     	}    
    //arr and arr1 are used to handle first k nodes 	
    Entry[] arr = new Entry[k];
    Entry[] arr1 = new Entry[k];
    Entry<T> tail0 = header.next;
    for(int i=0;i<k;i++)
    {
        arr[i]=tail0;
    	arr1[i]=tail0;
    	tail0=tail0.next; 
    }    
    //c is used to handle (k+1)th(current) node
    Entry<T> c = tail0; 
    int i=0;
    
    // Invariant: arr[i] is the starting element of the chain of elements with every ith index.
 	// c is current element to be processed.
 	// i indicates the state of the finite state machine
 	// state = i indicates that the current element is added after taili (i=0,1).
    
  while(i<k && c!=null)
    {
    	arr[i].next=c;
    	arr[i]=c;    
    	 if(i==k-1)
    	{
    		c=c.next;
    		i=0;
    	}
    	else 
    	{
    		c=c.next;
    		i++;
    	} 		
    }
  
  	// Invariant: arr[i] is the starting element of the chain of elements with every ith index.
	// linking the chains of every ith element until k-1

    for(int j=0;j<k-1;j++)
    {
    	arr[j].next=arr1[j+1];
    }
    arr[k-1].next=null;
    }

    public static void main(String[] args) {
        int n = 23;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        MultiUnzip<Integer> lst = new MultiUnzip<>();
        
        for(int i=1; i<=n; i++) {
            lst.add(new Integer(i));
        }
        lst.printList();
        System.out.println("enter the k value for unzip");
        Scanner sc=new Scanner(System.in); 
        int k=sc.nextInt();
        //multiunzip with given k
	    lst.unzip(k);
	    //printing the list
        lst.printList();
    }
}
