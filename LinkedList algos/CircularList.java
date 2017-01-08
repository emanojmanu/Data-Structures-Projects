package optionalproj;

import java.util.*;

public class CircularList<T extends Comparable<? super T>>
             implements Iterable<T> {/** Class Entry holds a single node of the list */
    public class Entry<T extends Comparable<? super T>> {
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

    CircularList() {
        header = new Entry<>(null, null);
        tail = header;
        size = 0;
    }

    public Iterator<T> iterator() { return new SLLIterator<>(header); }
    private class SLLIterator<E extends Comparable<? super E>>
        implements Iterator<E> {
	Entry<E> cursor, prev;
	SLLIterator(Entry<E> head) {
	    cursor = head;
	    prev = null;
	}

	public boolean hasNext() {
	    return cursor.next != header;
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
	tail.next = new Entry<>(x, header);
	tail = tail.next;
	size++;
    }

    void printList() {
   //   Entry<T> x = header.next;
	for(T item: this) {
	    System.out.print(item + " ");
	}

	System.out.println();
    }

    // Rearrange the elements of the list by linking the elements at even index
    // followed by the elements at odd index. Implemented by rearranging pointers
    // of existing elements without allocating any new elements.
     public <T extends Comparable<? super T>>void merge(CircularList<T> second)
    {
    	int flag=0;
		Entry<T> prevnextNode=null,prevNodeduplicateinL1=null; 
		Entry<T> LastNode= (Entry<T>) this.header;
		Entry<T> dummy1=(Entry<T>) this.header;
		Entry<T> dummy2=(Entry<T>) second.header.next; //first node in small list
		
	while(dummy1.next!=this.header)  //see that the node is present
	{
			if(flag==0&&dummy1.next.element.compareTo((dummy2.element))==0)
			{
				prevNodeduplicateinL1=dummy1.next;  //next node after common is stored in temp
				dummy1.next=dummy2;
				flag=1;                 //to stop the iteration after node matches. 
			/*	while(dummy2.next!=second.header)
				{	
				dummy2=dummy2.next;
				}*/
				second.tail.next=(CircularList<T>.Entry<T>) prevNodeduplicateinL1;
				/*
				dummy2.next=prevNodeduplicateinL1;*/
			}
			else 
			{
				dummy1=dummy1.next;
			}
	}	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CircularList<String> cl=new CircularList<>();
		cl.add("manoj1");
		cl.add("manoj2");
		cl.add("accac");
		cl.add("manoj3");
		cl.add("manoj4");
		cl.add("manoj5");
		cl.add("accac2");
		cl.add("manoj7");
		cl.add("manoj8");
		cl.add("manoj9");
		cl.add("accac2");
		cl.add("manoj10");
		CircularList<String> cl2=new CircularList<>();	
		cl2.add("accac2");
		cl2.add("accac3");
		cl2.add("accac4");
		cl2.add("a");
		cl2.add("a4");
		cl2.add("accac5");
		cl.merge(cl2);
		cl.printList();		
	}
}
