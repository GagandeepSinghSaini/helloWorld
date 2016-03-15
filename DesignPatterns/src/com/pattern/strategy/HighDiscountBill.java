package com.pattern.strategy;

public class HighDiscountBill implements ShoppingMallBill{

	@Override
	public void generateBill(int bill) {
		System.out.println("High Discount Bill (50% discount): "+(bill - (bill * 0.50)));	
	}

}
