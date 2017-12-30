package com.example.springdemo.domain;

import java.util.ArrayList;
import java.util.List;

public class Child extends Person {
	private Parent father;
	private Parent mother;
	
	public Parent getFather() {
		return father;
	}
	
	public Parent getMother() {
		return mother;
	}
	
	public void setFather(Parent father) {
		this.father = father;
	}
	
	public void setMother(Parent mother) {
		this.mother = mother;
	}
}