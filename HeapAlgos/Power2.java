import java.util.Comparator;
import java.util.PriorityQueue;

class PowerComparable2 implements Comparator<Power2>{

	@Override
	public int compare(Power2 o,Power2 o2) {
		// TODO Auto-generated method stub
		return (int) (o.power-o2.power);
	}
}
	
public class Power2 {

	double a;
	double b;
	double power;
	Power2(double a,double b)
	{
		this.a=a;
		this.b=b;
		this.power=calcpower(a,b);
	}
	
	private double calcpower(double a2, double b2) {
		// TODO Auto-generated method stub
		return Math.pow(a2, b2);
	}

	public void printPower(int n)
	{
		int count=0;
		PriorityQueue<Power2> q=new PriorityQueue<>(new PowerComparable2());
		q.add(this);
		Power2 dummy2=null;
		Power2 dummy=null;
		Power2 p=null;
		while(!q.isEmpty()&&count<n)
		{
			
			dummy2=q.peek();
			if(dummy==null||dummy.power!=dummy2.power)
			{
			System.out.println("a is "+dummy2.a+" b is"+dummy2.b+" power is "+dummy2.power);
            count++;
			}
			
			dummy=q.remove();
			
			if(dummy.a==2)
			{
				p=new Power2(dummy.a,dummy.b+1);
				q.add(p);
				p=new Power2(dummy.a+1,dummy.b);
				q.add(p);
				
			}
			else{
				p=new Power2(dummy.a+1,dummy.b);
				q.add(p);
					
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Power2 p=new Power2(2, 2);
		p.printPower(10);
		/*PriorityQueue<Power2> q=new PriorityQueue<>(new PowerComparable2());
		Power2 p=new Power2(2, 2);
		q.add(p);
		//p=new Power2(3,2);
		//q.add(p);
		int count=0;
		Power2 dummy2=null;
		Power2 dummy=null;
		while(!q.isEmpty()&&count<14)
		{
			
			dummy2=q.peek();
			if(dummy==null||dummy.power!=dummy2.power)
			{
			System.out.println("a is "+dummy2.a+" b is"+dummy2.b+" power is "+dummy2.power);
            count++;
			}
			
			dummy=q.remove();
			
			if(dummy.a==2)
			{
				p=new Power2(dummy.a,dummy.b+1);
				q.add(p);
				p=new Power2(dummy.a+1,dummy.b);
				q.add(p);
				
			}
			else{
				p=new Power2(dummy.a+1,dummy.b);
				q.add(p);
					
			}
		}*/
	}

}
