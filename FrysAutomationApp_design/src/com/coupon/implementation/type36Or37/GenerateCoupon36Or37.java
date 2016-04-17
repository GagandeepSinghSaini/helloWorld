package com.coupon.implementation.type36Or37;

import com.coupon.util.HelpingCouponService;
import com.frys.interfaces.FrysCouponProcess;
import com.frys.interfaces.FrysCouponType;



public class GenerateCoupon36Or37 implements FrysCouponProcess{

	public GenerateCoupon36Or37() {
		
	}
	@Override
	public boolean service(FrysCouponType couponTypeService) {

		try{
			if(couponTypeService!=null) {
				HelpingCouponService couponserv = new HelpingCouponService(couponTypeService);
		    	 if(couponserv != null && couponserv.checkCouponData()) {
		    		 couponserv.deleteFrysCoIfExist();
		    		 if(couponserv.updateCouponData()) {
		    			System.out.println("MainCoupon.applyCoupon(): SUCCESSFULLY UPDATED DATA FOR COUPON");
		    			return true;
		    		 }else {
		    			 System.out.println("MainCoupon.applyCoupon(): SORRY! SOME PROBLEM HAS OCCURED WHILE UPDATING DATA");
		    			 return false;
		    		 }	 
		    	 }else {
		    		 if(couponserv.insertCouponData()) {
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
