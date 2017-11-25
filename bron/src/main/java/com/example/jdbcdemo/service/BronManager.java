package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.util.List;

import com.example.jdbcdemo.domain.Bron;


public interface BronManager {
	
	public int addBron(Bron bron);
	int updateBron(Bron bron);
	Bron searchBron(String s);
	public List<Bron> getAllBronie();
	void clearBronie();
	Connection getConnection();
	public void addAllBronie(List<Bron> bronie);	
	public int deleteBron(String nazwa);
	
}

