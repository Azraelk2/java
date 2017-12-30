package com.example.springdemo.domain;

import java.util.ArrayList;
import java.util.List;

public class Parent extends Person {
	private GrandParent father;
	private GrandParent mother;
	
	private List<Child> children;
	
	public GrandParent getFather() {
		return father;
	}
	
	public GrandParent getMother() {
		return mother;
	}
	
	public void setFather(GrandParent father) {
		this.father = father;
	}
	
	public void setMother(GrandParent mother) {
		this.mother = mother;
	}
	
	public List<Child> getChildren(){
		return children;
	}
	
	public void setChildren(List<Child> children) {
		this.children = children;
	}
}