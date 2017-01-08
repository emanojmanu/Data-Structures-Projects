/** Removing Duplicates
 *  @authors Divya Vudem, Manoj Kumar Ellanti, Srabonti Chakraborty, Alwin Johns
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Remove_Duplicates {
	@SuppressWarnings("unchecked")
	public static<T> int findDistinct(ArrayList<T> arr){
		HashSet<Classeh> al1=new HashSet();
		//Add all the elements from the array list to Hash set
	    al1.addAll((Collection<? extends Classeh>) arr);
	    arr.clear();//clear the remaining elements of array list
	    arr.addAll((Collection<? extends T>) al1); //add the elements to the array list
		return 0;}
	
	public static void main(String[] args) {	    
	    ArrayList<Classeh> al=new ArrayList();
	    al.add(new Classeh(5));
	    al.add(new Classeh(10));
	    al.add(new Classeh(15));
	    al.add(new Classeh(5));
	    al.add(new Classeh(20));
	    al.add(new Classeh(25));
	    al.add(new Classeh(10));
	    for(Classeh e: al){System.out.print(e.getn()+" , ");}
	    findDistinct(al);  
	    System.out.println("\n");
	    for(Classeh e: al){System.out.print(e.getn()+" , ");}
	}
}

