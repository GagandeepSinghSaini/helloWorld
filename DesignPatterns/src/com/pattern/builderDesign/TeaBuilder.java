package com.pattern.builderDesign;

public class TeaBuilder implements BeverageBuilder{

	@Override
	public void setBeverageType() {
		System.out.println("WE ARE MAKING....tea");
		
	}

	@Override
	public void setWater() {
		System.out.println("ADD 2ML OF WATER");
		
	}

	@Override
	public void setMilk() {
		System.out.println("ADD 3ML OF MILK");
		
	}

	@Override
	public void setSugar() {
		System.out.println("ADD 1gm OF SUGAR");
		
	}

	@Override
	public void setPowderQuantity() {
		System.out.println("ADD 2GM OF POWDER");
		
	}

}
