/**
 * 
 */
package com.rajni.pearson.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author rajni.ubhi
 *
 */
public class LinkedIntList implements IntList{
	private ListNode front;
	
	/**
	 * 
	 */
	public LinkedIntList() {
		// TODO Auto-generated constructor stub
		front = null;
	}
	
	public LinkedIntList(ListNode front) {
		this.front = front;
	}
	/* (non-Javadoc)
	 * @see com.rajni.pearson.collections.IntList#add()
	 */
	@Override
	public void add(int num) {
		// TODO Auto-generated method stub
		if(front == null) {
			front = new ListNode(num);
			return;
		}	
		ListNode current = front;
		while(current.next != null) {
			current = current.next;
		}
		current.next = new ListNode(num, null);
	}

	/* (non-Javadoc)
	 * @see com.rajni.pearson.collections.IntList#add(int, int)
	 */
	@Override
	public void add(int index, int value) {
		// TODO Auto-generated method stub
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		if(front == null) {
			front = new ListNode(value);
			return;
		}
		for(int i = 0 ; i < index-1 ; i++) {
			current = current.next;
		}
		current.next = new ListNode(value, current.next);
	}

	/* (non-Javadoc)
	 * @see com.rajni.pearson.collections.IntList#remove(int)
	 */
	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			front = front.next;
			return;
		}
		ListNode current = front;
		for(int i = 0 ; i < index-1 ; i++) {
			current = current.next;
		}
		current.next = current.next.next;
	}

	/* (non-Javadoc)
	 * @see com.rajni.pearson.collections.IntList#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		if(front == null) {
			return 0;
		}
		ListNode current = front;
		int count = 0;
		while(current != null) {
			count++;
			current = current.next;
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.rajni.pearson.collections.IntList#get(int)
	 */
	@Override
	public int get(int index) {
		// TODO Auto-generated method stub
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
			//return -1;
		}
		ListNode current = front;
		for(int i = 0 ; i < index ; i++) {
			current = current.next;
		}
		return current.data;
	}
	
	public ListNode nodeAt(int index) {
		// TODO Auto-generated method stub
		if(index < 0 || index >= size()) {
			//throw new IndexOutOfBoundsException();
			return null;
		}
		ListNode current = front;
		for(int i = 0 ; i < index ; i++) {
			current = current.next;
		}
		return current;
	}


	public String toString() {
		if(front == null) {
			return "[]";
		}
		String str = "[";
		ListNode current = front;
		str += current.data;
		while(current.next != null) {
			current = current.next;
			str += ","+current.data;
		}
		str+="]";
		return str;
	}
	
	public static void main(String[] args) {
		LinkedIntList list = new LinkedIntList();
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		System.out.println(list);
		list.add(2,6);
		System.out.println(list);
		list.remove(2);
		System.out.println(list);
		Iterator<Integer> itr = list.iterator();
		Iterator<Integer> itr1 = list.iterator();
		while(itr.hasNext()) {
			System.out.println();
			itr1.next();
		//	itr.remove();
		//	itr1.remove();
		}
		
		System.out.println(list);
	}
	
	private class ListNode {
		public int data;
		public ListNode next;
		
		/**
		 * 
		 */
		public ListNode() {
			// TODO Auto-generated constructor stub
			this(0, null);
		}
		
		public ListNode(int data , ListNode node) {
			this.data = data;
			this.next = node;
		}
		
		public ListNode(int data) {
			this(data , null);
		}
		
		public String toString() {
			return "Node : "+data;
		}
	}

	/* (non-Javadoc)
	 * @see com.rajni.pearson.collections.IntList#iterator()
	 */
	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Integer> {
		int counter = 0;
		ListNode node = front;
		boolean okToRemove = false;
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			ListNode current = nodeAt(counter);
			return current != null;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Integer next() {
			// TODO Auto-generated method stub
			try {
				int val = get(counter++);
				okToRemove = true;
				return val;
			} catch(IndexOutOfBoundsException e) {
				throw new NoSuchElementException();
			}
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			if(node == null || !okToRemove) {
				throw new IllegalStateException();
			}
			okToRemove = false;
			int count = counter-1;
			if(count < 0 || count >= size()) {
				throw new IllegalStateException();
			}
			LinkedIntList.this.remove(count);
			counter--;
		}
		
	}
}
