package com.frys.factory;

import com.coupon.implementation.type36Or37.DeleteCoupon36Or37;
import com.coupon.implementation.type36Or37.GenerateCoupon36Or37;
import com.frys.interfaces.FrysCouponProcess;

public class CouponFactory {
	private static FrysCouponProcess cprocess = null;
	public static FrysCouponProcess getOperationInstance(String operation) {
		
		if(("generate_coupon").equalsIgnoreCase(operation)) {
			cprocess = new GenerateCoupon36Or37();
		}else if(("delete_coupon").equalsIgnoreCase(operation)) {
			cprocess = new DeleteCoupon36Or37();
		}
		return cprocess;
	}
	
}
