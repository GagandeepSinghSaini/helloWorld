package com.pattern.singleton.SigletonWithSerializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SingletonTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("*****WRITING OBJECT TO FILE");
		SerializedSingletonClass instance1 = SerializedSingletonClass.getInstance();
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream("C:/Users/gagandeepsingh/workspace/DesignPatterns/file/SingletonObj.txt"));
		out.writeObject(instance1);
		out.close();
		System.out.println(">>>>>>>READING OBJECT FROM FILE");
		ObjectInput input = new ObjectInputStream(new FileInputStream("C:/Users/gagandeepsingh/workspace/DesignPatterns/file/SingletonObj.txt"));
		SerializedSingletonClass instance2 = (SerializedSingletonClass)input.readObject();
		System.out.println("INSTANCE1: "+instance1);
		System.out.println("INSTANCE2: "+instance2);
		
	}
}
