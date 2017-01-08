import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

/** Data Structures Performance Comparision
 *  @authors Divya Vudem, Manoj Kumar Ellanti, Srabonti Chakraborty, Alwin Johns
 */

public class TreeMap_Comparisions {

	public static void main(String args[])
	{
		TreeSet tm= new TreeSet();
		HashSet hs = new HashSet();
		LinkedList ll = new LinkedList();
		SkipListImpl<Long> skipList = new SkipListImpl<>();
		//number of elements
		//int size=(int) Math.pow(2, 15);
		int size=20000000;
		
		/*Invariant
		 * perform insert operation if k value is 1
		 * perform remove operation if k value is 2
		 * perform contains operation if k value is 3
		 */
		Timer t = new Timer();
		t.start();
		for (int i=0;i<size;i++)
		{
			int k=( 1 + (int) (Math.random() * 3) );
			if(k==1)
				tm.add(( 1 + (int) (Math.random() * size) ));
			else if(k==2)
				tm.remove(( 1 + (int) (Math.random() * size) ));
			else if(k==3)
				tm.contains(( 1 + (int) (Math.random() * size) ));
		}
		t.end();
		System.out.println("tree map : "+t);
			
		//Hash map
		/*Invariant
		 * perform insert operation if k value is 1
		 * perform remove operation if k value is 2
		 * perform contains operation if k value is 3
		 */
		Timer t2 = new Timer();
		t2.start();
		for (int i=0;i<size;i++)
		{
			int k=( 1 + (int) (Math.random() * 3) );
			if(k==1)
				hs.add((long) (1 + (int) (Math.random() * size)));
			else if(k==2)
				hs.remove((long) (1 + (int) (Math.random() * size)));
			else if(k==3)
				hs.contains((long) (1 + (int) (Math.random() * size)));
		}
		t2.end();
		System.out.println("Hash Map : "+t2);
		
		//Linked List
		/*Invariant
		 * perform insert operation if k value is 1
		 * perform remove operation if k value is 2
		 * perform contains operation if k value is 3
		 */
		Timer t3 = new Timer();
		t3.start();
		for (int i=0;i<size;i++)
		{
			int k=( 1 + (int) (Math.random() * 3) );
			if(k==1)
				hs.add((long) (1 + (int) (Math.random() * size)));
			else if(k==2)
				hs.remove((long) (1 + (int) (Math.random() * size)));
			else if(k==3)
				hs.contains((long) (1 + (int) (Math.random() * size)));
		}
		t3.end();
		System.out.println("Linked List : "+t3);
		
		//SkipList
		/*Invariant
		 * perform insert operation if k value is 1
		 * perform remove operation if k value is 2
		 * perform contains operation if k value is 3
		 */
		Timer t1 = new Timer();
		t1.start();
		for (int i=0;i<size;i++)
		{
			int k=( 1 + (int) (Math.random() * 3) );
			if(k==1)
				skipList.add((long) (1 + (int) (Math.random() * size)));
			else if(k==2)
				skipList.remove((long) (1 + (int) (Math.random() * size)));
			else if(k==3)
				skipList.contains((long) (1 + (int) (Math.random() * size)));
		}
		t1.end();
		System.out.println("Skip List : "+t1);
		
	}
}
