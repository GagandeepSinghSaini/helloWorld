package com.pattern.strategy;

public class NoBillDiscountStrategy implements ShoppingMallBill{

	@Override
	public void generateBill(int bill) {
		System.out.println("Bill(no discount): "+bill);
	}

}
