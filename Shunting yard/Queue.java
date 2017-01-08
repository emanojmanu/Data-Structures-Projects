/** 
 *  Array bound operations : Queue
 *  Ver 1.0: added multiplication: 2016/09/19.
 */

public class Queue<T> {
	int front,rear,max;
	T[] arr;
	
//Constructor to create array with size of 16
	 public Queue()
	 {
		 max=16;
		 arr = (T[]) new Object[max];
		 front=-1;
		 rear=-1;		
	 }	 

	 public boolean isEmpty()
	 {
		 //returns true if array size is less than 20% of its size 
		 int j=(int) (0.2*(max-1));
		 if(rear == j)
		 {
			   return true;}
			else return false;
	 }
	 public boolean IsFull()
		{
		//returns true if array size is 90% of its size 
		 int j=(int) (0.9*(max-1));
		 if(rear == j)
		 {
			   return true;}
			else return false;
		}
	 public void offer(T i) 
	 {	
		//adding an element
			if (front==-1&&rear==-1)
			{ 
				System.out.println("element added front:" + i);
				front = rear = 0; 
				arr[rear] = i;
				return;
			}
			//Invariant : if array is 90% full, resize the array
			 if(IsFull())
			{
				 	resize(max*2);			
					System.out.println("element added resizing:" + i);
					arr[++rear] = i;				
			}
		 else { 
			//Invariant : adding the element to the queue
			 System.out.println("element added normal:" + i);
			 rear = (rear+1)%max;
			 arr[rear] = i;
		 }	 
			 
	 }
	 public T poll()
	 {
		//Invariant : if array is just 20% full, shrink the array
		 if(isEmpty()){
			shrink();
			return null;
		 }
		//Invariant : if array is empty set front and rear to -1
		 else if(front == rear ) 
			{
				rear = front = -1;
				return null;	
			}
		//Invariant : pop the top element from the queue	 
		 else {
			 front = (front)%max;
			 T x= (T) arr[front];
			 front++;
			 System.out.println("Popped element:" + x);
			 return  x;} 
	 }
	 public void resize(int i)
	 {
		 //Invariant : create an object of size i 
		 T[] arr1 = (T[]) new Object[i];
		//Invariant : copy elements from array
	        for (int j = 0; j <=rear; j++)
	        {
	        	arr1[j] = arr[j];
	        }
	        arr = arr1;
	        max=i;	
	        //pointing array to null such that array memory is free
	        arr1=null;
	        System.out.println("size resized to :" +max);
	 }
	 public void shrink()
	 {
		int i=max/2;
		//Invariant : maintaining minimum array size of 16
		if(i<=16) i=16;
		else i=max/2;
		//Invariant : create an object of size i
		 T[] arr1 = (T[]) new Object[i];
		//Invariant : copy elements from array
	        for (int j = 0; j <=rear; j++)
	        {
	        	arr1[j] = arr[j];
	        }
	        arr = arr1;
	        max=i;	
	        //pointing array to null such that array memory is free
	        arr1=null;
	       System.out.println("size shrinked to :" +max);
	 }
	 public void printlist()
	 {
		 //printing the queue elements
		    for (int j = 0; j <=rear; j++)
	        {
	        	System.out.println("check : "+ arr[j]);
	        }
	 }
	 

	 
	 public static void main(String[] args) {	     
	     Queue<Integer> lst = new Queue<>();  
	     lst.offer(10);
	     lst.poll();
	     lst.printlist();
	     lst.offer(20);
	     lst.offer(30);
	     lst.offer(40);
	 }
}
