package com.gagan.Stateless;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface ProcessShopRemote extends EJBObject{
	void parseOrder() throws RemoteException;
}
