package com.coupon.implementation;

import com.coupon.service.ServiceCoupon;
import com.frys.interfaces.FrysCouponProcess;

public class DeleteCoupon implements FrysCouponProcess {


	@Override
	public boolean service(ServiceCoupon serviceCoupon) {
		try {
			if(serviceCoupon!=null) {
				if(serviceCoupon.checkData()) {
					return serviceCoupon.expireCoupon();
				}
			}
		}catch(Exception e) {
			System.out.println("DeleteCoupon.service()");
			e.printStackTrace();
		}
		return false;
	}
}
