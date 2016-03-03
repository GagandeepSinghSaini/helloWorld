package com.gagan.Stateless;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class ProcessShopList implements SessionBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		System.out.println("ProcessShopList.ejbActivate()");
		
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		System.out.println("ProcessShopList.ejbPassivate()");
		
	}

	@Override
	public void ejbRemove() throws EJBException, RemoteException {
		System.out.println("ProcessShopList.ejbRemove()");
		
	}

	@Override
	public void setSessionContext(SessionContext arg0) throws EJBException,
			RemoteException {
		System.out.println("ProcessShopList.setSessionContext()");
		
	}
	
	public void ejbCreate() throws CreateException, RemoteException {
		System.out.println("ProcessShopList.ejbCreate()");
	}
	
	public void ejbPostCreate() throws CreateException, RemoteException {
		System.out.println("ProcessShopList.ejbPostCreate()");
	}

	public void parseOrder() throws RemoteException{
		System.out.println("ProcessShopList.parseOrder()");
	}
	
}
