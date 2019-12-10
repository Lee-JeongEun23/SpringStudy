package com.aopEx;

public class Animals {
	
	private String name;
	private int age;
	private String color;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public void getAnimalsInfo() {
		System.out.println("-----♥-♥-♥-♥-♥-♥-♥-♥-♥-♥-♥-♥-♥-♥-----");
		System.out.println("이름 : " + getName());
		System.out.println("나이 : " + getAge());
		System.out.println("색깔 : " + getColor());
		System.out.println("-----♥-♥-♥-♥-♥-♥-♥-♥-♥-♥-♥-♥-♥-♥-----");
	}
	
}
