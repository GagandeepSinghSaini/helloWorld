package com.pattern.builderDesign;

public class CoffeeBuilder implements BeverageBuilder{
int milkQty;
int waterQty;
int sugarQty;
int powderQty;
@Override
public void setBeverageType() {
	
}
@Override
public void setWater(int waterQty) {
	this.waterQty = waterQty;
}
@Override
public void setMilk(int milkQty) {
	this.milkQty = milkQty;	
}
@Override
public void setSugar(int sugarQty) {
	this.sugarQty = sugarQty;
}
@Override
public void setPowderQuantity(int powderQty) {
	this.powderQty = powderQty;
}

	
}
