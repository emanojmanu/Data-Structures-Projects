import java.util.LinkedList;

/** Separate Chaining Implementation
 *  @authors Divya Vudem, Manoj Kumar Ellanti, Srabonti Chakraborty, Alwin Johns
 */

public class SeparateChaining<T> {
static int size=10;
LinkedList<T>[] H;

//Constructor to declare Hash Table as a list of linked lists
SeparateChaining(int N){
	H=new LinkedList[N];
	for(int i=0;i<N;i++){
		H[i]=new LinkedList<T>();
	}
}

//Hash code
int HashCode(T key){
	return (Integer)key%size;
}

//Adding an element to Hash Table
public boolean add(T x){
	int i=HashCode(x)%size;
	/*
	 * If elements is already in the list return false
	 * Else add element and return true
	 */
	if(!H[i].contains(x)){
	H[i].add(x);
	return true;}
	else return false;
}

public boolean contains(T x){
	int i=HashCode(x);
	/*
	 * If table contains element,  return true
	 * Else return false
	 */
	if(H[i].contains(x)) return true;
	else
	return false;	
}


public void remove(T x)
{
	/*
	 * Find hash code of the element and remove the element
	 */
	int i=HashCode(x);
	H[i].remove(x);
}

	public static void main(String args[]){
		
		SeparateChaining<Integer> s= new SeparateChaining(10);
		s.add(new Integer(10));
		s.add(new Integer(12));
		s.add(new Integer(13));
		s.add(new Integer(20));
		s.add(new Integer(10));
		s.add(new Integer(18));
		s.remove(18);
		System.out.println(s.contains(20));
		for(int i=0;i<size;i++){
			System.out.println("Array : "+i);
			for(Integer x: s.H[i])
				System.out.println(x);
		}
	}
}
