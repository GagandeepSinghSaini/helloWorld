package com.frys.factory;

import com.coupon.implementation.DeleteCoupon;
import com.coupon.implementation.GenerateCoupon;
import com.frys.interfaces.FrysCouponProcess;

public class CouponFactory {
	private static FrysCouponProcess cprocess;
	public static FrysCouponProcess getInstance(String operation) {
		if(("generate_coupon").equalsIgnoreCase(operation)) {
			cprocess = new GenerateCoupon();
		}else if(("delete_coupon").equalsIgnoreCase(operation)) {
			cprocess = new DeleteCoupon();
		}
		return cprocess;
	}
	
}
