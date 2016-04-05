package com.frys.controller;

import com.coupon.service.ServiceCoupon;

public class MainCoupon  {
	
	//final static Logger logger = Logger.getLogger(MainCoupon.class);
	public static void main(String[] args) {
		 MainCoupon coup = new MainCoupon();
		 ServiceCoupon sCoupon = new ServiceCoupon();
		 sCoupon.getUserInfo();
		 coup.applyCoupon(sCoupon);
	}
	
	public boolean applyCoupon(ServiceCoupon sCoupon) {

		try{
		     if(sCoupon!=null) {
		    	 if(sCoupon.checkData()) {
		    		 
		    		 if(sCoupon.updateCouponData()) {
		    			System.out.println("MainCoupon.applyCoupon(): SUCCESSFULLY UPDATED DATA FOR COUPON");
		    			return true;
		    		 }else {
		    			 System.out.println("MainCoupon.applyCoupon(): SORRY! SOME PROBLEM HAS OCCURED WHILE UPDATING DATA");
		    			 return false;
		    		 }	 
		    	 }else {
		    		 if(sCoupon.insertCouponData()) {
		    			 System.out.println("MainCoupon.applyCoupon(): SUCCESSFULLY INSERTED DATA FOR COUPON");
		    			 return true;
		    		 } else {
		    			 System.out.println("MainCoupon.applyCoupon(): SORRY! SOME PROBLEM HAS OCCURED WHILE INSERTING DATA");
		    			 return false;
		    		 }
		    	 }
		     }
		}catch(Exception exp) {
			System.out.println("CoupnDbConnection.main()"+exp);
		}
	return false;
	}
}
