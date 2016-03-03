package com.gagan.Stateless;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {
	public static void main(String[] args) {
		try {
			System.out.println("Going to initialize context");
			Context ctx =  new InitialContext();
			ProcessShopHome home = (ProcessShopHome)ctx.lookup("com.process");
			System.out.println("Received Home Object");
			try {
				ProcessShopRemote remote = home.create();
				System.out.println("Successfully received remote object");
				remote.parseOrder();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CreateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
