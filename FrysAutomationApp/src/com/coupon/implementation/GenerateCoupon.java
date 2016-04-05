package com.coupon.implementation;

import com.coupon.service.ServiceCoupon;
import com.frys.interfaces.FrysCouponProcess;



public class GenerateCoupon implements FrysCouponProcess{

	@Override
	public boolean service(ServiceCoupon sCoupon) {

		try{
		     if(sCoupon!=null) {
		    	 if(sCoupon.checkData()) {
		    		 sCoupon.deleteFrysCoIfExist();
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
