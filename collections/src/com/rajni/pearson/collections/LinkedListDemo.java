/**
 * 
 */
package com.rajni.pearson.collections;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author rajni.ubhi
 *
 */
public class LinkedListDemo {
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		ListIterator<Integer> itr = list.listIterator();
		while(itr.hasNext()) {
			System.out.println("First Next: "+itr.next());
		//	itr.add(4);
			System.out.println("List contents Befor remove :"+list);
		//	System.out.println("First Previous : "+itr.previous());
		//	itr.previous();
			//itr.remove();
			System.out.println("List contents After remove : "+list);
			//System.out.println("Next after remove : "+itr.next());
		}
		
		while(itr.hasPrevious()) {
			System.out.println("Adding element 8 : ");
			itr.add(8);
			System.out.println("previous :"+itr.previous());
			System.out.println("List contents before removing : "+list);
			System.out.println("Removing element ");
			itr.remove();
			System.out.println("List contents : "+list);
			System.out.println("previous :"+itr.previous());
		}
	}
}
