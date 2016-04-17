package com.frys.factory;

import com.coupon.implementation.type36Or37.CouponType36Or37;
import com.frys.interfaces.FrysCouponType;

public class CouponTypeFactory {

	private static FrysCouponType ctype = null;
	
	public static FrysCouponType getCouponType(String type) {
		if(("36").equals(type) || ("37").equals(type)) {
			ctype = new CouponType36Or37();
		}
		return ctype;
	}
}
