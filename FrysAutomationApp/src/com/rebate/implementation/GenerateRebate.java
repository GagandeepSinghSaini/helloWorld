package com.rebate.implementation;

import com.frys.interfaces.FrysRebateProcess;
import com.rebate.service.RebateService;

public class GenerateRebate implements FrysRebateProcess{

	@Override
	public boolean service(RebateService service) {
		System.out.println("GenerateRebate.service()");
		try {
			if(service!=null){
				if(service.checkData()) {
					System.out.println("GenerateRebate.service(): DATA ALREADY EXISTS IN REBATE TABLES");
					if(service.updateRebateData()) {
		    			System.out.println("GenerateRebate.service(): SUCCESSFULLY UPDATED DATA FOR REBATE");
		    			return true;
		    		 }
				}else {
	    			 System.out.println("GenerateRebate.service(): DATA DOES NOT EXIST IN REBATE TABLES, NEED TO ADD NEW ENTRY");
	    			 if(service.insertRebateData()) {
	    				 return true;
	    			 }
	    			 System.out.println("GenerateRebate.service(): SORRY! SOME PROBLEM HAS OCCURED WHILE INSERTING DATA");
	    		 }	
			}
		}catch(Exception e) {
			System.out.println("GenerateRebate.service(): **** ERROR ******** : "+e);
			return false;
		}
		return false;
	}

}
