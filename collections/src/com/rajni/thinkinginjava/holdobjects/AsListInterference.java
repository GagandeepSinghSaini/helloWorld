/**
 * 
 */
package com.rajni.thinkinginjava.holdobjects;

import java.util.Arrays;
import java.util.List;

/**
 * @author rajni.ubhi
 *
 */
public class AsListInterference {
	public static void main(String[] args) {
		List<Snow> snow = Arrays.asList(new Slush() , new Powder(),new Crusty());
		
		List<Snow> snow1 = Arrays.asList(new Heavy(),new Light(),new Powder(),new Crusty());
	}
}
class Arrays1{
	public static <T> Lists<T> asList(T...ts ) {
		return null;
	}
}

class Lists<E> {
	
}
