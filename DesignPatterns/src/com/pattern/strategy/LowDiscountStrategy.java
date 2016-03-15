package com.pattern.strategy;

public class LowDiscountStrategy implements ShoppingMallBill {

	@Override
	public void generateBill(int bill) {
		System.out.println("Low Discount Bill (10% Discount): "+(bill - (bill*0.1)));
	}

}
