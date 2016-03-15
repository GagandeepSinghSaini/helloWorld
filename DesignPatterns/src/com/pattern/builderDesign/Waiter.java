package com.pattern.builderDesign;

public class Waiter {

	public void getOrder(String beverageType) {
		BeverageBuilder beverage = null;
		if(beverageType.equals("tea")) {
			beverage = new TeaBuilder();
		}else if(beverageType.equals("coffee")){
			beverage = new CoffeeBuilder();
		}
		beverage.build();
	}
}
