package com.pattern.strategy;

public class Context {

	public ShoppingMallBill shopBill;
	public Context(ShoppingMallBill shopBill) {
		this.shopBill = shopBill;
	}
	 public void getBill(int bill) {
		 shopBill.generateBill(bill);
	 }
}
