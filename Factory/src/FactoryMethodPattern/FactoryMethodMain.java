package FactoryMethodPattern;

public class FactoryMethodMain {

	public static void main(String[] args) {
		TypeMilkFactory typeMilkFactory = new TypeMilkFactory();
		
		Milk ChocoMilk = typeMilkFactory.createMilk("Choco");
		
		System.out.println(ChocoMilk.getName());

	}

}
