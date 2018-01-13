package com.example.jdbcdemo.domain;

public class Bron {
	
	private long id;
	
	private String nazwa;
	private int kaliber;
	private double cena;
	private String typ;
	
	public Bron() {
	}
	
	public Bron(String nazwa, int kaliber, double cena, String typ) {
		super();
		this.nazwa = nazwa;
		this.kaliber = kaliber;
		this.cena = cena;
		this.typ = typ;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public int getKaliber() {
		return kaliber;
	}
	public void setKaliber(int kaliber) {
		this.kaliber = kaliber;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	
}
