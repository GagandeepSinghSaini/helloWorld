package com.pattern.builderDesign;

public interface BeverageBuilder {
	void setBeverageType();
	void setWater(int waterQty);
	void setMilk(int milkQty);
	void setSugar(int sugarQty);
	void setPowderQuantity(int powderQty);
}
