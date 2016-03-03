package com.gagan.Stateless;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface ProcessShopHome extends EJBHome{
	ProcessShopRemote create() throws CreateException, RemoteException;
}
