package com.coupon.implementation.type36Or37;

import com.coupon.util.HelpingCouponService;
import com.frys.interfaces.FrysCouponProcess;
import com.frys.interfaces.FrysCouponType;

public class DeleteCoupon36Or37 implements FrysCouponProcess {

	public DeleteCoupon36Or37() {
		
	}
	@Override
	public boolean service(FrysCouponType couponType) {
		try {
			if(couponType!=null) {
				HelpingCouponService couponserv = new HelpingCouponService(couponType);
				if(couponserv != null && couponserv.checkCouponData()) {
					return couponserv.expireCoupon();
				}
			}
		}catch(Exception e) {
			System.out.println("DeleteCoupon.service()");
			e.printStackTrace();
		}
		return false;
	}
}
