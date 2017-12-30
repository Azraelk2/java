package com.example.springdemo.domain;

import java.util.ArrayList;
import java.util.List;

public class GrandParent extends Person {
private List<Parent> children;
	
	public List<Parent> getChildren(){
		return children;
	}
	
	public void setChildren(List<Parent> children) {
		this.children = children;
	}
}