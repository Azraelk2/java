package com.example.springdemo.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.springdemo.domain.Child;
import com.example.springdemo.domain.GrandParent;
import com.example.springdemo.domain.Parent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class FamilyTest {
	@Autowired
	@Qualifier("kazimierz")
	GrandParent grandFather;
	
	@Autowired
	@Qualifier("maria")
	GrandParent grandMother;
	
	@Autowired
	@Qualifier("jan")
	Parent father;
	
	@Autowired
	@Qualifier("malgorzata")
	Parent mother;
	
	@Autowired
	@Qualifier("natalia")
	Child daughter;
	
	@Autowired
	@Qualifier("piotr")
	Child son;
	
	@Test
	public void FamilyMembersHaveNames() {
		assertEquals("Kazimierz", grandFather.getFirstName());
		assertEquals("Maria", grandMother.getFirstName());
		assertEquals("Jan", father.getFirstName());
		assertEquals("Malgorzata", mother.getFirstName());
		assertEquals("Natalia", daughter.getFirstName());
		assertEquals("Piotr", son.getFirstName());
	}
	
	@Test
	public void ChildHaveParents() {
		assertEquals("Kazimierz", father.getFather().getFirstName());
		assertEquals("Maria", father.getMother().getFirstName());
		assertEquals("Jan", daughter.getFather().getFirstName());
		assertEquals("Malgorzata", daughter.getMother().getFirstName());
		assertEquals("Jan", son.getFather().getFirstName());
		assertEquals("Malgorzata", son.getMother().getFirstName());
	}
}