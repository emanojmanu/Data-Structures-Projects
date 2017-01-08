import java.util.Comparator;
import java.util.PriorityQueue;

class Tree<T extends Comparable<? super T>>
{
	Tree left;
	Tree right;
	String code;
	Double element;
	public Tree(double x)
	{
		left=null;
		right=null;
		code="";
		element=x;
	}
}
class DummyComparator<T extends Comparable<? super T>> implements Comparator<Tree>{

	@Override
	public int compare(Tree arg0, Tree arg1) {
		// TODO Auto-generated method stub
		return arg0.element.compareTo(arg1.element);
	}
	
}
public class HuffmanCoding {
	
	public static void  preOrder(Tree root)
	{
		if(root!=null)
		{
			if(root.left!=null)
			{
			root.left.code=root.left.code.concat(root.code).concat("0");
			}
			if(root.right!=null)
			{
			root.right.code=root.right.code.concat(root.code).concat("1");
			}
			preOrder(root.left);
			preOrder(root.right);	
			System.out.println("infinte ");
		}
	}

	public static void main(String args[])
	{
		Double[] x={0.05,0.05,0.2,0.35,0.05,0.3};
		Tree[] node=new Tree[x.length];
		PriorityQueue<Tree> q=new PriorityQueue<>(10,new DummyComparator());
		int i=0;
		for(double k:x)
		{
			node[i]=new Tree(k);
			
			q.add(node[i]);
			i++;
		}
		Tree t=null;
		/*Tree[] treearr=new Tree[x.length-1];
		for(int l=0;l<x.length-1;l++)
		{
		treearr[l]=new Tree(0.0);
			Tree left=q.remove();
			Tree right=q.remove();
			System.out.println(left.element+" left"+right.element+" rigth");
			double sum=left.element+right.element;
			treearr[l].element=sum;
			treearr[l].left=left;
			treearr[l].right=right;
			q.add(treearr[l]);
			//System.out.println(sum);
		}*/
		//Tree[] treearr=new Tree[x.length-1];
		for(int l=0;l<x.length-1;l++)
		{
		t=new Tree(0.0);
			Tree left=q.remove();
			Tree right=q.remove();
			System.out.println(left.element+" left"+right.element+" rigth");
			double sum=left.element+right.element;
			t.element=sum;
			t.left=left;
			t.right=right;
			q.add(t);
			//System.out.println(sum);
		}
		/*
		preOrder(t);*/
	/*//preOrder(treearr[x.length-2]);
		System.out.println("ds");*/
		
	}
}
