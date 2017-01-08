package optionalproject2;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ShuntingYardDummy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean parenthesisMismatch=false;
		HashMap<Character,Integer> h=new HashMap<>();  //hash map to assign priority values to the operators
		h.put('^', 4);
		h.put('*', 3);
		h.put('/', 3);
		h.put('+', 2);
		h.put('-', 2);
		h.put('!', 5);
		h.put('(',152);  //( is given the highest priority to make comparisons easier 
		Queue<Character> inputQueue=new LinkedList<>();
		String s="3+4*2/(1-5)^2^3+(2*3)^7^7";        //input string
		String ch="^*+-/(";
		Stack<Character> stack=new Stack<>();
		int flag=0;
		char[] c= s.toCharArray();	
		/*Loop invariant Initially parenthesismismatch is false,
		 * Initially stack,inputQuue are empty, when co[] contains all the character from the input
		 every time a loop runs we have a stack that contains the operators in the order specified by algorithm,
		 and we populate queue with both operands and operators according to algorithm.
		 after the end of iteration we will have a stack with the operators in postorder and queue 
		 with operands and operators arranged in postorder.
		 */
		for(char co:c)
		{
			if(co!=')')   
			{			
				if(ch.contains(co+"")) //checks if the char is operator or operand
				{
					if(stack.isEmpty()) //no priority conflict in case of empty
					{
						stack.push(co);    
					}
					else if(h.get(stack.peek())<h.get(co)) //priority less
					{
						stack.push(co);
					}
					else if(h.get(stack.peek())==h.get(co)) //priority same
                     { 
						if((stack.peek())!='^')
						{                             
					     inputQueue.add(stack.pop());
						stack.push(co);
						}
						else
						{                                 //for ^ right associativeness 
							 stack.push(co);                   //flag=1;
						    flag++;
						}
                      }
					else if(stack.peek()=='('){           //else if stack top is (
						                             // just push to stack
						stack.push(co);
					}
					else if(flag!=0){                  //code to handle right associativeness of ^
						while(!stack.isEmpty())
						{
							
						inputQueue.add(stack.pop());
						}
						flag=0;
						stack.push(co);
					}
					else{                           //input operator is of less priority than stack top
						inputQueue.add(co);
					}
				}
				else{                                    //operand we add to queue
					inputQueue.add(co);				
				}	
			}
			else{
				//loop invariants: initially both stack and queue contains so far prov=cessed elements
				//for every iteration we look for a '(' element from stack and add the top elemnt to queue
				//Termination:once '(' or stack isempty is found we terminate the loop. 
				while(!stack.isEmpty()&&stack.peek()!='(')
				{
					inputQueue.add(stack.pop());
				}
				if(stack.isEmpty())
				{
					parenthesisMismatch=true;
				System.out.println("invalid parenthesis match found");
				}
				else{
					stack.pop();	
				}
			}
		}
		while(!stack.empty()&&stack.peek()!='(')  //to add the stack elements to queue
		{
			inputQueue.add(stack.pop());
		}
		if(!stack.empty()&&stack.peek()=='(')  //to check excess of ( operator
		{
			parenthesisMismatch=true;
			System.out.println("invalid parenthesis match found");
		}
		while(!inputQueue.isEmpty()&&!parenthesisMismatch)   // to print only when parenthesis are correctly matched
		{
			System.out.println(inputQueue.poll());
		}
	}
}
