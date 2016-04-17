package com.coupon.implementation.type36Or37;

import com.frys.bean.CouponBean;
import com.frys.bean.FrysCoupBean;
import com.frys.interfaces.FrysCouponProcess;
import com.frys.interfaces.FrysCouponType;

public class CouponType36Or37 implements FrysCouponType{
	private CouponType36Or37  couponService;
	private CouponBean couponInfo;
	private FrysCoupBean coupBean;
	

	@Override
	public boolean CoupService(FrysCouponProcess cprocess) {
		return cprocess.service(couponService);
	}

	@Override
	public void setCouponServ(FrysCouponType couponService) {
		this.couponService = (CouponType36Or37) couponService;
		
	}

	@Override
	public void setUserCouponInfo(CouponBean couponInfo) {
		this.couponInfo = couponInfo;
		
	}

	
	@Override
	public void setCouponPromoBean(FrysCoupBean coupBean) {
		this.coupBean = coupBean;
	}

	@Override
	public CouponBean getUserCouponInfo() {
		return couponInfo;
	}

	@Override
	public FrysCoupBean getCouponPromoBean() {
		return coupBean;
	}

}
