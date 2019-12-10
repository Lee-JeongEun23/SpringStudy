package FactoryMethodPattern;

public class TypeMilkFactory extends MilkFactory{

	@Override
	public Milk createMilk(String type) {
		Milk milk = null;
		
		switch(type) {
		case "Choco" : 
			milk = new ChocoMilk();
			break;
		case "Strawberry" : 
			milk = new StrawberryMilk();
			break;
		case "Banana" :
			milk = new BananaMilk();
			break;
		}
		
		return milk;
	}

}
