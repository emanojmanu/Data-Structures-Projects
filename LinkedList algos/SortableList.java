/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** 
 *  Ver 1.0:  Sorting Linked List: 2016/09/08.
 */
public class SortableList<T extends Comparable<? super T>> extends SinglyLinkedList<T> {

    void mergeSort() 
    { mergeSort(this);      
    }
    
    public static<T extends Comparable<? super T>> void mergeSort(SortableList<T> lst) {
        
        if (lst.header == null || lst.header.next == null){
            return;
        }
        int countElements = 0;
        SortableList<T>.Entry<T> temp = lst.header;
        
        //Invariant : countElements counts number of elements to find the middle element
        while( temp.next != null){
            countElements++;
            temp = temp.next;
        }
        
        //too few elements
        if (countElements == 1){
            return;
        }
        
        //calculate middle element
        int mid = countElements/2;
        
        SortableList<T>.Entry<T> currNode = lst.header;
        SortableList<T>.Entry<T> left = lst.header.next;
        SortableList<T>.Entry<T> right = null;
        SortableList<T>.Entry<T> next = null;        
        int itr =0;
        
        //create 2 lists, one for left list and other for the right one
        SortableList<T> leftLst = new SortableList<>();
        SortableList<T> rightLst = new SortableList<>();
        
        //Invariant : elements are added to leftLst from beginning to mid elements      
        while(itr < mid){
            leftLst.add(left.element);
            next = left.next;
            left = next;
            itr++;
        }
       //Invariant : elements are added to rightLst from mid to the end of the list
        right = left;
        while(right != null){
           rightLst.add(right.element);
           next = right.next;
           right = next;
        }
        
        //recursively call merge sort
        mergeSort(leftLst);
        mergeSort(rightLst);        
        leftLst.merge(rightLst);
        
       //print out the final sorted list
        System.out.println("sorted list");
        leftLst.printList();
        
         
    }
    
    public void merge(SortableList<T> list) 
    {        
        this.printList();
        list.printList();
        SortableList<T>.Entry<T> head;
        //comparing elements of both lists
        if (this.header.next.element.compareTo(list.header.next.element) < 0){
            head = this.header.next;
        }
        else
        {
            head = list.header.next;
            list.header.next = this.header.next;
            this.header.next = head;
        }
        
        SortableList<T>.Entry<T> leftCurr = this.header.next;
        SortableList<T>.Entry<T> rightCurr = list.header.next;
        head = leftCurr;
        
        //Invariant : comparing left and right lists elements 
        //merging the elements into left list accordingly
        //loop continues until the list ends
        if (head.next != null)
        while(leftCurr != null){
            if (leftCurr.element.compareTo(rightCurr.element) > 0){
                SortableList<T>.Entry<T> temp = leftCurr;
                leftCurr = rightCurr;
                rightCurr = temp;        
            }
            leftCurr = leftCurr.next;
        }
        if (head.next != null)
        if (leftCurr == null){
        leftCurr = rightCurr;
        }        
        this.header = head;     
    }
    
    public static void main(String[] args) {
    	//creating a list and adding elements to the list
        SortableList<Integer> lst = new SortableList<>();
        lst.add(2);
        lst.add(1);
        lst.add(4);
        lst.add(3);
        lst.add(8);
        lst.mergeSort();
    }
    
}
