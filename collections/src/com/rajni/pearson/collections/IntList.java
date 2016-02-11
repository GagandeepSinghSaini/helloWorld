/**
 * 
 */
package com.rajni.pearson.collections;

import java.util.Iterator;

/**
 * @author rajni.ubhi
 *
 */
public interface IntList {
	public void add(int num);
	public void add(int index , int value);
	public void remove(int index);
	public int size();
	public int get(int index);
	public Iterator<Integer> iterator();
}
