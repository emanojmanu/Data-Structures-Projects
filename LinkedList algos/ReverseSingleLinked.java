package optionalproj;

import java.util.Iterator;

import org.w3c.dom.Node;

class SinglyLinkList<T> implements Iterable<T> {

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

    SinglyLinkList() {
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
    void printList() {
	for(T item: this) {
	    System.out.print(item + " ");
	}
	System.out.println();
    }
    /*
     * here we are sending last node recursively 
     * and traversing till the last node 
     * to get the value of the node before last node
     * like list is 5 4 2 9 8 
      *     recursive (8) gives recursive(9) recursive(2)
      *     ......
     */
    void reversePrintListRecursive(Entry<T> node) {
    	Entry<T> dummy=node;   //to store incoming last 
    	Entry<T> dummyflag=null; //intailly null is assigned
    	Entry<T> headdummy=this.header;
    		while(headdummy.next!=dummy)
    	{
    		headdummy=headdummy.next;
    	    dummyflag=headdummy;     //used to store last 
    	}  
    	System.out.println(headdummy.next.element);
    	if(dummyflag!=null) //to check whether dummy flag is not header
    		                     // call the recursive function till 
    		                  // first element is traversed i.e., once 
    		                     //5 is reached we r going to stop
    		{
    	reversePrintListRecursive(headdummy);
    	}
    }
    
    /*
 void ReversePrintList()
 {
	 Entry<T> tail=this.tail;
	 Entry<T> current=this.header;
	 while(tail!=this.header&&current.next!=tail)
	 {
		 current=current.next;
		 if(current.next.equals(tail))
		 {
			 System.out.println(current.next.element);
			 tail=current;
			 current=this.header;
		 }
	 }
	 if(current.next==tail)
	 {
		 System.out.println(current.next.element);
		 
	 }
 }
  */  

    void ReversePrintList()
    {
   	 Entry<T> tail=this.tail;
   	 Entry<T> current=this.header;
   	 while(current.next!=tail)
   	 {
   		 current=current.next;                //traverse till the tail and print tail
   		 if(current.next.equals(tail))
   		 {
   			 System.out.println(current.next.element);
   			 tail=current;                      // reset tail to prev before tail
   			 current=this.header;           //traverse from starting 
   		 }
   	 }
   	 if(current==this.header)              //for the header element
   	 {                                      //where while fails we will print next element
   		 System.out.println(current.next.element);
   		 
   	 }
    }
     
    
    	public void reverse()
	{
		SinglyLinkList<T> l=this;
		int index=1;
		Entry<T> current=this.header.next,prev=null,nextNode=null;
		while(current!=null)
		{                               //swapping the next value of all the nodes till tail   
			nextNode=current.next;
            current.next=prev;   
			prev=current;
			current=nextNode;
		}
		this.tail=this.header.next;
		this.header.next=prev;		
		
	}

	public void reverseRecursive(Entry<T> tail)
	{
		Entry<T> current=this.header;
    	Entry<T> last=null;
		while(current.next!=tail)       //travering till the last  
		{
			current=current.next;
		}
		if(current==this.header)             //if the header is found we need 
			                          //to change this.tail and this.header
		{
			current.next.next=null;
			last=current.next;          //dummy to store objects header next
			                            //header next will be objects tail 
                             			//tail will be dummy
			this.header.next=this.tail;    //header next will be objects tail 
			this.tail=last;
		}
		else{                               // and making the changes to last element
			                                   //next value to current 
    		current.next.next=current;              
			reverseRecursive(current);       //passing the node before current as the new tail
			}
	}
	 }
public class ReverseSingleLinked {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*SinglyLinkList<String> list=new SinglyLinkList<String>();
		list.add("maon");
		list.add("1");
		list.add("m2");
		list.add("ma3");
		list.add("m4");
		list.add("poikn4");
		list.add("n4");
		*/
		SinglyLinkList<Integer> list=new SinglyLinkList<Integer>();
		list.add(23);
		list.add(3);
		list.add(2);
		list.add(2903);
		
		System.out.println("Original List");
list.printList();
	   System.out.println("reversing list nonrecursively");
	    list.reverse();
	    list.printList();   
	   System.out.println("reversing List recursively");
	   
        list.reverseRecursive(list.tail);
	    list.printList();
	    System.out.println(" Printing in reverse order nonrecursively");
	    list.ReversePrintList(); 

		System.out.println(" Printing in reverse order recursively");
	    list.reversePrintListRecursive(list.tail);
	    	}

}
