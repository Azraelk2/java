package com.example.jdbcdemo.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Bron;

public class BronManagerTest {
	
	
	BronManagerJDBC bronManager = new BronManagerJDBC();
	
	private final static String NAZWA_1 = "Ak-47";
	private final static int KALIBER_1 = 7;
	private final static double CENA_1 = 1999.99;
	private final static String TYP_1 = "Palna";
	
	private final static String NAZWA_2 = "Glock";
	private final static int KALIBER_2 = 5;
	private final static double CENA_2 = 2999.99;
	private final static String TYP_2 = "Palna";
	
	private final static String NAZWA_3 = "Desert Eagle";
	private final static int KALIBER_3 = 5;
	private final static double CENA_3 = 2999.99;
	private final static String TYP_3 = "Palna";
	
	private final static String NAZWA_4 = "M4A4";
	private final static int KALIBER_4 = 5;
	private final static double CENA_4 = 3999.99;
	private final static String TYP_4 = "Palna";
	
	Bron bron1 = new Bron(NAZWA_1, KALIBER_1, CENA_1, TYP_1);
	Bron bron2 = new Bron(NAZWA_2, KALIBER_2, CENA_2, TYP_2);
	Bron bron3 = new Bron(NAZWA_3, KALIBER_3, CENA_3, TYP_3);
	Bron bron4 = new Bron(NAZWA_4, KALIBER_4, CENA_4, TYP_4);
	
	@Test
	public void checkConnection(){
		assertNotNull(bronManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		
		Bron bron = new Bron(NAZWA_1, KALIBER_1, CENA_1, TYP_1);
		
		bronManager.clearBronie();
		assertEquals(1,bronManager.addBron(bron));
		
		List<Bron> bronie = bronManager.getAllBronie();
		Bron bronRetrieved = bronie.get(0);
		
		assertEquals(NAZWA_1, bronRetrieved.getNazwa());
		assertEquals(KALIBER_1, bronRetrieved.getKaliber());
		assertEquals(CENA_1, bronRetrieved.getCena(), 5);
		assertEquals(TYP_1, bronRetrieved.getTyp());
		
	}
	
	@Test
	public void checkUpdating(){
		
		Bron bron1 = new Bron(NAZWA_1, KALIBER_2, CENA_2, TYP_2);

		assertEquals(1,bronManager.updateBron(bron1));
		
		List<Bron> bronie = bronManager.getAllBronie();
		Bron bronRetrieved = bronie.get(0);
		
		assertEquals(NAZWA_1, bronRetrieved.getNazwa());
		assertEquals(KALIBER_2, bronRetrieved.getKaliber());
		assertEquals(CENA_2, bronRetrieved.getCena(), 5);
		assertEquals(TYP_2, bronRetrieved.getTyp());
		
	}
	
	@Test
	public void checkSearching(){
		Bron x = bronManager.searchBron(NAZWA_1);

		assertEquals(NAZWA_1, x.getNazwa());
		assertEquals(KALIBER_2, x.getKaliber());
		assertEquals(CENA_2, x.getCena(), 5);
		assertEquals(TYP_2, x.getTyp());


	}
	
	@Test
	public void checkDelete() {
		
		assertEquals(1,bronManager.deleteBron(NAZWA_1));
		
		
	}
}//
