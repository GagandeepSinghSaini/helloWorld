package com.pattern.strategy;

public class Client {

	public static void main(String[] args) {
		Context ctx = new Context(new HighDiscountBill());
		ctx.getBill(1000);
		
		ctx = new Context(new LowDiscountStrategy());
		ctx.getBill(1000);
		
		ctx = new Context(new NoBillDiscountStrategy());
		ctx.getBill(1000);
		
		Context context = new Context(null);
		System.out.println("(**********CUSTOMER**************)");
		String str="friday" ;
		switch (str) {
		
		case "monday":
			System.out.println("MONDAY: ");
			context.shopBill = new LowDiscountStrategy();
			break;
		case "wednesday":
			System.out.println("WEDNESDAY: ");
			context.shopBill = new NoBillDiscountStrategy();
			break;
		case "friday":
			System.out.println("FRIDAY: ");
			context.shopBill = new HighDiscountBill();
			break;
		default:
			System.out.println("NORMAL ORDER WITH NORMAL DAY");
			context.shopBill = new NoBillDiscountStrategy();
			break;
		}
		context.getBill(1000);
	}
}
