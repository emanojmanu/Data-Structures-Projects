/** Class containing Integer Object
 *  @authors Divya Vudem, Manoj Kumar Ellanti, Srabonti Chakraborty, Alwin Johns
 */

public class Classeh {
	public Integer n; //element in Classeh class
	
	//Constructor
	Classeh(Integer n)
	{
	    this.n = n;
	}
	
	//Function to retreive n value
	  public int getn()
	    {
	        return n;
	    }
	
	@Override
	public boolean equals(Object other) {
		/*
		 * Compare the values of the objects based on the elements in it
		 * Return true if they are same else return false
		 */
		 if (other instanceof Classeh)
         {
			 Classeh c = (Classeh) other;
             boolean flag=(c.n.equals(this.n)); 
             return flag;
         }
         else 
             {
                 return false;
             }
	}

	@Override
	//Return hash code of the element
	public int hashCode() {
	    int hashCode = n.hashCode();
	     return hashCode;
	}
}
