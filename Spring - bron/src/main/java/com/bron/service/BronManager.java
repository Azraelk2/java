package com.bron.service;

import java.util.List;

import com.bron.domain.Bron;
import com.bron.domain.Person;
import com.bron.domain.Fabryka;

public interface BronManager {

  void addBron(Bron bron);	//bron
  List<Bron> getAllBronie();
  void removeAllBronie();
  void updateBron(Bron bron);
  void removeBron(Bron bron);
  Bron findBronByNazwa(String nazwa);
  void addFabryka(Fabryka fabryka); //fabryka
  List<Fabryka> getAllFabryki();
  void removeAllFabryki();
  void updateFabryka(Fabryka fabryka);
  void removeFabryka(Fabryka fabryka);
  Fabryka findFabrykaByNazwa(String nazwa);
  void addPerson(Person person);	//person
  List<Person> getAllPersons();
  void removeAllPersons();
  void updatePerson(Person person);
  void removePerson(Person person);
  Person findPersonByEmail(String email);
}
