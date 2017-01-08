import java.util.PriorityQueue;

public class KsmallestBinaryHeap<T extends Comparable <? super T>> {

	PriorityQueue<T> q;
	
	public KsmallestBinaryHeap() {
		// TODO Auto-generated constructor stub
	 q=new PriorityQueue<>();
	
	}
	
	public T Kthsmallest(int k)
	{
		PriorityQueue<T> q2=new PriorityQueue<>();
		int count=0;
		while(!q.isEmpty()&& count<k-1)
		{
			T dummy=q.remove();
			q2.add(dummy);
			count++;
		}
		if(count==k-1)
		{
			T dummy=q.peek();
			q.addAll(q2);
			return dummy;
		}
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		KsmallestBinaryHeap<Integer> k=new KsmallestBinaryHeap<>();
		
		for(int i=0;i<10;i++)
		{
			int l=(int) ((Math.random()+1)*i);
			System.out.println(l+ " l");
		k.q.add(l);
		}
		int j=0;
		//System.out.println(k.Kthsmallest(4));
		while(j<=9)
		{
			j++;	
			
		System.out.println(k.Kthsmallest(j));
		}
		}

}
