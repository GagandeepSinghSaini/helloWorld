package com.rebate.implementation;

import com.frys.interfaces.FrysRebateProcess;
import com.rebate.service.RebateService;

public class CancelRebate implements FrysRebateProcess{

	@Override
	public boolean service(RebateService service) {
		System.out.println("CancelRebate.service()");
		try {
			if(service!=null){
				if(service.checkData()) {
					return service.cancelRebate();
				} else {
					System.out.println("CancelRebate.service(): PRODUCT ALREADY DOES NOT HAVE REBATE");
				}
			}
		}catch(Exception e) {
			System.out.println("CancelRebate.service(): SOME ERROR IS THERE");
			e.printStackTrace();
		}
		return false;
	}

}
